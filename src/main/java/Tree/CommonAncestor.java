package Tree;

public class CommonAncestor {

	public Node commonAncestor(Node root, Node p, Node q){
		
		if( !covers(root, p) || !covers(root,q)){
			return null;
		}
		
		return commonAncestorHelper(root, p, q);
	}
	
	public Node commonAncestorHelper(Node root, Node p, Node q){
		
		if(root == null) return null;
		if(root == p || root == q) return root;
		
		boolean is_p_on_left = covers(root.left, p);
		boolean is_q_on_left = covers(root.left, q);
		
		// if p and q are on different sides, return root
		if(is_p_on_left != is_q_on_left) return root;
		
		//else, they are on same side, Traverse this side
		Node child_side = is_p_on_left ? root.left : root.right;
		return commonAncestorHelper(child_side, p,q);
		
		
		
	}
	
	// Return true if p is a descendent of root
	public boolean covers(Node root, Node p){
		
		if(root == null) return false;
		if(root == p) return true;
		return covers(root.left, p) || covers(root.right, p);
		
	}
	
	
}
