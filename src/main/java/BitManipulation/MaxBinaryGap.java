package BitManipulation;

public class MaxBinaryGap {

	
	public static int maxBinaryGap1(int N){
		
		int max=0, count=0;
		for(int i=0; i<32;i++){
			if( (N & (1<<i)) == 0){ //ith bit is 0
				count++;
			}else{				//ith bit is 1
				max = Math.max(max, count);
				count = 0;
			}
		}
		
		return max;
	}
	
	

	/**
	* Given an int, returns the number of set bits in x.
	*/

	public static int countBits(int n){
		 
		int count = 0;
		for(int i=0;i<32;i++){
			int result = n & (1<<i);
			System.out.println("result: "+result);
			if(result == 0){
				
			}else{
				count++;
			}
		}
		
		return count;
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(maxBinaryGap1(9));
	}

}
