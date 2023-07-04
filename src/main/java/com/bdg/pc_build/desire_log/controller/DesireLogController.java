package com.bdg.pc_build.desire_log.controller;

import com.bdg.pc_build.desire_log.model.dto.DesireLogDTO;
import com.bdg.pc_build.desire_log.model.request.DesireLogCreationRequest;
import com.bdg.pc_build.desire_log.service.DesireLogService;
import com.bdg.pc_build.exception.IdOutOfScopeException;
import com.bdg.pc_build.user.model.dto.UserDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/desire-log")
@SecurityRequirement(name = "bearerAuth")
public class DesireLogController {

    private final DesireLogService desireLogService;

    @PostMapping("/new")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<?> save(
            @Valid @RequestBody final DesireLogCreationRequest desireLogCreationRequest,
            HttpServletRequest httpServletRequest
    ) {
        return ResponseEntity.ok().body(desireLogService.save(httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION), new DesireLogDTO(desireLogCreationRequest)));
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DesireLogDTO> getById(
            @PathVariable("id") final Long id
    ) {
        if(id <= 0){
            throw new IdOutOfScopeException();
        }
        return ResponseEntity.ok().body(desireLogService.getById(id));
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<DesireLogDTO>> getAll() {
        return ResponseEntity.ok().body(desireLogService.getAllLogs());
    }

    @GetMapping("/get-by-checked-status/{status}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<DesireLogDTO> getByCheckedStatus(
            @PathVariable("status") boolean status
    ) {
        return desireLogService.getByCheckStatus(status);
    }

    @PutMapping("/mark-as-checked/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DesireLogDTO> markAsChecked(
            @PathVariable("id") Long id
    ) {
        if(id <= 0){
            throw new IdOutOfScopeException();
        }
        return ResponseEntity.ok().body(desireLogService.markAsCheckedById(id));
    }

    @GetMapping("/get/{id}/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Set<UserDTO>> getUsersByLogId(
            @PathVariable("id") Long id
    ) {
        if(id <= 0){
            throw new IdOutOfScopeException();
        }
        return ResponseEntity.ok().body(desireLogService.getUsersByLogId(id));
    }
}