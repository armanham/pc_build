package com.bdg.pc_build.cart.controller;

import com.bdg.pc_build.cart.model.CartItem;
import com.bdg.pc_build.cart.service.CartService;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/shopping-cart")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class CartController {

    CartService cartService;

    @GetMapping("/get")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Map<ProductDTO, Integer>> getCartItems() {
        return ResponseEntity.ok(cartService.getProductsInCart());
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> addCartItem(@RequestBody CartItem cartItem) {
        cartService.addProduct(cartItem);
        return ResponseEntity.ok("Product is added to the cart successfully");
    }

    @GetMapping("/remove")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> removeCartItem(@RequestBody CartItem cartItem) {
        cartService.removeProduct(cartItem);
        return ResponseEntity.ok("Product is removed from the cart successfully");
    }

    @GetMapping("/checkout")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> checkout(
            HttpServletRequest request
    ) {
        cartService.checkout(request.getHeader(HttpHeaders.AUTHORIZATION));
        return ResponseEntity.ok("All products are bought successfully");
    }
}