package com.bdg.pc_build.order.service;

import com.bdg.pc_build.product.model.dto.ProductDTO;

import java.math.BigDecimal;
import java.util.Set;

public interface OrderService {
    Set<ProductDTO> save(Set<ProductDTO> products, BigDecimal totalPrice);
}