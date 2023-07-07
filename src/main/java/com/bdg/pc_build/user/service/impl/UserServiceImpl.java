package com.bdg.pc_build.user.service.impl;

import com.bdg.pc_build.authentication.service.AuthenticationService;
import com.bdg.pc_build.computer_builder.model.dto.ComputerDTO;
import com.bdg.pc_build.config.JwtService;
import com.bdg.pc_build.desire_log.model.dto.DesireLogDTO;
import com.bdg.pc_build.exception.EmailAlreadyExistsException;
import com.bdg.pc_build.exception.InvalidAuthHeaderException;
import com.bdg.pc_build.exception.InvalidTokenException;
import com.bdg.pc_build.exception.UserNotFoundException;
import com.bdg.pc_build.order.model.dto.OrderDTO;
import com.bdg.pc_build.token.model.entity.Token;
import com.bdg.pc_build.user.enumerations.Role;
import com.bdg.pc_build.user.model.dto.UserDTO;
import com.bdg.pc_build.user.model.entity.User;
import com.bdg.pc_build.user.repository.UserDAO;
import com.bdg.pc_build.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;


    @Override
    public User getUserById(final Long id) {
        return userDAO.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User getUserByEmail(final String email) {
        return userDAO.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
    }

    @Override
    public void updateFirstNameByAuthHeader(final String authHeader, final String newFirstName) {
        User userToUpdate = getUserByAuthHeader(authHeader);
        userToUpdate.setFirstName(newFirstName);
        userDAO.save(userToUpdate);
    }

    @Override
    public void updateLastNameByAuthHeader(final String authHeader, final String newLastName) {
        User userToUpdate = getUserByAuthHeader(authHeader);
        userToUpdate.setLastName(newLastName);
        userDAO.save(userToUpdate);
    }

    @Override
    public void updateEmailByAuthHeader(final String authHeader, final String newEmail) {
        User userToUpdate = getUserByAuthHeader(authHeader);

        Optional<User> userOptionalWithNewEmail = userDAO.findByEmail(newEmail);
        if (userOptionalWithNewEmail.isPresent()) {
            throw new EmailAlreadyExistsException(HttpStatus.ALREADY_REPORTED, newEmail);
        }
        userToUpdate.setEmail(newEmail);
        expireAndRevokeAllOldTokensAndGenerateNewToken(userToUpdate);
        userDAO.save(userToUpdate);
    }

    @Override
    public void updatePasswordByAuthHeader(final String authHeader, final String newPassword) {
        User userToUpdate = getUserByAuthHeader(authHeader);
        userToUpdate.setPassword(newPassword);
        userDAO.save(userToUpdate);
    }

    @Override
    public void updateFirstNameById(final Long id, final String newFirstName) {
        User userToUpdate = getUserById(id);
        userToUpdate.setFirstName(newFirstName);
        userDAO.save(userToUpdate);
    }

    @Override
    public void updateLastNameById(final Long id, final String newLastName) {
        User userToUpdate = getUserById(id);
        userToUpdate.setLastName(newLastName);
        userDAO.save(userToUpdate);
    }

    @Override
    public void updateEmailById(final Long id, final String newEmail) {
        User userToUpdate = getUserById(id);

        Optional<User> userOptionalWithNewEmail = userDAO.findByEmail(newEmail);
        if (userOptionalWithNewEmail.isPresent()) {
            throw new EmailAlreadyExistsException(HttpStatus.ALREADY_REPORTED, newEmail);
        }
        userToUpdate.setEmail(newEmail);
        expireAndRevokeAllOldTokensAndGenerateNewToken(userToUpdate);
        userDAO.save(userToUpdate);
    }

    @Override
    public void changeUserRoleToAdminByEmail(final String email) {
        User user = getUserByEmail(email);
        user.setRole(Role.ADMIN);
        userDAO.save(user);
    }

    @Override
    public void changeAdminRoleToUserByEmail(final String email) {
        User user = getUserByEmail(email);
        user.setRole(Role.USER);
        userDAO.save(user);
    }

    @Override
    public List<UserDTO> findAll() {
        return userDAO.findAll()
                .stream()
                .map(UserDTO::new)
                .toList();
    }

    @Override
    public List<ComputerDTO> getBuiltComputersByAuthHeader(final String authHeader) {
        return getUserByAuthHeader(authHeader)
                .getComputers()
                .stream()
                .map(ComputerDTO::new)
                .toList();
    }

    @Override
    public List<ComputerDTO> getBuiltComputersByUserId(final Long userId) {
        return getUserById(userId).getComputers()
                .stream()
                .map(ComputerDTO::new)
                .toList();
    }

    @Override
    public List<DesireLogDTO> getDesireLogsByAuthHeader(final String authHeader) {
        return getUserByAuthHeader(authHeader).getDesireLogs()
                .stream()
                .map(DesireLogDTO::new)
                .toList();
    }

    @Override
    public List<DesireLogDTO> getDesireLogsByUserId(final Long userId) {
        return getUserById(userId).getDesireLogs()
                .stream()
                .map(DesireLogDTO::new)
                .toList();
    }

    @Override
    public List<OrderDTO> getOrdersByAuthHeader(final String authHeader) {
        return getUserByAuthHeader(authHeader).getOrders()
                .stream()
                .map(OrderDTO::new)
                .toList();
    }

    @Override
    public List<OrderDTO> getOrdersByUserId(final Long userId) {
        return getUserById(userId).getOrders()
                .stream()
                .map(OrderDTO::new)
                .toList();
    }

    public User getUserByAuthHeader(final String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new InvalidAuthHeaderException(HttpStatus.BAD_REQUEST);
        }
        final String token = authHeader.substring(7);
        final String email = jwtService.extractUsername(token);

        User user = userDAO.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));

        if (!jwtService.isTokenValid(token, user)) {
            throw new InvalidTokenException(HttpStatus.BAD_REQUEST);
        }
        return user;
    }

    private void expireAndRevokeAllOldTokensAndGenerateNewToken(final User userToUpdate) { //TODO
        for (Token token : userToUpdate.getTokens()) {
            token.setExpired(true);
            token.setRevoked(true);
        }

        authenticationService.saveUserToken(userToUpdate, jwtService.generateToken(userToUpdate));
    }
}