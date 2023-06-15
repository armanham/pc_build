package com.bdg.pc_build.product.model.request.creation.peripheral;

import com.bdg.pc_build.product.model.request.creation.ProductCreationRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Locale;

import static com.bdg.pc_build.checking.pattern.Pattern.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class MonitorCreationRequest extends ProductCreationRequest {

    @NotBlank(message = "'screen_size' field can not be blank")
    @Pattern(
            regexp = FLOATING_POINT_NUMBER_PATTERN,
            message = WRONG_FLOATING_POINT_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "screen_size", required = true)
    String screenSize;

    @NotBlank(message = "'refresh_rate' field can not be blank")
    @Pattern(
            regexp = POSITIVE_INTEGER_NUMBER_PATTERN,
            message = WRONG_POSITIVE_INTEGER_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "refresh_rate", required = true)
    String refreshRate;

    @NotBlank(message = "'screen_type' field can not be blank")
    @Pattern(
            regexp = MONITOR_SCREEN_TYPE_ENUM_PATTERN,
            message = WRONG_ENUM_PATTERN_COMMON_MESSAGE
    )
    @JsonProperty(value = "screen_type", required = true)
    String screenType;

    public MonitorCreationRequest(
            final String name,
            final String price,
            final String purchasedPrice,
            final String count,
            final String screenSize,
            final String refreshRate,
            final String screenType
    ) {
        super(name, price, purchasedPrice, count);
        this.screenSize = screenSize;
        this.refreshRate = refreshRate;
        this.screenType = screenType;
    }
}