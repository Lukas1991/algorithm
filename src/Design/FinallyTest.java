package Design;

public class FinallyTest {

	/**foo1() has return value. so the catch block fully execute, then the finally block, 
	 * and then the function foo() actually returns.
	 * @param args
	 */
	public static String lem(){
		System.out.println("lem");
		return "return from lem";
	}
	
	public static void foo(){
		int x=0;
		int y=5;
		try{
			System.out.println("start try");
			int b=y/x;			
			System.out.println("end try");
			//return "returned from try";
		}catch(Exception ex){
			System.out.println("catch");
			String s= lem()+" |returned from catch";
			System.out.println(s);
		}finally{
			System.out.println("finally");
		}
		
	}
	
	public static void bar(){
		System.out.println("start bar");
		foo();
		//System.out.println(v);
		System.out.println("end bar");
	}
	
	public static String foo1(){
		int x=0;
		int y=5;
		try{
			System.out.println("start try");
			int b=y/x;			
			System.out.println("end try");
			return "returned from try";
		}catch(Exception ex){
			System.out.println("catch");
			return lem()+" |returned from catch";
		}finally{
			System.out.println("finally");
		}
		
	}
	
	public static void bar1(){
		System.out.println("start bar");
		String v=foo1();
		System.out.println(v);
		System.out.println("end bar");
	}
	
	public static void main(String[] args) {
		//bar();
		bar1();

	}

}
