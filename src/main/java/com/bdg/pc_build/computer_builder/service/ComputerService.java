package com.bdg.pc_build.computer_builder.service;

import com.bdg.pc_build.computer_builder.model.entity.Computer;
import com.bdg.pc_build.computer_builder.model.request.ComputerCreationRequest;

import java.util.List;

public interface ComputerService {

    void checkComputer(Computer computer);

    Computer save(ComputerCreationRequest computerCreationRequest, String authHeader);

    Computer getComputerById(Long id);

    void checkout(Long id, String authHeader);

    List<Computer> getAllComputersByIsOrdered(Boolean isOrdered);

}