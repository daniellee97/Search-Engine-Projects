import java.util.*;

/**
 * This class assign url, totalScore, factorScores, index and pagerank and contains setPageRank, updateTotalScore, and updateFactorScore function.
 */
public class URL{
	
	//initialize variables
	private String url;
	private int totalScore;
	private ArrayList<Integer> factorScores = new ArrayList<>();
	private int index;
	private int pageRank;
	
	/**
	 * constructor that sets url, total score, factor scores, and index
	 * @param url, totalScore, factorScores, index
	 */
	public URL(String url, int totalScore, ArrayList<Integer> factorScores, int index) {
		this.url = url;
		this.totalScore = totalScore;
		this.factorScores = factorScores;
		this.index = index;
	}
	
	/**
	 * get url of web page
	 * @return url
	 */
	public String getUrl() {return url;}
		
	/**
	 * get total score of web page
	 * @return totalScore
	 */
	public Integer getTotalScore() { return totalScore;}
	
	/**
	 * get factor scores of web page
	 * @return factorScores
	 */
	public ArrayList<Integer> getFactorScores(){ return factorScores;}
	
	/**
	 * get original index of web page
	 * @return index
	 */
	public Integer getIndex() { return index;}
	
	/**
	 * get page rank of web page
	 * @return pageRank
	 */
	public Integer getPageRank() {return pageRank;}
	
	/**
	 * set the page ranks
	 * @param urls, i ,j
	 */
	public void setPageRank(ArrayList<URL> urls, int i, int j) {
		urls.get(i).pageRank = j;
	}
	
	/**
	 * update the total score of web page
	 */
	public void updateTotalScore() {
		totalScore =0;
		for(int i=0;i<4;i++) {
			
			totalScore += factorScores.get(i);
		}
	}
	/**
	 * update factor scores
	 * @param index, newScore
	 */
	public void updateFactorScores(int index, int newScore) {
		factorScores.set(index-1, factorScores.get(index-1)+newScore);
		
	}
	
	
	
}
