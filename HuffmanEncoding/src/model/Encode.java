package model;

import model.PriorityQueue.PriorityQueue;
import model.PriorityQueue.HuffmanNode;

import model.HashTable.*;

public class Encode {

	/**
	 * Encode a given input string using Huffman encoding.
	 * 
	 * @param input The input string to be encoded
	 * @return The encoded string using Huffman codes
	 */
	public static String encodeString(String input) {
		// Calculate character frequencies
		HashTable<Character, Integer> frequencyMap = calculateFrequencies(input);

		// Build Huffman tree
		HuffmanNode root = buildHuffmanTree(frequencyMap);

		// Generate Huffman codes
		HashTable<Character, String> codes = generateHuffmanCodes(root);

		// Encode the input string
		StringBuilder encodedString = new StringBuilder();
		for (char c : input.toCharArray()) {
			encodedString.append(codes.get(c));
		}

		return encodedString.toString();
	}

	/**
	 * Calculate frequencies of characters in the input string.
	 * 
	 * @param input The input string
	 * @return A map of character frequencies
	 */
	private static HashTable<Character, Integer> calculateFrequencies(String input) {
		HashTable<Character, Integer> frequencyMap = new HashTable<>();
		for (char c : input.toCharArray()) {
			frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
		}
		return frequencyMap;
	}

	private static HuffmanNode buildHuffmanTree(HashTable<Character, Integer> frequencyMap) {
		PriorityQueue queue = new PriorityQueue(frequencyMap.size());

		// Iterate over each key-value pair in frequency map
		for (Character c : frequencyMap.keySet()) {
			Integer frequency = frequencyMap.get(c);
			HuffmanNode node = new HuffmanNode(c, frequency); 
																
			queue.insert(node); 
		}

		// Build the Huffman tree by combining nodes from the priority queue
		while (queue.size() > 1) {
			HuffmanNode left = queue.popMin();
			HuffmanNode right = queue.popMin();
			HuffmanNode parent = new HuffmanNode('\0', left.getFrequency() + right.getFrequency());
			parent.setLeftChild(left);
			parent.setRightChild(right);
			queue.insert(parent);
		}

		// Return the root node of the Huffman tree
		return queue.popMin();
	}

	/**
	 * Generate Huffman codes from the Huffman tree.
	 * 
	 * @param root The root node of the Huffman tree
	 * @return A map of characters to their Huffman codes
	 */
	private static HashTable<Character, String> generateHuffmanCodes(HuffmanNode root) {
		HashTable<Character, String> codes = new HashTable<>();
		StringBuilder debugTable = new StringBuilder();
		debugTable.append("Symbol\tWeight\tHuffman Code\n");

		generateCodes(root, "", codes, debugTable);

		System.out.println(debugTable.toString());

		return codes;
	}

	/**
	 * Recursive method to generate Huffman codes for each character.
	 * 
	 * @param node       The current node in the Huffman tree
	 * @param code       The Huffman code built so far
	 * @param codes      The HashTable to store character codes
	 * @param debugTable StringBuilder to build output
	 */
	private static void generateCodes(HuffmanNode node, String code, HashTable<Character, String> codes,
			StringBuilder tableBuilder) {
		if (node == null) {
			return;
		}

		if (node.getLeftChild() == null && node.getRightChild() == null) {
			codes.put(node.getData(), code);

			tableBuilder.append(node.getData()).append("\t").append(node.getFrequency()).append("\t").append(code)
					.append("\n");
		} else {
			generateCodes(node.getLeftChild(), code + "0", codes, tableBuilder);
			generateCodes(node.getRightChild(), code + "1", codes, tableBuilder);
		}
	}

}
