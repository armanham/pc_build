package com.bdg.pc_build.product.controller;

import com.bdg.pc_build.product.model.dto.main.*;
import com.bdg.pc_build.product.model.dto.peripheral.*;
import com.bdg.pc_build.product.model.request.creation.main.*;
import com.bdg.pc_build.product.model.request.creation.peripheral.*;
import com.bdg.pc_build.product.model.request.update.EditPriceRequest;
import com.bdg.pc_build.product.model.request.update.ReduceCountRequest;
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
@SecurityRequirement(name = "bearerAuth")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/new/monitor")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MonitorDTO> saveMonitor(
            @Valid @RequestBody MonitorCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveMonitor(new MonitorDTO(request)));
    }

    @PostMapping("/new/case")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CaseDTO> saveCase(
            @Valid @RequestBody CaseCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveCase(new CaseDTO(request)));
    }

    @PostMapping("/new/cooler")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CoolerDTO> saveCooler(
            @Valid @RequestBody CoolerCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveCooler(new CoolerDTO(request)));
    }

    @PostMapping("/new/cpu-cooler")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CPUCoolerDTO> saveCPUCooler(
            @Valid @RequestBody CPUCoolerCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveCpuCooler(new CPUCoolerDTO(request)));
    }

    @PostMapping("/new/cpu")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CPUDTO> saveCPU(
            @Valid @RequestBody CPUCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveCPU(new CPUDTO(request)));
    }

    @PostMapping("/new/gpu")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<GPUDTO> saveGPU(
            @Valid @RequestBody GPUCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveGPU(new GPUDTO(request)));
    }

    @PostMapping("/new/internal-hard-drive")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<InternalHardDriveDTO> saveInternalHardDrive(
            @Valid @RequestBody InternalHardDriveCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveInternalHardDrive(new InternalHardDriveDTO(request)));
    }

    @PostMapping("/new/motherboard")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MotherboardDTO> saveMotherboard(
            @Valid @RequestBody MotherboardCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveMotherboard(new MotherboardDTO(request)));
    }

    @PostMapping("/new/power-supply")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<PowerSupplyDTO> savePowerSupply(
            @Valid @RequestBody PowerSupplyCreationRequest request
    ) {
        return ResponseEntity.ok(productService.savePowerSupply(new PowerSupplyDTO(request)));
    }

    @PostMapping("/new/ram")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<RAMDTO> saveRAM(
            @Valid @RequestBody RAMCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveRAM(new RAMDTO(request)));
    }

    @PostMapping("/new/external-hard-drive")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ExternalHardDriveDTO> saveExternalHardDrive(
            @Valid @RequestBody ExternalHardDriveCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveExternalHardDrive(new ExternalHardDriveDTO(request)));
    }

    @PostMapping("/new/headset")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HeadsetDTO> saveHeadset(
            @Valid @RequestBody HeadsetCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveHeadset(new HeadsetDTO(request)));
    }

    @PostMapping("/new/keyboard")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<KeyboardDTO> saveKeyboard(
            @Valid @RequestBody KeyboardCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveKeyboard(new KeyboardDTO(request)));
    }

    @PostMapping("/new/mouse")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MouseDTO> saveMouse(
            @Valid @RequestBody MouseCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveMouse(new MouseDTO(request)));
    }

    @PostMapping("/new/speaker")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<SpeakerDTO> saveSpeaker(
            @Valid @RequestBody SpeakerCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveSpeaker(new SpeakerDTO(request)));
    }

    @PutMapping(value = "/edit-price")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> editPrice(
            @Valid @RequestBody EditPriceRequest request
    ) {
        productService.updatePriceById(request.productId(), request.newPrice());
        return ResponseEntity.ok().body("Product price updated successfully");
    }

    @PutMapping(value = "/reduce")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> reduceCount(
            @Valid @RequestBody ReduceCountRequest request
    ) {
        productService.reduceCountById(request.productId(), request.countToBeReduced());
        return ResponseEntity.ok().body("Product count reduced successfully");
    }

    @GetMapping("/case")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<CaseDTO>> getAllCases() {
        return ResponseEntity.ok().body(productService.getAllCases());
    }

    @GetMapping("/cooler")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<CoolerDTO>> getAllCoolers() {
        return ResponseEntity.ok().body(productService.getAllCoolers());
    }

    @GetMapping("/cpu-cooler")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<CPUCoolerDTO>> getAllCpuCoolers() {
        return ResponseEntity.ok().body(productService.getAllCpuCoolers());
    }

    @GetMapping("/cpu")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<CPUDTO>> getAllCpus() {
        return ResponseEntity.ok().body(productService.getAllCpus());
    }

    @GetMapping("/gpu")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<GPUDTO>> getAllGpus() {
        return ResponseEntity.ok().body(productService.getAllGpus());
    }

    @GetMapping("/internal-hard-drive")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<InternalHardDriveDTO>> getAllInternalHardDrives() {
        return ResponseEntity.ok().body(productService.getAllInternalHardDrives());
    }

    @GetMapping("/motherboard")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<MotherboardDTO>> getAllMotherboards() {
        return ResponseEntity.ok().body(productService.getAllMotherboards());
    }

    @GetMapping("/power-supply")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<PowerSupplyDTO>> getAllPowerSupplies() {
        return ResponseEntity.ok().body(productService.getAllPowerSupplies());
    }

    @GetMapping("/ram")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<RAMDTO>> getAllRams() {
        return ResponseEntity.ok().body(productService.getAllRams());
    }

    @GetMapping("/external-hard-drive")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<ExternalHardDriveDTO>> getAllExternalHardDrives() {
        return ResponseEntity.ok().body(productService.getAllExternalHardDrives());
    }

    @GetMapping("/headset")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<HeadsetDTO>> getAllHeadsets() {
        return ResponseEntity.ok().body(productService.getAllHeadsets());
    }

    @GetMapping("/keyboard")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<KeyboardDTO>> getAllKeyboards() {
        return ResponseEntity.ok().body(productService.getAllKeyboards());
    }

    @GetMapping("/monitor")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<MonitorDTO>> getAllMonitors() {
        return ResponseEntity.ok().body(productService.getAllMonitors());
    }

    @GetMapping("/mouse")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<MouseDTO>> getAllMice() {
        return ResponseEntity.ok().body(productService.getAllMice());
    }

    @GetMapping("/speaker")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<SpeakerDTO>> getAllSpeakers() {
        return ResponseEntity.ok().body(productService.getAllSpeakers());
    }
}