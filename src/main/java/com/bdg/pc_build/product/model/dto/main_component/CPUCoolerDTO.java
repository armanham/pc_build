package com.bdg.pc_build.product.model.dto.main_component;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CPUCoolerDTO{

        String name;
        Double price;
        Integer count;
        Integer fanRPM;
        String socket;
        Integer tdp;



}
