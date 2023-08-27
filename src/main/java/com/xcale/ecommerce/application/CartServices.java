package com.xcale.ecommerce.application;

import com.xcale.ecommerce.domain.Cart;
import com.xcale.ecommerce.domain.port.CartPersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CartServices implements CartUseCase {

    private final CartPersistencePort cartPersistencePort;
    @Override
    public Cart addCart(Cart cart) {
        return cartPersistencePort.saveCart(cart);
    }

    @Override
    public Cart findById(Long id) {
        return cartPersistencePort.findById(id);
    }
}
