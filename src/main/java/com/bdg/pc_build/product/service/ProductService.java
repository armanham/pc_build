package com.bdg.pc_build.product.service;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.dto.main.*;
import com.bdg.pc_build.product.model.dto.peripheral.*;

import java.util.List;

public interface ProductService {

    ProductDTO findById(Long id);


    //save methods for display components
    MonitorDTO saveMonitor(MonitorDTO dto);

    //save methods for main components
    CaseDTO saveCase(CaseDTO dto);

    CoolerDTO saveCooler(CoolerDTO dto);

    CPUCoolerDTO saveCpuCooler(CPUCoolerDTO dto);

    CPUDTO saveCpu(CPUDTO dto);

    InternalHardDriveDTO saveInternalHardDrive(InternalHardDriveDTO dto);

    GPUDTO saveGpu(GPUDTO dto);

    ExternalHardDriveDTO saveExternalHardDrive(ExternalHardDriveDTO dto);

    MotherboardDTO saveMotherboard(MotherboardDTO dto);

    PowerSupplyDTO savePowerSupply(PowerSupplyDTO dto);

    RAMDTO saveRam(RAMDTO dto);


    //save methods for peripheral components
    HeadsetDTO saveHeadset(HeadsetDTO dto);

    KeyboardDTO saveKeyboard(KeyboardDTO dto);

    MouseDTO saveMouse(MouseDTO dto);

    SpeakerDTO saveSpeaker(SpeakerDTO dto);


    void updatePriceById(Long id, Double newPrice);

    void reduceCountById(Long id, Integer countToBeReduced);

    void increaseCountById(Long id, Integer countToBeIncreased);


    List<CaseDTO> getAllCases();

    List<CoolerDTO> getAllCoolers();

    List<CPUCoolerDTO> getAllCpuCoolers();

    List<CPUDTO> getAllCpus();

    List<GPUDTO> getAllGpus();

    List<InternalHardDriveDTO> getAllInternalHardDrives();

    List<MotherboardDTO> getAllMotherboards();

    List<PowerSupplyDTO> getAllPowerSupplies();

    List<RAMDTO> getAllRams();

    List<ExternalHardDriveDTO> getAllExternalHardDrives();

    List<HeadsetDTO> getAllHeadsets();

    List<KeyboardDTO> getAllKeyboards();

    List<MonitorDTO> getAllMonitors();

    List<MouseDTO> getAllMice();

    List<SpeakerDTO> getAllSpeakers();

}