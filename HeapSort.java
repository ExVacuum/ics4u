//Silas Bartha, Sept. 6, 2019, HeapSort
package heapsort;

import java.util.Random;

public class HeapSort {

	static Random random = new Random();
	
    public static void sort(int arrA[]) {
        int size = arrA.length;

        // Build max heap from Array, first non-leaf node is given by size/2-1
        for (int i = size/2-1; i >= 0; i--) {
            heapify(arrA, size, i);
        }

        // One by one extract the root element from heap and
        // replace it with the last element in the array
        for (int i=size-1; i>=0; i--) {

            //arrA[0] is a root of the heap and is the biggest element in the array
            int x = arrA[0];
            arrA[0] = arrA[i];
            arrA[i] = x;
            

            //heapify the reduced heap
            heapify(arrA, i, 0);
        }
    }

    // To heapify a subtree from node i
    static void heapify(int[] arrA, int heapSize, int i) {
        int largest = i; // Initialize largest as root
        int leftChild  = 2*i + 1; // left = 2*i + 1 e.g node arrA[0]'s left child is 2*0+1 = arrA[1] 
        int rightChild  = 2*i + 2; // right = 2*i + 2 e.g node arrA[0]'s left child is 2*0+2 = arrA[2] 

        // If left child exists and is larger than root
        if (leftChild  < heapSize && arrA[leftChild ] > arrA[largest])
            largest = leftChild;

        // If right child exists and is larger than largest so far
        if (rightChild  < heapSize && arrA[rightChild ] > arrA[largest])
            largest = rightChild;

        // If largest is not root
        if (largest != i) {
            int swap = arrA[i];
            arrA[i] = arrA[largest];
            arrA[largest] = swap;

            // Recursive call to heapify the sub-tree
            heapify(arrA, heapSize, largest);
        }
    }

    public static void main(String args[]) throws InterruptedException {
        int[] arrA = new int[100000000];
        System.out.println("Started...");
        for(int i = 0; i < arrA.length; i++) {
        	arrA[i] = random.nextInt(1000000);
        }
        
        long starttime = System.nanoTime();
        sort(arrA);
        long endtime = System.nanoTime();
        long totaltime = endtime-starttime;
        System.out.println(totaltime/1000000000.0 + " seconds.");
    }
}
