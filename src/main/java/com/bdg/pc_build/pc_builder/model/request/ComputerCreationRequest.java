package com.bdg.pc_build.pc_builder.model.request;

import com.bdg.pc_build.cart.model.CartItem;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor //todo remove
@Getter
public class ComputerCreationRequest {

    @JsonProperty(value = "cart_items", required = true)
    private List<CartItem> cartItems;
}