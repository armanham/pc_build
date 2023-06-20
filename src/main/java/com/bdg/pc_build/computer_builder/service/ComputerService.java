package com.bdg.pc_build.computer_builder.service;

import com.bdg.pc_build.computer_builder.model.dto.ComputerDTO;
import com.bdg.pc_build.computer_builder.model.request.ComputerCreationRequest;

public interface ComputerService {

    ComputerDTO initComputerDTOFromRequest(ComputerCreationRequest request);
}