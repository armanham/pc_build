package com.bdg.pc_build.product.repository.peripheral;


import com.bdg.pc_build.product.model.entity.peripheral.Mouse;
import com.bdg.pc_build.product.model.enumerations.ConnectivityType;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MouseDAO extends ProductDAO<Mouse> {

    List<Mouse> findAllByMaxResolutionBetween(Integer minResolution, Integer maxResolution);

    List<Mouse> findAllByCableLengthBetween(Double minCableLength, Double maxCableLength);

    List<Mouse> findAllByWeightBetween(Double minWeight, Double maxWeight);

    @Query("SELECT p FROM Mouse p WHERE " +
                    "(:connectivityType IS NULL OR p.connectivityType = :connectivityType) ")
    List<Mouse> findAllByConnectivityType(@Param("connectivityType") ConnectivityType connectivityType);

    @Query("SELECT p FROM Mouse p " +
                    "WHERE :term IS NULL " +
                    "OR lower(CONCAT(p.name, ' ', p.connectivityType, ' ', " +
                    "p.maxResolution, ' ', p.cableLength, ' ', p.weight)) " +
                    "LIKE CONCAT('%', :term, '%') ")
    List<Mouse> findAllBasedOnTerm(@Param("term") String term);

    @Query("select min(p.maxResolution) from Mouse p ")
    Integer getMinResolutionOfMice();

    @Query("select max(p.maxResolution) from Mouse p ")
    Integer getMaxResolutionOfMice();

    @Query("select min(p.cableLength) from Mouse p ")
    Double getMinCableLengthOfMice();

    @Query("select max(p.cableLength) from Mouse p ")
    Double getMaxCableLengthOfMice();

    @Query("select min(p.weight) from Mouse p ")
    Double getMinWeightOfMice();

    @Query("select max(p.weight) from Mouse p ")
    Double getMaxWeightOfMice();
}