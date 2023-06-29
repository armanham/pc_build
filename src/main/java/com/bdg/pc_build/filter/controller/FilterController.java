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

    @GetMapping(value = "/all")
    public ResponseEntity<List<ProductDTO>> filterAllProductsByNameAndPrice(
            @RequestBody ProductFilterRequest request
    ) {
        return ResponseEntity.ok().body(filterService.filterAllProductsByNameAndPrice(new ProductFilterDTO(request)));
    }

    @GetMapping(value = "/case")
    public ResponseEntity<List<CaseDTO>> filterCasesBasedOnSpecification(
            @RequestBody CaseFilterRequest request
    ) {
        return ResponseEntity.ok().body(filterService.filterAllCasesBasedOnSpecification(new CaseFilterDTO(request)));
    }

    @GetMapping(value = "/cooler")
    public ResponseEntity<List<CoolerDTO>> filterCoolersBasedOnSpecification(
            @RequestBody CoolerFilterRequest request
    ) {
        return ResponseEntity.ok().body(filterService.filterAllCoolersBasedOnSpecification(new CoolerFilterDTO(request)));
    }

    @GetMapping(value = "/cpu")
    public ResponseEntity<List<CPUDTO>> filterCpusBasedOnSpecification(
            @RequestBody CPUFilterRequest request
    ) {
        return ResponseEntity.ok().body(filterService.filterAllCpusBasedOnSpecification(new CPUFilterDTO(request)));
    }

    @GetMapping(value = "/cpu-cooler")
    public ResponseEntity<List<CPUCoolerDTO>> filterCpuCoolersBasedOnSpecification(
            @RequestBody CPUCoolerFilterRequest request
    ) {
        return ResponseEntity.ok().body(filterService.filterAllCpuCoolersBasedOnSpecification(new CPUCoolerFilterDTO(request)));
    }

    @GetMapping(value = "/gpu")
    public ResponseEntity<List<GPUDTO>> filterGpusBasedOnSpecification(
            @RequestBody GPUFilterRequest request
    ) {
        return ResponseEntity.ok().body(filterService.filterAllGpusBasedOnSpecification(new GPUFilterDTO(request)));
    }

    @GetMapping(value = "/internal-hard-drive")
    public ResponseEntity<List<InternalHardDriveDTO>> filterInternalHardDrivesBasedOnSpecification(
            @RequestBody InternalHardDriveFilterRequest request
    ) {
        return ResponseEntity.ok().body(filterService.filterAllInternalHardDrivesBasedOnSpecification(new InternalHardDriveFilterDTO(request)));
    }

    @GetMapping(value = "/motherboard")
    public ResponseEntity<List<MotherboardDTO>> filterMotherboardsBasedOnSpecification(
            @RequestBody MotherboardFilterRequest request
    ) {
        return ResponseEntity.ok().body(filterService.filterAllMotherboardsBasedOnSpecification(new MotherboardFilterDTO(request)));
    }

    @GetMapping(value = "/power-supply")
    public ResponseEntity<List<PowerSupplyDTO>> filterPowerSuppliesBasedOnSpecification(
            @RequestBody PowerSupplyFilterRequest request
    ) {
        return ResponseEntity.ok().body(filterService.filterAllPowerSuppliesBasedOnSpecification(new PowerSupplyFilterDTO(request)));
    }

    @GetMapping(value = "/ram")
    public ResponseEntity<List<RAMDTO>> filterRamsBasedOnSpecification(
            @RequestBody RAMFilterRequest request
    ) {
        return ResponseEntity.ok().body(filterService.filterAllRamsBasedOnSpecification(new RAMFilterDTO(request)));
    }

    @GetMapping(value = "/external-hard-drive")
    public ResponseEntity<List<ExternalHardDriveDTO>> filterExternalHardDrivesBasedOnSpecification(
            @RequestBody ExternalHardDriveFilterRequest request
    ) {
        return ResponseEntity.ok().body(filterService.filterAllExternalHardDrivesBasedOnSpecification(new ExternalHardDriveFilterDTO(request)));
    }

    @GetMapping(value = "/headset")
    public ResponseEntity<List<HeadsetDTO>> filterHeadsetsBasedOnSpecification(
            @RequestBody HeadsetFilterRequest request
    ) {
        return ResponseEntity.ok().body(filterService.filterAllHeadsetsBasedOnSpecification(new HeadsetFilterDTO(request)));
    }

    @GetMapping(value = "/keyboard")
    public ResponseEntity<List<KeyboardDTO>> filterKeyboardsBasedOnSpecification(
            @RequestBody KeyboardFilterRequest request
    ) {
        return ResponseEntity.ok().body(filterService.filterAllKeyboardsBasedOnSpecification(new KeyboardFilterDTO(request)));
    }

    @GetMapping(value = "/monitor")
    public ResponseEntity<List<MonitorDTO>> filterMonitorsBasedOnSpecification(
            @RequestBody MonitorFilterRequest request
    ) {
        return ResponseEntity.ok().body(filterService.filterAllMonitorsBasedOnSpecification(new MonitorFilterDTO(request)));
    }

    @GetMapping(value = "/mouse")
    public ResponseEntity<List<MouseDTO>> filterMiceBasedOnSpecification(
            @RequestBody MouseFilterRequest request
    ) {
        return ResponseEntity.ok().body(filterService.filterAllMiceBasedOnSpecification(new MouseFilterDTO(request)));
    }

    @GetMapping(value = "/speaker")
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