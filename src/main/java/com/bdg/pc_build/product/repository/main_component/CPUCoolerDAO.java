package com.bdg.pc_build.product.repository.main_component;

import com.bdg.pc_build.product.model.entity.main_component.CPUCooler;
import com.bdg.pc_build.product.model.entity.main_component.aCase;
import com.bdg.pc_build.product.model.enumerations.SocketType;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CPUCoolerDAO extends ProductDAO<CPUCooler> {

    @Query(
            "SELECT p FROM CPUCooler p WHERE " +
                    "(:name IS NULL OR lower(p.name) LIKE lower(concat('%', :name, '%'))) " +
                    "AND (p.price BETWEEN :minPrice AND :maxPrice) " +
                    "AND (p.fanRpm BETWEEN :minFanRpm AND :maxFanRpm) " +
                    "AND (p.tdp BETWEEN :minTdp AND :maxTdp) " +
                    "AND ((:socketTypes) is null or p.socketType in (:socketTypes)) "
    )
    List<CPUCooler> filterAllCpuCoolersBasedOnSpecification(
            @Param("name") String name,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("minFanRpm") Integer minFanRpm,
            @Param("maxFanRpm") Integer maxFanRpm,
            @Param("minTdp") Integer minTdp,
            @Param("maxTdp") Integer maxTdp,
            @Param("socketTypes") Set<SocketType> socketTypes
    );

    @Query(
            "SELECT p FROM CPUCooler p " +
                    "WHERE :term IS NULL " +
                    "OR CONCAT(p.name, ' ', p.tdp, ' ', p.fanRpm, ' ', p.socketType) " +
                    "LIKE CONCAT('%', :term, '%') "
    )
    List<CPUCooler> findAllCpuCoolersBasedOnTerm(
            @Param("term") String term
    );

    @Query("select max(p.tdp) from CPUCooler p")
    Integer getMaxTdpOfCpuCoolers();

    @Query("select min(p.tdp) from CPUCooler p")
    Integer getMinTdpOfCpuCoolers();

    @Query("select max(p.fanRpm) from CPUCooler p")
    Integer getMaxFanRpmOfCpuCoolers();

    @Query("select min(p.fanRpm) from CPUCooler p")
    Integer getMinFanRpmOfCpuCoolers();
}