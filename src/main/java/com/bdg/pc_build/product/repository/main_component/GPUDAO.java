package com.bdg.pc_build.product.repository.main_component;
import com.bdg.pc_build.product.model.entity.main_component.GPU;
import com.bdg.pc_build.product.repository.ProductDAO;
import org.springframework.stereotype.Repository;

@Repository
public interface GPUDAO extends ProductDAO<GPU> {
}