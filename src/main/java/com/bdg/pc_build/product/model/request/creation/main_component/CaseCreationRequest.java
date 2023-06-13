package com.bdg.pc_build.product.model.request.creation.main_component;

import com.bdg.pc_build.product.model.enumerations.TowerType;
import com.bdg.pc_build.product.model.request.creation.ProductCreationRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static com.bdg.pc_build.checking.pattern.Pattern.*;
import static com.bdg.pc_build.checking.pattern.Pattern.WRONG_POSITIVE_INTEGER_NUMBER_PATTERN_MESSAGE;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class CaseCreationRequest extends ProductCreationRequest {

    @NotBlank(message = "'max_cpu_cooler_height' field can not be blank")
    @Pattern(
            regexp = FLOATING_POINT_NUMBER_PATTERN,
            message = WRONG_FLOATING_POINT_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "max_cpu_cooler_height", required = true)
    String maxCPUCoolerHeight;

    @NotBlank(message = "'max_gpu_length' field can not be blank")
    @Pattern(
            regexp = FLOATING_POINT_NUMBER_PATTERN,
            message = WRONG_FLOATING_POINT_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "max_gpu_length", required = true)
    String maxGPULength;

    @NotBlank(message = "'pre_installed_fans' field can not be blank")
    @Pattern(
            regexp = POSITIVE_INTEGER_NUMBER_PATTERN,
            message = WRONG_POSITIVE_INTEGER_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "pre_installed_fans", required = true)
    String preInstalledFans;

    @NotBlank(message = "'pre_installed_fans' field can not be blank")
    @JsonProperty(value = "tower_type", required = true)
    String towerType;

    public CaseCreationRequest(
            final String name,
            final String price,
            final String purchasedPrice,
            final String count,
            final String maxCPUCoolerHeight,
            final String maxGPULength,
            final String preInstalledFans,
            final String towerType
    ) {
        super(name, price, purchasedPrice, count);
        this.maxCPUCoolerHeight = maxCPUCoolerHeight;
        this.maxGPULength = maxGPULength;
        this.preInstalledFans = preInstalledFans;
        this.towerType = towerType.trim();
    }
}