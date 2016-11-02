package Recursion;

import java.util.ArrayList;

public class GetPermutation {

	public static int count=0;
	public static ArrayList<String> getPermutation(String str){
		ArrayList<String> perms=new ArrayList();
		
		int size=str.length();
		if(size==0) return perms;
		if(size==1){
			perms.add(str);
		}else{
			for(int i=0;i<size;i++){
				Character c=str.charAt(i);
				String sub=str.substring(0,i)+str.substring(i+1, size);
				ArrayList<String> subPerms=getPermutation(sub);
			
				for(String s:subPerms){
					String newS=c+s;
					perms.add(newS);
				}
			
			}
		}
		//System.out.println(sub);
		
		//count++;
		//System.out.println("return perm"+count);
		return perms;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getPermutation("cate"));
	}

}
