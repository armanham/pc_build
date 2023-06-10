package com.bdg.pc_build.product.service;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.dto.display.MonitorDTO;
import com.bdg.pc_build.product.model.dto.main_component.*;
import com.bdg.pc_build.product.model.dto.main_component.InternalHardDriveDTO;
import com.bdg.pc_build.product.model.dto.peripheral.*;

import java.util.List;

public interface ProductService {

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

    MotherboardDTO saveMotherBoard(MotherboardDTO dto);

    PowerSupplyDTO savePowerSupply(PowerSupplyDTO dto);

    RAMDTO saveRAM(RAMDTO dto);


    //save methods for peripheral components
    HeadsetDTO saveHeadSet(HeadsetDTO dto);

    KeyboardDTO saveKeyboard(KeyboardDTO dto);

    MouseDTO saveMouse(MouseDTO dto);

    SpeakerDTO saveSpeaker(SpeakerDTO dto);


    //general find method
    List<ProductDTO> findAllProductsByPrice(Double minPrice, Double maxPrice);

    List<ProductDTO> findAllProductsByNameIgnoreCaseAndLikeTerm(String name);


    //find methods for display components
    MonitorDTO findMonitorByName(String name);

    List<MonitorDTO> findMonitorByPrice(Double minPrice, Double maxPrice);

    List<MonitorDTO> findMonitorByPurchasedPrice(Double minPurchasedPrice, Double maxPurchasedPrice);


    //find methods for main components
    CaseDTO findCaseByName(String name);

    List<CaseDTO> findCaseByPrice(Double minPrice, Double maxPrice);

    List<CaseDTO> findCaseByPurchasedPrice(Double minPurchasedPrice, Double maxPurchasedPrice);

    CoolerDTO findCoolerByName(String name);

    List<CoolerDTO> findCoolerByPrice(Double minPrice, Double maxPrice);

    List<CoolerDTO> findCoolerByPurchasedPrice(Double minPurchasedPrice, Double maxPurchasedPrice);

    CPUCoolerDTO findCPUCoolerByName(String name);

    List<CPUCoolerDTO> findCPUCoolerByPrice(Double minPrice, Double maxPrice);

    List<CPUCoolerDTO> findCPUCoolerByPurchasedPrice(Double minPurchasedPrice, Double maxPurchasedPrice);

    CPUDTO findCPUByName(String name);

    List<CPUDTO> findCPUByPrice(Double minPrice, Double maxPrice);

    List<CPUDTO> findCPUByPurchasedPrice(Double minPurchasedPrice, Double maxPurchasedPrice);

    InternalHardDriveDTO findInternalHardDriveByName(String name);

    List<InternalHardDriveDTO> findInternalHardDriveByPrice(Double minPrice, Double maxPrice);

    List<InternalHardDriveDTO> findInternalHardDriveByPurchasedPrice(Double minPurchasedPrice, Double maxPurchasedPrice);

    GPUDTO findGPUByName(String name);

    List<GPUDTO> findGPUByPrice(Double minPrice, Double maxPrice);

    List<GPUDTO> findGPUByPurchasedPrice(Double minPurchasedPrice, Double maxPurchasedPrice);

    ExternalHardDriveDTO findExternalHardDriveByName(String name);

    List<ExternalHardDriveDTO> findExternalHardDriveByPrice(Double minPrice, Double maxPrice);

    List<ExternalHardDriveDTO> findExternalHardDriveByPurchasedPrice(Double minPurchasedPrice, Double maxPurchasedPrice);

    MotherboardDTO findMotherboardByName(String name);

    List<MotherboardDTO> findMotherboardByPrice(Double minPrice, Double maxPrice);

    List<MotherboardDTO> findMotherboardByPurchasedPrice(Double minPurchasedPrice, Double maxPurchasedPrice);

    PowerSupplyDTO findPowerSupplyByName(String name);

    List<PowerSupplyDTO> findPowerSupplyByPrice(Double minPrice, Double maxPrice);

