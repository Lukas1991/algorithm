package Interview;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;


/**
 * HashMap uses hashcode to find bucket for that key object, if hashcodes are same then only it checks for equals method
 * hashcode is used to calculate index for above Entry[] table.
 * default hashcode method will use memory address.
 *
 * So now hashcode for above two objects india1 and india2 are same, so Both will be point to same bucket,
 * now equals method will be used to compare them which will return true.
 * This is the reason java doc says "if you override equals() method then you must override hashCode() method"
 *
 * If you are overriding equals method then you should override hashcode() also.
 * If two objects are equal then they must have same hashcode.
 * If two objects have same hashcode then they may or may not be equal
 * Always use same attributes to generate equals and hashcode as in our case we have used name.
 */
class Dog implements Comparable<Dog>{
	String color;
	int size;
 
	Dog(String c, int size) {
		color = c;
		this.size = size;
	}
	public String toString(){	
		return color + " dog " + size;
	}

	@Override
	public boolean equals(Object o){
		return ((Dog) o).color.equals(color);
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		return result;
	}

	//sort by size ASC
	@Override
	public int compareTo(Dog o) {
		return  this.size - o.size;
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
		/**
		 * Dog has to override hashcode and equals method
		 */
		HashMap<Dog, Integer> hashMap = new HashMap<Dog, Integer>();
		Dog d1 = new Dog("red", 30);
		Dog d2 = new Dog("black", 20);
		Dog d3 = new Dog("white", 15);
		Dog d4 = new Dog("white", 10);
 
		hashMap.put(d1, 15000);
		hashMap.put(d2, 10000);
		hashMap.put(d3, 20000);
		hashMap.put(d4, 50000);
 
		//print size
		System.out.println(hashMap.size());
 
		//loop HashMap
		for (Entry<Dog, Integer> entry : hashMap.entrySet()) {
			System.out.println(entry.getKey().toString() + " - " + entry.getValue());
		}
		System.out.println("------------------Tree Map Sort Map by Key--------------------");
		/**
		 * Dog has to implement Comparable interface
		 * TreeMap now uses compareTo() method to compare keys.
		 * if two dogs compareTo = 0, only save one dog
		 */
		TreeMap<Dog, Integer> treeMap = new TreeMap<>(hashMap);

		Iterator<Dog> iterator = treeMap.keySet().iterator();
		while (iterator.hasNext()) {
			Dog d = iterator.next();
			int population = hashMap.get(d);
			System.out.println(d.toString() + " - " + population);
		}

		System.out.println("------------------Sort Map by Value--------------------");
		List<Entry<Dog, Integer>> entryList = new LinkedList<>(hashMap.entrySet());

		entryList.sort((o1, o2) -> o1.getValue().compareTo(o2.getValue()));

		LinkedHashMap<Dog, Integer> linkedHashMap = new LinkedHashMap<>();
		for (Entry<Dog, Integer> entry : entryList) {
			linkedHashMap.put(entry.getKey(), entry.getValue());
		}

		for (Entry<Dog, Integer> entry : linkedHashMap.entrySet()) {
			System.out.println(entry.getKey().toString() + " - " + entry.getValue());
		}
	}

	//Generic Types
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
		Collections.sort(list, new Comparator<Entry<K, V>>() {
			@Override
			public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
				return (e1.getValue()).compareTo(e2.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}

		return result;
	}

}
