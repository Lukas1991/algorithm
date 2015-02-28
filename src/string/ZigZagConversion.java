package string;

import java.util.ArrayList;

public class ZigZagConversion {

	/**
	 * @param args
	 */
	
	public static String convert(String s, int nRows) {
		if(nRows<=1) return s;
        if(s==null || s.length()<=nRows){
        	return s;
        }
		int length = s.length();
		
        ArrayList<String> list = new ArrayList<String>();
        int step = 2*nRows-2;
        
        for(int i=1;i<=nRows; i++){
        	StringBuilder sb=new StringBuilder();    	
        	if(i==1 || i==nRows){
        		//append x+step
        		int x = i;
        		while(x<=length){
        			sb.append(s.charAt(x-1));
        			x=x+step;
        		}
        		
        	}else{
        		int x1=i, x2=2*nRows-i;
        		//append(x1+step), append(x2+step)
        		while(x1<=length){
        			sb.append(s.charAt(x1-1));
        			x1=x1+step;
        			if(x2<=length){
        				sb.append(s.charAt(x2-1));
            			x2=x2+step;
        			}
        		}
        		
        	}
        	
        list.add(sb.toString());
        	
        }
        
    	StringBuilder result=new StringBuilder();   	
        for(int j=0;j<list.size();j++){
        	result.append(list.get(j));
        }
        
        return result.toString();
        
		
    }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String res=convert("PAYPALISHIRING", 3);
		System.out.println(res);
	}

}
