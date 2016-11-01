package Tree;

import java.util.ArrayList;
import java.util.LinkedList;

public class TreeProblem {
	 

	/*
	 * 4.1 check if a tree is balanced
	 * the difference of min depth and max depth should not exceed 1
	 * the difference is the maximum distance possible in the tree.
	 */

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
	
		
		//4.3 given a sorted array, create a BST with minimal height
		public static Node createMinimalBST (int[] array){
			if(array.length == 0) return null;
			
			return createMinimalBST(array, 0, array.length-1);
		}
	
		public static Node createMinimalBST (int[] arr,int start, int end){
			if(end<start){return null;}
			int mid=(start+end)/2;
			Node n=new Node(arr[mid]);
			n.left=createMinimalBST(arr,start,mid-1);
			n.right=createMinimalBST(arr,mid+1,end);
			return n;
		}
		
		
		
		
		//4.4 linked list of all nodes at each depth
		ArrayList<LinkedList<Node>> findLevelLinkList (Node root){
			int level=0;
			ArrayList<LinkedList<Node>> result=new ArrayList<LinkedList<Node>>();
			LinkedList<Node> list=new LinkedList<Node>();
			list.add(root);
			result.add(level,list);
			while(true){
				list=new LinkedList<Node>();
				for (int i=0; i<result.get(level).size();i++){
					Node n=(Node) result.get(level).get(i);
							if(n!=null){
								if(n.left!=null) list.add(n.left);
								if(n.right!=null) list.add(n.right);
							}
				}
				if (list.size()>0){
					result.add(level+1,list);
				}else{break;}
				level++;
			}
			return result;
		}
}

