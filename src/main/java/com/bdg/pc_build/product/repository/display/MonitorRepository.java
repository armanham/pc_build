package com.bdg.pc_build.product.repository.display;

import com.bdg.pc_build.product.model.entity.display.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitorRepository extends JpaRepository<Monitor, String> {
}
