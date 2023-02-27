package calculator;

import com.google.common.math.BigIntegerMath;

import java.math.BigInteger;
import java.math.RoundingMode;

public class RationalCalculator implements Calculator<RationalNumber> {
    @Override
    public RationalNumber add(RationalNumber num1, RationalNumber num2) {
        BigInteger commonDenominator = num1.getDenominator().multiply(num2.getDenominator());
        BigInteger numerator1 = num1.getNumerator().multiply(num2.getDenominator());
        BigInteger numerator2 = num2.getNumerator().multiply(num1.getDenominator());
        BigInteger numerator = numerator1.add(numerator2);
        return new RationalNumber(numerator, commonDenominator);
    }

    public RationalNumber commonDenominator(RationalNumber num1, RationalNumber num2) {
            BigInteger lcm = lcm(num1, num2);
            BigInteger numerator1 = num1.getNumerator().multiply(lcm).divide(num1.getDenominator());
            BigInteger numerator2 = num2.getNumerator().multiply(lcm).divide(num2.getDenominator());
            return new RationalNumber(numerator1.add(numerator2), lcm);
    }

    @Override
    public RationalNumber subtract(RationalNumber num1, RationalNumber num2) {
        BigInteger commonDenominator = num1.getDenominator().multiply(num2.getDenominator());
        BigInteger numerator1 = num1.getNumerator().multiply(num2.getDenominator());
        BigInteger numerator2 = num2.getNumerator().multiply(num1.getDenominator());
        BigInteger numerator = numerator1.subtract(numerator2);
        return new RationalNumber(numerator, commonDenominator);
    }

    public BigInteger lcm(RationalNumber a, RationalNumber b) {
        BigInteger gcd = a.getDenominator().gcd(b.getDenominator());
        return a.getDenominator().multiply(b.getDenominator()).divide(gcd);
    }

    @Override
    public RationalNumber multiply(RationalNumber num1, RationalNumber num2) {
        BigInteger numerator = num1.getNumerator().multiply(num2.getNumerator());
        BigInteger denominator = num1.getDenominator().multiply(num2.getDenominator());
        return new RationalNumber(numerator, denominator);
    }

    @Override
    public RationalNumber divide(RationalNumber num1, RationalNumber num2) {
        BigInteger numerator = num1.getNumerator().multiply(num2.getDenominator());
        BigInteger denominator = num1.getDenominator().multiply(num2.getNumerator());
        return new RationalNumber(numerator, denominator);
    }

    @Override
    public RationalNumber power(RationalNumber base, int exponent) {
        BigInteger numerator = base.getNumerator().pow(exponent);
        BigInteger denominator = base.getDenominator().pow(exponent);
        return new RationalNumber(numerator, denominator);
    }

    @Override
    public RationalNumber sqrt(RationalNumber num) {
        BigInteger numerator = BigIntegerMath.sqrt(num.getNumerator(), RoundingMode.HALF_EVEN);
        BigInteger denominator = BigIntegerMath.sqrt(num.getDenominator(), RoundingMode.HALF_EVEN);
        return new RationalNumber(numerator, denominator);
    }
}
