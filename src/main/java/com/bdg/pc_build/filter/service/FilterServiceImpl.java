package com.bdg.pc_build.filter.service;

import com.bdg.pc_build.filter.model.dto.ProductFilterDTO;
import com.bdg.pc_build.filter.model.dto.peripheral.MonitorFilterDTO;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.dto.peripheral.MonitorDTO;
import com.bdg.pc_build.product.model.dto.main_component.*;
import com.bdg.pc_build.product.model.dto.peripheral.*;
import com.bdg.pc_build.product.repository.peripheral.MonitorDAO;
import com.bdg.pc_build.product.repository.main_component.*;
import com.bdg.pc_build.product.repository.peripheral.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class FilterServiceImpl implements FilterService {

    //Display repositories
    MonitorDAO monitorDAO;

    //Main component repositories
    CaseDAO caseDAO;
    CoolerDAO coolerDAO;
    CPUCoolerDAO cpuCoolerDAO;
    CPUDAO cpuDAO;
    GPUDAO gpuDAO;
    InternalHardDriveDAO internalHardDriveDAO;
    MotherboardDAO motherboardDAO;
    PowerSupplyDAO powerSupplyDAO;
    RAMDAO ramDAO;

    //Peripheral repositories
    ExternalHardDriveDAO externalHardDriveDAO;
    HeadsetDAO headsetDAO;
    KeyboardDAO keyboardDAO;
    MouseDAO mouseDAO;
    SpeakerDAO speakerDAO;

    @Override
    public List<ProductDTO> filterAllProductsByNameAndPrice(final ProductFilterDTO filterDTO) {
        List<ProductDTO> productDTOList = new ArrayList<>();

        productDTOList.addAll(findAllMonitorsByNameAndPrice(filterDTO.getName(), filterDTO.getMinPrice(), filterDTO.getMaxPrice()));
        productDTOList.addAll(findAllCasesByNameAndPrice(filterDTO.getName(), filterDTO.getMinPrice(), filterDTO.getMaxPrice()));
        productDTOList.addAll(findAllCoolersByNameAndPrice(filterDTO.getName(), filterDTO.getMinPrice(), filterDTO.getMaxPrice()));
        productDTOList.addAll(findAllCPUsByNameAndPrice(filterDTO.getName(), filterDTO.getMinPrice(), filterDTO.getMaxPrice()));
        productDTOList.addAll(findAllCPUCoolersByNameAndPrice(filterDTO.getName(), filterDTO.getMinPrice(), filterDTO.getMaxPrice()));
        productDTOList.addAll(findAllGPUsByNameAndPrice(filterDTO.getName(), filterDTO.getMinPrice(), filterDTO.getMaxPrice()));
        productDTOList.addAll(findAllInternalHardDrivesByNameAndPrice(filterDTO.getName(), filterDTO.getMinPrice(), filterDTO.getMaxPrice()));
        productDTOList.addAll(findAllMotherboardsByNameAndPrice(filterDTO.getName(), filterDTO.getMinPrice(), filterDTO.getMaxPrice()));
        productDTOList.addAll(findAllPowerSuppliesByNameAndPrice(filterDTO.getName(), filterDTO.getMinPrice(), filterDTO.getMaxPrice()));
        productDTOList.addAll(findAllRamsByNameAndPrice(filterDTO.getName(), filterDTO.getMinPrice(), filterDTO.getMaxPrice()));
        productDTOList.addAll(findAllExternalHardDrivesByNameAndPrice(filterDTO.getName(), filterDTO.getMinPrice(), filterDTO.getMaxPrice()));
        productDTOList.addAll(findAllHeadsetsByNameAndPrice(filterDTO.getName(), filterDTO.getMinPrice(), filterDTO.getMaxPrice()));
        productDTOList.addAll(findAllKeyboardsByNameAndPrice(filterDTO.getName(), filterDTO.getMinPrice(), filterDTO.getMaxPrice()));
        productDTOList.addAll(findAllMousesByNameAndPrice(filterDTO.getName(), filterDTO.getMinPrice(), filterDTO.getMaxPrice()));
        productDTOList.addAll(findAllSpeakersByNameAndPrice(filterDTO.getName(), filterDTO.getMinPrice(), filterDTO.getMaxPrice()));

        return productDTOList;
    }

    @Override
    public List<MonitorDTO> filterAllMonitorsBasedOnSpecification(final MonitorFilterDTO filterDTO) {
        return monitorDAO.filterAllMonitorsBasedOnSpecification(
                        filterDTO.getName(),
                        filterDTO.getMinPrice(),
                        filterDTO.getMaxPrice(),
                        filterDTO.getMinScreenSize(),
                        filterDTO.getMaxPrice(),
                        filterDTO.getMinRefreshRate(),
                        filterDTO.getMaxRefreshRate(),
                        filterDTO.getScreenTypes()
                )
                .stream()
                .map(MonitorDTO::initDTOFromEntity)
                .toList();
    }
}