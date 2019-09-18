//Silas Bartha, Sept. 4, 2019, Splitting Digits
package modulus;

import java.util.Random;

public class SplittingDigits {
		
	//Splitting Digits 3 Ways
	public static void main(String[] args) {
		
		//Random Double Digit Number
		Random random = new Random();
		int doubleDigit = random.nextInt(91)+10;
		System.out.println(doubleDigit);
		
		//1: Modulus Method
		int digitOne = doubleDigit/10;
		int digitTwo = doubleDigit%10;
		System.out.println(digitOne + " & " + digitTwo);
		
		//Get String for Next Methods
		String number = String.valueOf(doubleDigit);
		
		//2: Character Array
		char[] digitsChars = number.toCharArray();
		digitOne = Character.getNumericValue(digitsChars[0]);
		digitTwo = Character.getNumericValue(digitsChars[1]);
		System.out.println(digitOne + " & " + digitTwo);
		
		//3: Splitting String
		String[] digitsStrings = number.split("");
		digitOne = Integer.parseInt(digitsStrings[0]);
		digitTwo = Integer.parseInt(digitsStrings[1]);
		System.out.println(digitOne + " & " + digitTwo);
	}
}
