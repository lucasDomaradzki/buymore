package com.ecommerce.buymore.model.product.mapper;

public abstract class AbstractMapper <I, O> {

    public abstract O map(I input);

}
