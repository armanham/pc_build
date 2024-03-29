package com.bdg.pc_build.product.controller;

import com.bdg.pc_build.product.model.dto.main.*;
import com.bdg.pc_build.product.model.dto.peripheral.*;
import com.bdg.pc_build.product.model.request.creation.main.*;
import com.bdg.pc_build.product.model.request.creation.peripheral.*;
import com.bdg.pc_build.product.model.request.update.EditPriceRequest;
import com.bdg.pc_build.product.model.request.update.UpdateProductCountRequest;
import com.bdg.pc_build.product.service.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/new/monitor")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MonitorDTO> saveMonitor(
            @Valid @RequestBody MonitorCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveMonitor(new MonitorDTO(request)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/new/case")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CaseDTO> saveCase(
            @Valid @RequestBody CaseCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveCase(new CaseDTO(request)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/new/cooler")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CoolerDTO> saveCooler(
            @Valid @RequestBody CoolerCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveCooler(new CoolerDTO(request)));
    }

    @PostMapping("/new/cpu-cooler")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CPUCoolerDTO> saveCpuCooler(
            @Valid @RequestBody CPUCoolerCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveCpuCooler(new CPUCoolerDTO(request)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/new/cpu")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CPUDTO> saveCpu(
            @Valid @RequestBody CPUCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveCpu(new CPUDTO(request)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/new/gpu")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<GPUDTO> saveGpu(
            @Valid @RequestBody GPUCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveGpu(new GPUDTO(request)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/new/internal-hard-drive")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<InternalHardDriveDTO> saveInternalHardDrive(
            @Valid @RequestBody InternalHardDriveCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveInternalHardDrive(new InternalHardDriveDTO(request)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/new/motherboard")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MotherboardDTO> saveMotherboard(
            @Valid @RequestBody MotherboardCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveMotherboard(new MotherboardDTO(request)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/new/power-supply")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<PowerSupplyDTO> savePowerSupply(
            @Valid @RequestBody PowerSupplyCreationRequest request
    ) {
        return ResponseEntity.ok(productService.savePowerSupply(new PowerSupplyDTO(request)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/new/ram")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<RAMDTO> saveRam(
            @Valid @RequestBody RAMCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveRam(new RAMDTO(request)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/new/external-hard-drive")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ExternalHardDriveDTO> saveExternalHardDrive(
            @Valid @RequestBody ExternalHardDriveCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveExternalHardDrive(new ExternalHardDriveDTO(request)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/new/headset")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HeadsetDTO> saveHeadset(
            @Valid @RequestBody HeadsetCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveHeadset(new HeadsetDTO(request)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/new/keyboard")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<KeyboardDTO> saveKeyboard(
            @Valid @RequestBody KeyboardCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveKeyboard(new KeyboardDTO(request)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/new/mouse")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MouseDTO> saveMouse(
            @Valid @RequestBody MouseCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveMouse(new MouseDTO(request)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/new/speaker")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<SpeakerDTO> saveSpeaker(
            @Valid @RequestBody SpeakerCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveSpeaker(new SpeakerDTO(request)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping(value = "/edit-price")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> editPrice(
            @Valid @RequestBody EditPriceRequest request
    ) {
        productService.updatePriceById(request.productId(), request.newPrice());
        return ResponseEntity.ok().body("Product price updated successfully");
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping(value = "/reduce")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> reduceCount(
            @Valid @RequestBody UpdateProductCountRequest request
    ) {
        productService.reduceCountById(request.productId(), request.countToBeChanged());
        return ResponseEntity.ok().body("Product count reduced successfully");
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping(value = "/increase")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> increaseCount(
            @Valid @RequestBody UpdateProductCountRequest request
    ) {
        productService.increaseCountById(request.productId(), request.countToBeChanged());
        return ResponseEntity.ok().body("Product count increased successfully");
    }

    @GetMapping("/case")
    public ResponseEntity<List<CaseDTO>> getAllCases() {
        return ResponseEntity.ok().body(productService.getAllCases());
    }

    @GetMapping("/cooler")
    public ResponseEntity<List<CoolerDTO>> getAllCoolers() {
        return ResponseEntity.ok().body(productService.getAllCoolers());
    }

    @GetMapping("/cpu-cooler")
    public ResponseEntity<List<CPUCoolerDTO>> getAllCpuCoolers() {
        return ResponseEntity.ok().body(productService.getAllCpuCoolers());
    }

    @GetMapping("/cpu")
    public ResponseEntity<List<CPUDTO>> getAllCpus() {
        return ResponseEntity.ok().body(productService.getAllCpus());
    }

    @GetMapping("/gpu")
    public ResponseEntity<List<GPUDTO>> getAllGpus() {
        return ResponseEntity.ok().body(productService.getAllGpus());
    }

    @GetMapping("/internal-hard-drive")
    public ResponseEntity<List<InternalHardDriveDTO>> getAllInternalHardDrives() {
        return ResponseEntity.ok().body(productService.getAllInternalHardDrives());
    }

    @GetMapping("/motherboard")
    public ResponseEntity<List<MotherboardDTO>> getAllMotherboards() {
        return ResponseEntity.ok().body(productService.getAllMotherboards());
    }

    @GetMapping("/power-supply")
    public ResponseEntity<List<PowerSupplyDTO>> getAllPowerSupplies() {
        return ResponseEntity.ok().body(productService.getAllPowerSupplies());
    }

    @GetMapping("/ram")
    public ResponseEntity<List<RAMDTO>> getAllRams() {
        return ResponseEntity.ok().body(productService.getAllRams());
    }

    @GetMapping("/external-hard-drive")
    public ResponseEntity<List<ExternalHardDriveDTO>> getAllExternalHardDrives() {
        return ResponseEntity.ok().body(productService.getAllExternalHardDrives());
    }

    @GetMapping("/headset")
    public ResponseEntity<List<HeadsetDTO>> getAllHeadsets() {
        return ResponseEntity.ok().body(productService.getAllHeadsets());
    }

    @GetMapping("/keyboard")
    public ResponseEntity<List<KeyboardDTO>> getAllKeyboards() {
        return ResponseEntity.ok().body(productService.getAllKeyboards());
    }

    @GetMapping("/monitor")
    public ResponseEntity<List<MonitorDTO>> getAllMonitors() {
        return ResponseEntity.ok().body(productService.getAllMonitors());
    }

    @GetMapping("/mouse")
    public ResponseEntity<List<MouseDTO>> getAllMice() {
        return ResponseEntity.ok().body(productService.getAllMice());
    }

    @GetMapping("/speaker")
    public ResponseEntity<List<SpeakerDTO>> getAllSpeakers() {
        return ResponseEntity.ok().body(productService.getAllSpeakers());
    }
}