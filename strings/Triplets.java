package strings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;

import freqanalysis.FreqAnalysis;

public class Triplets {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file;
		StringBuilder fileData = new StringBuilder();
		BufferedReader reader = null;
		
		try {
			//Build String
			file = new File(FreqAnalysis.class.getResource("/res/beyondgoodandevil.txt").toURI());
			reader = new BufferedReader(new FileReader(file.getPath()));
			char[] buf = new char[1024];
			int numRead = 0;
			while ((numRead = reader.read(buf)) != -1) {
				String readData = String.valueOf(buf, 0, numRead);
				fileData.append(readData);
				buf = new char[1024];
			}
			
			reader.close();
		} catch (URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		String returnStr = fileData.toString();
		
		//Create word array
		String[] words = returnStr.split("\\s");
		
		//Print every word with triple letters
		for(int i = 0; i < words.length; i++) {
			boolean triplet = false;
			for(int j = 0; j < words[i].length()-2; j++) {
				
				//Check if current char is equal to following 2 chars.
				if(words[i].charAt(j)==words[i].charAt(j+1)&&words[i].charAt(j)==words[i].charAt(j+2)){
					triplet = true;
				}
			}
			if(triplet){
				System.out.println(words[i]);
			}
		}
	}

}
