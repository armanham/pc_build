package com.bdg.pc_build.cart.controller;

import com.bdg.pc_build.cart.model.CartItem;
import com.bdg.pc_build.cart.service.CartService;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.bdg.pc_build.cart.service.CartServiceImpl.ProductCountPrice;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/shopping-cart")
@SecurityRequirement(name = "bearerAuth")
public class CartController {

    private final CartService cartService;

    //todo test
    @GetMapping("/get")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<ProductCountPrice> getCurrentCart() {
        return ResponseEntity.ok(cartService.getCurrentCart());
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<?> addCartItem(
            @Valid @RequestBody CartItem cartItem
    ) {
        cartService.addProduct(cartItem);
        return ResponseEntity.ok("Product is added to the cart successfully");
    }

    @DeleteMapping("/remove")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<?> removeCartItem(
            @Valid @RequestBody CartItem cartItem
    ) {
        cartService.removeProduct(cartItem);
        return ResponseEntity.ok("Product is removed from the cart successfully");
    }

    @DeleteMapping("/clear")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<?> clearCart() {
        cartService.clearCart();
        return ResponseEntity.ok("All products are removed from the cart successfully");
    }

    //todo test
    @GetMapping("/checkout")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<?> checkout(
            HttpServletRequest request
    ) {
        Long id = cartService.checkout(request.getHeader(HttpHeaders.AUTHORIZATION), false);
        return ResponseEntity.ok("All products are ordered successfully!\nYour order id is " + id);
    }
}