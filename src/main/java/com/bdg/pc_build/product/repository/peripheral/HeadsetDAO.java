package com.bdg.pc_build.product.repository.peripheral;

import com.bdg.pc_build.product.model.entity.peripheral.Headset;
import com.bdg.pc_build.product.model.entity.peripheral.Keyboard;
import com.bdg.pc_build.product.model.entity.peripheral.Monitor;
import com.bdg.pc_build.product.model.enumerations.ConnectivityType;
import com.bdg.pc_build.product.model.enumerations.MonitorScreenType;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface HeadsetDAO extends ProductDAO<Headset> {

    @Query(
            "SELECT p FROM Headset p WHERE " +
                    "(:name IS NULL OR lower(p.name) LIKE lower(concat('%', :name, '%'))) " +
                    "AND (p.price BETWEEN :minPrice AND :maxPrice) " +
                    "AND (p.frequency BETWEEN :minFrequency AND :maxFrequency) " +
                    "AND (p.cableLength BETWEEN :minCableLength AND :maxCableLength) " +
                    "AND ((:connectivityTypes) IS NULL OR p.connectivityType IN (:connectivityTypes) )"
    )
    List<Headset> filterAllHeadsetsBasedOnSpecification(
            @Param("name") String name,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("minFrequency") Integer minFrequency,
            @Param("maxFrequency") Integer maxFrequency,
            @Param("minCableLength") Double minCableLength,
            @Param("maxCableLength") Double maxCableLength,
            @Param("connectivityTypes") Set<ConnectivityType> connectivityTypes
    );

    List<Headset> findAllByFrequencyBetween(Integer minFrequency, Integer maxFrequency);
    List<Headset> findAllByCableLengthBetween(Double minCableLength, Double maxCableLength);

    @Query(
            "SELECT p FROM Headset p WHERE " +
                    "(:connectivityType IS NULL OR p.connectivityType = :connectivityType) "
    )
    List<Headset> findAllByConnectivityType(@Param("connectivityType") ConnectivityType connectivityType);


    @Query(
            "SELECT p FROM Headset p " +
                    "WHERE :term IS NULL " +
                    "OR CONCAT(p.name, ' ', p.frequency, ' ', " +
                    "p.connectivityType, ' ', p.cableLength) " +
                    "LIKE CONCAT('%', :term, '%') "
    )
    List<Headset> findAllHeadsetsBasedOnTerm(
            @Param("term") String term
    );

    @Query("select min(p.frequency) from Headset p ")
    Integer getMinFrequencyOfHeadset();

    @Query("select max(p.frequency) from Headset p ")
    Integer getMaxFrequencyOfHeadset();

    @Query("select min(p.cableLength) from Headset p ")
    Double getMinCableLengthOfHeadset();

    @Query("select max(p.cableLength) from Headset p ")
    Double getMaxCableLengthOfHeadset();
}
