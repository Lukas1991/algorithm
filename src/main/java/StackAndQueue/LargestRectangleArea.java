package StackAndQueue;

import java.util.Stack;

public class LargestRectangleArea {
	
	/**
	 * 84. Largest Rectangle in Histogram
	 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
	 */
	 public static int largestRectangleArea(int[] heights) {
	        //stack keep indexes
	        Stack<Integer> stack = new Stack<>();
	        //stack.push(-1);
	        int max = 0;
	        for (int i = 0; i < heights.length; i++) {
	            //if larger than the peek's height, push the index
	            if (stack.isEmpty() || heights[i] > heights[stack.peek()]) {
	                stack.push(i);
	            } else {
	                //pop from stack, draw a line at pop's height, 
	                //width is from stack.peek's right index(=stack.peek+1) to i's left (=i), width is i-stack.peek()-1
	                //calculate each area and compare with max
	                
	                while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {                
	                    int topIndex = stack.pop();
	                    int width = stack.isEmpty() ? i : i - stack.peek() - 1;
	                    int area = heights[topIndex] * width;
	                    max = Math.max(max, area);
	                    System.err.println("height= " + heights[topIndex] + " width= " + width + " current index height: " + heights[i]);
	                }
	                stack.push(i);
	            }
	            
	        }
	        
	        while (!stack.isEmpty()) {
	                int topIndex = stack.pop();
	                int width = stack.isEmpty() ? heights.length : heights.length - stack.peek() - 1;
                    int area = heights[topIndex] * width;
	                max = Math.max(max, area);
	        }
	        return max;
	    }

	public static void main(String[] args) {
		int[] heights = {3,6,5,7,4,8,1,0};
		largestRectangleArea(heights);
	}

}
