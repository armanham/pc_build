package com.bdg.pc_build.product.repository.peripheral;

import com.bdg.pc_build.product.model.entity.peripheral.Headset;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeadsetDAO extends ProductDAO<Headset> {
    @Query(
            "SELECT p FROM Headset p " +
                    "WHERE :term IS NULL " +
                    "OR CONCAT(p.name, ' ', p.frequency, ' ', " +
                    "p.connectivity, ' ', p.cableLength) " +
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
