package Design;

import java.util.LinkedList;

public class LinkedListUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(System.getProperty("java.version"));
		LinkedList list=new LinkedList();
		list.add("100");
		list.add("200");
		list.add("300");
		list.add("400");
		System.out.println(list.getFirst());
		System.out.println(list.getLast());
		//list.poll(); //remove from first
		//list.pollFirst();
		//list.pollLast(); //remore from last
		System.out.println(list.getFirst());
		System.out.println(list.getLast());
		
		//add to first
		list.addFirst("500");
		System.out.println(list.getFirst());
		System.out.println(list.getLast());
		
		//search an element
		System.out.println(list.indexOf("200"));
		System.out.println(list.lastIndexOf("400"));
		list.get(2);//get by index
		
		
	}

}
