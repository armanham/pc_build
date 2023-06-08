package com.bdg.pc_build.product.repository;

import com.bdg.pc_build.product.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseProductRepository<ENTITY extends Product> extends JpaRepository<ENTITY, String> {

    Optional<ENTITY> findByName(String name);

    List<ENTITY> findAllByPriceBetween(Double minPrice, Double maxPrice);

    List<ENTITY> findAllByPurchasedPriceBetween(Double minPrice, Double maxPrice);
}