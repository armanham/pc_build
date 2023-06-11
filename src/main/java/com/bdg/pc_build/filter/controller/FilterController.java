package com.bdg.pc_build.filter.controller;

import com.bdg.pc_build.filter.model.dto.ProductFilterDTO;
import com.bdg.pc_build.filter.model.dto.display.MonitorFilterDTO;
import com.bdg.pc_build.filter.model.request.ProductFilterRequest;
import com.bdg.pc_build.filter.model.request.display.MonitorFilterRequest;
import com.bdg.pc_build.filter.service.FilterService;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.dto.display.MonitorDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/filter")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class FilterController {

    FilterService filterService;

    @GetMapping(value = "/byNameAndPrice")
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

//    @GetMapping(value = "/bytdp")
//    public List<ProductDTO> filterAllProductsByTdp(
//            @RequestBody FilterRequestForProduct filterRequestForProduct
//    )
}