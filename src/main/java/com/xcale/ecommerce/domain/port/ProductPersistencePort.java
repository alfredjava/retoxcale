package com.xcale.ecommerce.domain.port;

import com.xcale.ecommerce.domain.Product;

import java.util.List;

public interface ProductPersistencePort {
    Product createProduct(Product product);

    Object saveAll(List<Product> products);

    Product findById(String id);

    Product findByName(String name);

    Product update(Product product);

    List<Product> findAll();

    void updateStock(Long id, Integer quantity);
}
