package com.ecommerce.buymore.model.product.service;

import com.ecommerce.buymore.common.exception.BuyMoreException;
import com.ecommerce.buymore.model.product.dto.ProductCreateDTO;
import com.ecommerce.buymore.model.product.dto.ProductDeleteDTO;
import com.ecommerce.buymore.model.product.dto.ProductDetailDTO;
import com.ecommerce.buymore.model.product.dto.ProductUpdateDTO;
import com.ecommerce.buymore.model.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductCreateDTO createProduct(ProductCreateDTO product) throws BuyMoreException {
        ProductValidator.isValid(product);
        Long id = productRepository.createProduct(product);
        product.setId(id);
        product.setActive(true);
        return product;
    }

    public Optional<Long> countAllProducts() throws BuyMoreException {
        return Optional.ofNullable(productRepository.countAllProducts());
    }

    public List<ProductDetailDTO> findAllProducts() throws BuyMoreException {
        return productRepository.findAllProducts();
    }

    public ProductDetailDTO findProductById(Long productId) throws BuyMoreException {
        return productRepository.findProductById(productId);
    }

    public List<ProductDetailDTO> findProductsByName(String productName) throws BuyMoreException {
        return this.productRepository.findProductsByName(productName, true);
    }

    public ProductUpdateDTO patchProduct(ProductUpdateDTO product) throws BuyMoreException{
        return this.productRepository.updateProduct(product);
    }

    public ProductUpdateDTO updateProduct(ProductUpdateDTO product) throws BuyMoreException {
        return this.productRepository.updateProduct(product);
    }

    public void deleteProduct(ProductDeleteDTO deleteProduct) throws BuyMoreException {
        ProductValidator.isValid(deleteProduct);
        this.productRepository.deleteProduct(deleteProduct);
    }

}
