//HuffmanNode.java
package model.PriorityQueue;

/**
 * A basic node for use in huffman encoding.
 * 
 * @author Chris Reid
 */
public class HuffmanNode implements Comparable<HuffmanNode> {

	char data;
	int frequency;
	HuffmanNode leftChild;
	HuffmanNode rightChild;

	/**
	 * Create a new HuffmanNode with no children.
	 * 
	 * @param newInput  Char this HuffmanNode represents
	 * @param frequency The number of occurrences of this char.
	 */
	public HuffmanNode(char newInput, int frequency) {
		this.data = newInput;
		this.frequency = frequency;
		this.leftChild = null;
		this.rightChild = null;
	}

	/**
	 * Get the frequency of this char.
	 * 
	 * @return Frequency of the car
	 */
	public int getFrequency() {
		return frequency;
	}

	/**
	 * Set the lesser child of this node
	 * 
	 * @param leftNode Node which must be less than this node.
	 */
	public void setLeftChild(HuffmanNode leftNode) {
		this.leftChild = leftNode;
	}

	/**
	 * Set the greater child of this node
	 * 
	 * @param rightNode Node which must be greater than this node
	 */
	public void setRightChild(HuffmanNode rightNode) {
		this.rightChild = rightNode;
	}

	@Override
	public int compareTo(HuffmanNode compared) {
		return this.frequency - compared.frequency;
	}

	/**
	 * Get the huffman node with a lower frequency than this node.
	 * 
	 * @return The left child
	 */
	public HuffmanNode getLeftChild() {
		return leftChild;
	}

	/**
	 * Get the huffman node with a greater frequency than this node.
	 * 
	 * @return The right child
	 */
	public HuffmanNode getRightChild() {
		return rightChild;
	}

	/**
	 * Get the char this node represents
	 * 
	 * @return The char of this node.
	 */
	public char getData() {
		return data;
	}

	/**
	 * Determine if this node is a leaf or a parent.
	 * 
	 * @return True if a leaf, false if a parent.
	 */
	public boolean isLeaf() {
		return leftChild == null && rightChild == null;
	}
}
