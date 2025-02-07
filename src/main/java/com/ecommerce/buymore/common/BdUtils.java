package com.ecommerce.buymore.common;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Optional;
import java.util.function.Function;

import static java.math.BigDecimal.ZERO;

public class BdUtils {

    public static final RoundingMode DEFAULT_ROUND_MODE = RoundingMode.HALF_DOWN;
    public static final Function<BigDecimal, BigDecimal> SCALE_AND_ROUND =
            x -> x.setScale(2, DEFAULT_ROUND_MODE);

    private static BigDecimal emptyThenZero(Optional<BigDecimal> value) {
        return value.orElse(ZERO);
    }

    private static BigDecimal nullThenZero(BigDecimal value) {
        return value == null ? ZERO : value;
    }

    public static BigDecimal add(BigDecimal one, BigDecimal two) {
        return nullThenZero(one).add(nullThenZero(two));
    }

    public static BigDecimal add(Optional<BigDecimal> one, BigDecimal two) {
        return emptyThenZero(one).add(nullThenZero(two));
    }

    public static BigDecimal add(BigDecimal one, Optional<BigDecimal> two) {
        return nullThenZero(one).add(emptyThenZero(two));
    }

    public static BigDecimal add(Optional<BigDecimal> one, Optional<BigDecimal> two) {
        return emptyThenZero(one).add(emptyThenZero(two));
    }

    public static BigDecimal subtract(BigDecimal one, BigDecimal two) {
        return nullThenZero(one).subtract(nullThenZero(two));
    }

    public static BigDecimal subtract(Optional<BigDecimal> one, BigDecimal two) {
        return emptyThenZero(one).subtract(nullThenZero(two));
    }

    public static BigDecimal subtract(BigDecimal one, Optional<BigDecimal> two) {
        return nullThenZero(one).subtract(emptyThenZero(two));
    }

    public static BigDecimal subtract(Optional<BigDecimal> one, Optional<BigDecimal> two) {
        return emptyThenZero(one).subtract(emptyThenZero(two));
    }

    public static BigDecimal multiply(BigDecimal one, BigDecimal two) {
        return nullThenZero(one).multiply(nullThenZero(two));
    }

    public static BigDecimal multiply(Optional<BigDecimal> one, BigDecimal two) {
        return emptyThenZero(one).multiply(nullThenZero(two));
    }

    public static BigDecimal multiply(BigDecimal one, Optional<BigDecimal> two) {
        return nullThenZero(one).multiply(emptyThenZero(two));
    }

    public static BigDecimal multiply(Optional<BigDecimal> one, Optional<BigDecimal> two) {
        return emptyThenZero(one).multiply(emptyThenZero(two));
    }

}
