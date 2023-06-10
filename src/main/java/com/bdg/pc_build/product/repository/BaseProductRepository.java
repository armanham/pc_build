package com.bdg.pc_build.product.repository;

import com.bdg.pc_build.product.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseProductRepository<ENTITY extends Product> extends JpaRepository<ENTITY, String> {

    Optional<ENTITY> findByName(String name);

    @Query("SELECT p FROM #{#entityName} p WHERE lower(p.name) LIKE lower(concat('%', :name, '%'))")
    List<ENTITY> findAllProductsByNameIgnoreCaseLikeTerm(@Param("name") String name);

    List<ENTITY> findAllProductsByPriceBetween(Double minPrice, Double maxPrice);

    List<ENTITY> findAllProductsByPurchasedPriceBetween(Double minPrice, Double maxPrice);

}