package Interview;

import java.util.*;
import java.util.Map.Entry;

class Dog implements Comparable<Dog>{
	String color;
	int size;
 
	Dog(String c, int size) {
		color = c;
		this.size = size;
	}
	public String toString(){	
		return color + " dog";
	}
	
	public boolean equals(Object o){
		return ((Dog) o).color.equals(color);
	}
	
	public int hashCode(){
		return color.length();
	}
	@Override
	public int compareTo(Dog o) {
		// TODO Auto-generated method stub
		return  o.size - this.size;
	}
	
}
public class HashMaps {

	/**
	 * HashMap is implemented as a hash table, and there is no ordering on keys or values.
	 * TreeMap is implemented based on red-black tree structure, and it is ordered by the key.
	 * LinkedHashMap preserves the insertion order
	 * Hashtable is synchronized, in contrast to HashMap.
	 * @param args
	 */
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**
		 * Dog has to override hashcode and equals method
		 */
		HashMap<Dog, Integer> hashMap = new HashMap<Dog, Integer>();
		Dog d1 = new Dog("red", 30);
		Dog d2 = new Dog("black", 20);
		Dog d3 = new Dog("white", 10);
		Dog d4 = new Dog("white", 10);
 
		hashMap.put(d1, 10);
		hashMap.put(d2, 15);
		hashMap.put(d3, 5);
		hashMap.put(d4, 20);
 
		//print size
		//System.out.println(hashMap.size());
 
		//loop HashMap
		for (Entry<Dog, Integer> entry : hashMap.entrySet()) {
			System.out.println(entry.getKey().toString() + " - " + entry.getValue());
		}
		System.out.println("------------------Tree Map--------------------");
		/**
		 * Dog has to implement Comparable interface
		 * TreeMap now uses compareTo() method to compare keys. 
		 * if two dogs compareTo = 0, only save one dog
		 */
		TreeMap<Dog, Integer> treeMap = new TreeMap<Dog, Integer>();
		treeMap.put(d1, 10);
		treeMap.put(d2, 15);
		treeMap.put(d3, 5);
		treeMap.put(d4, 20);
 
		for (Entry<Dog, Integer> entry : treeMap.entrySet()) {
			System.out.println(entry.getKey() + " - " + entry.getValue());
		}
	}

}
