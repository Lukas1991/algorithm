package Design.Calculator.model;

public enum OpType {
    ADD("+"), SUBSTRACT("-"), MULTIPLY("*"), DIVIDE("/");

    private String symbol;

    OpType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static OpType fromSymbol(String s) {
        for (OpType opType : OpType.values()) {
            if (opType.getSymbol().equals(s)) {
                return opType;
            }
        }
        return null;
    }
}
