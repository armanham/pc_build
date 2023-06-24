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

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/product")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProductController {

    ProductService productService;

    @PostMapping("/new/monitor")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MonitorDTO> saveMonitor(
            @Valid @RequestBody MonitorCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveMonitor(MonitorDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/case")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CaseDTO> saveCase(
            @Valid @RequestBody CaseCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveCase(CaseDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/cooler")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CoolerDTO> saveCooler(
            @Valid @RequestBody CoolerCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveCooler(CoolerDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/cpu-cooler")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CPUCoolerDTO> saveCPUCooler(
            @Valid @RequestBody CPUCoolerCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveCpuCooler(CPUCoolerDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/cpu")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CPUDTO> saveCPU(
            @Valid @RequestBody CPUCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveCPU(CPUDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/gpu")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<GPUDTO> saveGPU(
            @Valid @RequestBody GPUCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveGPU(GPUDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/internal-hard-drive")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<InternalHardDriveDTO> saveInternalHardDrive(
            @Valid @RequestBody InternalHardDriveCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveInternalHardDrive(InternalHardDriveDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/motherboard")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MotherboardDTO> saveMotherboard(
            @Valid @RequestBody MotherboardCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveMotherboard(MotherboardDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/power-supply")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<PowerSupplyDTO> savePowerSupply(
            @Valid @RequestBody PowerSupplyCreationRequest request
    ) {
        return ResponseEntity.ok(productService.savePowerSupply(PowerSupplyDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/ram")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<RAMDTO> saveRAM(
            @Valid @RequestBody RAMCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveRAM(RAMDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/external-hard-drive")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ExternalHardDriveDTO> saveExternalHardDrive(
            @Valid @RequestBody ExternalHardDriveCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveExternalHardDrive(ExternalHardDriveDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/headset")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HeadsetDTO> saveHeadset(
            @Valid @RequestBody HeadsetCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveHeadset(HeadsetDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/keyboard")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<KeyboardDTO> saveKeyboard(
            @Valid @RequestBody KeyboardCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveKeyboard(KeyboardDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/mouse")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MouseDTO> saveMouse(
            @Valid @RequestBody MouseCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveMouse(MouseDTO.initDTOFromRequest(request)));
    }

    @PostMapping("/new/speaker")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<SpeakerDTO> saveSpeaker(
            @Valid @RequestBody SpeakerCreationRequest request
    ) {
        return ResponseEntity.ok(productService.saveSpeaker(SpeakerDTO.initDTOFromRequest(request)));
    }

    @PutMapping(value = "/edit-price/{component-type}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> editPrice(
            @PathVariable("component-type") String componentType,
            @RequestBody EditPriceRequest request
    ) {
        switch (componentType) {
            case "case" ->
                    ResponseEntity.ok().body(productService.updateCasePriceById(request.productId(), request.newPrice()));
            case "cooler" ->
                    ResponseEntity.ok().body(productService.updateCoolerPriceById(request.productId(), request.newPrice()));
            case "cpu-cooler" ->
                    ResponseEntity.ok().body(productService.updateCpuCoolerPriceById(request.productId(), request.newPrice()));
            case "cpu" ->
                    ResponseEntity.ok().body(productService.updateCpuPriceById(request.productId(), request.newPrice()));
            case "gpu" ->
                    ResponseEntity.ok().body(productService.updateGpuPriceById(request.productId(), request.newPrice()));
            case "internal-hard-drive" ->
                    ResponseEntity.ok().body(productService.updateInternalHardDrivePriceById(request.productId(), request.newPrice()));
            case "motherboard" ->
                    ResponseEntity.ok().body(productService.updateMotherboardPriceById(request.productId(), request.newPrice()));
            case "power-supply" ->
                    ResponseEntity.ok().body(productService.updatePowerSupplyPriceById(request.productId(), request.newPrice()));
            case "ram" ->
                    ResponseEntity.ok().body(productService.updateRamPriceById(request.productId(), request.newPrice()));
            case "external-hard-drive" ->
                    ResponseEntity.ok().body(productService.updateExternalHardDrivePriceById(request.productId(), request.newPrice()));
            case "headset" ->
                    ResponseEntity.ok().body(productService.updateHeadsetPriceById(request.productId(), request.newPrice()));
            case "keyboard" ->
                    ResponseEntity.ok().body(productService.updateKeyboardPriceById(request.productId(), request.newPrice()));
            case "monitor" ->
                    ResponseEntity.ok().body(productService.updateMonitorPriceById(request.productId(), request.newPrice()));
            case "mouse" ->
                    ResponseEntity.ok().body(productService.updateMousePriceById(request.productId(), request.newPrice()));
            case "speaker" ->
                    ResponseEntity.ok().body(productService.updateSpeakerPriceById(request.productId(), request.newPrice()));
            default -> ResponseEntity.badRequest().body("Invalid component type");
        }
        return ResponseEntity.ok().body("");
    }

    @PutMapping(value = "/reduce/{component-type}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> reduceCount(
            @PathVariable("component-type") String componentType,
            @RequestBody ReduceCountRequest request
    ) {
        switch (componentType) {
            case "case" ->
                    ResponseEntity.ok().body(productService.reduceCaseCountById(request.productId(), request.countToBeReduced()));
            case "cooler" ->
                    ResponseEntity.ok().body(productService.reduceCoolerCountById(request.productId(), request.countToBeReduced()));
            case "cpu-cooler" ->
                    ResponseEntity.ok().body(productService.reduceCPUCoolerCountById(request.productId(), request.countToBeReduced()));
            case "cpu" ->
                    ResponseEntity.ok().body(productService.reduceCPUCountById(request.productId(), request.countToBeReduced()));
            case "gpu" ->
                    ResponseEntity.ok().body(productService.reduceGPUCountById(request.productId(), request.countToBeReduced()));
            case "internal-hard-drive" ->
                    ResponseEntity.ok().body(productService.reduceInternalHardDriveCountById(request.productId(), request.countToBeReduced()));
            case "motherboard" ->
                    ResponseEntity.ok().body(productService.reduceMotherboardCountById(request.productId(), request.countToBeReduced()));
            case "power-supply" ->
                    ResponseEntity.ok().body(productService.reducePowerSupplyCountById(request.productId(), request.countToBeReduced()));
            case "ram" ->
                    ResponseEntity.ok().body(productService.reduceRAMCountById(request.productId(), request.countToBeReduced()));
            case "external-hard-drive" ->
                    ResponseEntity.ok().body(productService.reduceExternalHardDriveCountById(request.productId(), request.countToBeReduced()));
            case "headset" ->
                    ResponseEntity.ok().body(productService.reduceHeadsetCountById(request.productId(), request.countToBeReduced()));
            case "keyboard" ->
                    ResponseEntity.ok().body(productService.reduceKeyboardCountById(request.productId(), request.countToBeReduced()));
            case "monitor" ->
                    ResponseEntity.ok().body(productService.reduceMonitorCountById(request.productId(), request.countToBeReduced()));
            case "mouse" ->
                    ResponseEntity.ok().body(productService.reduceMouseCountById(request.productId(), request.countToBeReduced()));
            case "speaker" ->
                    ResponseEntity.ok().body(productService.reduceSpeakerCountById(request.productId(), request.countToBeReduced()));
            default -> ResponseEntity.badRequest().body("Invalid component type");
        }
        return ResponseEntity.ok().body("");
    }

    @GetMapping("/case")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public List<CaseDTO> getAllCases() {
        return productService.getAllCases();
    }

    @GetMapping("/cooler")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public List<CoolerDTO> getAllCoolers() {
        return productService.getAllCoolers();
    }

    @GetMapping("/cpu-cooler")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public List<CPUCoolerDTO> getAllCpuCoolers() {
        return productService.getAllCpuCoolers();
    }

    @GetMapping("/cpu")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public List<CPUDTO> getAllCpus() {
        return productService.getAllCpus();
    }

    @GetMapping("/gpu")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public List<GPUDTO> getAllGpus() {
        return productService.getAllGpus();
    }

    @GetMapping("/internal-hard-drive")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public List<InternalHardDriveDTO> getAllInternalHardDrives() {
        return productService.getAllInternalHardDrives();
    }

    @GetMapping("/motherboard")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public List<MotherboardDTO> getAllMotherboards() {
        return productService.getAllMotherboards();
    }

    @GetMapping("/power-supply")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public List<PowerSupplyDTO> getAllPowerSupplies() {
        return productService.getAllPowerSupplies();
    }

    @GetMapping("/ram")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public List<RAMDTO> getAllRams() {
        return productService.getAllRams();
    }

    @GetMapping("/external-hard-drive")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public List<ExternalHardDriveDTO> getAllExternalHardDrives() {
        return productService.getAllExternalHardDrives();
    }

    @GetMapping("/headset")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public List<HeadsetDTO> getAllHeadsets() {
        return productService.getAllHeadsets();
    }

    @GetMapping("/keyboard")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public List<KeyboardDTO> getAllKeyboards() {
        return productService.getAllKeyboards();
    }

    @GetMapping("/monitor")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public List<MonitorDTO> getAllMonitors() {
        return productService.getAllMonitors();
    }

    @GetMapping("/mouse")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public List<MouseDTO> getAllMice() {
        return productService.getAllMice();
    }

    @GetMapping("/speaker")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public List<SpeakerDTO> getAllSpeakers() {
        return productService.getAllSpeakers();
    }
}