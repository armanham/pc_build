package com.bdg.pc_build.product.repository.peripheral;

import com.bdg.pc_build.product.model.entity.main_component.InternalHardDrive;
import com.bdg.pc_build.product.model.entity.peripheral.ExternalHardDrive;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalHardDriveDAO extends ProductDAO<ExternalHardDrive> {
}