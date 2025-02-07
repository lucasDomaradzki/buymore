package com.ecommerce.buymore.model.product.entity;

import com.ecommerce.buymore.model.product.service.ProductFinalPriceUtil;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "product")
public class ProductEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @Column(name = "starting_price", nullable = false)
    private BigDecimal startingPrice;

    @Column(name = "discount")
    private BigDecimal discount;

    @Column(name = "profit_margin", nullable = false)
    private double profitMargin;

    @Column(name = "description", nullable = false, length = 750)
    private String description;

    @Column(name = "category", length = 60)
    private String category;

    @Column(name = "active")
    private boolean active;

    @Column(name = "comments", length = 120)
    private String comments;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime lastUpdateDate;

    @PrePersist
    private void beforePersist() {
        if (this.creationDate == null) {
            this.creationDate = LocalDateTime.now();
        }

        this.lastUpdateDate = LocalDateTime.now();
    }

    @PreUpdate
    private void beforeUpdate() {
        this.lastUpdateDate = LocalDateTime.now();
    }

    public BigDecimal getFinalPrice() {
        return ProductFinalPriceUtil.getFinalPrice(startingPrice, profitMargin, discount);
    }

}
