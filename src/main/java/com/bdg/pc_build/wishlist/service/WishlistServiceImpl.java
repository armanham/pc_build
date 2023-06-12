package com.bdg.pc_build.wishlist.service;


import com.bdg.pc_build.user.User;
import com.bdg.pc_build.user.UserDAO;
import com.bdg.pc_build.user.UserDTO;
import com.bdg.pc_build.wishlist.model.dto.WishlistDTO;
import com.bdg.pc_build.wishlist.model.entity.Wishlist;
import com.bdg.pc_build.wishlist.repository.WishlistDAO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    WishlistDAO wishlistDAO;
    UserDAO userDAO;

    @Override
    public boolean saveProductIntoWishlist(Long id, WishlistDTO dto) {
        Optional<User> optionalUser = userDAO.findById(id);
    if (optionalUser.isEmpty()){
        throw new IllegalArgumentException();
    }

     wishlistDAO.save(new  Wishlist(dto));
    return true;
    }
}
