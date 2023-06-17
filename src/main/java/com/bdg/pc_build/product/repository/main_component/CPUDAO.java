package com.bdg.pc_build.product.repository.main_component;

import com.bdg.pc_build.product.model.entity.main_component.CPU;
import com.bdg.pc_build.product.model.enumerations.SocketType;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CPUDAO extends ProductDAO<CPU> {

    List<CPU> findAllByCoreCountBetween(Integer minCoreCount, Integer maxCoreCount);

    List<CPU> findAllByCoreClockBetween(Double minCoreClock, Double maxCoreClock);

    List<CPU> findAllByBoostClockBetween(Double minBoostClock, Double maxBoostClock);

    List<CPU> findAllByTdpBetween(Integer minTdp, Integer maxTdp);

    @Query(
            "SELECT p FROM CPU p WHERE " +
                    "(:socketType IS NULL OR p.socketType = :socketType) "
    )
    List<CPU> findAllBySocketType(@Param("socketType") SocketType socketType);

    @Query(
            "SELECT p FROM CPU p WHERE " +
                    "(:integratedGraphics IS NULL OR p.integratedGraphics = :integratedGraphics) "
    )
    List<CPU> findAllByIntegratedGraphics(@Param("integratedGraphics") String integratedGraphics);

    @Query(
            "SELECT p FROM CPU p " +
                    "WHERE :term IS NULL " +
                    "OR CONCAT(p.name, ' ', p.socketType, ' ', p.tdp, " +
                    "' ', p.boostClock, ' ', p.coreClock, ' ', p.coreCount, ' ', p.integratedGraphics) " +
                    "LIKE CONCAT('%', :term, '%') "
    )
    List<CPU> findAllCpusBasedOnTerm(
            @Param("term") String term
    );

    @Query("select max(p.tdp) from CPU p")
    Integer getMaxTdpOfCpus();

    @Query("select min(p.tdp) from CPU p")
    Integer getMinTdpOfCpus();

    @Query("select max(p.boostClock) from CPU p")
    Double getMaxBoostClockOfCpus();

    @Query("select min(p.boostClock) from CPU p")
    Double getMinBoostClockOfCpus();

    @Query("select max(p.coreClock) from CPU p")
    Double getMaxCoreClockOfCpus();

    @Query("select min(p.coreClock) from CPU p")
    Double getMinCoreClockOfCpus();

    @Query("select max(p.coreCount) from CPU p")
    Integer getMaxCoreCountOfCpus();

    @Query("select min(p.coreCount) from CPU p")
    Integer getMinCoreCountOfCpus();
}