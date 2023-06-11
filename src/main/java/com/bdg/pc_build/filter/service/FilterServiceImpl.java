package com.bdg.pc_build.filter.service;

import com.bdg.pc_build.filter.model.dto.ProductFilterDTO;
import com.bdg.pc_build.filter.model.dto.display.MonitorFilterDTO;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.dto.display.MonitorDTO;
import com.bdg.pc_build.product.model.dto.main_component.*;
import com.bdg.pc_build.product.model.dto.peripheral.*;
import com.bdg.pc_build.product.repository.display.MonitorDAO;
import com.bdg.pc_build.product.repository.main_component.*;
import com.bdg.pc_build.product.repository.peripheral.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.StreamSupport.stream;

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
    ExternalHardDriveDAO externalHardDriveDAO;
    GPUDAO gpuDAO;
    InternalHardDriveDAO internalHardDriveDAO;
    MotherboardDAO motherboardDAO;
    PowerSupplyDAO powerSupplyDAO;
    RAMDAO ramDAO;

    //Peripheral repositories
    HeadsetDAO headsetDAO;
    KeyboardDAO keyboardDAO;
    MouseDAO mouseDAO;
    SpeakerDAO speakerDAO;

    String[] strings = {"IPS", "Nano IPS", "VA"};
    String joinedStrings = String.join(", ", strings);


    @Override
    public List<ProductDTO> filterAllProductsByNameAndPrice(final ProductFilterDTO filterDTO) {
        List<ProductDTO> productDTOList = new ArrayList<>();

        productDTOList.addAll(monitorDAO.filterAllProductsBasedOnNameAndPrice(filterDTO.getName(), filterDTO.getMinPrice(), filterDTO.getMaxPrice())
                .stream().map(MonitorDTO::initDTOFromEntity).toList());

        productDTOList.addAll(caseDAO.filterAllProductsBasedOnNameAndPrice(filterDTO.getName(), filterDTO.getMinPrice(), filterDTO.getMaxPrice())
                .stream().map(CaseDTO::initDTOFromEntity).toList());

        productDTOList.addAll(coolerDAO.filterAllProductsBasedOnNameAndPrice(filterDTO.getName(), filterDTO.getMinPrice(), filterDTO.getMaxPrice())
                .stream().map(CoolerDTO::initDTOFromEntity).toList());

        productDTOList.addAll(cpuDAO.filterAllProductsBasedOnNameAndPrice(filterDTO.getName(), filterDTO.getMinPrice(), filterDTO.getMaxPrice())
                .stream().map(CPUDTO::initDTOFromEntity).toList());

        productDTOList.addAll(cpuCoolerDAO.filterAllProductsBasedOnNameAndPrice(filterDTO.getName(), filterDTO.getMinPrice(), filterDTO.getMaxPrice())
                .stream().map(CPUCoolerDTO::initDTOFromEntity).toList());

        productDTOList.addAll(gpuDAO.filterAllProductsBasedOnNameAndPrice(filterDTO.getName(), filterDTO.getMinPrice(), filterDTO.getMaxPrice())
                .stream().map(GPUDTO::initDTOFromEntity).toList());

        productDTOList.addAll(internalHardDriveDAO.filterAllProductsBasedOnNameAndPrice(filterDTO.getName(), filterDTO.getMinPrice(), filterDTO.getMaxPrice())
                .stream().map(InternalHardDriveDTO::initDTOFromEntity).toList());

        productDTOList.addAll(motherboardDAO.filterAllProductsBasedOnNameAndPrice(filterDTO.getName(), filterDTO.getMinPrice(), filterDTO.getMaxPrice())
                .stream().map(MotherboardDTO::initDTOFromEntity).toList());

        productDTOList.addAll(powerSupplyDAO.filterAllProductsBasedOnNameAndPrice(filterDTO.getName(), filterDTO.getMinPrice(), filterDTO.getMaxPrice())
                .stream().map(PowerSupplyDTO::initDTOFromEntity).toList());

        productDTOList.addAll(ramDAO.filterAllProductsBasedOnNameAndPrice(filterDTO.getName(), filterDTO.getMinPrice(), filterDTO.getMaxPrice())
                .stream().map(RAMDTO::initDTOFromEntity).toList());

        productDTOList.addAll(externalHardDriveDAO.filterAllProductsBasedOnNameAndPrice(filterDTO.getName(), filterDTO.getMinPrice(), filterDTO.getMaxPrice())
                .stream().map(ExternalHardDriveDTO::initDTOFromEntity).toList());

        productDTOList.addAll(headsetDAO.filterAllProductsBasedOnNameAndPrice(filterDTO.getName(), filterDTO.getMinPrice(), filterDTO.getMaxPrice())
                .stream().map(HeadsetDTO::initDTOFromEntity).toList());

        productDTOList.addAll(keyboardDAO.filterAllProductsBasedOnNameAndPrice(filterDTO.getName(), filterDTO.getMinPrice(), filterDTO.getMaxPrice())
                .stream().map(KeyboardDTO::initDTOFromEntity).toList());

        productDTOList.addAll(mouseDAO.filterAllProductsBasedOnNameAndPrice(filterDTO.getName(), filterDTO.getMinPrice(), filterDTO.getMaxPrice())
                .stream().map(MouseDTO::initDTOFromEntity).toList());

        productDTOList.addAll(speakerDAO.filterAllProductsBasedOnNameAndPrice(filterDTO.getName(), filterDTO.getMinPrice(), filterDTO.getMaxPrice())
                .stream().map(SpeakerDTO::initDTOFromEntity).toList());

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