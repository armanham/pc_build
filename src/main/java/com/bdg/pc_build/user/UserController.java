package com.bdg.pc_build.user;


import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/user")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserController {

    UserService userService;

    @PutMapping("/firstname")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> updateFirstName(@Valid  @RequestBody FirstNameUpdateRequest firstNameUpdateRequest) {
        userService.updateFirstNameByEmail(firstNameUpdateRequest.getEmail(), firstNameUpdateRequest.getNewFirstName());
        return ResponseEntity.ok("First name updated successfully");
    }

    @PutMapping("/lastname")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> updateLastName(@Valid  @RequestBody FirstNameUpdateRequest lastNameUpdateRequest) {
        userService.updateLastNameByEmail(lastNameUpdateRequest.getEmail(), lastNameUpdateRequest.getNewFirstName());
        return ResponseEntity.ok("Last name updated successfully");
    }

//    @PutMapping("/email")
//    public ResponseEntity<String> updateEmail( @RequestParam String email, ) {
//        userService.updateEmailByEmail(email);
//        return ResponseEntity.ok("Email updated successfully");
//    }
//
//    @PutMapping("/role")
//    public ResponseEntity<String> updateRole(@RequestParam String email,  String role) {
//        userService.updateRoleByEmail(email, role);
//        return ResponseEntity.ok("Role updated successfully");
//    }
}
