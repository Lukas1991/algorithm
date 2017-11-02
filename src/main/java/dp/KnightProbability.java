package dp;

public class KnightProbability {
	//double[][] predp save each cell's probability in previous step
	//double[][] dp save each cell's probability in current step
	public double knightProbability(int N, int K, int r, int c) {
		int[][] d = {{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}};
		double[][] predp = new double[N][N];
		predp[r][c] = 1.0;
		
		for (; K>0; K--) {
			double[][] dp = new double[N][N];
			
			for (int x = 0; x < N; x++) {
				for (int y = 0; y < N; y++) {
					
					for (int[] arr : d) {
						int x1 = x + arr[0];
						int y1 = y + arr[1];
						
						if (x1 >= 0 && y1 >= 0 && x1 < N && y1 < N) {
                            dp[x1][y1] += predp[x][y] / 8.0;
                        }
					}
					
				}
			}
			
			predp = dp;
		}
		
		double sum = 0;
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				sum += predp[x][y];
			}
		}
		return sum;
	}
}
