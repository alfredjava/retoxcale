package com.xcale.ecommerce.infrastructure.repository;

import com.xcale.ecommerce.infrastructure.database.entity.CartEntity;
import com.xcale.ecommerce.infrastructure.database.entity.ProductEntity;
import com.xcale.ecommerce.infrastructure.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CartJpaRepository extends JpaRepository<CartEntity,Long> {
    CartEntity findCartEntityByUserEntity(UserEntity userEntity);
    List<CartEntity> findAll();
}
