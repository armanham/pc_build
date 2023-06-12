package com.bdg.pc_build.wishlist.service;


import com.bdg.pc_build.wishlist.model.dto.WishlistDTO;
import com.bdg.pc_build.wishlist.model.entity.Wishlist;
import com.bdg.pc_build.wishlist.repository.WishlistDAO;

public interface WishlistService {

    boolean saveProductIntoWishlist(Long id, WishlistDTO dto);

}
