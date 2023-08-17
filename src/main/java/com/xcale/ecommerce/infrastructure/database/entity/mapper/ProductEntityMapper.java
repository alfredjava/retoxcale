package com.xcale.ecommerce.infrastructure.database.dto.mapper;

import com.xcale.ecommerce.domain.Product;
import com.xcale.ecommerce.infrastructure.database.entity.ProductEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(nullValueCheckStrategy = org.mapstruct.NullValueCheckStrategy.ALWAYS,builder = @Builder(disableBuilder = true))
public interface ProductEntityMapper {

    ProductEntityMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(ProductEntityMapper.class);
    @Mapping(target ="countInStock",source = "stock")
    @Mapping(target ="createdAt",expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target ="updatedAt",expression = "java(java.time.LocalDateTime.now())")
    ProductEntity toEntity(Product product);

    Product toDomain(ProductEntity productDto);
}
