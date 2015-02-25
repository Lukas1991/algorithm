package BitManipulation;

public class UpdateBits {

	//Question 5.1
	public static int updateBits(int n, int m, int i, int j){
		System.out.println("n is: "+Integer.toBinaryString(n));
		System.out.println("m is: "+Integer.toBinaryString(m));
		
		int max=~0; //All 1's
		//System.out.println(Integer.toBinaryString(max));
		
		int left=max-((1<<j)-1);
		//System.out.println(Integer.toBinaryString(left));
		
		int right=((1<<i)-1);
		//System.out.println(Integer.toBinaryString(right));
		
		int mask=left | right;
		System.out.println("mask is:"+Integer.toBinaryString(mask));
		int result=(n & mask) | (m <<i);
		System.out.println("result is:"+Integer.toBinaryString(result));
		return result;
		
	}
	//Question 5.2
	public static String printBinary(String n){
		int intPart=Integer.parseInt(n.substring(0, n.indexOf('.')));
		double decPart=Double.parseDouble(n.substring(n.indexOf('.'),n.length()));
		String intString="";
		String decString="";
		while(intPart>0){
			int r=intPart%2;
			intPart=intPart/2;
			intString=r+intString;
		}
		
		System.out.println("int String: "+intString);
		
		while(decPart>0){
			if(decString.length()>32) return "error";
			double re=decPart*2;
			if(re>=1){
				decPart=re-1;
				decString=decString+"1";
			}else{
				decPart=re;
				decString=decString+"0";
			}
		}
		
		System.out.println("decString:"+decString);
		String result=intString+"."+decString;
		System.out.println("result:"+result);
		return result;
		
	}
	//Question 5.5
	public static int bitSwapRequired(int a,int b){
		int count=0;
		System.out.println("a: "+Integer.toBinaryString(a));
		System.out.println("b: "+Integer.toBinaryString(b));
		int c=a^b;  //XOR
		System.out.println("a XOR b: "+Integer.toBinaryString(c));
		//count all the 1's
		//System.out.println(c&1);
		while(c!=0){
			count=count+(c&1);
			c=c>>1;
		}
		System.out.println("count: "+count);
		return count;
	}
	//Question 5.6
	public static int swapOddEvenBits(){
		int x=732;
		System.out.println("x is:"+Integer.toBinaryString(x));
		//0xaa:1010; 0x55:0101
		int result=((x & 0xaaaaaaaa)>>1)|((x & 0x55555555)<<1);
		System.out.println(Integer.toBinaryString(result));
		
		return result;
	}
	
	
	
	public static void main(String[] args) {
		
		//updateBits(240,12,3,6);
		
		/*String result=printBinary("3.72");
		System.out.println(result);*/
		
		//bitSwapRequired(32,24);
		swapOddEvenBits();
		
		
	}

}
