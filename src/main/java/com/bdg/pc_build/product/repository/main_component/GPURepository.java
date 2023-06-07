package com.bdg.pc_build.product.repository.main_component;
import com.bdg.pc_build.product.model.entity.main_component.GPU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GPURepository extends JpaRepository<GPU, String> {
}
