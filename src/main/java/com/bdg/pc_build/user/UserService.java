package com.bdg.pc_build.user;

public interface UserService {

    User updateFirstNameByEmail(String email , String firstName);
    User updateLastNameByEmail(String email, String lastName);
    User updateEmailByEmail(String email, String newEmail);
//    User updateRoleByEmail(String email, String role);
}
