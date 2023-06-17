package com.bdg.pc_build.builder.service;

import com.bdg.pc_build.builder.model.dto.ComputerDTO;
import com.bdg.pc_build.builder.model.request.ComputerCreationRequest;

public interface ComputerService {

    ComputerDTO initComputerDTOFromRequest(ComputerCreationRequest request);
}