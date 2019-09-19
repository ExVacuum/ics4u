//Silas Bartha, Sept. 4, 2019, Modulus Practice 3
package modulus;

public class Modulus3 {
	public static void main(String[] args) {
		
		//Print Integers From 1 to 200, Inserting Line Break after Every Multiple of 12.
		for(int i = 1; i<=200; i++){
			System.out.printf(" %-3d ", i);
			if(i%12==0) {
				System.out.println();
			}
		}
	}
}
