package array;

import java.util.Arrays;
import java.util.Collections;

public class ArrayStringTest {

	/**
	 * @param args
	 */
	public static String removeDuplicateCharacter(char[] str){
		if (str== null) return "null";
		
		int tail=0;
		boolean flag=false;
		for(int i=1;i<str.length;i++){
			//check each with the previous
			INNER:
			for(int j=0;j<=tail;j++){
				if (str[i]==str[j]){
					flag=true;
					break INNER;
				}				
			}
			//if no duplicate
			if(!flag){
				//System.out.println("no duplicate at i "+i);
				tail++;
				str[tail]=str[i];
				flag=false;
			}else{
				//System.out.println("Duplicate at i "+i);
				flag=false;
			}
			System.out.println("finish "+i);
		}
		String newStr= new String(str);
		return newStr.substring(0, tail+1);
		//System.out.println(newStr.substring(0, tail+1));
	}
	
	/**
	 * with additional memory of constant size
	 */
	public static String removeDuplicateCharacterMemory(char[] str){
		boolean[] set=new boolean[256];
		for(int i=0;i<256;i++) set[i]=false;
		
		int tail=0;
		for(int i=1;i<str.length;i++){
			int val=str[i];
			//if no duplicate
			if(!set[val]){
				tail++;
				str[tail]=str[i];
				set[val]=true;
			}						
		}
		
		String newStr= new String(str);
		return newStr.substring(0, tail+1);
		
	}
	
	public static boolean anagram(String s1,String s2){
		char[] c1=s1.toCharArray();
		//System.out.println(c1);
		Arrays.sort(c1);
		//System.out.println(c1);
		char[] c2=s2.toCharArray();
		//System.out.println(c2);
		Arrays.sort(c2);
		//System.out.println(c2);
		return Arrays.equals(c1, c2);

	}
	public static boolean anagram2(String s1,String s2){
		int[] set=new int[256];
		for(int i=0;i<256;i++) set[i]=0;
		//add s1 character number to set
		for(int i=0;i<s1.length();i++){
			int val=s1.charAt(i);
			set[val]++;
		}
		//decrease s2 character number to set
		for(int i=0;i<s2.length();i++){
			int val=s2.charAt(i);
			set[val]--;
		}
		//check if set is all zero
		for(int i=0;i<256;i++){
			if(set[i]!=0) return false;
		}
		return true;
		
	}
	
	public static int[][] rotate(int[][] image){
		int n=image[0].length;
		int[][] image2=new int[n][n];
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				image2[i][j]=image[n-1-j][i];
			}
		}
		return image2;		
	}
	
	public static int[][] matrixZero(int[][] image){
		int m=image.length;
		int n=image[0].length;
		int[][] image2=image;
		
		int[] row=new int[m];
		int[] column=new int[n];
		
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				if(image[i][j]==0){
					row[i]=1;
					column[j]=1;
				}
			}
		}
		
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				if(row[i]==1 || column[j]==1)
					image2[i][j]=0;				
			}
			
		}
		
		return image2;
	}
	
	public static void main(String[] args) {
		
		/*//remove duplicate characters in string
		String str="abccc";
		//String newStr=removeDuplicateCharacter(str.toCharArray());
		String newStr=removeDuplicateCharacterMemory(str.toCharArray());
		System.out.println(newStr);*/
		
		/*//check if two strings are anagrams
		boolean flag=anagram("astl","last");
		System.out.println(flag);*/
		
		/*//rotate a N*N matrix
		int[][] image={{00,01,02,03},{10,11,12,13},{20,21,22,23},{30,31,32,33}};
		int[][] image2=rotate(image);
		int n=image2[0].length;
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				System.out.print(image2[i][j]+", ");
			}
			System.out.println();
		}*/
		
		/*//1.7 if an element in an M*N matrix is 0, its entire row and column is set to 0
		int[][] image={{00,01,0,03,5},{10,0,12,13,4},{20,21,22,23,4},{30,31,32,33,4}};
		int[][] image2=matrixZero(image);
		
		for(int i=0;i<image2.length;i++){
			for(int j=0;j<image2[0].length;j++){
				System.out.print(image2[i][j]+", ");
			}
			System.out.println();
		}*/
		
		
		
		
		
	}

}
