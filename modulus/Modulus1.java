//Silas Bartha, Sept. 4, 2019, Modulus Review 1
package modulus;

public class Modulus1 {
	public static void main(String[] args) {
		
		//Print 2 columns, 0-100 in one, 0-7 repeating in the other.
		for(int i = 0; i<=100; i++) {
			System.out.printf("%-5d %-5d \n", i, i%8);
		}
	}
}
