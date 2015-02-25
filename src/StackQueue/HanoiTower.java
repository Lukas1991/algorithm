package StackQueue;

import java.util.Stack;

public class HanoiTower {
	
	private Stack<Integer> disks;
	private int index;
	public static int sum=0;
	public HanoiTower(int i){
		disks=new Stack<Integer>();
		index=i;
	}
	
	public int index(){
		return index;
	}
	
	public void add(int d){
		//if disks.peek > d, place it
		if(!disks.isEmpty() && disks.peek() <=d){
			System.out.println("error placing disk "+d);			
		}else{
			disks.push(d);
		}		
	}
	
	public void moveTopTo(HanoiTower t){
		int top=disks.pop();
		t.add(top);
		System.out.println("Move disk "+ top+" from "+index()+" to "+t.index );
		sum++;
	}
	
	public void print(){
		System.out.println("Contents of Tower "+index());
		for(int i=disks.size()-1;i>=0;i--){
			System.out.println(" "+disks.get(i));
		}		
	}
	
	public void moveDisks(int n, HanoiTower destination, HanoiTower buffer){
		if(n<=0) return;
		else{
			moveDisks(n-1,buffer,destination);
			moveTopTo(destination);
			buffer.moveDisks(n-1, destination, this);
			
		}
	}
	
	public static void main(String[] args) {
		
		int n=4;
		//int n=5;
		HanoiTower[] towers=new HanoiTower[n];
		for(int i=0;i<3;i++) towers[i]=new HanoiTower(i);
		
		for (int i=n-1;i>=0;i--) towers[0].add(i);
		
		towers[0].moveDisks(n, towers[2], towers[1]);
		
		System.out.println(sum);
	}
	
	
	

}
