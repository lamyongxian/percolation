package percolation;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.Math;

public class Percolation {
		protected WeightedQuickUnionUF ufP;
		protected final int N;
		
	   public Percolation(int n) {
		   N = n;
		   int intNoOfSites = (N*N) + 2;
		   
		   ufP = new WeightedQuickUnionUF(intNoOfSites);
		   
		   for(int i = 1; i <= N; i++) {
			   ufP.union(i, 0); //connect all to the origin site
		   }
		   /*
		    *     0
		    *     |
		    * ---------
		    * |   |   |
		    * 1   2   3 
		    * 
		    * */
		   	
	   }  // create n-by-n grid, with all sites blocked
	   
	   public    void open(int row, int col)  {
		   
		   /*
		    * index = (row - 1 * N) + (col - 1)
		    *  
		    *   0 1 2
		    *   -----
		    * 0|1 2 3
		    * 1|4 5 6
		    * 2|7 8 9
		    */
		   //double intTotal = Math.pow((N + 1),2);
		   
		   
	   }  // open site (row, col) if it is not open already
	   
	   public boolean isOpen(int row, int col) {
		   return ufP.connected(row,col);
		   //return false;} // is site (row, col) open?
	   }
	   
	   public boolean isFull(int row, int col) {
		   //ufP.co
		   //return false;} // is site (row, col) full?
	   }
	   public     int numberOfOpenSites() { return 0;}  // number of open sites
	   public boolean percolates() {return false;}     // does the system percolate?
	   
	   private int to2DIndex(int row, int col) {
		   return N;
	   }
	   
	   public static void main(String[] args)  {
		   // test client (optional)
	   }
	   
}