import java.util.*;

//main class that perform simulation
public class Simulator{
	public static void main (String [] args) {
		
		//initialization
		Random rand = new Random();
		ArrayList<URL> urls = new ArrayList<>();
		ArrayList<Integer> totalScores = new ArrayList<>();
		ArrayList<Keyword> keywords = new ArrayList<>();
		String keyword;
		String urlToSearch;
		Scanner reader = new Scanner(System.in);
		boolean stage1 = true;
		boolean stage2 = true;
		
		while(stage1 == true) {
			
			//ask keyword and operate web crawler
			print("Type start or stop: ('start'/'stop') ");
			String response = reader.nextLine();
			
			//if user chooses to end the program
			if(response.toUpperCase().equals("STOP")) {
				stage1 =false;
				
				//construct heapForKeyword
				HeapForKeyword hk = new HeapForKeyword(keywords);
				
				//Print out priority queue for keyword
				ArrayList<Keyword> priorityQueue = hk.getPriorityQueue(keywords);
				print("Today's hot keywords:");
				for(int i=0;i<priorityQueue.size();i++)
					System.out.println("Keyword is " + priorityQueue.get(i).getKeyword()
							+ ", and counter is " +  priorityQueue.get(i).getCounter());

				print("Thank you!");
			}
			
			//if user wants to find keyword
			else if(response.toUpperCase().equals("START")){
				Spider spider = new Spider();
				print("Type in keyword: ");
				
				//clear previous results in the list
				urls.clear();
				totalScores.clear();
				
				//get the user input
				keyword = reader.nextLine();
				
				//check if the keyword is already in the list
				boolean foundKeyword = false;
				for(int i=0; i<keywords.size();i++) {
					if(keywords.get(i).getKeyword().equals(keyword))
						foundKeyword =true;
				}
				
				//add keyword to the list if keyword is not found
				if(!foundKeyword)
					keywords.add(new Keyword(keyword, 1));
				
				//increase counter by 1 if keyword is already in the list
				else {
					int i =0;
					for(Keyword k: keywords) {
						if(k.getKeyword().equals(keyword))
							k.increaseCounter(keywords, i);
						i++;
					}
				}
				
				//operate web crawler
				spider.search("http://google.com/", keyword);
				
				//add urls into an array list
				for(int i=1;i<=30;i++) {
					int totalScore=0;
					int index =i;
					ArrayList<Integer> factorScores = new ArrayList<>();
					for(int j=0;j<4;j++) {
						factorScores.add(rand.nextInt(100) + 1);
						totalScore+=factorScores.get(j);
					}
					totalScores.add(totalScore);
					urls.add(new URL(spider.getUrls(i-1), totalScore, factorScores, index));
				}
				
				//initialize heap and operate heapsort
				HeapForURL heap = new HeapForURL(urls);
				heap.heapSort(urls);
				
				//setting page rank
				for(int i = 0;i<urls.size();i++)
					urls.get(i).setPageRank(urls, i, urls.size()-i);
				
				//print out all urls, their indexes, factor scores, total scores, and page ranks
				print("Here are the the sorted list of urls: ");
				for(URL url: urls)
					System.out.println("Index: " + url.getIndex() + ", Url: " + url.getUrl() + ", Factor scores: " + url.getFactorScores() 
						+ ", Total score: "+ url.getTotalScore() + ", Page rank: " + url.getPageRank());
				
				//make a priority queue
				heap.setPriorityQueue(urls);
				
				//print out elements in priority queue
				print("Heap Priority Queue: ");
				for(int i=0;i<heap.getPriorityQueue().size();i++) 
					System.out.println("Url: " + heap.getPriorityQueue().get(i).getUrl() 
							+ ", and page rank is: " + heap.getPriorityQueue().get(i).getPageRank());
				
				stage2 = true;
				while(stage2==true) {
					boolean found = false;
					//ask user's url
					
					print("Type in your url: ");
					
					//get user input
					urlToSearch = reader.nextLine();
					
					
					//search url that user typed in if the url exists in the list
					for(URL url: urls) {
						if(url.getUrl().equals(urlToSearch)) {
							//make found to true if url is found
							found = true;
							
							//print out the original factor scores, total score and page rank
							System.out.println("Your factor scores are " + url.getFactorScores().toString());
							System.out.println("Your total score is " + url.getTotalScore().toString());
							System.out.println("Your page rank is " + url.getPageRank().toString());
							
							//ask if the user wants to raise their score
							print("Do you want to raise your score? (y/n)");
							
							//get user input
							String answer = reader.next();
							
							// if the answer is yes
							if(answer.equals("y") || answer.equals("Y")) {
								
								//ask an user which factor score he/she wants to increase, and how much they want to increase
								print("Which factor score do you want to increase? (1/2/3/4)");
								int factor = reader.nextInt();
								
								//check if the user typed correct number, and if so, then update total score and factor scores
								if(factor==1 || factor==2 || factor==3 || factor==4) {
									print("How much do you want to increase? ");
									int newScore = reader.nextInt();
									url.updateFactorScores(factor, newScore);
									url.updateTotalScore();
									System.out.println("Your factor scores are now " + url.getFactorScores().toString());
									System.out.println("Your total score is now " + url.getTotalScore().toString());
									
									//update priorityQueue
									heap.setPriorityQueue(urls);
									
									//print out new rank
									System.out.println("Your page rank is now " + url.getPageRank().toString());
									
									//print out heap priority queue after updating
									print("Heap Priority Queue: ");
									for(int i=0;i<heap.getPriorityQueue().size();i++) 
										System.out.println("URL: " + heap.getPriorityQueue().get(i).getUrl() 
												+ ", and page rank is " + heap.getPriorityQueue().get(i).getPageRank());
									
									break;
									
								}
								else {
									print("Please try again.");
								}
								
								
							}
							
							
							//if user doesn't want to raise his/her score, go back to the beginning
							else {
								print("Thank you");
								stage2 = false;
							}
						}
					}
					
					//if url is not existed in the list, make user retype the url
					if(found ==false && !urlToSearch.isEmpty())
						print("Sorry, please try again!");
				}
			}
			
			//if the response is wrong, print this
			else if(!response.isEmpty() && !response.toUpperCase().equals("START") && !response.toUpperCase().equals("STOP")){
				print("Sorry, please type again!");
			}
			
		}
		
	}
	
	//print method
	public static void print (String s) {
		System.out.println(s);
	}
		
}