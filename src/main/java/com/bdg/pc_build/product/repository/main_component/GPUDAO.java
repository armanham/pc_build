package com.bdg.pc_build.product.repository.main_component;

import com.bdg.pc_build.product.model.entity.main_component.GPU;
import com.bdg.pc_build.product.model.entity.main_component.aCase;
import com.bdg.pc_build.product.model.enumerations.GPUInterfaceType;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface GPUDAO extends ProductDAO<GPU> {

    @Query(
            "SELECT p FROM GPU p WHERE " +
                    "(:name IS NULL OR lower(p.name) LIKE lower(concat('%', :name, '%'))) " +
                    "AND (p.price BETWEEN :minPrice AND :maxPrice) " +
                    "AND (p.memory BETWEEN :minMemory AND :maxMemory) " +
                    "AND (p.coreClock BETWEEN :minCoreClock AND :maxCoreClock) " +
                    "AND (p.boostClock BETWEEN :minBoostClock AND :maxBoostClock) " +
                    "AND (p.length BETWEEN :minLength AND :maxLength) " +
                    "AND (p.tdp BETWEEN :minTdp AND :maxTdp) " +
                    "AND ((:gpuInterfaceTypes) is null or p.gpuInterfaceType in (:gpuInterfaceTypes)) "
    )
    List<GPU> filterAllGpusBasedOnSpecification(
            @Param("name") String name,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("minMemory") Integer minMemory,
            @Param("maxMemory") Integer maxMemory,
            @Param("minCoreClock") Double minCoreClock,
            @Param("maxCoreClock") Double maxCoreClock,
            @Param("minBoostClock") Double minBoostClock,
            @Param("maxBoostClock") Double maxBoostClock,
            @Param("minLength") Double minLength,
            @Param("maxLength") Double maxLength,
            @Param("minTdp") Integer minTdp,
            @Param("maxTdp") Integer maxTdp,
            @Param("gpuInterfaceTypes") Set<GPUInterfaceType> gpuInterfaceTypes
    );

    @Query(
            "SELECT p FROM GPU p " +
                    "WHERE :term IS NULL " +
                    "OR CONCAT(p.name, ' ', p.coreClock, ' ', p.boostClock, " +
                    "' ', p.tdp, ' ', p.gpuInterfaceType, ' ', p.length, ' ', p.memory) " +
                    "LIKE CONCAT('%', :term, '%') "
    )
    List<GPU> findAllGpusBasedOnTerm(
            @Param("term") String term
    );

    @Query("select max(p.tdp) from GPU p")
    Integer getMaxTdpOfGpus();

    @Query("select min(p.tdp) from GPU p")
    Integer getMinTdpOfGpus();

    @Query("select max(p.boostClock) from GPU p")
    Double getMaxBoostClockOfGpus();

    @Query("select min(p.boostClock) from GPU p")
    Double getMinBoostClockOfGpus();

    @Query("select max(p.coreClock) from GPU p")
    Double getMaxCoreClockOfGpus();

    @Query("select min(p.coreClock) from GPU p")
    Double getMinCoreClockOfGpus();

    @Query("select max(p.memory) from GPU p")
    Integer getMaxMemoryOfGpus();

    @Query("select min(p.memory) from GPU p")
    Integer getMinMemoryOfGpus();

    @Query("select max(p.length) from GPU p")
    Double getMaxLengthOfGpus();

    @Query("select min(p.length) from GPU p")
    Double getMinLengthOfGpus();
}