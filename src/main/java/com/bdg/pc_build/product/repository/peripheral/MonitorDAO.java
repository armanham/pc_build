package com.bdg.pc_build.product.repository.peripheral;

import com.bdg.pc_build.product.model.entity.peripheral.Monitor;
import com.bdg.pc_build.product.model.enumerations.MonitorScreenType;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface MonitorDAO extends ProductDAO<Monitor> {

    @Query(
            "SELECT p FROM Monitor p WHERE " +
                    "(:name IS NULL OR lower(p.name) LIKE lower(concat('%', :name, '%'))) " +
                    "AND (p.price BETWEEN :minPrice AND :maxPrice) " +
                    "AND (p.screenSize BETWEEN :minScreenSize AND :maxScreenSize) " +
                    "AND (p.refreshRate BETWEEN :minRefreshRate AND :maxRefreshRate) " +
                    "AND ((:screenTypes) IS NULL OR p.screenType IN (:screenTypes))"
    )
    List<Monitor> filterAllMonitorsBasedOnSpecification(
            @Param("name") String name,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("minScreenSize") Double minScreenSize,
            @Param("maxScreenSize") Double maxScreenSize,
            @Param("minRefreshRate") Integer minRefreshRate,
            @Param("maxRefreshRate") Integer maxRefreshRate,
            @Param("screenTypes") Set<MonitorScreenType> screenTypes
    );

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