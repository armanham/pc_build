package com.bdg.pc_build.product.repository.main_component;
import com.bdg.pc_build.product.model.entity.main_component.GPU;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GPUDAO extends ProductDAO<GPU> {

    @Query(
            "SELECT p FROM GPU p " +
                    "WHERE :term IS NULL " +
                    "OR CONCAT(p.name, ' ', p.coreClock, ' ', p.boostClock, " +
                    "' ', p.tdp, ' ', p.gpuInterfaceType, ' ', p.length, ' ', p.memory) " +
                    "LIKE CONCAT('%', :term, '%') "
    )
    List<GPU> findAllGPUsBasedOnTerm(
            @Param("term") String term
    );

    @Query("select max(p.tdp) from GPU p")
    Integer getMaxTdpOfGPUs();

    @Query("select min(p.tdp) from GPU p")
    Integer getMinTdpOfGPUs();

    @Query("select max(p.boostClock) from GPU p")
    Double getMaxBoostClockOfGPUs();

    @Query("select min(p.boostClock) from GPU p")
    Double getMinBoostClockOfGPUs();

    @Query("select max(p.coreClock) from GPU p")
    Double getMaxCoreClockOfGPUs();

    @Query("select min(p.coreClock) from GPU p")
    Double getMinCoreClockOfGPUs();

    @Query("select max(p.memory) from GPU p")
    Integer getMaxMemoryOfGPUs();

    @Query("select min(p.memory) from GPU p")
    Integer getMinMemoryOfGPUs();

    @Query("select max(p.length) from GPU p")
    Double getMaxLengthOfGPUs();

    @Query("select min(p.length) from GPU p")
    Double getMinLengthOfGPUs();
}