package com.bdg.pc_build.user.controller;


import com.bdg.pc_build.user.model.entity.User;
import com.bdg.pc_build.user.model.request.EmailUpdateRequest;
import com.bdg.pc_build.user.model.request.FirstNameUpdateRequest;
import com.bdg.pc_build.user.model.request.LastNameUpdateRequest;
import com.bdg.pc_build.user.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {

    UserService userService;

    @PutMapping("/update/first-name")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> updateFirstName(
            @Valid
            @RequestBody FirstNameUpdateRequest request
    ) {
        userService.updateFirstNameByEmail(request.email(), request.newFirstName());
        return ResponseEntity.ok("First name updated successfully");
    }

    @PutMapping("update/last-name")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> updateLastName(
            @Valid
            @RequestBody LastNameUpdateRequest request
    ) {
        userService.updateLastNameByEmail(request.email(), request.newLastName());
        return ResponseEntity.ok("Last name updated successfully");
    }

    @PutMapping("update/email")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> updateEmail(
            @Valid
            @RequestBody EmailUpdateRequest request
    ) {
        userService.updateEmailByEmail(request.email(), request.newEmail());
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

    @GetMapping("/get-all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> getAllUsers() {
        return userService.findAll();
    }
}