package com.bdg.pc_build.product.controller;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.dto.display.MonitorDTO;
import com.bdg.pc_build.product.model.entity.Product;
import com.bdg.pc_build.product.model.entity.main_component.CPU;
import com.bdg.pc_build.product.model.request.ProductRequest;
import com.bdg.pc_build.product.service.ProductService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/product/new")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProductPostController {
    ProductService productService;
    ModelMapper modelMapper;

    @PostMapping("/{componentType}")
    public ResponseEntity<String> saveComponent(@PathVariable String componentType, @RequestBody ProductRequest product) {
        switch (componentType) {
            case "monitor":
                  productService.saveMonitor(MonitorDTO.initDTOFromRequest(product));
                  break;
            default:
                return ResponseEntity.badRequest().body("Invalid component type");
        }
        return ResponseEntity.ok("Component saved successfully");
    }



}
