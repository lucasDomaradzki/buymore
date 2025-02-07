package com.ecommerce.buymore.model.product.mapper;

import com.ecommerce.buymore.model.product.dto.ProductUpdateDTO;
import com.ecommerce.buymore.model.product.entity.ProductEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductEntityToProductUpdateDTOMapper extends AbstractMapper<ProductEntity, ProductUpdateDTO> {

    @Override
    public ProductUpdateDTO map(ProductEntity entity) {
        return ProductUpdateDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .category(entity.getCategory())
                .description(entity.getDescription())
                .createdAt(entity.getCreationDate())
                .lastUpdatedAt(entity.getLastUpdateDate())
                .comments(entity.getComments())
                .startingPrice(entity.getStartingPrice())
                .discount(entity.getDiscount())
                .profitMargin(entity.getProfitMargin())
                .finalPrice(entity.getFinalPrice())
                .build();
    }

}
