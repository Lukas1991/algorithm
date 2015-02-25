package string;

public class CompareVersionNumber {

	/**
	 * @param args
	 */
	 public String convert(String str){
		if(str.contains(".")){
			if(str.startsWith(".")){
				str= "0"+str;
			}
			if(str.endsWith(".")){
				str= str + "0";
			}
			return str;
		}else 
			return str;
		
	 }
	
	
	 public int compareVersion(String version1, String version2) {
		 version1=version1.trim();
		 version2=version2.trim();
		 int result=0;
		
		 if( !version1.contains(".") && !version2.contains(".")){			 
			 return result=Integer.compare(Integer.parseInt(version1), Integer.parseInt(version2));	
			 
		 }else if(!version1.contains(".") && version2.contains(".")){
			 version1=version1+".0";
			 return compareVersion(version1,convert(version2));
			 
		 }else if(version1.contains(".") && !version2.contains(".")){
			 version2=version2+".0";
			 return compareVersion(convert(version1),version2);
			 
		 }else{  //both contains "."
			 version1=convert(version1);
			 version2=convert(version2);
			 int i1=version1.indexOf(".");
			 int i2=version2.indexOf(".");
			 String str1=version1.substring(0, i1);
			 String str2=version2.substring(0, i2);

			 //only compare the string before dot
			 result=Integer.compare(Integer.parseInt(str1), Integer.parseInt(str2));
			
			 if(result != 0){
				 return result;
			 } 
			 else{				 
				 String rest1=version1.substring(i1+1, version1.length());
				 String rest2=version2.substring(i2+1, version2.length());
				 return compareVersion(rest1,rest2);				 
			 }
			 			 
		 }
		 
		 	        
	    }
	
	
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="0..0";
		
		if(str.contains(".")){
			System.out.println("contains");
		}else{
			System.out.println("not contains");
		}
		
		String[] arr=str.split("\\.");
		
		System.out.println(arr.length);
	//	System.out.println(Integer.parseInt(arr[0]));
		
		CompareVersionNumber cvn=new CompareVersionNumber();
		System.out.println(cvn.convert(".."));
		String version1="1.12";
		
		version1=cvn.convert(version1);
		int i1=version1.indexOf(".");
		
		System.out.println(version1.substring(0, i1));
		System.out.println(version1.substring(i1+1, version1.length()));
		
		System.out.println(cvn.compareVersion("1.1", "1.01.0"));
		
	}

}
