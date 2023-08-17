package com.xcale.ecommerce.domain.port;

import com.xcale.ecommerce.domain.Product;

import java.util.List;

public interface ProductPersistencePort {
    Product createProduct(Product product);

    Product findById(String id);

    Product update(Product product);

    List<Product> findAll();
}
