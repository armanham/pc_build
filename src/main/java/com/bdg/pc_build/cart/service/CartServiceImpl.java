package com.bdg.pc_build.cart.service;

import com.bdg.pc_build.cart.model.CartItem;
import com.bdg.pc_build.config.JwtService;
import com.bdg.pc_build.exception.UserNotFoundException;
import com.bdg.pc_build.order.entity.Order;
import com.bdg.pc_build.order.service.OrderService;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.service.ProductService;
import com.bdg.pc_build.user.model.entity.User;
import com.bdg.pc_build.user.repository.UserDAO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
//@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class CartServiceImpl implements CartService {

    ProductService productService;
    OrderService orderService;
    JwtService jwtService;
    UserDAO userDAO;
    private Map<ProductDTO, Integer> cartItems = new HashMap<>();




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
    public Map<ProductDTO, Integer> getProductsInCart() {
        return Collections.unmodifiableMap(cartItems);
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
    public void checkout(final String authHeader) {
        User user = getUserByAuthHeader(authHeader);
        for (Map.Entry<ProductDTO, Integer> entry : cartItems.entrySet()) {
            if (entry.getKey().getCount() < entry.getValue()) {
                //todo
                throw new IllegalArgumentException("Not enough products");
            } else {
                System.out.println(entry.getKey().getId());
                productService.reduceCountById(entry.getKey().getId(), entry.getValue());
            }
        }
        orderService.save(cartItems.keySet(), getTotal(), user);
        cartItems.clear();
    }


    private User getUserByAuthHeader(final String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException(); //TODO
        }
        final String token = authHeader.substring(7);
        final String email = jwtService.extractUsername(token);

        User user = userDAO.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));

        if (!jwtService.isTokenValid(token, user)) {
            throw new IllegalArgumentException(); //TODO
        }
        return user;
    }
}