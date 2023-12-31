package com.xcale.ecommerce.infrastructure.rest;

import com.xcale.ecommerce.application.CartUseCase;
import com.xcale.ecommerce.domain.Cart;
import com.xcale.ecommerce.infrastructure.MyException;
import com.xcale.ecommerce.infrastructure.database.entity.mapper.CartEntityMapper;
import com.xcale.ecommerce.infrastructure.rest.dto.CartDetailsDto;
import com.xcale.ecommerce.infrastructure.rest.dto.CartDto;
import com.xcale.ecommerce.infrastructure.rest.dto.CartRequestDto;
import com.xcale.ecommerce.infrastructure.rest.mapper.CartMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.xcale.ecommerce.infrastructure.rest.TestUtils.readDataFromFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartControllerTest {
    //@Mock
    //private CartMapper cartMapper;
    CartMapper cartMapper = CartMapper.INSTANCE;
    @BeforeEach
    public void init(){

        //cartMapper =   mock(CartMapper.class);
    }
    @Test
    void createCart() throws IOException {

        CartUseCase cartUseCase = mock(CartUseCase.class);
        CartController cartController = new CartController(cartUseCase,cartMapper);
        CartRequestDto cartRequestDto = readDataFromFile("request_cart.json", CartRequestDto.class); // Crear un objeto Cart para usar en las pruebas
        Cart cart = cartMapper.toDomain(cartRequestDto);
        cart.setId(1L);
        when(cartUseCase.addCart(any(Cart.class))).thenReturn(cart);


        CartDetailsDto cartDetailsDto = CartDetailsDto.builder().idProduct(1L).quantity(1).price(10.0).total(100.0)
                .build();
        List<CartDetailsDto> listCa = new ArrayList<>() ;
        listCa.add(cartDetailsDto);
        CartDto cartDto = CartDto.builder().id(1L).cartDetailsDto(listCa).
        build();
        // Act
        ResponseEntity<CartDto> response = cartController.createCart(cartRequestDto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(cartDto.getId(), response.getBody().getId());
        assertEquals(cartDto.getCartDetailsDto().get(0).getIdProduct(),
                response.getBody().getCartDetailsDto().get(0).getIdProduct());
        assertEquals(cartDto.getCartDetailsDto().get(0).getQuantity(),
                response.getBody().getCartDetailsDto().get(0).getQuantity());
    }

    @Test
    public void testCreateCartConflict() throws MyException {
        // Arrange
        CartUseCase cartUseCase = mock(CartUseCase.class);
        when(cartUseCase.addCart(any(Cart.class))).thenReturn(null);

        CartController cartController = new CartController(cartUseCase,cartMapper);

        // Act
        ResponseEntity<CartDto> response = cartController.createCart(new CartRequestDto());

        // Assert
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testFindAllSuccess() {
        // Arrange
        Long cartId = 1L;
        CartUseCase cartUseCase = mock(CartUseCase.class);
        Cart cart = Cart.builder().id(1L).build(); // Crear un objeto Cart para usar en las pruebas
        when(cartUseCase.findById(cartId)).thenReturn(cart);

        CartController cartController = new CartController(cartUseCase,cartMapper);
        CartDto cartDto = CartDto.builder().id(1L).build();
        // Act
        ResponseEntity<CartDto> response = cartController.findAll(cartId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cartDto.getId(), response.getBody().getId());
    }

    @Test
    public void testFindAllNoContent() {
        // Arrange
        Long cartId = 2L;
        CartUseCase cartUseCase = mock(CartUseCase.class);
        when(cartUseCase.findById(cartId)).thenReturn(null);

        CartController cartController = new CartController(cartUseCase,cartMapper);

        // Act
        ResponseEntity<CartDto> response = cartController.findAll(cartId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testFindAllServerError() {
        // Arrange
        Long cartId = 3L;
        CartUseCase cartUseCase = mock(CartUseCase.class);
        when(cartUseCase.findById(cartId)).thenThrow(new RuntimeException());

        CartController cartController = new CartController(cartUseCase,cartMapper);

        // Act
        ResponseEntity<CartDto> response = cartController.findAll(cartId);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}