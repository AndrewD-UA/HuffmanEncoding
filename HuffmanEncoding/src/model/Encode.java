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
	public static String encodeString(String input, HuffmanNode root) {
		// Generate Huffman codes
		HashTable<Character, String> codes = generateHuffmanCodes(root);

		// Encode the input string
		StringBuilder encodedString = new StringBuilder();
		for (char c : input.toCharArray()) {
			//System.out.printf("%c : %s\n", c, codes.get(c));
			encodedString.append(codes.get(c));
		}

		return encodedString.toString();
	}
	
	public static int[] encodeBits(String input, HuffmanNode root) {
		int[] bits = new int[2];
		
		// Generate Huffman codes
		HashTable<Character, String> codes = generateHuffmanCodes(root);

		// The 32 bits we are currently encoding
		int currBits = 0;
		// The index we can next insert at
		int index = 0;
		for (char c : input.toCharArray()) {
			String code = codes.get(c);
			int codeBits = Integer.parseInt(code);
			int numBits = code.length();
			
			if (numBits > Integer.SIZE - index) {
				// Add the remaining bits, then copy array
			}
			
			//System.out.printf("%c : %s\n", c, codes.get(c));
		}

		return bits;
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
		//System.out.printf("Table built: \n%s\n", frequencyMap.toString());
		return frequencyMap;
	}

	public static HuffmanNode buildHuffmanTree(String input) {
		HashTable<Character, Integer> frequencyMap = calculateFrequencies(input);
		
		PriorityQueue queue = new PriorityQueue(frequencyMap.size());

		// Iterate over each key-value pair in frequency map
		for (char c : frequencyMap.keySet()) {
			Integer frequency = frequencyMap.get(c);
			HuffmanNode node = new HuffmanNode(c, frequency); 
																
			queue.insert(node); 
		}

		// Build the Huffman tree by combining nodes from the priority queue
		while (queue.size() > 1) {
			HuffmanNode left = queue.popMin();
			HuffmanNode right = queue.popMin();
			HuffmanNode parent = new HuffmanNode('\0', left.getFrequency() + right.getFrequency());
			//System.out.printf("New parent from %c, %c\n", left.getData(), right.getData());
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
		//debugTable.append("Symbol\tWeight\tHuffman Code\n");

		generateCodes(root, "", codes, debugTable);

		//System.out.println(debugTable.toString());

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

			tableBuilder.append(String.format("%c\t%d\t%s\n", node.getData(), node.getFrequency(), code));
		} else {
			generateCodes(node.getLeftChild(), code + "0", codes, tableBuilder);
			generateCodes(node.getRightChild(), code + "1", codes, tableBuilder);
		}
	}

}
