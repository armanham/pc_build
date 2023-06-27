package com.bdg.pc_build.desire_log.repository;

import com.bdg.pc_build.desire_log.model.entity.DesireLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DesireLogDAO extends JpaRepository<DesireLog, Long> {

    List<DesireLog> findAllByChecked(Boolean checked);

    Optional<DesireLog> findByNameAndComponentTypeAndDescriptionAndChecked(String name, String componentType, String description, Boolean checked);
}