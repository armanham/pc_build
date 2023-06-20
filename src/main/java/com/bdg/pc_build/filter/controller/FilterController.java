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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/filter")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class FilterController {

    FilterService filterService;

    @GetMapping(value = "/all")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public List<ProductDTO> filterAllProductsByNameAndPrice(
            @RequestBody ProductFilterRequest request
    ) {
        return filterService.filterAllProductsByNameAndPrice(
                new ProductFilterDTO(request)
        );
    }

    @GetMapping(value = "/case")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public List<CaseDTO> filterCasesBasedOnSpecification(
            @RequestBody CaseFilterRequest request
    ) {
        return filterService.filterAllCasesBasedOnSpecification(
                new CaseFilterDTO(request)
        );
    }

    @GetMapping(value = "/cooler")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public List<CoolerDTO> filterCoolersBasedOnSpecification(
            @RequestBody CoolerFilterRequest request
    ) {
        return filterService.filterAllCoolersBasedOnSpecification(
                new CoolerFilterDTO(request)
        );
    }

    @GetMapping(value = "/cpu")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public List<CPUDTO> filterCpusBasedOnSpecification(
            @RequestBody CPUFilterRequest request
    ) {
        return filterService.filterAllCpusBasedOnSpecification(
                new CPUFilterDTO(request)
        );
    }

    @GetMapping(value = "/cpu-cooler")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public List<CPUCoolerDTO> filterCpuCoolersBasedOnSpecification(
            @RequestBody CPUCoolerFilterRequest request
    ) {
        return filterService.filterAllCpuCoolersBasedOnSpecification(
                new CPUCoolerFilterDTO(request)
        );
    }

    @GetMapping(value = "/gpu")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public List<GPUDTO> filterGpusBasedOnSpecification(
            @RequestBody GPUFilterRequest request
    ) {
        return filterService.filterAllGpusBasedOnSpecification(
                new GPUFilterDTO(request)
        );
    }

    @GetMapping(value = "/internal-hard-drive")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public List<InternalHardDriveDTO> filterInternalHardDrivesBasedOnSpecification(
            @RequestBody InternalHardDriveFilterRequest request
    ) {
        return filterService.filterAllInternalHardDrivesBasedOnSpecification(
                new InternalHardDriveFilterDTO(request)
        );
    }

    @GetMapping(value = "/motherboard")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public List<MotherboardDTO> filterMotherboardsBasedOnSpecification(
            @RequestBody MotherboardFilterRequest request
    ) {
        return filterService.filterAllMotherboardsBasedOnSpecification(
                new MotherboardFilterDTO(request)
        );
    }

    @GetMapping(value = "/power-supply")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public List<PowerSupplyDTO> filterPowerSuppliesBasedOnSpecification(
            @RequestBody PowerSupplyFilterRequest request
    ) {
        return filterService.filterAllPowerSuppliesBasedOnSpecification(
                new PowerSupplyFilterDTO(request)
        );
    }

    @GetMapping(value = "/ram")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public List<RAMDTO> filterRamsBasedOnSpecification(
            @RequestBody RAMFilterRequest request
    ) {
        return filterService.filterAllRamsBasedOnSpecification(
                new RAMFilterDTO(request)
        );
    }

    @GetMapping(value = "/external-hard-drive")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public List<ExternalHardDriveDTO> filterExternalHardDrivesBasedOnSpecification(
            @RequestBody ExternalHardDriveFilterRequest request
    ) {
        return filterService.filterAllExternalHardDrivesBasedOnSpecification(
                new ExternalHardDriveFilterDTO(request)
        );
    }

    @GetMapping(value = "/headset")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public List<HeadsetDTO> filterHeadsetsBasedOnSpecification(
            @RequestBody HeadsetFilterRequest request
    ) {
        return filterService.filterAllHeadsetsBasedOnSpecification(
                new HeadsetFilterDTO(request)
        );
    }

    @GetMapping(value = "/keyboard")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public List<KeyboardDTO> filterKeyboardsBasedOnSpecification(
            @RequestBody KeyboardFilterRequest request
    ) {
        return filterService.filterAllKeyboardsBasedOnSpecification(
                new KeyboardFilterDTO(request)
        );
    }

    @GetMapping(value = "/monitor")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public List<MonitorDTO> filterMonitorsBasedOnSpecification(
            @RequestBody MonitorFilterRequest request
    ) {
        return filterService.filterAllMonitorsBasedOnSpecification(
                new MonitorFilterDTO(request)
        );
    }

    @GetMapping(value = "/mouse")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public List<MouseDTO> filterMiceBasedOnSpecification(
            @RequestBody MouseFilterRequest request
    ) {
        return filterService.filterAllMiceBasedOnSpecification(
                new MouseFilterDTO(request)
        );
    }

    @GetMapping(value = "/speaker")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public List<SpeakerDTO> filterSpeakersBasedOnSpecification(
            @RequestBody SpeakerFilterRequest request
    ) {
        return filterService.filterAllSpeakersBasedOnSpecification(
                new SpeakerFilterDTO(request)
        );
    }

    @GetMapping(value = "/{term}")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public List<ProductDTO> filterAllByTerm(
            @PathVariable String term
    ) {
        return filterService.findAllProductsBasedOnTerm(term);
    }
}