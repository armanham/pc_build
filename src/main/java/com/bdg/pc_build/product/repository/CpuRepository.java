package com.bdg.pc_build.product.repository;

import com.bdg.pc_build.product.model.entity.Cpu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CpuRepository extends JpaRepository<Cpu, String> {
}