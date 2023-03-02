package calculator;

import utilities.Model;

import java.math.BigInteger;

public class CalculatorModel implements Model {
    protected ComplexNumber complexSqrt(ComplexNumber num) {
        ComplexNumber result = num.sqrt();
        return result;
    }

    protected ComplexNumber complexPower(ComplexNumber num, int power) {
        return num.power(power);
    }

    protected ComplexNumber complexDivide(ComplexNumber num1, ComplexNumber num2) {
        return num1.divide(num2);
    }

    protected ComplexNumber complexMultiply(ComplexNumber num1, ComplexNumber num2) {
        return num1.multiply(num2);
    }

    protected ComplexNumber complexSubtract(ComplexNumber num1, ComplexNumber num2) {
        return num1.subtract(num2);
    }

    protected ComplexNumber complexAdd(ComplexNumber num1, ComplexNumber num2) {
        return num1.add(num2);
    }

    protected double complexModulus(ComplexNumber num) {
        return num.modulus();
    }

    protected double complexArgument(ComplexNumber num) {
        return num.argument();
    }

    protected BigInteger rationalLcm(RationalNumber num1, RationalNumber num2) {
        return num1.lcm(num2);
    }

    protected RationalNumber rationalGcd(RationalNumber num1, RationalNumber num2) {
        return num1.toCommonDenominator(num2);
    }

    protected RationalNumber rationalSqrt(RationalNumber num) {
        return num.sqrt();
    }

    protected RationalNumber rationalPower(RationalNumber num, int power) {
        return num.power(power);
    }

    protected RationalNumber rationalDivide(RationalNumber num1, RationalNumber num2) {
        return num1.divide(num2);
    }

    protected RationalNumber rationalMultiply(RationalNumber num1, RationalNumber num2) {
        return num1.multiply(num2);
    }

    protected RationalNumber rationalSubtract(RationalNumber num1, RationalNumber num2) {
        return num1.subtract(num2);
    }

    protected RationalNumber rationalAdd(RationalNumber num1, RationalNumber num2) {
        return num1.add(num2);
    }
}
