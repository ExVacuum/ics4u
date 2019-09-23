//Silas Bartha, Sept 2019, Frequency Analysis
package freqanalysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class FreqAnalysis {

	static byte[] data;
	
	public static void main(String[] args) {

		//Intitialize
		Scanner sc = null;
		File file;
		try {
			file = new File(FreqAnalysis.class.getResource("/res/beyondgoodandevil.txt").toURI());
			sc = new Scanner(file);
		} catch (URISyntaxException | FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//2D array, in order to keep letters attached to frequencies
		int[][] freqs = new int[2][26];
		
		for(int i = 'A'; i<='Z'; i++) {
			freqs[0][i-'A']=i;
		}
		
		//Tally up all letters in the array
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			for(int i=0; i<line.length();i++) {
				char ch = line.charAt(i);
				if(ch>='a'&&ch<='z'){
					ch = Character.toUpperCase(ch);
				}
				if(ch>='A'&&ch<='Z') {
					freqs[1][ch-'A']++;
				}
			}
		}
		
		//Print Frequencies
		System.out.println("Letter Frequencies:\n");
		for(int i = 0; i<freqs[0].length; i++) {
			System.out.println((char)freqs[0][i] + ": " + freqs[1][i]);
		}
		
		//Sort both arrays by the values in the frequency array
		for (int i = 0; i < freqs[0].length; i++){
			
            for (int j = i + 1; j < freqs[0].length; j++){
            	//If greater than an element
                if (freqs[1][i] > freqs[1][j]){
                	
                	//Swap values of frequencies
                	int temp = freqs[0][i];
                    freqs[0][i] = freqs[0][j];
                    freqs[0][j] = temp;
                    
                    //Swap values of characters as well
                    temp = freqs[1][i];
                    freqs[1][i] = freqs[1][j];
                    freqs[1][j] = temp;
                }
            }
        }
		
		System.out.println("\nTop 5:\n");
		for(int i = freqs[0].length-1; i>freqs[0].length-6; i--) {
			System.out.println((char)freqs[0][i] + ": " + freqs[1][i]);
		}
		
		System.out.println("\nBottom 5:\n");
		for(int i = 0; i<5; i++) {
			System.out.println((char)freqs[0][i] + ": " + freqs[1][i]);
		}
	}
}