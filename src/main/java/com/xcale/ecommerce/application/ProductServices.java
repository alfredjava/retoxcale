package com.xcale.ecommerce.application;

import com.xcale.ecommerce.domain.Product;
import com.xcale.ecommerce.domain.port.ProductPersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class ProductServices implements ProductUseCase{

    private final ProductPersistencePort productPersistencePort;
    @Override
    public Product createProduct(Product product) {
        return productPersistencePort.createProduct(product);
    }

    @Override
    public Product getProductById(String id) {
        return productPersistencePort.findById(id);
    }

    @Override
    public Product updateProduct(Product product) {
        return productPersistencePort.update(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productPersistencePort.findAll();
    }


}
