package calculator;

import utilities.ConsoleIO;
import utilities.View;

import java.math.BigInteger;

public class CalculatorView implements View {
    protected int getInt(String menu) {
        return ConsoleIO.intInput(menu);
    }

    protected ComplexNumber getComplexNumber() {
        double real = ConsoleIO.doubleInput("Введите действительную часть: ");
        double imaginary = ConsoleIO.doubleInput("Введите мнимую часть: ");
        return new ComplexNumber(real, imaginary);
    }

    protected RationalNumber getRationalNumber() {
        BigInteger numerator, denominator;
        numerator = ConsoleIO.bigIntInput("Введите числитель: ");
        do {
            denominator = ConsoleIO.bigIntInput("Введите знаменатель (!=0): ");
        } while (denominator.intValue() == 0);
        return new RationalNumber(numerator, denominator);
    }
}