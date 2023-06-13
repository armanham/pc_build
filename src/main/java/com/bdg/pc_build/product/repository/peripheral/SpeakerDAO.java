package com.bdg.pc_build.product.repository.peripheral;

import com.bdg.pc_build.product.model.entity.peripheral.Speaker;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeakerDAO extends ProductDAO<Speaker> {
}