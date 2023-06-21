package com.bdg.pc_build.product.repository.main_component;

import com.bdg.pc_build.product.model.entity.main_component.aCase;
import com.bdg.pc_build.product.enumerations.TowerType;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaseDAO extends ProductDAO<aCase> {

    List<aCase> findAllByMaxCpuCoolerHeightBetween(Double minCpuCoolerHeight, Double maxCpuCoolerHeight);

    List<aCase> findAllByMaxGpuLengthBetween(Double minGpuLength, Double maxGpuLength);

    List<aCase> findAllByPreInstalledFansBetween(Integer minPreInstalledFans, Integer maxPreInstalledFans);

    @Query("SELECT p FROM aCase p WHERE " +
                    "(:towerType IS NULL OR p.towerType = :towerType) ")
    List<aCase> findAllByTowerType(@Param("towerType") TowerType towerType);

    @Query("SELECT p FROM aCase p " +
                    "WHERE :term IS NULL " +
                    "OR lower(CONCAT(p.name, ' ', p.maxCpuCoolerHeight, ' ', p.maxGpuLength, " +
                    "' ', p.preInstalledFans, ' ', p.towerType)) " +
                    "LIKE CONCAT('%', :term, '%') ")
    List<aCase> findAllBasedOnTerm(@Param("term") String term);

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