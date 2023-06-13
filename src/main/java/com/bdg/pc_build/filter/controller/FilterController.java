package com.bdg.pc_build.filter.controller;

import com.bdg.pc_build.filter.model.dto.ProductFilterDTO;
import com.bdg.pc_build.filter.model.dto.peripheral.MonitorFilterDTO;
import com.bdg.pc_build.filter.model.request.ProductFilterRequest;
import com.bdg.pc_build.filter.model.request.peripheral.MonitorFilterRequest;
import com.bdg.pc_build.filter.service.FilterService;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.dto.peripheral.MonitorDTO;
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

    @GetMapping(value = "/monitor")
    public List<MonitorDTO> filterMonitorsBasedOnSpecification(
            @RequestBody MonitorFilterRequest request
    ) {
        return filterService.filterAllMonitorsBasedOnSpecification(
                new MonitorFilterDTO(request)
        );
    }

    @GetMapping(value = "/{term}")
    public List<ProductDTO> filterAllByTerm(
            @PathVariable("term") String term
    ) {
        return filterService.findAllBasedOnTerm(term);
    }

//    @GetMapping(value = "/bytdp")
//    public List<ProductDTO> filterAllProductsByTdp(
//            @RequestBody FilterRequestForProduct filterRequestForProduct
//    )
}