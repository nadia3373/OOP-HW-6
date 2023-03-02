package calculator;

public interface Calculator<T extends Number> {
    T add(T num);
    T subtract(T num);
    T multiply(T num);
    T divide(T num);
    T power(int exponent);
    T sqrt();
}
