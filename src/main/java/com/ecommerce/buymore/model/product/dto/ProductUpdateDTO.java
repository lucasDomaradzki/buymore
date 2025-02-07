package com.ecommerce.buymore.model.product.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductUpdateDTO implements Serializable {

    private String name;

    private String category;

    @Setter
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    private BigDecimal startingPrice;

    private BigDecimal discount;

    @Setter
    private BigDecimal finalPrice;

    private String description;

    private double profitMargin;

    private String comments;

    private LocalDateTime createdAt;

    private LocalDateTime lastUpdatedAt;

}
