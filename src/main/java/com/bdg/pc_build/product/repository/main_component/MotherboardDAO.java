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
import java.util.Set;

@Repository
public interface MotherboardDAO extends ProductDAO<Motherboard> {

    @Query(
            "SELECT p FROM Motherboard p WHERE " +
                    "(:name IS NULL OR lower(p.name) LIKE lower(concat('%', :name, '%'))) " +
                    "AND (p.price BETWEEN :minPrice AND :maxPrice) " +
                    "AND (p.memoryMax BETWEEN :minMemory AND :maxMemory) " +
                    "AND (p.memorySlots BETWEEN :minMemorySlots AND :maxMemorySlots) " +
                    "AND (p.tdp BETWEEN :minTdp AND :maxTdp) " +
                    "AND ((:isM2) is null or p.isM2 in (:isM2)) " +
                    "AND ((:ddrTypes) is null or p.ddrType in (:ddrTypes)) " +
                    "AND ((:gpuInterfaceTypes) is null or p.gpuInterfaceType in (:gpuInterfaceTypes)) " +
                    "AND ((:socketTypes) is null or p.socketType in (:socketTypes)) " +
                    "AND ((:atxTypes) is null or p.atxType in (:atxTypes)) "
    )
    List<Motherboard> filterAllMotherboardsBasedOnSpecification(
            @Param("name") String name,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("minMemory") Integer minMemory,
            @Param("maxMemory") Integer maxMemory,
            @Param("minMemorySlots") Integer minMemorySlots,
            @Param("maxMemorySlots") Integer maxMemorySlots,
            @Param("minTdp") Integer minTdp,
            @Param("maxTdp") Integer maxTdp,
            @Param("isM2") Set<Boolean> isM2,
            @Param("ddrTypes") Set<DDRType> ddrTypes,
            @Param("gpuInterfaceTypes") Set<GPUInterfaceType> gpuInterfaceTypes,
            @Param("socketTypes") Set<SocketType> socketTypes,
            @Param("atxTypes") Set<ATXType> atxTypes
    );

    @Query(
            "SELECT p FROM Motherboard p " +
                    "WHERE :term IS NULL " +
                    "OR CONCAT(p.name, ' ', p.tdp, ' ', p.gpuInterfaceType, " +
                    "' ', p.socketType, ' ', p.atxType, ' ', p.isM2, " +
                    "' ', p.memoryMax, ' ', p.memorySlots, ' ', p.ddrType) " +
                    "LIKE CONCAT('%', :term, '%') "
    )
    List<Motherboard> findAllMotherboardsBasedOnTerm(
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