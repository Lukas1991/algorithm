package Design.Calculator.handler;

import Design.Calculator.model.OpType;

public class CalculateHandlerFactory {

    public CalculateHandler getCalculateHandler(OpType type) {
        switch (type) {
            case ADD:
                return new AddHandler();
            case SUBSTRACT:
                return new SubstractHandler();
            case MULTIPLY:
                return new MultiplyHandler();
            case DIVIDE:
                return new DivideHandler();
            default:
                throw new UnsupportedOperationException();
        }
    }
}
