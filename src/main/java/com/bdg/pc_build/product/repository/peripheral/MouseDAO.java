package com.bdg.pc_build.product.repository.peripheral;

import com.bdg.pc_build.product.model.entity.peripheral.Mouse;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.stereotype.Repository;

@Repository
public interface MouseDAO extends ProductDAO<Mouse> {
}
