package com.bdg.pc_build.product.repository.main_component;

import com.bdg.pc_build.product.model.entity.main_component.RAM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RAMRepository extends JpaRepository<RAM, String> {
}