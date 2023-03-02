package calculator;

import com.google.common.math.BigIntegerMath;
import lombok.Getter;

import java.math.BigInteger;
import java.math.RoundingMode;

@Getter
public class RationalNumber extends Number implements RationalCalculator {
    private BigInteger numerator, denominator;

    protected RationalNumber(BigInteger numerator, BigInteger denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Override
    public RationalNumber add(RationalNumber num) {
        BigInteger commonDenominator = this.denominator.multiply(num.getDenominator());
        BigInteger numerator1 = this.numerator.multiply(num.getDenominator());
        BigInteger numerator2 = num.getNumerator().multiply(this.denominator);
        BigInteger numerator = numerator1.add(numerator2);
        return new RationalNumber(numerator, commonDenominator).reduce();
    }


    public RationalNumber toCommonDenominator(RationalNumber num) {
        BigInteger lcm = this.lcm(num);
        BigInteger numerator1 = this.numerator.multiply(lcm).divide(this.denominator);
        BigInteger numerator2 = num.getNumerator().multiply(lcm).divide(num.getDenominator());
        return new RationalNumber(numerator1.add(numerator2), lcm).reduce();
    }

    @Override
    public RationalNumber subtract(RationalNumber num) {
        BigInteger commonDenominator = this.denominator.multiply(num.getDenominator());
        BigInteger numerator1 = this.numerator.multiply(num.getDenominator());
        BigInteger numerator2 = num.getNumerator().multiply(this.denominator);
        BigInteger numerator = numerator1.subtract(numerator2);
        return new RationalNumber(numerator, commonDenominator).reduce();
    }

    public BigInteger lcm(RationalNumber num) {
        BigInteger gcd = this.denominator.gcd(num.getDenominator());
        return this.denominator.multiply(num.getDenominator()).divide(gcd);
    }

    @Override
    public RationalNumber multiply(RationalNumber num) {
        BigInteger numerator = this.numerator.multiply(num.getNumerator());
        BigInteger denominator = this.denominator.multiply(num.getDenominator());
        return new RationalNumber(numerator, denominator).reduce();
    }

    @Override
    public RationalNumber divide(RationalNumber num) {
        BigInteger numerator = this.numerator.multiply(num.getDenominator());
        BigInteger denominator = this.denominator.multiply(num.getNumerator());
        return new RationalNumber(numerator, denominator).reduce();
    }

    @Override
    public RationalNumber power(int exponent) {
        BigInteger numerator = this.numerator.pow(exponent);
        BigInteger denominator = this.denominator.pow(exponent);
        return new RationalNumber(numerator, denominator).reduce();
    }

    public RationalNumber reduce() {
        BigInteger gcd = numerator.gcd(denominator);
        return new RationalNumber(numerator.divide(gcd), denominator.divide(gcd));
    }

    @Override
    public RationalNumber sqrt() {
        BigInteger numerator = BigIntegerMath.sqrt(this.numerator, RoundingMode.HALF_EVEN);
        BigInteger denominator = BigIntegerMath.sqrt(this.denominator, RoundingMode.HALF_EVEN);
        return new RationalNumber(numerator, denominator).reduce();
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
}