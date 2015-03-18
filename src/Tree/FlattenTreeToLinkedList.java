package Tree;

import java.util.Stack;



public class FlattenTreeToLinkedList {

	/**
	 * each node's right child points to the next node of a pre-order traversal.
	 * @param args
	 */
	
	private static class TreeNode {
	    int val;
	    TreeNode left;
	    TreeNode right;
	    TreeNode(int x) { val = x; }
	}
	
	
	public static void flatten(TreeNode root) {
        if(root == null) return;
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        TreeNode p = null;
                
        while(!stack.empty()){
            TreeNode c = stack.pop();
            
             //push right node first, then push left node
            if(c.right != null) 
                stack.push(c.right);
            
            if(c.left != null) 
                stack.push(c.left);
            
            if(p != null){
                //move c to p.right, set p.left to null
                p.right = c;
                p.left = null;
                p = p.right;
            }else{
                p=root;
            }
                      
        }
        
        
    }
	
/*
			1 
		   / \ 
		  2   5 
		 / \   \ 
		3   4   6 
			   / \
			  7	  8
*/
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode r1 = new TreeNode(1);
		TreeNode r2 = new TreeNode(2);
		TreeNode r3 = new TreeNode(3);
		TreeNode r4 = new TreeNode(4);
		TreeNode r5 = new TreeNode(5);
		TreeNode r6 = new TreeNode(6);
		TreeNode r7 = new TreeNode(7);
		TreeNode r8 = new TreeNode(8);
		
		r1.left = r2;
		r1.right = r5;
		r2.left = r3;
		r2.right = r4;
		r5.right = r6;
		r6.left = r7;
		r6.right = r8;
		
		flatten(r1);
		StringBuilder sb = new StringBuilder();
		TreeNode p = r1;
		while(p !=null){
			sb.append(p.val+"-->");
			p=p.right;
		}
		
		System.out.println(sb);
		
	}

}
