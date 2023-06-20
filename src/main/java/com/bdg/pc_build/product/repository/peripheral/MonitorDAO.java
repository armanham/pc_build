package com.bdg.pc_build.product.repository.peripheral;

import com.bdg.pc_build.product.model.entity.peripheral.Monitor;
import com.bdg.pc_build.product.enumerations.MonitorScreenType;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitorDAO extends ProductDAO<Monitor> {

    List<Monitor> findAllByScreenSizeBetween(Double minScreenSize, Double maxScreenSize);

    List<Monitor> findAllByRefreshRateBetween(Integer minRefreshRate, Integer maxRefreshRate);

    @Query("SELECT p FROM Monitor p WHERE " +
            "(:monitorScreenType IS NULL OR p.screenType = :monitorScreenType) ")
    List<Monitor> findAllByMonitorScreenType(@Param("monitorScreenType") MonitorScreenType monitorScreenType);

    @Query("SELECT p FROM Monitor p " +
            "WHERE :term IS NULL " +
            "OR length(:term) = 0 " +
            "OR lower(CONCAT(p.name, ' ', p.screenSize, ' ', p.refreshRate, ' ', p.screenType)) " +
            "LIKE CONCAT('%', :term, '%') ")
    List<Monitor> findAllBasedOnTerm(@Param("term") String term);

    @Query("select min(p.screenSize) from Monitor p ")
    Double getMinScreenSizeOfMonitors();

    @Query("select max(p.screenSize) from Monitor p ")
    Double getMaxScreenSizeOfMonitors();

    @Query("select min(p.refreshRate) from Monitor p ")
    Integer getMinRefreshRateOfMonitors();

    @Query("select max(p.refreshRate) from Monitor p ")
    Integer getMaxRefreshRateOfMonitors();
}