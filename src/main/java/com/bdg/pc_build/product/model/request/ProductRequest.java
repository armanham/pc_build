package com.bdg.pc_build.product.model.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {
    String name;
    Double price;
    Double purchasedPrice;
    Integer count;
    Double screenSize;
    Integer refreshRate;
    String screenType;
    Integer maxCPUCoolerHeight;
    Double maxGPULength;
    Integer preInstalledFans;
    Boolean isATX;
    Integer tdp;
}
