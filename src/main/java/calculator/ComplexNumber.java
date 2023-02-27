package calculator;

import lombok.Getter;

@Getter
public class ComplexNumber extends Number {
    private double real, imaginary;

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    protected double argument() {
        return Math.atan2(imaginary, real);
    }

    protected double modulus() {
        return Math.sqrt(real * real + imaginary * imaginary);
    }

    @Override
    public String toString() {
        return String.format("%.2f + %.2fj", real, imaginary);
    }
}
