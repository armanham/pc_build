package com.bdg.pc_build.product.controller;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.dto.display.MonitorDTO;
import com.bdg.pc_build.product.model.dto.main_component.CaseDTO;
import com.bdg.pc_build.product.model.dto.main_component.CoolerDTO;
import com.bdg.pc_build.product.model.request.BetweenPricesRequest;
import com.bdg.pc_build.product.model.request.EditPriceRequest;
import com.bdg.pc_build.product.model.request.creation.ProductRequest;
import com.bdg.pc_build.product.model.request.ReduceCountRequest;
import com.bdg.pc_build.product.model.request.creation.MonitorCreationRequest;
import com.bdg.pc_build.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProductController {

    ProductService productService;

//    @PostMapping("/new/{componentType}")
//    public ResponseEntity<String> saveComponent(
//            @PathVariable("componentType") String componentType,
//            @RequestBody ProductRequest product) {
//        MonitorCreationRequest monitorCreationRequest = new MonitorCreationRequest();
//        switch (componentType) {
//            case "monitor" ->
//                    productService.saveMonitor(MonitorDTO.initDTOFromRequest(monitorCreationRequest.initMonitorRequestFromProductRequest(product)));
//            case "case" -> productService.saveCase(CaseDTO.initDTOFromRequest(product));
//            case "cooler" -> productService.saveCooler(CoolerDTO.initDTOFromRequest(product));
//            //TODO cases
//            default -> {
//                return ResponseEntity.badRequest().body("Invalid component type");
//            }
//        }
//        return ResponseEntity.ok("Component saved successfully");
//    }
    @PostMapping("/new/monitor")
    public ResponseEntity<String> saveMonitor(@Valid @RequestBody MonitorCreationRequest request){
        productService.saveMonitor(MonitorDTO.initDTOFromRequest(request));
        return ResponseEntity.ok("Component saved successfully");
    }

    @GetMapping("/get/{componentType}/{name}")
    public ResponseEntity<?> getByName(
            @PathVariable("componentType") String componentType,
            @PathVariable("name") String name) {
        ProductDTO productDTO = null;
        switch (componentType) {
            case "monitor" -> productDTO = productService.findMonitorByName(name);
            case "case" -> productDTO = productService.findCaseByName(name);
//            case "cooler" -> productService.
            //TODO cases
            default -> {
                return ResponseEntity.badRequest().body("Invalid component type");
            }
        }
        return ResponseEntity.ok(productDTO);
    }


    @GetMapping(value = "/getAllByComponentTypeAndPrice/{componentType}")
    public List<?> getAllBetweenPrice(
            @PathVariable("componentType") String componentType,
            @RequestBody BetweenPricesRequest request
    ) {
        List<ProductDTO> productDTOList;
        switch (componentType) {
            case "monitor" -> {
                return productService.findMonitorByPrice(Double.valueOf(request.minPrice()), Double.valueOf(request.maxPrice()));
//            case "case" -> productService.findCaseByName(name);
//            case "cooler" -> productService.
                //TODO cases
            }
            default -> {
                return new ArrayList<>();
            }
        }
    }


    @PutMapping(value = "/edit/{componentType}")
    public ResponseEntity<?> editPrice(
            @PathVariable("componentType") String componentType,
            @RequestBody EditPriceRequest request
    ) {
        switch (componentType) {
            case "monitor" -> {
                return ResponseEntity.ok().body(productService.updateMonitorPriceByName(request.productName(), request.newPrice()));
//            case "case" -> productService.findCaseByName(name);
//            case "cooler" -> productService.
                //TODO cases
            }
            default -> {
                return ResponseEntity.badRequest().body("Invalid component type");
            }
        }
    }

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
                return ResponseEntity.badRequest().body("Invalid component type");
            }
        }
    }

    @GetMapping(value = "/getAllByPrice")
    public List<ProductDTO> getAllByPrice(
            @RequestBody BetweenPricesRequest request
    ) {
        return productService.findAllProductsByPrice(Double.valueOf(request.minPrice()), Double.valueOf(request.maxPrice()));
    }

    @GetMapping(value = "/getAllByName/{name}")
    public List<ProductDTO> getAllByName(
            @PathVariable("name") String name
    ) {
        return productService.findAllProductsByNameIgnoreCaseAndLikeTerm(name);
    }
}