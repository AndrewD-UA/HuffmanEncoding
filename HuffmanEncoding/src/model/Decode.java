package model;

import model.PriorityQueue.HuffmanNode;

/**
 * Provides the functionality of decoding Huffman-encoded bits
 * 
 * @author Andrew Dennison
 */
public class Decode {
	
	public static String decode(String encoding, HuffmanNode root) {
		char[] chars = encoding.toCharArray();
		int index = 0;
		HuffmanNode currNode = root;
		
		String result = "";
		System.out.printf("Decoding: %s\n", encoding);
		//HuffmanTest.printTree(root);
		
		while (index < encoding.length()) {
			char currentChar = chars[index];
			System.out.printf("currChar: %s index: %d data: %s\n", currentChar, index, currNode.getData());
			
			if (currentChar == '0') {
				currNode = currNode.getLeftChild();
				if (currNode.isLeaf()) {
					result += currNode.getData();
					currNode = root;
				}
			}
			
			else if (currentChar == '1') {
				currNode = currNode.getRightChild();
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
