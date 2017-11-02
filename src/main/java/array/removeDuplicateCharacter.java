package array;

public class removeDuplicateCharacter {

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

	
	public static void main(String[] args) {
		//remove duplicate characters in string
		String str="abccc";
		//String newStr=removeDuplicateCharacter(str.toCharArray());
		String newStr=removeDuplicateCharacterMemory(str.toCharArray());
		System.out.println(newStr);
	}

}
