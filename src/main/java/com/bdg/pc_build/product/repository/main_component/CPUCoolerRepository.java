package com.bdg.pc_build.product.repository.main_component;

import com.bdg.pc_build.product.model.entity.main_component.CPUCooler;
import com.bdg.pc_build.product.repository.BaseProductRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CPUCoolerRepository extends BaseProductRepository<CPUCooler> {
}