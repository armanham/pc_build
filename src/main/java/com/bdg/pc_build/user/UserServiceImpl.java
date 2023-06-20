package com.bdg.pc_build.user;

import com.bdg.pc_build.checking.exception.EmailAlreadyExistsException;

import com.bdg.pc_build.checking.exception.UserNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    final UserDAO userDAO;

    @Override
    public User updateFirstNameByEmail(String email, String firstName) {
        User user = userDAO.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));

        user.setFirstname(firstName);
        return  userDAO.save(user);

    }


    @Override
    public User updateLastNameByEmail(String email, String lastName) {
        User user = userDAO.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
        user.setLastname(lastName);
        return userDAO.save(user);
    }



    @Override
    public User updateEmailByEmail(String email, String newEmail) {
        Optional<User> existingUser = userDAO.findByEmail(email);
        if ( existingUser.isEmpty()) {
            throw new UserNotFoundException(email);
        }
        if(Objects.equals(email, newEmail)) {
            throw new EmailAlreadyExistsException(email);
        }
        Optional<User> newExistingUser = userDAO.findByEmail(newEmail);
        if ( newExistingUser.isPresent()) {
            throw new EmailAlreadyExistsException(newEmail);
        }
        User user = existingUser.get();
        user.setEmail(newEmail);
        return userDAO.save(user);
    }

//    @Override
//    public UserUpdateRoleByEmail(String email) {
//        User user = userDAO.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
//        user.setRole(Role.ADMIN);
//        return  User(userDAO.save(user);
//    }
}
