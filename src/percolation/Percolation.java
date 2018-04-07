package percolation;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
		private WeightedQuickUnionUF ufP;
		private boolean[] isOpen;
		//protected int intOpen;
		private final int N;
		
	   public Percolation(int n) {
		   if (n > 0) {
			   N = n;
			   int intNoOfSites = (N*N) + 2; // NxN sites + 1 origin site
			   
			   /*
			    * if N = 3
			    * 
			    * 0 1 2 3 4 5 6 7 8 9 10
			    * | F F F F F F F F F |
			    * V                   V
			    * Origin Site         Ending Sites
			    */
			   
			   ufP = new WeightedQuickUnionUF(intNoOfSites);
			   isOpen = new boolean[intNoOfSites];
			   
		   } else {
			   throw new IllegalArgumentException();
		   }
		   	
	   }  // create n-by-n grid, with all sites blocked
	   
	   public    void open(int row, int col)  {
		   
		   if (row <= N && row > 0 && col <= N && col > 0) {
			   int indexCenter = get1DArrayIndex(row, col);
			   
			   /*
			    * Check for open adjacent sites (left/right/up/down)
			    * If yes, union site with adjacent sites
			    */
			   if ((col - 1) >= 1) { //Min col is 1
				   int indexLeft = get1DArrayIndex(row, col - 1);
				   if (isOpen[indexLeft]) {
					ufP.union(indexLeft, indexCenter);
				}
			   }
			   
			   if ((col + 1) <= N) { //Max col is N
				   int indexRight = get1DArrayIndex(row, col + 1);
				   if (isOpen[indexRight]) {
					ufP.union(indexRight, indexCenter);
				}
			   }
			   
			   if ((row - 1) >= 1) { //Min row is 1
				   int indexUp = get1DArrayIndex(row - 1, col);
				   if (isOpen[indexUp]) {
					ufP.union(indexUp, indexCenter);
				}
			   }
			   
			   if ((row + 1) <= N) { //Max row is N
				   int indexDown = get1DArrayIndex(row + 1, col);
				   if (isOpen[indexDown]) {
					ufP.union(indexDown, indexCenter);
				}
				   
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
			   if (row == N) { // If row is last row AND site is connected to origin, union with ending site
				   
					ufP.union(N*N + 1, indexCenter);
				    
			   }
			   
			   isOpen[indexCenter] = true; //open site
			   
		   } else {
			   throw new IllegalArgumentException();
		   }
		   
	   }  // open site (row, col) if it is not open already
	   
	   public boolean isOpen(int row, int col) {
		   
		   if (row <= N && row > 0 && col <= N && col > 0) {
			   int index = get1DArrayIndex(row, col);
			   return isOpen[index]; //open site
		   } else {
			   throw new IllegalArgumentException();
		   }
		   // is site (row, col) open?
	   }
	   
	   public boolean isFull(int row, int col) {
		   
		   if (row <= N && row > 0 && col <= N && col > 0) {
			   int index = get1DArrayIndex(row, col);

			   return ufP.connected(0, index);
			   
		   } else {
			   throw new IllegalArgumentException();
		   }
	   } // is site (row, col) full?
	   
	   public     int numberOfOpenSites() {

		   return (N*N + 2) - ufP.count();
		   
	   }  // number of open sites
	   
	   public boolean percolates() {
		   
		   return ufP.connected(0, N*N + 1); //TODO: Bug on heart25.txt
		   
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
	   
	   //TODO: Attempt to solve backwash
//	   private int getCorrespondingEndSite(int site) {
//		   /*   
//		    *   1  2  3
//		    *   -------
//		    * 1|1  2  3
//		    * 2|4  5  6
//		    * 3|7  8  9
//		    *   10 11 12
//		    */
//		   
//		   return site + N;
//	   }
	   
	   public static void main(String[] args)  {
		   // test client (optional)
		   Percolation p = new Percolation(3);

		   System.out.println("Index for (3, 3): " + p.get1DArrayIndex(3, 3));
		   
		   p.open(1, 1);
		   System.out.println("open(1, 1) Percolate? " + p.percolates());
		   p.open(1, 2);
		   System.out.println("open(1, 2) Percolate? " + p.percolates());
		   p.open(2, 2);
		   System.out.println("open(2, 2) Percolate? " + p.percolates());
		   p.open(3, 2);
		   System.out.println("open(3, 2) Percolate? " + p.percolates());
	   }
}