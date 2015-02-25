package Graph;

public class GraphNode {

	/**
	 * @param args
	 */
	int val;
	GraphNode next;
	GraphNode[] neighbors;
	boolean visited;
	
	GraphNode(int x){
		val= x;
	}
	
	GraphNode(int x, GraphNode[] n){
		this.val = x;
		this.neighbors = n;
	}
	
	public String toString(){
		return "value: " + this.val;
	}
	
	

}
