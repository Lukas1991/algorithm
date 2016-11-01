package Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Symmetric {

	private static class TreeNode {
	    int val;
	    TreeNode left;
	    TreeNode right;
	    TreeNode(int x) { val = x; }
	}
	/**Recursive
	 * @param args
	 */
	public boolean isSymmetricRecursive(TreeNode root) {
        if(root == null) return true;
        return isMirror(root.left, root.right);
    }
    
    public boolean isMirror(TreeNode left, TreeNode right) {
        if(left == null && right == null){
            return true;
        }else if(left != null && right !=null){
            if(left.val != right.val){
                return false;
            }else{
                 return isMirror(left.left, right.right) && isMirror(left.right, right.left);
            }
               
        }else{
            return false;
        }
    }
	
    /**
     * Iteratively
     * @param args
     */
    public boolean isSymmetricIterative(TreeNode root) {
        if(root == null) return true;
        
        Queue<TreeNode> q1 = new LinkedList<TreeNode>();
        Queue<TreeNode> q2 = new LinkedList<TreeNode>();
        
        q1.add(root.left);
        q2.add(root.right);
        
        //queue allow null
        while(!q1.isEmpty() && !q2.isEmpty()){
        	TreeNode n1 = q1.remove();
        	TreeNode n2 = q2.remove();
        	if(n1==null && n2==null){
        		//continue
        	}else if (n1!=null && n2!=null){
        		if(n1.val != n2.val) return false;
        		else{
        			q1.add(n1.left);
            		q1.add(n1.right);
            		q2.add(n2.right);
            		q2.add(n2.left);
        		}
        		      		
        	}else{
        		return false;
        	}
        	      	
        }
        
        return true;
        
    }
	
    
    /**
     * get a tree's mirror tree
     * Recursively, in-place
     * @param root
     * @return
     */
    public TreeNode getSymmetricRecursiveInPlace(TreeNode root){
    	if(root==null) return null;
    	TreeNode right = getSymmetricRecursiveInPlace(root.right);
    	TreeNode left = getSymmetricRecursiveInPlace(root.left);
    	root.left =right;
    	root.right =left;
    	return root;
    	
    }
    
    //return a new tree
    public TreeNode getSymmetricRecursiveNewTree(TreeNode root){
    	if(root==null) return null;
    	TreeNode newRoot = new TreeNode(root.val);
    	
    	TreeNode right = getSymmetricRecursiveInPlace(root.right);
    	TreeNode left = getSymmetricRecursiveInPlace(root.left);
    	
    	newRoot.left =right;
    	newRoot.right =left;
    	return newRoot;
    	
    }
    
    //return mirror in place
    public TreeNode mirrorCopyInPlace(TreeNode root){  
    	if(root == null) return null;
  	
    	Stack<TreeNode> stack = new Stack<TreeNode>();
       	stack.push(root);
    	   	
    	while(!stack.empty()){
    		TreeNode curr = stack.pop();
    		TreeNode left = curr.left;
    		TreeNode right = curr.right;
    		curr.left = right;
    		curr.right = left;
    		
    		if(curr.right != null){
    			stack.push(curr.right);    			
    		}
    		
    		if(curr.left != null){
    			stack.push(curr.left);    			
    		}
    	}
    	
    	return root;  	
    }
    
    // return mirror, do not break given, return a new tree
    // use two stacks 
    public TreeNode mirrorCopy(TreeNode root){  
    	if(root == null) return null;
    	TreeNode newRoot = new TreeNode(root.val);
    	
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	Stack<TreeNode> newstack = new Stack<TreeNode>();
    	stack.push(root);
    	newstack.push(root);
    	
    	while(!stack.empty()){
    		TreeNode curr = stack.pop();
    		TreeNode newcurr = newstack.pop();
    		
    		if(curr.right != null){
    			newcurr.left = new TreeNode(curr.right.val);
    			stack.push(curr.right);
    			newstack.push(newcurr.left);
    		}
    		
    		if(curr.left != null){
    			newcurr.right = new TreeNode(curr.left.val);
    			stack.push(curr.left);
    			newstack.push(newcurr.right);
    		}
    		
    	}
    	
    	return newRoot;
    	
    }
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
