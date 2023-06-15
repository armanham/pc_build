package com.bdg.pc_build.product.repository.main_component;

import com.bdg.pc_build.product.model.entity.main_component.PowerSupply;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PowerSupplyDAO extends ProductDAO<PowerSupply> {

    @Query(
            "SELECT p FROM PowerSupply p " +
                    "WHERE :term IS NULL " +
                    "OR CONCAT(p.name, ' ', p.efficiencyRating, ' ', " +
                    "p.wattage, ' ', p.isModular, ' ', p.tdp) " +
                    "LIKE CONCAT('%', :term, '%') "
    )
    List<PowerSupply> findAllPowerSuppliesBasedOnTerm(
            @Param("term") String term
    );

    @Query("select min(p.wattage) from PowerSupply p")
    Integer getMinWattageOfPowerSupplies();

    @Query("select max(p.wattage) from PowerSupply p")
    Integer getMaxWattageOfPowerSupplies();

    @Query("select min(p.tdp) from PowerSupply p")
    Integer getMinTdpOfPowerSupplies();

    @Query("select max(p.tdp) from PowerSupply p")
    Integer getMaxTdpOfPowerSupplies();
}