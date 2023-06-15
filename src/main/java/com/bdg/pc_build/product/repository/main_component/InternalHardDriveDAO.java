package com.bdg.pc_build.product.repository.main_component;

import com.bdg.pc_build.product.model.entity.main_component.InternalHardDrive;
import com.bdg.pc_build.product.model.enumerations.InternalHardDriveInterfaceType;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface InternalHardDriveDAO extends ProductDAO<InternalHardDrive> {

    @Query(
            "SELECT p FROM InternalHardDrive p WHERE " +
                    "(:name IS NULL OR lower(p.name) LIKE lower(concat('%', :name, '%'))) " +
                    "AND (p.price BETWEEN :minPrice AND :maxPrice) " +
                    "AND (p.capacity BETWEEN :minCapacity AND :maxCapacity) " +
                    "AND (p.tdp BETWEEN :minTdp AND :maxTdp) " +
                    "AND ((:internalHardDriveInterfaceTypes) is null or p.internalHardDriveInterfaceType in (:internalHardDriveInterfaceTypes)) "
    )
    List<InternalHardDrive> filterAllInternalHardDrivesBasedOnSpecification(
            @Param("name") String name,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("minCapacity") Integer minCapacity,
            @Param("maxCapacity") Integer maxCapacity,
            @Param("minTdp") Integer minTdp,
            @Param("maxTdp") Integer maxTdp,
            @Param("internalHardDriveInterfaceTypes") Set<InternalHardDriveInterfaceType> internalHardDriveInterfaceTypes
    );

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
    Integer getMinCapacityOfInternalHardDrives();

    @Query("select max(p.capacity) from InternalHardDrive p")
    Integer getMaxCapacityOfInternalHardDrives();
}