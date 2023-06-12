package com.bdg.pc_build.wishlist.controller;

import com.bdg.pc_build.wishlist.model.dto.WishlistDTO;
import com.bdg.pc_build.wishlist.model.request.WishlistRequest;
import com.bdg.pc_build.wishlist.service.WishlistService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/wishlist")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class WishlistController {

    WishlistService wishlistService;

    @PostMapping(value = "/save/{id}")
    public boolean save(
            @PathVariable ("id" ) Long id,
            @RequestBody WishlistRequest wishlist
    ){
        wishlistService.saveProductIntoWishlist(id, WishlistDTO.initDTOFromRequest(wishlist));
        return true;
    }

}
