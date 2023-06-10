package com.bdg.pc_build.product.model.dto.peripheral;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.dto.display.MonitorDTO;
import com.bdg.pc_build.product.model.entity.display.Monitor;
import com.bdg.pc_build.product.model.entity.peripheral.Keyboard;
import com.bdg.pc_build.product.model.request.ProductRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter
public class KeyboardDTO extends ProductDTO {

    String keyboardClass;
    Double cableLength;
    String dimension;
    Double weight;

    @Builder
    public KeyboardDTO(
            String name,
            Double price,
            Double purchasedPrice,
            Integer count,
            String keyboardClass,
            Double cableLength,
            String dimension,
            Double weight
    ) {
        super(name, price, purchasedPrice, count);
        this.keyboardClass = keyboardClass;
        this.cableLength = cableLength;
        this.dimension = dimension;
        this.weight = weight;
    }

    public static KeyboardDTO initDTOFromEntity(final Keyboard entity) {
        return KeyboardDTO.builder()
                .name(entity.getName())
                .price(entity.getPrice())
                .purchasedPrice(entity.getPurchasedPrice())
                .count(entity.getCount())
                .keyboardClass(entity.getKeyboardClass())
                .cableLength(entity.getCableLength())
                .dimension(entity.getDimension())
                .weight(entity.getWeight())
                .build();
    }

    public static KeyboardDTO initDTOFromRequest(final ProductRequest request){
        return KeyboardDTO.builder()
                .name(request.name())
                .price(Double.valueOf(request.price()))
                .purchasedPrice(Double.valueOf(request.purchasedPrice()))
                .count(Integer.valueOf(request.count()))
                .keyboardClass(request.keyboardClass())
                .cableLength(Double.valueOf(request.cableLength()))
                .dimension(request.dimension())
                .weight(Double.valueOf(request.weight()))
                .build();
    }
}
