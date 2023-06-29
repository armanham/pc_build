package com.bdg.pc_build.desire_log.service;

import com.bdg.pc_build.desire_log.model.dto.DesireLogDTO;
import com.bdg.pc_build.user.model.dto.UserDTO;
import com.bdg.pc_build.user.model.entity.User;

import java.util.List;
import java.util.Set;

public interface DesireLogService {

    DesireLogDTO save(String authHeader, DesireLogDTO dto);

    List<DesireLogDTO> getAllLogs();

    DesireLogDTO markAsCheckedById(Long id);

    List<DesireLogDTO> getByCheckStatus(Boolean checkStatus);

    DesireLogDTO getById(Long id);

    Set<UserDTO> getUsersByLogId(Long id);
}