package com.bdg.pc_build.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    final UserDAO userDAO;

    @Override
    public boolean save(UserDTO userDTO) {

        userDAO.save(new User(userDTO));

        return true;
    }
}
