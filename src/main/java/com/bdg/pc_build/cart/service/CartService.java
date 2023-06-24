package com.bdg.pc_build.cart.service;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.shopping_cart.CartItem;

import java.math.BigDecimal;
import java.util.Map;

public interface CartService {
    void addProduct(CartItem item);

    void removeProduct(CartItem item);

    Map<ProductDTO, Integer> getProductsInCart();

    void checkout();

    BigDecimal getTotal();
}
