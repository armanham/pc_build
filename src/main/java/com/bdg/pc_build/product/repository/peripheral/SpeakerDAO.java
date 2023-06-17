package com.bdg.pc_build.product.repository.peripheral;

import com.bdg.pc_build.product.model.entity.main_component.GPU;
import com.bdg.pc_build.product.model.entity.peripheral.Speaker;
import com.bdg.pc_build.product.model.enumerations.GPUInterfaceType;
import com.bdg.pc_build.product.model.enumerations.PowerSourceType;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;
import java.util.Set;

@Repository
public interface SpeakerDAO extends ProductDAO<Speaker> {

    List<Speaker> findAllByFrequencyBetween(Integer minFrequency, Integer maxFrequency);

    List<Speaker> findAllByCableLengthBetween(Integer minCableLength, Integer maxCableLength);

    @Query(
            "SELECT p FROM Speaker p WHERE " +
                    "(:dimension IS NULL OR lower(p.dimension) = :dimension) "
    )
    List<GPU> findAllByDimension(@Param("dimension") Dimension dimension);

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