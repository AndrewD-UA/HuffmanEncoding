package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.Encode;
import model.PriorityQueue.HuffmanNode;

public class HuffmanTest {
	
	@Test
	public void testEncoding() { //PASS
	    String input = "abacabad";
	    String expectedEncoded = "01001100100111";
	    System.out.print("input: "+input + "\n");
	    String encoded = Encode.encodeString(input);
	    assertEquals(expectedEncoded, encoded);
	}
	
	@Test
	public void testEncoding1() { //PASS
	    String input = "dabacaba";
	    String expectedEncoded = "11101001100100";
	    System.out.print("input: "+input + "\n");
	    String encoded = Encode.encodeString(input);
	    assertEquals(expectedEncoded, encoded); 
	}
	
	@Test
	public void testEncoding12() { 
	    String input = "dabacabapoyojoypp";
	    String expectedEncoded = "";
	    System.out.print("input: "+input + "\n");
	    String encoded = Encode.encodeString(input);
	    assertEquals(expectedEncoded, encoded); 
	}
	
	@Test
	public void testEncodingArkansas() { 
	    String input = "arkansas";
	    String expectedEncoded = "110001011011101110";
	    System.out.print("input: "+input + "\n");
	    String encoded = Encode.encodeString(input);
	    assertEquals(expectedEncoded, encoded); 
	}
	
	
	@Test
	public void testEncoding2() { //PASS (relatively different frequencies)
	    String input = "abacdbdbcbab";
	    String expectedEncoded = "10010110111011101100100";
	    System.out.print("input: "+input + "\n");
	    String encoded = Encode.encodeString(input);
	    assertEquals(expectedEncoded, encoded);
	}
	
	@Test
	public void testEncoding3() { //FAIL (same frequencies)
	    String input = "aabbccdd";
	    String expectedEncoded = "0000010110101111";
	    System.out.print("input: "+input + "\n");
	    String encoded = Encode.encodeString(input);
	    assertEquals(expectedEncoded, encoded);
	}
	
	
	
	@Test
	public void testEncoding3Similar() { //PASS (similar frequencies)
	    String input = "aaabbc";
	    String expectedEncoded = "000111110";
	    System.out.print("input: "+input + "\n");
	    String encoded = Encode.encodeString(input);
	    assertEquals(expectedEncoded, encoded);
	}
	
	@Test
	public void testEncoding3Similar1() { //PASS (similar frequencies)
	    String input = "aaabbcaaabbc";
	    String expectedEncoded = "000111110000111110";
	    System.out.print("input: "+input + "\n");
	    String encoded = Encode.encodeString(input);
	    assertEquals(expectedEncoded, encoded);
	}
	
	@Test
	public void testEncoding3Similar2() { //FAIl
	    String input = "zbbzznnhhytwthhhh";
	    String expectedEncoded = "00010010000001101111111011100101010011111111";
	    String encoded = Encode.encodeString(input);
	    assertEquals(expectedEncoded, encoded);

	}
	
	
	
	@Test
	public void testEncoding4() { //PASS (kinda similar frequencies)
	    String input = "aaaabbbccd";
	    String expectedEncoded = "0000101010111111110";
	 
	    String encoded = Encode.encodeString(input);
	    assertEquals(expectedEncoded, encoded);
	}
	
	@Test
	public void testEncoding5() { //PASS (very DIFF frequencies)
	    String input = "aaaaabbcdbc";
	    String expectedEncoded = "00000101011111010111";
	 
	    String encoded = Encode.encodeString(input);
	    assertEquals(expectedEncoded, encoded);
	}
	
	

	
	@Test
	public void testDecoding() {	
	}
	
	@Test
	public void testNodeFunctionality() {
	    // Test node creation
	    HuffmanNode nodeA = new HuffmanNode('a', 3);
	    HuffmanNode nodeB = new HuffmanNode('b', 2);
	    
	    // Test compareTo method
	    assertTrue(nodeA.compareTo(nodeB) > 0); // 'a' has higher frequency than 'b'
	    
	    // Test setting and getting child nodes
	    HuffmanNode leftChild = new HuffmanNode('c', 1);
	    HuffmanNode rightChild = new HuffmanNode('d', 4);
	    HuffmanNode parentNode = new HuffmanNode('\0', 5);
	    parentNode.setLeftChild(leftChild);
	    parentNode.setRightChild(rightChild);
	    
	    assertEquals(leftChild, parentNode.getLeftChild());
	    assertEquals(rightChild, parentNode.getRightChild());
	}

}
