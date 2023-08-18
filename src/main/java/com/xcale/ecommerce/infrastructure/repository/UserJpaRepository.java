package com.xcale.ecommerce.infrastructure.repository;

import com.xcale.ecommerce.infrastructure.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByEmail (String email);
}
