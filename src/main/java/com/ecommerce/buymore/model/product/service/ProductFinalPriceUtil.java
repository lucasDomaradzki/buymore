package com.ecommerce.buymore.model.product.service;

import com.ecommerce.buymore.common.BdUtils;

import java.math.BigDecimal;

import static com.ecommerce.buymore.common.BdUtils.SCALE_AND_ROUND;

/**
 * Utility class to calculate the final price with starting (price + profit margin) - discount
 */
public class ProductFinalPriceUtil {

    public static BigDecimal getFinalPrice(BigDecimal startingPrice, double profitMargin, BigDecimal discount) {
        BigDecimal margin = BigDecimal.valueOf(profitMargin / 100);
        BigDecimal profit = BdUtils.multiply(startingPrice, margin);
        BigDecimal startingPricePlusProfit = BdUtils.add(startingPrice, profit);
        BigDecimal finalPrice = BdUtils.subtract(startingPricePlusProfit, discount);
        finalPrice = SCALE_AND_ROUND.apply(finalPrice);
        return finalPrice;
    }

}
