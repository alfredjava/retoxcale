package com.xcale.ecommerce.application;

import com.xcale.ecommerce.domain.Cart;

public interface CartUseCase {
    Cart addCart(Cart cart);
    Cart getCartById (Long id);
}
