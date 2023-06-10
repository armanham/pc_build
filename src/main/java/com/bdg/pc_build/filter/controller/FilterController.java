package com.bdg.pc_build.filter.controller;

import com.bdg.pc_build.filter.model.dto.FilterDTOForProduct;
import com.bdg.pc_build.filter.model.request.FilterRequestForProduct;
import com.bdg.pc_build.filter.service.FilterService;
import com.bdg.pc_build.product.model.dto.ProductDTO;
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
            @RequestBody FilterRequestForProduct request
    ) {
        return filterService.filterByRequest(
                new FilterDTOForProduct(request)
        );
    }

//    @GetMapping(value = "/bytdp")
//    public List<ProductDTO> filterAllProductsByTdp(
//            @RequestBody FilterRequestForProduct filterRequestForProduct
//    )
}