package strings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;

import freqanalysis.FreqAnalysis;

public class ThirdWord {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file;
		try {
			//Build String
			StringBuilder fileData = new StringBuilder();
			file = new File(FreqAnalysis.class.getResource("/res/beyondgoodandevil.txt").toURI());
			BufferedReader reader = new BufferedReader(new FileReader(file.getPath()));
			char[] buf = new char[1024];
			int numRead = 0;
			while ((numRead = reader.read(buf)) != -1) {
				String readData = String.valueOf(buf, 0, numRead);
				fileData.append(readData);
				buf = new char[1024];
			}
	 
			reader.close();
	 
			String returnStr = fileData.toString();
			
			//Create word array
			String[] words = returnStr.split("\\s");
			
			//Print every third word
			for(int i = 0; i<words.length; i++) {
				if((i+1)%3==0) {
					System.out.print(words[i]+" ");
				}
				if((i+1)%36==0) {
				System.out.println();
				}
			}
			
		} catch (URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
