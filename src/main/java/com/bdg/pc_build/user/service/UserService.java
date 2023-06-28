package com.bdg.pc_build.user.service;

import com.bdg.pc_build.user.model.dto.UserDTO;
import com.bdg.pc_build.user.model.entity.User;

import java.util.List;

public interface UserService {

    User findUserByAuthHeader(String authHeader);

    User findUserById(Long id);

    void updateFirstNameByAuthHeader(String authHeader, String newFirstName);

    void updateLastNameByAuthHeader(String authHeader, String newLastName);

    void updateEmailByAuthHeader(String authHeader, String newEmail);

    void updatePasswordByAuthHeader(String authHeader, String newPassword); //todo

    void changeUserRoleToAdminByEmail(String email);

    void changeAdminRoleToUserByEmail(String email);

    List<UserDTO> findAll();

}