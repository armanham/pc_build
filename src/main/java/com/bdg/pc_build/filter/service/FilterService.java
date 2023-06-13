package com.bdg.pc_build.filter.service;

import com.bdg.pc_build.filter.model.dto.ProductFilterDTO;
import com.bdg.pc_build.filter.model.dto.peripheral.MonitorFilterDTO;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.dto.peripheral.MonitorDTO;

import java.util.List;

public interface FilterService {

    List<ProductDTO> filterAllProductsByNameAndPrice(ProductFilterDTO filterDTO);

    List<MonitorDTO> filterAllMonitorsBasedOnSpecification(MonitorFilterDTO filterDTO);
}