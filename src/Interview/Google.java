package Interview;
import java.util.*;

class AutoComponent{
	public String getName(){
		return "name";}
	public HashMap<AutoComponent, Integer> getChildren(){
		HashMap<AutoComponent, Integer> m=new HashMap();		
		return m;
	}
	
}

public class Google {

	/**
	 * You have a tree collection of objects that have parent-child relationships to each other (bill of material). Any child can exist multiple times throughout the structure.

Example:

	•	Vehicle
	◦	Chassis - Quantity=1
	▪	Seat - Quantity=4
	▪	.25 Socket Head Cap Screw - Quantity = 12
	▪	Headrest - Quantity = 1
	▪	Frame - Quantity = 1
	▪	Foam Cushion - Quantity = 1
	▪	.25 Socket Head Cap Screw - Quantity = 4
	▪	Seat Bottom - Quantity = 2
	▪	Frame - Quantity = 1
	▪	Foam Cushion - Quantity = 1
	▪	.25 Socket Head Cap Screw - Quantity = 9
	◦	Engine - Quantity = 1
	▪	Piston - Quantity = 4
	▪	Engine Block - Quantity = 1
	▪	Intake Manifold - Quantity = 1
	▪	.25 Socket Head Cap Screw - Quantity = 46
	▪	Alternator - Quantity = 1
	▪	Coil - Quantity = 4
	▪	Stator - Quantity = 1
	▪	.25 Socket Head Cap Screw - Quantity = 6
	◦	Wheel - Quantity = 4
	▪	Brake Assembly - Quantity = 1
	▪	Piston - Quantity = 2
	▪	Caliper - Quantity =2
	▪	.25 Socket Head Cap Screw - Quantity = 8
	▪	Hub - Quantity = 2
	▪	.25 Socket Head Cap Screw - Quantity = 2
	▪	Bearing - Quantity = 4

Assume the following:
Every component listed in the tree is a java class instance of type, AutoComponent.
Methods of AutoComponent:
String getName()
HashMap<AutoComponent autocomponent, int quantity> getChildren()




Write a recursive method that can accept the start point AutoComponent and a string indicating which component to get an aggregate count for as parameters (and any other parameters you deem necessary).

e.g. How many .25 Socket Head Cap Screw’s are there in total?

	 */
	public int getTotal(AutoComponent auto,String s){
		int sum=0;
		if(auto.getChildren()==null){
			return sum;
		}else{
			HashMap<AutoComponent, Integer> children=new HashMap();
			//iteratoe throught the map
			for(Map.Entry<AutoComponent, Integer> entry:children.entrySet()){
				AutoComponent thisauto=entry.getKey();
				if(thisauto.getName().equals(".25")){
					sum+=entry.getValue();
				}
				if(thisauto.getChildren()!=null){
					int quan=getTotal(thisauto,s);
					sum+=quan;
				}
				
				//System.out.println(entry.getKey()+" "+entry.getValue());
			}
			return sum;			
		}
		
	}
	
	
	public static void main(String[] args) {
		
		/*HashMap<String,Integer> m=new HashMap();
		m.put("abc",1);
		m.put("abcd",2);
		m.put("abcde",3);
		
		for(Map.Entry<String, Integer> entry:m.entrySet()){
			System.out.println(entry.getKey()+" "+entry.getValue());
		}*/
		
		}

}


