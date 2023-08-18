package com.xcale.ecommerce.domain.port;

import com.xcale.ecommerce.domain.Cart;

public interface CartPersistencePort {
    Cart saveCart(Cart cart);
    Cart getCartById (Long id);
}
