package com.bdg.pc_build.product.repository;

import com.bdg.pc_build.product.model.entity.Motherboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotherboardRepository extends JpaRepository<Motherboard, String> {
}
