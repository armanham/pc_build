package com.bdg.pc_build.product.repository.main_component;

import com.bdg.pc_build.product.model.entity.main_component.aCase;
import com.bdg.pc_build.product.model.entity.peripheral.Monitor;
import com.bdg.pc_build.product.model.enumerations.TowerType;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface CaseDAO extends ProductDAO<aCase> {

    @Query(
            "SELECT p FROM aCase p WHERE " +
                    "(:name IS NULL OR lower(p.name) LIKE lower(concat('%', :name, '%'))) " +
                    "AND (p.price BETWEEN :minPrice AND :maxPrice) " +
                    "AND (p.maxCPUCoolerHeight BETWEEN :minCPUCoolerHeight AND :maxCPUCoolerHeight) " +
                    "AND (p.maxGPULength BETWEEN :minGPULength AND :maxGPULength) " +
                    "AND (p.preInstalledFans BETWEEN :minPreInstalledFans AND :maxPreInstalledFans) " +
                    "AND ((:towerTypes) IS NULL OR p.towerType IN (:towerTypes) )"
    )
    List<aCase> filterAllCasesBasedOnSpecification(
            @Param("name") String name,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("minCPUCoolerHeight") Double minCPUCoolerHeight,
            @Param("maxCPUCoolerHeight") Double maxCPUCoolerHeight,
            @Param("minGPULength") Double minGPULength,
            @Param("maxGPULength") Double maxGPULength,
            @Param("minPreInstalledFans") Integer minPreInstalledFans,
            @Param("maxPreInstalledFans") Integer maxPreInstalledFans,
            @Param("towerTypes") List<TowerType> towerTypes
    );

    @Query(
            "SELECT p FROM aCase p " +
                    "WHERE :term IS NULL " +
                    "OR CONCAT(p.name, ' ', p.maxCPUCoolerHeight, ' ', p.maxGPULength, " +
                    "' ', p.preInstalledFans, ' ', p.towerType) " +
                    "LIKE CONCAT('%', :term, '%') "
    )
    List<aCase> findAllCasesBasedOnTerm(
            @Param("term") String term
    );

    @Query("select max(p.maxCPUCoolerHeight) from aCase p")
    Double getMaxCpuCoolerHeightOfCases();

    @Query("select min(p.maxCPUCoolerHeight) from aCase p")
    Double getMinCpuCoolerHeightOfCases();

    @Query("select max(p.maxGPULength) from aCase p")
    Double getMaxGpuLengthOfCases();

    @Query("select min(p.maxGPULength) from aCase p")
    Double getMinGpuLengthOfCases();

    @Query("select max(p.preInstalledFans) from aCase p")
    Integer getMaxPreInstalledFansOfCases();

    @Query("select min(p.preInstalledFans) from aCase p")
    Integer getMinPreInstalledFansOfCases();
}