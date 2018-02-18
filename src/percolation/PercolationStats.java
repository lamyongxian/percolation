package percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	
	private double x[];
	private final int T;

	public PercolationStats(int n, int trials) {

		if (n > 0 && trials > 0) {
			T = trials;
			x = new double[trials];
			double dblThreshold = 0.0d;
			/*
			 * Run t numbers of trials, keep result in x[]
			 */
			for(int t = 1; t <= trials; t++) {
				Percolation p = new Percolation(n);
				int countT = 0;
				while(countT < (n*n) && !p.percolates()) { //open until percolates, or closed sites exhausted
					int row = StdRandom.uniform(1, n+1); //[1,n+1)
					int col = StdRandom.uniform(1, n+1);
					if(!p.isOpen(row, col)) { //Skip count if site on row and col already opened
						p.open(row, col);
						countT++;
					}
				}
				
				//System.out.println("countT for trial " + t + ": " +countT); //TODO: Remove after debug
				
				dblThreshold = (double) countT / (n*n);
				x[t-1] = dblThreshold;
				
				//System.out.println("dblThreshold for trial " + t + ": " + dblThreshold); //TODO: Remove after debug
			}
			
		} else {
			throw new IllegalArgumentException();
		}
		
	} // perform trials independent experiments on an n-by-n grid

	public double mean() {
		return StdStats.mean(x);
	} // sample mean of percolation threshold

	public double stddev() {
		return StdStats.stddev(x);
	} // sample standard deviation of percolation threshold

	public double confidenceLo() {

		double mean = StdStats.mean(x);
		double stddev = StdStats.stddev(x);
		double rootT = Math.sqrt(T);
		
		double lo = mean - ((1.96*stddev)/rootT);
		
		return lo;
	} // low endpoint of 95% confidence interval

	public double confidenceHi() {
		
		double mean = StdStats.mean(x);
		double stddev = StdStats.stddev(x);
		double rootT = Math.sqrt(T);
		
		double hi = mean + ((1.96*stddev)/rootT);
		
		return hi;
	} // high endpoint of 95% confidence interval

	public static void main(String[] args) {
		
		int n = Integer.parseInt(args[0]);
		int trials = Integer.parseInt(args[1]);
		
		PercolationStats ps = new PercolationStats(n, trials);
		System.out.println("mean			= " + ps.mean());
		System.out.println("stddev			= " + ps.stddev());
		System.out.println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
	} // test client (described below)
}