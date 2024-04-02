//HuffmanNode.java

public class HuffmanNode implements Comparable<HuffmanNode>{
	char data;
	int frequency;
	HuffmanNode left;
	HuffmanNode right;
	
	HuffmanNode(char newInput, int frequency) {
		this.data = newInput;
		this.frequency = frequency;
		this.left = null;
		this.right = null;
	}
	
	int getFrequency() {
		return frequency;
	}
	
	void setLeftNode(HuffmanNode leftNode) {
		this.left = leftNode;
	}	
	
	void setRightNode(HuffmanNode rightNode) {
		this.right = rightNode;
	}
	
	@Override
	public int compareTo(HuffmanNode compared) {
		return this.frequency - compared.frequency;
	}
}
