# HuffmanEncoding

Contributors: Andrew Dennison, Chris Reid, and Adam Fehse

Huffman Encoding Overview:

To use the code within this repository, you may either convert via console or call the methods directly.  The class HuffmanConsole will provide a text interface for testing encoding and decoding.
Otherwise, you can call the methods herein statically from your own code in the following order:
  - String someInput = "";
  - HuffmanNode root = Encode.buildHuffmanTree(someInput);
  - String encodedString = Encode.encodeString(someInput, root);
  - String decodedString = Decode.decodeString(encodedString, root);
ALTERNATIVELY,
  - String someInput = "";
  - HuffmanNode root = Encode.buildHuffmanTree(someInput);
  - int[] encodedBits = Encode.encodeString(someInput, root);
  - String decodedString = Decode.decodeString(encodedBits, root);

Huffman Encoding Explanation:

Encoding works by storing the frequencies of all the characters of a String in a HashTable.
Once the frequencies are generated, a tree of HuffmanNodes is built where the root is the highest frequency character, and the leaves the lowest.
This is accomplished by maintaining a heap of HuffmanNodes in minimum heap order, with the next two lowest frequency nodes available.
Then, the lowest two frequency nodes are popped off the heap, combined into one with a null parent node, and the resulting node pushed back onto the heap.

