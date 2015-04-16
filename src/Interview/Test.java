package Interview;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Test {

	/**
	 * @param args
	 */
	 public static String sort(String str){
	        char[] arr = str.toCharArray();
	        Arrays.sort(arr);
	        return String.valueOf(arr);
	    }
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
		if(9646324351L > Integer.MAX_VALUE){
			System.out.println("bigger");
		}
		
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "e");
		
		System.out.println(sort("cat"));
		
		Random rn = new Random();
		rn.nextInt(10);
		Math.random();
		
	}

}
