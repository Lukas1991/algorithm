package Design.Calculator.model;

import java.util.function.Function;

public class Token {

    public Token() {
    }

    public static Function<String, Token> tokenFunction = s -> {
        OpType opType = OpType.fromSymbol(s);
        if (opType == null) {
            return new Operand(Integer.valueOf(s));
        } else {
            return new Operator(opType);
        }
    };
}
