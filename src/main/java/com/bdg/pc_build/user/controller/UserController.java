package com.bdg.pc_build.user.controller;

import com.bdg.pc_build.computer_builder.model.dto.ComputerDTO;
import com.bdg.pc_build.desire_log.model.dto.DesireLogDTO;
import com.bdg.pc_build.exception.IdOutOfScopeException;
import com.bdg.pc_build.order.model.dto.OrderDTO;
import com.bdg.pc_build.user.model.dto.UserDTO;
import com.bdg.pc_build.user.model.request.EmailUpdateRequest;
import com.bdg.pc_build.user.model.request.FirstNameUpdateRequest;
import com.bdg.pc_build.user.model.request.LastNameUpdateRequest;
import com.bdg.pc_build.user.service.UserService;
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
@RequestMapping(value = "/api/v1/user")
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private final UserService userService;

    @PutMapping("/update/first-name")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<String> updateFirstName(
            @Valid @RequestBody FirstNameUpdateRequest firstNameUpdateRequest,
            HttpServletRequest httpServletRequest
    ) {
        userService.updateFirstNameByAuthHeader(httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION), firstNameUpdateRequest.newFirstName());
        return ResponseEntity.ok("First name updated successfully");
    }

    @PutMapping("update/last-name")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<String> updateLastName(
            @Valid @RequestBody LastNameUpdateRequest lastNameUpdateRequest,
            HttpServletRequest httpServletRequest
    ) {
        userService.updateLastNameByAuthHeader(httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION), lastNameUpdateRequest.newLastName());
        return ResponseEntity.ok("Last name updated successfully");
    }

    @PutMapping("update/email")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<String> updateEmail(
            @Valid @RequestBody EmailUpdateRequest emailUpdateRequest,
            HttpServletRequest httpServletRequest

    ) {
        userService.updateEmailByAuthHeader(httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION), emailUpdateRequest.newEmail());
        return ResponseEntity.ok("Email updated successfully");
    }

    @PutMapping("update/mark-as-admin/{email}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> markAsAdmin(
            @PathVariable("email") final String email
    ) {
        userService.changeUserRoleToAdminByEmail(email);
        return ResponseEntity.ok("Role updated successfully");
    }

    @PutMapping("update/mark-as-user/{email}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> markAsUser(
            @PathVariable("email") final String email
    ) {
        userService.changeAdminRoleToUserByEmail(email);
        return ResponseEntity.ok("Role updated successfully");
    }

    @GetMapping("/built-computers")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<ComputerDTO>> getBuiltAndOrderedComputers(
            HttpServletRequest request
    ) {
        return ResponseEntity.ok(
                userService.getBuiltComputersByAuthHeader(request.getHeader(HttpHeaders.AUTHORIZATION))
        );
    }

    @GetMapping("/orders")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<OrderDTO>> getOrders(
            HttpServletRequest request
    ) {
        return ResponseEntity.ok(
                userService.getOrdersByAuthHeader(request.getHeader(HttpHeaders.AUTHORIZATION))
        );
    }

    @GetMapping("/desire-log")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<DesireLogDTO>> getDesireLogs(
            HttpServletRequest request
    ) {
        return ResponseEntity.ok(
                userService.getDesireLogsByAuthHeader(request.getHeader(HttpHeaders.AUTHORIZATION))
        );
    }

    @GetMapping("/{id}/built-computers")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<ComputerDTO>> getBuiltComputersByUserId(
            @PathVariable("id") Long id
    ) {
        if(id <= 0){
            throw new IdOutOfScopeException();
        }
        return ResponseEntity.ok(userService.getBuiltComputersByUserId(id));
    }

    @GetMapping("/{id}/orders")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<OrderDTO>> getOrdersByUserId(
            @PathVariable("id") Long id
    ) {
        if(id <= 0){
            throw new IdOutOfScopeException();
        }
        return ResponseEntity.ok(userService.getOrdersByUserId(id));
    }

    @GetMapping("/{id}/desire-log")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<DesireLogDTO>> getDesireLogsByUserId(
            @PathVariable("id") Long id
    ) {
        if(id <= 0){
            throw new IdOutOfScopeException();
        }
        return ResponseEntity.ok(userService.getDesireLogsByUserId(id));
    }

    @GetMapping("/get-all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserDTO> getAllUsers() {
        return userService.findAll();
    }
}