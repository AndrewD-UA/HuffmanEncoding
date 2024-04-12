package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

import model.Decode;
import model.Encode;
import model.HashTable.HashTable;
import model.PriorityQueue.HuffmanNode;
import model.PriorityQueue.PriorityQueue;

/**
 * A variety of test cases to demonstrate the functionality of HuffmanEncoding.
 * 
 * @author Andrew Dennison, Chris Reid, and Adam Fehse.
 */
public class HuffmanTest {
	
	@Test
	public void testEncoding() {
	    String input = "abacabad";
	    String expectedEncoded = "01001100100111";
	    HuffmanNode root = Encode.buildHuffmanTree(input);
	    String encoded = Encode.encodeString(input, root);
	    assertEquals(input, Decode.decode(encoded, root));	    
	    assertEquals(expectedEncoded, encoded);
	}
	
	@Test
	public void testEncoding1() {
	    String input = "dabacaba";
	    String expectedEncoded = "11101001100100";
	    HuffmanNode root = Encode.buildHuffmanTree(input);
	    String encoded = Encode.encodeString(input, root);
	    assertEquals(input, Decode.decode(encoded, root));	    
	    assertEquals(expectedEncoded, encoded); 
	}
	
	@Test
	public void testEncoding12() { 
	    String input = "dabacabapoyojoypp";
	    HuffmanNode root = Encode.buildHuffmanTree(input);
	    String encoded = Encode.encodeString(input, root);
	    assertEquals(input, Decode.decode(encoded, root));	    
	}
	
	@Test
	public void testEncodingArkansas() { 
	    String input = "arkansas";
	    String expectedEncoded = "011011100111110010";
	    HuffmanNode root = Encode.buildHuffmanTree(input);
	    String encoded = Encode.encodeString(input, root);
	    assertEquals(input, Decode.decode(encoded, root));	    
	    assertEquals(expectedEncoded, encoded); 
	}
	
	
	@Test
	public void testEncoding2() { // relatively different frequencies
	    String input = "abacdbdbcbab";
	    String expectedEncoded = "10010110111011101100100";
	    HuffmanNode root = Encode.buildHuffmanTree(input);
	    String encoded = Encode.encodeString(input, root);
	    assertEquals(input, Decode.decode(encoded, root));	    
	    assertEquals(expectedEncoded, encoded);
	}
	
	@Test
	public void testEncoding3() { // same frequencies
	    String input = "aabbccdd";
	    HuffmanNode root = Encode.buildHuffmanTree(input);
	    String encoded = Encode.encodeString(input, root);
	    assertEquals(input, Decode.decode(encoded, root));
	}
	
	@Test
	public void testAbraEncoding() {
		String input = "ABRACADABRA";
		
		HuffmanNode root = Encode.buildHuffmanTree(input);
		String encoded = Encode.encodeString(input, root);
		assertEquals(input, Decode.decode(encoded, root));
	}
	
	@Test
	public void testEncoding3Similar() { // similar frequencies
	    String input = "aaabbc";
	    String expectedEncoded = "000111110";
	    HuffmanNode root = Encode.buildHuffmanTree(input);
	    String encoded = Encode.encodeString(input, root);
	    assertEquals(input, Decode.decode(encoded, root));	    
	    assertEquals(expectedEncoded, encoded);
	}
	
	@Test
	public void testEncoding3Similar1() { // similar frequencies
	    String input = "aaabbcaaabbc";
	    String expectedEncoded = "000111110000111110";
	    HuffmanNode root = Encode.buildHuffmanTree(input);
	    String encoded = Encode.encodeString(input, root);
	    assertEquals(input, Decode.decode(encoded, root));	    
	    assertEquals(expectedEncoded, encoded);
	}
	
	@Test
	public void testEncoding3Similar2() {
	    String input = "zbbzznnhhytwthhhh";
	    HuffmanNode root = Encode.buildHuffmanTree(input);
	    String encoded = Encode.encodeString(input, root);
	    assertEquals(input, Decode.decode(encoded, root));
	}	
	
	@Test
	public void testEncoding4() { // kinda similar frequencies
	    String input = "aaaabbbccd";
	    String expectedEncoded = "0000101010111111110";
	 
	    HuffmanNode root = Encode.buildHuffmanTree(input);
	    String encoded = Encode.encodeString(input, root);
	    assertEquals(input, Decode.decode(encoded, root));	    
	    assertEquals(expectedEncoded, encoded);
	}
	
