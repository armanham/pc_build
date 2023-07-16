package com.bdg.pc_build.pc_builder.service;

import com.bdg.pc_build.cart.model.CartItem;
import com.bdg.pc_build.cart.service.impl.CartServiceImpl;
import com.bdg.pc_build.pc_builder.model.entity.Computer;
import com.bdg.pc_build.product.model.dto.ProductDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface PCService {

    void addProduct(CartItem component);

    void removeProduct(CartItem component);

    void clearBuilder();

    CartServiceImpl.ProductCountPrice getCurrentBuilder();

    BigDecimal getTotal();

    Map<ProductDTO, Integer> getComponents();

    void checkCompatibilityBetweenComponentsOfComputer(Computer computer);

    Computer save(Map<ProductDTO, Integer> components, String authHeader);

    Long checkSaveOrder(Map<ProductDTO, Integer> components, String authHeader);

    Computer getComputerById(final Long id);

    Long orderComputerById(final Long id, final String authHeader);

    List<Computer> getAllComputersByIsOrderedAndIsFullyConstructed(final Boolean isOrdered, final Boolean isFullyConstructed);
}