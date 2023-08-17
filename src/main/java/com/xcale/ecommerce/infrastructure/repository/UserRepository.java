package com.xcale.ecommerce.infrastructure.repository;

import com.xcale.ecommerce.domain.port.UserPersistencePort;
import com.xcale.ecommerce.infrastructure.database.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
@Slf4j
public class UserRepository implements UserPersistencePort {
    private final UserJpaRepository userJpaRepository;
    @Override
    public UserEntity save(UserEntity user) {
        return userJpaRepository.save(user);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return Optional.ofNullable(userJpaRepository.findByEmail(email));
    }
}
