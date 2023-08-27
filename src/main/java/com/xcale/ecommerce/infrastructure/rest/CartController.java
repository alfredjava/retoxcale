package com.xcale.ecommerce.infrastructure.rest;

import com.xcale.ecommerce.application.CartUseCase;
import com.xcale.ecommerce.domain.Cart;
import com.xcale.ecommerce.infrastructure.MyException;
import com.xcale.ecommerce.infrastructure.exception.Throws;
import com.xcale.ecommerce.infrastructure.rest.dto.CartDto;
import com.xcale.ecommerce.infrastructure.rest.mapper.CartMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
@Slf4j
public class CartController {
    private final CartUseCase cartUseCase;
    private final CartMapper cartMapper;

    @PostMapping
    @Throws(MyException.class)
    public ResponseEntity<CartDto> createCart(@RequestBody Cart cart) {
        try {
            CartDto cartDto =  cartMapper.toDto(cartUseCase.addCart(cart));

            if (cartDto == null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(cartDto, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error in createCart", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDto> findAll(@PathVariable Long id) {
        try {
            Cart cart =  cartUseCase.findById(id);

            if (cart == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(cartMapper.toDto(cart));
        } catch (Exception e) {
            log.error("Error in findAll", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
