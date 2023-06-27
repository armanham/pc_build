package com.bdg.pc_build.computer_builder.model.request;

import com.bdg.pc_build.cart.model.CartItem;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ComputerCreationRequest {

    @JsonProperty("cart_items")
    private List<CartItem> cartItems;
}