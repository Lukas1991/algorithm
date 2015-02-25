package Graph;

public class DFS {

	/**
	 * @param args
	 */
	//recursive:
	public static void dfs(GraphNode root, int x){
		
		if (root == null) return;
		
		if(root.val == x){
			System.out.println("find in root");
		}
		
		root.visited = true;
		
		if(root.neighbors != null){
			for(GraphNode n : root.neighbors){
				if(!n.visited){
					
					dfs(n,x);
					
				}
				
			}
		}
		
		
	} 
	
	
	public static void main(String[] args) {

				GraphNode n1 = new GraphNode(1);
				dfs(null,1);
				dfs(n1,1);
	}

}
