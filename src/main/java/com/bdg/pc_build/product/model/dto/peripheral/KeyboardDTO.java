package com.bdg.pc_build.product.model.dto.peripheral;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.peripheral.Keyboard;
import com.bdg.pc_build.product.model.enumerations.Connectivity;
import com.bdg.pc_build.product.model.request.creation.peripheral.KeyboardCreationRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
public class KeyboardDTO extends ProductDTO {

    Connectivity connectivityKeyboard;
    Double cableLength;
    String dimension;
    Double weight;

    @Builder
    public KeyboardDTO(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final Connectivity connectivityKeyboard,
            final Double cableLength,
            final String dimension,
            final Double weight
    ) {
        super(name, price, purchasedPrice, count);
        this.connectivityKeyboard = connectivityKeyboard;
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
                .connectivityKeyboard(entity.getConnectivityKeyboard())
                .cableLength(entity.getCableLength())
                .dimension(entity.getDimension())
                .weight(entity.getWeight())
                .build();
    }

    public static KeyboardDTO initDTOFromRequest(final KeyboardCreationRequest request) {
        return KeyboardDTO.builder()
                .name(request.getName())
                .price(Double.valueOf(request.getPrice()))
                .purchasedPrice(Double.valueOf(request.getPurchasedPrice()))
                .count(Integer.valueOf(request.getCount()))
                .connectivityKeyboard(Connectivity.valueOf(request.getConnectivityKeyboard()))
                .cableLength(Double.valueOf(request.getCableLength()))
                .dimension(request.getDimension())
                .weight(Double.valueOf(request.getWeight()))
                .build();
    }
}