# HuffmanEncoding

**Contributors: Andrew Dennison, Chris Reid, and Adam Fehse**

**Huffman Encoding Overview:**

To use the code within this repository, you may either convert via console or call the methods directly.  The class HuffmanConsole will provide a text interface for testing encoding and decoding.
Otherwise, you can call the methods herein statically from your own code in the following order:
  - String someInput = "";
  - HuffmanNode root = Encode.buildHuffmanTree(someInput);
  - String encodedString = Encode.encodeString(someInput, root);
  - String decodedString = Decode.decode(encodedString, root);

ALTERNATIVELY,
  - String someInput = "";
  - HuffmanNode root = Encode.buildHuffmanTree(someInput);
  - int[] encodedBits = Encode.encodeBits(someInput, root);
  - String decodedString = Decode.decode(encodedBits, root);

**Huffman Encoding Explanation:**

Encoding works by storing the frequencies of all the characters of a String in a HashTable.
Once the frequencies are generated, a tree of HuffmanNodes is built where the root is the highest frequency character, and the leaves the lowest.
This is accomplished by maintaining a heap of HuffmanNodes in minimum heap order, such that the next two lowest frequency nodes available next.
Then, the lowest two frequency nodes are popped off the heap, combined into one with a null parent node, and the resulting node pushed back onto the heap.

**For the encoding portion:**
After the tree is constructed, each leaf node is read back into a Hashtable where a character is mapped to its String sequence of 1s and 0s.
A 0 corresponds to the left child, while a 1 corresponds to the right child.

  For String encodings:
    - A String is returned, built using the 1s and 0s pulled from the HashTable for each character of the input String.
  For Bit encodings:
   -  An array of integers are returned.  The first integer is the number of characters processed, while the rest of the integers read in binary as an encoding of the tree from left to right.

**For the decoding portion:**

The decoding algorithm is provided the same tree used to construct the nodes, as well as the encoded String/bits.

  For String decodings:
    - A String is read in as a sequence of 1s and 0s.  The 1s and 0s correspond to the path traveled down the Huffman tree such that when a leaf node is reached, the decoding of that character is complete.
    - When the end of the String is reached, decoding is terminated.

  For Bit decodings:
    - The bits are read out of the integers provided, left-to-right, corresponding to the traversal of the Huffman tree described above.
    - The first integer in the int array is equivalent to the number of leaf nodes that need to be read.
    - When the number of leaf nodes read reaches the first integer's value, decoding is terminated and the String returned.

**DATA STRUCTURES USED**

**HuffmanNode**
- A HuffmanNode is an object with a character value and a pointer to a left and right child.
- The character value can be '\0', or a null character, when the node is an interior node.
- Otherwise, the character value is a particular character corresponding to the result of any given traversal.  This occurs when the node is a leaf node.
- The structure is built and maintained recursively, so the root of any HuffmanTree is a single HuffmanNode that is passed around.

**HashTable<K, V>**
- A generic Hashtable is available, which is used as both a <Character, Integer> table and <Character, String> table.
- The generic Hashtable resolves collisions via separate chaining.
- The <Character, Integer> table stores frequencies of characters as read in the input string, e.g. in "Hello" the frequency of the character 'l' is 2 and all other characters are frequency 1.
- The <Character, String> table stores the String encoding of each Character in a table.
  - Assume two characters 'a' and 'b' are encoded, both with frequency 1. Thus, the tree consists of a '\0' root node which has left child 'a' and right child 'b'.
  - Thus, the encoding of the 'a' is 0 and the encoding of 'b' is 1
  - The Hashtable then stores the character 'a' with the String "0", as well as the character 'b' with the String "1"

**PriorityQueue**
- A PriorityQueue is maintained, which acts a min heap.
- It is self-organizing and self-resizing, so the only requirement is to create HuffmanNOdes outside of the table before passing them in
- The PriorityQueue will always keep the HuffmanNode with the lowest frequency available at the "root", or index 0, of the queue.

**LIMITATIONS**
The primary limitation of our approach is the '\0' character.  Because the null character is used to mark interior nodes, attempting to encode null characters can throw off the processing of a String.
Depending on the placement of the null character in the String, it may either skip processing the null character, or produce an invalid path that causes the Decoding function to return the error String.
We chose to use the null character because using a true null value to represent interior nodes breaks the comparison of nodes in the PriorityQueue and building of the HuffmanTree, because nulls cannot
be compared to chars.
Thus, we have built one of our JUnit tests to specify that Strings with null characters never encode and decode to the same String that was input.d
