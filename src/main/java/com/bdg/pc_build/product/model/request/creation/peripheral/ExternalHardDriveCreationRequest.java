package com.bdg.pc_build.product.model.request.creation.peripheral;

import com.bdg.pc_build.product.model.request.creation.ProductCreationRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static com.bdg.pc_build.checking.pattern.Pattern.POSITIVE_INTEGER_NUMBER_PATTERN;
import static com.bdg.pc_build.checking.pattern.Pattern.WRONG_POSITIVE_INTEGER_NUMBER_PATTERN_MESSAGE;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class ExternalHardDriveCreationRequest extends ProductCreationRequest {

    @NotBlank(message = "'capacity' field can not be blank")
    @Pattern(
            regexp = POSITIVE_INTEGER_NUMBER_PATTERN,
            message = WRONG_POSITIVE_INTEGER_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "capacity", required = true)
    String capacity;

    @NotBlank(message = "'tdp' field can not be blank")
    @Pattern(
            regexp = POSITIVE_INTEGER_NUMBER_PATTERN,
            message = WRONG_POSITIVE_INTEGER_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "tdp", required = true)
    String tdp;

    public ExternalHardDriveCreationRequest(
            final String name,
            final String price,
            final String purchasedPrice,
            final String count,
            final String capacity,
            final String tdp
    ) {
        super(name, price, purchasedPrice, count);
        this.capacity = capacity;
        this.tdp = tdp;
    }
}