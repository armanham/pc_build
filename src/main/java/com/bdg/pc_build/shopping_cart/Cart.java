package com.bdg.pc_build.shopping_cart;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public class Cart {

    List<CartItem> cartItems = new ArrayList<>();

}