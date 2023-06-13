package com.bdg.pc_build.product.repository.main_component;

import com.bdg.pc_build.product.model.entity.main_component.aCase;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaseDAO extends ProductDAO<aCase> {

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
    Double getMaxGpuCoolerHeightOfCases();

    @Query("select min(p.maxCPUCoolerHeight) from aCase p")
    Double getMinGpuCoolerHeightOfCases();

    @Query("select max(p.maxGPULength) from aCase p")
    Double getMaxGpuLengthOfCases();

    @Query("select min(p.maxGPULength) from aCase p")
    Double getMinGpuLengthOfCases();

    @Query("select max(p.preInstalledFans) from aCase p")
    Integer getMaxPreInstalledFansOfCases();

    @Query("select min(p.preInstalledFans) from aCase p")
    Integer getMinPreInstalledFansOfCases();
}