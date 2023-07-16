package com.bdg.pc_build.pc_builder.service;

import com.bdg.pc_build.cart.model.CartItem;
import com.bdg.pc_build.cart.service.CartService;
import com.bdg.pc_build.cart.service.impl.CartServiceImpl;
import com.bdg.pc_build.pc_builder.converter.ComputerEntityInitializerBasedOnRequest;
import com.bdg.pc_build.pc_builder.model.entity.Computer;
import com.bdg.pc_build.pc_builder.repository.ComputerDAO;
import com.bdg.pc_build.pc_builder.validator.CompatibilityValidator;
import com.bdg.pc_build.exception.ProductNotFoundException;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main.Cooler;
import com.bdg.pc_build.product.model.entity.main.InternalHardDrive;
import com.bdg.pc_build.product.model.entity.main.RAM;
import com.bdg.pc_build.product.model.entity.peripheral.*;
import com.bdg.pc_build.product.service.ProductService;
import com.bdg.pc_build.user.model.entity.User;
import com.bdg.pc_build.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@RequiredArgsConstructor
@Service
public class PCServiceImpl implements PCService {

    private final ComputerDAO computerDAO;

    private final CartService cartService;
    private final UserService userService;
    private final ProductService productService;

    private final ComputerEntityInitializerBasedOnRequest entityInitializerBasedOnRequest;
    private final CompatibilityValidator compatibilityValidator;

    private final Map<ProductDTO, Integer> components = new HashMap<>();

    @Override
    public void addProduct(final CartItem component) {
        ProductDTO product = productService.findById(component.productId());
        if (components.containsKey(product)) {
            components.replace(product, components.get(product) + component.quantity());
        } else {
            components.put(product, component.quantity());
        }
    }

    @Override
    public void removeProduct(CartItem component) {
        ProductDTO product = productService.findById(component.productId());
        if (components.containsKey(product)) {
            if (components.get(product) > component.quantity())
                components.replace(product, components.get(product) - component.quantity());
            else if (Objects.equals(components.get(product), component.quantity())) {
                components.remove(product);
            }
        }
    }

    @Override
    public void clearBuilder() {
        components.clear();
    }

    @Override
    public CartServiceImpl.ProductCountPrice getCurrentBuilder() {
        return new CartServiceImpl.ProductCountPrice(Collections.unmodifiableMap(components), getTotal());
    }

    @Override
    public BigDecimal getTotal() {
        return components.entrySet()
                .stream()
                .map(entry -> BigDecimal.valueOf(entry.getKey().getPrice()).multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    @Override
    public Map<ProductDTO, Integer> getComponents() {
        return components;
    }

    @Override
    public void checkCompatibilityBetweenComponentsOfComputer(Computer computer) {
        compatibilityValidator.validateComputer(computer);
    }

    @Override
    public Computer save(Map<ProductDTO, Integer> components, String authHeader) {
        User user = userService.getUserByAuthHeader(authHeader);
        Computer computer = entityInitializerBasedOnRequest.initEntityFromRequest(components);
        checkCompatibilityBetweenComponentsOfComputer(computer);
        computer.setUser(user);
        return computerDAO.save(computer);
    }

    @Override
    public Long checkSaveOrder(Map<ProductDTO, Integer> components, final String authHeader) {
        Computer savedComputer = save(components, authHeader);
        return orderComputerById(savedComputer.getId(), authHeader);
    }

    @Override
    public Computer getComputerById(final Long id) {
        return computerDAO.findById(id).orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, Computer.class, id));
    }

    @Override
    public Long orderComputerById(final Long id, final String authHeader) {
        Computer foundedComputer = getComputerById(id);
        User user = userService.getUserByAuthHeader(authHeader);

        boolean isComputerSavedByUser = false;
        for (Computer userComputer : user.getComputers()) {
            if (userComputer.getId().equals(id)) {
                isComputerSavedByUser = true;
                break;
            }
        }

        if (!isComputerSavedByUser && !(foundedComputer.getIsOrdered() && foundedComputer.getIsFullyConstructed())) {
            throw new IllegalArgumentException(); //todo
        }

        if (foundedComputer.getACase() != null) {
            cartService.addProduct(new CartItem(foundedComputer.getACase().getId(), 1));
        }
        for (Cooler cooler : foundedComputer.getCoolers()) {
            cartService.addProduct(new CartItem(cooler.getId(), 1));
        }
        if (foundedComputer.getCpu() != null) {
            cartService.addProduct(new CartItem(foundedComputer.getCpu().getId(), 1));
        }
        if (foundedComputer.getCpuCooler() != null) {
            cartService.addProduct(new CartItem(foundedComputer.getCpuCooler().getId(), 1));
        }
        if (foundedComputer.getGpu() != null) {
            cartService.addProduct(new CartItem(foundedComputer.getGpu().getId(), 1));
        }
        for (InternalHardDrive internalHardDrive : foundedComputer.getInternalHardDrives()) {
            cartService.addProduct(new CartItem(internalHardDrive.getId(), 1));
        }
        if (foundedComputer.getMotherboard() != null) {
            cartService.addProduct(new CartItem(foundedComputer.getMotherboard().getId(), 1));
        }
        if (foundedComputer.getPowerSupply() != null) {
            cartService.addProduct(new CartItem(foundedComputer.getPowerSupply().getId(), 1));
        }
        for (RAM ram : foundedComputer.getRams()) {
            cartService.addProduct(new CartItem(ram.getId(), 1));
        }
        for (ExternalHardDrive externalHardDrive : foundedComputer.getExternalHardDrives()) {
            cartService.addProduct(new CartItem(externalHardDrive.getId(), 1));
        }
        for (Headset headset : foundedComputer.getHeadsets()) {
            cartService.addProduct(new CartItem(headset.getId(), 1));
        }
        for (Keyboard keyboard : foundedComputer.getKeyboards()) {
            cartService.addProduct(new CartItem(keyboard.getId(), 1));
        }
        for (Monitor monitor : foundedComputer.getMonitors()) {
            cartService.addProduct(new CartItem(monitor.getId(), 1));
        }
        for (Mouse mouse : foundedComputer.getMice()) {
            cartService.addProduct(new CartItem(mouse.getId(), 1));
        }
        for (Speaker speaker : foundedComputer.getSpeakers()) {
            cartService.addProduct(new CartItem(speaker.getId(), 1));
        }

        Long orderId = cartService.checkout(authHeader, true);
        foundedComputer.setIsOrdered(true);
        computerDAO.save(foundedComputer);
        return orderId;
    }

    @Override
    public List<Computer> getAllComputersByIsOrderedAndIsFullyConstructed(final Boolean isOrdered, final Boolean isFullyConstructed) {
        return computerDAO.findAllByIsOrderedAndIsFullyConstructed(isOrdered, isFullyConstructed);
    }
}