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
public class ProductCreateDTO implements Serializable {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    private String category;

    @Setter
    private Long id;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal startingPrice;

    private BigDecimal discount;

    @Setter
    private BigDecimal finalPrice;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private double profitMargin;

    @Setter
    private boolean active;

    private String comments;

    private LocalDateTime createdAt;

    private LocalDateTime lastUpdatedAt;
}
