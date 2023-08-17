package com.xcale.ecommerce.application;


import com.xcale.ecommerce.infrastructure.database.entity.UserEntity;

public interface UserUseCase {
    UserEntity save(UserEntity user);
    UserEntity findByEmail(String email);
}
