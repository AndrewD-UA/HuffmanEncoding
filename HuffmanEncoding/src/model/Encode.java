package model;

import model.PriorityQueue.PriorityQueue;
import model.PriorityQueue.HuffmanNode;

import model.HashTable.*;

/**
 * Provides Huffman Encoding functionality for any String
 * 
 * @author Adam Fehse and Andrew Dennison
 */
public class Encode {

	public static final String ERROR_MESSAGE = "";

	/**
	 * Encode a given input string using Huffman encoding.
	 * 
	 * @param input The input string to be encoded
	 * @return The encoded string using Huffman codes
	 */
	public static String encodeString(String input, HuffmanNode root) {
		if (root == null) {
			return ERROR_MESSAGE;
		}

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
	 * Given a string and a HuffmanNode tree, produce an array of bits representing
	 * its Huffman encoding.
	 * 
	 * @param input The string to convert to bits
	 * @param root  The HuffmanTree encoding
	 * @return An integer array of {numChars, bits, bits, bits...}
	 */
	public static int[] encodeBits(String input, HuffmanNode root) {
		if (root == null) {
			return new int[] { 0, 0 };
		}

		int[] bits = new int[2];
		// The number of characters encoded
		int length = 0;

		// Generate Huffman codes
		HashTable<Character, String> codes = generateHuffmanCodes(root);

		// The 32 bits we are currently encoding.
		int currBits = 0;
		// The index we can next insert at.
		int outputIndex = Integer.SIZE - 1;
		// The index in the array of ints we are currently working at.
		int posInArr = 1;

		/*
		 * For each character in our given input string, convert the char to its
		 * HuffmanEncoding as a String. Then, convert the String representation of the
		 * HuffmanEncoding to a series of bits, and mask them into an Integer.
		 * 
		 * When we run out of bit space in the current integer, add more ints to the
		 * array of Integers.
		 */
		for (char c : input.toCharArray()) {
			String code = codes.get(c);
			int bitIndex = 0;
			length += code.length();

			while (bitIndex < code.length()) {
				// Resize the array if necessary
				if (outputIndex < 0) {
					bits[posInArr] = currBits; // Store the current int in the array
					posInArr++; // Move to the next int in the array
					outputIndex = Integer.SIZE - 1; // Reset our location in the current int
					currBits = 0; // Reset the current int
					bits = resizeArray(bits, bits.length + 1); // Resize the array
				}

				// Mask in the next bit
				int currCodeBit = Character.getNumericValue(code.charAt(bitIndex));
				currBits = currBits | currCodeBit << outputIndex;

				bitIndex++;
				outputIndex--;
			}
		}

		// Store the result
		bits[posInArr] = currBits;
		bits[0] = length;
		return bits;
	}

	/**
	 * Resize an array of integers to a given size.
	 * 
	 * @param arr     Array to resize
	 * @param newSize Desired size to resize arr to.
	 * @return The resized array with elements copied to the new array.
	 */
	private static int[] resizeArray(int[] arr, int newSize) {
		int[] newBits = new int[newSize];
		for (int i = 0; i < arr.length; i++) {
			newBits[i] = arr[i];
		}

		return newBits;
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

	/**
	 * Given a string, determine the frequencies of each character and store it in a
	 * huffman tree.
	 * 
	 * @param input String to form a tree out of.
	 * @return The root of the huffman tree.
	 */
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
		generateCodes(root, "", codes);
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
	private static void generateCodes(HuffmanNode node, String code, HashTable<Character, String> codes) {
		if (node == null) {
			return;
		}

		if (node.getLeftChild() == null && node.getRightChild() == null) {
			codes.put(node.getData(), code);
		} else {
			generateCodes(node.getLeftChild(), code + "0", codes);
			generateCodes(node.getRightChild(), code + "1", codes);
		}
	}
}
