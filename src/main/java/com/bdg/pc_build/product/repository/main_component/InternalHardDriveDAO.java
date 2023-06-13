package com.bdg.pc_build.product.repository.main_component;

import com.bdg.pc_build.product.model.entity.main_component.InternalHardDrive;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternalHardDriveDAO extends ProductDAO<InternalHardDrive> {

    @Query(
            "SELECT p FROM InternalHardDrive p " +
                    "WHERE :term IS NULL " +
                    "OR CONCAT(p.name, ' ', p.tdp, ' ', p.capacity, " +
                    "' ', p.internalHardDriveInterfaceType) " +
                    "LIKE CONCAT('%', :term, '%') "
    )
    List<InternalHardDrive> findAllInternalHardDrivesBasedOnTerm(
            @Param("term") String term
    );

    @Query("select max(p.tdp) from InternalHardDrive p")
    Integer getMaxTdpOfInternalHardDrives();

    @Query("select min(p.tdp) from InternalHardDrive p")
    Integer getMinTdpOfInternalHardDrives();

    @Query("select min(p.capacity) from InternalHardDrive p")
    Double getMinCapacityOfInternalHardDrives();

    @Query("select max(p.capacity) from InternalHardDrive p")
    Double getMaxCapacityOfInternalHardDrives();
}