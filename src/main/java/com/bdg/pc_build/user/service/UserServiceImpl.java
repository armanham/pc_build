package com.bdg.pc_build.user.service;

import com.bdg.pc_build.authentication.AuthenticationService;
import com.bdg.pc_build.config.JwtService;
import com.bdg.pc_build.exception.EmailAlreadyExistsException;
import com.bdg.pc_build.exception.InvalidAuthHeaderException;
import com.bdg.pc_build.exception.InvalidTokenException;
import com.bdg.pc_build.exception.UserNotFoundException;
import com.bdg.pc_build.token.Token;
import com.bdg.pc_build.user.enumerations.Role;
import com.bdg.pc_build.user.model.entity.User;
import com.bdg.pc_build.user.repository.UserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;
    private final UserDAO userDAO;

    @Override
    public User findUserByAuthHeader(final String authHeader) {
        return getUserByAuthHeader(authHeader);
    }

    @Override
    public User findUserById(Long id) {
        return userDAO.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User updateFirstNameByAuthHeader(final String authHeader, final String newFirstName) {
        User userToUpdate = getUserByAuthHeader(authHeader);
        userToUpdate.setFirstName(newFirstName);
//        expireAndRevokeAllOldTokensAndGenerateNewToken(userToUpdate);
        return userDAO.save(userToUpdate);
    }

    @Override
    public User updateLastNameByAuthHeader(final String authHeader, final String newLastName) {
        User userToUpdate = getUserByAuthHeader(authHeader);
        userToUpdate.setLastName(newLastName);
//        expireAndRevokeAllOldTokensAndGenerateNewToken(userToUpdate);
        return userDAO.save(userToUpdate);
    }

    @Override
    public User updateEmailByAuthHeader(final String authHeader, final String newEmail) {
        User userToUpdate = getUserByAuthHeader(authHeader);

        Optional<User> userOptionalWithNewEmail = userDAO.findByEmail(newEmail);
        if (userOptionalWithNewEmail.isPresent()) {
            throw new EmailAlreadyExistsException(newEmail);
        }
        userToUpdate.setEmail(newEmail);
        expireAndRevokeAllOldTokensAndGenerateNewToken(userToUpdate);
        return userDAO.save(userToUpdate);
    }

    @Override
    public User updatePasswordByAuthHeader(final String authHeader, final String newPassword) {
        User userToUpdate = getUserByAuthHeader(authHeader);
        userToUpdate.setLastName(newPassword);
//        expireAndRevokeAllOldTokensAndGenerateNewToken(userToUpdate);
        return userDAO.save(userToUpdate);
    }

    @Override
    public User changeUserRoleToAdminByEmail(final String email) {
        User user = userDAO.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
        user.setRole(Role.ADMIN);
        return userDAO.save(user);
    }

    @Override
    public User changeAdminRoleToUserByEmail(final String email) {
        User user = userDAO.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
        user.setRole(Role.USER);
        return userDAO.save(user);
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
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

    private void expireAndRevokeAllOldTokensAndGenerateNewToken(final User userToUpdate) { //TODO
        for (Token token : userToUpdate.getTokens()) {
            token.setExpired(true);
            token.setRevoked(true);
        }

        authenticationService.saveUserToken(userToUpdate, jwtService.generateToken(userToUpdate));
    }
}