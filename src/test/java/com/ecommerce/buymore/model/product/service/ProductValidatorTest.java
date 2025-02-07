package com.ecommerce.buymore.model.product.service;

import com.ecommerce.buymore.common.exception.BadRequestErrorException;
import com.ecommerce.buymore.model.product.dto.ProductCreateDTO;
import com.ecommerce.buymore.model.product.dto.ProductUpdateDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * Test if Product has the necessary fields for CREATE/UPDATE action
 * If product does not have all the required fields provided, BadRequestException (404) is returned
 * To see more information about which fields are required check: com.ecommerce.buymore.product.model.Product
 */
public class ProductValidatorTest {

    @Test
    public void testValidCreateProduct() {
        ProductCreateDTO product = ProductCreateDTO.builder()
                .name("Product name A")
                .startingPrice(new BigDecimal("15.75"))
                .description("Product description A")
                .profitMargin(45L).build();
        Assertions.assertDoesNotThrow(() -> ProductValidator.isValid(product));
    }

    @Test
    public void testInvalidCreateProduct() {
        ProductCreateDTO productA = ProductCreateDTO.builder()
                // .name("Product name A") // Name is missing
                .startingPrice(new BigDecimal("15.75"))
                .description("Product description A")
                .profitMargin(45L).build();
        Assertions.assertThrows(
            BadRequestErrorException.class,
            () -> ProductValidator.isValid(productA)
        );

        ProductCreateDTO productB = ProductCreateDTO.builder()
                .name("") // Name is blank
                .startingPrice(new BigDecimal("15.75"))
                .description("Product description A")
                .profitMargin(45L).build();
        Assertions.assertThrows(
                BadRequestErrorException.class,
                () -> ProductValidator.isValid(productB)
        );
    }

    @Test
    public void testValidUpdateProduct() {
        ProductUpdateDTO product = ProductUpdateDTO.builder().id(1L).build();
        Assertions.assertDoesNotThrow(() -> ProductValidator.isValid(product));
    }

    @Test
    public void testInvalidUpdateProduct() {
        // .id(1L) // Id is missing
        ProductCreateDTO product = ProductCreateDTO.builder().build();
        Assertions.assertThrows(
            BadRequestErrorException.class,
            () -> ProductValidator.isValid(product)
        );
    }

//    public void testValidDeleteProduct() {
//        DeleteProductDTO deleteProduct = DeleteProductDTO.
//    }

}