package com.bdg.pc_build.product.repository.main_component;

import com.bdg.pc_build.product.model.entity.main_component.GPU;
import com.bdg.pc_build.product.model.enumerations.GPUInterfaceType;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GPUDAO extends ProductDAO<GPU> {

    List<GPU> findAllByMemoryBetween(Integer minMemory, Integer maxMemory);

    List<GPU> findAllByCoreClockBetween(Double minCoreClock, Double maxCoreClock);

    List<GPU> findAllByBoostClockBetween(Double minBoostClock, Double maxBoostClock);

    List<GPU> findAllByLengthBetween(Double minLength, Double maxLength);

    List<GPU> findAllByTdpBetween(Integer minTdp, Integer maxTdp);

    @Query("SELECT p FROM GPU p WHERE " +
            "(:gpuInterfaceType IS NULL OR p.gpuInterfaceType = :gpuInterfaceType) ")
    List<GPU> findAllByGpuInterfaceType(@Param("gpuInterfaceType") GPUInterfaceType gpuInterfaceType);

    @Query("SELECT p FROM GPU p " +
            "WHERE :term IS NULL " +
            "OR lower(CONCAT(p.name, ' ', p.coreClock, ' ', p.boostClock, " +
            "' ', p.tdp, ' ', p.gpuInterfaceType, ' ', p.length, ' ', p.memory)) " +
            "LIKE CONCAT('%', :term, '%') ")
    List<GPU> findAllBasedOnTerm(@Param("term") String term);

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