package com.bdg.pc_build.filter.controller;

import com.bdg.pc_build.filter.model.dto.ProductFilterDTO;
import com.bdg.pc_build.filter.model.dto.main_component.*;
import com.bdg.pc_build.filter.model.dto.peripheral.*;
import com.bdg.pc_build.filter.model.request.ProductFilterRequest;
import com.bdg.pc_build.filter.model.request.main_component.*;
import com.bdg.pc_build.filter.model.request.peripheral.*;
import com.bdg.pc_build.filter.service.FilterService;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.dto.main_component.*;
import com.bdg.pc_build.product.model.dto.peripheral.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/filter")
@RequiredArgsConstructor
public class FilterController {

    private final FilterService filterService;

    @PostMapping(value = "/all")
    public ResponseEntity<List<ProductDTO>> filterAllProductsByNameAndPrice(
            @RequestBody ProductFilterRequest request
    ) {
        return ResponseEntity.ok().body(filterService.filterAllProductsByNameAndPrice(new ProductFilterDTO(request)));
    }

    @PostMapping(value = "/case")
    public ResponseEntity<List<CaseDTO>> filterCasesBasedOnSpecification(
            @RequestBody CaseFilterRequest request
    ) {
        return ResponseEntity.ok().body(filterService.filterAllCasesBasedOnSpecification(new CaseFilterDTO(request)));
    }

    @PostMapping(value = "/cooler")
    public ResponseEntity<List<CoolerDTO>> filterCoolersBasedOnSpecification(
            @RequestBody CoolerFilterRequest request
    ) {
        return ResponseEntity.ok().body(filterService.filterAllCoolersBasedOnSpecification(new CoolerFilterDTO(request)));
    }

    @PostMapping(value = "/cpu")
    public ResponseEntity<List<CPUDTO>> filterCpusBasedOnSpecification(
            @RequestBody CPUFilterRequest request
    ) {
        return ResponseEntity.ok().body(filterService.filterAllCpusBasedOnSpecification(new CPUFilterDTO(request)));
    }

    @PostMapping(value = "/cpu-cooler")
    public ResponseEntity<List<CPUCoolerDTO>> filterCpuCoolersBasedOnSpecification(
            @RequestBody CPUCoolerFilterRequest request
    ) {
        return ResponseEntity.ok().body(filterService.filterAllCpuCoolersBasedOnSpecification(new CPUCoolerFilterDTO(request)));
    }

    @PostMapping(value = "/gpu")
    public ResponseEntity<List<GPUDTO>> filterGpusBasedOnSpecification(
            @RequestBody GPUFilterRequest request
    ) {
        return ResponseEntity.ok().body(filterService.filterAllGpusBasedOnSpecification(new GPUFilterDTO(request)));
    }

    @PostMapping(value = "/internal-hard-drive")
    public ResponseEntity<List<InternalHardDriveDTO>> filterInternalHardDrivesBasedOnSpecification(
            @RequestBody InternalHardDriveFilterRequest request
    ) {
        return ResponseEntity.ok().body(filterService.filterAllInternalHardDrivesBasedOnSpecification(new InternalHardDriveFilterDTO(request)));
    }

    @PostMapping(value = "/motherboard")
    public ResponseEntity<List<MotherboardDTO>> filterMotherboardsBasedOnSpecification(
            @RequestBody MotherboardFilterRequest request
    ) {
        return ResponseEntity.ok().body(filterService.filterAllMotherboardsBasedOnSpecification(new MotherboardFilterDTO(request)));
    }

    @PostMapping(value = "/power-supply")
    public ResponseEntity<List<PowerSupplyDTO>> filterPowerSuppliesBasedOnSpecification(
            @RequestBody PowerSupplyFilterRequest request
    ) {
        return ResponseEntity.ok().body(filterService.filterAllPowerSuppliesBasedOnSpecification(new PowerSupplyFilterDTO(request)));
    }

    @PostMapping(value = "/ram")
    public ResponseEntity<List<RAMDTO>> filterRamsBasedOnSpecification(
            @RequestBody RAMFilterRequest request
    ) {
        return ResponseEntity.ok().body(filterService.filterAllRamsBasedOnSpecification(new RAMFilterDTO(request)));
    }

    @PostMapping(value = "/external-hard-drive")
    public ResponseEntity<List<ExternalHardDriveDTO>> filterExternalHardDrivesBasedOnSpecification(
            @RequestBody ExternalHardDriveFilterRequest request
    ) {
        return ResponseEntity.ok().body(filterService.filterAllExternalHardDrivesBasedOnSpecification(new ExternalHardDriveFilterDTO(request)));
    }

    @PostMapping(value = "/headset")
    public ResponseEntity<List<HeadsetDTO>> filterHeadsetsBasedOnSpecification(
            @RequestBody HeadsetFilterRequest request
    ) {
        return ResponseEntity.ok().body(filterService.filterAllHeadsetsBasedOnSpecification(new HeadsetFilterDTO(request)));
    }

    @PostMapping(value = "/keyboard")
    public ResponseEntity<List<KeyboardDTO>> filterKeyboardsBasedOnSpecification(
            @RequestBody KeyboardFilterRequest request
    ) {
        return ResponseEntity.ok().body(filterService.filterAllKeyboardsBasedOnSpecification(new KeyboardFilterDTO(request)));
    }

    @PostMapping(value = "/monitor")
    public ResponseEntity<List<MonitorDTO>> filterMonitorsBasedOnSpecification(
            @RequestBody MonitorFilterRequest request
    ) {
        return ResponseEntity.ok().body(filterService.filterAllMonitorsBasedOnSpecification(new MonitorFilterDTO(request)));
    }

    @PostMapping(value = "/mouse")
    public ResponseEntity<List<MouseDTO>> filterMiceBasedOnSpecification(
            @RequestBody MouseFilterRequest request
    ) {
        return ResponseEntity.ok().body(filterService.filterAllMiceBasedOnSpecification(new MouseFilterDTO(request)));
    }

    @PostMapping(value = "/speaker")
    public ResponseEntity<List<SpeakerDTO>> filterSpeakersBasedOnSpecification(
            @RequestBody SpeakerFilterRequest request
    ) {
        return ResponseEntity.ok().body(filterService.filterAllSpeakersBasedOnSpecification(new SpeakerFilterDTO(request)));
    }

    @GetMapping(value = "/{term}")
    public ResponseEntity<List<ProductDTO>> filterAllByTerm(
            @PathVariable String term
    ) {
        return ResponseEntity.ok().body(filterService.findAllProductsBasedOnTerm(term));
    }
}