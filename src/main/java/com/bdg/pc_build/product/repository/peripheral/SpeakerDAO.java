package com.bdg.pc_build.product.repository.peripheral;

import com.bdg.pc_build.product.model.entity.peripheral.Speaker;
import com.bdg.pc_build.product.model.enumerations.PowerSourceType;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SpeakerDAO extends ProductDAO<Speaker> {
    //todo check filterBasedOnSpecification
    @Query(
            "SELECT p FROM Speaker p WHERE " +
                    "(:name IS NULL OR lower(p.name) LIKE lower(concat('%', :name, '%'))) " +
                    "AND (p.price BETWEEN :minPrice AND :maxPrice) " +
                    "AND (p.frequency BETWEEN :minFrequency AND :maxFrequency) " +
                    "AND (p.cableLength BETWEEN :minCableLength AND :maxCableLength) " +
                    "AND ((:dimensions) IS NULL OR lower(p.dimension) in (:dimensions)) " +
                    "AND ((:powerSourceTypes) IS NULL OR p.powerSourceType IN (:powerSourceTypes))"
    )
    List<Speaker> filterAllSpeakersBasedOnSpecification(
            @Param("name") String name,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("minFrequency") Integer minFrequency,
            @Param("maxFrequency") Integer maxFrequency,
            @Param("minCableLength") Double minCableLength,
            @Param("maxCableLength") Double maxCableLength,
            @Param("dimensions") Set<String> dimensions,
            @Param("powerSourceTypes") Set<PowerSourceType> powerSourceTypes
    );

    @Query(
            "SELECT p FROM Speaker p " +
                    "WHERE :term IS NULL " +
                    "OR CONCAT(p.name, ' ', p.frequency, ' ', " +
                    "p.powerSourceType, ' ', p.cableLength, ' ', p.dimension) " +
                    "LIKE CONCAT('%', :term, '%') "
    )
    List<Speaker> findAllSpeakersBasedOnTerm(
            @Param("term") String term
    );

    @Query("select min(p.frequency) from Speaker p ")
    Integer getMinFrequencyOfSpeakers();

    @Query("select max(p.frequency) from Speaker p ")
    Integer getMaxFrequencyOfSpeakers();

    @Query("select min(p.cableLength) from Speaker p ")
    Double getMinCableLengthOfSpeakers();

    @Query("select max(p.cableLength) from Speaker p ")
    Double getMaxCableLengthOfSpeakers();
}