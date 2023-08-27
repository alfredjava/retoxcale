package com.xcale.ecommerce.infrastructure.database.entity.mapper;

import com.xcale.ecommerce.domain.Cart;
import com.xcale.ecommerce.domain.CartDetails;
import com.xcale.ecommerce.infrastructure.database.entity.CartDetailsEntity;
import com.xcale.ecommerce.infrastructure.database.entity.CartEntity;
import com.xcale.ecommerce.infrastructure.database.entity.ProductEntity;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class CartEntityMapper {

    public static final CartEntityMapper INSTANCE = Mappers.getMapper(CartEntityMapper.class);

    @Mapping(target ="createdAt",expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target ="updatedAt",expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "userEntity.id", source = "idUser")
    public abstract CartEntity toEntity(Cart cart);

    @Named("mapDetailsCart")
    public List<CartDetailsEntity> setDetails(List<CartDetails> cartDetails){
        if (cartDetails == null){
            return Collections.emptyList();
        }
        return cartDetails.stream().map(this::cartLineToItem).collect(Collectors.toList());

    }

    @Mapping(target = "product", source = "idProduct", qualifiedByName = "setProduct")
    @Mapping(target ="createdAt",expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target ="updatedAt",expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "total", expression = "java(cartDetails.getPrice()*cartDetails.getQuantity())")
    public abstract CartDetailsEntity cartLineToItem(CartDetails cartDetails) ;

    @Named("setProduct")
    public ProductEntity setProduct(Long idProduct){
        return  ProductEntity.builder().id(idProduct).build();
    }


    @Mapping(target = "idUser", source = "userEntity.id")
    @Mapping(target = "cartDetails", source = "cartDetailsEntities",qualifiedByName = "mapDetailsCartDomain")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    @Mapping(target = "id", source = "id")
    public abstract Cart toDomain(CartEntity cartEntity);


    @Named("mapDetailsCartDomain")
    public List<CartDetails> mapDetailsCartDomain(List<CartDetailsEntity> cartDetailsEntities){
        if (cartDetailsEntities == null){
            return Collections.emptyList();
        }
        return cartDetailsEntities.stream().map(this::cartLineToItemDomain).collect(Collectors.toList());

    }
    @Mapping(target = "idProduct", source = "product.id")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "total", source = "total")
    public abstract CartDetails cartLineToItemDomain(CartDetailsEntity cartDetailsEntity);

}
