package Recursion;

public class PaintFill {

	public static boolean PaintFill(int[][]screen, int x, int y, int newColor){
		int thisColor=screen[x][y];
		return PaintFill(screen, x, y, thisColor, newColor);		
	}
	
	public static boolean PaintFill(int[][]screen, int x, int y, int thisColor, int newColor){
		if(x<0 || x>=screen[0].length || y<0 || y>=screen.length){
			return false;
		}
		if(screen[x][y]==thisColor){
			screen[x][y]=newColor;
			PaintFill(screen, x-1,y,thisColor,newColor);
			PaintFill(screen, x+1,y,thisColor,newColor);
			PaintFill(screen, x,y-1,thisColor,newColor);
			PaintFill(screen, x,y+1,thisColor,newColor);
			
		}
		return true;
		
	}
	
	public static void main(String[] args) {
		int[][] screen=new int[5][5];
		for(int i=0;i<5;i++)
			for(int j=0;j<5;j++)
				screen[i][j]=0;
		
		screen[3][1]=2;screen[2][3]=2;screen[1][4]=2;screen[3][2]=2;screen[2][2]=2;screen[2][1]=2;
		
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++)
				System.out.print(screen[i][j]+",");
			System.out.println();
		}
		System.out.println();
		System.out.println();
		
		PaintFill(screen,3,1,3);
		
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++)
				System.out.print(screen[i][j]+",");
			System.out.println();
		}

	}

}
