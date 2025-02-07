package com.ecommerce.buymore.model.product.mapper;

import com.ecommerce.buymore.model.product.dto.ProductCreateDTO;
import com.ecommerce.buymore.model.product.entity.ProductEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductCreateDTOToProductEntityMapper extends AbstractMapper<ProductCreateDTO, ProductEntity> {

    @Override
    public ProductEntity map(ProductCreateDTO product) {
        ProductEntity entity = new ProductEntity();
        entity.setCategory(product.getCategory());
        entity.setDescription(product.getDescription());
        entity.setName(product.getName());
        entity.setDiscount(product.getDiscount());
        entity.setProfitMargin(product.getProfitMargin());
        entity.setStartingPrice(product.getStartingPrice());
        entity.setActive(true);
        return entity;
    }

}
