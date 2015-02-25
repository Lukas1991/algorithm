package StackQueue;

class StackNode{
	public int previous;//previous index
	public int value;
	public StackNode(int p, int v){
		value=v;
		previous=p;
	}
}


class ThreeStack {
	int stackSize=300;
	int indexUsed=0;
	int bufferPP=0;
	int[] stackPointer={-1,-1,-1,-1};
	StackNode[] buffer=new StackNode[stackSize*4];
	
	void push(int stackNum, int value){
		int lastIndex=stackPointer[stackNum];
		if(stackPointer[3]>-1){
			StackNode deleted=buffer[stackPointer[3]];
			
			buffer[stackPointer[3]]=new StackNode(lastIndex,value);
			stackPointer[stackNum]=stackPointer[3];
			stackPointer[3]=deleted.previous;
			
		}else{
			buffer[indexUsed]=new StackNode(lastIndex,value);
			stackPointer[stackNum]=indexUsed;
			indexUsed++;
		}
		
		
		//System.out.println(stackPointer[stackNum]);
	}

	int pop(int stackNum){
		int lastIndex=stackPointer[stackNum];
		
		StackNode deleted=buffer[lastIndex];
		int temp=buffer[lastIndex].value;
		
		stackPointer[stackNum]=deleted.previous;
		//insert into deleted stack
		deleted.previous=stackPointer[3];
		
		stackPointer[3]=lastIndex;
		
				//buffer[lastIndex]=null;   ///!!!!!!
		//indexUsed--; ///!!!!!!				
		//indexUsed=stackPointer[stackNum];
		return temp;
		
		
	}
	int peek(int stackNum){
		int lastIndex=stackPointer[stackNum];
		return buffer[lastIndex].value;		
	}
	
	boolean isEmpty(int stackNum){
		return stackPointer[stackNum] ==-1;		
	}
	void display(){
		for(int i=0;i<indexUsed;i++) System.out.print(buffer[i].value+"->");
		System.out.println("   indexUsed:"+indexUsed);
		
	}
	public static void main(String[] args){
		ThreeStack test=new ThreeStack();
		test.push(0, 10);
		test.push(0, 20);
		//test.display();
		test.push(1, 11);test.display();
		test.pop(0);test.display();
		test.push(1, 21);
		test.display();
		
		
		
	}
}
