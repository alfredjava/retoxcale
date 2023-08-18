package com.xcale.ecommerce.infrastructure.repository;

import com.xcale.ecommerce.domain.User;
import com.xcale.ecommerce.infrastructure.database.entity.mapper.UserEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class UserRepository implements com.xcale.ecommerce.domain.port.UserPersistencePort{
    private final UserJpaRepository userJpaRepository;
    private final UserEntityMapper userEntityMapper;
    @Override
    public User createUser(User user) {
        return userEntityMapper.toDomain(userJpaRepository.save(userEntityMapper.toEntity(user)));
    }

    @Override
    public User findByEmail(String email) {
        return userEntityMapper.toDomain(userJpaRepository.findByEmail(email));
    }
}
