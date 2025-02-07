package com.ecommerce.buymore.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

public class BdUtilsTest {

    @Test
    public void test() {
        BigDecimal one = null;
        BigDecimal two = null;

        // Add
        Assertions.assertEquals(BigDecimal.ZERO, BdUtils.add(one, two));
        Assertions.assertEquals(BigDecimal.ZERO, BdUtils.add(Optional.empty(), two));
        Assertions.assertEquals(BigDecimal.ZERO, BdUtils.add(one, Optional.empty()));
        Assertions.assertEquals(BigDecimal.ZERO, BdUtils.add(Optional.empty(), Optional.empty()));

        // Subtract
        Assertions.assertEquals(BigDecimal.ZERO, BdUtils.subtract(one, two));
        Assertions.assertEquals(BigDecimal.ZERO, BdUtils.subtract(Optional.empty(), two));
        Assertions.assertEquals(BigDecimal.ZERO, BdUtils.subtract(one, Optional.empty()));
        Assertions.assertEquals(BigDecimal.ZERO, BdUtils.subtract(Optional.empty(), Optional.empty()));

        // Multiply
        Assertions.assertEquals(BigDecimal.ZERO, BdUtils.multiply(one, two));
        Assertions.assertEquals(BigDecimal.ZERO, BdUtils.multiply(Optional.empty(), two));
        Assertions.assertEquals(BigDecimal.ZERO, BdUtils.multiply(one, Optional.empty()));
        Assertions.assertEquals(BigDecimal.ZERO, BdUtils.multiply(Optional.empty(), Optional.empty()));

        one = BigDecimal.valueOf(1);
        two = BigDecimal.valueOf(2);

        // Add
        Assertions.assertEquals(BigDecimal.valueOf(3), BdUtils.add(one, two));
        Assertions.assertEquals(BigDecimal.valueOf(2), BdUtils.add(Optional.empty(), two));
        Assertions.assertEquals(BigDecimal.valueOf(1), BdUtils.add(one, Optional.empty()));
        Assertions.assertEquals(BigDecimal.ZERO, BdUtils.add(Optional.empty(), Optional.empty()));

        // Subtract
        Assertions.assertEquals(BigDecimal.valueOf(-1), BdUtils.subtract(one, two));
        Assertions.assertEquals(BigDecimal.valueOf(-2), BdUtils.subtract(Optional.empty(), two));
        Assertions.assertEquals(BigDecimal.valueOf(1), BdUtils.subtract(one, Optional.empty()));
        Assertions.assertEquals(BigDecimal.ZERO, BdUtils.subtract(Optional.empty(), Optional.empty()));

        // Multiply
        Assertions.assertEquals(BigDecimal.valueOf(2), BdUtils.multiply(one, two));
        Assertions.assertEquals(BigDecimal.ZERO, BdUtils.multiply(Optional.empty(), two));
        Assertions.assertEquals(BigDecimal.ZERO, BdUtils.multiply(one, Optional.empty()));
        Assertions.assertEquals(BigDecimal.ZERO, BdUtils.multiply(Optional.empty(), Optional.empty()));
    }

}