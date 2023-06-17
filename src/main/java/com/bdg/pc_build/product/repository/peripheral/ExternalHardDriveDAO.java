package com.bdg.pc_build.product.repository.peripheral;

import com.bdg.pc_build.product.model.entity.peripheral.ExternalHardDrive;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExternalHardDriveDAO extends ProductDAO<ExternalHardDrive> {

    List<ExternalHardDrive> findAllByCapacityBetween(Integer minCapacity, Integer maxCapacity);

    List<ExternalHardDrive> findAllByTdpBetween(Integer minTdp, Integer maxTdp);

    @Query("SELECT p FROM ExternalHardDrive p " +
            "WHERE :term IS NULL " +
            "OR lower(CONCAT(p.name, ' ', p.capacity, ' ', p.tdp)) " +
            "LIKE CONCAT('%', :term, '%') ")
    List<ExternalHardDrive> findAllBasedOnTerm(@Param("term") String term);

    @Query("select min(p.tdp) from ExternalHardDrive p ")
    Integer getMinTdpOfExternalHardDrives();

    @Query("select max(p.tdp) from ExternalHardDrive p ")
    Integer getMaxTdpOfExternalHardDrives();

    @Query("select min(p.capacity) from ExternalHardDrive p ")
    Integer getMinCapacityOfExternalHardDrives();

    @Query("select max(p.capacity) from ExternalHardDrive p ")
    Integer getMaxCapacityOfExternalHardDrives();
}