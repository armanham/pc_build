package com.bdg.pc_build.product.repository.main_component;

import com.bdg.pc_build.product.model.entity.main_component.CPU;
import com.bdg.pc_build.product.model.entity.main_component.ExternalHardDrive;
import com.bdg.pc_build.product.repository.BaseProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalHardDriveRepository extends BaseProductRepository<ExternalHardDrive> {
}