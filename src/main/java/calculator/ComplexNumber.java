package calculator;

import lombok.Getter;

@Getter
public class ComplexNumber extends Number implements ComplexCalculator {
    private double real, imaginary;

    protected ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    @Override
    public ComplexNumber add(ComplexNumber num) {
        return new ComplexNumber(this.real + num.getReal(),
                this.imaginary + num.getImaginary());
    }

    @Override
    public ComplexNumber subtract(ComplexNumber num) {
        return new ComplexNumber(this.real - num.getReal(),
                this.imaginary - num.getImaginary());
    }

    @Override
    public double modulus() {
        return Math.sqrt(this.real * this.real + this.imaginary * this.imaginary);
    }

    @Override
    public ComplexNumber multiply(ComplexNumber num) {
        return new ComplexNumber(this.real * num.getReal() - this.imaginary * num.getImaginary(),
                this.real * num.getImaginary() + this.imaginary * num.getReal());
    }

    @Override
    public ComplexNumber divide(ComplexNumber num) {
        double denominator = num.getReal() * num.getReal() + num.getImaginary() * num.getImaginary();
        return new ComplexNumber((this.real * num.getReal() + this.imaginary * num.getImaginary()) / denominator,
                (this.imaginary * num.getReal() - this.real * num.getImaginary()) / denominator);
    }

    @Override
    public ComplexNumber power(int exponent) {
        double magnitude = Math.pow(this.modulus(), exponent);
        double angle = this.argument() * exponent;
        return new ComplexNumber(magnitude * Math.cos(angle), magnitude * Math.sin(angle));
    }

    @Override
    public ComplexNumber sqrt() {
        double mod = Math.sqrt(this.modulus());
        double arg = this.argument() / 2.0;
        double real = mod * Math.cos(arg);
        double imaginary = mod * Math.sin(arg);
        return new ComplexNumber(real, imaginary);
    }

    @Override
    public double argument() {
        return Math.atan2(imaginary, real);
    }

    @Override
    public String toString() {
        return String.format("%.2f + %.2fj", real, imaginary);
    }
}
