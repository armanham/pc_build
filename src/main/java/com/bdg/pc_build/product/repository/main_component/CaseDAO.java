package com.bdg.pc_build.product.repository.main_component;

import com.bdg.pc_build.product.model.entity.main_component.aCase;
import com.bdg.pc_build.product.model.enumerations.TowerType;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CaseDAO extends ProductDAO<aCase> {

    @Query(
            "SELECT p FROM aCase p WHERE " +
                    "(:name IS NULL OR lower(p.name) LIKE lower(concat('%', :name, '%'))) " +
                    "AND (p.price BETWEEN :minPrice AND :maxPrice) " +
                    "AND (p.maxCpuCoolerHeight BETWEEN :minCpuCoolerHeight AND :maxCpuCoolerHeight) " +
                    "AND (p.maxGpuLength BETWEEN :minGpuLength AND :maxGpuLength) " +
                    "AND (p.preInstalledFans BETWEEN :minPreInstalledFans AND :maxPreInstalledFans) " +
                    "AND ((:towerTypes) IS NULL OR p.towerType IN (:towerTypes) )"
    )
    List<aCase> filterAllCasesBasedOnSpecification(
            @Param("name") String name,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("minCpuCoolerHeight") Double minCpuCoolerHeight,
            @Param("maxCpuCoolerHeight") Double maxCpuCoolerHeight,
            @Param("minGpuLength") Double minGpuLength,
            @Param("maxGpuLength") Double maxGpuLength,
            @Param("minPreInstalledFans") Integer minPreInstalledFans,
            @Param("maxPreInstalledFans") Integer maxPreInstalledFans,
            @Param("towerTypes") Set<TowerType> towerTypes
    );

    @Query(
            "SELECT p FROM aCase p " +
                    "WHERE :term IS NULL " +
                    "OR CONCAT(p.name, ' ', p.maxCpuCoolerHeight, ' ', p.maxGpuLength, " +
                    "' ', p.preInstalledFans, ' ', p.towerType) " +
                    "LIKE CONCAT('%', :term, '%') "
    )
    List<aCase> findAllCasesBasedOnTerm(
            @Param("term") String term
    );

    @Query("select max(p.maxCpuCoolerHeight) from aCase p")
    Double getMaxCpuCoolerHeightOfCases();

    @Query("select min(p.maxCpuCoolerHeight) from aCase p")
    Double getMinCpuCoolerHeightOfCases();

    @Query("select max(p.maxGpuLength) from aCase p")
    Double getMaxGpuLengthOfCases();

    @Query("select min(p.maxGpuLength) from aCase p")
    Double getMinGpuLengthOfCases();

    @Query("select max(p.preInstalledFans) from aCase p")
    Integer getMaxPreInstalledFansOfCases();

    @Query("select min(p.preInstalledFans) from aCase p")
    Integer getMinPreInstalledFansOfCases();
}