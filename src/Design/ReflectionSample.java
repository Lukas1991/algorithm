package Design;

import java.lang.reflect.*;
import java.util.ArrayList;
class A{
	private int data=123;
	public int getData(){
		return data;
	} 
	private void getPrivate(){
		System.out.println("get private through reflection");
	}
}
public class ReflectionSample {

	/**
	 * @param args
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws NoSuchFieldException 
	 */
	
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		try {
			/**
			 * Three way to get the objects of Class class:
			 * 1. forName() if know the full qualified name of class
			 * 2. getClass() use object to return an instance of Class class.
			 * 3.if no object of that class availale, Type.class;
			 *   can be used for primitive data type also.
			 * 
			 */
			
			/*A a=new A();
			a.getClass().getName();
			System.out.println(a.getClass().getName());
			
			Class ca=A.class;
			System.out.println(ca.getName());
		
			Class cb=int.class;
			System.out.println(cb.getName());*/
			
			//java.sql.Connection
			/*Class c=Class.forName("java.sql.Connection");
			
			Method m[]=c.getDeclaredMethods();			
			for(int i=0;i<m.length;i++){
				System.out.println(m[i].toString());
				System.out.println("name = " + m[i].getName());
				System.out.println("decl class = " + m[i].getDeclaringClass());
				System.out.println("return type = " + m[i].getReturnType());
			}
			
			Constructor con[]=c.getConstructors();
			for(int i=0;i<con.length;i++){
				System.out.println(con[i].getName());
			}*/
			
						
			/*Class cls = Class.forName("OOD.corejava.A");
			
			boolean b1 = cls.isInstance(new Integer(37));
			System.out.println(b1);
			boolean b2 = cls.isInstance(new A());
			System.out.println(b2);
			
			Method methlist[] = cls.getDeclaredMethods();
			for(int i=0;i<methlist.length;i++){
				System.out.println(methlist[i].toString());
			}*/
			
			/*
			 *  access to private method of a class, change a private field value
			 *  from outside the class by changing the runtime behaviour of the class
			 */

			Class cprivate=Class.forName("OOD.corejava.A");
			A object=(A)cprivate.newInstance();
			//access to private method
			Method mprivate=cprivate.getDeclaredMethod("getPrivate", null);
			mprivate.setAccessible(true);
			mprivate.invoke(object, null);
			//change private field
			Field f=cprivate.getDeclaredField("data");
			f.setAccessible(true);
			f.set(object, 1);
			System.out.println(object.getData());
			System.out.println(f.getInt(object));
			
			
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
