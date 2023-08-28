package com.xcale.ecommerce.infrastructure.database.entity.mapper;

import com.xcale.ecommerce.domain.User;
import com.xcale.ecommerce.infrastructure.database.entity.UserEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(nullValueCheckStrategy = org.mapstruct.NullValueCheckStrategy.ALWAYS,builder = @Builder(disableBuilder = true))
public interface UserEntityMapper {

    UserEntityMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(UserEntityMapper.class);
    @Mapping(target = "role",source = "role")
    @Mapping(target = "username",source = "userName")
    UserEntity toEntity(User user);
    @Mapping(target = "password", ignore = true)
    User toDomain(UserEntity save);
}
