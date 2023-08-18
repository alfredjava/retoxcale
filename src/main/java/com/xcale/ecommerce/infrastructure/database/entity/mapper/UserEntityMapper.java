package com.xcale.ecommerce.infrastructure.database.entity.mapper;

import com.xcale.ecommerce.domain.User;
import com.xcale.ecommerce.infrastructure.database.entity.UserEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(nullValueCheckStrategy = org.mapstruct.NullValueCheckStrategy.ALWAYS,builder = @Builder(disableBuilder = true))
public interface UserEntityMapper {

        UserEntityMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(UserEntityMapper.class);

    UserEntity toEntity(User user);

    User toDomain(UserEntity save);
}
