package com.xcale.ecommerce.infrastructure.repository;

import com.xcale.ecommerce.domain.Cart;
import com.xcale.ecommerce.domain.port.CartPersistencePort;
import com.xcale.ecommerce.infrastructure.MyException;
import com.xcale.ecommerce.infrastructure.database.entity.CartEntity;
import com.xcale.ecommerce.infrastructure.database.entity.mapper.CartEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;


@RequiredArgsConstructor
@Component
@Slf4j
@Transactional(readOnly = true)
public class CartRepository implements CartPersistencePort {

    private final CartJpaRepository cartJpaRepository;
    private final CartDetailsJpaRepository cartDetailsJpaRepository;
    private final CartEntityMapper cartEntityMapper;


    @Override
    @Transactional(readOnly = false)
    public Cart saveCart(Cart cart) {
        try {

            CartEntity cartEntity = cartJpaRepository.save(cartEntityMapper.toEntity(cart));

            /*cartEntityMapper.setDetails(cart.getCartDetails()).stream().forEach(
                    cartEntityd -> {
                        cartEntityd.setCart(cartEntity);
                        cartDetailsJpaRepository.save(cartEntityd) ;
                    }

            );

             */



            cartDetailsJpaRepository.saveAll(cartEntityMapper.setDetails(cart.getCartDetails()).stream()
                    .map(cartDetailsEntity -> {cartDetailsEntity.setCart(cartEntity);
                        return cartDetailsEntity;
                    }).collect(Collectors.toList()));



            return cartEntityMapper.toDomain(cartEntity);
        }catch (Exception e){
            log.error("Error creating car: {}", e.getMessage());
            throw new MyException("Error creating cart: {}", cart.toString());
        }
    }
}
