package com.bdg.pc_build.computer_builder.service;

import com.bdg.pc_build.computer_builder.model.entity.Computer;
import com.bdg.pc_build.computer_builder.model.request.ComputerCreationRequest;

public interface ComputerService {

    Computer checkComputer(Computer computer);

    Computer save(ComputerCreationRequest computerCreationRequest, String authHeader);

    Computer getComputerById(Long id);

}