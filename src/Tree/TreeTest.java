package Tree;

import java.util.ArrayList;

public class TreeTest {
	
	
	public static void main(String[] args) {
		BSTree tree=new BSTree();
		tree.insert(5);
		tree.insert(3);
		tree.insert(9);
		tree.insert(1);
		tree.insert(4);
		tree.insert(6);
		tree.insert(7);//tree.insert(8);
		
		Node find=tree.find(3);
		if(find!=null){
			find.displayNode();
		}else{
			System.out.println("not find");
		}
		tree.displayTree();
		
		//tree.delete(5);
		//tree.displayTree();
		
		  

	}

}
