package com.bdg.pc_build.product.repository.display;

import com.bdg.pc_build.product.model.entity.display.Monitor;
import com.bdg.pc_build.product.model.entity.main_component.CPU;
import com.bdg.pc_build.product.repository.BaseProductRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitorRepository extends BaseProductRepository<Monitor> {
}
