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

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/shopping-cart")
@SecurityRequirement(name = "bearerAuth")
public class CartController {

    private final CartService cartService;

    @GetMapping("/get")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Map<ProductDTO, Integer>> getCartItems() {
        return ResponseEntity.ok(cartService.getProductsInCart());
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
    public ResponseEntity<?> removeCartItem() {
        cartService.clearCart();
        return ResponseEntity.ok("All products are removed from the cart successfully");
    }

    @GetMapping("/checkout")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<?> checkout(
            HttpServletRequest request
    ) {
        cartService.checkout(request.getHeader(HttpHeaders.AUTHORIZATION), false);
        return ResponseEntity.ok("All products are ordered successfully");
    }
}