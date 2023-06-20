package com.bdg.pc_build.desire_log.controller;

import com.bdg.pc_build.desire_log.model.dto.DesireLogDTO;
import com.bdg.pc_build.desire_log.model.request.DesireLogRequest;
import com.bdg.pc_build.desire_log.service.DesireLogService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping("/desire-log")
public class DesireLogController {

    DesireLogService desireLogService;

    @PostMapping("/new")
    public ResponseEntity<?> addNew(
            @Valid
            @RequestBody final DesireLogRequest request
    ) {
        return ResponseEntity.ok().body(desireLogService.save(new DesireLogDTO(request)));
    }

    @GetMapping("/get-by-id/{id}")
    public DesireLogDTO getById(
            @PathVariable("id") final Long id
    ) {
        return desireLogService.getById(id);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(desireLogService.getAllLogs());
    }

    @GetMapping("/get-by-checked-status/{status}")
    public List<DesireLogDTO> getByCheckedStatus(
            @PathVariable("status") boolean status
    ) {
        return desireLogService.getByCheckStatus(status);
    }

    @PutMapping("/{id}")
    public DesireLogDTO markAsChecked(
            @PathVariable("id") Long id
    ) {
        return desireLogService.markAsCheckedById(id);
    }
}