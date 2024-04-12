package controller_view;

import java.util.Scanner;

import model.Decode;
import model.Encode;
import model.PriorityQueue.HuffmanNode;

public class HuffmanConsole {

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		
		while(true) {			
			System.out.print("Please enter a string: ");
			String input = scanner.nextLine();
			
			HuffmanNode root = Encode.buildHuffmanTree(input);
			String stringBits = Encode.encodeString(input, root);
			int[] intBits = Encode.encodeBits(input, root);
			
			System.out.printf("%s has been encoded as %s\n", input, stringBits);
			System.out.print("Additionally, the bits are equivalent to: ");
			for(int i = 1; i < intBits.length; i++) {
				System.out.print(intBits[i]);
			}
			System.out.print(" in base 10\n");
			
			System.out.println("\nTo demonstrate functionality, we will now convert back to the original string.");
			
			String resultString = Decode.decode(stringBits, root);
			String resultBits = Decode.decode(intBits, root);
			
			System.out.printf("\nThe String of 1s and 0s was read as: %s\n"
							+ "The integer(s) presented above were read in binary as: %s\n",
								resultString, resultBits);
			
			System.out.print("Would you like to try another String? (Y/N) ");
			if(scanner.next().equalsIgnoreCase("N")){
				break;
			}
			System.out.println();
			scanner.nextLine();
			
		}
		
		scanner.close();
	}
}
