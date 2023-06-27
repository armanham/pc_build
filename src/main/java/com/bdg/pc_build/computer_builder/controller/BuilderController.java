package com.bdg.pc_build.computer_builder.controller;

import com.bdg.pc_build.cart.service.CartService;
import com.bdg.pc_build.computer_builder.model.request.ComputerCreationRequest;
import com.bdg.pc_build.computer_builder.service.ComputerEntityInitializerBasedOnRequest;
import com.bdg.pc_build.computer_builder.service.ComputerService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/builder")
public class BuilderController {

    ComputerService computerService;
    CartService cartService;
    ComputerEntityInitializerBasedOnRequest entityInitializerBasedOnRequest;

    @PostMapping("/check")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<?> build(
            @RequestBody ComputerCreationRequest request
    ) {
        return ResponseEntity.ok().body(computerService.checkComputer(entityInitializerBasedOnRequest.initEntityFromRequest(request)));
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<?> save(
            @RequestBody ComputerCreationRequest computerCreationRequest,
            HttpServletRequest httpServletRequest
    ) {
        return ResponseEntity.ok().body(computerService.save(computerCreationRequest, httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION)));
    }

//    @GetMapping("/built-computers")
//    public List<ComputerDTO> getAllComputers() {
//        return computerDAOOO.getComputerDTOList();
//    }

    @PostMapping("/checkout/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<?> checkout(
            @PathVariable Long id,
            HttpServletRequest httpServletRequest
    ) {
        computerService.checkout(id, httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION));
        return ResponseEntity.ok().body("Computer is bought successfully!!!");
    }

}