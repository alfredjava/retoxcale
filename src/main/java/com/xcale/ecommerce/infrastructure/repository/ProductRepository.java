package com.xcale.ecommerce.infrastructure.repository;

import com.xcale.ecommerce.domain.Product;
import com.xcale.ecommerce.domain.port.ProductPersistencePort;
import com.xcale.ecommerce.infrastructure.MyException;
import com.xcale.ecommerce.infrastructure.database.dto.mapper.ProductEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Slf4j
public class ProductRepository implements ProductPersistencePort {

    private final ProductJpaRepository productJpaRepository;
    private final ProductEntityMapper productMapper;
    @Override
    public Product findById(String id) {
        return null;
    }

    @Override
    public Product update(Product product) {
        return null;
    }



    @Override
    public Product createProduct(Product product) {
        try {
            return productMapper.toDomain(productJpaRepository.save(productMapper.toEntity(product)));
        }catch (DataIntegrityViolationException data){
            log.error("Error creating product: product with name {}  exist", product.getName());
            throw new MyException("Error creating product: product with name "+ product.getName() +" exist", product.toString());
        }
        catch (Exception e){
            log.error("Error creating product: {}", e.getMessage());
            throw new MyException("Error creating product: {}", product.toString());
        }

    }

    @Override
    public List<Product> findAll() {
        return productJpaRepository.findAll()
                .stream().map(productMapper::toDomain).collect(Collectors.toList());
    }


}
