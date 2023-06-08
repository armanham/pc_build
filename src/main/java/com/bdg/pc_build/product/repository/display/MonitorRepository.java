package com.bdg.pc_build.product.repository.display;

import com.bdg.pc_build.product.model.entity.display.Monitor;
import com.bdg.pc_build.product.repository.BaseProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitorRepository extends BaseProductRepository<Monitor> {

  // List<Monitor> findAllByPriceBetween(Double minPrice, Double maxPrice);
}