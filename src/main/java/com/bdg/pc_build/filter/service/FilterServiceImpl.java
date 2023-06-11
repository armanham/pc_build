package com.bdg.pc_build.filter.service;

import com.bdg.pc_build.filter.model.dto.ProductFilterDTO;
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


    @Override
    public List<ProductDTO> filterByRequest(final ProductFilterDTO dto) {
        List<ProductDTO> productDTOList = new ArrayList<>();

        productDTOList.addAll(monitorDAO.filterAllProductsBasedOnSpecification(dto.getName(), dto.getMinPrice(), dto.getMaxPrice())
                .stream().map(MonitorDTO::initDTOFromEntity).toList());

        productDTOList.addAll(caseDAO.filterAllProductsBasedOnSpecification(dto.getName(), dto.getMinPrice(), dto.getMaxPrice())
                .stream().map(CaseDTO::initDTOFromEntity).toList());

        productDTOList.addAll(coolerDAO.filterAllProductsBasedOnSpecification(dto.getName(), dto.getMinPrice(), dto.getMaxPrice())
                .stream().map(CoolerDTO::initDTOFromEntity).toList());

        productDTOList.addAll(cpuDAO.filterAllProductsBasedOnSpecification(dto.getName(), dto.getMinPrice(), dto.getMaxPrice())
                .stream().map(CPUDTO::initDTOFromEntity).toList());

        productDTOList.addAll(cpuCoolerDAO.filterAllProductsBasedOnSpecification(dto.getName(), dto.getMinPrice(), dto.getMaxPrice())
                .stream().map(CPUCoolerDTO::initDTOFromEntity).toList());

        productDTOList.addAll(gpuDAO.filterAllProductsBasedOnSpecification(dto.getName(), dto.getMinPrice(), dto.getMaxPrice())
                .stream().map(GPUDTO::initDTOFromEntity).toList());

        productDTOList.addAll(internalHardDriveDAO.filterAllProductsBasedOnSpecification(dto.getName(), dto.getMinPrice(), dto.getMaxPrice())
                .stream().map(InternalHardDriveDTO::initDTOFromEntity).toList());

        productDTOList.addAll(motherboardDAO.filterAllProductsBasedOnSpecification(dto.getName(), dto.getMinPrice(), dto.getMaxPrice())
                .stream().map(MotherboardDTO::initDTOFromEntity).toList());

        productDTOList.addAll(powerSupplyDAO.filterAllProductsBasedOnSpecification(dto.getName(), dto.getMinPrice(), dto.getMaxPrice())
                .stream().map(PowerSupplyDTO::initDTOFromEntity).toList());

        productDTOList.addAll(ramDAO.filterAllProductsBasedOnSpecification(dto.getName(), dto.getMinPrice(), dto.getMaxPrice())
                .stream().map(RAMDTO::initDTOFromEntity).toList());

        productDTOList.addAll(externalHardDriveDAO.filterAllProductsBasedOnSpecification(dto.getName(), dto.getMinPrice(), dto.getMaxPrice())
                .stream().map(ExternalHardDriveDTO::initDTOFromEntity).toList());

        productDTOList.addAll(headsetDAO.filterAllProductsBasedOnSpecification(dto.getName(), dto.getMinPrice(), dto.getMaxPrice())
                .stream().map(HeadsetDTO::initDTOFromEntity).toList());

        productDTOList.addAll(keyboardDAO.filterAllProductsBasedOnSpecification(dto.getName(), dto.getMinPrice(), dto.getMaxPrice())
                .stream().map(KeyboardDTO::initDTOFromEntity).toList());

        productDTOList.addAll(mouseDAO.filterAllProductsBasedOnSpecification(dto.getName(), dto.getMinPrice(), dto.getMaxPrice())
                .stream().map(MouseDTO::initDTOFromEntity).toList());

        productDTOList.addAll(speakerDAO.filterAllProductsBasedOnSpecification(dto.getName(), dto.getMinPrice(), dto.getMaxPrice())
                .stream().map(SpeakerDTO::initDTOFromEntity).toList());

        return productDTOList;
    }
}