package model;

import model.PriorityQueue.HuffmanNode;

/**
 * Provides the functionality of decoding Huffman-encoded bits
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
	 * @param encoding
	 * @param root
	 * @return
	 */
	public static String decode(int[] encoding, HuffmanNode root) {
		int length = encoding[0];
		int index = 0;
		HuffmanNode currNode = root;
		String result = "";
		
		while (index < length) {
			int currArr = (index / Integer.SIZE) + 1;
			int currPosInArr = Integer.SIZE - (index % Integer.SIZE) - 1;
			
			int currVal = encoding[currArr] >> currPosInArr & 0x1;
			System.out.printf("%d : %d : %d\n", currArr, currPosInArr, currVal);
			if (currVal == 0) {
				currNode = currNode.getLeftChild();
				System.out.println("Going left");
				
				// If the left child is a leaf, add the character associated with that node to the result
				if (currNode.isLeaf()) {
					result += currNode.getData();
					currNode = root;
					System.out.printf("Added %c, resetting\n", currNode.getData());
				}
			}
		
			// Else, travel to the right child (it must be a 1)
			else{
				currNode = currNode.getRightChild();
				System.out.println("going right");
				
				// If the right child is a leaf, add the character associated with that node to the result
				if (currNode.isLeaf()) {
					result += currNode.getData();
					currNode = root;
					System.out.printf("Added %c, resetting\n", currNode.getData());
				}
			} 
			
			index++;
		}
		
		return result;
	}
}
