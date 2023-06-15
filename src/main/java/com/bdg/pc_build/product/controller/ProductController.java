package com.bdg.pc_build.product.controller;

import com.bdg.pc_build.product.model.dto.peripheral.MonitorDTO;
import com.bdg.pc_build.product.model.dto.main_component.*;
import com.bdg.pc_build.product.model.dto.peripheral.*;
import com.bdg.pc_build.product.model.request.EditPriceRequest;
import com.bdg.pc_build.product.model.request.ReduceCountRequest;
import com.bdg.pc_build.product.model.request.creation.main_component.*;
import com.bdg.pc_build.product.model.request.creation.peripheral.*;
import com.bdg.pc_build.product.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/product")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProductController {

    ProductService productService;

    @PostMapping("/new/monitor")
    public ResponseEntity<MonitorDTO> saveMonitor(
            @Valid
            @RequestBody MonitorCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveMonitor(MonitorDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/case")
    public ResponseEntity<CaseDTO> saveCase(
            @Valid
            @RequestBody CaseCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveCase(CaseDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/cooler")
    public ResponseEntity<CoolerDTO> saveCooler(
            @Valid
            @RequestBody CoolerCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveCooler(CoolerDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/cpuCooler")
    public ResponseEntity<CPUCoolerDTO> saveCPUCooler(
            @Valid
            @RequestBody CPUCoolerCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveCpuCooler(CPUCoolerDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/cpu")
    public ResponseEntity<CPUDTO> saveCPU(
            @Valid
            @RequestBody CPUCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveCPU(CPUDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/gpu")
    public ResponseEntity<GPUDTO> saveGPU(
            @Valid
            @RequestBody GPUCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveGPU(GPUDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/internalHardDrive")
    public ResponseEntity<InternalHardDriveDTO> saveInternalHardDrive(
            @Valid
            @RequestBody InternalHardDriveCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveInternalHardDrive(InternalHardDriveDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/motherboard")
    public ResponseEntity<MotherboardDTO> saveMotherboard(
            @Valid
            @RequestBody MotherboardCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveMotherboard(MotherboardDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/powerSupply")
    public ResponseEntity<PowerSupplyDTO> savePowerSupply(
            @Valid
            @RequestBody PowerSupplyCreationRequest request
    ) {
        return ResponseEntity.ok(productService.savePowerSupply(PowerSupplyDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/ram")
    public ResponseEntity<RAMDTO> saveRAM(
            @Valid
            @RequestBody RAMCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveRAM(RAMDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/externalHardDrive")
    public ResponseEntity<ExternalHardDriveDTO> saveExternalHardDrive(
            @Valid
            @RequestBody ExternalHardDriveCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveExternalHardDrive(ExternalHardDriveDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/headset")
    public ResponseEntity<HeadsetDTO> saveHeadset(
            @Valid
            @RequestBody HeadsetCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveHeadset(HeadsetDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/keyboard")
    public ResponseEntity<KeyboardDTO> saveKeyboard(
            @Valid
            @RequestBody KeyboardCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveKeyboard(KeyboardDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/mouse")
    public ResponseEntity<MouseDTO> saveMouse(
            @Valid
            @RequestBody MouseCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveMouse(MouseDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/speaker")
    public ResponseEntity<SpeakerDTO> saveSpeaker(
            @Valid
            @RequestBody SpeakerCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveSpeaker(SpeakerDTO.initDTOFromRequest(request)));
    }

    //ADMIN
    @PutMapping(value = "/edit/{componentType}")
    public ResponseEntity<?> editPrice(
            @PathVariable("componentType") String componentType,
            @RequestBody EditPriceRequest request
    ) {
        switch (componentType) {
            case "monitor" -> {
                return ResponseEntity.ok().body(productService.updateMonitorPriceByName(request.productName(), request.newPrice()));
//            case "case" -> productService.findCaseByName(name);
//            case "cooler" -> productService.
                //TODO cases
            }
            default -> {
                return ResponseEntity.badRequest().body("Invalid component connectivityTypes");
            }
        }
    }

    //ADMIN/USER
    @PutMapping(value = "/reduce/{componentType}")
    public ResponseEntity<?> reduceCount(
            @PathVariable("componentType") String componentType,
            @RequestBody ReduceCountRequest request
    ) {
        switch (componentType) {
            case "monitor" -> {
                return ResponseEntity.ok().body(productService.reduceMonitorCountByName(request.productName(), request.countToBeReduced()));
//            case "case" -> productService.findCaseByName(name);
//            case "cooler" -> productService.
                //TODO cases
            }
            default -> {
                return ResponseEntity.badRequest().body("Invalid component connectivityTypes");
            }
        }
    }
}