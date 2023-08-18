package com.xcale.ecommerce.infrastructure.repository;

import com.xcale.ecommerce.infrastructure.database.entity.CartDetailsEntity;
import com.xcale.ecommerce.infrastructure.database.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CartDetailsJpaRepository extends JpaRepository<CartDetailsEntity,Long> {
}
