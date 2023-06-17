package com.bdg.pc_build.product.repository.main_component;

import com.bdg.pc_build.product.model.entity.main_component.Motherboard;
import com.bdg.pc_build.product.model.enumerations.ATXType;
import com.bdg.pc_build.product.model.enumerations.DDRType;
import com.bdg.pc_build.product.model.enumerations.GPUInterfaceType;
import com.bdg.pc_build.product.model.enumerations.SocketType;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotherboardDAO extends ProductDAO<Motherboard> {

    List<Motherboard> findAllByMemoryMaxBetween(Integer minMemory, Integer maxMemory);

    List<Motherboard> findAllByMemorySlotsBetween(Integer minMemorySlots, Integer maxMemorySlots);

    List<Motherboard> findAllByTdpBetween(Integer minTdp, Integer maxTdp);

    @Query(
            "SELECT p FROM Motherboard p WHERE " +
                    "(:isM2 IS NULL OR p.isM2 = :isM2) "
    )
    List<Motherboard> findAllByIsM2(@Param("isM2") Boolean isM2);

    @Query(
            "SELECT p FROM Motherboard p WHERE " +
                    "(:ddrType IS NULL OR p.ddrType = :ddrType) "
    )
    List<Motherboard> findAllByDdrType(@Param("ddrType") DDRType ddrType);

    @Query(
            "SELECT p FROM Motherboard p WHERE " +
                    "(:gpuInterfaceType IS NULL OR p.gpuInterfaceType = :gpuInterfaceType) "
    )
    List<Motherboard> findAllByGpuInterfaceType(@Param("gpuInterfaceType") GPUInterfaceType gpuInterfaceType);

    @Query(
            "SELECT p FROM Motherboard p WHERE " +
                    "(:socketType IS NULL OR p.socketType = :socketType) "
    )
    List<Motherboard> findAllBySocketType(@Param("socketType") SocketType socketType);

    @Query(
            "SELECT p FROM Motherboard p WHERE " +
                    "(:atxType IS NULL OR p.atxType = :atxType) "
    )
    List<Motherboard> findAllByAtxType(@Param("atxType") ATXType atxType);

    @Query(
            "SELECT p FROM Motherboard p " +
                    "WHERE :term IS NULL " +
                    "OR lower(CONCAT(p.name, ' ', p.tdp, ' ', p.gpuInterfaceType, " +
                    "' ', p.socketType, ' ', p.atxType, ' ', p.isM2, " +
                    "' ', p.memoryMax, ' ', p.memorySlots, ' ', p.ddrType)) " +
                    "LIKE CONCAT('%', :term, '%') "
    )
    List<Motherboard> findAllBasedOnTerm(
            @Param("term") String term
    );

    @Query("select max(p.tdp) from Motherboard p")
    Integer getMaxTdpOfMotherboards();

    @Query("select min(p.tdp) from Motherboard p")
    Integer getMinTdpOfMotherboards();

    @Query("select max(p.memoryMax) from Motherboard p")
    Integer getMaxMemoryOfMotherboards();

    @Query("select min(p.memoryMax) from Motherboard p")
    Integer getMinMemoryOfMotherboards();

    @Query("select max(p.memorySlots) from Motherboard p")
    Integer getMaxMemorySlotsOfMotherboards();

    @Query("select min(p.memorySlots) from Motherboard p")
    Integer getMinMemorySlotsOfMotherboards();
}