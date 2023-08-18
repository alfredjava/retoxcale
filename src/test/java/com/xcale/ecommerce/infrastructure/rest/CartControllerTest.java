package com.xcale.ecommerce.infrastructure.rest;

import com.xcale.ecommerce.application.CartUseCase;
import com.xcale.ecommerce.domain.Cart;
import com.xcale.ecommerce.domain.User;
import com.xcale.ecommerce.infrastructure.MyException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static com.xcale.ecommerce.infrastructure.rest.TestUtils.readDataFromFile;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartControllerTest {
    @Test
    void createCart() throws IOException {

        CartUseCase cartUseCase = mock(CartUseCase.class);
        Cart cart = readDataFromFile("request_cart.json",Cart.class); // Crear un objeto Cart para usar en las pruebas
        when(cartUseCase.addCart(any(Cart.class))).thenReturn(cart);

        CartController cartController = new CartController(cartUseCase);

        // Act
        ResponseEntity<Cart> response = cartController.createCart(cart);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(cart, response.getBody());
    }

    @Test
    public void testCreateCartConflict() throws MyException {
        // Arrange
        CartUseCase cartUseCase = mock(CartUseCase.class);
        when(cartUseCase.addCart(any(Cart.class))).thenReturn(null);

        CartController cartController = new CartController(cartUseCase);

        // Act
        ResponseEntity<Cart> response = cartController.createCart(new Cart());

        // Assert
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testCreateCartServerError() throws MyException {
        // Arrange
        CartUseCase cartUseCase = mock(CartUseCase.class);
        when(cartUseCase.addCart(any(Cart.class))).thenThrow(new MyException("Error save",""));

        CartController cartController = new CartController(cartUseCase);

        // Act
        ResponseEntity<Cart> response = cartController.createCart(new Cart());

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
    @Test
    public void testFindAllSuccess() {
        // Arrange
        Long cartId = 1L;
        CartUseCase cartUseCase = mock(CartUseCase.class);
        Cart cart = new Cart(); // Crear un objeto Cart para usar en las pruebas
        when(cartUseCase.getCartById(cartId)).thenReturn(cart);

        CartController cartController = new CartController(cartUseCase);

        // Act
        ResponseEntity<Cart> response = cartController.findAll(cartId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cart, response.getBody());
    }

    @Test
    public void testFindAllNoContent() {
        // Arrange
        Long cartId = 2L;
        CartUseCase cartUseCase = mock(CartUseCase.class);
        when(cartUseCase.getCartById(cartId)).thenReturn(null);

        CartController cartController = new CartController(cartUseCase);

        // Act
        ResponseEntity<Cart> response = cartController.findAll(cartId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testFindAllServerError() {
        // Arrange
        Long cartId = 3L;
        CartUseCase cartUseCase = mock(CartUseCase.class);
        when(cartUseCase.getCartById(cartId)).thenThrow(new RuntimeException());

        CartController cartController = new CartController(cartUseCase);

        // Act
        ResponseEntity<Cart> response = cartController.findAll(cartId);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}