package Tree;

public class ContainsTree {

	/**
	 * @param args
	 */
	public boolean containsTree(Node t1, Node t2){
		
		if(t2 == null) return true; //the empty tree is always a subtree
		
		return subTree(t1, t2);
		
	}
	
	public boolean subTree(Node t1, Node t2){
		if(t1 == null) return false; //big tree empty & subtree still not found
		
		if(t1.data == t2.data){
			if(matchTree(t1,t2)) return true;
		}
		
		return matchTree(t1.left,t2) || matchTree(t1.right,t2);
		
		
	}
	
	public boolean matchTree(Node p, Node q) {
        
        if(p == null && q == null) return true;
        else if(p == null && q != null) return false;
        else if (p != null && q == null) return false;
        else{
            if(p.data != q.data) return false;
            else{
                return matchTree(p.left, q.left) && matchTree(p.right, q.right);
            }
            
        }
        
        
    }
	
	
	public static void main(String[] args) {

	}

}
