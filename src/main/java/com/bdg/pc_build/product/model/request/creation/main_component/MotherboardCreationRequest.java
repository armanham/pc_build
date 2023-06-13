package com.bdg.pc_build.product.model.request.creation.main_component;

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
public class MotherboardCreationRequest extends ProductCreationRequest {

    @NotBlank(message = "'socket_type' field can not be blank")
    @JsonProperty(value = "socket_type", required = true)
    String socketType;

    @NotBlank(message = "'atx_type' field can not be blank")
    @JsonProperty(value = "atx_type", required = true)
    String atxType;

    @NotBlank(message = "'memory_max' field can not be blank")
    @Pattern(
            regexp = POSITIVE_INTEGER_NUMBER_PATTERN,
            message = WRONG_POSITIVE_INTEGER_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "memory_max", required = true)
    String memoryMax;

    @NotBlank(message = "'memory_slots' field can not be blank")
    @Pattern(
            regexp = POSITIVE_INTEGER_NUMBER_PATTERN,
            message = WRONG_POSITIVE_INTEGER_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "memory_slots", required = true)
    String memorySlots;

    @NotBlank(message = "'memory_type' field can not be blank")
    @JsonProperty(value = "memory_type", required = true)
    String memoryType;

    @NotBlank(message = "'internal_connections' field can not be blank")
    @JsonProperty(value = "internal_connections", required = true)
    String internalConnections;

    @NotBlank(message = "'gpu_interface_type' field can not be blank")
    @JsonProperty(value = "gpu_interface_type", required = true)
    String gpuInterfaceType;

    @NotBlank(message = "'tdp' field can not be blank")
    @Pattern(
            regexp = POSITIVE_INTEGER_NUMBER_PATTERN,
            message = WRONG_POSITIVE_INTEGER_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "tdp", required = true)
    String tdp;

    public MotherboardCreationRequest(
            final String name,
            final String price,
            final String purchasedPrice,
            final String count,
            final String socketType,
            final String atxType,
            final String memoryMax,
            final String memorySlots,
            final String memoryType,
            final String internalConnections,
            final String gpuInterfaceType,
            final String tdp
    ) {
        super(name, price, purchasedPrice, count);
        this.socketType = socketType.trim();
        this.atxType = atxType.trim();
        this.memoryMax = memoryMax;
        this.memorySlots = memorySlots;
        this.memoryType = memoryType;
        this.internalConnections = internalConnections.trim();
        this.gpuInterfaceType = gpuInterfaceType.trim();
        this.tdp = tdp;
    }
}