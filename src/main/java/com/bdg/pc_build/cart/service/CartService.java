package com.bdg.pc_build.cart.service;

import com.bdg.pc_build.cart.model.CartItem;
import com.bdg.pc_build.product.model.dto.ProductDTO;

import java.math.BigDecimal;
import java.util.Map;

public interface CartService {

    void addProduct(CartItem item);

    void removeProduct(CartItem item);

    void clearCart();

    Map<ProductDTO, Integer> getProductsInCart();

    void checkout(String authHeader, Boolean isFromBuilder);

    BigDecimal getTotal();
}