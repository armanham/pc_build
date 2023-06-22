package com.bdg.pc_build.shopping_cart;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class CartService {

    ProductService productService;

    public Double calculateSumOfPrices(final Cart cart) {
        Double sum = 0.0;
        for (CartItem item : cart.getCartItems()) {
            ProductDTO productByName = productService.findProductByName(item.productName());
            if (productByName != null) {
                sum += productByName.getPrice();
            }
        }
        return sum;
    }
}