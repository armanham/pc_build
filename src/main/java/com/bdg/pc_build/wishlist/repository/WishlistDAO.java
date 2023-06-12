package com.bdg.pc_build.wishlist.repository;

import com.bdg.pc_build.wishlist.model.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistDAO extends JpaRepository<Wishlist, Long > {

}
