package strings;

import java.util.Random;

public class RandomWord {
	public static void main(String[] args) {
		
		//Initialize
		Random random = new Random();
		char[] chars = new char[6];
		
		//Get number of vowels and positions of vowels
		int num = random.nextInt(2)+1;
		int pos1 = random.nextInt(6);
		int pos2 = pos1;
		if(num==2) {
			while(pos2 == pos1) {
				pos2 = random.nextInt(6);
			}
		}
		
		//Fill array with random vowels and consonants.
		for(int i = 0; i<chars.length; i++) {
			if(i==pos1||(i==pos2&&num==2)) {
				int vowel = random.nextInt(5);
				switch(vowel){
				case 0:
					chars[i] = 'A';
					break;
				case 1:
					chars[i] = 'E';
					break;
				case 2:
					chars[i] = 'I';
					break;
				case 3:
					chars[i] = 'O';
					break;
				case 4:
					chars[i] = 'U';
					break;
				}
			}else{
				char c = 'A';
				while(c=='A'||c=='E'||c=='I'||c=='O'||c=='U') {
					c = (char)('A' + random.nextInt(26));
				}
				chars[i] = c;
			}
		}
		
		//Create string and print
		String word = new String(chars);
		System.out.println(word);
	}
}
