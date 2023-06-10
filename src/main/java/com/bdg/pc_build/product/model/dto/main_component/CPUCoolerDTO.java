package com.bdg.pc_build.product.model.dto.main_component;


import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main_component.CPUCooler;
import com.bdg.pc_build.product.model.request.ProductRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter
public class CPUCoolerDTO extends ProductDTO {

        Integer fanRPM;
        String socket;
        Integer tdp;

        @Builder
        public CPUCoolerDTO(
                final String name,
                final Double price,
                final Double purchasedPrice,
                final Integer count,
                final Integer fanRPM,
                final String socket,
                final Integer tdp
        ) {
                super(name, price, purchasedPrice, count);
                this.fanRPM = fanRPM;
                this.socket = socket;
                this.tdp = tdp;
        }


        public static CPUCoolerDTO initDTOFromEntity(CPUCooler entity){
                return CPUCoolerDTO.builder()
                        .name(entity.getName())
                        .price(entity.getPrice())
                        .purchasedPrice(entity.getPurchasedPrice())
                        .count(entity.getCount())
                        .fanRPM(entity.getFanRPM())
                        .socket(String.valueOf(entity.getSocketType()))
                        .tdp(entity.getTdp())
                        .build();
        }


        public static CPUCoolerDTO initDTOFromRequest(final ProductRequest request){
                return CPUCoolerDTO.builder()
                        .name(request.name())
                        .price(request.price())
                        .purchasedPrice(request.purchasedPrice())
                        .count(request.count())
                        .fanRPM(request.fanRPM())
                        .socket(request.socket())
                        .tdp(request.tdp())
                        .build();
        }
}
