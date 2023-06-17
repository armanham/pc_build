package com.bdg.pc_build.product.repository.main_component;

import com.bdg.pc_build.product.model.entity.main_component.CPUCooler;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CPUCoolerDAO extends ProductDAO<CPUCooler> {

    List<CPUCooler> findAllByFanRpmBetween(Integer minFanRpm, Integer maxFanRpm);

    List<CPUCooler> findAllByTdpBetween(Integer minTdp, Integer maxTdp);

    @Query(
            "SELECT p FROM CPUCooler p " +
                    "WHERE :term IS NULL " +
                    "OR CONCAT(p.name, ' ', p.tdp, ' ', p.fanRpm, ' ', p.socketType) " +
                    "LIKE CONCAT('%', :term, '%') "
    )
    List<CPUCooler> findAllCpuCoolersBasedOnTerm(
            @Param("term") String term
    );

    @Query("select max(p.tdp) from CPUCooler p")
    Integer getMaxTdpOfCpuCoolers();

    @Query("select min(p.tdp) from CPUCooler p")
    Integer getMinTdpOfCpuCoolers();

    @Query("select max(p.fanRpm) from CPUCooler p")
    Integer getMaxFanRpmOfCpuCoolers();

    @Query("select min(p.fanRpm) from CPUCooler p")
    Integer getMinFanRpmOfCpuCoolers();
}