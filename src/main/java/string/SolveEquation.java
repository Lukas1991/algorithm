package string;

import java.util.Arrays;

public class SolveEquation {

    /**
     * 640. Solve the Equation
     */
    public static String solveEquation(String equation) {
        String[] arr = equation.split("=");
        if (arr.length < 2) {
            return "No solution";
        }

        int[] leftCount = evaluateExpression(arr[0]);
        int[] rightCount = evaluateExpression(arr[1]);

        int countX = leftCount[0] - rightCount[0];
        int countN = rightCount[1] - leftCount[1];

        if (countX == 0 && countN == 0) {
            return "Infinite solutions";
        } else if (countX == 0) {
            return "No solution";
        } else {
            int res = countN / countX;
            return "x=" + res;
        }
    }

    /**
     * use positive lookahead by prefixing ?= group on the pattern. q(?=u) matches a q that is followed by a u, without making the u part of the match.
     * The positive lookahead construct is a pair of parentheses, with the opening parenthesis followed by a question mark and an equals sign.
     */
    public static int[] evaluateExpression(String exp) {
        String[] tokens = exp.split("(?=[-+])");
        int[] res =  new int[2];
        for (String token : tokens) {
            if (token.equals("+x") || token.equals("x")) res[0] += 1;
            else if (token.equals("-x")) res[0] -= 1;
            else if (token.contains("x")) res[0] += Integer.parseInt(token.substring(0, token.indexOf("x")));
            else res[1] += Integer.parseInt(token);
        }
        return res;
    }

    public static void main(String[] args) {

        System.err.println(solveEquation("x+5-3+x=6+x-2"));
        System.err.println(solveEquation("x=x"));
        System.err.println(solveEquation("2x=x"));
        System.err.println(solveEquation("2x+3x-6x=x+2"));
        System.err.println(solveEquation("x=x+2"));

        String exp = "2x+3x+5-6x";
        String[] tokens = exp.split("(?=[-+])");
        System.err.println(Arrays.toString(tokens));  //[2x, +3x, +5, -6x]
    }
}
