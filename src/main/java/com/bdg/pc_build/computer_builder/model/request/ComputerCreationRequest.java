package com.bdg.pc_build.computer_builder.model.request;

import com.bdg.pc_build.cart.model.CartItem;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class ComputerCreationRequest {

    List<CartItem> cartItems = new ArrayList<>();

}