package com.bdg.pc_build.order.controller;

import com.bdg.pc_build.exception.IdOutOfScopeException;
import com.bdg.pc_build.order.enumerations.OrderStatus;
import com.bdg.pc_build.order.model.dto.OrderDTO;
import com.bdg.pc_build.order.service.OrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping(value = "/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/get-all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/get-all-by-status/new")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<OrderDTO>> getAllOrdersByNewStatus() {
        return ResponseEntity.ok(orderService.getAllOrdersByStatus(OrderStatus.NEW));
    }

    @GetMapping("/get-all-by-status/in-process")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<OrderDTO>> getAllOrdersByInProcessStatus() {
        return ResponseEntity.ok(orderService.getAllOrdersByStatus(OrderStatus.IN_PROCESS));
    }

    @GetMapping("/get-all-by-status/completed")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<OrderDTO>> getAllOrdersByCompletedStatus() {
        return ResponseEntity.ok(orderService.getAllOrdersByStatus(OrderStatus.COMPLETED));
    }

    @GetMapping("/get-all-by-status/canceled")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<OrderDTO>> getAllOrdersByCanceledStatus() {
        return ResponseEntity.ok(orderService.getAllOrdersByStatus(OrderStatus.CANCELED));
    }

    @PutMapping("/{id}/mark-as-in-process")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> markOrderAsInProcess(
            @PathVariable("id") Long id
    ) {
        if(id <= 0){
            throw new IdOutOfScopeException(HttpStatus.BAD_REQUEST);
        }
        orderService.markOrderAsInProcessById(id);
        return ResponseEntity.ok("Order status changed successfully: NEW -> IN_PROCESS");
    }

    @PutMapping("/{id}/mark-as-completed")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> markOrderAsCompleted(
            @PathVariable("id") Long id
    ) {
        if(id <= 0){
            throw new IdOutOfScopeException(HttpStatus.BAD_REQUEST);
        }
        orderService.markOrderAsCompletedById(id);
        return ResponseEntity.ok("Order status changed successfully: IN_PROCESS -> COMPLETED");
    }

    @PutMapping("/{id}/mark-as-canceled")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> markOrderAsCanceled(
            @PathVariable("id") Long id
    ) {
        if(id <= 0){
            throw new IdOutOfScopeException(HttpStatus.BAD_REQUEST);
        }
        orderService.markOrderAsCanceledById(id);
        return ResponseEntity.ok("Order status changed successfully: CANCELED");
    }

    @PutMapping("/own/{id}/mark-as-canceled")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<?> markOrderAsCanceled(
            @PathVariable("id") Long id,
            HttpServletRequest request
    ) {
        if(id <= 0){
            throw new IdOutOfScopeException(HttpStatus.BAD_REQUEST);
        }
        orderService.markOrderAsCanceledById(id, request.getHeader(HttpHeaders.AUTHORIZATION));
        return ResponseEntity.ok("Order status changed successfully: CANCELED");
    }
}