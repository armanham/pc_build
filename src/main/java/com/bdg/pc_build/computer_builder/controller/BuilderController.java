package com.bdg.pc_build.computer_builder.controller;

import com.bdg.pc_build.computer_builder.model.dto.ComputerDTO;
import com.bdg.pc_build.computer_builder.model.request.ComputerCreationRequest;
import com.bdg.pc_build.computer_builder.repository.ComputerDAO;
import com.bdg.pc_build.computer_builder.service.CompatibilityValidator;
import com.bdg.pc_build.computer_builder.service.ComputerService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/builder")
public class BuilderController {

    ComputerService computerService;
    ComputerDAO computerDAO;

    @PostMapping("/build-computer")
    public ResponseEntity<?> build(
            @RequestBody ComputerCreationRequest request
    ) {
        ComputerDTO computerDTO = computerService.initComputerDTOFromRequest(request);
        CompatibilityValidator compatibilityValidator = new CompatibilityValidator(computerDTO);
        if (compatibilityValidator.getComputerDTOToCompatibilityCheck() != null) {
            computerDAO.addComputerDTO(compatibilityValidator.getComputerDTOToCompatibilityCheck());
            return ResponseEntity.ok(compatibilityValidator.getComputerDTOToCompatibilityCheck());
        }
        return ResponseEntity.badRequest().body("Compatibility check failed");
    }

    @GetMapping("built-computers")
    public List<ComputerDTO> getAllComputers() {
        return computerDAO.getComputerDTOList();
    }
}