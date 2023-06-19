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
import org.springframework.web.bind.annotation.*;

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
    @PutMapping(value = "/edit-price/{componentType}")
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