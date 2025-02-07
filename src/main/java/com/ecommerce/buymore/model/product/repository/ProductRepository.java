package com.ecommerce.buymore.model.product.repository;

import com.ecommerce.buymore.common.exception.AlreadyReportedException;
import com.ecommerce.buymore.common.exception.BuyMoreException;
import com.ecommerce.buymore.common.exception.InternalServerErrorException;
import com.ecommerce.buymore.common.exception.NotFoundErrorException;
import com.ecommerce.buymore.model.product.dto.ProductCreateDTO;
import com.ecommerce.buymore.model.product.dto.ProductDeleteDTO;
import com.ecommerce.buymore.model.product.dto.ProductDetailDTO;
import com.ecommerce.buymore.model.product.dto.ProductUpdateDTO;
import com.ecommerce.buymore.model.product.entity.ProductEntity;
import com.ecommerce.buymore.model.product.mapper.ProductCreateDTOToProductEntityMapper;
import com.ecommerce.buymore.model.product.mapper.ProductEntityToProductDetailDTOMapper;
import com.ecommerce.buymore.model.product.mapper.ProductEntityToProductUpdateDTOMapper;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.ecommerce.buymore.common.Common.get;

@Repository
public class ProductRepository {

    private final EntityManager entityManager;
    private final ProductCreateDTOToProductEntityMapper productCreateMapper;
    private final ProductEntityToProductDetailDTOMapper productEntityToProductDetailMapper;
    private final ProductEntityToProductUpdateDTOMapper productEntityToProductUpdateMapper;

    public ProductRepository(
        EntityManager entityManager,
        ProductCreateDTOToProductEntityMapper productCreateMapper,
        ProductEntityToProductDetailDTOMapper productEntityToProductDetailMapper,
        ProductEntityToProductUpdateDTOMapper productEntityToProductUpdateMapper
    ) {
        this.entityManager = entityManager;
        this.productCreateMapper = productCreateMapper;
        this.productEntityToProductDetailMapper = productEntityToProductDetailMapper;
        this.productEntityToProductUpdateMapper = productEntityToProductUpdateMapper;
    }

    @Transactional(rollbackFor = BuyMoreException.class)
    public Long createProduct(ProductCreateDTO product) throws BuyMoreException {
        try {
            List<ProductDetailDTO> products = findProductsByName(product.getName(), false);
            if (!products.isEmpty()) {
                throw new AlreadyReportedException(
                    "Product with name: [{0}] already saved with id: [{1}]. Product name is unique",
                    product.getName(),
                    products.get(0).getId()
                );
            }

            ProductEntity entity = productCreateMapper.map(product);
            this.entityManager.persist(entity);
            return entity.getId();
        } catch (EntityExistsException e) {
            throw new InternalServerErrorException("Failed to create new product with error message: {0}", e.getMessage());
        }
    }

    @Transactional(readOnly = true, rollbackFor = BuyMoreException.class)
    public Long countAllProducts() throws BuyMoreException {
        try {
            String query = "SELECT count(id) FROM product WHERE active = :active";
            Query nativeQuery = this.entityManager.createNativeQuery(query, Long.class);
            nativeQuery.setParameter("active", true);
            return (Long) nativeQuery.getSingleResult();
        } catch (NoResultException e) {
            throw new InternalServerErrorException("No products found", e.getMessage());
        }
    }

    @Transactional(readOnly = true, rollbackFor = BuyMoreException.class)
    public List<ProductDetailDTO> findAllProducts() throws BuyMoreException {
        try {
            String jpql = "FROM ProductEntity WHERE active = :active";
            TypedQuery<ProductEntity> query = this.entityManager.createQuery(jpql, ProductEntity.class);
            query.setParameter("active", true);
            List<ProductEntity> resultList = query.getResultList();
            return resultList.stream().map(productEntityToProductDetailMapper::map).collect(Collectors.toList());
        } catch (NoResultException e) {
            throw new NotFoundErrorException("No product found", e.getMessage());
        }
    }

    @Transactional(readOnly = true, rollbackFor = BuyMoreException.class)
    public ProductDetailDTO findProductById(Long productId) throws BuyMoreException {
        try {
            String jpql = "FROM ProductEntity WHERE id = :id AND active = :active";
            TypedQuery<ProductEntity> query = this.entityManager.createQuery(jpql, ProductEntity.class);
            query.setParameter("id", productId);
            query.setParameter("active", true);
            return productEntityToProductDetailMapper.map(query.getSingleResult());
        } catch (NoResultException | NonUniqueResultException e) {
            throw new NotFoundErrorException("No product found for id: {0}", productId);
        }
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true, rollbackFor = BuyMoreException.class)
    public List<ProductDetailDTO> findProductsByName(String productName, boolean isActive) throws BuyMoreException {
        try {
            String sqlQuery = null;
            if (isActive) {
                sqlQuery = "SELECT * FROM product WHERE name LIKE :name AND active = :active";
            } else {
                sqlQuery = "SELECT * FROM product WHERE name LIKE :name";
            }

            Query nativeQuery = this.entityManager.createNativeQuery(sqlQuery, ProductEntity.class);
            nativeQuery.setParameter("name", "%" + productName + "%");
            if (isActive) {
                nativeQuery.setParameter("active", true);
            }
            return (List<ProductDetailDTO>) nativeQuery.getResultList()
                    .stream()
                    .map(entity -> productEntityToProductDetailMapper.map((ProductEntity) entity))
                    .collect(Collectors.toList());
        } catch (NoResultException e) {
            throw new NotFoundErrorException("No products found for name: {0}", productName);
        }
    }

    @Transactional(rollbackFor = BuyMoreException.class)
    public ProductUpdateDTO updateProduct(ProductUpdateDTO product) throws BuyMoreException {
        ProductEntity entity = this.entityManager.find(ProductEntity.class, product.getId());
        if (entity == null) {
            throw new NotFoundErrorException("No product found with id: {0}", product.getId());
        }

        get(product.getName()).ifPresent(entity::setName);
        get(product.getCategory()).ifPresent(entity::setCategory);
        get(product.getStartingPrice()).ifPresent(entity::setStartingPrice);
        get(product.getDiscount()).ifPresent(entity::setDiscount);
        get(product.getDescription()).ifPresent(entity::setDescription);
        get(product.getProfitMargin()).ifPresent(entity::setProfitMargin);
        this.entityManager.flush();
        return productEntityToProductUpdateMapper.map(entity);
    }

    /**
     * Logical deletion marking product.active = false
     *
     * @param deleteProduct Object with id and comments for logical deletion
     */
    @Transactional(rollbackFor = BuyMoreException.class)
    public void deleteProduct(ProductDeleteDTO deleteProduct) {
        ProductEntity productEntity = this.entityManager.find(ProductEntity.class, deleteProduct.getProductId());
        productEntity.setActive(false);
        productEntity.setComments(deleteProduct.getComment());
        this.entityManager.flush();
    }

}
