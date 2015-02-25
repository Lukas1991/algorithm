package Tree;

import java.util.ArrayList;
import java.util.Collections;

public class isBST {

	/**
	 *All values on the left sub tree must be less than root, 
	 *and all values on the right sub tree must be greater than root.
	 */
	public static boolean isValidBST(Node root) {
		return validate(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
 
	public static boolean validate(Node root, int min, int max) {
		if (root == null) {
			return true;
		}
 
		// not in range
		if (root.data <= min || root.data >= max) {
			return false;
		}
 
		// left subtree must be < root.val && right subtree must be > root.val
		return validate(root.left, min, root.data) && validate(root.right, root.data, max);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList list = new ArrayList();
		
		Collections.shuffle(list);

	}

}
