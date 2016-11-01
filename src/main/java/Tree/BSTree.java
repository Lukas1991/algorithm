package Tree;

import java.util.Stack;

public class BSTree {
	public Node root;
	public BSTree(){
		root=null;
	}
	// -------------------------------------------------------------	
	public Node find(int key){
		Node current=root;  //start at root
				
		while(current!=null){ //while not to leaf
			if(current.data==key){
				return current;
			}else if(key<current.data){
				current=current.left;
			}else{
				current=current.right;
			}
		}
		//if current==null didn't find it
		return null;		
	}
	// -------------------------------------------------------------
	public void insert(int value){
		Node newNode=new Node(value);
		if(root==null){
			root=newNode;
			return;
		}
			
		Node parent=root;
		Node current=root;
		while(true){						
			if(value<=current.data){
				parent=current;
				current=current.left;
				if(current==null){//if go end of the line, insert here
					parent.left=newNode;
					System.out.println(value+" inserted");
					return;
				}				
			}				
			else {
				parent=current;
				current=current.right;
				if(current==null){
					parent.right=newNode;
					System.out.println(value+" inserted");
					return;
				}
			}
		}		
	}
	// -------------------------------------------------------------	
	public Node delete(int value){
		System.out.println("Deleting: "+value);
		Node current=root;
		Node parent=root;

		while(true){
			if(current==null){
				System.out.println("not find");
				return null;
			}
			if(value<current.data){
				parent=current;
				current=current.left;
			}else if(value>current.data) {
				parent=current;
				current=current.right;
			}
			else if (value==current.data){
				break;
			}
		}
		//delete the current node
		if(current.left==null && current.right==null){
			if(current==root){
				root=null;
			}
			if(value<parent.data){
				parent.left=null;
			}else{
				parent.right=null;
			}
		}
		//if do not have left child
		else if(current.left==null){
			if(current==root){
				root=current.right;
			}
			if(value<parent.data){
				parent.left=current.right;
			}else{
				parent.right=current.right;
			}
			
		}
		//if do not have right child
		else if(current.right==null){
			if(current==root){
				root=current.left;
			}
			if(value<parent.data){
				parent.left=current.left;
			}else{
				parent.right=current.left;
			}
		}
		//if has both left and right child
		else{		
			Node successor=getSuccessor(current);
			System.out.println("successor is: "+successor.data);
			
			if(current==root){
				root=successor;
			}
			if(value<parent.data){
				parent.left=successor;
			}else{
				parent.right=successor;				
			}
			successor.left=current.left;									
			
		}
	
		
		return current;
		
	}
	// -------------------------------------------------------------	
	   // returns node with next-highest value after delNode	
	   // goes to right child, then right child's left descendants	
	public Node getSuccessor(Node delNode){
		Node successor=delNode;
		Node successorParent=delNode;
		Node current=delNode.right;
		while(current!=null){
			successorParent=successor;
			successor=current;
			current=current.left;
		}
		if(successor!=delNode.right){
			successorParent.left=successor.right;
			successor.right=delNode.right;
		}
		
		//System.out.println("parent"+successorParent.data);
		return successor;
	}
	// -------------------------------------------------------------	
	
	//preOrder: mid, left, right
	public void preOrder(Node localRoot){
		if(localRoot!=null){
			localRoot.displayNode();
			preOrder(localRoot.left);
			preOrder(localRoot.right);
		}
	}
	
	//inOrder: left, mid, right
	public void inOrder(Node localRoot){
			if(localRoot!=null){
				inOrder(localRoot.left);
				localRoot.displayNode();				
				inOrder(localRoot.right);
			}
		}
		
	//postOrder: left right mid
	public void postOrder(Node localRoot){
		if(localRoot!=null){
			postOrder(localRoot.left);					
			postOrder(localRoot.right);
			localRoot.displayNode();		
		}
	}
	// -------------------------------------------------------------	
	public void displayTree()	
    {	
    Stack globalStack = new Stack();	
    globalStack.push(root);	
    int nBlanks = 32;	
    boolean isRowEmpty = false;	
    System.out.println(	
    "......................................................");	
    while(isRowEmpty==false)	
       {	
       Stack localStack = new Stack();	
       isRowEmpty = true;	
       // set isRowEmpty true, if globalStack is empty, exit the outer while
       // if globalStack not empty, but all nodes are leaf, isRowEmpty is still true, exit
       for(int j=0; j<nBlanks; j++)	
          System.out.print(' ');	
	
       while(globalStack.isEmpty()==false)	
          {	
          Node temp = (Node)globalStack.pop();	
          if(temp != null)	
             {	
             System.out.print(temp.data);	
             localStack.push(temp.left);	
             localStack.push(temp.right);	
             // if left not null OR right not null, that is have child, set isRowEmpty false,
             // we can have another level.
             // if left AND right are null, it is leaf, do not have child
             // if all the nodes in this level are leaf, isRowEmpty is true, we reach the bottom
             if(temp.left != null || temp.right != null)
            	 isRowEmpty = false;	
             }	
          else	 // temp is null
             {	
             System.out.print("--");	
             localStack.push(null);	
             localStack.push(null);	
             }	
          for(int j=0; j<nBlanks*2-2; j++)	
             System.out.print(' ');	
          }  // end while globalStack not empty	
       System.out.println();	
       nBlanks /= 2;	
     //move all the nodes in localStack to globalStack
		while(localStack.isEmpty()==false)	     	  
          globalStack.push( localStack.pop() );	
          
       }  // end while isRowEmpty is false	
    System.out.println(	
    "......................................................");	
    }  // end displayTree()	
	
	
	
	
	// -------------------------------------------------------------	

}
