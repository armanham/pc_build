package com.bdg.pc_build.product.model.request.creation.peripheral;

import com.bdg.pc_build.product.model.request.creation.ProductCreationRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static com.bdg.pc_build.product.pattern.Pattern.MONITOR_SCREEN_TYPE_ENUM_PATTERN;
import static com.bdg.pc_build.product.pattern.Pattern.WRONG_ENUM_PATTERN_COMMON_MESSAGE;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class MonitorCreationRequest extends ProductCreationRequest {

    @Positive
    @JsonProperty(value = "screen_size", required = true)
    Double screenSize;

    @Positive
    @JsonProperty(value = "refresh_rate", required = true)
    Integer refreshRate;

    @NotBlank(message = "'screen_type' field can not be blank")
    @Pattern(
            regexp = MONITOR_SCREEN_TYPE_ENUM_PATTERN,
            message = WRONG_ENUM_PATTERN_COMMON_MESSAGE
    )
    @JsonProperty(value = "screen_type", required = true)
    String screenType;

    public MonitorCreationRequest(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final Double screenSize,
            final Integer refreshRate,
            final String screenType
    ) {
        super(name, price, purchasedPrice, count);
        this.screenSize = screenSize;
        this.refreshRate = refreshRate;
        this.screenType = screenType;
    }
}