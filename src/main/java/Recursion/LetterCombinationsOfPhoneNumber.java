package Recursion;

import java.util.ArrayList;

public class LetterCombinationsOfPhoneNumber {

	/**construct a dictionary to hold allowed characters for each digit
	 * from string last index N to 0, get listN-1, list.add(listN-1.get(i)+characters)
	 * when index is 0, list.add(characters)
	 */
	public ArrayList<String> letterCombinations(String digits) { 
		
		String[] dic = {"","","abc","def","ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
		if(digits == null || digits.length()==0) return new ArrayList<String>();
		
		return letterCombinations(digits, digits.length()-1, dic);
		
		
	}
	
	public ArrayList<String> letterCombinations(String digits, int index, String[] dic) { 
		
		ArrayList<String> list = new ArrayList<String>();
		//base case
		if(index == 0){
			int number = digits.charAt(0) - '0';
			char[] chars = dic[number].toCharArray();			
			for(char c:chars){
				list.add(String.valueOf(c));
				System.out.println(String.valueOf(c));
			}
			
		}else{
			
			int number = digits.charAt(index) - '0';	
			char[] chars = dic[number].toCharArray();
			ArrayList<String> preList = letterCombinations(digits, index-1, dic);
						
			for(char c:chars){				
				for(int i=0;i<preList.size();i++){
					String str = preList.get(i);					
					list.add(str+c);
					System.out.println(str+c);
				}
				
			}
			
		}

		return list;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String digits = "22";
		LetterCombinationsOfPhoneNumber obj = new LetterCombinationsOfPhoneNumber();
		obj.letterCombinations(digits);
	}

}
