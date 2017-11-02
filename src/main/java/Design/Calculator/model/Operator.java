package Design.Calculator.model;

public class Operator extends Token {

    private OpType opType;

    public Operator(OpType type) {
        this.opType = type;
    }

    public OpType getOpType() {
        return opType;
    }
}
