package com.bdg.pc_build.product.repository.peripheral;

import com.bdg.pc_build.product.model.entity.peripheral.Keyboard;
import com.bdg.pc_build.product.model.entity.peripheral.Monitor;
import com.bdg.pc_build.product.model.enumerations.MonitorScreenType;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeyboardDAO extends ProductDAO<Keyboard> {

    //todo
    @Query(
            "SELECT p FROM Keyboard p WHERE " +
                    "(:name IS NULL OR lower(p.name) LIKE lower(concat('%', :name, '%'))) " +
             //       "AND (:keyboardClass IS NULL OR lower(p.keyboardClass) LIKE lower(concat('%', :keyboardClass, '%')))" +
                    "AND (p.cableLength BETWEEN :minCableLength AND :maxCableLength) " +
                    "AND (:dimension IS NULL OR lower(p.dimension) LIKE lower(concat('%', :dimension, '%')))" +
                    "AND (p.weight BETWEEN :minWeight AND :maxWeight)"
    )
    List<Monitor> filterAllMonitorsBasedOnSpecification(
            @Param("name") String name,
            @Param("keyboardClass") String keyboardClass,
            @Param("minCableLength") Double minCableLength,
            @Param("maxCableLength") Double maxCableLength,
            @Param("dimension") String dimension,
            @Param("minWeight") Double minWeight,
            @Param("maxWeight") Double maxWeight
    );
    @Query(
            "SELECT p FROM Keyboard p " +
                    "WHERE :term IS NULL " +
                    "OR CONCAT(p.name, ' ', p.connectivityType, ' ', " +
                    "p.cableLength, ' ', p.dimension, ' ', p.weight) " +
                    "LIKE CONCAT('%', :term, '%') "
    )
    List<Keyboard> findAllKeyboardsBasedOnTerm(
            @Param("term") String term
    );

    @Query("select min(p.cableLength) from Keyboard p ")
    Double getMinCableLengthOfKeyboard();

    @Query("select max(p.cableLength) from Keyboard p ")
    Double getMaxCableLengthOfKeyboard();

    @Query("select min(p.weight) from Keyboard p ")
    Double getMinWeightOfKeyboard();

    @Query("select max(p.weight) from Keyboard p ")
    Double getMaxWeightOfKeyboard();
}