package com.github.gpor0.order.rest.api;

import com.github.gpor0.beer.rest.api.model.Beer;
import com.github.gpor0.order.rest.api.model.Product;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductMapper {


    public Product map(Beer beer) {
        if (null == beer) {
            return null;
        }

        Product product = new Product();
        product.setCompany(beer.getCompany());
        product.setName(beer.getName());
        product.setPrice(beer.getPrice());

        return product;
    }
}
