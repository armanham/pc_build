package com.bdg.pc_build.computer_builder.service;

import com.bdg.pc_build.cart.model.CartItem;
import com.bdg.pc_build.cart.service.CartService;
import com.bdg.pc_build.computer_builder.model.entity.Computer;
import com.bdg.pc_build.computer_builder.model.request.ComputerCreationRequest;
import com.bdg.pc_build.computer_builder.repository.ComputerDAO;
import com.bdg.pc_build.config.JwtService;
import com.bdg.pc_build.exception.InvalidAuthHeaderException;
import com.bdg.pc_build.exception.InvalidTokenException;
import com.bdg.pc_build.exception.ProductNotFoundException;
import com.bdg.pc_build.exception.UserNotFoundException;
import com.bdg.pc_build.product.model.entity.main_component.Cooler;
import com.bdg.pc_build.product.model.entity.main_component.InternalHardDrive;
import com.bdg.pc_build.product.model.entity.main_component.RAM;
import com.bdg.pc_build.product.model.entity.peripheral.*;
import com.bdg.pc_build.user.model.entity.User;
import com.bdg.pc_build.user.repository.UserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ComputerServiceImpl implements ComputerService {

    private final ComputerDAO computerDAO;
    private final UserDAO userDAO;

    private final CartService cartService;
    private final JwtService jwtService;

    private final ComputerEntityInitializerBasedOnRequest entityInitializerBasedOnRequest;
    private final CompatibilityValidator compatibilityValidator;

    @Override
    public void checkComputer(final Computer computer) {
        compatibilityValidator.validateComputer(computer);
    }

    @Override
    public Computer save(final ComputerCreationRequest computerCreationRequest, final String authHeader) {
        User user = getUserByAuthHeader(authHeader);
        Computer computer = entityInitializerBasedOnRequest.initEntityFromRequest(computerCreationRequest);
        checkComputer(computer);
        if (computer != null) {
            computer.setUser(user);
            return computerDAO.save(computer);
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Computer getComputerById(final Long id) {
        return computerDAO.findById(id).orElseThrow(() -> new ProductNotFoundException("Computer"));//todo exception
    }

    @Override
    public void checkout(final Long id, final String authHeader) {
        Computer computer = getComputerById(id);

        if (computer.getACase() != null) {
            cartService.addProduct(new CartItem(computer.getACase().getId(), 1));
        }
        for (Cooler cooler : computer.getCoolers()) {
            cartService.addProduct(new CartItem(cooler.getId(), 1));
        }
        if (computer.getCpu() != null) {
            cartService.addProduct(new CartItem(computer.getCpu().getId(), 1));
        }
        if (computer.getCpuCooler() != null) {
            cartService.addProduct(new CartItem(computer.getCpuCooler().getId(), 1));
        }
        if (computer.getGpu() != null) {
            cartService.addProduct(new CartItem(computer.getGpu().getId(), 1));
        }
        for (InternalHardDrive internalHardDrive : computer.getInternalHardDrives()) {
            cartService.addProduct(new CartItem(internalHardDrive.getId(), 1));
        }
        if (computer.getMotherboard() != null) {
            cartService.addProduct(new CartItem(computer.getMotherboard().getId(), 1));
        }
        if (computer.getPowerSupply() != null) {
            cartService.addProduct(new CartItem(computer.getPowerSupply().getId(), 1));
        }
        for (RAM ram : computer.getRams()) {
            cartService.addProduct(new CartItem(ram.getId(), 1));
        }
        for (ExternalHardDrive externalHardDrive : computer.getExternalHardDrives()) {
            cartService.addProduct(new CartItem(externalHardDrive.getId(), 1));
        }
        for (Headset headset : computer.getHeadsets()) {
            cartService.addProduct(new CartItem(headset.getId(), 1));
        }
        for (Keyboard keyboard : computer.getKeyboards()) {
            cartService.addProduct(new CartItem(keyboard.getId(), 1));
        }
        for (Monitor monitor : computer.getMonitors()) {
            cartService.addProduct(new CartItem(monitor.getId(), 1));
        }
        for (Mouse mouse : computer.getMice()) {
            cartService.addProduct(new CartItem(mouse.getId(), 1));
        }
        for (Speaker speaker : computer.getSpeakers()) {
            cartService.addProduct(new CartItem(speaker.getId(), 1));
        }

        cartService.checkout(authHeader, true);
        computer.setIsOrdered(true);
        computerDAO.save(computer);
    }

    @Override
    public List<Computer> getAllComputersByIsOrdered(final Boolean isOrdered) {
        return computerDAO.findAllByIsOrdered(isOrdered);
    }

    private User getUserByAuthHeader(final String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new InvalidAuthHeaderException();
        }
        final String token = authHeader.substring(7);
        final String email = jwtService.extractUsername(token);

        User user = userDAO.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));

        if (!jwtService.isTokenValid(token, user)) {
            throw new InvalidTokenException();
        }
        return user;
    }
}