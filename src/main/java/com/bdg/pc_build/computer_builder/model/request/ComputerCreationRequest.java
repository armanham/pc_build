package com.bdg.pc_build.computer_builder.model.request;

import com.bdg.pc_build.cart.model.CartItem;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class ComputerCreationRequest {

    @JsonProperty("cart_items")
    List<CartItem> cartItems;
}