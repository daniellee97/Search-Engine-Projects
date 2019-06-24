import java.util.*;

/**
 * This class assign keyword and counter of each keyword and contains increase counter functions.
 */
public class Keyword {
	
	//initialize variables
	private int counter;
	private String keyword;
	
	/**
	 * sets keyword and counter
	 * @param keyword, counter
	 */
	public Keyword(String keyword, int counter) {
		this.keyword = keyword;
		this.counter = counter;
	}
	
	/**
	 * get keyword
	 * @return keyword
	 */
	public String getKeyword() {return keyword;}
	
	/**
	 * get counter of keyword
	 * @return counter
	 */
	public Integer getCounter() {return counter;}
	
	/**
	 * increase counter every time it finds the same keyword
	 * @param keyword, i
	 */
	public void increaseCounter(ArrayList<Keyword> keywords, int i) {
		keywords.get(i).counter ++;
	}
	
}
