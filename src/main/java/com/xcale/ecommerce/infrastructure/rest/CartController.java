package com.xcale.ecommerce.infrastructure.rest;

import com.xcale.ecommerce.application.CartUseCase;
import com.xcale.ecommerce.domain.Cart;
import com.xcale.ecommerce.domain.Product;
import com.xcale.ecommerce.infrastructure.MyException;
import com.xcale.ecommerce.infrastructure.exception.Throws;
import com.xcale.ecommerce.infrastructure.rest.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
@Slf4j
public class CartController {
    private final CartUseCase cartUseCase;


    @PostMapping
    @Throws(MyException.class)
    public ResponseEntity<Cart> createCart(@RequestBody Cart Cart) {
        try {
            Cart cart =  cartUseCase.addCart(Cart);

            if (cart == null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(cart, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> findAll(@PathVariable Long id) {
        try {
            Cart cart =  cartUseCase.getCartById(id);

            if (cart == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
