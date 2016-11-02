package array;

public class ReverseWords {

	public String reverseWords(String s) {
		//split string by one ore more whitespace
		String[] arr=s.trim().split("\\s+");
		int len=arr.length;
		
		//reverse the array
		for(int i=0;i<len/2;i++){			
			String temp=arr[i];
			arr[i]=arr[len-1-i];
			arr[len-1-i]=temp;
						
		}
		
		StringBuilder sb=new StringBuilder();
		
		for(int i=0;i<len;i++){
			sb.append(arr[i]).append(" ");
		}
		
		String result=sb.toString().trim();
		return result;
    }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	String test="a b";
	ReverseWords rw=new ReverseWords();
	rw.reverseWords(test);

	}

}