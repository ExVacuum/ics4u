package strings;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;

import freqanalysis.FreqAnalysis;

public class VowelCount {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file;
		try {
			
			file = new File(FreqAnalysis.class.getResource("/res/beyondgoodandevil.txt").toURI());
			byte[] data = Files.readAllBytes(file.toPath());
			String text = new String(data, "UTF-8");
			char[] chars = text.toCharArray();
			
			//Print every vowel
			int count = 0;
			for(int i = 0; i<chars.length; i++) {
				char c = Character.toUpperCase(chars[i]);
				if(c=='A'||c=='E'||c=='I'||c=='O'||c=='U') {
					System.out.printf("%3c",chars[i]);
					count++;
					if(count%50==0) {
						System.out.println("\n" );
					}
				}
			}
			
			System.out.println("\n Vowel Count: " + count);
		} catch (URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
