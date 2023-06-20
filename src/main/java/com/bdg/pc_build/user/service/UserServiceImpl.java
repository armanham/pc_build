package com.bdg.pc_build.user.service;

import com.bdg.pc_build.checking.exception.EmailAlreadyExistsException;
import com.bdg.pc_build.checking.exception.UserNotFoundException;
import com.bdg.pc_build.user.enumerations.Role;
import com.bdg.pc_build.user.model.entity.User;
import com.bdg.pc_build.user.repository.UserDAO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    UserDAO userDAO;

    @Override
    public User updateFirstNameByEmail(final String email, final String firstName) {
        User userToUpdate = userDAO.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
        userToUpdate.setFirstName(firstName);
        return userDAO.save(userToUpdate);
    }

    @Override
    public User updateLastNameByEmail(final String email, final String lastName) {
        User userToUpdate = userDAO.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
        userToUpdate.setLastName(lastName);
        return userDAO.save(userToUpdate);
    }

    @Override
    public User updateEmailByEmail(final String email, final String newEmail) {
        Optional<User> optionalUser = userDAO.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(email);
        }

        Optional<User> userOptionalWithNewEmail = userDAO.findByEmail(newEmail);
        if (userOptionalWithNewEmail.isPresent()) {
            throw new EmailAlreadyExistsException(newEmail);
        }
        User userToUpdate = optionalUser.get();
        userToUpdate.setEmail(newEmail);
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
}