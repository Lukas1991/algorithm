package Recursion;

public class PrintParenthese {

	public static void printPar(int count){
		char[] str=new char[2*count];
		printPar(count,count,str,0);
	}
	
	public static void printPar(int l,int r, char[] str, int count){
		System.out.printf("l: %d, r: %d, count: %d\n",l,r,count);

		if(l<0 || r<l) return;  //invalid state
		if(l==0 && r==0){
			System.out.println(str);//found one, so print it
			//System.out.println(count);
		}else{
			if(l>0){
				str[count]='(';
				printPar(l-1,r,str,count+1);
			}
			if(r>l){
				str[count]=')';
				printPar(l,r-1,str,count+1);
			}
		}
		//System.out.printf("l: %d, r: %d, count: %d\n",l,r,count);
	}
	
	public static void main(String[] args) {
		printPar(3);

	}

}
