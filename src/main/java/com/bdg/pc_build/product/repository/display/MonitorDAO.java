package com.bdg.pc_build.product.repository.display;

import com.bdg.pc_build.product.model.entity.display.Monitor;
import com.bdg.pc_build.product.model.enumerations.MonitorScreenType;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Repository
public interface MonitorDAO extends ProductDAO<Monitor> {

    @Query(
            "SELECT p FROM Monitor p WHERE " +
                    "(:name IS NULL OR lower(p.name) LIKE lower(concat('%', :name, '%'))) " +
                    "AND ((:minPrice IS NULL AND :maxPrice IS NULL) " +
                    "OR (p.price BETWEEN COALESCE(:minPrice, 0) AND COALESCE(:maxPrice, 10000))) " +
                    "AND ((:minScreenSize IS NULL AND :maxScreenSize IS NULL) " +
                    "OR (p.screenSize BETWEEN COALESCE(:minScreenSize, 0) AND COALESCE(:maxScreenSize, 100))) " +
                    "AND ((:minRefreshRate IS NULL AND :maxRefreshRate IS NULL) " +
                    "OR (p.refreshRate BETWEEN COALESCE(:minRefreshRate, 0) AND COALESCE(:maxRefreshRate, 240))) " +
                    "AND (:screenTypes IS NULL OR p.screenType in (:screenTypes))"
    )
    List<Monitor> filterAllMonitorsBasedOnSpecification(
            @Param("name") String name,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("minScreenSize") Double minScreenSize,
            @Param("maxScreenSize") Double maxScreenSize,
            @Param("minRefreshRate") Integer minRefreshRate,
            @Param("maxRefreshRate") Integer maxRefreshRate,
            @Param("screenTypes") String screenTypes
    );
}