package com.ecommerce.buymore.model.product.service;

import com.ecommerce.buymore.common.exception.BadRequestErrorException;
import com.ecommerce.buymore.common.exception.BuyMoreException;
import com.ecommerce.buymore.model.product.dto.ProductCreateDTO;
import com.ecommerce.buymore.model.product.dto.ProductDeleteDTO;
import com.ecommerce.buymore.model.product.dto.ProductUpdateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * Utility class used to validate fields marked with @RequiredField annotation
 * If Field is marked as required and field is null, then product is not valid and exception is thrown
 */
public class ProductValidator {

    /**
     * Validates if all required product fields were provided
     *
     * @param product Product create
     * @throws BuyMoreException Exception is thrown when mandatory fields are not provided
     */
    public static void isValid(ProductCreateDTO product) throws BuyMoreException {
        validate(product);
        if (product.getProfitMargin() > 100.00) {
            throw new BadRequestErrorException(
                "Profit margin can't exceed 100.00. Provided profit margin: [{0}]", product.getProfitMargin()
            );
        }
    }

    /**
     * Validates if all required product fields were provided
     *
     * @param product Product delete
     * @throws BuyMoreException Exception is thrown when mandatory fields are not provided
     */
    public static void isValid(ProductDeleteDTO product) throws BuyMoreException {
        validate(product);
    }

    /**
     * Validates if all required product fields were provided
     *
     * @param product Product update
     * @throws BuyMoreException Exception is thrown when mandatory fields are not provided
     */
    public static void isValid(ProductUpdateDTO product) throws BuyMoreException {
        validate(product);
    }

    /**
     * If fields are null or if instance of String and blank, exception is raised informing invalid fields
     * @param object Object of product create/delete/update
     * @throws BuyMoreException Exception when required fields are null or blank
     */
    private static void validate(Object object) throws BuyMoreException {
        List<String> invalidFields = Arrays.stream(object.getClass()
                        .getDeclaredFields())
                .filter(field -> {
                    return field.isAnnotationPresent(Schema.class)
                    && Schema.RequiredMode.REQUIRED.equals(field.getDeclaredAnnotation(Schema.class).requiredMode());
                }).filter(field -> {
                    field.setAccessible(true);
                    try {
                        return field.get(object) == null
                        || field.get(object) instanceof String && StringUtils.isBlank((String) field.get(object));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(Field::getName).toList();

        if (!invalidFields.isEmpty()) {
            throw new BadRequestErrorException(
                "One or more required fields are missing: [{0}]",
                StringUtils.join(invalidFields, ", ")
            );
        }
    }

}
