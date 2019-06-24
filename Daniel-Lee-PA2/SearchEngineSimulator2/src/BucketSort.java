import java.util.*;
// BucketSort class has the method that operates bucket sort
public class BucketSort {
	//initialize InsertionSort class
	InsertionSort is = new InsertionSort();
	
	/**
	 * This method operates bucket sort.
	 * @param urls
	 * @return urls sorted list
	 */
	public ArrayList<URL> bucketSort(ArrayList<URL> urls){
		int n = urls.size();
		Bucket[] B = new Bucket[26];   	//initialize buckets
		char index = ' ';				//initialize variable
		for(int i=0;i<B.length;i++)		
			B[i]= new Bucket();			//creating empty buckets
		for(int j=0;j<n;j++) {
			URL url = urls.get(j);
			for(int i=0;i<url.getUrl().toCharArray().length;i++) {
				if(index!=' '&&index!='w'&&index!='h'&&index!='t'&&index!='p'&&index!='/'&&index!='.') {
					index = url.getUrl().charAt(i);
					break;
				}
			}
			//store url into specific bucket
			if(index=='a') {
				B[0].bucket.add(url);
			}else if(index=='b') {
				B[1].bucket.add(url);
			}else if(index=='c') {
				B[2].bucket.add(url);
			}else if(index=='d') {
				B[3].bucket.add(url);
			}else if(index=='e') {
				B[4].bucket.add(url);
			}else if(index=='f') {
				B[5].bucket.add(url);
			}else if(index=='g') {
				B[6].bucket.add(url);
			}else if(index=='h') {
				B[7].bucket.add(url);
			}else if(index=='i') {
				B[8].bucket.add(url);
			}else if(index=='j') {
				B[9].bucket.add(url);
			}else if(index=='k') {
				B[10].bucket.add(url);
			}else if(index=='l') {
				B[11].bucket.add(url);
			}else if(index=='m') {
				B[12].bucket.add(url);
			}else if(index=='n') {
				B[13].bucket.add(url);
			}else if(index=='o') {
				B[14].bucket.add(url);
			}else if(index=='p') {
				B[15].bucket.add(url);
			}else if(index=='q') {
				B[16].bucket.add(url);
			}else if(index=='r') {
				B[17].bucket.add(url);
			}else if(index=='s') {
				B[18].bucket.add(url);
			}else if(index=='t') {
				B[19].bucket.add(url);
			}else if(index=='u') {
				B[20].bucket.add(url);
			}else if(index=='v') {
				B[21].bucket.add(url);
			}else if(index=='w') {
				B[22].bucket.add(url);
			}else if(index=='x') {
				B[23].bucket.add(url);
			}else if(index=='y') {
				B[24].bucket.add(url);
			}
			else{
				B[25].bucket.add(url);
			}
		}
		int counter =0;
		//operate insertion sort on each bucket
		for(Bucket b:B) {
			is.insertionSort(b.bucket);
			//put urls back into origianl array
			for(URL url:b.bucket) {
				urls.set(counter,url);
				counter++;
			}
		}	
		return urls;
	}
	//Bucket class that initialize bucket
	class Bucket{
		ArrayList<URL> bucket = new ArrayList<>();
	}
}