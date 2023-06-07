package com.bdg.pc_build.product.repository;

import com.bdg.pc_build.product.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface BaseProductRepository<T extends Product> extends JpaRepository<T, Long> {
    // Add any custom methods or queries common to all product entities
    Optional<T> findByName(String name);
}
