package com.bdg.pc_build.product.model.request.creation.main_component;


import com.bdg.pc_build.product.model.request.creation.ProductCreationRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static com.bdg.pc_build.checking.pattern.Pattern.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class CaseCreationRequest extends ProductCreationRequest {

    @NotBlank(message = "'max_cpu_cooler_height' field can not be blank")
    @Pattern(
            regexp = FLOATING_POINT_NUMBER_PATTERN,
            message = WRONG_FLOATING_POINT_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "max_cpu_cooler_height", required = true)
    String maxCpuCoolerHeight;

    @NotBlank(message = "'max_gpu_length' field can not be blank")
    @Pattern(
            regexp = FLOATING_POINT_NUMBER_PATTERN,
            message = WRONG_FLOATING_POINT_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "max_gpu_length", required = true)
    String maxGpuLength;

    @NotBlank(message = "'pre_installed_fans' field can not be blank")
    @Pattern(
            regexp = POSITIVE_INTEGER_NUMBER_PATTERN,
            message = WRONG_POSITIVE_INTEGER_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "pre_installed_fans", required = true)
    String preInstalledFans;

    @NotBlank(message = "'tower_type' field can not be blank")
    @Pattern(
            regexp = TOWER_TYPE_ENUM_PATTERN,
            message = WRONG_ENUM_PATTERN_COMMON_MESSAGE
    )
    @JsonProperty(value = "tower_type", required = true)
    String towerType;

    public CaseCreationRequest(
            final String name,
            final String price,
            final String purchasedPrice,
            final String count,
            final String maxCpuCoolerHeight,
            final String maxGpuLength,
            final String preInstalledFans,
            final String towerType
    ) {
        super(name, price, purchasedPrice, count);
        this.maxCpuCoolerHeight = maxCpuCoolerHeight;
        this.maxGpuLength = maxGpuLength;
        this.preInstalledFans = preInstalledFans;
        this.towerType = towerType;
    }
}