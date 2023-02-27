package calculator;

public interface Calculator<T extends Number> {
    T add(T num1, T num2);
    T subtract(T num1, T num2);
    T multiply(T num1, T num2);
    T divide(T num1, T num2);
    T power(T base, int exponent);
    T sqrt(T num);
}
