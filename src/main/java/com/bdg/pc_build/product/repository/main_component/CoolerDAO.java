package com.bdg.pc_build.product.repository.main_component;

import com.bdg.pc_build.product.model.entity.main_component.Cooler;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoolerDAO extends ProductDAO<Cooler> {

    List<Cooler> findAllByTdpBetween(Integer minTdp, Integer maxTdp);

    @Query(
            "SELECT p FROM Cooler p " +
                    "WHERE :term IS NULL " +
                    "OR CONCAT(p.name, ' ', p.tdp) " +
                    "LIKE CONCAT('%', :term, '%') "
    )
    List<Cooler> findAllCoolersBasedOnTerm(
            @Param("term") String term
    );

    @Query("select max(p.tdp) from Cooler p")
    Integer getMaxTdpOfCoolers();

    @Query("select min(p.tdp) from Cooler p")
    Integer getMinTdpOfCoolers();
}