package com.bdg.pc_build.builder.controller;

import com.bdg.pc_build.builder.model.dto.ComputerDTO;
import com.bdg.pc_build.builder.model.request.ComputerCreationRequest;
import com.bdg.pc_build.builder.repository.ComputerDAO;
import com.bdg.pc_build.builder.service.CompatibilityValidator;
import com.bdg.pc_build.builder.service.ComputerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping("/builder")
@Api(tags = "builder_controller")
public class BuilderController {

    ComputerService computerService;
    ComputerDAO computerDAO;

    @PostMapping("/")
    @ApiOperation(value = "build")
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