package calculator;

public interface ComplexCalculator extends Calculator<ComplexNumber> {
    double argument();
    double modulus();
}
