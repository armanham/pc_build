package com.bdg.pc_build.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternalHardDriveRepository extends JpaRepository<InternalHardDrive, String> {
}
