package com.bdg.pc_build.product.repository.peripheral;

import com.bdg.pc_build.product.enumerations.ConnectivityType;
import com.bdg.pc_build.product.model.entity.peripheral.Headset;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeadsetDAO extends ProductDAO<Headset> {

    List<Headset> findAllByFrequencyBetween(Integer minFrequency, Integer maxFrequency);

    List<Headset> findAllByCableLengthBetween(Double minCableLength, Double maxCableLength);

    @Query("SELECT p FROM Headset p WHERE " +
            "(:connectivityType IS NULL OR p.connectivityType = :connectivityType) ")
    List<Headset> findAllByConnectivityType(@Param("connectivityType") ConnectivityType connectivityType);

    @Query("SELECT p FROM Headset p " +
            "WHERE :term IS NULL " +
            "OR lower(CONCAT(p.name, ' ', p.frequency, ' ', " +
            "p.connectivityType, ' ', p.cableLength))" +
            "LIKE CONCAT('%', :term, '%') ")
    List<Headset> findAllBasedOnTerm(@Param("term") String term);

    @Query("select min(p.frequency) from Headset p ")
    Integer getMinFrequencyOfHeadset();

    @Query("select max(p.frequency) from Headset p ")
    Integer getMaxFrequencyOfHeadset();

    @Query("select min(p.cableLength) from Headset p ")
    Double getMinCableLengthOfHeadset();

    @Query("select max(p.cableLength) from Headset p ")
    Double getMaxCableLengthOfHeadset();
}