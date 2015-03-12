
public class IPSoft {

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

		System.out.println(Integer.MAX_VALUE);
		System.out.println(2<<1);  //4
		System.out.println(2<<3);  //=2^(3+1)=2^4=16
		IPSoft obj = new IPSoft();
		System.out.println(countBits(10));//2
		
	}

}
