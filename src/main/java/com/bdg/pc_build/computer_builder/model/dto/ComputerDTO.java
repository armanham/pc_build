package com.bdg.pc_build.computer_builder.model.dto;

import com.bdg.pc_build.product.model.dto.main_component.*;
import com.bdg.pc_build.product.model.dto.peripheral.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class ComputerDTO {

    CaseDTO aCase;
    List<CoolerDTO> coolers;
    CPUDTO cpu;
    CPUCoolerDTO cpuCooler;
    GPUDTO gpu;
    List<InternalHardDriveDTO> internalHardDrives;
    MotherboardDTO motherboard;
    PowerSupplyDTO powerSupply;
    List<RAMDTO> rams;
    List<ExternalHardDriveDTO> externalHardDrives;
    List<HeadsetDTO> headsets;
    List<KeyboardDTO> keyboards;
    List<MonitorDTO> monitors;
    List<MouseDTO> mouses;
    List<SpeakerDTO> speakers;

    Double price = 0.0;
}