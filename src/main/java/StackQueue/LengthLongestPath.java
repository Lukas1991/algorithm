package StackQueue;

import java.util.Stack;

public class LengthLongestPath {

	public int lengthLongestPath(String input) {
        String[] arr = input.split("\n");
        //store dir length
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        
        for (int i = 0; i < arr.length; i++) {
            String s = arr[i];
            String text = s.replaceAll("\t", "");
            int level = s.length() - text.length();
            
            while (stack.size() > level) {
                stack.pop();
            }
            
            int count = text.length();
                
            if (isFile(text)) {
                if (!stack.isEmpty()) {
                     count = count + stack.peek() + 1;
                }
                
                max = Math.max(max, count);
            } else {
                if (stack.isEmpty()) {
                     stack.push(count);
                } else {
                    stack.push(stack.peek() + count + 1);
                }
            }
        }
        
        return max;
    }
    
    private boolean isFile(String s) {
        return s.indexOf(".") > -1;
    }
    
	public static void main(String[] args) {
		LengthLongestPath obj = new LengthLongestPath();
		int max = obj.lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext");
		System.err.println(max);
	}

}
