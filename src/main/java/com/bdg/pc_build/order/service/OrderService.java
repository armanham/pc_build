package com.bdg.pc_build.order.service;

import com.bdg.pc_build.order.entity.Order;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.user.model.entity.User;

import java.math.BigDecimal;
import java.util.Set;

public interface OrderService {

    Order save(Set<ProductDTO> products, BigDecimal totalPrice, User user, Boolean isFromBuilder);
}