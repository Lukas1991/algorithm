package Recursion;

public class Fibonacci {

	public static int Fibonacci(int n){
		if(n==0) return 0;
		else if(n==1) return 1;
		else if(n>1)  return Fibonacci(n-1)+Fibonacci(n-2);
		else return -1;
	}
	
	public static int FibonacciIterative(int n){
		//check if n==0,n==1,n==2,n<0
		int result=0;
		int f1=1,f2=1;
		for(int i=3;i<=n;i++) {
			result=f1+f2;
			f1=f2;
			f2=result;
		}
		return result;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int result=Fibonacci(6);
		System.out.println(result);
		System.out.println(FibonacciIterative(6));
	}

}
