package model;

import model.PriorityQueue.HuffmanNode;

/**
 * Provides the functionality of decoding Huffman-encoded bits.
 * 
 * Facilitates both integer array and String versions of a Huffman encoding.
 * 
 * @author Andrew Dennison
 */
public class Decode {
	
	/**Given the root of a Huffman tree and the encoding, decode it to English characters.
	 * 
	 * @param encoding	String representation of 0s and 1s for the encoded data
	 * @param root		root of a Huffman tree which contains the decoding
	 * @return			The decoded String
	 */
	public static String decode(String encoding, HuffmanNode root) {
		char[] chars = encoding.toCharArray();
		int index = 0;
		HuffmanNode currNode = root;		
		String result = "";
		
		// For each character in the String
		while (index < encoding.length()) {
			char currentChar = chars[index];
			
			// Travel to the left child if it's a 0
			if (currentChar == '0') {
				currNode = currNode.getLeftChild();
				
				// If the left child is a leaf, add the character associated with that node to the result
				if (currNode.isLeaf()) {
					result += currNode.getData();
					currNode = root;
				}
			}
			
			// Else, travel to the right child (it must be a 1)
			else{
				currNode = currNode.getRightChild();
				
				// If an invalid tree was submitted
				if (currNode == null) {
					return "Please create a tree before trying to decode!";
				}
				
				// If the right child is a leaf, add the character associated with that node to the result
				if (currNode.isLeaf()) {
					result += currNode.getData();
					currNode = root;
				}
			} 
			
			index++;
		}
		return result;
	}
	
	/**
	 * This simulates receiving a contiguous block of 1s and 0s as ints.
	 * 
	 * The first int is always the length of the transmissions. The remaining ints are 
	 * the encoding as 1s and 0s.  The ending of the transmission is assumed to be zero-padded.
	 * 
	 * @param encoding	The integer array representing the bits of the encoding
	 * @param root		The root of the huffman tree
	 * @return			The decoded String
	 */
	public static String decode(int[] encoding, HuffmanNode root) {
		int length = encoding[0];
		int index = 0;
		HuffmanNode currNode = root;
		String result = "";
		
		while (index < length) {
			// How far into the Integer array we are currrently
			int currArr = (index / Integer.SIZE) + 1;
			// Which bit of the current Integer needs to be shifted out
			int currPosInArr = Integer.SIZE - (index % Integer.SIZE) - 1;
			
			// Shift and mask out the current bit
			int currVal = encoding[currArr] >> currPosInArr & 0x1;
			if (currVal == 0) {
				currNode = currNode.getLeftChild();
				
				// If the left child is a leaf, add the character associated with that node to the result
				if (currNode.isLeaf()) {
					result += currNode.getData();
					currNode = root;
				}
			}
		
			// Else, travel to the right child (it must be a 1)
			else{
				currNode = currNode.getRightChild();
				
				// If the right child is a leaf, add the character associated with that node to the result
				if (currNode.isLeaf()) {
					result += currNode.getData();
					currNode = root;
				}
			} 
			
			index++;
		}
		
		return result;
	}
}
