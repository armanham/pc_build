package com.bdg.pc_build.computer_builder.service;

import com.bdg.pc_build.computer_builder.model.entity.Computer;
import com.bdg.pc_build.computer_builder.model.request.ComputerCreationRequest;

import java.util.List;

public interface ComputerService {

    void checkCompatibilityBetweenComponentsOfComputer(Computer computer);

    Computer save(ComputerCreationRequest computerCreationRequest, String authHeader);

    Computer getComputerById(Long id);

    void orderComputerById(Long id, String authHeader);

    void checkSaveOrder(ComputerCreationRequest computerCreationRequest, String authHeader);

    List<Computer> getAllComputersByIsOrderedAndIsFullyConstructed(final Boolean isOrdered, final Boolean isFullyConstructed);

}