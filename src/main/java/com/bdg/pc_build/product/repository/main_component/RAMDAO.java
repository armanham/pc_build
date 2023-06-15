package com.bdg.pc_build.product.repository.main_component;

import com.bdg.pc_build.product.model.entity.main_component.RAM;
import com.bdg.pc_build.product.model.entity.peripheral.Monitor;
import com.bdg.pc_build.product.model.enumerations.DDRType;
import com.bdg.pc_build.product.model.enumerations.MonitorScreenType;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RAMDAO extends ProductDAO<RAM> {
    @Query(
            "SELECT p FROM RAM p WHERE " +
                    "(:name IS NULL OR lower(p.name) LIKE lower(concat('%', :name, '%'))) " +
                    "AND (p.price BETWEEN :minPrice AND :maxPrice) " +
                    "AND (p.countOfRam BETWEEN :minCountOfRam AND :maxCountOfRam) " +
                    "AND (p.gbOfRam BETWEEN :minGbOfRam AND :maxGbOfRam) " +
                    "AND (p.tdp BETWEEN :minTdp AND :maxTdp) " +
                    "AND ((:ddrTypes) IS NULL OR p.ddrType IN (:ddrTypes))"
    )
    List<RAM> filterAllRamsBasedOnSpecification(
            @Param("name") String name,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("minCountOfRam") Integer minCountOfRam,
            @Param("maxCountOfRam") Integer maxCountOfRam,
            @Param("minGbOfRam") Double minGbOfRam,
            @Param("maxGbOfRam") Double maxGbOfRam,
            @Param("maxTdp") Integer maxTdp,
            @Param("minTdp") Integer minTdp,
            @Param("ddrTypes") Set<DDRType> ddrTypes
    );

    @Query(
            "SELECT p FROM RAM p " +
                    "WHERE :term IS NULL " +
                    "OR CONCAT(p.name, ' ', p.ddrType, ' ', p.countOfRam, ' ', p.gbOfRam, " +
                    "' ', p.tdp) " +
                    "LIKE CONCAT('%', :term, '%') "
    )
    List<RAM> findAllRamsBasedOnTerm(
            @Param("term") String term
    );

    @Query("select max(p.tdp) from RAM p")
    Integer getMaxTdpOfRams();

    @Query("select min(p.tdp) from RAM p")
    Integer getMinTdpOfRams();

    @Query("select min(p.countOfRam) from RAM p")
    Integer getMinCountOfRamOfRams();

    @Query("select max(p.countOfRam) from RAM p")
    Integer getMaxCountOfRamOfRams();

    @Query("select min(p.gbOfRam) from RAM p")
    Double getMinGbOfRamOfRams();

    @Query("select max(p.gbOfRam) from RAM p")
    Double getMaxGbOfRamOfRams();
}