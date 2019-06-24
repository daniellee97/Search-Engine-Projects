import java.util.*;

// InsertionSort class has the method that operates insertion sort.
public class InsertionSort {
	
	/**
	 * this method will operates insertion sort.
	 * @param bucket
	 * @return bucket sorted bucket
	 */
	public ArrayList<URL> insertionSort(ArrayList<URL> bucket) {
		for(int j=1;j<bucket.size();j++) {
			URL key = bucket.get(j);
			int i = j-1;
			while(i>=0 && bucket.get(i).getUrl().compareTo(key.getUrl())>0) {
				bucket.set(i+1, bucket.get(i));
				i --;
			}
			bucket.set(i+1, key);
		}
		return bucket;
	}
	
}
