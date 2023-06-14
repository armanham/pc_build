package com.bdg.pc_build.product.repository.peripheral;

import com.bdg.pc_build.product.model.entity.peripheral.Monitor;
import com.bdg.pc_build.product.model.entity.peripheral.Speaker;
import com.bdg.pc_build.product.model.enumerations.MonitorScreenType;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeakerDAO extends ProductDAO<Speaker> {

    @Query(
            "SELECT p FROM Speaker p WHERE " +
                    "(:name IS NULL OR lower(p.name) LIKE lower(concat('%', :name, '%'))) " +
                    "AND (p.price BETWEEN :minPrice AND :maxPrice) " +
                    "AND (p.frequency BETWEEN :minFrequency AND :maxFrequency) " +
                    "AND (p.cableLength BETWEEN :minCableLength AND :maxCableLength) " +
                    "AND (:dimension IS NULL OR lower(p.dimension) LIKE lower(concat('%', :dimension, '%')))" +
                    "AND (:powerSource IS NULL OR lower(p.powerSource) LIKE lower(concat('%', :powerSource, '%')))"
    )
    List<Speaker> filterAllSpeakersBasedOnSpecification(
            @Param("name") String name,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("minFrequency") Integer minFrequency,
            @Param("maxFrequency") Integer maxFrequency,
            @Param("minCableLength") Double minCableLength,
            @Param("maxCableLength") Double maxCableLength,
            @Param("dimension") String dimension,
            @Param("powerSource") String powerSource
    );
    @Query(
            "SELECT p FROM Speaker p " +
                    "WHERE :term IS NULL " +
                    "OR CONCAT(p.name, ' ', p.frequency, ' ', " +
                    "p.powerSource, ' ', p.cableLength, ' ', p.dimension) " +
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