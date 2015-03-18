package Tree;

public class ConstructTreeInorderPostORPreorder {

	/**
	 * in-order: (left, root, right)   4 2 5  (1)  6 7 3 8
	 * 								   \   /        \    /
	 * 							left sub-tree       right sub-tree
	 * 
	 * post-order: (left, right, root) 4 5 2  6 7 8 3  (1)
	 * 								   \   /  \     /
	 *           			  left sub-tree 	right sub-tree	
	 */
	
		
/*
	1 
   / \ 
  2   3 
 / \   \ 
4   5   7 
	   / \
	  6	  8
*/

	public TreeNode buildTreeInPost(int[] inorder, int[] postorder) {
        int inStart = 0;
        int inEnd = inorder.length-1;
        int postStart =0;
        int postEnd = postorder.length-1;
 
        return buildTreeInPost(inorder, inStart, inEnd, postorder, postStart, postEnd);
    }
	
	 public TreeNode buildTreeInPost(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd){
		if(postStart > postEnd)
			return null;
		
		//last element in post-order array is root
		int rootValue = postorder[postEnd];
		TreeNode root = new TreeNode(rootValue);
		
		//find the root in in-order array
		int k=findIndex(inorder,rootValue, inStart, inEnd);		
		int leftsize = k-inStart;
		
		//set inorder left sub-tree, and postorder left sub-tree
		root.left = buildTreeInPost(inorder, inStart, k-1, postorder, postStart, postStart+leftsize-1);
				
		//set inorder right sub-tree, and postorder right sub-tree
		root.right = buildTreeInPost(inorder, k+1, inEnd, postorder, postStart+leftsize, postEnd-1);
		
		return root;
		}
	 
	 protected int findIndex(int[] inorder, int val, int inStart, int inEnd) {
	        for (int i = inStart; i <= inEnd; i++) {
	            if (inorder[i] == val) {
	                return i;
	            }
	        }
	        return -1;
	    }
	
	private static class TreeNode {
	    int val;
	    TreeNode left;
	    TreeNode right;
	    TreeNode(int x) { val = x; }
	}

	/**
	 * first element in pre-order arr is root
	 * 
	 * pre-order: (root, left, right) (1) 2 4 5 , 3 7 6 8  
	 * 								   	  \   /   \     /
	 *           			  	left sub-tree ,	right sub-tree	
	 */
	public TreeNode buildTreeInPre(int[] preorder, int[] inorder) {
        return buildTreeInPre(inorder, 0, inorder.length-1, preorder, 0, preorder.length-1);
    }
    
	
	 public TreeNode buildTreeInPre(int[] inorder, int inStart, int inEnd, int[] preorder, int preStart, int preEnd){
		if(preStart > preEnd)
			return null;
		
		//first element in pre-order arr is root
		int rootValue = preorder[preStart];
		TreeNode root = new TreeNode(rootValue);
		
		//find the root in in-order array
		int k=findIndex(inorder,rootValue, inStart, inEnd);		
		int leftsize = k-inStart;
		
		//no difference for inorder's start and end
		//set inorder left sub-tree, and preorder left sub-tree
		root.left = buildTreeInPre(inorder, inStart, k-1, preorder, preStart+1, preStart+leftsize);
				
		//set inorder right sub-tree, and preorder right sub-tree
		root.right = buildTreeInPre(inorder, k+1, inEnd, preorder, preStart+leftsize+1, preEnd);
		
		return root;
		}
	 
	 
	
	
	
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
		r1.right = r3;
		r2.left = r4;
		r2.right = r5;
		r3.right = r7;
		r7.left = r6;
		r7.right = r8;
	}

}
