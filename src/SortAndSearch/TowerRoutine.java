package SortAndSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TowerRoutine {
	
	static class Person implements Comparable<Person>{
		public int height;
		public int weight;
		
		public Person(int h, int w){
			this.height = h;
			this.weight = w;
		}

		@Override
		public int compareTo(Person p) {
			if(this.height != p.height)
				return ((Integer)this.height).compareTo(p.height);
			else{
				return ((Integer)this.weight).compareTo(p.weight);
			}			
		}	
		
		public boolean isBefore(Person p){
			if(this.height < p.height && this.weight < p.weight)
				return true;
			else
				return false;
		}
		
		public String toString(){
			return "(" + this.height +  ","+ this.weight + ")";
		}
		
	}
	
	public static ArrayList<Person> getIncreasingSequence(ArrayList<Person> items){
		//Collections.sort(items);
		//System.out.println("sorted: "+items);
		return longestIncreasingSubsequence(items);
	}
	
	public static ArrayList<Person> longestIncreasingSubsequence(ArrayList<Person> array){
		ArrayList<Person>[] solutions = new ArrayList[array.size()];
		longestIncreasingSubsequence(array, solutions, 0);
		
		
		System.out.println(Arrays.toString(solutions));
		ArrayList<Person> best_sequence = null;
		for(int i=0; i<array.size(); i++){
			best_sequence = seqWithMaxLength(best_sequence, solutions[i]);
		}
		return best_sequence;
	}
	
	public static void longestIncreasingSubsequence(ArrayList<Person> array, ArrayList<Person>[] solutions, int current_index){
		if(current_index >= array.size() || current_index < 0)
			return;
		Person current_person = array.get(current_index);
		
		// find the longest subsequence that we can append the current person to 
		ArrayList<Person> best_sequence = null;
		for(int i=0; i<current_index; i++){
			if(array.get(i).isBefore(current_person)){
				best_sequence = seqWithMaxLength(best_sequence, solutions[i]);
			}
		}
		ArrayList<Person> new_solution = new ArrayList<>();
		if(best_sequence != null)
			new_solution.addAll(best_sequence);
		new_solution.add(current_person);
		
		// add to list and recurse
		solutions[current_index] = new_solution;
		longestIncreasingSubsequence(array, solutions, current_index+1);
	}
	
	public static ArrayList<Person> seqWithMaxLength(ArrayList<Person> a1, ArrayList<Person> a2){
		if(a1 == null)
			return a2;
		if(a2 == null)
			return a1;
		else
			return a1.size() > a2.size() ? a1 : a2;
	}
	
	public static void main(String[] args){
		Person p3 = new Person(56,90);
		Person p4 = new Person(60,95);
		Person p5 = new Person(60,100);
		Person p6 = new Person(70,100);
		Person p1 = new Person(70,150);
		Person p2 = new Person(75,190);
		
		ArrayList<Person> persons = new ArrayList<>();
		persons.add(p1);
		persons.add(p2);
		persons.add(p3);
		persons.add(p4);
		persons.add(p5);
		persons.add(p6);
		
		System.out.println(persons);
		
		ArrayList<Person> result = getIncreasingSequence(persons);
		System.out.println("result: "+result);
	}

}
