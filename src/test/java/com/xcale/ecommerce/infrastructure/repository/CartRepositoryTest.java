package com.xcale.ecommerce.infrastructure.repository;

import com.xcale.ecommerce.domain.Cart;
import com.xcale.ecommerce.domain.CartDetails;
import com.xcale.ecommerce.domain.Product;
import com.xcale.ecommerce.infrastructure.database.entity.CartDetailsEntity;
import com.xcale.ecommerce.infrastructure.database.entity.CartEntity;
import com.xcale.ecommerce.infrastructure.database.entity.ProductEntity;
import com.xcale.ecommerce.infrastructure.database.entity.UserEntity;
import com.xcale.ecommerce.infrastructure.database.entity.mapper.CartEntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartRepositoryTest {

    @Mock
    private  CartJpaRepository cartJpaRepository;
    @Mock
    private  CartDetailsJpaRepository cartDetailsJpaRepository;

    private CartEntityMapper cartEntityMapper = CartEntityMapper.INSTANCE;
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CartRepository cartRepository;
    private Cart cart;

    @BeforeEach
    public void setUp() {
        cartJpaRepository =   mock(CartJpaRepository.class);
        cartDetailsJpaRepository =   mock(CartDetailsJpaRepository.class);
        productRepository =   mock(ProductRepository.class);


         cartRepository = new CartRepository(cartJpaRepository,
                cartDetailsJpaRepository,
                cartEntityMapper,
                productRepository
        );
        cart = new Cart();
        cart.setId(1L);
    }


    @Test
    public void testSaveCart() {
        // Crea un objeto Cart para utilizar en la prueba
        List<CartDetails> cartDetailsList = new ArrayList<>();
        CartDetails cartDetails = CartDetails.builder().idProduct(1L).price(20.00).quantity(1).build();
        cartDetailsList.add(cartDetails);
        cart.setCartDetails(cartDetailsList);
        cart.setIdUser(1L);

        UserEntity userEntity = UserEntity.builder().id(1L).username("Alfredo").build();

        Authentication authentication = new UsernamePasswordAuthenticationToken(userEntity, "contraseña1");

        // Configura el objeto SecurityContextHolder para que devuelva el objeto Authentication mock cuando se llame al método getContext().getAuthentication()
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        Product product = Product.builder().stock(100).price(200.0).id(1L).build();

        when(productRepository.findById(anyLong())).thenReturn(product);

        CartEntity cartEntity = CartEntity.builder().userEntity(userEntity)
                .id(1L).total(20.0).build();


        List<CartDetailsEntity> listCartDet = new ArrayList<>();
        CartDetailsEntity cartDetailsEntity1 = new CartDetailsEntity();
        cartDetailsEntity1.setId(1L);
        cartDetailsEntity1.setProduct(ProductEntity.builder().id(1L).price(200.0).name("Iphone11").build());
        cartDetailsEntity1.setQuantity(1);
        cartDetailsEntity1.setPrice(cartDetailsEntity1.getProduct().getPrice());
        cartDetailsEntity1.setTotal(cartDetailsEntity1.getPrice()*cartDetailsEntity1.getQuantity());
        listCartDet.add(cartDetailsEntity1);

        when(cartDetailsJpaRepository.saveAll(anyList())).thenReturn(listCartDet);

        when(cartJpaRepository.save(any(CartEntity.class))).thenReturn(cartEntity);
        // Configura el comportamiento del mock CartRepository para que devuelva el objeto Cart cuando se llame al método save()
        Cart savedCart =  cartRepository.saveCart(cart);

        assertEquals(cart.getId(), savedCart.getId());
        assertEquals(cart.getIdUser(), savedCart.getIdUser());
        assertEquals(cart.getCartDetails().get(0).getTotal(), savedCart.getCartDetails().get(0).getTotal());


    }



    @Test
    public void testFindCartById() {

        CartEntity cartEntity = CartEntity.builder().id(1L).build();
        when(cartJpaRepository.findById(anyLong())).thenReturn(Optional.of(cartEntity));
        Cart foundCart = cartRepository.findById(cart.getId());
        assertEquals(foundCart.getId(),cart.getId());
    }
}