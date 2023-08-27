package com.xcale.ecommerce.infrastructure.repository;

import com.xcale.ecommerce.domain.Cart;
import com.xcale.ecommerce.domain.User;
import com.xcale.ecommerce.infrastructure.database.entity.UserEntity;
import com.xcale.ecommerce.infrastructure.database.entity.mapper.CartEntityMapper;
import com.xcale.ecommerce.infrastructure.database.entity.mapper.UserEntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartRepositoryTest {

    @Mock
    private  CartJpaRepository cartJpaRepository;
    @Mock
    private  CartDetailsJpaRepository cartDetailsJpaRepository;
    @Mock
    private CartEntityMapper cartEntityMapper;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserEntityMapper userEntityMapper;
    @InjectMocks
    private CartRepository cartRepository;
    private Cart cart;

    @BeforeEach
    public void setUp() {
        cartJpaRepository =   mock(CartJpaRepository.class);
        cartDetailsJpaRepository =   mock(CartDetailsJpaRepository.class);
        cartEntityMapper =   mock(CartEntityMapper.class);
        productRepository =   mock(ProductRepository.class);
        userEntityMapper =   mock(UserEntityMapper.class);


         cartRepository = new CartRepository(cartJpaRepository,
                cartDetailsJpaRepository,
                cartEntityMapper,
                productRepository,
                userRepository,
                userEntityMapper
        );
        cart = new Cart();
        cart.setId(1L);
    }

/*
    @Test
    public void testSaveCart() {

        User user = User.builder().id(1L).build();

        when(userEntityMapper.toDomain(any(UserEntity.class))).thenReturn(user);

        when(userRepository.findByEmail(anyString())).thenReturn(user);



        when(cartRepository.saveCart(cart)).thenReturn(cart);

        Cart savedCart = cartRepository.saveCart(cart);

        assertEquals(savedCart,cart);
    }

 */

    @Test
    public void testFindCartById() {
        when(cartRepository.findById(cart.getId())).thenReturn(cart);

        Cart foundCart = cartRepository.findById(cart.getId());

        assertEquals(foundCart,cart);
    }
}