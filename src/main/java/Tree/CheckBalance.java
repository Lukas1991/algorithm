package Tree;

public class CheckBalance {


	/**
	 * getHeight could check if the tree is balanced as the same time as it's checking height. if the subtree isn't balanced, return -1
	 * run time O(N) and O(H) space, where H is the height of the tree
	 * @param args
	 */
	public static boolean isBalancedBest(Node root){
		if(checkHeight(root) == -1){
			return false;
		}else 
			return true;
	}
	
	
	public static int checkHeight(Node root){
		if(root == null){
			return 0;
		}
		//check if left is balanced
		int leftHeight = checkHeight(root.left);
		if(leftHeight == -1){
			return -1; //not balanced
		}
		//check if right is balanced
		int rightHeight = checkHeight(root.right);
		if(rightHeight == -1){
			return -1; //not balanced
		}
		
		//check if current node is balanced
		int diff = leftHeight - rightHeight;
		if(Math.abs(diff) > 1){
			return -1; //not balanced
		}else{
			return 1 + Math.max(leftHeight, rightHeight); //return height
		}
		
		
	}
	
	
	/*
	 * 4.1 check if a tree is balanced
	 * the difference of min depth and max depth should not exceed 1
	 * the difference is the maximum distance possible in the tree.
	 * Cons: On each node, we recurse through its entire subtree. getHeight() is called repeatedly on the same node, O(N^2)
	 */
	public static int getHeight(Node root){
		if(root==null) return 0;
		return 1+Math.max(getHeight(root.left), getHeight(root.right));
	}
	
	public static boolean isBalanced(Node root){
		if(root ==null) return true;
		//int leftHeight=getHeight(root.left);
		//int rightHeight=getHeight(root.right);
		//System.out.println("leftHeight: "+leftHeight+" rightHeight: "+rightHeight);
		int heightDiff=getHeight(root.left)-getHeight(root.right);
		if(Math.abs(heightDiff)>1) return false;
		else {
			System.out.println(isBalanced(root.left)+" "+isBalanced(root.right));
			//if left is balanced, and right is balanced, return true;
			//return true;  
			return isBalanced(root.left) && isBalanced(root.right) ;
		}
	}
	//------------------------------------------------------
	
	
	public static void main(String[] args) {
		BSTree tree=new BSTree();
		tree.insert(5);
		tree.insert(3);
		tree.insert(9);
		tree.insert(1);
		tree.insert(4);
		tree.insert(6);
		tree.insert(7);//tree.insert(8);
				
		tree.displayTree();
		
		
		boolean result=isBalanced(tree.root);
		System.out.println("result is+"+result);

	}

}
