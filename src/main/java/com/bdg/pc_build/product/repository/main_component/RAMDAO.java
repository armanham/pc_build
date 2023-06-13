package com.bdg.pc_build.product.repository.main_component;

import com.bdg.pc_build.product.model.entity.main_component.RAM;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RAMDAO extends ProductDAO<RAM> {

    @Query(
            "SELECT p FROM RAM p " +
                    "WHERE :term IS NULL " +
                    "OR CONCAT(p.name, ' ', p.speed, ' ', p.countOfRam, ' ', p.gbOfRam, " +
                    "' ', p.tdp) " +
                    "LIKE CONCAT('%', :term, '%') "
    )
    List<RAM> findAllRamsBasedOnTerm(
            @Param("term") String term
    );

    @Query("select max(p.tdp) from RAM p")
    Integer getMaxTdpOfRams();

    @Query("select min(p.tdp) from RAM p")
    Integer getMinTdpOfRams();

    @Query("select min(p.speed) from RAM p")
    Integer getMinSpeedOfRams();

    @Query("select max(p.speed) from RAM p")
    Integer getMaxSpeedOfRams();

    @Query("select min(p.countOfRam) from RAM p")
    Integer getMinCountOfRamOfRams();

    @Query("select max(p.countOfRam) from RAM p")
    Integer getMaxCountOfRamOfRams();

    @Query("select min(p.gbOfRam) from RAM p")
    Double getMinGbOfRamOfRams();

    @Query("select max(p.gbOfRam) from RAM p")
    Double getMaxGbOfRamOfRams();
}