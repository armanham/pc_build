package com.bdg.pc_build.desire_log.service;

import com.bdg.pc_build.checking.exception.ProductAlreadyCheckedException;
import com.bdg.pc_build.checking.exception.ProductNotFoundException;
import com.bdg.pc_build.checking.exception.UserNotFoundException;
import com.bdg.pc_build.config.JwtService;
import com.bdg.pc_build.desire_log.model.dto.DesireLogDTO;
import com.bdg.pc_build.desire_log.model.entity.DesireLog;
import com.bdg.pc_build.desire_log.repository.DesireLogDAO;
import com.bdg.pc_build.user.model.entity.User;
import com.bdg.pc_build.user.repository.UserDAO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional
@Service
public class DesireLogServiceImpl implements DesireLogService {

    JwtService jwtService;
    UserDAO userDAO;
    DesireLogDAO desireLogDAO;

    @Override
    public DesireLogDTO save(final String authHeader, final DesireLogDTO dto) {
        User user = getUserByAuthHeader(authHeader);
        Optional<DesireLog> optionalDesireLog =
                desireLogDAO
                        .findByNameAndComponentTypeAndDescriptionAndChecked(dto.getName(), dto.getComponentType(), dto.getDescription(), false);
        if (optionalDesireLog.isEmpty()) {
            DesireLog desireLogToSave = new DesireLog(dto);
            desireLogToSave.addUser(user);
            return new DesireLogDTO(desireLogDAO.save(desireLogToSave));
        }

        DesireLog desireLog = optionalDesireLog.get();

        if (desireLog.getChecked()) {
            DesireLog desireLogToSave = new DesireLog(dto);
            desireLogToSave.addUser(user);
            return new DesireLogDTO(desireLogDAO.save(desireLogToSave));
        }
        if (desireLog.getUsers().contains(user)) {
            throw new IllegalArgumentException();//TODO
        }
        desireLog.setCount(desireLog.getCount() + 1);
        desireLog.addUser(user);
        return new DesireLogDTO(desireLog);
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
            throw new ProductNotFoundException("Product with the given id: " + id + " in desire log not found: ");
        }

        DesireLog desireLog = optionalDesireLog.get();
        if (desireLog.getChecked()) {
            throw new ProductAlreadyCheckedException("Product with the given id: " + id + " in desire log already checked: ");
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
            throw new ProductNotFoundException("Product with the given id: " + id + " in desire log not found: ");
        }
        return new DesireLogDTO(optionalDesireLog.get());
    }

    private User getUserByAuthHeader(final String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException(); //TODO
        }
        final String token = authHeader.substring(7);
        final String email = jwtService.extractUsername(token);

        User user = userDAO.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));

        if (!jwtService.isTokenValid(token, user)) {
            throw new IllegalArgumentException(); //TODO
        }
        return user;
    }
}