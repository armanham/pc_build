package com.bdg.pc_build.product.repository.peripheral;


import com.bdg.pc_build.product.model.entity.peripheral.Mouse;
import com.bdg.pc_build.product.model.enumerations.ConnectivityType;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface MouseDAO extends ProductDAO<Mouse> {

    @Query(
            "SELECT p FROM Mouse p WHERE " +
                    "(:name IS NULL OR lower(p.name) LIKE lower(concat('%', :name, '%'))) " +
                    "AND (p.price BETWEEN :minPrice AND :maxPrice) " +
                    "AND (p.maxResolution BETWEEN :minResolution AND :maxResolution) " +
                    "AND (p.cableLength BETWEEN :minCableLength AND :maxCableLength) " +
                    "AND (p.weight BETWEEN :minWeight AND :maxWeight)" +
                    "AND ((:connectivityTypes) IS NULL OR p.connectivityType IN (:connectivityTypes))"
    )
    List<Mouse> filterAllMiceBasedOnSpecification(
            @Param("name") String name,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("minResolution") Integer minResolution,
            @Param("maxResolution") Integer maxResolution,
            @Param("minCableLength") Double minCableLength,
            @Param("maxCableLength") Double maxCableLength,
            @Param("minWeight") Double minWeight,
            @Param("maxWeight") Double maxWeight,
            @Param("connectivityTypes") Set<ConnectivityType> connectivityTypes
    );

    @Query(
            "SELECT p FROM Mouse p " +
                    "WHERE :term IS NULL " +
                    "OR CONCAT(p.name, ' ', p.connectivityType, ' ', " +
                    "p.maxResolution, ' ', p.cableLength, ' ', p.weight) " +
                    "LIKE CONCAT('%', :term, '%') "
    )
    List<Mouse> findAllMiceBasedOnTerm(
            @Param("term") String term
    );

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
