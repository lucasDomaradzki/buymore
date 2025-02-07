package com.ecommerce.buymore.model.product.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ProductFinalPriceUtilTest {

    @Test
    public void testFinalPrices() {
        Assertions.assertThat(
            ProductFinalPriceUtil.getFinalPrice(BigDecimal.valueOf(100), 10.0, BigDecimal.ZERO)
        ).isEqualTo(new BigDecimal("110.00"));

        Assertions.assertThat(
            ProductFinalPriceUtil.getFinalPrice(BigDecimal.valueOf(700), 35, BigDecimal.valueOf(100))
        ).isEqualTo(new BigDecimal("845.00"));

        Assertions.assertThat(
            ProductFinalPriceUtil.getFinalPrice(BigDecimal.valueOf(1339), 25.75, BigDecimal.valueOf(135))
        ).isEqualTo(new BigDecimal("1548.79"));

        Assertions.assertThat(
            ProductFinalPriceUtil.getFinalPrice(BigDecimal.ZERO, 25.75, BigDecimal.ZERO)
        ).isEqualTo(new BigDecimal("00.00"));

        Assertions.assertThat(
            ProductFinalPriceUtil.getFinalPrice(BigDecimal.ZERO, 25.75, BigDecimal.valueOf(100))
        ).isEqualTo(new BigDecimal("-100.00"));
    }

}