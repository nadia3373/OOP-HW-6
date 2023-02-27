package calculator;

public class ComplexCalculator implements Calculator<ComplexNumber> {
    @Override
    public ComplexNumber add(ComplexNumber num1, ComplexNumber num2) {
        return new ComplexNumber(num1.getReal() + num2.getReal(),
                num1.getImaginary() + num2.getImaginary());
    }

    @Override
    public ComplexNumber subtract(ComplexNumber num1, ComplexNumber num2) {
        return new ComplexNumber(num1.getReal() - num2.getReal(),
                num1.getImaginary() - num2.getImaginary());
    }

    public double modulus(ComplexNumber num) {
        return Math.sqrt(num.getReal() * num.getReal() + num.getImaginary() * num.getImaginary());
    }

    @Override
    public ComplexNumber multiply(ComplexNumber num1, ComplexNumber num2) {
        return new ComplexNumber(num1.getReal() * num2.getReal() - num1.getImaginary() * num2.getImaginary(),
                num1.getReal() * num2.getImaginary() + num1.getImaginary() * num2.getReal());
    }

    @Override
    public ComplexNumber divide(ComplexNumber num1, ComplexNumber num2) {
        double denominator = num2.getReal() * num2.getReal() + num2.getImaginary() * num2.getImaginary();
        return new ComplexNumber((num1.getReal() * num2.getReal() + num1.getImaginary() * num2.getImaginary()) / denominator,
                (num1.getImaginary() * num2.getReal() - num1.getReal() * num2.getImaginary()) / denominator);
    }

    @Override
    public ComplexNumber power(ComplexNumber base, int exponent) {
        double magnitude = Math.pow(base.modulus(), exponent);
        double angle = base.argument() * exponent;
        return new ComplexNumber(magnitude * Math.cos(angle), magnitude * Math.sin(angle));
    }


    @Override
    public ComplexNumber sqrt(ComplexNumber num) {
        double mod = Math.sqrt(num.modulus());
        double arg = num.argument() / 2.0;
        double real = mod * Math.cos(arg);
        double imaginary = mod * Math.sin(arg);
        return new ComplexNumber(real, imaginary);
    }
}
