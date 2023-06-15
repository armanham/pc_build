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
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/filter")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class FilterController {

    FilterService filterService;

    @GetMapping(value = "/all")
    public List<ProductDTO> filterAllProductsByNameAndPrice(
            @RequestBody ProductFilterRequest request
    ) {
        return filterService.filterAllProductsByNameAndPrice(
                new ProductFilterDTO(request)
        );
    }

    @GetMapping(value = "/case")
    public List<CaseDTO> filterCasesBasedOnSpecification(
            @RequestBody CaseFilterRequest request
    ) {
        return filterService.filterAllCasesBasedOnSpecification(
                new CaseFilterDTO(request)
        );
    }

    @GetMapping(value = "/cooler")
    public List<CoolerDTO> filterCoolersBasedOnSpecification(
            @RequestBody CoolerFilterRequest request
    ) {
        return filterService.filterAllCoolersBasedOnSpecification(
                new CoolerFilterDTO(request)
        );
    }

    @GetMapping(value = "/cpu")
    public List<CPUDTO> filterCpusBasedOnSpecification(
            @RequestBody CPUFilterRequest request
    ) {
        return filterService.filterAllCpusBasedOnSpecification(
                new CPUFilterDTO(request)
        );
    }

    @GetMapping(value = "/cpuCooler")
    public List<CPUCoolerDTO> filterCpuCoolersBasedOnSpecification(
            @RequestBody CPUCoolerFilterRequest request
    ) {
        return filterService.filterAllCpuCoolersBasedOnSpecification(
                new CPUCoolerFilterDTO(request)
        );
    }

    @GetMapping(value = "/gpu")
    public List<GPUDTO> filterGpusBasedOnSpecification(
            @RequestBody GPUFilterRequest request
    ) {
        return filterService.filterAllGpusBasedOnSpecification(
                new GPUFilterDTO(request)
        );
    }

    @GetMapping(value = "/internalHardDrive")
    public List<InternalHardDriveDTO> filterInternalHardDrivesBasedOnSpecification(
            @RequestBody InternalHardDriveFilterRequest request
    ) {
        return filterService.filterAllInternalHardDrivesBasedOnSpecification(
                new InternalHardDriveFilterDTO(request)
        );
    }

    @GetMapping(value = "/motherboard")
    public List<MotherboardDTO> filterMotherboardsBasedOnSpecification(
            @RequestBody MotherboardFilterRequest request
    ) {
        return filterService.filterAllMotherboardsBasedOnSpecification(
                new MotherboardFilterDTO(request)
        );
    }

    @GetMapping(value = "/powerSupply")
    public List<PowerSupplyDTO> filterPowerSuppliesBasedOnSpecification(
            @RequestBody PowerSupplyFilterRequest request
    ) {
        return filterService.filterAllPowerSuppliesBasedOnSpecification(
                new PowerSupplyFilterDTO(request)
        );
    }

    @GetMapping(value = "/ram")
    public List<RAMDTO> filterRamsBasedOnSpecification(
            @RequestBody RAMFilterRequest request
    ) {
        return filterService.filterAllRamsBasedOnSpecification(
                new RAMFilterDTO(request)
        );
    }

    @GetMapping(value = "/externalHardDrive")
    public List<ExternalHardDriveDTO> filterExternalHardDrivesBasedOnSpecification(
            @RequestBody ExternalHardDriveFilterRequest request
    ) {
        return filterService.filterAllExternalHardDrivesBasedOnSpecification(
                new ExternalHardDriveFilterDTO(request)
        );
    }

    @GetMapping(value = "/headset")
    public List<HeadsetDTO> filterHeadsetsBasedOnSpecification(
            @RequestBody HeadsetFilterRequest request
    ) {
        return filterService.filterAllHeadsetsBasedOnSpecification(
                new HeadsetFilterDTO(request)
        );
    }

    @GetMapping(value = "/keyboard")
    public List<KeyboardDTO> filterKeyboardsBasedOnSpecification(
            @RequestBody KeyboardFilterRequest request
    ) {
        return filterService.filterAllKeyboardsBasedOnSpecification(
                new KeyboardFilterDTO(request)
        );
    }

    @GetMapping(value = "/monitor")
    public List<MonitorDTO> filterMonitorsBasedOnSpecification(
            @RequestBody MonitorFilterRequest request
    ) {
        return filterService.filterAllMonitorsBasedOnSpecification(
                new MonitorFilterDTO(request)
        );
    }

    @GetMapping(value = "/mouse")
    public List<MouseDTO> filterMiceBasedOnSpecification(
            @RequestBody MouseFilterRequest request
    ) {
        return filterService.filterAllMiceBasedOnSpecification(
                new MouseFilterDTO(request)
        );
    }

    @GetMapping(value = "/speaker")
    public List<SpeakerDTO> filterSpeakersBasedOnSpecification(
            @RequestBody SpeakerFilterRequest request
    ) {
        return filterService.filterAllSpeakersBasedOnSpecification(
                new SpeakerFilterDTO(request)
        );
    }

    @GetMapping(value = "/{term}")
    public List<ProductDTO> filterAllByTerm(
            @PathVariable("term") String term
    ) {
        return filterService.findAllProductsBasedOnTerm(term);
    }
}