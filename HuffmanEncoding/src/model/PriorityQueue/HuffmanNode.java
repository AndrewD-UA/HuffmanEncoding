//HuffmanNode.java
package model.PriorityQueue;

/**
 * A basic node for use in huffman encodiong
 * 
 * @author Chris Reid
 */
public class HuffmanNode implements Comparable<HuffmanNode>{
	char data;
	int frequency;
	HuffmanNode leftChild;
	HuffmanNode rightChild;
	
	public HuffmanNode(char newInput, int frequency) {
		this.data = newInput;
		this.frequency = frequency;
		this.leftChild = null;
		this.rightChild = null;
	}
	
	public int getFrequency() {
		return frequency;
	}
	
	public void setLeftChild(HuffmanNode leftNode) {
		this.leftChild = leftNode;
	}	
	
	public void setRightChild(HuffmanNode rightNode) {
		this.rightChild = rightNode;
	}
	
	@Override
	public int compareTo(HuffmanNode compared) {
		return this.frequency - compared.frequency;
	}

	public HuffmanNode getLeftChild() {
		// TODO Auto-generated method stub
		return leftChild;
	}

	public HuffmanNode getRightChild() {
		// TODO Auto-generated method stub
		return rightChild;
	}

	public char getData() {
		// TODO Auto-generated method stub
		return data;
	}
}
