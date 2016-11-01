package Recursion;

public class PlaceQueen {

	public static int Column[]=new int[8];
	public static int Column2[]=new int[8];
	public static int count=0;
	public static boolean check(int row){
		//check this row with previous rows
		for(int i=0;i<row;i++){
			int diff=Math.abs(Column[i]-Column[row]);
			//when diff ==(row-i), diagonal
			if((diff==(row-i)) ||(Column[i]==Column[row])){
				return false;
			}			
		}		
		return true;
	}
	
	public static void PlaceQueen(int row){
		if(row==8){
			printBoard();
			count++;
			System.out.println("----------");
			return;
			}
		for(int i=0;i<8;i++){
			Column[row]=i;
			if(check(row)){
				//Column2[row]=Column[row];
				//System.out.println(Column[row]);//if true, leave Queen here, begin to place next
				PlaceQueen(row+1);
			}
		}
				
	}
	public static void printBoard(){
		for(int i=0;i<8;i++){
			int column=Column[i];
			
			System.out.println("("+i+","+column+")");
			
			
			/*for(int j=0;j<column;j++){
				System.out.print(" ,");
				
			}
			System.out.print(column);
		
		System.out.println();*/
		}
	}
	
	public static void main(String[] args) {
		PlaceQueen(0);
		System.out.println(count);
				
	}

}
