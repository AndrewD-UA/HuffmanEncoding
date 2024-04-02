//HuffmanEncoding.java
public class HuffmanEncoding {
	public static void main(String[] args) {
		HuffmanNode node1 = new HuffmanNode('a', 1);
		HuffmanNode node2 = new HuffmanNode('b', 2);
		HuffmanNode node3 = new HuffmanNode('c', 3);
		HuffmanNode node4 = new HuffmanNode('d', 4);
		
		node1.setLeftNode(node2);
		node1.setRightNode(node3);
		node2.setLeftNode(node4);
		
		System.out.println(node1.compareTo(node4)); 
		
		System.out.println();
	}

}


