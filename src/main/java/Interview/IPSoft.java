package Interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class IPSoft {

	/**
	 * onsite JK?, clone tree
	 */
	
	/**
	* This method receives an array with positive integers, where all values in the array are repeated - except for one which is unique. 
	* Return the unique element in the array.
	*
	* @param intArray[] array of ints 
	* @return the non-repeated int
	*
	* e.g. 
	* int[] arr = {1,2,3,5,6,3,2,5,1,32,32,3,4,4,1}
	* int nonRepeated = findNonRepeatedInt(arr);
	* System.out.println(nonRepeated); // prints 6 
	*
	*/
	//if other non-unique element appears twice or even times, use bit manipulation. 
	public static int findNonRepeatedInt(int[] intArray){
		if(intArray == null || intArray.length == 0){
			return 0;
		}
		
		int x=0;
		
		for(int element : intArray){
			x = x ^ element;			
		}
		
		return x;
	}
	
	//if other non-unique element appears twice or even times, use set. 
	public static int findNonRepeatedIntSet(int[] intArray){
		if(intArray == null || intArray.length == 0){
			return 0;
		}
		
		Set set = new HashSet();
		for(int i=0;i<intArray.length;i++){
			int element = intArray[i];
			if(set.contains(element)){
				set.remove(element);
			}else{
				set.add(element);
			}
			
		}
		
		if(set.isEmpty()){
			return 0;
		}else{
			ArrayList<Integer> list = new ArrayList<Integer>(set);
			System.out.println(list.get(0));
			return list.get(0);
		}
		
		
	}
	
	//if other non-unique element appears any times, use map. 
	public static int findNonRepeatedIntMap(int[] intArray){
		if(intArray == null || intArray.length == 0){
			return 0;
		}
		
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		for(int i=0;i<intArray.length;i++){
			int element = intArray[i];
			if(map.containsKey(element)){
				map.put(element, map.get(element)+1);
			}else{
				map.put(element, 1);
			}
			
		}
		
		for(Map.Entry<Integer,Integer> en: map.entrySet()){
			if(en.getValue() == 1){
				System.out.println(en.getKey());
				return en.getKey();
			}
						
		}
		
		return 0;
		
		
		
	}
	
	
	/**
	 * Given a list of objects, returns the last object in the list that is an instance of type Foo.
	 * @param stuff the list of objects
	 * @return an object of type Foo
	 * @throws NoSuchFooException if there is no Foo in the list
	 */
	class Foo{
		
	}
	class NoSuchFooException extends Throwable{
		
	}
	
	public Foo getLstFoo(List stuff) throws NoSuchFooException{
		if(stuff == null || stuff.size() == 0) throw new NoSuchFooException();
		
		int size = stuff.size();
		for(int i=size-1; i>=0; i--){
			Object obj = stuff.get(i);
			if(obj instanceof Foo ){
				return (Foo) obj;
			}
			
		}
		
		throw new NoSuchFooException();		
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

		System.out.println(Integer.MAX_VALUE);
		System.out.println(2<<1);  //4
		System.out.println(2<<3);  //=2^(3+1)=2^4=16
		IPSoft obj = new IPSoft();
		System.out.println(countBits(10));//2
		
		String binaryString = "01101010";
		int base = 2;
		int decimal = Integer.parseInt(binaryString, base);
		System.out.println(decimal);
		
		
		int i=decimal;
		//i=i>>>1;
		//System.out.println(i);
		System.out.println("Binary = " + Integer.toBinaryString(decimal));
		System.out.println(Integer.bitCount(decimal));
		
		int[] arr = {1,2,3,5,6,3,2,5,1,32,32,4,4};
		int nonRepeated = findNonRepeatedInt(arr);
		System.out.println(nonRepeated);
		
		StringBuilder sb = new StringBuilder("123");
		
		
	}

}