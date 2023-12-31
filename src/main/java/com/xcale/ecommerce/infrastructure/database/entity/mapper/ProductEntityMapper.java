package com.xcale.ecommerce.infrastructure.database.entity.mapper;

import com.xcale.ecommerce.domain.Product;
import com.xcale.ecommerce.infrastructure.database.entity.ProductEntity;
import org.mapstruct.*;


@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,builder = @Builder(disableBuilder = true))
public interface ProductEntityMapper {

    ProductEntityMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(ProductEntityMapper.class);
    @Mapping(target ="countInStock",source = "stock")
    @Mapping(target ="createdAt",expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target ="updatedAt",expression = "java(java.time.LocalDateTime.now())")
    ProductEntity toEntity(Product product);

    @Mapping(target ="stock",source = "countInStock")
    @Mapping(target ="price",source = "price")
    Product toDomain(ProductEntity productDto);
}
