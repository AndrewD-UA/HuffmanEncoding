//HuffmanNode.java

public class HuffmanNode implements Comparable<HuffmanNode>{
	char data;
	int frequency;
	HuffmanNode leftChild;
	HuffmanNode rightChild;
	
	HuffmanNode(char newInput, int frequency) {
		this.data = newInput;
		this.frequency = frequency;
		this.leftChild = null;
		this.rightChild = null;
	}
	
	int getFrequency() {
		return frequency;
	}
	
	void setLeftChild(HuffmanNode leftNode) {
		this.leftChild = leftNode;
	}	
	
	void setRightCHild(HuffmanNode rightNode) {
		this.rightChild = rightNode;
	}
	
	@Override
	public int compareTo(HuffmanNode compared) {
		return this.frequency - compared.frequency;
	}
}
