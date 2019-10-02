package arrays;

import java.util.Random;

public class OpenBookQuiz {
	
	//Create Array
	int width = 6, height = 5;
	int[][] arr = new int[height][width];
	Random rand = new Random();
	boolean found;
	
	public static void main(String[] args) {
		new OpenBookQuiz();
	}
	
	OpenBookQuiz(){
		
		//Fill Array
		for(int row = 0; row < height; row++) {
			for(int col = 0; col < width; col++) {
				arr[row][col] = rand.nextInt(20)+1;
			}
		}
		
		//Print Array
		for(int row = 0; row < height; row++) {
			for(int col = 0; col < width; col++) {
				System.out.printf("%-3d",arr[row][col]);
			}
			System.out.println();
		}
		
		//Find 13
		finder:
		for(int row = 0; row < height; row++) {
			for(int col = 0; col < width; col++) {
				if(arr[row][col]==13) {
					
					//Print Location
					System.out.printf("\n13 found at (%d,%d)\n", row, col);
					
					//Print Next Number
					if((col+1)<width) {
						System.out.printf("\n%d is the number after this.",arr[row][col+1]);
					}else if ((row+1)<height){
						System.out.printf("\n%d is the number after this.",arr[row+1][0]);
					}else {
						System.out.print("\nThat was the last number.");
					}
					
					//Stop once 13 found
					found = true;
					break finder;
				}
			}
		}
		
		//Handle case with no 13s
		if(!found) System.out.println("\nNo 13s found!");
	}
}
