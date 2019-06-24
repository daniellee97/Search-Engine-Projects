import java.util.*;

//QuickSort class that contains quickSort, partition, exchange, and printSortedArray methods
public class QuickSort {
	
	/**
	 * Quick sort method
	 * @param A array to be sorted
	 * @param p first index of array
	 * @param r last index of array
	 */
	public void quickSort(ArrayList<URL> A, int p, int r) {
		if(p<r) {
			int q = partition(A,p,r);
			quickSort(A,p,q-1);
			quickSort(A,q+1,r);
		}
	}
	
	/**
	 * partition method
	 * @param A array to do partition
	 * @param p first index of array
	 * @param r last index of array
	 * @return i next index of pivot
	 */
	public int partition(ArrayList<URL> A, int p, int r) {
		URL x = A.get(r);
		int i = p-1;
		for(int j=p;j<=r-1;j++) {
			if(A.get(j).getTotalScore()<=x.getTotalScore()) {
				i++;
				exchange(A,i,j);
			}	
		}
		exchange(A,i+1, r);
		return i+1;
	}
	
	/**
	 * Quick sort method for keywords
	 * @param A array to be sorted
	 * @param p first index of array
	 * @param r last index of array
	 */
	public void quickSortForKeyword(ArrayList<Keyword> A, int p, int r) {
		if(p<r) {
			int q = partitionForKeyword(A,p,r);
			quickSortForKeyword(A,p,q-1);
			quickSortForKeyword(A,q+1,r);
		}
	}
	
	/**
	 * partition method for keywords
	 * @param A array to do partition
	 * @param p first index of array
	 * @param r last index of array
	 * @return i next index of pivot
	 */
	public int partitionForKeyword(ArrayList<Keyword> A, int p, int r) {
		Keyword x = A.get(r);
		int i = p-1;
		for(int j=p;j<=r-1;j++) {
			if(A.get(j).getCounter()<=x.getCounter()) {
				i++;
				exchangeForKeyword(A,i,j);
			}	
		}
		exchangeForKeyword(A,i+1, r);
		return i+1;
	}
	
	/**
	 * exchange two elements in array
	 * @param A array
	 * @param i one of the index that you want to exchange
	 * @param j the other index that you want to exchange
	 */
	public void exchange(ArrayList<URL> A, int i, int j) {
		URL temp = A.get(i);
		A.set(i, A.get(j));
		A.set(j, temp);
	}
	
	/**
	 * exchange two elements in array for keyword
	 * @param A array
	 * @param i one of the index that you want to exchange
	 * @param j the other index that you want to exchange
	 */
	public void exchangeForKeyword(ArrayList<Keyword> A, int i, int j) {
		Keyword temp = A.get(i);
		A.set(i, A.get(j));
		A.set(j, temp);
	}
	
	/**
	 * print out sorted array
	 * @param A sorted array
	 */
	public void printSortedArray(int[] A) {
		System.out.print("Sorted Array: ");
		for(int i=0;i<A.length;i++)
			System.out.print(A[i] + " ");
		System.out.println();
	}

}
