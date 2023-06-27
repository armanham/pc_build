package com.bdg.pc_build.desire_log.service;

import com.bdg.pc_build.exception.*;
import com.bdg.pc_build.config.JwtService;
import com.bdg.pc_build.desire_log.model.dto.DesireLogDTO;
import com.bdg.pc_build.desire_log.model.entity.DesireLog;
import com.bdg.pc_build.desire_log.repository.DesireLogDAO;
import com.bdg.pc_build.user.model.entity.User;
import com.bdg.pc_build.user.repository.UserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Transactional
@Service
public class DesireLogServiceImpl implements DesireLogService {

    private final JwtService jwtService;
    private final UserDAO userDAO;
    private final DesireLogDAO desireLogDAO;

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

    @Override
    public Set<User> getUsersByLogId(final Long id) {
        DesireLog desireLog = desireLogDAO.findById(id).orElseThrow(()-> new ProductNotFoundException("Product with the given id: " + id + " in desire log not found: "));

        return desireLog.getUsers();
    }


    private User getUserByAuthHeader(final String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new InvalidAuthHeaderException(); //TODO
        }
        final String token = authHeader.substring(7);
        final String email = jwtService.extractUsername(token);

        User user = userDAO.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));

        if (!jwtService.isTokenValid(token, user)) {
            throw new InvalidTokenException(); //TODO
        }
        return user;
    }
}