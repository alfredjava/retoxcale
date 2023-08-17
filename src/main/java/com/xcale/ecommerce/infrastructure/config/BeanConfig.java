package com.xcale.ecommerce.infrastructure.config;

import com.xcale.ecommerce.application.ProductServices;
import com.xcale.ecommerce.application.ProductUseCase;
import com.xcale.ecommerce.application.UserServices;
import com.xcale.ecommerce.application.UserUseCase;
import com.xcale.ecommerce.domain.port.ProductPersistencePort;
import com.xcale.ecommerce.domain.port.UserPersistencePort;
import com.xcale.ecommerce.infrastructure.database.dto.mapper.ProductEntityMapper;
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
    public ProductEntityMapper productMapper() {
        return ProductEntityMapper.INSTANCE;
    }

    @Bean
    public ProductMapper productRestMapper() {
        return ProductMapper.INSTANCE;
    }

    @Bean
    public UserUseCase userUseCase(UserPersistencePort userPersistencePort) {
        return new UserServices(userPersistencePort);
    }



}
