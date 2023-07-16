package com.bdg.pc_build.pc_builder.controller;

import com.bdg.pc_build.pc_builder.service.PCService;
import com.bdg.pc_build.cart.model.CartItem;
import com.bdg.pc_build.cart.service.impl.CartServiceImpl;
import com.bdg.pc_build.pc_builder.converter.ComputerEntityInitializerBasedOnRequest;
import com.bdg.pc_build.pc_builder.model.dto.ComputerDTO;
import com.bdg.pc_build.exception.IdOutOfScopeException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/pc-builder")
public class PCBuilderController {

    private final PCService pcService;
    private final ComputerEntityInitializerBasedOnRequest entityInitializerBasedOnRequest;

    @GetMapping("/get")
    public ResponseEntity<CartServiceImpl.ProductCountPrice> getCurrentBuilder() {
        return ResponseEntity.ok(pcService.getCurrentBuilder());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addComponent(
            @Valid @RequestBody CartItem cartItem
    ) {
        pcService.addProduct(cartItem);
        return ResponseEntity.ok("Product is added to the builder successfully");
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> removeComponent(
            @Valid @RequestBody CartItem cartItem
    ) {
        pcService.removeProduct(cartItem);
        return ResponseEntity.ok("Product is removed from the builder successfully");
    }

    @DeleteMapping("/clear")
    public ResponseEntity<?> clearBuilder() {
        pcService.clearBuilder();
        return ResponseEntity.ok("All products are removed from the builder successfully");
    }

    @PostMapping("/check")
    public ResponseEntity<?> check() {
        pcService.checkCompatibilityBetweenComponentsOfComputer(entityInitializerBasedOnRequest.initEntityFromRequest(pcService.getComponents()));
        return ResponseEntity.ok().body("Compatibility validation passed!");
    }

    @PostMapping("/save")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<ComputerDTO> save(
            HttpServletRequest httpServletRequest
    ) {
        return ResponseEntity.ok().body(new ComputerDTO(pcService.save(pcService.getComponents(), httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION))));
    }

    @PostMapping("/built-computers/{id}/order")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<?> orderBuiltComputer(
            @PathVariable Long id,
            HttpServletRequest httpServletRequest
    ) {
        if (id <= 0) {
            throw new IdOutOfScopeException(HttpStatus.BAD_REQUEST);
        }
        Long orderId = pcService.orderComputerById(id, httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION));
        return ResponseEntity.ok().body("Computer is ordered successfully! Order Id: " + orderId);
    }

    @PostMapping("/buy")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<?> buy(
            HttpServletRequest httpServletRequest
    ) {
        Long orderId = pcService.checkSaveOrder(pcService.getComponents(), httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION));
        return ResponseEntity.ok().body("Compatibility validation passed! Computer is saved and ordered successfully! Order Id: " + orderId);
    }

    @GetMapping("/complete-built-computers")
    public ResponseEntity<List<ComputerDTO>> getAllOrderedAndCompleteComputers() {
        return ResponseEntity.ok().body(pcService.getAllComputersByIsOrderedAndIsFullyConstructed(true, true)
                .stream()
                .map(ComputerDTO::new)
                .toList());
    }
}