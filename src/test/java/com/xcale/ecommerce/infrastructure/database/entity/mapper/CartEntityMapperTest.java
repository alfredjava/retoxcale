package com.xcale.ecommerce.infrastructure.database.entity.mapper;

import com.xcale.ecommerce.domain.Cart;
import com.xcale.ecommerce.domain.CartDetails;
import com.xcale.ecommerce.infrastructure.database.entity.CartDetailsEntity;
import com.xcale.ecommerce.infrastructure.database.entity.CartEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CartEntityMapperTest {


    CartEntityMapper cartEntityMapper = CartEntityMapper.INSTANCE;

    @Test
    void testToEntity() {
        // Given
        CartDetails cartItem = new CartDetails(1L, 10.2,2,100.3);
        Cart cart =  Cart.builder().id(1L).idUser(1L).createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now()).cartDetails(Collections.singletonList(cartItem)).build();



        // When
        CartEntity cartEntity = cartEntityMapper.toEntity(cart);



        // Then
        assertNotNull(cartEntity);
        assertEquals(1L, cartEntity.getUserEntity().getId());

    }

    @Test
    void testSetDetails() {
        // Given
        CartDetails cartDetails = CartDetails.builder().total(10.0).price(100.0).quantity(1).idProduct(1L).build();
        List<CartDetails> listCartDetails = new ArrayList<>();
        listCartDetails.add(cartDetails);
        cartDetails = CartDetails.builder().total(20.0).price(100.0).quantity(2).idProduct(2L).build();
        listCartDetails.add(cartDetails);
        // When
        List<CartDetailsEntity>  listDetailEntity = cartEntityMapper.setDetails(listCartDetails);

        // Then
        assertNotNull(listDetailEntity);
        assertEquals(100.0,listDetailEntity.get(0).getTotal());
        assertEquals(200.0,listDetailEntity.get(1).getTotal());
    }

}