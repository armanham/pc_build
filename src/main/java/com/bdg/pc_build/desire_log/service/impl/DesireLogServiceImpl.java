package com.bdg.pc_build.desire_log.service.impl;

import com.bdg.pc_build.desire_log.model.dto.DesireLogDTO;
import com.bdg.pc_build.desire_log.model.entity.DesireLog;
import com.bdg.pc_build.desire_log.repository.DesireLogDAO;
import com.bdg.pc_build.desire_log.service.DesireLogService;
import com.bdg.pc_build.exception.ProductAlreadyCheckedInDesireLogException;
import com.bdg.pc_build.exception.ProductAlreadyReportedInDesireLogException;
import com.bdg.pc_build.exception.ProductNotFoundException;
import com.bdg.pc_build.user.model.dto.UserDTO;
import com.bdg.pc_build.user.model.entity.User;
import com.bdg.pc_build.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class DesireLogServiceImpl implements DesireLogService {

    private final DesireLogDAO desireLogDAO;
    private final UserService userService;

    @Override
    public DesireLogDTO save(final String authHeader, final DesireLogDTO dto) {
        User user = userService.getUserByAuthHeader(authHeader);
        Optional<DesireLog> optionalDesireLog =
                desireLogDAO
                        .findByNameAndComponentTypeAndDescriptionAndChecked(dto.getName(), dto.getComponentType(), dto.getDescription(), false);
        if (optionalDesireLog.isEmpty()) {
            DesireLog desireLogToSave = new DesireLog(dto);
            desireLogToSave.addUser(user);
            return new DesireLogDTO(desireLogDAO.save(desireLogToSave));
        }

        DesireLog findedDesireLog = optionalDesireLog.get();

        if (findedDesireLog.getChecked()) {
            DesireLog duplicateDesireLogToSave = new DesireLog(dto);
            duplicateDesireLogToSave.addUser(user);
            return new DesireLogDTO(desireLogDAO.save(duplicateDesireLogToSave));
        }
        if (findedDesireLog.getUsers().contains(user)) {
            throw new ProductAlreadyReportedInDesireLogException(HttpStatus.ALREADY_REPORTED, user.getEmail());
        }
        findedDesireLog.setCount(findedDesireLog.getCount() + 1);
        findedDesireLog.addUser(user);
        return new DesireLogDTO(desireLogDAO.save(findedDesireLog));
    }

    @Override
    public List<DesireLogDTO> getAllLogs() {
        return desireLogDAO
                .findAll()
                .stream()
                .map(DesireLogDTO::new)
                .toList();
    }

    @Override
    public DesireLogDTO markAsCheckedById(final Long id) {
        Optional<DesireLog> optionalDesireLog = desireLogDAO.findById(id);
        if (optionalDesireLog.isEmpty()) {
            throw new ProductNotFoundException(HttpStatus.NOT_FOUND, DesireLog.class, id);
        }

        DesireLog desireLog = optionalDesireLog.get();
        if (desireLog.getChecked()) {
            throw new ProductAlreadyCheckedInDesireLogException(HttpStatus.NOT_ACCEPTABLE, id);
        }
        desireLog.setChecked(true);
        return new DesireLogDTO(desireLogDAO.save(desireLog));
    }

    @Override
    public List<DesireLogDTO> getByCheckStatus(final Boolean checkStatus) {
        return desireLogDAO.findAllByChecked(checkStatus)
                .stream()
                .map(DesireLogDTO::new)
                .toList();
    }

    @Override
    public DesireLogDTO getById(final Long id) {
        Optional<DesireLog> optionalDesireLog = desireLogDAO.findById(id);
        if (optionalDesireLog.isEmpty()) {
            throw new ProductNotFoundException(HttpStatus.NOT_FOUND, DesireLog.class, id);
        }
        return new DesireLogDTO(optionalDesireLog.get());
    }

    @Override
    public Set<UserDTO> getUsersByLogId(final Long id) {
        DesireLog desireLog = desireLogDAO.findById(id).orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, DesireLog.class, id));

        return desireLog.getUsers()
                .stream()
                .map(UserDTO::new)
                .collect(Collectors.toSet());
    }
}