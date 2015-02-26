package string;

public class AddBinary {

    public String addBinary(String a, String b) {
        if(a==null || b==null) return null;
        
        int aLen=a.length();
        int bLen=b.length();
        int maxLen=aLen;
        if(aLen > bLen){
            b = addPadding(b,aLen-bLen);  
            maxLen = aLen;
        }else if(aLen < bLen){
            a = addPadding(a,bLen-aLen);  
            maxLen = bLen;
        }
        
        boolean carry = false;
        StringBuilder c = new StringBuilder();
        for(int i=maxLen-1; i>=0; i--){
            if(a.charAt(i)=='0' && b.charAt(i)=='0'){
                if(carry){
                	c.append('1');carry=false;
                }else{
                	c.append('0'); carry=false;
                }
            }else if(a.charAt(i)=='1' && b.charAt(i)=='1'){
                if(carry){
                	c.append('1'); carry=true;
                }else{
                	c.append('0');  carry=true;
                }
            }else if((a.charAt(i)=='0' && b.charAt(i)=='1') || (a.charAt(i)=='1' && b.charAt(i)=='0')){
                if(carry){
                	c.append('0'); carry=true;
                }else{
                	c.append('1');  carry=false;
                }
            }
            else return null;
        }
        
        if(carry) {
        	c.append('1');        	
        }
        return c.reverse().toString();
        
    }
    
    public String addPadding(String s, int diff){
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=diff;i++){
            sb.append("0");
        } 
        sb.append(s);
        return sb.toString();
    }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
