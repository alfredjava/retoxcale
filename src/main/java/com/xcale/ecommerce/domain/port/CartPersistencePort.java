package com.xcale.ecommerce.domain.port;

import com.xcale.ecommerce.domain.Cart;

import java.util.List;

public interface CartPersistencePort {
    Cart saveCart(Cart cart);
    Cart findById (Long id);

    List<Cart> findAll();
    void deleteById (Long id);
}
