package StackAndQueue;

import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

public class CountOfAtoms {

    public String countOfAtoms(String formula) {
        Stack<Node> stack = new Stack<>();

        int i = 0;
        while (i < formula.length()) {
            char c = formula.charAt(i);
            if (Character.isUpperCase(c)) {

                String name = "" + c;
                while (i + 1 < formula.length() && Character.isLowerCase(formula.charAt(i+1))) {
                    name = name + formula.charAt(i+1);
                    i++;
                }

                String countS = "";
                while (i + 1 < formula.length() && Character.isDigit(formula.charAt(i+1))) {
                    countS = countS + formula.charAt(i+1);
                    i++;
                }

                int count = countS.isEmpty() ? 1 : Integer.valueOf(countS);

                stack.push(new Node(name, count));
                i++;
            } else if (c == '(') {
                stack.push(new Node("(", 0));
                i++;
            } else if (c == ')') {
                String countS = "";
                while (i + 1 < formula.length() && Character.isDigit(formula.charAt(i+1))) {
                    countS = countS + formula.charAt(i+1);
                    i++;
                }

                int count = countS.isEmpty() ? 1 : Integer.valueOf(countS);

                Stack<Node> tmp = new Stack<>();
                while (!stack.isEmpty() && !stack.peek().name.equals("(")) {
                    Node n = stack.pop();
                    n.count *= count;
                    tmp.push(n);
                }

                stack.pop(); //pop (

                while (!tmp.isEmpty()){
                    stack.push(tmp.pop());
                }

                i++;
            }

        }

        TreeMap<String, Integer> map = new TreeMap<>();
        while (!stack.isEmpty()) {
            Node n = stack.pop();
            map.put(n.name, map.getOrDefault(n.name, 0) + n.count);
        }

        StringBuilder sb = new StringBuilder();
        while(!map.isEmpty()) {
            Map.Entry<String, Integer> entry = map.pollFirstEntry();
            sb.append(entry.getKey());
            if (entry.getValue() > 1) {
                sb.append(entry.getValue());
            }
        }

        return sb.toString();
    }

    private class Node {
        String name;
        int count;

        public Node(String name, int count) {
            this.name = name;
            this.count = count;
        }
    }

    public static void main(String[] args) {
        CountOfAtoms obj = new CountOfAtoms();
        System.err.println(obj.countOfAtoms("K4(ON(SO3)2)2"));//K4N2O14S4
        System.err.println(obj.countOfAtoms("Mg(OH)2")); //H2MgO2
        System.err.println(obj.countOfAtoms("H2O"));
    }
}
