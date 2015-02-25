package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CloneGraph {

	/**
	 * @param args
	 */
	
	class UndirectedGraphNode {
		      int label;
		      List<UndirectedGraphNode> neighbors;
		      UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
		  };
	
		  
		  
		  
		  
		  public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		        if(node == null) return null;
		        UndirectedGraphNode newHead = new UndirectedGraphNode(node.label);
		        
		        //use a map to flag whether a node has been visited or not
		        Map<UndirectedGraphNode,UndirectedGraphNode> map=new HashMap<UndirectedGraphNode,UndirectedGraphNode>();
		        
		        Queue<UndirectedGraphNode> q = new LinkedList<UndirectedGraphNode>();
		        q.add(node);
		        map.put(node, newHead);
		        
		        while(q.size()>0){
		            UndirectedGraphNode current = q.poll();
		            ArrayList<UndirectedGraphNode> currentN = (ArrayList<UndirectedGraphNode>) current.neighbors;
		            
		            if(currentN != null && currentN.size() > 0){
		                
		                for(UndirectedGraphNode n : currentN){
		                    if(!map.containsKey(n)){
		                        //if not visited
		                         UndirectedGraphNode newNode = new UndirectedGraphNode(n.label);
		                         
		                         map.get(current).neighbors.add(newNode);
		                         map.put(n,newNode);
		                         q.add(n);
		                         
		                    }else{
		                         map.get(current).neighbors.add(map.get(n));
		                    }
		                    
		                    
		                    
		                    
		                    
		                }
		            }
		            
		            
		            
		        }
		        
		        
		        return newHead;
		               
		    }
			
		  
		  
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
