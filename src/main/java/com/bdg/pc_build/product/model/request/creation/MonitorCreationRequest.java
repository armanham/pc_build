package com.bdg.pc_build.product.model.request.creation;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

import static com.bdg.pc_build.checking.pattern.Pattern.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MonitorCreationRequest extends PR{

//    String name;
//
//    String price;
//
//    String purchasedPrice;
//
//    String count;

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
    @JsonProperty(value = "screen_type", required = true)
    String screenType;

//    public MonitorCreationRequest(final ProductRequest productRequest) {
//        this.name = productRequest.name();
//        this.price = productRequest.price();
//        this.purchasedPrice = productRequest.purchasedPrice();
//        this.count = productRequest.count();
//
//        this.screenSize = productRequest.screenSize();
//        this.refreshRate = productRequest.refreshRate();
//        this.screenType = productRequest.screenType();
//    }

    //    public MonitorCreationRequest() {
//    }

//    @Validated
//    public MonitorCreationRequest initMonitorRequestFromProductRequest(final ProductRequest productRequest) {
//        return new MonitorCreationRequest(productRequest);
//    this.name = productRequest.name();
//        this.price = productRequest.price();
//        this.purchasedPrice = productRequest.purchasedPrice();
//        this.count = productRequest.count();
//
//        this.screenSize = productRequest.screenSize();
//        this.refreshRate = productRequest.refreshRate();
//        this.screenType = productRequest.screenType();
    //}
}