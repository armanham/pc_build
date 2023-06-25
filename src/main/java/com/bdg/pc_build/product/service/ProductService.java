package com.bdg.pc_build.product.service;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.dto.main_component.*;
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

    CPUDTO saveCPU(CPUDTO dto);

    InternalHardDriveDTO saveInternalHardDrive(InternalHardDriveDTO dto);

    GPUDTO saveGPU(GPUDTO dto);

    ExternalHardDriveDTO saveExternalHardDrive(ExternalHardDriveDTO dto);

    MotherboardDTO saveMotherboard(MotherboardDTO dto);

    PowerSupplyDTO savePowerSupply(PowerSupplyDTO dto);

    RAMDTO saveRAM(RAMDTO dto);


    //save methods for peripheral components
    HeadsetDTO saveHeadset(HeadsetDTO dto);

    KeyboardDTO saveKeyboard(KeyboardDTO dto);

    MouseDTO saveMouse(MouseDTO dto);

    SpeakerDTO saveSpeaker(SpeakerDTO dto);


    //find methods for display components
    List<MonitorDTO> findMonitorByPurchasedPrice(Double minPurchasedPrice, Double maxPurchasedPrice);


    //find methods for main components
    List<CaseDTO> findCaseByPurchasedPrice(Double minPurchasedPrice, Double maxPurchasedPrice);

    List<CoolerDTO> findCoolerByPurchasedPrice(Double minPurchasedPrice, Double maxPurchasedPrice);

    List<CPUCoolerDTO> findCpuCoolerByPurchasedPrice(Double minPurchasedPrice, Double maxPurchasedPrice);

    List<CPUDTO> findCpuByPurchasedPrice(Double minPurchasedPrice, Double maxPurchasedPrice);

    List<InternalHardDriveDTO> findInternalHardDriveByPurchasedPrice(Double minPurchasedPrice, Double maxPurchasedPrice);

    List<GPUDTO> findGpuByPurchasedPrice(Double minPurchasedPrice, Double maxPurchasedPrice);

    List<ExternalHardDriveDTO> findExternalHardDriveByPurchasedPrice(Double minPurchasedPrice, Double maxPurchasedPrice);

    List<MotherboardDTO> findMotherboardByPurchasedPrice(Double minPurchasedPrice, Double maxPurchasedPrice);

    List<PowerSupplyDTO> findPowerSupplyByPurchasedPrice(Double minPurchasedPrice, Double maxPurchasedPrice);

    List<RAMDTO> findRamByPurchasedPrice(Double minPurchasedPrice, Double maxPurchasedPrice);


    //find methods for peripheral components
    List<HeadsetDTO> findHeadsetByPurchasedPrice(Double minPurchasedPrice, Double maxPurchasedPrice);

    List<KeyboardDTO> findKeyboardByPurchasedPrice(Double minPurchasedPrice, Double maxPurchasedPrice);

    List<MouseDTO> findMouseByPurchasedPrice(Double minPurchasedPrice, Double maxPurchasedPrice);

    List<SpeakerDTO> findSpeakerByPurchasedPrice(Double minPurchasedPrice, Double maxPurchasedPrice);


    void updatePriceById(Long id, Double newPrice);

    void reduceCountById(Long id, Integer countToBeReduced);


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