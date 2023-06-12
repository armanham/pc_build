package com.bdg.pc_build.wishlist.model.dto;

import com.bdg.pc_build.user.User;
import com.bdg.pc_build.user.UserDTO;
import com.bdg.pc_build.wishlist.model.entity.Wishlist;
import com.bdg.pc_build.wishlist.model.request.WishlistRequest;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class WishlistDTO {

        String productName;

        String description;

//        Double recommendedPrice;

        String username;




        public static WishlistDTO initDTOFromEntity(final Wishlist entity) {
                return WishlistDTO.builder()
                        .productName(entity.getProductName())
                        .description(entity.getDescription())
//                        .recommendedPrice(entity.getRecommendedPrice())
                        .username(entity.getUsername())
                        .build();
        }


        public static WishlistDTO initDTOFromRequest(final WishlistRequest request) {
                return WishlistDTO.builder()
                        .productName(request.productName())
                        .description(request.description())
//                        .recommendedPrice(Double.valueOf(request.recommendedPrice()))
                        .build();
        }
}

