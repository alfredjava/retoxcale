package com.xcale.ecommerce.infrastructure.database.entity.mapper;

import com.xcale.ecommerce.domain.Cart;
import com.xcale.ecommerce.domain.CartDetails;
import com.xcale.ecommerce.domain.Product;
import com.xcale.ecommerce.domain.User;
import com.xcale.ecommerce.infrastructure.database.entity.CartDetailsEntity;
import com.xcale.ecommerce.infrastructure.database.entity.CartEntity;
import com.xcale.ecommerce.infrastructure.database.entity.ProductEntity;
import com.xcale.ecommerce.infrastructure.database.entity.UserEntity;
import com.xcale.ecommerce.infrastructure.repository.ProductRepository;
import com.xcale.ecommerce.infrastructure.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @Autowired
    private ProductRepository productRepository ;

    @Autowired
    private UserRepository userRepository ;

    @Autowired
    private UserEntityMapper userEntityMapper;

    @Autowired
    private ProductEntityMapper productEntityMapper;


    @Mapping(target ="createdAt",expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target ="updatedAt",expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "total", source = "cartDetails",qualifiedByName = "setTotal")
    @Mapping(target = "userEntity", source = "user",qualifiedByName = "setUser")
    //@Mapping(target = "cartDetailsEntities", source = "cartDetails",qualifiedByName = "mapDetailsCart")
    public abstract CartEntity toEntity(Cart cart);


    @Named("setUser")
    public UserEntity setUser(User user){
        return  userEntityMapper.toEntity(userRepository.findByEmail(user.getEmail()));
    }

    @Named("mapDetailsCart")
    public List<CartDetailsEntity> setDetails(List<CartDetails> cartDetails){
        if (cartDetails == null){
            return Collections.emptyList();
        }
        return cartDetails.stream().map(this::cartLineToItem).collect(Collectors.toList());

    }

    @Mapping(target = "product", source = "product", qualifiedByName = "setProduct")
    @Mapping(target ="createdAt",expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target ="updatedAt",expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "total", expression = "java(cartDetails.getPrice()*cartDetails.getQuantity())")
    public abstract CartDetailsEntity cartLineToItem(CartDetails cartDetails) ;

    @Named("setProduct")
    public ProductEntity setProduct(String product){
        return  productEntityMapper.toEntity(productRepository.findByName(product));
    }
    @Named("setTotal")
    public Double setTotal(List<CartDetails> cartDetails){

        return cartDetails.stream().mapToDouble(
                detalle ->{
                    Product product = productRepository.findByName(detalle.getProduct());
                    detalle.setPrice(product.getPrice());
                    detalle.setTotal(product.getPrice() * detalle.getQuantity());
                    return detalle.getTotal();
                }
        ).sum();

    }


    @Mapping(target = "user.id", source = "userEntity.id")
    @Mapping(target = "user.email", source = "userEntity.email")
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
    @Mapping(target = "product", source = "product.name")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "total", source = "total")
    public abstract CartDetails cartLineToItemDomain(CartDetailsEntity cartDetailsEntity);

}
