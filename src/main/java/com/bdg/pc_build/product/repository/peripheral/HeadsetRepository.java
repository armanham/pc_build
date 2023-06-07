package com.bdg.pc_build.product.repository.peripheral;

import com.bdg.pc_build.product.model.entity.main_component.CPU;
import com.bdg.pc_build.product.model.entity.peripheral.Headset;
import com.bdg.pc_build.product.repository.BaseProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeadsetRepository extends BaseProductRepository<Headset> {
}
