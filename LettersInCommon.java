package strings;

public class LettersInCommon {

	public static void main(String[] args) {
		String[] words = {"guyana", "lotion", "chilly", "doggie", "fledgy", "swanky", "crawly", "physic"};
		String compWord = "digger";
		int[] counts = new int[words.length];
		
		//Get array of chars for "digger"
		char[] compChars = compWord.toCharArray();
		
		//For each word
		for(int i = 0; i<words.length; i++) {
			
			//Get array of chars for the current word
			char[] sChars = words[i].toCharArray();
			
			//Count matches
			for(char c: sChars) {
				for(int j = 0; j<compChars.length; j++){	
					if(compChars[j]==c) {
						counts[i]++;
						
						//Break if match found for this character
						break;
					}
				} 
			}
		}
		
		//Sort to get max count and print it
		heapsort.HeapSort.sort(counts);
		System.out.println(counts[counts.length-1]);
	}

}
