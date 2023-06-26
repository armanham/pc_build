package com.bdg.pc_build.computer_builder.service;

import com.bdg.pc_build.cart.service.CartService;
import com.bdg.pc_build.computer_builder.model.entity.Computer;
import com.bdg.pc_build.computer_builder.model.request.ComputerCreationRequest;
import com.bdg.pc_build.computer_builder.repository.ComputerDAO;
import com.bdg.pc_build.config.JwtService;
import com.bdg.pc_build.exception.InvalidAuthHeaderException;
import com.bdg.pc_build.exception.InvalidTokenException;
import com.bdg.pc_build.exception.ProductNotFoundException;
import com.bdg.pc_build.exception.UserNotFoundException;
import com.bdg.pc_build.user.model.entity.User;
import com.bdg.pc_build.user.repository.UserDAO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class ComputerServiceImpl implements ComputerService {

    ComputerDAO computerDAO;
    UserDAO userDAO;
    ComputerEntityInitializerBasedOnRequest entityInitializerBasedOnRequest;
    CartService cartService;
    JwtService jwtService;

    @Override
    public Computer checkComputer(final Computer computer) {
        CompatibilityValidator validator = new CompatibilityValidator(computer);
        return validator.getComputerToCompatibilityCheck();
    }

    @Override
    public Computer save(final ComputerCreationRequest computerCreationRequest, final String authHeader) {
        User user = getUserByAuthHeader(authHeader);
        Computer computer = entityInitializerBasedOnRequest.initEntityFromRequest(computerCreationRequest);
        computer = checkComputer(computer);
        if (computer != null) {
            computer.setUser(user);
            computer.setTotalPrice(BigDecimal.valueOf(9999)); //todooo
            return computerDAO.save(computer);
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Computer getComputerById(final Long id) {
        return computerDAO.findById(id).orElseThrow(()->new ProductNotFoundException("Computer"));//todo exception
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