package Tree;

class TreeNode{
	int data;
	TreeNode left;
	TreeNode right;
	TreeNode parent;
	TreeNode(int data){
		this.data=data;
		left=null;right=null;
	}
	
}


public class findNextNode {

	/**
	 * @param args
	 */
	public TreeNode inOrderSuccessor(TreeNode n){
		if(n == null) return null;
		//when n is root, or n has right child, return left most node of right child
		if(n.parent == null || n.right != null){
			return leftMostChild(n.right);
		}else{
			//go up until we are on the left side of ancestors.
			TreeNode current = n;
			TreeNode parent=current.parent;
			while(parent != null && current != parent.left){
				current = parent;
				parent = parent.parent;				
			}
			return parent;
			
		}
		
		
	}
	
	public TreeNode leftMostChild(TreeNode n){
		if (n == null) return null;
		while (n.left != null){
			n=n.left;
		}
		return n;

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
