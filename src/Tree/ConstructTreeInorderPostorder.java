package Tree;

public class ConstructTreeInorderPostorder {

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

	public TreeNode buildTree(int[] inorder, int[] postorder) {
        int inStart = 0;
        int inEnd = inorder.length-1;
        int postStart =0;
        int postEnd = postorder.length-1;
 
        return buildTree(inorder, inStart, inEnd, postorder, postStart, postEnd);
    }
	
	 public TreeNode buildTree(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd){
		if(postStart > postEnd)
			return null;
		
		//last element in post-order array is root
		int rootValue = postorder[postEnd];
		TreeNode root = new TreeNode(rootValue);
		
		//find the root in in-order array
		int k=findIndex(inorder,rootValue, inStart, inEnd);		
		int leftsize = k-inStart;
		
		//set inorder left sub-tree, and postorder left sub-tree
		root.left = buildTree(inorder, inStart, k-1, postorder, postStart, postStart+leftsize-1);
				
		//set inorder right sub-tree, and postorder right sub-tree
		root.right = buildTree(inorder, k+1, inEnd, postorder, postStart+leftsize, postEnd-1);
		
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
