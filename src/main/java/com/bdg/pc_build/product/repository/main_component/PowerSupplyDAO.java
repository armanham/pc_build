package com.bdg.pc_build.product.repository.main_component;

import com.bdg.pc_build.product.model.entity.main_component.PowerSupply;
import com.bdg.pc_build.product.model.entity.main_component.RAM;
import com.bdg.pc_build.product.model.enumerations.DDRType;
import com.bdg.pc_build.product.model.enumerations.EfficiencyRating;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PowerSupplyDAO extends ProductDAO<PowerSupply> {

    @Query(
            "SELECT p FROM PowerSupply p WHERE " +
                    "(:name IS NULL OR lower(p.name) LIKE lower(concat('%', :name, '%'))) " +
                    "AND (p.price BETWEEN :minPrice AND :maxPrice) " +
                    "AND (p.wattage BETWEEN :minWattage AND :maxWattage) " +
                    "AND (p.tdp BETWEEN :minTdp AND :maxTdp) " +
                    "AND ((:isModular) is null or p.isModular in (:isModular)) " +
                    "AND ((:efficiencyRating) IS NULL OR p.efficiencyRating IN (:efficiencyRating))"
    )
    List<PowerSupply> filterAllRPowerSuppliesBasedOnSpecification(
            @Param("name") String name,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("minWattage") Integer minWattage,
            @Param("maxWattage") Integer maxWattage,
            @Param("minTdp") Integer minTdp,
            @Param("maxTdp") Integer maxTdp,
            @Param("isModular") Set<Boolean> isModular,
            @Param("efficiencyRating") Set<EfficiencyRating> efficiencyRating
    );

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