package Interview;

import java.util.ArrayList;

public class JKString {

	/**
	 * @param args
	 */
	public static ArrayList<String> replace(String str){
		
		ArrayList<String> res = new ArrayList<String>();
		res.add("");
		
		for(int i=str.length()-1; i>=0; i--){
			ArrayList<String> newlist = new ArrayList<String>();
			char c = str.charAt(i);
			if(c=='?'){
				for(String s : res){
					newlist.add("J"+s);
					newlist.add("K"+s);
				}
			}else if(c=='K' || c=='J'){
				for(String s : res){
					newlist.add(c+s);
				}
			}
			
			res=newlist;
			
		}
		
		return res;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="JJK?K?";
		ArrayList<String> res =replace(str);
		for(String s:res){
			System.out.println(s);
		}

	}

}
