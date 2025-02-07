package com.ecommerce.buymore.model.product.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDetailDTO implements Serializable {

    private String name;
    private String category;
    @Setter
    private Long id;
    @Setter
    private BigDecimal finalPrice;
    private String description;
    @Setter
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;

}
