package com.bdg.pc_build.shopping_cart;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Service
public class Cart {

    final ProductService productService;

    final List<CartItem> cartItems = new ArrayList<>();

    Double sum = 0.0;

    public void addItem(final CartItem item) {
        ProductDTO productByName = productService.findById(item.productId());
        System.out.println(productByName);
        if (productByName != null) {
            sum += productByName.getPrice() * item.quantity();
        }
        cartItems.add(item);
    }

    public void removeItem(final CartItem item) {
        if (cartItems.contains(item)) {
            sum -= productService.findById(item.productId()).getPrice() * item.quantity();
        }
        cartItems.remove(item);
    }

    public void clear() {
        cartItems.clear();
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public Double getSum() {
        return sum;
    }
}