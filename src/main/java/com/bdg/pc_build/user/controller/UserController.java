package com.bdg.pc_build.user.controller;

import com.bdg.pc_build.pc_builder.model.dto.ComputerDTO;
import com.bdg.pc_build.desire_log.model.dto.DesireLogDTO;
import com.bdg.pc_build.exception.IdOutOfScopeException;
import com.bdg.pc_build.order.model.dto.OrderDTO;
import com.bdg.pc_build.user.model.dto.UserDTO;
import com.bdg.pc_build.user.model.request.EmailUpdateRequest;
import com.bdg.pc_build.user.model.request.FirstNameUpdateRequest;
import com.bdg.pc_build.user.model.request.LastNameUpdateRequest;
import com.bdg.pc_build.user.model.request.PasswordUpdateRequest;
import com.bdg.pc_build.user.service.UserService;
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
@RequestMapping(value = "/api/v1/user")
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private final UserService userService;

    @PutMapping("/update/first-name")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<String> updateFirstName(
            @Valid @RequestBody final FirstNameUpdateRequest firstNameUpdateRequest,
            HttpServletRequest httpServletRequest
    ) {
        userService.updateFirstNameByAuthHeader(httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION), firstNameUpdateRequest.newFirstName());
        return ResponseEntity.ok("First name updated successfully");
    }

    @PutMapping("/update/last-name")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<String> updateLastName(
            @Valid @RequestBody final LastNameUpdateRequest lastNameUpdateRequest,
            HttpServletRequest httpServletRequest
    ) {
        userService.updateLastNameByAuthHeader(httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION), lastNameUpdateRequest.newLastName());
        return ResponseEntity.ok("Last name updated successfully");
    }

    @PutMapping("/update/email")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<String> updateEmail(
            @Valid @RequestBody final EmailUpdateRequest emailUpdateRequest,
            HttpServletRequest httpServletRequest

    ) {
        final String newToken = userService.updateEmailByAuthHeader(httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION), emailUpdateRequest.newEmail());
        return ResponseEntity.ok("Email updated successfully: New token: " + newToken);
    }

    @PutMapping("/update/password")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<String> updatePassword(
            @Valid @RequestBody final PasswordUpdateRequest passwordUpdateRequest,
            HttpServletRequest httpServletRequest
    ) {
        final String newToken = userService.updatePasswordByAuthHeader(httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION), passwordUpdateRequest.newPassword());
        return ResponseEntity.ok("Password updated successfully: New token: " + newToken);
    }

    @PutMapping("/{id}/update/first-name")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> updateFirstName(
            @PathVariable("id") final Long id,
            @Valid @RequestBody final FirstNameUpdateRequest request
    ) {
        if (id <= 0) {
            throw new IdOutOfScopeException(HttpStatus.BAD_REQUEST);
        }
        userService.updateFirstNameById(id, request.newFirstName());
        return ResponseEntity.ok("First name updated successfully");
    }

    @PutMapping("/{id}/update/last-name")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> updateLastName(
            @PathVariable("id") final Long id,
            @Valid @RequestBody final LastNameUpdateRequest request
    ) {
        if (id <= 0) {
            throw new IdOutOfScopeException(HttpStatus.BAD_REQUEST);
        }
        userService.updateLastNameById(id, request.newLastName());
        return ResponseEntity.ok("Last name updated successfully");
    }

    @PutMapping("/{id}/update/email")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> updateEmail(
            @PathVariable("id") final Long id,
            @Valid @RequestBody final EmailUpdateRequest request
    ) {
        if (id <= 0) {
            throw new IdOutOfScopeException(HttpStatus.BAD_REQUEST);
        }
        final String newToken = userService.updateEmailById(id, request.newEmail());
        return ResponseEntity.ok("Email updated successfully: New token: " + newToken);
    }

    @PutMapping("/{id}/update/password")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> updatePassword(
            @PathVariable("id") final Long id,
            @Valid @RequestBody final PasswordUpdateRequest request
    ) {
        if (id <= 0) {
            throw new IdOutOfScopeException(HttpStatus.BAD_REQUEST);
        }
        final String newToken = userService.updatePasswordById(id, request.newPassword());
        return ResponseEntity.ok("Password updated successfully: New token: " + newToken);
    }

    @PutMapping("/{email}/update/mark-as-admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> markAsAdmin(
            @PathVariable("email") final String email
    ) {
        userService.changeUserRoleToAdminByEmail(email);
        return ResponseEntity.ok("Role updated successfully");
    }

    @PutMapping("/{email}/update/mark-as-user")
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
            @PathVariable("id") final Long id
    ) {
        if (id <= 0) {
            throw new IdOutOfScopeException(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(userService.getBuiltComputersByUserId(id));
    }

    @GetMapping("/{id}/orders")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<OrderDTO>> getOrdersByUserId(
            @PathVariable("id") final Long id
    ) {
        if (id <= 0) {
            throw new IdOutOfScopeException(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(userService.getOrdersByUserId(id));
    }

    @GetMapping("/{id}/desire-log")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<DesireLogDTO>> getDesireLogsByUserId(
            @PathVariable("id") final Long id
    ) {
        if (id <= 0) {
            throw new IdOutOfScopeException(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(userService.getDesireLogsByUserId(id));
    }

    @GetMapping("/get-all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }
}