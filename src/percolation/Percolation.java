package percolation;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.Math;
import java.util.Arrays;

public class Percolation {
		protected WeightedQuickUnionUF ufP;
		protected boolean isOpen[];
		//protected int intOpen;
		protected final int N;
		
	   public Percolation(int n) {
		   if (n > 0) {
			   N = n;
			   int intNoOfSites = (N*N) + 2;
			   
			   /*
			    * if N = 3
			    * 
			    * 0 1 2 3 4 5 6 7 8 9 10
			    * | F F F F F F F F F |
			    * V                   V
			    * Origin Site         Ending Site
			    */
			   
			   ufP = new WeightedQuickUnionUF(intNoOfSites);
			   isOpen = new boolean[intNoOfSites];
			   
		   } else {
			   throw new IllegalArgumentException();
		   }
		   	
	   }  // create n-by-n grid, with all sites blocked
	   
	   public    void open(int row, int col)  {
		   
		   if(row <= N && col <= N) {
			   int indexCenter = get1DArrayIndex(row, col);
			   
			   /*
			    * Check for open adjacent sites (left/right/up/down)
			    * If yes, union site with adjacent sites
			    */
			   if ((col - 1) >= 1) { //Min col is 1
				   int indexLeft = get1DArrayIndex(row, col - 1);
				   if (isOpen[indexLeft]) ufP.union(indexLeft, indexCenter);
			   }
			   
			   if ((col + 1) <= N) { //Max col is N
				   int indexRight = get1DArrayIndex(row, col + 1);
				   if (isOpen[indexRight]) ufP.union(indexRight, indexCenter);
			   }
			   
			   if ((row - 1) >= 1) { //Min row is 1
				   int indexUp = get1DArrayIndex(row - 1, col);
				   if (isOpen[indexUp]) ufP.union(indexUp, indexCenter);
			   }
			   
			   if ((row + 1) <= N) { //Max row is N
				   int indexDown = get1DArrayIndex(row + 1, col);
				   if (isOpen[indexDown]) ufP.union(indexDown, indexCenter);
				   
			   } 
			   
			   /*
			    * Origin Site connects to first rows
			    * 
			    *     0
			    *     |
			    * ---------
			    * |   |   |
			    * 1   2   3 
			    * 
			    * */
			   if (row == 1) { //If row is last row, union with ending site
				   ufP.union(0, indexCenter);
			   }
			   
			   /*
			    * Ending Site connects to last rows
			    * 
			    * 7   8   9
			    * |   |   |
			    * ---------
			    *     |
			    *     10
			    * 
			    * */
			   if (row == N) { //If row is last row, union with ending site
				   ufP.union(N*N + 1, indexCenter);
			   }
			   
			   isOpen[indexCenter] = true; //open site
			   
		   }
		   
	   }  // open site (row, col) if it is not open already
	   
	   public boolean isOpen(int row, int col) {
		   
		   if(row <= N && col <= N) {
			   int index = get1DArrayIndex(row, col);
			   return isOpen[index]; //open site
		   } else {
			   return false;
		   }
		   // is site (row, col) open?
	   }
	   
	   public boolean isFull(int row, int col) {
		   if(row <= N && col <= N) {
			   int index = get1DArrayIndex(row, col);
			   
			   return ufP.connected(0, index);
			   
		   } else {
			   return false; 
		   }
	   } // is site (row, col) full?
	   
	   public     int numberOfOpenSites() { 
		   int count = 0;
		   for(int i = 0; i < isOpen.length; i++) {
			   if (isOpen[i]) count++;
		   }
		   return count;
		   
	   }  // number of open sites
	   
	   public boolean percolates() {
		   
		   return ufP.connected(0, N*N + 1);
		   
	   }     // does the system percolate?
	   
	   private int get1DArrayIndex(int row, int col) {
		   /*
		    * index = ((row - 1) * N) + col
		    *  
		    *   1 2 3
		    *   -----
		    * 1|1 2 3
		    * 2|4 5 6
		    * 3|7 8 9
		    */
		   
		   return ((row - 1) * N) + col;
	   }
	   
	   public static void main(String[] args)  {
		   // test client (optional)
		   Percolation p = new Percolation(4);

		   System.out.println("Index for p: " + p.get1DArrayIndex(3, 3));
		   System.out.println("Index for p: " + p.get1DArrayIndex(3, 3 -1));
		   
		   p.open(1, 1);
		   System.out.println("Percolate? " + p.percolates());
	   }
}