package com.bdg.pc_build.product.controller;

import com.bdg.pc_build.product.model.dto.main_component.*;
import com.bdg.pc_build.product.model.dto.peripheral.*;
import com.bdg.pc_build.product.model.request.EditPriceRequest;
import com.bdg.pc_build.product.model.request.ReduceCountRequest;
import com.bdg.pc_build.product.model.request.creation.main_component.*;
import com.bdg.pc_build.product.model.request.creation.peripheral.*;
import com.bdg.pc_build.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/management/product")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProductManagementController {

    ProductService productService;

    @PostMapping("/new/monitor")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MonitorDTO> saveMonitor(
            @Valid
            @RequestBody MonitorCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveMonitor(MonitorDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/case")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CaseDTO> saveCase(
            @Valid
            @RequestBody CaseCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveCase(CaseDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/cooler")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CoolerDTO> saveCooler(
            @Valid
            @RequestBody CoolerCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveCooler(CoolerDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/cpu-cooler")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CPUCoolerDTO> saveCPUCooler(
            @Valid
            @RequestBody CPUCoolerCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveCpuCooler(CPUCoolerDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/cpu")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CPUDTO> saveCPU(
            @Valid
            @RequestBody CPUCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveCPU(CPUDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/gpu")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<GPUDTO> saveGPU(
            @Valid
            @RequestBody GPUCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveGPU(GPUDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/internal-hard-drive")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<InternalHardDriveDTO> saveInternalHardDrive(
            @Valid
            @RequestBody InternalHardDriveCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveInternalHardDrive(InternalHardDriveDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/motherboard")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MotherboardDTO> saveMotherboard(
            @Valid
            @RequestBody MotherboardCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveMotherboard(MotherboardDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/power-supply")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<PowerSupplyDTO> savePowerSupply(
            @Valid
            @RequestBody PowerSupplyCreationRequest request
    ) {
        return ResponseEntity.ok(productService.savePowerSupply(PowerSupplyDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/ram")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<RAMDTO> saveRAM(
            @Valid
            @RequestBody RAMCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveRAM(RAMDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/external-hard-drive")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ExternalHardDriveDTO> saveExternalHardDrive(
            @Valid
            @RequestBody ExternalHardDriveCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveExternalHardDrive(ExternalHardDriveDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/headset")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HeadsetDTO> saveHeadset(
            @Valid
            @RequestBody HeadsetCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveHeadset(HeadsetDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/keyboard")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<KeyboardDTO> saveKeyboard(
            @Valid
            @RequestBody KeyboardCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveKeyboard(KeyboardDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/mouse")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MouseDTO> saveMouse(
            @Valid
            @RequestBody MouseCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveMouse(MouseDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/speaker")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<SpeakerDTO> saveSpeaker(
            @Valid
            @RequestBody SpeakerCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveSpeaker(SpeakerDTO.initDTOFromRequest(request)));
    }

    @PutMapping(value = "/edit-price/{componentType}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> editPrice(
            @PathVariable("componentType") String componentType,
            @RequestBody EditPriceRequest request
    ) {
        switch (componentType) {
            case "case" ->
                    ResponseEntity.ok().body(productService.updateCasePriceByName(request.productName(), request.newPrice()));
            case "cooler" ->
                    ResponseEntity.ok().body(productService.updateCoolerPriceByName(request.productName(), request.newPrice()));
            case "cpu-cooler" ->
                    ResponseEntity.ok().body(productService.updateCpuCoolerPriceByName(request.productName(), request.newPrice()));
            case "cpu" ->
                    ResponseEntity.ok().body(productService.updateCpuPriceByName(request.productName(), request.newPrice()));
            case "gpu" ->
                    ResponseEntity.ok().body(productService.updateGpuPriceByName(request.productName(), request.newPrice()));
            case "internal-hard-drive" ->
                    ResponseEntity.ok().body(productService.updateInternalHardDrivePriceByName(request.productName(), request.newPrice()));
            case "motherboard" ->
                    ResponseEntity.ok().body(productService.updateMotherboardPriceByName(request.productName(), request.newPrice()));
            case "power-supply" ->
                    ResponseEntity.ok().body(productService.updatePowerSupplyPriceByName(request.productName(), request.newPrice()));
            case "ram" ->
                    ResponseEntity.ok().body(productService.updateRamPriceByName(request.productName(), request.newPrice()));
            case "external-hard-drive" ->
                    ResponseEntity.ok().body(productService.updateExternalHardDrivePriceByName(request.productName(), request.newPrice()));
            case "headset" ->
                    ResponseEntity.ok().body(productService.updateHeadsetPriceByName(request.productName(), request.newPrice()));
            case "keyboard" ->
                    ResponseEntity.ok().body(productService.updateKeyboardPriceByName(request.productName(), request.newPrice()));
            case "monitor" ->
                    ResponseEntity.ok().body(productService.updateMonitorPriceByName(request.productName(), request.newPrice()));
            case "mouse" ->
                    ResponseEntity.ok().body(productService.updateMousePriceByName(request.productName(), request.newPrice()));
            case "speaker" ->
                    ResponseEntity.ok().body(productService.updateSpeakerPriceByName(request.productName(), request.newPrice()));
            default -> ResponseEntity.badRequest().body("Invalid component type");
        }
        return ResponseEntity.ok().body("");
    }

    @PutMapping(value = "/reduce/{componentType}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> reduceCount(
            @PathVariable("componentType") String componentType,
            @RequestBody ReduceCountRequest request
    ) {
        switch (componentType) {
            case "case" ->
                    ResponseEntity.ok().body(productService.reduceCaseCountByName(request.productName(), request.countToBeReduced()));
            case "cooler" ->
                    ResponseEntity.ok().body(productService.reduceCoolerCountByName(request.productName(), request.countToBeReduced()));
            case "cpu-cooler" ->
                    ResponseEntity.ok().body(productService.reduceCPUCoolerCountByName(request.productName(), request.countToBeReduced()));
            case "cpu" ->
                    ResponseEntity.ok().body(productService.reduceCPUCountByName(request.productName(), request.countToBeReduced()));
            case "gpu" ->
                    ResponseEntity.ok().body(productService.reduceGPUCountByName(request.productName(), request.countToBeReduced()));
            case "internal-hard-drive" ->
                    ResponseEntity.ok().body(productService.reduceInternalHardDriveCountByName(request.productName(), request.countToBeReduced()));
            case "motherboard" ->
                    ResponseEntity.ok().body(productService.reduceMotherboardCountByName(request.productName(), request.countToBeReduced()));
            case "power-supply" ->
                    ResponseEntity.ok().body(productService.reducePowerSupplyCountByName(request.productName(), request.countToBeReduced()));
            case "ram" ->
                    ResponseEntity.ok().body(productService.reduceRAMCountByName(request.productName(), request.countToBeReduced()));
            case "external-hard-drive" ->
                    ResponseEntity.ok().body(productService.reduceExternalHardDriveCountByName(request.productName(), request.countToBeReduced()));
            case "headset" ->
                    ResponseEntity.ok().body(productService.reduceHeadsetCountByName(request.productName(), request.countToBeReduced()));
            case "keyboard" ->
                    ResponseEntity.ok().body(productService.reduceKeyboardCountByName(request.productName(), request.countToBeReduced()));
            case "monitor" ->
                    ResponseEntity.ok().body(productService.reduceMonitorCountByName(request.productName(), request.countToBeReduced()));
            case "mouse" ->
                    ResponseEntity.ok().body(productService.reduceMouseCountByName(request.productName(), request.countToBeReduced()));
            case "speaker" ->
                    ResponseEntity.ok().body(productService.reduceSpeakerCountByName(request.productName(), request.countToBeReduced()));
            default -> ResponseEntity.badRequest().body("Invalid component type");
        }
        return ResponseEntity.ok().body("");
    }
}