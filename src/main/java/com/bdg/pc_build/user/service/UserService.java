package com.bdg.pc_build.user.service;

import com.bdg.pc_build.user.model.entity.User;

import java.util.List;

public interface UserService {

    User updateFirstNameByAuthHeader(String authHeader, String newFirstName);

    User updateLastNameByAuthHeader(String authHeader, String newLastName);

    User updateEmailByAuthHeader(String authHeader, String newEmail);

    User updatePasswordByAuthHeader(String authHeader, String newPassword);

    User changeUserRoleToAdminByEmail(String email);

    User changeAdminRoleToUserByEmail(String email);

    List<User> findAll();

}