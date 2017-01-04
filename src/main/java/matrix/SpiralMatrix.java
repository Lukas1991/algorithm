package matrix;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

	/**
	 * Spiral Matrix I and II
	 */

	/**
	 * Spiral Matrix I
	 * @param n
	 * @return
	 */
	public static List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> res = new ArrayList<Integer>();
		if(matrix == null || matrix.length == 0) return res;
        
        int m = matrix.length;
        int n = matrix[0].length;
        int x=0,y=0;
        
        while(m>0 && n>0){
        	//only have one row or one column
        	if(m==1 && n==1){
        		res.add(matrix[x][y]);
        		break;
        	}else if(m==1){
        		for(int i=0; i<n; i++){
        			res.add(matrix[x][y+i]);        			
        		}
        		break;
        	}else if(n==1){
        		for(int i=0; i<m; i++){
        			res.add(matrix[x+i][y]);
        		}
        		break;
        	}else{  //has a circle
        		//from top left to right
            	for(int i=0; i<n; i++){
        			res.add(matrix[x][y+i]);       			
        		}
            	//from top right to bottom right
            	for(int j=1; j<m; j++){
            		res.add(matrix[x+j][y+n-1]);           		
            	}

            	for(int i=n-2; i>=0; i--){
            		res.add(matrix[x+m-1][y+i]);            		
            	}

            	for(int j=m-2; j>0; j--){
            		res.add(matrix[x+j][y]);            		
            	}

        	}
        	
        	x++; y++;
        	m = m-2;
        	n = n-2;
        }
        
        return res;
    }
	
	/**
	 * Spiral Matrix II
	 * @param n
	 * @return
	 */
	public int[][] generateMatrix(int n) {
		if(n<0) return null;
		int[][] matrix = new int[n][n];
		int num = 1;
		
		int x=0,y=0; 
		while(n>1){
			//from top left to right
        	for(int i=0; i<n; i++){
    			matrix[x][y+i]=num;
    			num++;
    		}
        	//from top right to bottom right
        	for(int j=1; j<n; j++){
        		matrix[x+j][y+n-1]=num;
        		num++;
        	}

        	for(int i=n-2; i>=0; i--){
        		matrix[x+n-1][y+i]=num;
        		num++;
        	}

        	for(int j=n-2; j>0; j--){
        		matrix[x+j][y]=num;
        		num++;
        	}
        	
        	x++;y++;n=n-2;
			
		}
		if(n==1){
			matrix[x][y]= num;
		}
		
		
		return matrix;
        
    }
	
	public static void main(String[] args) {
		int[][] m2 = {{1,2}, {3,4}};
		int[][] m3 = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
		List<Integer> res = spiralOrder(m3);
		for(int i:res){
			System.out.println(i);
		}
	}
}
