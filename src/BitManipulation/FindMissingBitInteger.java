package BitManipulation;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
//Question 5.7
public class FindMissingBitInteger {
	
	static class BitInteger{
		public String value;
		public static final int INTEGER_SIZE = 4;
		// big integer have no limits on the size; big decimal have no limits on the size and precision.
		public BitInteger(int a){
			String s = String.format("%04d", new BigInteger(Integer.toBinaryString(a)));
			this.value = s;
		}
		
		public int fetch(int col){
			char c = value.charAt(col);
			return Integer.valueOf(Character.toString(c));
		}
		
		public String toString(){
			return value;
		}
	}
	
	public int findMissing(ArrayList<BitInteger> array){
		return findMissing(array, BitInteger.INTEGER_SIZE-1);
	}
	
	public int findMissing(ArrayList<BitInteger> array, int colum){
		if(colum < 0)
			return 0;
		ArrayList<BitInteger> oddList = new ArrayList<>();
		ArrayList<BitInteger> evenList = new ArrayList<>();
	
		for(BitInteger t : array){
			if(t != null){
				if(t.fetch(colum) == 0)
					evenList.add(t);
				if(t.fetch(colum) == 1)
					oddList.add(t);
			}
		}
		// recurse from least significant bit to most significant bit
		// if the missing one, at most significant bit is 1, 0|1=1, otherwise 0|0=0
		// the next bit, missing 1, left shift first, then set it to 1, <<1, |1; 
		if(evenList.size() <= oddList.size()){  //miss zero
			int i=findMissing(evenList, colum-1) << 1 | 0;
			System.out.println(Integer.toBinaryString(i));
			return i;
		}
		else{  //miss one
			int j=findMissing(oddList, colum-1) << 1 | 1;
			System.out.println(Integer.toBinaryString(j));
			return j;
		}
		
	}
	
	public static void main(String[] args){
		FindMissingBitInteger f = new FindMissingBitInteger();
		ArrayList<BitInteger> arr = new ArrayList<>();
		for(int i=0;i<14;i++){
			//arr.add(new BitInteger(13-i));
			arr.add(new BitInteger(i));
		}
		System.out.println(arr);
		
		// set index 5 to missing
		arr.set(5, null);
		System.out.println(arr);
		
		
		int result = f.findMissing(arr);
		System.out.println(result);
		System.out.println(Integer.toBinaryString(result));
		
		/*
		// big decimal have no limits on the size and precision.
		BigDecimal d=new BigDecimal("4.35");
		BigDecimal e=new BigDecimal("100");
		BigDecimal m=d.multiply(e);
		System.out.println(m);*/
		
		
	
	}

}
