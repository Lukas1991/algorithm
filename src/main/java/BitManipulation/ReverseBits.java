package BitManipulation;

public class ReverseBits {

	/**
	 * @param args
	 */
	public int reverseBits(int n) {
        int[] bits = new int[32];
       //convert n all bits to a bits array.
        for(int i=0; i<32; i++){
           int test = n & (1<<i);
           if(test == 0){
               bits[i] = 0;
           }else{
                bits[i] = 1;
           }
        }
        
        int res = 0;
        for(int i=31; i>=0; i--){
            res |= bits[i] << (31-i);
        }
        
        
        return res;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n=2;
		System.out.println(n>>1);
		System.out.println(n);
	}

}
