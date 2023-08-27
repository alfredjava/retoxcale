package com.xcale.ecommerce.application;

import com.xcale.ecommerce.domain.Product;

import java.util.List;


public interface ProductUseCase {
    Product createProduct(Product product);
    Product getProductById(Long id);
    Product updateProduct(Product product);

    List<Product> getAllProducts();
}
