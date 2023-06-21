package com.bdg.pc_build.desire_log.controller;

import com.bdg.pc_build.desire_log.model.dto.DesireLogDTO;
import com.bdg.pc_build.desire_log.model.request.DesireLogRequest;
import com.bdg.pc_build.desire_log.service.DesireLogService;
import jakarta.validation.Valid;
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
@RequestMapping("/api/v1/desire-log")
public class DesireLogController {

    DesireLogService desireLogService;

    @PostMapping("/new")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> addNew(
            @Valid
            @RequestBody final DesireLogRequest request
    ) {
        return ResponseEntity.ok().body(desireLogService.save(new DesireLogDTO(request)));
    }

    @GetMapping("/get-by-id/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public DesireLogDTO getById(
            @PathVariable("id") final Long id
    ) {
        return desireLogService.getById(id);
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAll() {
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
    public DesireLogDTO markAsChecked(
            @PathVariable("id") Long id
    ) {
        return desireLogService.markAsCheckedById(id);
    }
}