import java.util.*;

//main class that perform simulation of search engine
public class Simulator {
	public static void main (String [] args) {
		
		//initialization
		Random rand = new Random();
		ArrayList<URL> urls = new ArrayList<>();
		ArrayList<URL> originalArray = new ArrayList<>();
		ArrayList<URL> topUrls = new ArrayList<>();
		ArrayList<Integer> totalScores = new ArrayList<>();
		ArrayList<Keyword> keywords = new ArrayList<>();
		String keyword;
		String urlToSearch;
		Scanner reader = new Scanner(System.in);
		boolean stage1 = true;
		boolean stage2 = true;
		BinarySearchTree bst = new BinarySearchTree();
		int counterForIndex = 0;
		QuickSort qs = new QuickSort();
		int counterOfTopKeyword = 0;
		String topKeyword = null;
		int indexOfTopKeyword;
		
		while(stage1 == true) {
			
			//ask keyword and operate web crawler
			print("Type start or stop: ('start'/'stop') ");
			String response = reader.next();
			
			//if user chooses to end the program
			if(response.toUpperCase().equals("STOP")) {
				stage1 =false;
				
				//operate quick sort to sort keyword list
				qs.quickSortForKeyword(keywords, 0, keywords.size()-1);
				
				//make an arraylist of top 10 keywords
				ArrayList<Keyword> popularKeywords = new ArrayList<>();
				if(keywords.size()>10) {
					for(int i=keywords.size()-1;i>keywords.size()-11;i--)
						popularKeywords.add(keywords.get(i));
				}
				else {
					popularKeywords = keywords;
				}
				
				
				//Print out popular keywords
				print("Today's hot keywords:");
				for(int i=0;i<popularKeywords.size();i++)
					System.out.println(popularKeywords.get(i).getKeyword() + " ");
				
				//operate bucket sort and print out the lists of urls of top keyword
				BucketSort bs = new BucketSort();
				bs.bucketSort(topUrls);
				print("List of urls of top keyword(" + topKeyword + ")");
				for(int i=0;i<topUrls.size();i++) {
					print(topUrls.get(i).getUrl());
				}
				
				print("Thank you!");
			}
			
			//if user wants to find keyword
			else if(response.toUpperCase().equals("START")){
				//clear previous results in the list
				bst.clearTree(urls);
				urls.clear();
				originalArray.clear();
				totalScores.clear();
				
				Spider spider = new Spider();
				print("Type in keyword: ");
				
				//get the user input
				keyword = reader.next();
				
				//check if the keyword is already in the list
				boolean foundKeyword = false;
				for(int i=0; i<keywords.size();i++) {
					if(keywords.get(i).getKeyword().equals(keyword)) {
						foundKeyword =true;
						indexOfTopKeyword = i;
					}
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
				spider.search("http://www.sjsu.edu/", keyword);
				counterForIndex = 0;
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
					URL a = new URL(spider.getUrls(i-1), totalScore, factorScores, index);
					urls.add(a);
					
				}
				//copy the original array
				originalArray.addAll(urls);
				
				//set top keyword and keep the top keyword's lists of urls
				if(keywords.get(keywords.size()-1).getCounter()>counterOfTopKeyword) {
					counterOfTopKeyword = keywords.get(keywords.size()-1).getCounter();
					topKeyword = keywords.get(keywords.size()-1).getKeyword();
					topUrls.clear();
					topUrls.addAll(originalArray);
				}
				
				//initialize quick sort and operate QuickSort
				qs.quickSort(urls, 0, 29);
				
				//setting page rank
				for(int i = 0;i<urls.size();i++)
					urls.get(i).setPageRank(urls, i, urls.size()-i);
				
				//insert to bst
				for(int i=0;i<originalArray.size();i++)
					bst.treeInsertion(originalArray.get(i));
				
				//print out all urls, their indexes, factor scores, total scores, and page ranks
				print("");
				print("Here are the the sorted list of urls: ");
				for(URL url: urls)
					System.out.println("Index: " + url.getIndex() + ", Url: " + url.getUrl() + ", Factor scores: " + url.getFactorScores() 
						+ ", Total score: "+ url.getTotalScore() + ", Page rank: " + url.getPageRank());
				
				print("original array");
				for(URL url:originalArray) {
					print(url.getUrl());
				}
				
				stage2 = true;
				while(stage2==true) {
					boolean found = false;
					print("Which operation would you like to do? (1, 2, 3, 4, 5) \n"
							+ "1: Look up URL by page rank \n"
							+ "2: Insert URL into Binary Search Tree \n"
							+ "3: Delete URL from Binary Search Tree \n"
							+ "4: Make a sorted list of urls by using BST sort and show results \n"
							+ "5: Restart the program");
					int option = reader.nextInt();
					
					//User wants to look up URL by page rank
					if(option ==1) {
						//ask user to type in page rank
						print("Type in page rank: ");
						int pageRankToSearch = reader.nextInt();
						
						//if it founds the node
						if(bst.iterativeTreeSearch(pageRankToSearch)!=null) {
							found = true;
							//print out the original factor scores, total score and page rank
							System.out.println("The URL is " + bst.iterativeTreeSearch(pageRankToSearch).key.getUrl());
							System.out.println("The index is " + bst.iterativeTreeSearch(pageRankToSearch).key.getIndex());
							System.out.println("The page rank is " + bst.iterativeTreeSearch(pageRankToSearch).key.getPageRank());
							System.out.println("The factor scores are " + bst.iterativeTreeSearch(pageRankToSearch).key.getFactorScores().toString());
							System.out.println("The total score is " + bst.iterativeTreeSearch(pageRankToSearch).key.getTotalScore().toString());
						}
						
						//if user types in wrong page rank
						if(found ==false)
							print("Sorry, please try again!");
					}
					
					//User wants to insert URL into Binary Search Tree
					else if(option == 2) {
						print("Type in URL: ");
						String url = reader.next();
						ArrayList<Integer> factorScores = new ArrayList<>();
						int totalScore = 0;
						
						for(int i=0;i<4;i++) {
							factorScores.add(rand.nextInt(100) + 1);
							totalScore += factorScores.get(i);
						}
						counterForIndex++;
						//create new url
						URL newUrl = new URL(url, totalScore, factorScores, 30+counterForIndex);
						//insert new url into array list urls
						if(topKeyword.equals(keyword))
							topUrls.add(newUrl);
						urls.add(newUrl);
						originalArray.add(newUrl);
						//reset page rank
						qs.quickSort(urls, 0, urls.size()-1);
						for(int i=0;i<urls.size();i++)
							urls.get(i).setPageRank(urls, i, urls.size()-i);
						//insert new url to binary search tree
						bst.treeInsertion(newUrl);
						//show tree
						bst.showTree();
					}
					
					//User wants to delete URL from Binary Search Tree
					else if(option == 3) {
						//ask user which url to delete
						print("Type in URL that you want to delete: ");
						//initialize dummy variable
						URL u = new URL(null, 0,null,0);
						String urlToDelete = reader.next();
						found = false;
						for(URL url: urls) {
							if(url.getUrl().equals(urlToDelete)) {
								found = true;
								u = url;
							}
						}
						if(found == true) {
							//remove url from array list urls
							urls.remove(u);
							originalArray.remove(u);
							//remove url from binary search tree
							bst.treeDeletion(bst.iterativeTreeSearch(u.getPageRank()));
							//operate quick sort
							qs.quickSort(urls, 0, urls.size()-1);
							
							//reset the page rank
							for(int i=0;i<urls.size();i++)
								urls.get(i).setPageRank(urls, i, urls.size()-i);
							//show tree
							bst.showTree();
						}
						//if user typed in wrong url
						else
							print("Please try again!");
					}
					
					//User wants to sort Binary Search Tree and see the results
					else if (option == 4) {
						//clear tree once to avoid duplication
						bst.clearTree(urls);
						//bst sorting 
						bst.BSTSort(originalArray);
					}
					
					//User wants to restart the program
					else if(option == 5) {
						
						stage2 = false;
					}
					
					//User typed wrong number
					else {
						print("Please try again!");
					}
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