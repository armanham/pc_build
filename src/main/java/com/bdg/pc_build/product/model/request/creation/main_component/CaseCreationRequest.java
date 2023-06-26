package com.bdg.pc_build.product.model.request.creation.main_component;


import com.bdg.pc_build.product.model.request.creation.ProductCreationRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static com.bdg.pc_build.product.pattern.Pattern.TOWER_TYPE_ENUM_PATTERN;
import static com.bdg.pc_build.product.pattern.Pattern.WRONG_ENUM_PATTERN_COMMON_MESSAGE;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class CaseCreationRequest extends ProductCreationRequest {

    @Positive
    @JsonProperty(value = "max_cpu_cooler_height", required = true)
    Double maxCpuCoolerHeight;

    @Positive
    @JsonProperty(value = "max_gpu_length", required = true)
    Double maxGpuLength;

    @Positive
    @JsonProperty(value = "pre_installed_fans", required = true)
    Integer preInstalledFans;

    @NotBlank(message = "'tower_type' field can not be blank")
    @Pattern(
            regexp = TOWER_TYPE_ENUM_PATTERN,
            message = WRONG_ENUM_PATTERN_COMMON_MESSAGE
    )
    @JsonProperty(value = "tower_type", required = true)
    String towerType;

    public CaseCreationRequest(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final Double maxCpuCoolerHeight,
            final Double maxGpuLength,
            final Integer preInstalledFans,
            final String towerType
    ) {
        super(name, price, purchasedPrice, count);
        this.maxCpuCoolerHeight = maxCpuCoolerHeight;
        this.maxGpuLength = maxGpuLength;
        this.preInstalledFans = preInstalledFans;
        this.towerType = towerType;
    }
}