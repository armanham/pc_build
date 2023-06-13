package com.bdg.pc_build.product.repository.peripheral;

import com.bdg.pc_build.product.model.entity.peripheral.Keyboard;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeyboardDAO extends ProductDAO<Keyboard> {
    @Query(
            "SELECT p FROM Keyboard p " +
                    "WHERE :term IS NULL " +
                    "OR CONCAT(p.name, ' ', p.keyboardClass, ' ', " +
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