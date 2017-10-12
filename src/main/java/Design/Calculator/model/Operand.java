package Design.Calculator.model;

public class Operand extends Token {

    private int value;

    public Operand(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
