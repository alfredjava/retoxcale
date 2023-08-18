package com.xcale.ecommerce.infrastructure.rest.mapper;


import com.xcale.ecommerce.domain.Product;
import com.xcale.ecommerce.infrastructure.rest.dto.ProductDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(nullValueCheckStrategy = org.mapstruct.NullValueCheckStrategy.ALWAYS,builder = @Builder(disableBuilder = true))
public interface ProductMapper {
    ProductMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(ProductMapper.class);

    @Mapping(target ="price",source = "price")
    ProductDto toDto(Product product);
}
