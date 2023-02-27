package calculator;

import lombok.Getter;

import java.math.BigInteger;

@Getter
public class RationalNumber extends Number {
    private BigInteger numerator, denominator;

    public RationalNumber(BigInteger numerator, BigInteger denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }
    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
}