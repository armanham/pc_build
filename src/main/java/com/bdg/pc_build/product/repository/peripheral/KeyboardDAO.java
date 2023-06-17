package com.bdg.pc_build.product.repository.peripheral;

import com.bdg.pc_build.product.model.entity.main_component.PowerSupply;
import com.bdg.pc_build.product.model.entity.peripheral.Keyboard;
import com.bdg.pc_build.product.model.entity.peripheral.Monitor;
import com.bdg.pc_build.product.model.enumerations.ConnectivityType;
import com.bdg.pc_build.product.model.enumerations.ModularType;
import com.bdg.pc_build.product.model.enumerations.MonitorScreenType;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface KeyboardDAO extends ProductDAO<Keyboard> {

    @Query(
            "SELECT p FROM Keyboard p WHERE " +
                    "(:name IS NULL OR lower(p.name) LIKE lower(concat('%', :name, '%'))) " +
                    "AND (p.price BETWEEN :minPrice AND :maxPrice) " +
                    "AND (p.cableLength BETWEEN :minCableLength AND :maxCableLength) " +
                    "AND (p.weight BETWEEN :minWeight AND :maxWeight)" +
                    "AND ((:dimensions) IS NULL OR lower(p.dimension) in (:dimensions)) " +
                    "AND ((:connectivityTypes) IS NULL OR p.connectivityType IN (:connectivityTypes))"

    )
    List<Keyboard> filterAllKeyboardsBasedOnSpecification(
            @Param("name") String name,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("minCableLength") Double minCableLength,
            @Param("maxCableLength") Double maxCableLength,
            @Param("minWeight") Double minWeight,
            @Param("maxWeight") Double maxWeight,
            @Param("dimensions") Set<String> dimensions,
            @Param("connectivityTypes") Set<ConnectivityType> connectivityTypes
    );

    List<Keyboard> findAllByCableLengthBetween(Double minCableLength,Double maxCableLength);
    List<Keyboard> findAllByWeightBetween(Double minWeight,Double maxWeight);

    @Query(
            "SELECT p FROM Keyboard p WHERE " +
                    "(:dimension IS NULL OR lower(p.dimension) = :dimension) "
    )
    List<Keyboard> findAllByDimension(@Param("dimension") String dimension);


    @Query(
            "SELECT p FROM Keyboard p WHERE " +
                    "(:connectivityType IS NULL OR p.connectivityType = :connectivityType) "
    )
    List<Keyboard> findAllByConnectivityType(@Param("connectivityType") ConnectivityType connectivityType);

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