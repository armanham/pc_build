package com.bdg.pc_build.user.service;

import com.bdg.pc_build.computer_builder.model.dto.ComputerDTO;
import com.bdg.pc_build.desire_log.model.dto.DesireLogDTO;
import com.bdg.pc_build.order.model.dto.OrderDTO;
import com.bdg.pc_build.user.model.dto.UserDTO;
import com.bdg.pc_build.user.model.entity.User;

import java.util.List;

public interface UserService {

    User getUserById(Long id);

    User getUserByEmail(final String email);

    User getUserByAuthHeader(final String authHeader);

    void updateFirstNameByAuthHeader(String authHeader, String newFirstName);

    void updateLastNameByAuthHeader(String authHeader, String newLastName);

    String updateEmailByAuthHeader(String authHeader, String newEmail);

    String updatePasswordByAuthHeader(String authHeader, String newPassword); //todo

    void updateFirstNameById(Long id, String newFirstName);

    void updateLastNameById(Long id, String newLastName);

    String updateEmailById(Long id, String newEmail);

    String updatePasswordById(final Long id, final String newPassword);

    void changeUserRoleToAdminByEmail(String email);

    void changeAdminRoleToUserByEmail(String email);

    List<UserDTO> findAll();

    List<ComputerDTO> getBuiltComputersByAuthHeader(String authHeader);

    List<ComputerDTO> getBuiltComputersByUserId(Long userId);

    List<DesireLogDTO> getDesireLogsByAuthHeader(String authHeader);

    List<DesireLogDTO> getDesireLogsByUserId(Long userId);

    List<OrderDTO> getOrdersByAuthHeader(String authHeader);

    List<OrderDTO> getOrdersByUserId(Long userId);

}