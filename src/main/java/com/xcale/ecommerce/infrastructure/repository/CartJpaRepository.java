package com.xcale.ecommerce.infrastructure.repository;

import com.xcale.ecommerce.infrastructure.database.entity.CartEntity;
import com.xcale.ecommerce.infrastructure.database.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CartJpaRepository extends JpaRepository<CartEntity,Long> {
}
