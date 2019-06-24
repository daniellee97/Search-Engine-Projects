import java.util.*;

/**
 * This class constructs heap and contains heapsort functions.
 */

public class HeapForURL {

	//initialize variables
	int heapSize;
	ArrayList<URL> priorityQueue = new ArrayList<URL>();
	
	/**
	 * constructor that sets heapSize equal to array list of urls size
	 * @param urls
	 */
	public HeapForURL(ArrayList<URL> urls) {
		heapSize = urls.size();
	}
	
	/**
	 * sets the priority queue of web page and reset their page ranks
	 * @param urls
	 */
	public void setPriorityQueue(ArrayList<URL> urls) {
		for(int i=1;i<=urls.size();i++) {
			heapInsert(urls,urls.get(i-1));
		}
		
		if(priorityQueue.size()==0) {
			for(int k=1;k<=10;k++)
				priorityQueue.add(heapExtractMax(urls));
		}
		else {
			priorityQueue.clear();
			for(int k=1;k<=10;k++)
				priorityQueue.add(heapExtractMax(urls));
		}
		heapSort(urls);
		for(int j=0;j<urls.size();j++) {
			urls.get(j).setPageRank(urls, j, urls.size()-j);
		}
	}
	
	/**
	 * get the priority queue of web page 
	 * @return priorityQueue
	 */
	public ArrayList<URL> getPriorityQueue(){ return priorityQueue;}
	
	/**
	 * returns the left child index of ith url in the list
	 * @param i 
	 * @return i*2+1
	*/
	int left(int i) { return i*2+1; }
	
	/**
	 * returns the right child index of ith url in the list
	 * @param i 
	 * @return i*2+2
	*/
	int right(int i) { return i*2+2; }
	
	/**
	 * returns the parent index of ith url in the list
	 * @param i 
	 * @return (i-1)/2
	*/
	int parent(int i) { return (i-1)/2; }
	
	/**
	 * rearrange the elements of the heap in decreasing order
	 * @param list, i
	*/
	public void maxHeapify(ArrayList<URL> list, int i) {
		int l = left(i);
		int r = right(i);
		int largest =i;
		if(l <= heapSize-1 && list.get(l).getTotalScore() > list.get(i).getTotalScore())
			largest = l;
		else
			largest = i;
		if(r<= heapSize-1 && list.get(r).getTotalScore() > list.get(largest).getTotalScore())
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
	public void buildMaxHeap(ArrayList<URL> list) {
		 heapSize = list.size();
		 for(int i = list.size()/2-1; i >=0; i--) 
			 maxHeapify(list,i); 
	}
	
	/**
	 * operate heapsort 
	 * @param list
	*/
	public void heapSort(ArrayList<URL> list) {
		buildMaxHeap(list);
		for(int i=list.size()-1;i>=0;i--) {
			exchange(0,i,list);
			heapSize--;
			maxHeapify(list,0);
		}
	}
	
	/**
	 * return the max value from heap
	 * @param list
	 * @return list.get(0)
	*/
	public URL heapMaximum (ArrayList<URL> list) { return list.get(0); }
	
	/**
	 * insert new key to heap
	 * @param list, key
	*/
	public void heapInsert(ArrayList<URL> list, URL key) {
		heapSize++;
		
		heapIncreaseKey(list,heapSize-1,key);
	}
	
	/**
	 * return max value and remove it from heap
	 * @param list
	 * @return max
	*/
	public URL heapExtractMax(ArrayList<URL> list) {
		if(heapSize<1)
			System.out.println("heap underflow");
		URL max = list.get(0);
		exchange(0, heapSize-1, list);
		heapSize--;
		maxHeapify(list,0);
		return max;
	}
	
	/**
	 * increase the key value from heap
	 * @param list, i, key
	*/
	public void heapIncreaseKey(ArrayList<URL> list, int i, URL key) {
		if (key.getTotalScore() < list.get(i).getTotalScore())
			System.out.println("new key is smaller than the current key");
		else {
			list.set(i, key);
			while (i>0 && list.get(parent(i)).getTotalScore()<list.get(i).getTotalScore()) {
				exchange(i,parent(i), list);
				i = parent(i);
			}
		}
	}
	
	/**
	 * exchange ith and jth url of the list
	 * @param i, j, list
	*/
	public void exchange(int i, int j, ArrayList<URL> list) {
		URL temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
	}
}
