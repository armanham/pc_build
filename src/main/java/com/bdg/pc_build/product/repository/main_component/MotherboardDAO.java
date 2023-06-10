package com.bdg.pc_build.product.repository.main_component;

import com.bdg.pc_build.product.model.entity.main_component.Motherboard;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.stereotype.Repository;

@Repository
public interface MotherboardDAO extends ProductDAO<Motherboard> {
}