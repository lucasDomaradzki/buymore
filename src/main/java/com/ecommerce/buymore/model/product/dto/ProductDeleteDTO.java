package com.ecommerce.buymore.model.product.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDeleteDTO implements Serializable {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private Long productId;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String comment;

}
