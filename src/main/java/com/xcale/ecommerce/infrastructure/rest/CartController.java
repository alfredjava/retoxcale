package com.xcale.ecommerce.infrastructure.rest;

import com.xcale.ecommerce.application.CartUseCase;
import com.xcale.ecommerce.domain.Cart;
import com.xcale.ecommerce.domain.Product;
import com.xcale.ecommerce.infrastructure.MyException;
import com.xcale.ecommerce.infrastructure.exception.Throws;
import com.xcale.ecommerce.infrastructure.rest.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
@Slf4j
public class CartController {
    private final CartUseCase cartUseCase;


    @PostMapping
    @Throws(MyException.class)
    public ResponseEntity<Cart> createCart(@RequestBody Cart Cart) {
        return ResponseEntity.ok(
                cartUseCase.addCart(Cart));
    }
}
