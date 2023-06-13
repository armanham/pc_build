package com.bdg.pc_build.product.repository.main_component;

import com.bdg.pc_build.product.model.entity.main_component.CPU;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CPUDAO extends ProductDAO<CPU> {

    @Query(
            "SELECT p FROM CPU p " +
                    "WHERE :term IS NULL " +
                    "OR CONCAT(p.name, ' ', p.socketType, ' ', p.tdp, " +
                    "' ', p.boostClock, ' ', p.coreClock, ' ', p.coreCount, ' ', p.integratedGraphics) " +
                    "LIKE CONCAT('%', :term, '%') "
    )
    List<CPU> findAllCPUsBasedOnTerm(
            @Param("term") String term
    );

    @Query("select max(p.tdp) from CPU p")
    Integer getMaxTdpOfCPUs();

    @Query("select min(p.tdp) from CPU p")
    Integer getMinTdpOfCPUs();

    @Query("select max(p.boostClock) from CPU p")
    Double getMaxBoostClockOfCPUs();

    @Query("select min(p.boostClock) from CPU p")
    Double getMinBoostClockOfCPUs();

    @Query("select max(p.coreClock) from CPU p")
    Double getMaxCoreClockOfCPUs();

    @Query("select min(p.coreClock) from CPU p")
    Double getMinCoreClockOfCPUs();

    @Query("select max(p.coreCount) from CPU p")
    Integer getMaxCoreCountOfCPUs();

    @Query("select min(p.coreCount) from CPU p")
    Integer getMinCoreCountOfCPUs();
}