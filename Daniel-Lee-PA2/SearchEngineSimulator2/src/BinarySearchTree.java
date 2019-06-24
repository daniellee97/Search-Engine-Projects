import java.util.*;

//Binary Search Tree class operates searching specific node, inserting a node and deleting a node.
public class BinarySearchTree {
    
    Node root; 
    //Constructor which sets the root to null
    public BinarySearchTree() {  
        root = null;
    } 
	
    /**
     * This method search specific node which has page rank of k
     * @param k is a page rank
     * @return x
     */
	public Node iterativeTreeSearch(int k) {
		Node x = root;
		while(x!=null && k!=x.key.getPageRank()) {
			if(k>x.key.getPageRank())
				x = x.left;
			else
				x = x.right;
		}
		
		return x;
	}
	
	/**
	 * This method prints elements in BST inorder
	 * @param x 
	 */
	public void inorderTreeWalk(Node x) {
		if(x!=null) {
			inorderTreeWalk(x.left);
			System.out.println("Index: " + x.key.getIndex() + "\n"
							 + "Total Score: " + x.key.getTotalScore() + "\n"
							 + "Page rank: " + x.key.getPageRank() + "\n"
							 + "URL: " + x.key.getUrl());			 
			inorderTreeWalk(x.right);
		}
	}
	
	/**
	 * This method shows tree in order
	 */
	public void showTree() {
		inorderTreeWalk(root);
	}
	
	/**
	 * This method operates BST sorting algorithm.
	 * @param urls
	 */
	public void BSTSort(ArrayList<URL> urls) {
		for(int i=0;i<urls.size();i++)
			treeInsertion(urls.get(i));
		inorderTreeWalk(root);
	}
	
	public void clearTree(ArrayList<URL> urls) {
		for(int i=0;i<urls.size();i++){
			treeDeletion(iterativeTreeSearch(i+1));
		}
	}
	
	/**
	 * This method finds the successor of node x
	 * @param x
	 * @return x
	 */
	public Node treeMinimum(Node x) {
		while(x.left!=null)
			x = x.left;
		return x;
	}
	
	/**
	 * Insertion method that inserts a node to a tree
	 * @param z
	 * @param url
	 */
	public void treeInsertion (URL url) {
		Node z = new Node(url);
		Node y = null;
		Node x = root;
		while(x != null) {
			y=x;
			if(z.key.getTotalScore() < x.key.getTotalScore())
				x=x.left;
			else
				x=x.right;
		}
		z.p = y;
		
		//When tree is empty
		if (y==null)
			root = z;
		else if (z.key.getTotalScore()<y.key.getTotalScore()) 
			y.left = z;
		else
			y.right = z;
			
	}
	
	/**
	 * This method deletes a node z which user passes in.
	 * @param z 
	 */
	public void treeDeletion(Node z) {
		// z has no left child
		if (z.left == null)
			transplant(z,z.right);
		//z has a left child, but no right child
		else if (z.right ==null)
			transplant(z,z.left);
		//z has two children
		else {
			//find z's successor
			Node y = treeMinimum(z.right);
			//y lies within y’s right subtree but is not the root of this subtree
			if(y.p!=z) {
				transplant(y,y.right);
				y.right = z.right;
				y.right.p = y;
			}
			transplant(z,y);
			y.left = z.left;
			y.left.p = y;
		}
	}
	
	/**
	 * This method swaps two nodes u and v.
	 * @param u
	 * @param v
	 */
	public void transplant(Node u, Node v) {
		//Handle u is root of tree
		if(u.p == null)
			root = v;
		//if u is a left child
		else if (u == u.p.left)
			u.p.left = v;
		//if u is a right child
		else
			u.p.right = v;
		//update v.p if v is non null
		if(v!=null)
			v.p = u.p;
	}
	
	/**
	 * Node class which helps to organize tree
	 */
	public class Node { 
        URL key;
        Node left, right, p; 
        
        /**
         * Constructor that sets key, p, left, right to url, null, null and null
         * @param url
         */
        public Node(URL url) { 
            key = url;
            p = left = right = null; 
        } 
    } 
}
