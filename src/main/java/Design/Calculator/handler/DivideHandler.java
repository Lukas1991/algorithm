package Design.Calculator.handler;

import Design.Calculator.model.Operand;

public class DivideHandler implements CalculateHandler {

    @Override
    public Operand cal(Operand op1, Operand op2) {
        if (op2.getValue() == 0) {
            return new Operand(Integer.MAX_VALUE);
        }

        int value = op1.getValue() / op2.getValue();
        return new Operand(value);
    }
}
