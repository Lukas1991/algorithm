package Graph;

public class BFS {

	/**
	 * @param args
	 */
	public static void bfs(GraphNode root, int x){
		if (root == null) return;
		
		if(root.val == x){
			System.out.println("find in root");
		}
		
		CustomQueue q = new CustomQueue();
		root.visited = true;
		q.enqueue(root);
		
		//while q not empty
		while(q.first != null){
			
			GraphNode current = q.dequeue();
			if(current.neighbors != null){
				for(GraphNode n: current.neighbors){
					if(!n.visited){
						n.visited = true;
						if(n.val == x){
							System.out.println("find"+n);
						}
						q.enqueue(n);
					}
					
				}
			}
			
		}
		
	}
	
	
	public static void main(String[] args) {
		GraphNode n1 = new GraphNode(1);
		bfs(null,1);
		bfs(n1,1);
		
	}

}
