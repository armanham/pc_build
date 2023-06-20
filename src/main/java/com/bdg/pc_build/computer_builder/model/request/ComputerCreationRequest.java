package com.bdg.pc_build.computer_builder.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class ComputerCreationRequest {

    @JsonProperty(value = "case_name")
    String aCaseName;

    @JsonProperty(value = "cooler_names")
    List<String> coolerNames;

    @JsonProperty(value = "cpu_name")
    String cpuName;

    @JsonProperty(value = "cpu_cooler_name")
    String cpuCoolerName;

    @JsonProperty(value = "gpu_name")
    String gpuName;

    @JsonProperty(value = "internal_hard_drive_names")
    List<String> internalHardDriveNames;

    @JsonProperty(value = "motherboard_name")
    String motherboardName;

    @JsonProperty(value = "power_supply_name")
    String powerSupplyName;

    @JsonProperty(value = "ram_names")
    List<String> ramNames;

    @JsonProperty(value = "external_hard_drive_names")
    List<String> externalHardDriveNames;

    @JsonProperty(value = "headset_names")
    List<String> headsetNames;

    @JsonProperty(value = "keyboard_names")
    List<String> keyboardNames;

    @JsonProperty(value = "monitor_names")
    List<String> monitorNames;

    @JsonProperty(value = "mouse_names")
    List<String> mouseNames;

    @JsonProperty(value = "speaker_names")
    List<String> speakerNames;

}