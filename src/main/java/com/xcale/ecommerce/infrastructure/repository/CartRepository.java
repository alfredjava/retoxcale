package com.xcale.ecommerce.infrastructure.repository;

import com.xcale.ecommerce.domain.Cart;
import com.xcale.ecommerce.domain.User;
import com.xcale.ecommerce.domain.port.CartPersistencePort;
import com.xcale.ecommerce.infrastructure.MyException;
import com.xcale.ecommerce.infrastructure.database.entity.CartEntity;
import com.xcale.ecommerce.infrastructure.database.entity.UserEntity;
import com.xcale.ecommerce.infrastructure.database.entity.mapper.CartEntityMapper;
import com.xcale.ecommerce.infrastructure.database.entity.mapper.UserEntityMapper;
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
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;


    @Override
    @Transactional(readOnly = false)
    public Cart saveCart(Cart cart) {
        try {

            User user = userRepository.findByEmail(cart.getUser().getEmail());

            CartEntity cartEntity = cartJpaRepository.findCartEntityByUserEntity(userEntityMapper.toEntity(user));

            if (cartEntity != null){
                //elimino el carrito
                cartJpaRepository.delete(cartEntity);
            }
            cartEntity = cartJpaRepository.save(cartEntityMapper.toEntity(cart));

            CartEntity finalCartEntity = cartEntity;
            cartDetailsJpaRepository.saveAll(cartEntityMapper.setDetails(cart.getCartDetails()).stream()
                    .map(cartDetailsEntity -> {
                        cartDetailsEntity.setCart(finalCartEntity);
                        //update stock.
                        productRepository.updateStock(cartDetailsEntity.getProduct().getId(),
                                cartDetailsEntity.getQuantity());

                        return cartDetailsEntity;
                    }).collect(Collectors.toList()));

            return cartEntityMapper.toDomain(cartEntity);
        }catch (Exception e){
            log.error("Error creating car: {}", e.getMessage());
            throw new MyException("Error creating cart: {}", cart.toString());
        }
    }

    @Override
    public Cart getCartById(Long id) {
        return cartEntityMapper.toDomain(cartJpaRepository.findById(id).orElse(null));
    }
}
