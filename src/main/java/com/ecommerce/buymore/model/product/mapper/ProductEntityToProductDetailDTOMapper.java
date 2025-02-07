package com.ecommerce.buymore.model.product.mapper;

import com.ecommerce.buymore.model.product.dto.ProductDetailDTO;
import com.ecommerce.buymore.model.product.entity.ProductEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductEntityToProductDetailDTOMapper extends AbstractMapper<ProductEntity, ProductDetailDTO> {

    @Override
    public ProductDetailDTO map(ProductEntity entity) {
        return ProductDetailDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .category(entity.getCategory())
                .description(entity.getDescription())
                .createdAt(entity.getCreationDate())
                .lastUpdatedAt(entity.getLastUpdateDate())
                .active(entity.isActive())
                .finalPrice(entity.getFinalPrice())
                .build();
    }

}
