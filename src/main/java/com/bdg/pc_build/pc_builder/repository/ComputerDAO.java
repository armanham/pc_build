package com.bdg.pc_build.pc_builder.repository;

import com.bdg.pc_build.pc_builder.model.entity.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComputerDAO extends JpaRepository<Computer, Long> {

    List<Computer> findAllByIsOrderedAndIsFullyConstructed(Boolean isOrdered, Boolean isFullyConstructed);

}