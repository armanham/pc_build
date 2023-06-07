package com.bdg.pc_build.product.service;

import com.bdg.pc_build.product.model.dto.display.MonitorDTO;
import com.bdg.pc_build.product.model.dto.main_component.*;
import com.bdg.pc_build.product.model.dto.peripheral.HeadsetDTO;
import com.bdg.pc_build.product.model.dto.peripheral.KeyboardDTO;
import com.bdg.pc_build.product.model.dto.peripheral.MouseDTO;
import com.bdg.pc_build.product.model.dto.peripheral.SpeakerDTO;

public interface ProductService {
    //save methods for display components
    MonitorDTO saveMonitor(MonitorDTO dto);

    //save methods for main components
    CaseDTO saveCase(CaseDTO dto);
    CoolerDTO saveCooler(CoolerDTO dto);
    CPUCoolerDTO saveCpuCooler(CPUCoolerDTO dto);
    CPUDTO saveCPU(CPUDTO dto);
    ExternalHardDriveDTO saveExternalHardDrive(ExternalHardDriveDTO dto);
    GPUDTO saveGPU(GPUDTO dto);
    InternalHardDriveDTO saveInternalHardDrive(InternalHardDriveDTO dto);
    MotherboardDTO saveMotherBoard(MotherboardDTO dto);
    PowerSupplyDTO savePowerSupply(PowerSupplyDTO dto);
    RAMDTO saveRAM(RAMDTO dto);

    //save methods for peripheral components
    HeadsetDTO saveHeadSet(HeadsetDTO dto);
    KeyboardDTO saveKeyboard(KeyboardDTO dto);
    MouseDTO saveMouse(MouseDTO dto);
    SpeakerDTO saveSpeaker(SpeakerDTO dto);
}
