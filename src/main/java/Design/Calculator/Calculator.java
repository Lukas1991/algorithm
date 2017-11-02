package Design.Calculator;

import Design.Calculator.handler.CalculateHandler;
import Design.Calculator.handler.CalculateHandlerFactory;
import Design.Calculator.model.Operand;
import Design.Calculator.model.Operator;
import Design.Calculator.model.Token;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Calculator {

    CalculateHandlerFactory handlerFactory = new CalculateHandlerFactory();

    public int calculate(String[] strings) {
        List<Token> list = Arrays.stream(strings)
            .map(Token.tokenFunction)
            .collect(Collectors.toList());

        Stack<Operand> stack = new Stack<>();

        for (Token token : list) {
            if (token instanceof Operator) {
                Operator operator = (Operator) token;

                Operand operand1 = stack.pop();
                Operand operand2 = stack.pop();

                CalculateHandler calculateHandler = handlerFactory.getCalculateHandler(operator.getOpType());
                Operand result = calculateHandler.cal(operand2, operand1);

                stack.push(result);
            } else {
                Operand operand = (Operand) token;
                stack.push(operand);
            }
        }

        return stack.pop().getValue();
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.err.println(calculator.calculate(new String[]{"2", "1", "+", "3", "*"}));
        System.err.println(calculator.calculate(new String[]{"4", "13", "5", "/", "+"}));
    }
}
