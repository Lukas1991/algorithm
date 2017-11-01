package Tree;

import java.util.Stack;

/**
 * 331. Verify Preorder Serialization of a Binary Tree
 */
public class IsValidSerialization {

    /**
     * using a stack, scan left to right
     * case 1: if we see a number, just push it to the stack
     * case 2: if we see #, check if the top of stack is also #
     * 2.1 if so, pop #, pop the number in a while loop, until top of stack is not #
     * 2.2 if not, push it to stack
     * in the end, check if the stack size is 1, and stack top is #
     */
    public boolean isValidSerialization(String preorder) {
        if (preorder.isEmpty()) {
            return true;
        }
        String[] arr = preorder.split(",");
        Stack<String> stack = new Stack<>();

        for (String s : arr) {
            if (s.equals("#")) {

                while (!stack.isEmpty() && stack.peek().equals("#")) {
                    stack.pop();
                    if (stack.isEmpty()) {
                        return false;
                    }
                    stack.pop();
                }

                stack.push(s);

            } else {
                stack.push(s);
            }
        }

        return stack.size() == 1 && stack.peek().equals("#");
    }
}
