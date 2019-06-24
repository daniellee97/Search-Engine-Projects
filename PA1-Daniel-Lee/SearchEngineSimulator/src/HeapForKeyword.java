import java.util.*;

/**
 * This class constructs heap and contains heapsort functions.
 */
public class HeapForKeyword {
	
	//initialize a variable
	int heapSize;
	
	/**
	 * constructor that sets heapSize equal to array list of keywords size
	 * @param keywords
	 */
	public HeapForKeyword(ArrayList<Keyword> keywords) {
		heapSize = keywords.size();
	}
	
	/**
	 * gets the priority queue of keywords
	 * @param keywords
	 */
	public ArrayList<Keyword> getPriorityQueue(ArrayList<Keyword> keywords){
		ArrayList<Keyword> priorityQueue = new ArrayList<>();
		heapSort(keywords);
		if(keywords.size()<10) {
			for(int i=1;i<=keywords.size();i++)
				priorityQueue.add(keywords.get(keywords.size()-i));
			return priorityQueue;
		}
		else {
			for(int i=1;i<10;i++)
				priorityQueue.add(keywords.get(keywords.size()-i));
			return priorityQueue;
		}
		
	}
	
	/**
	 * returns the left child index of ith keyword in the list
	 * @param i 
	 * @return i*2+1
	*/
	int left(int i) { return i*2+1; }
	
	/**
	 * returns the right child index of ith keyword in the list
	 * @param i 
	 * @return i*2+2
	*/
	int right(int i) { return i*2+2; }
	
	/**
	 * returns the parent index of ith keyword in the list
	 * @param i 
	 * @return (i-1)/2
	*/
	int parent(int i) { return (i-1)/2; }
	
	/**
	 * rearrange the elements of the heap in decreasing order
	 * @param list, i
	*/
	public void maxHeapify(ArrayList<Keyword> list, int i) {
		int l = left(i);
		int r = right(i);
		int largest =i;
		if(l <= heapSize-1 && list.get(l).getCounter() > list.get(i).getCounter())
			largest = l;
		else
			largest = i;
		if(r<= heapSize-1 && list.get(r).getCounter() > list.get(largest).getCounter())
			largest =r;
		if(largest != i) {
			exchange(i,largest,list);
			maxHeapify(list, largest);
		}	
	}
	
	/**
	 * build the max heap
	 * @param list
	*/
	public void buildMaxHeap(ArrayList<Keyword> list) {
		 heapSize = list.size();
		 for(int i = list.size()/2-1; i >=0; i--) 
			 maxHeapify(list,i); 
	}
	
	/**
	 * operate heapsort 
	 * @param list
	*/
	public void heapSort(ArrayList<Keyword> list) {
		buildMaxHeap(list);
		for(int i=list.size()-1;i>=0;i--) {
			exchange(0,i,list);
			heapSize--;
			maxHeapify(list,0);
		}
	}
	
	/**
	 * exchange ith and jth keyword of the list
	 * @param i, j, list
	*/
	public void exchange(int i, int j, ArrayList<Keyword> list) {
		Keyword temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
	}
}
