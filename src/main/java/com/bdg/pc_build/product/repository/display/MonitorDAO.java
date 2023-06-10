package com.bdg.pc_build.product.repository.display;

import com.bdg.pc_build.product.model.entity.display.Monitor;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitorDAO extends ProductDAO<Monitor> {

//    @Query("SELECT m FROM Monitor m WHERE " +
//            "(:name IS NULL OR lower(p.name) LIKE lower(concat('%', :name, '%'))) " +
//            "AND ((:minPrice IS NULL AND :maxPrice IS NULL) " +
//            "OR (p.price BETWEEN COALESCE(:minPrice, 0) AND COALESCE(:maxPrice, 1000000)))")
    List<Monitor> filterAllMonitorsBasedOnSpecification(
            @Param("screenSize") Double screenSize,
            @Param("refreshRate") Integer refreshRate,
            @Param("screenType") String screenType,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice
    );
}