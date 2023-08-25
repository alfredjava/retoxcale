package com.xcale.ecommerce.infrastructure.config;

import com.xcale.ecommerce.application.CartServices;
import com.xcale.ecommerce.application.CartUseCase;
import com.xcale.ecommerce.application.ProductServices;
import com.xcale.ecommerce.application.ProductUseCase;
import com.xcale.ecommerce.domain.port.CartPersistencePort;
import com.xcale.ecommerce.domain.port.ProductPersistencePort;
import com.xcale.ecommerce.infrastructure.database.entity.mapper.ProductEntityMapper;
import com.xcale.ecommerce.infrastructure.database.entity.mapper.UserEntityMapper;
import com.xcale.ecommerce.infrastructure.rest.mapper.CartMapper;
import com.xcale.ecommerce.infrastructure.rest.mapper.ProductMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public ProductUseCase productUseCase(ProductPersistencePort productPersistencePort) {
        return new ProductServices(productPersistencePort);
    }

    @Bean
    public ProductEntityMapper productEntityMapper() {
        return ProductEntityMapper.INSTANCE;
    }

    @Bean
    public ProductMapper productMapper() {
        return ProductMapper.INSTANCE;
    }

    @Bean
    public UserEntityMapper userEntityMapper() {
        return UserEntityMapper.INSTANCE;
    }

    @Bean
    public CartUseCase cartUseCase(CartPersistencePort cartPersistencePort){
        return new CartServices(cartPersistencePort);
    }

    @Bean
    public CartMapper cartMapper(){
        return CartMapper.INSTANCE;
    }

}
