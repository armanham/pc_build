package com.bdg.pc_build.filter.service;

import com.bdg.pc_build.filter.model.dto.ProductFilterDTO;
import com.bdg.pc_build.product.model.dto.ProductDTO;

import java.util.List;

public interface FilterService {

    List<ProductDTO> filterByRequest(ProductFilterDTO dto);
}