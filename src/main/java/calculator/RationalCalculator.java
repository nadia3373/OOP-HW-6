package calculator;

import java.math.BigInteger;

public interface RationalCalculator extends Calculator<RationalNumber> {
    RationalNumber toCommonDenominator(RationalNumber num);

    BigInteger lcm(RationalNumber num);
}
