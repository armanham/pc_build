package com.bdg.pc_build.cart.service;

import com.bdg.pc_build.order.service.OrderService;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.service.ProductService;
import com.bdg.pc_build.cart.model.CartItem;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.*;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class CartServiceImpl implements CartService{

    ProductService productService;
    OrderService orderService;
    private Map<ProductDTO, Integer> products = new HashMap<>();



    @Override
    public void addProduct(CartItem item) {
        ProductDTO productDTO = productService.findById(item.productId());
        if(products.containsKey(productDTO)){
            products.replace(productDTO, products.get(productDTO) + item.quantity());
        }
        else{
            products.put(productDTO, item.quantity());
        }
    }


    @Override
    public void removeProduct(CartItem item) {
        ProductDTO productDTO = productService.findById(item.productId());
        if (products.containsKey(productDTO)) {
            if (products.get(productDTO) > item.quantity())
                products.replace(productDTO, products.get(productDTO) - item.quantity());
            else if (Objects.equals(products.get(productDTO), item.quantity())) {
                products.remove(productDTO);
            }
        }
    }

    @Override
    public Map<ProductDTO, Integer> getProductsInCart() {
        return Collections.unmodifiableMap(products);
    }

    @Override
    public BigDecimal getTotal() {
        return products.entrySet()
                .stream()
                .map(entry ->  BigDecimal.valueOf(entry.getKey().getPrice()).multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    @Override
    public void checkout() {
        for (Map.Entry<ProductDTO, Integer> entry : products.entrySet()) {
            if(entry.getKey().getCount() < entry.getValue()){
                //todo
                throw new IllegalArgumentException("Not enough products");
            }
            else{
                productService.reduceCountByDto(entry.getKey(), entry.getValue());
            }
        }
        orderService.save(products.keySet(), getTotal());
        products.clear();
    }
}
