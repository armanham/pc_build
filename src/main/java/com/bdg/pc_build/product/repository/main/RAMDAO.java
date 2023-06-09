package com.bdg.pc_build.product.repository.main;

import com.bdg.pc_build.product.enumerations.DDRType;
import com.bdg.pc_build.product.model.entity.main.RAM;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RAMDAO extends ProductDAO<RAM> {

    List<RAM> findAllByCountOfRamBetween(Integer minCountOfRam, Integer maxCountOfRam);

    List<RAM> findAllByGbOfRamBetween(Integer minGbOfRam, Integer maxGbOfRam);

    List<RAM> findAllByTdpBetween(Integer minTdp, Integer maxTdp);

    @Query("SELECT p FROM RAM p WHERE " +
            "(:ddrType IS NULL OR p.ddrType = :ddrType) ")
    List<RAM> findAllByDdrType(DDRType ddrType);

    @Query("SELECT p FROM RAM p " +
            "WHERE :term IS NULL " +
            "OR lower(CONCAT(p.name, ' ', p.ddrType, ' ', p.countOfRam, ' ', p.gbOfRam, " +
            "' ', p.tdp)) " +
            "LIKE CONCAT('%', :term, '%') ")
    List<RAM> findAllBasedOnTerm(@Param("term") String term);

    @Query("select max(p.tdp) from RAM p")
    Integer getMaxTdpOfRams();

    @Query("select min(p.tdp) from RAM p")
    Integer getMinTdpOfRams();

    @Query("select min(p.countOfRam) from RAM p")
    Integer getMinCountOfRamOfRams();

    @Query("select max(p.countOfRam) from RAM p")
    Integer getMaxCountOfRamOfRams();

    @Query("select min(p.gbOfRam) from RAM p")
    Integer getMinGbOfRamOfRams();

    @Query("select max(p.gbOfRam) from RAM p")
    Integer getMaxGbOfRamOfRams();
}