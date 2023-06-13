package com.bdg.pc_build.product.repository.main_component;

import com.bdg.pc_build.product.model.entity.main_component.Motherboard;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotherboardDAO extends ProductDAO<Motherboard> {

    @Query(
            "SELECT p FROM Motherboard p " +
                    "WHERE :term IS NULL " +
                    "OR CONCAT(p.name, ' ', p.tdp, ' ', p.gpuInterfaceType, " +
                    "' ', p.socketType, ' ', p.atxType, ' ', p.internalConnections, " +
                    "' ', p.memoryMax, ' ', p.memorySlots, ' ', p.memoryType) " +
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