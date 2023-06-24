package com.bdg.pc_build.cart.controller;

import com.bdg.pc_build.cart.service.CartService;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.shopping_cart.CartItem;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CartController {
    CartService cartService;


    @GetMapping("/shopping-cart")
    public ResponseEntity<Map<ProductDTO, Integer>> getCartItems() {
        return ResponseEntity.ok(cartService.getProductsInCart());
    }

    @GetMapping("/shopping-cart/add")
    public ResponseEntity<?> addCartItem(@RequestBody CartItem cartItem){
        cartService.addProduct(cartItem);
        return ResponseEntity.ok("Product is added to the cart successfully");
    }

    @GetMapping("/shopping-cart/remove")
    public ResponseEntity<?> removeCartItem(@RequestBody CartItem cartItem){
        cartService.removeProduct(cartItem);
        return ResponseEntity.ok("Product is removed from the cart successfully");
    }

    @GetMapping("/shopping-cart/checkout")
    public ResponseEntity<?> checkout(){
        cartService.checkout();
        return ResponseEntity.ok("All products are bought successfully");
    }

}
