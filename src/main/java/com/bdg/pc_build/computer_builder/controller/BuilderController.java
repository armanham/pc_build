package com.bdg.pc_build.computer_builder.controller;

import com.bdg.pc_build.computer_builder.model.entity.Computer;
import com.bdg.pc_build.computer_builder.model.request.ComputerCreationRequest;
import com.bdg.pc_build.computer_builder.service.ComputerEntityInitializerBasedOnRequest;
import com.bdg.pc_build.computer_builder.service.ComputerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    ComputerEntityInitializerBasedOnRequest entityInitializerBasedOnRequest;

    @PostMapping("/check")
    public ResponseEntity<?> check(
            @RequestBody ComputerCreationRequest request
    ) {
        computerService.checkComputer(entityInitializerBasedOnRequest.initEntityFromRequest(request));
        return ResponseEntity.ok().body("Compatibility validation passed!");
    }

    @PostMapping("/save")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<?> save(
            @RequestBody ComputerCreationRequest computerCreationRequest,
            HttpServletRequest httpServletRequest
    ) {
        return ResponseEntity.ok().body(computerService.save(computerCreationRequest, httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION)));
    }

    @PostMapping("/checkout/{id}")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<?> checkout(
            @PathVariable Long id,
            HttpServletRequest httpServletRequest
    ) {
        computerService.checkout(id, httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION));
        return ResponseEntity.ok().body("Computer is bought successfully!");
    }

    @GetMapping("/built-computers")
    public ResponseEntity<List<Computer>> getAllOrderedComputers() {
        return ResponseEntity.ok().body(computerService.getAllComputersByIsOrdered(true));
    }
}