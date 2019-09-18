//Silas Bartha, Sept. 4, 2019, Modulus Review 2
package modulus;

public class Modulus2 {
	public static void main(String[] args) {
		
		//Print Out Multiples of 13 Between 0 and 200
		for(int i = 0; i<=200; i++) {
			if(i%13 == 0){
				System.out.print(i + " ");
			}
		}
	}
}
