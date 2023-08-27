package com.xcale.ecommerce.infrastructure.rest.mapper;

import com.xcale.ecommerce.domain.Cart;
import com.xcale.ecommerce.domain.CartDetails;
import com.xcale.ecommerce.domain.User;
import com.xcale.ecommerce.infrastructure.rest.dto.CartDetailsDto;
import com.xcale.ecommerce.infrastructure.rest.dto.CartDto;
import com.xcale.ecommerce.infrastructure.rest.dto.UserDto;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

    @Mapper(componentModel = "spring")
    public interface CartMapper {

        CartMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(CartMapper.class);

        @Mapping(target = "cartDetailsDto", source = "cartDetails")
        @Mapping(target = "userDto.id", source = "idUser")
        CartDto toDto(Cart cart);




        @Mapping(target = "email", source = "email")
        @Mapping(target = "id", source = "id")
        UserDto toDto(User user);

        List<CartDetailsDto> toDtoList(List<CartDetails> cartDetailsList);

        List<CartDto> toDtoListCart(List<Cart> cartList);
        @Mapping(target = "product", source = "idProduct")
        @Mapping(target = "price", source = "price")
        @Mapping(target = "quantity", source = "quantity")
        @Mapping(target = "total", source = "total")
        CartDetailsDto toDto(CartDetails cartDetails);

        UserDto map(User value);
    }

