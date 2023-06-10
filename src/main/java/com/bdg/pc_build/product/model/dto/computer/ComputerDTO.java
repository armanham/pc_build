package com.bdg.pc_build.product.model.dto.computer;

import com.bdg.pc_build.product.model.dto.display.MonitorDTO;
import com.bdg.pc_build.product.model.dto.main_component.*;
import com.bdg.pc_build.product.model.dto.peripheral.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ComputerDTO {

    Long id;

    String constructor;

    Double price;

    MonitorDTO monitor;

    CaseDTO aCase;

    CoolerDTO cooler;

    CPUDTO cpu;

    CPUCoolerDTO cpuCooler;

    ExternalHardDriveDTO externalHardDrive;

    GPUDTO gpu;

    InternalHardDriveDTO internalHardDrive;

    MotherboardDTO motherboard;

    PowerSupplyDTO powerSupply;

    RAMDTO ram;

    HeadsetDTO headset;

    KeyboardDTO keyboard;

    MouseDTO mouse;

    SpeakerDTO speaker;


    @Builder
    public ComputerDTO(
            final MonitorDTO monitor,
            final CaseDTO aCase,
            final CoolerDTO cooler,
            final CPUDTO cpu,
            final CPUCoolerDTO cpuCooler,
            final ExternalHardDriveDTO externalHardDrive,
            final GPUDTO gpu,
            final InternalHardDriveDTO internalHardDrive,
            final MotherboardDTO motherboard,
            final PowerSupplyDTO powerSupply,
            final RAMDTO ram, HeadsetDTO headset,
            final KeyboardDTO keyboard,
            final MouseDTO mouse,
            final SpeakerDTO speaker) {
        this.monitor = monitor;
        this.aCase = aCase;
        this.cooler = cooler;
        this.cpu = cpu;
        this.cpuCooler = cpuCooler;
        this.externalHardDrive = externalHardDrive;
        this.gpu = gpu;
        this.internalHardDrive = internalHardDrive;
        this.motherboard = motherboard;
        this.powerSupply = powerSupply;
        this.ram = ram;
        this.headset = headset;
        this.keyboard = keyboard;
        this.mouse = mouse;
        this.speaker = speaker;
    }
}