	@Test
	public void testEncoding5() { // very DIFF frequencies
	    String input = "aaaaabbcdbc";
	    String expectedEncoded = "00000101011111010111";
	 
	    HuffmanNode root = Encode.buildHuffmanTree(input);
	    String encoded = Encode.encodeString(input, root);
	    assertEquals(input, Decode.decode(encoded, root));	    
	    assertEquals(expectedEncoded, encoded);
	}
	
	@Test
	public void testLotsOfChars() { // A huge variety of possible characters
		String input = "abcd\n\tefghijklmnopqrstuvwxyz123456789/*--='][><.,`abcdddefasld;fkjpocixvjz";
	 
	    HuffmanNode root = Encode.buildHuffmanTree(input);
	    String encoded = Encode.encodeString(input, root);
	    assertEquals(input, Decode.decode(encoded, root));	    
	}
		
	@Test
	public void testDecodingString() {
		String input = "aaaabbbccd";
	    String expectedEncoded = "0000101010111111110";
	    
	    HuffmanNode root = Encode.buildHuffmanTree(input);
	    String encoded = Encode.encodeString(input, root);
	    assertEquals(expectedEncoded, encoded);
	    
	    String decoded = Decode.decode(encoded, root);
	    assertEquals(input, decoded);
	}
	
	@Test
	public void testEmpty() {	// An empty string
		String input = "";
		HuffmanNode root = Encode.buildHuffmanTree(input);
	    String encoded = Encode.encodeString(input, root);
	    int[] encodedBits = Encode.encodeBits(input, root);
	    
	    assertEquals(input, Decode.decode(encoded, root));
	    assertEquals(input, Decode.decode(encodedBits, root));	    
	}
	
	@Test
	public void testDecodingBits() {
		String input = "aaaabbbccd";
	    int[] encoded = new int[] {
	    		19,
	    		0xABFC000
	    };
	    
	    HuffmanNode root = Encode.buildHuffmanTree(input);
	    
	    String decoded = Decode.decode(encoded, root);
	    assertEquals(input, decoded);
	}
	
	@Test
	public void testBitOnlyOperations() {
		String input = "testString";
		HuffmanNode root = Encode.buildHuffmanTree(input);
		int[] encode = Encode.encodeBits(input, root);
		String decode = Decode.decode(encode, root);
		assertEquals(input, decode);
	}
	
	@Test
	public void testAHugeNumberOfChars() {
		StringBuilder sb = new StringBuilder();
		Random r = new Random(System.currentTimeMillis());
		
		for(int i = 0; i < 10000; i++) {
			sb.append((char) r.nextInt(50, 100));
		}
		
		HuffmanNode root = Encode.buildHuffmanTree(sb.toString());
		String encodeString = Encode.encodeString(sb.toString(), root);
		int[] encodeBits = Encode.encodeBits(sb.toString(), root);
		
		System.out.println(sb.toString());
		
		assertEquals(sb.toString(), Decode.decode(encodeBits, root));
		assertEquals(sb.toString(), Decode.decode(encodeString, root));
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
	
	@Test
	public void testQueueFunctionality() {
		PriorityQueue pq = new PriorityQueue(20);
		assertEquals(0, pq.size());
		assertEquals(null, pq.popMin());
		
		HuffmanNode low = new HuffmanNode('a', 1);
		pq.insert(low);
		assertEquals(1, pq.size());
		
		HuffmanNode mid = new HuffmanNode('b', 2);
		HuffmanNode high = new HuffmanNode('c', 3);
		pq.insert(mid);
		pq.insert(high);
		assertEquals(3, pq.size());
		
		assertEquals(low, pq.popMin());
		assertEquals(2, pq.size());

		Random r = new Random(System.currentTimeMillis());
		for (int i = 0; i < 50; i++ ) {
			pq.insert(new HuffmanNode((char) i, r.nextInt()));
		}
		
		assertEquals(52, pq.size());
	}
	
	@Test
	public void testHashTable() {
		HashTable<Character, Integer> ht = new HashTable<>();
		assertEquals(0, ht.size());
		
		ht.put('a', 0);
		int a = ht.get('a');
		assertEquals(0, a);
		
		ht.put('b', 0);
		assertEquals(2, ht.size());
		String print = "a : 0\n"
				+ "b : 0\n";
		assertEquals(print, ht.toString());
	}
}
