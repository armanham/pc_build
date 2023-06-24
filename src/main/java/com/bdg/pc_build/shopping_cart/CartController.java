//package com.bdg.pc_build.shopping_cart;
//
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/api/v1/cart")
//public class CartController {
//
//    Cart cart;
//
//    @PostMapping("/add-item")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
//    public ResponseEntity<?> addItem(
//            @RequestBody CartItem item
//    ) {
//        cart.addItem(item);
//        System.out.println(cart.getCartItems());
//        return ResponseEntity.ok("Cart added successfully");
//    }
//
//    @PostMapping("/remove-item")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
//    public ResponseEntity<?> removeItem(
//            @RequestBody CartItem item
//    ) {
//        cart.removeItem(item);
//        return ResponseEntity.ok("Cart added successfully");
//    }
//}