package com.bdg.pc_build.user.service;

import com.bdg.pc_build.user.model.entity.User;

import java.util.List;

public interface UserService {

    User updateFirstNameByEmail(String email, String firstName);

    User updateLastNameByEmail(String email, String lastName);

    User updateEmailByEmail(String email, String newEmail);

    User changeUserRoleToAdminByEmail(String email);

    User changeAdminRoleToUserByEmail(String email);

    List<User> findAll();

}