package com.bdg.pc_build.product.repository.peripheral;

import com.bdg.pc_build.product.model.entity.peripheral.Keyboard;
import com.bdg.pc_build.product.enumerations.ConnectivityType;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeyboardDAO extends ProductDAO<Keyboard> {

    List<Keyboard> findAllByCableLengthBetween(Double minCableLength, Double maxCableLength);

    List<Keyboard> findAllByWeightBetween(Double minWeight, Double maxWeight);

    @Query("SELECT p FROM Keyboard p WHERE " +
            "(:dimension IS NULL OR lower(p.dimension) = :dimension) ")
    List<Keyboard> findAllByDimension(@Param("dimension") String dimension);

    @Query("SELECT p FROM Keyboard p WHERE " +
                    "(:connectivityType IS NULL OR p.connectivityType = :connectivityType) ")
    List<Keyboard> findAllByConnectivityType(@Param("connectivityType") ConnectivityType connectivityType);

    @Query("SELECT p FROM Keyboard p " +
                    "WHERE :term IS NULL " +
                    "OR lower(CONCAT(p.name, ' ', p.connectivityType, ' ', " +
                    "p.cableLength, ' ', p.dimension, ' ', p.weight)) " +
                    "LIKE CONCAT('%', :term, '%') ")
    List<Keyboard> findAllBasedOnTerm(@Param("term") String term);

    @Query("select min(p.cableLength) from Keyboard p ")
    Double getMinCableLengthOfKeyboard();

    @Query("select max(p.cableLength) from Keyboard p ")
    Double getMaxCableLengthOfKeyboard();

    @Query("select min(p.weight) from Keyboard p ")
    Double getMinWeightOfKeyboard();

    @Query("select max(p.weight) from Keyboard p ")
    Double getMaxWeightOfKeyboard();
}