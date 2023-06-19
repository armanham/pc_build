package com.bdg.pc_build.desire_log.service;

import com.bdg.pc_build.desire_log.model.dto.DesireLogDTO;

import java.util.List;

public interface DesireLogService {

    DesireLogDTO save(DesireLogDTO dto);

    List<DesireLogDTO> getAllLogs();

    DesireLogDTO markAsCheckedById(Long id);

    List<DesireLogDTO> getByCheckStatus(Boolean checkStatus);

    DesireLogDTO getById(Long id);
}