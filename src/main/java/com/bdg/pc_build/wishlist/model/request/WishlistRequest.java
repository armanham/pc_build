package com.bdg.pc_build.wishlist.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WishlistRequest (

    @JsonProperty(value = "product_name")
    String productName,

    @JsonProperty(value = "description")
    String description,

    @JsonProperty(value = "username")
    String username

//    @JsonProperty(value = "recommended_price")
//    String recommendedPrice

)


{

}

