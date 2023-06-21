package com.bdg.pc_build.product.repository.peripheral;

import com.bdg.pc_build.product.enumerations.PowerSourceType;
import com.bdg.pc_build.product.model.entity.peripheral.Speaker;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeakerDAO extends ProductDAO<Speaker> {

    List<Speaker> findAllByFrequencyBetween(Integer minFrequency, Integer maxFrequency);

    List<Speaker> findAllByCableLengthBetween(Double minCableLength, Double maxCableLength);

    @Query("SELECT p FROM Speaker p WHERE " +
            "(:dimension IS NULL OR lower(p.dimension) = :dimension) ")
    List<Speaker> findAllByDimension(@Param("dimension") String dimension);

    @Query("SELECT p FROM Speaker p WHERE " +
            "(:powerSourceType IS NULL OR lower(p.powerSourceType) = :powerSourceType) ")
    List<Speaker> findAllByPowerSourceType(@Param("powerSourceType") PowerSourceType powerSourceType);

    @Query("SELECT p FROM Speaker p " +
            "WHERE :term IS NULL " +
            "OR lower(CONCAT(p.name, ' ', p.frequency, ' ', " +
            "p.powerSourceType, ' ', p.cableLength, ' ', p.dimension)) " +
            "LIKE CONCAT('%', :term, '%') ")
    List<Speaker> findAllBasedOnTerm(@Param("term") String term);

    @Query("select min(p.frequency) from Speaker p ")
    Integer getMinFrequencyOfSpeakers();

    @Query("select max(p.frequency) from Speaker p ")
    Integer getMaxFrequencyOfSpeakers();

    @Query("select min(p.cableLength) from Speaker p ")
    Double getMinCableLengthOfSpeakers();

    @Query("select max(p.cableLength) from Speaker p ")
    Double getMaxCableLengthOfSpeakers();
}