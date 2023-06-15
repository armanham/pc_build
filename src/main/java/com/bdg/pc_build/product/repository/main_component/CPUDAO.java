package com.bdg.pc_build.product.repository.main_component;

import com.bdg.pc_build.product.model.entity.main_component.CPU;
import com.bdg.pc_build.product.model.entity.main_component.aCase;
import com.bdg.pc_build.product.model.enumerations.SocketType;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CPUDAO extends ProductDAO<CPU> {

    @Query(
            "SELECT p FROM CPU p WHERE " +
                    "(:name IS NULL OR lower(p.name) LIKE lower(concat('%', :name, '%'))) " +
                    "AND (p.price BETWEEN :minPrice AND :maxPrice) " +
                    "AND (p.coreCount BETWEEN :minCoreCount AND :maxCoreCount) " +
                    "AND (p.coreClock BETWEEN :minCoreClock AND :maxCoreClock) " +
                    "AND (p.boostClock BETWEEN :minBoostClock AND :maxBoostClock) " +
                    "AND (p.tdp BETWEEN :minTdp AND :maxTdp) " +
                    "AND ((:integratedGraphics) is null or p.integratedGraphics in (:integratedGraphics)) " +
                    "AND ((:socketTypes) is null or p.socketType in (:socketTypes)) "
    )
    List<CPU> filterAllCpusBasedOnSpecification(
            @Param("name") String name,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("minCoreCount") Integer minCoreCount,
            @Param("maxCoreCount") Integer maxCoreCount,
            @Param("minCoreClock") Double minCoreClock,
            @Param("maxCoreClock") Double maxCoreClock,
            @Param("minBoostClock") Double minBoostClock,
            @Param("maxBoostClock") Double maxBoostClock,
            @Param("minTdp") Integer minTdp,
            @Param("maxTdp") Integer maxTdp,
            @Param("integratedGraphics") Set<String> integratedGraphics,
            @Param("socketTypes") Set<SocketType> socketTypes
    );

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