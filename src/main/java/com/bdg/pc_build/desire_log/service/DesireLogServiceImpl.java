package com.bdg.pc_build.desire_log.service;

import com.bdg.pc_build.checking.exception.ProductAlreadyCheckedException;
import com.bdg.pc_build.checking.exception.ProductNotFoundException;
import com.bdg.pc_build.desire_log.model.dto.DesireLogDTO;
import com.bdg.pc_build.desire_log.model.entity.DesireLog;
import com.bdg.pc_build.desire_log.repository.DesireLogDAO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class DesireLogServiceImpl implements DesireLogService {

    DesireLogDAO desireLogDAO;

    @Override
    public DesireLogDTO save(final DesireLogDTO dto) {
        Optional<DesireLog> optionalDesireLog =
                desireLogDAO
                        .findByNameAndComponentTypeAndDescriptionAndChecked(dto.getName(), dto.getComponentType(), dto.getDescription(), false);
        if (optionalDesireLog.isEmpty()) {
            return new DesireLogDTO(desireLogDAO.save(new DesireLog(dto)));
        }

        DesireLog desireLog = optionalDesireLog.get();
        desireLog.setCount(desireLog.getCount() + 1);
        return new DesireLogDTO(desireLogDAO.save(desireLog));
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
}