package Design.Calculator.handler;

import Design.Calculator.model.Operand;

public class AddHandler implements CalculateHandler {

    @Override
    public Operand cal(Operand op1, Operand op2) {
        int value = op1.getValue() + op2.getValue();
        return new Operand(value);
    }
}
