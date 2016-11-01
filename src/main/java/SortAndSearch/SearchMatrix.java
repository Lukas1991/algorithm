package SortAndSearch;

public class SearchMatrix {

	public static int count=0;
	public static boolean find(int[][] m, int find,int M,int N){
		int row=0;
		int col=N-1;
		while(row<M && col>=0){
			count++;
			if(m[row][col]==find) return true;
			else if(find>m[row][col]) row++;
			else col--;			
		}
		return false;
	}
	
	public static void main(String[] args) {
		int [][] m=new int[5][4];
		int a=0;
		for(int i=0;i<5;i++){
			for(int j=0;j<4;j++){
				m[i][j]=++a;
			}
		}
		
		for(int i=0;i<5;i++){
			for(int j=0;j<4;j++){
				System.out.print(m[i][j]+", ");
			}
			System.out.println();
		}
		//O(M+N)
		System.out.println(find(m,17,5,4));
		System.out.println("count: "+count);
		
	}

}
