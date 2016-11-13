package matrix;

import java.util.ArrayList;
import java.util.List;

public class twoDMatrix {

	/**1. rotate Image
	 * 2. set Matrix Zero. O(1) space
	 * 3. Search Matrix
	 * 4. Spiral Matrix I and II
	 * @param args
	 */
	//rotate in-place， layer by layer
	public void rotate(int[][] matrix) {
		int n=matrix[0].length;
		for(int layer = 0; layer< n/2; layer++){
			for(int j = layer; j < n-1-layer; j++){
			    
				//left top
				int leftTop = matrix[layer][j];
				//right top
				int rightTop = matrix[j][n-1-layer];
				matrix[j][n-1-layer] = leftTop;
				//left bottom
				int leftBottom = matrix[n-1-layer][n-1-j];
				matrix[n-1-layer][n-1-j] = rightTop;
				//right bottom
				int rightBottom = matrix[n-1-j][layer];
				matrix[n-1-j][layer] = leftBottom;
				
				matrix[layer][j] = rightBottom;
			
			}
		}
		
    }
	
	 public void rotateDa(int[][] matrix) {
	        int n = matrix.length;
	        
	        for (int layer = 0; layer < n/2; layer++) {
	            int last = n - layer - 1;
	            
	            for (int i = layer; i < last; i++) {
	                int offset = i - layer;
	                //leftBottom
	                int tmp = matrix[last-offset][layer]; // left to tmp
	                
	                matrix[last-offset][layer] = matrix[last][last-offset]; // bottom to left
	                matrix[last][last-offset] = matrix[i][last]; // right to bottom
	                matrix[i][last] = matrix[layer][i]; // top to right
	                matrix[layer][i] = tmp;
	            }
	        }
	    }
	
	
	//create a new image matrix
	public int[][] rotate2(int[][] matrix) {
		int n=matrix[0].length;
		int[][] image2=new int[n][n];
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				image2[i][j]=matrix[n-1-j][i];
			}
		}
		return image2;	
    }
	
	
	/**
	 *use O(1) space 
	 *have  boolean firstRowZero and firstColumnZero, use matrix's first row and first column as the flag arrays
	 */	
	public void matrixZeroBest(int[][] matrix){
		 int m = matrix.length;
	     int n = matrix[0].length;
	     boolean firstRowZero = false;
	     boolean firstColumnZero = false;
	     
	     //check first row and first column
	     for(int i=0; i<m; i++){
	    	 if(matrix[i][0]==0){
	    		 firstRowZero = true;
	    		 break;
	    	 }	    		 
	     }
	     
	     for(int j=0; j<n; j++){
	    	 if(matrix[0][j]==0){
	    		 firstColumnZero = true;
	    		 break;
	    	 }	    		 
	     }
	     
	     //use first row and first column in matrix as the flag arrays, index should start from 1
	     for(int i=1; i<m; i++){
	    	 for(int j=1; j<n; j++){
	    		 if(matrix[i][j]==0){
	    			 matrix[0][j] = 0;
	    			 matrix[i][0] = 0;
	    		 }
	    	 }	    	  		 
	     }
	     
	     //use flag arrays to set the matrix to zero
	     for(int i=1; i<m; i++){
	    	 for(int j=1; j<n; j++){
	    		 if(matrix[0][j]== 0 || matrix[i][0] == 0){
	    			 matrix[i][j] = 0;
	    		 }
	    	 }	    	  		 
	     }
	     
	     //use firstRowZero and firstColumnZero to set the matrix first row and column
	     if(firstRowZero){
	    	 for(int i=0; i<m; i++){
		    	 matrix[i][0] = 0;		    		 	    		 
		     }
	     }
	     if(firstColumnZero){
	    	 for(int j=0; j<n; j++){
	    		 matrix[0][j] = 0;		    		 	    		 
		     }
	     }
	     
	}
		
	/**
	 *use O(m + n) space 
	 */	
	public static int[][] matrixZeroSecond(int[][] image){
		int m=image.length;
		int n=image[0].length;
		int[][] image2=image;
		
		int[] row=new int[m];
		int[] column=new int[n];
		
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				if(image[i][j]==0){
					row[i]=1;
					column[j]=1;
				}
			}
		}
		
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				if(row[i]==1 || column[j]==1)
					image2[i][j]=0;				
			}
			
		}
		
		return image2;
	}
	
	
	/**
	 * the matrix can be considered as a sorted array.The end is m*n -1.
	 * row is mid/n; column is mid%n;
	 */
	public boolean searchMatrixBetter(int[][] matrix, int target) {
        if(matrix==null || matrix.length==0 || matrix[0].length==0) 
            return false;
 
        int m = matrix.length;
        int n = matrix[0].length;
 
        int start = 0;
        int end = m*n-1;
 
        while(start<=end){
            int mid=(start+end)/2;
            int midX=mid/n;
            int midY=mid%n;
 
            if(matrix[midX][midY]==target) 
                return true;
 
            if(matrix[midX][midY]<target){
                start=mid+1;
            }else{
                end=mid-1;
            }
        }
 
        return false;
    }
	
	
	/**
	 * 1. binary search to find in which row first.
	 * 2. binary search of that row
	 */
	public static boolean searchMatrix(int[][] matrix, int target) {
		 int m = matrix.length;
	     int n = matrix[0].length;
	     if(target < matrix[0][0] || target > matrix[m-1][n-1]) return false;
	     int mStart=0, mEnd=n-1, nStart=0, nEnd=m-1;
	     
	     while(nStart <= nEnd){
	    	 int nMid = (nStart+nEnd)/2;
	    	 //System.out.println(nStart+" "+nEnd+" "+nMid);
	    	 if(matrix[nMid][0] == target){
	    		 return true;
	    	 }else if(matrix[nMid][0] > target){
	    		 nEnd = nMid-1;
	    	 }else{	    		 
	    		 nStart = nMid+1;
	    	 }
	     }
	     //nEnd is the row index for target
	   // System.out.println(nEnd);
	    
	    while(mStart <= mEnd){
	    	int mMid = (mStart+mEnd)/2;
	    	if(matrix[nEnd][mMid] == target){
	    		return true;
	    	}else if(matrix[nEnd][mMid] > target){
	    		mEnd = mMid-1;
	    	}else{
	    		mStart = mMid+1;
	    	}
	    }
	    
	    return false;
	     
	     
    }
	
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
		// TODO Auto-generated method stub
		int[][] matrix={{1,3,5,7},{10,11,16,20},{23,30,34,50}};
		//searchMatrix(matrix, 0);
		//searchMatrix(matrix, 16);
		//searchMatrix(matrix, 20);
		
		int[][] m2 = {{1,2}, {3,4}};
		int[][] m3 = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
		List<Integer> res = spiralOrder(m3);
		for(int i:res){
			System.out.println(i);
		}

		/*//rotate a N*N matrix
		int[][] image={{00,01,02,03},{10,11,12,13},{20,21,22,23},{30,31,32,33}};
		int[][] image2=rotate(image);
		int n=image2[0].length;
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				System.out.print(image2[i][j]+", ");
			}
			System.out.println();
		}*/

		/*//1.7 if an element in an M*N matrix is 0, its entire row and column is set to 0
		int[][] image={{00,01,0,03,5},{10,0,12,13,4},{20,21,22,23,4},{30,31,32,33,4}};
		int[][] image2=matrixZero(image);

		for(int i=0;i<image2.length;i++){
			for(int j=0;j<image2[0].length;j++){
				System.out.print(image2[i][j]+", ");
			}
			System.out.println();
		}*/
	}

}