    List<PowerSupplyDTO> findPowerSupplyByPurchasedPrice(Double minPurchasedPrice, Double maxPurchasedPrice);

    RAMDTO findRAMByName(String name);

    List<RAMDTO> findRAMByPrice(Double minPrice, Double maxPrice);

    List<RAMDTO> findRAMByPurchasedPrice(Double minPurchasedPrice, Double maxPurchasedPrice);


    //find methods for peripheral components
    HeadsetDTO findHeadsetByName(String name);

    List<HeadsetDTO> findHeadsetByPrice(Double minPrice, Double maxPrice);

    List<HeadsetDTO> findHeadsetByPurchasedPrice(Double minPurchasedPrice, Double maxPurchasedPrice);

    KeyboardDTO findKeyboardByName(String name);

    List<KeyboardDTO> findKeyboardByPrice(Double minPrice, Double maxPrice);

    List<KeyboardDTO> findKeyboardByPurchasedPrice(Double minPurchasedPrice, Double maxPurchasedPrice);

    MouseDTO findMouseByName(String name);

    List<MouseDTO> findMouseByPrice(Double minPrice, Double maxPrice);

    List<MouseDTO> findMouseByPurchasedPrice(Double minPurchasedPrice, Double maxPurchasedPrice);

    SpeakerDTO findSpeakerByName(String name);

    List<SpeakerDTO> findSpeakerByPrice(Double minPrice, Double maxPrice);

    List<SpeakerDTO> findSpeakerByPurchasedPrice(Double minPurchasedPrice, Double maxPurchasedPrice);


    //update methods for display components
    MonitorDTO updateMonitorPriceByName(String name, Double newPrice);


    //update methods for main components
    CaseDTO updateCasePriceByName(String name, Double newPrice);

    CoolerDTO updateCoolerPriceByName(String name, Double newPrice);

    CPUDTO updateCPUPriceByName(String name, Double newPrice);

    CPUCoolerDTO updateCPUCoolerPriceByName(String name, Double newPrice);

    InternalHardDriveDTO updateInternalHardDrivePriceByName(String name, Double newPrice);

    GPUDTO updateGPUPriceByName(String name, Double newPrice);

    ExternalHardDriveDTO updateExternalHardDrivePriceByName(String name, Double newPrice);

    MotherboardDTO updateMotherboardPriceByName(String name, Double newPrice);

    PowerSupplyDTO updatePowerSupplyPriceByName(String name, Double newPrice);

    RAMDTO updateRAMPriceByName(String name, Double newPrice);

    HeadsetDTO updateHeadsetPriceByName(String name, Double newPrice);

    KeyboardDTO updateKeyboardPriceByName(String name, Double newPrice);

    MouseDTO updateMousePriceByName(String name, Double newPrice);

    SpeakerDTO updateSpeakerPriceByName(String name, Double newPrice);


    //reduce methods for display components
    MonitorDTO reduceMonitorCountByName(String name, Integer count);


    //reduce methods for main components
    CaseDTO reduceCaseCountByName(String name, Integer count);

    CoolerDTO reduceCoolerCountByName(String name, Integer count);

    CPUDTO reduceCPUCountByName(String name, Integer count);

    CPUCoolerDTO reduceCPUCoolerCountByName(String name, Integer count);

    InternalHardDriveDTO reduceInternalHardDriveCountByName(String name, Integer count);

    GPUDTO reduceGPUCountByName(String name, Integer count);

    ExternalHardDriveDTO reduceExternalHardDriveCountByName(String name, Integer count);

    MotherboardDTO reduceMotherboardCountByName(String name, Integer count);

    PowerSupplyDTO reducePowerSupplyCountByName(String name, Integer count);

    RAMDTO reduceRAMCountByName(String name, Integer count);


    //reduce methods for peripheral components
    HeadsetDTO reduceHeadsetCountByName(String name, Integer count);

    KeyboardDTO reduceKeyboardCountByName(String name, Integer count);

    MouseDTO reduceMouseCountByName(String name, Integer count);

    SpeakerDTO reduceSpeakerCountByName(String name, Integer count);

}