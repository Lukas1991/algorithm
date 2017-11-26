package StackAndQueue;

import java.util.Stack;

/**
 * 735. Asteroid Collision
 * Say we have our answer as a stack with rightmost asteroid top, and a new asteroid comes in.
 * If new is moving right (new > 0), or if top is moving left (top < 0), no collision occurs.
 * Otherwise, if abs(new) < abs(top), then the new asteroid will blow up;
 * if abs(new) == abs(top) then both asteroids will blow up;
 * and if abs(new) > abs(top), then the top asteroid will blow up (and possibly more asteroids will, so we should continue checking.)
 */
public class AsteroidCollision {

    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack();
        for (int ast: asteroids) {
            checkCollision(stack, ast);
        }

        int[] ans = new int[stack.size()];
        for (int t = ans.length - 1; t >= 0; --t) {
            ans[t] = stack.pop();
        }
        return ans;
    }

    private void checkCollision(Stack<Integer> stack, int newValue) {
        while (!stack.isEmpty() && newValue < 0 && stack.peek() > 0) {
            if (stack.peek() < -newValue) {
                stack.pop();
                continue;
            } else if (stack.peek() == -newValue) {
                stack.pop();
                return;
            } else {
                return;
            }
        }

        stack.push(newValue);
    }
}
