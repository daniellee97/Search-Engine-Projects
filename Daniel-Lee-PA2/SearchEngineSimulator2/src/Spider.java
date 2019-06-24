import java.util.*;
/**
 * This class contains search function.
 */
public class Spider
{
	
  //initialize variables
  private static final int MAX_PAGES_TO_SEARCH = 30;
  private Set<String> pagesVisited = new HashSet<String>();
  private List<String> pagesToVisit = new LinkedList<String>();
  public ArrayList<String> urls = new ArrayList<String>();


  /**
   * Our main launching point for the Spider's functionality. Internally it creates spider legs
   * that make an HTTP request and parse the response (the web page).
   * 
   * @param url
   *            - The starting point of the spider
   * @param searchWord
   *            - The word or string that you are searching for
   */
  
  public String getUrls(int i){
	  return urls.get(i);
  }
  
  public void search(String url, String searchWord)
  {
	  System.out.println("Finding...");
      while(this.pagesVisited.size() < MAX_PAGES_TO_SEARCH)
      {
          String currentUrl;
          SpiderLeg leg = new SpiderLeg();
          if(this.pagesToVisit.isEmpty())
          {
              currentUrl = url;
              this.pagesVisited.add(url);
              this.urls.add(url);
              
          }
          else
          {
              currentUrl = this.nextUrl();
          }
          leg.crawl(currentUrl); // Lots of stuff happening here. Look at the crawl method in
                                 // SpiderLeg
          boolean success = leg.searchForWord(searchWord);
          if(success)
          {
              
              break;
          }
          this.pagesToVisit.addAll(leg.getLinks());
      }
      
  }

  public int numberOfPageVisited() {return pagesVisited.size(); }

  /**
   * Returns the next URL to visit (in the order that they were found). We also do a check to make
   * sure this method doesn't return a URL that has already been visited.
   * 
   * @return
   */
  private String nextUrl()
  {
      String nextUrl;
      do
      {
          nextUrl = this.pagesToVisit.remove(0);
      } while(this.pagesVisited.contains(nextUrl));
      this.pagesVisited.add(nextUrl);
      this.urls.add(nextUrl);
      return nextUrl;
  }
}