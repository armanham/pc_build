package com.bdg.pc_build.filter.service;

import com.bdg.pc_build.filter.model.dto.ProductFilterDTO;
import com.bdg.pc_build.filter.model.dto.main_component.*;
import com.bdg.pc_build.filter.model.dto.peripheral.*;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.dto.main_component.*;
import com.bdg.pc_build.product.model.dto.peripheral.*;

import java.util.List;
import java.util.Set;

public interface FilterService {

    List<ProductDTO> filterAllProductsByNameAndPrice(ProductFilterDTO filterDTO);

    List<CaseDTO> filterAllCasesBasedOnSpecification(CaseFilterDTO filterDTO);

    List<CoolerDTO> filterAllCoolersBasedOnSpecification(CoolerFilterDTO filterDTO);

    List<CPUDTO> filterAllCpusBasedOnSpecification(CPUFilterDTO filterDTO);

    List<CPUCoolerDTO> filterAllCpuCoolersBasedOnSpecification(CPUCoolerFilterDTO filterDTO);

    List<GPUDTO> filterAllGpusBasedOnSpecification(GPUFilterDTO filterDTO);

    List<InternalHardDriveDTO> filterAllInternalHardDrivesBasedOnSpecification(InternalHardDriveFilterDTO filterDTO);

    List<MotherboardDTO> filterAllMotherboardsBasedOnSpecification(MotherboardFilterDTO filterDTO);

    List<PowerSupplyDTO> filterAllPowerSuppliesBasedOnSpecification(PowerSupplyFilterDTO filterDTO);

    List<RAMDTO> filterAllRamsBasedOnSpecification(RAMFilterDTO filterDTO);

    List<ExternalHardDriveDTO> filterAllExternalHardDrivesBasedOnSpecification(ExternalHardDriveFilterDTO filterDTO);

    List<HeadsetDTO> filterAllHeadsetsBasedOnSpecification(HeadsetFilterDTO filterDTO);

    List<KeyboardDTO> filterAllKeyboardsBasedOnSpecification(KeyboardFilterDTO filterDTO);

    List<MonitorDTO> filterAllMonitorsBasedOnSpecification(MonitorFilterDTO filterDTO);

    List<MouseDTO> filterAllMiceBasedOnSpecification(MouseFilterDTO filterDTO);

    List<SpeakerDTO> filterAllSpeakersBasedOnSpecification(SpeakerFilterDTO filterDTO);

    List<ProductDTO> findAllProductsBasedOnTerm(String term);
}