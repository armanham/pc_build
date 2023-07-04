package com.bdg.pc_build.cart.service;

import com.bdg.pc_build.cart.model.CartItem;
import com.bdg.pc_build.exception.NotEnoughInStockException;
import com.bdg.pc_build.exception.OutOfStockException;
import com.bdg.pc_build.order.model.dto.OrderDTO;
import com.bdg.pc_build.order.service.OrderService;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.service.ProductService;
import com.bdg.pc_build.user.model.entity.User;
import com.bdg.pc_build.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Service
@Transactional
public class CartServiceImpl implements CartService {

    private final ProductService productService;
    private final OrderService orderService;
    private final UserService userService;

    private final Map<ProductDTO, Integer> cartItems = new HashMap<>();


    @Override
    public void addProduct(final CartItem item) {
        ProductDTO product = productService.findById(item.productId());
        if (cartItems.containsKey(product)) {
            cartItems.replace(product, cartItems.get(product) + item.quantity());
        } else {
            cartItems.put(product, item.quantity());
        }
    }

    @Override
    public void removeProduct(final CartItem item) {
        ProductDTO product = productService.findById(item.productId());
        if (cartItems.containsKey(product)) {
            if (cartItems.get(product) > item.quantity())
                cartItems.replace(product, cartItems.get(product) - item.quantity());
            else if (Objects.equals(cartItems.get(product), item.quantity())) {
                cartItems.remove(product);
            }
        }
    }

    @Override
    public void clearCart() {
        cartItems.clear();
    }

    @Override
    public ProductCountPrice getCurrentCart() {

        return new ProductCountPrice(Collections.unmodifiableMap(cartItems), getTotal());
    }

    @Override
    public BigDecimal getTotal() {
        return cartItems.entrySet()
                .stream()
                .map(entry -> BigDecimal.valueOf(entry.getKey().getPrice()).multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
    @Override
    public Long checkout(final String authHeader, final Boolean isFromBuilder) {
        User user = userService.getUserByAuthHeader(authHeader);
        for (Map.Entry<ProductDTO, Integer> entry : cartItems.entrySet()) {
            ProductDTO currentProduct = entry.getKey();
            if (currentProduct.getCount() == 0) {
                throw new OutOfStockException(currentProduct.getClass(), currentProduct.getName());
            } else if (currentProduct.getCount() < entry.getValue()) {
                throw new NotEnoughInStockException(currentProduct.getClass(), currentProduct.getName(), currentProduct.getCount());
            }
        }
        Long id = orderService.saveOrder(cartItems.keySet(), getTotal(), user, isFromBuilder).getId();
        cartItems.clear();
        return id;
    }


    public record ProductCountPrice(
            Map<ProductDTO, Integer> productInCart,
            BigDecimal totalPrice
    ){
    }
}
