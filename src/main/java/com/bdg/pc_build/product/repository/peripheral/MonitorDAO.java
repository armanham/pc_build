package com.bdg.pc_build.product.repository.peripheral;

import com.bdg.pc_build.product.model.entity.main_component.GPU;
import com.bdg.pc_build.product.model.entity.peripheral.Monitor;
import com.bdg.pc_build.product.model.enumerations.GPUInterfaceType;
import com.bdg.pc_build.product.model.enumerations.MonitorScreenType;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface MonitorDAO extends ProductDAO<Monitor> {

    List<Monitor> findAllByScreenSizeBetween(Double minScreenSize, Double maxScreenSize);

    List<Monitor> findAllByRefreshRateBetween(Double minRefreshRate, Double maxRefreshRate);

    @Query(
            "SELECT p FROM Monitor p WHERE " +
                    "(:monitorScreenType IS NULL OR p.screenType = :monitorScreenType) "
    )
    List<Monitor> findAllByMonitorScreenType(@Param("monitorScreenType") MonitorScreenType monitorScreenType);

    @Query(
            "SELECT p FROM Monitor p " +
                    "WHERE :term IS NULL " +
                    "OR CONCAT(p.name, ' ', p.screenSize, ' ', p.refreshRate, ' ', p.screenType) " +
                    "LIKE CONCAT('%', :term, '%') "
    )
    List<Monitor> findAllMonitorsBasedOnTerm(
            @Param("term") String term
    );

    @Query("select min(p.screenSize) from Monitor p ")
    Double getMinScreenSizeOfMonitors();

    @Query("select max(p.screenSize) from Monitor p ")
    Double getMaxScreenSizeOfMonitors();

    @Query("select min(p.refreshRate) from Monitor p ")
    Integer getMinRefreshRateOfMonitors();

    @Query("select max(p.refreshRate) from Monitor p ")
    Integer getMaxRefreshRateOfMonitors();
}