package com.bdg.pc_build.cart.service;

import com.bdg.pc_build.cart.model.CartItem;
import com.bdg.pc_build.cart.service.impl.CartServiceImpl.ProductCountPrice;

import java.math.BigDecimal;

public interface CartService {

    void addProduct(CartItem item);

    void removeProduct(CartItem item);

    void clearCart();

    ProductCountPrice getCurrentCart();

    Long checkout(String authHeader, Boolean isFromBuilder);

    BigDecimal getTotal();
}