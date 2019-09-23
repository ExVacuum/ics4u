//Emily Brereton
//October 8th 2018
//ICS 4U1

/* This program finds the most common pairs of consonants.
 * Except that it doesn't. It just finds the most common pairs of letters including vowels.
 * ** Fixed my Mr. M. Harwood
 */
package freqanalysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;

public class PairsOfConsonants {

	public static void main(String[] args) {

		File file;
		int[][] freqs = new int[26][26];

		System.out.println("Frequency of letter pairs...26x26 \n");

		try {
			file = new File(FreqAnalysis.class.getResource("/res/beyondgoodandevil.txt").toURI());
			BufferedReader in = new BufferedReader (new FileReader (file));
			String line;

			//make a dictionary that maps Strings to ints

			while((line = in.readLine()) != null){ //read in each line
				line = line.toUpperCase(); //make upper case
				char[] chars = line.toCharArray();
				for(int i = 0; i < chars.length - 1; i++){ //each loop gives one character in line
					char first = chars[i]; //first of two letters
					char second = chars[i + 1]; //second of two

					//calculate frequencies
					if(Character.isLetter(first) && Character.isLetter(second)){ //only continue if there's a letter
						int firstIndex = first - 'A'; //get first position in alphabet
						int secondIndex = second - 'A';	//get second position in alphabet				

						freqs[firstIndex][secondIndex]++; //incrementing the frequency of the pair
					}
				}

			}
			in.close();
		} catch (IOException e) {
			System.out.println("File not found (or other IO error)");
			e.printStackTrace();
			System.exit(0);
		} catch(URISyntaxException e){
			e.printStackTrace();
		}

		//Print out all frequencies
		System.out.print("     ");
		for (int i = 0; i < 26; i++) { //reads down each row of letters
			System.out.printf("%-5s", (char)(i+'A'));
		}
		System.out.println();
		for (int i = 0; i < 26; i++) { //reads down each row of letters
			System.out.printf("%-5s", (char)(i+'A'));
			for (int k = 0; k < 26; k++) { //reads across each column 
				System.out.printf("%-5d",freqs[i][k]); //prints frequency of i & k pair
			}
			System.out.print("\n");
		}

		/******** find 10 most common *******/

		//maxValue variable
		//loop through freqs

		int max = 0;
		int maxFirstPosition = 0;
		int maxSecondPosition = 0;
		int topSize=10; // this is how many of the top pairs to print.
		String vowels = "AEIOU";

		System.out.println("\n");
		System.out.println(topSize + " most common pairs:");

		for (int n = 0; n < topSize; n++) {
			for (int i = 0; i < 26; i++) { //reads down each row of letters
				for (int k = 0; k < 26; k++) { //reads across each column 
					int currentCount = freqs[i][k]; //count of current ik pair

					if (currentCount > max) { //if larger than current max
						max = currentCount; //make max equal to this count
						maxFirstPosition = i; //keep i position
						maxSecondPosition = k; //keep k position
					}
				}
			}

			char first = (char) ('A' + maxFirstPosition); //get position in alphabet back to character
			char second = (char) ('A' + maxSecondPosition);

			//skip the vowels:
			if (vowels.contains(""+first) || vowels.contains(""+second)) {
				n--;
				//continue;
				//No. We cannot continue here as we just repeat the same pair of letters over and over. 
				//    We must do the rest of the for loop.
			}
			else {
				// Print the current max
				System.out.print(first); 
				System.out.print(second);
				System.out.println(": " + max + " occurences");
			}
			
			// Zero out this max to find new max later
			freqs[maxFirstPosition][maxSecondPosition] = 0;

			// Reset for the next run of the loop
			max = 0;
			maxFirstPosition = 0;
			maxSecondPosition = 0;
		}

	}
}
//Track the frequency of each  pair of letters
//Print out the array as well as the 10 most common pairs of consonants
//this will be a 26x26 array
