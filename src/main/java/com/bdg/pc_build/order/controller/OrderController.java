package com.bdg.pc_build.order.controller;

import com.bdg.pc_build.order.entity.Order;
import com.bdg.pc_build.order.service.OrderService;
import com.bdg.pc_build.shopping_cart.Cart;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    OrderService orderService;

//    @PostMapping("/new")

}