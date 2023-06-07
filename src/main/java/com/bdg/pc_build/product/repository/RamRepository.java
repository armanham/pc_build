package com.bdg.pc_build.product.repository;

import com.bdg.pc_build.product.model.entity.Ram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RamRepository extends JpaRepository<Ram, String> {
}