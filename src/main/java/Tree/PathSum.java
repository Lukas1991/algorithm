package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class PathSum {

	/**
	 * @param args
	 */
	public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        if(root.left== null && root.right == null){
        	return sum == root.val? true:false;
        }else{
        	return hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val) ; 
        }
        
        
    }
	/**
	 * two Queue, one for Node, one for values
	 */
	public boolean hasPathSumIterative(TreeNode root, int sum) {
        if(root == null) return false;
        LinkedList<TreeNode> nodes = new LinkedList<TreeNode>();
        LinkedList<Integer> values = new LinkedList<Integer>();
 
        nodes.add(root);
        values.add(root.val);
        
        while(!nodes.isEmpty()){
        	TreeNode curr = nodes.remove();
        	int sumValue = values.remove();
        	
        	if(curr.val==sum && curr.left==null && curr.right==null) return true;
        	
        	if(curr.left != null){
                nodes.add(curr.left);
                values.add(sumValue+curr.left.val);
            }
 
            if(curr.right != null){
                nodes.add(curr.right);
                values.add(sumValue+curr.right.val);
            }
         	
        }
        
        return false;
        
    }
	
	/**
	 * return all paths
	 * after visit a node, remove current node from list
	 */
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root == null) return res;
        
        pathSum(root, sum, new ArrayList<Integer>(), res);
        return res;
    }
    
    public void pathSum(TreeNode root, int sum, ArrayList<Integer> currList, List<List<Integer>>res){
        
        if(root == null) return;
        if(root.left == null && root.right == null && root.val == sum){
            currList.add(root.val);
            res.add(new ArrayList<Integer>(currList));
            currList.remove(currList.size()-1);
            return;
        }
        
        currList.add(root.val);
        pathSum(root.left, sum-root.val, currList, res);
        pathSum(root.right, sum-root.val, currList, res);
        currList.remove(currList.size()-1);
        
        
    }
	
	
	private static class TreeNode {
	    int val;
	    TreeNode left;
	    TreeNode right;
	    TreeNode(int x) { val = x; }
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
