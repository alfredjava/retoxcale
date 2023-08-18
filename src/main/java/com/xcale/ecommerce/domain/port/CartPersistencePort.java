package com.xcale.ecommerce.domain.port;

import com.xcale.ecommerce.domain.Cart;

import java.util.List;

public interface CartPersistencePort {
    Cart saveCart(Cart cart);
    Cart getCartById (Long id);

    List<Cart> listAll();
    void deleteById (Long id);
}
