package regressao;

public enum Sign {
    ADDITION("+") {
        @Override public double apply(double x1, double x2) {
            return x1 + x2;
        }
    },
    SUBTRACTION("-") {
        @Override public double apply(double x1, double x2) {
            return x1 - x2;
        }
    },
    MULTIPLICATION("*"){
        @Override public double apply(double x1, double x2){
            return x1 * x2;
        }
    },
    DIVISION("/"){
        @Override public double apply(double x1, double x2){
            return x1 / x2;
        }
    };
    // You'd include other operators too...

    private final String text;
    private static final Sign[] toInteger = Sign.values();

    private Sign(String text) {
        this.text = text;
    }

    public static Sign byInteger(int i){
        return toInteger[i];
    }

    // Yes, enums *can* have abstract methods. This code compiles...
    public abstract double apply(double x1, double x2);

    @Override public String toString() {
        return text;
    }
}
