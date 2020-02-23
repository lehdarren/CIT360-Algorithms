
import java.util.Arrays;

public class HeapTester {

	public static void main(String[] args) {
		MaxHeap<Integer> heap = new MaxHeap<Integer>();
		int num;
		Integer[] A = new Integer[100];
		for (int i = 0; i < 100; i++) {
			num = (int) (Math.random() * 1000);
			A[i] = num;
			heap.add(num);
		}
		while (!heap.isEmpty())
			System.out.println(heap.remove());
	}

	public static <T extends Comparable<? super T>> void heapSort(T[] A) {

		// assuming that there is room in array
		int len = A.length;
		int index;
		int parent;
		T tempElement;
		
		//heapify
		for (int i = 0; i < len; i++) {
			// percolate up
			index = i;
			parent = (index - 1) / 2;
			

			while (parent >= 0) {
				// parent = (index - 1) / 2;
				if (A[index].compareTo(A[parent]) <= 0)
					break;
				// otherwise swap


				index = parent;
				parent = (index - 1) / 2;
						tempElement = A[parent];
				A[parent] = A[index];
				A[index] = tempElement;	}
		}
		
		
		
		for(int i = 0; i < len; i++) {
			//swap A[0] with A[len - i]
			tempElement = A[i];
			A[i] = A[len - i];
			A[len - i] = tempElement;
			
			//let A[0] sink down within the range 0..len-i-1
			
			
		}

	}

}
