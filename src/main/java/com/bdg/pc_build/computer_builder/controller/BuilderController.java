package com.bdg.pc_build.computer_builder.controller;

import com.bdg.pc_build.computer_builder.model.entity.Computer;
import com.bdg.pc_build.computer_builder.model.request.ComputerCreationRequest;
import com.bdg.pc_build.computer_builder.service.ComputerEntityInitializerBasedOnRequest;
import com.bdg.pc_build.computer_builder.service.ComputerService;
import com.bdg.pc_build.exception.IdOutOfScopeException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/builder")
public class BuilderController {

    private final ComputerService computerService;
    private final ComputerEntityInitializerBasedOnRequest entityInitializerBasedOnRequest;

    @PostMapping("/check")
    public ResponseEntity<?> check(
            @Valid @RequestBody ComputerCreationRequest request
    ) {
        computerService.checkCompatibilityBetweenComponentsOfComputer(entityInitializerBasedOnRequest.initEntityFromRequest(request));
        return ResponseEntity.ok().body("Compatibility validation passed!");
    }

    @PostMapping("/save")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<?> save(
            @Valid @RequestBody ComputerCreationRequest computerCreationRequest,
            HttpServletRequest httpServletRequest
    ) {
        computerService.save(computerCreationRequest, httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION));
        return ResponseEntity.ok().body("Computer is saved successfully!");
    }

    @PostMapping("/checkout/{id}")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<?> checkout(
            @PathVariable Long id,
            HttpServletRequest httpServletRequest
    ) {
        if(id <= 0){
            throw new IdOutOfScopeException();
        }
        computerService.orderComputerById(id, httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION));
        return ResponseEntity.ok().body("Computer is ordered successfully!");
    }

    @PostMapping("/buy")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<?> buy(
            @Valid @RequestBody ComputerCreationRequest computerCreationRequest,
            HttpServletRequest httpServletRequest
    ) {
        computerService.checkSaveOrder(computerCreationRequest, httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION));
        return ResponseEntity.ok().body("Compatibility validation passed! Computer is saved and ordered successfully!");
    }

    //todo test
    @GetMapping("/complete-built-computers")
    public ResponseEntity<List<Computer>> getAllOrderedAndCompleteComputers() {
        return ResponseEntity.ok().body(computerService.getAllComputersByIsOrderedAndIsFullyConstructed(true,true));
    }
}