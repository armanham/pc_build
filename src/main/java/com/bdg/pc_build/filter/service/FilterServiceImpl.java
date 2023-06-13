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

    @Override
    public List<ProductDTO> findAllBasedOnTerm(final String term) {
        List<ProductDTO> productList = new ArrayList<>();

        productList.addAll(findAllMonitorsBasedOnTerm(term));
        productList.addAll(findAllCasesBasedOnTerm(term));
        productList.addAll(findAllCoolersBasedOnTerm(term));
        productList.addAll(findAllCPUCoolersBasedOnTerm(term));
        productList.addAll(findAllCPUsBasedOnTerm(term));
        productList.addAll(findAllGPUsBasedOnTerm(term));
        productList.addAll(findAllInternalHardDrivesBasedOnTerm(term));
        productList.addAll(findAllMotherboardsBasedOnTerm(term));
        productList.addAll(findAllPowerSuppliesBasedOnTerm(term));
        productList.addAll(findAllRamsBasedOnTerm(term));
        productList.addAll(findAllExternalHardDrivesBasedOnTerm(term));
        productList.addAll(findAllHeadsetsBasedOnTerm(term));
        productList.addAll(findAllKeyboardsBasedOnTerm(term));
        productList.addAll(findAllMousesBasedOnTerm(term));
        productList.addAll(findAllSpeakersBasedOnTerm(term));

        return productList;
    }

    private List<MonitorDTO> findAllMonitorsBasedOnTerm(final String term) {
        return monitorDAO.findAllMonitorsBasedOnTerm(term.trim())
                .stream()
                .map(MonitorDTO::initDTOFromEntity)
                .toList();
    }

    private List<CaseDTO> findAllCasesBasedOnTerm(final String term) {
        return caseDAO.findAllCasesBasedOnTerm(term.trim())
                .stream()
                .map(CaseDTO::initDTOFromEntity)
                .toList();
    }

    private List<CoolerDTO> findAllCoolersBasedOnTerm(final String term) {
        return coolerDAO.findAllCoolersBasedOnTerm(term.trim())
                .stream()
                .map(CoolerDTO::initDTOFromEntity)
                .toList();
    }

    private List<CPUCoolerDTO> findAllCPUCoolersBasedOnTerm(final String term) {
        return cpuCoolerDAO.findAllCPUCoolersBasedOnTerm(term.trim())
                .stream()
                .map(CPUCoolerDTO::initDTOFromEntity)
                .toList();
    }

    private List<CPUDTO> findAllCPUsBasedOnTerm(final String term) {
        return cpuDAO.findAllCPUsBasedOnTerm(term.trim())
                .stream()
                .map(CPUDTO::initDTOFromEntity)
                .toList();
    }

    private List<GPUDTO> findAllGPUsBasedOnTerm(final String term) {
        return gpuDAO.findAllGPUsBasedOnTerm(term.trim())
                .stream()
                .map(GPUDTO::initDTOFromEntity)
                .toList();
    }

    private List<InternalHardDriveDTO> findAllInternalHardDrivesBasedOnTerm(final String term) {
        return internalHardDriveDAO.findAllInternalHardDrivesBasedOnTerm(term.trim())
                .stream()
                .map(InternalHardDriveDTO::initDTOFromEntity)
                .toList();
    }

    private List<MotherboardDTO> findAllMotherboardsBasedOnTerm(final String term) {
        return motherboardDAO.findAllMotherboardsBasedOnTerm(term.trim())
                .stream()
                .map(MotherboardDTO::initDTOFromEntity)
                .toList();
    }

    private List<PowerSupplyDTO> findAllPowerSuppliesBasedOnTerm(final String term) {
        return powerSupplyDAO.findAllPowerSuppliesBasedOnTerm(term.trim())
                .stream()
                .map(PowerSupplyDTO::initDTOFromEntity)
                .toList();
    }

    private List<RAMDTO> findAllRamsBasedOnTerm(final String term) {
        return ramDAO.findAllRamsBasedOnTerm(term.trim())
                .stream()
                .map(RAMDTO::initDTOFromEntity)
                .toList();
    }

    private List<ExternalHardDriveDTO> findAllExternalHardDrivesBasedOnTerm(final String term) {
        return externalHardDriveDAO.findAllExternalHardDrivesBasedOnTerm(term.trim())
                .stream()
                .map(ExternalHardDriveDTO::initDTOFromEntity)
                .toList();
    }

    private List<HeadsetDTO> findAllHeadsetsBasedOnTerm(final String term) {
        return headsetDAO.findAllHeadsetsBasedOnTerm(term.trim())
                .stream()
                .map(HeadsetDTO::initDTOFromEntity)
                .toList();
    }

    private List<KeyboardDTO> findAllKeyboardsBasedOnTerm(final String term) {
        return keyboardDAO.findAllKeyboardsBasedOnTerm(term.trim())
                .stream()
                .map(KeyboardDTO::initDTOFromEntity)
                .toList();
    }

    private List<MouseDTO> findAllMousesBasedOnTerm(final String term) {
        return mouseDAO.findAllMousesBasedOnTerm(term.trim())
                .stream()
                .map(MouseDTO::initDTOFromEntity)
                .toList();
    }

    private List<SpeakerDTO> findAllSpeakersBasedOnTerm(final String term) {
        return speakerDAO.findAllSpeakersBasedOnTerm(term.trim())
                .stream()
                .map(SpeakerDTO::initDTOFromEntity)
                .toList();
    }

    private List<MonitorDTO> findAllMonitorsByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        if (minPrice == null) {
            minPrice = monitorDAO.getMinPriceFromAllProduct();
        }
        if (maxPrice == null) {
            maxPrice = monitorDAO.getMaxPriceFromAllProduct();
        }
        return monitorDAO.filterAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice)
                .stream()
                .map(MonitorDTO::initDTOFromEntity)
                .toList();
    }

    private List<CaseDTO> findAllCasesByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        if (minPrice == null) {
            minPrice = caseDAO.getMinPriceFromAllProduct();
        }
        if (maxPrice == null) {
            maxPrice = caseDAO.getMaxPriceFromAllProduct();
        }
        return caseDAO.filterAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice)
                .stream()
                .map(CaseDTO::initDTOFromEntity)
                .toList();
    }

    private List<CoolerDTO> findAllCoolersByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        if (minPrice == null) {
            minPrice = coolerDAO.getMinPriceFromAllProduct();
        }
        if (maxPrice == null) {
            maxPrice = coolerDAO.getMaxPriceFromAllProduct();
        }
        return coolerDAO.filterAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice)
                .stream()
                .map(CoolerDTO::initDTOFromEntity)
                .toList();
    }

    private List<CPUDTO> findAllCPUsByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        if (minPrice == null) {
            minPrice = cpuDAO.getMinPriceFromAllProduct();
        }
        if (maxPrice == null) {
            maxPrice = cpuDAO.getMaxPriceFromAllProduct();
        }
        return cpuDAO.filterAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice)
                .stream()
                .map(CPUDTO::initDTOFromEntity)
                .toList();
    }

    private List<CPUCoolerDTO> findAllCPUCoolersByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        if (minPrice == null) {
            minPrice = cpuCoolerDAO.getMinPriceFromAllProduct();
        }
        if (maxPrice == null) {
            maxPrice = cpuCoolerDAO.getMaxPriceFromAllProduct();
        }
        return cpuCoolerDAO.filterAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice)
                .stream()
                .map(CPUCoolerDTO::initDTOFromEntity)
                .toList();
    }

    private List<GPUDTO> findAllGPUsByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        if (minPrice == null) {
            minPrice = gpuDAO.getMinPriceFromAllProduct();
        }
        if (maxPrice == null) {
            maxPrice = gpuDAO.getMaxPriceFromAllProduct();
        }
        return gpuDAO.filterAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice)
                .stream()
                .map(GPUDTO::initDTOFromEntity)
                .toList();
    }

    private List<InternalHardDriveDTO> findAllInternalHardDrivesByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        if (minPrice == null) {
            minPrice = internalHardDriveDAO.getMinPriceFromAllProduct();
        }
        if (maxPrice == null) {
            maxPrice = internalHardDriveDAO.getMaxPriceFromAllProduct();
        }
        return internalHardDriveDAO.filterAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice)
                .stream()
                .map(InternalHardDriveDTO::initDTOFromEntity)
                .toList();
    }

    private List<MotherboardDTO> findAllMotherboardsByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        if (minPrice == null) {
            minPrice = motherboardDAO.getMinPriceFromAllProduct();
        }
        if (maxPrice == null) {
            maxPrice = motherboardDAO.getMaxPriceFromAllProduct();
        }
        return motherboardDAO.filterAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice)
                .stream()
                .map(MotherboardDTO::initDTOFromEntity)
                .toList();
    }

    private List<PowerSupplyDTO> findAllPowerSuppliesByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        if (minPrice == null) {
            minPrice = powerSupplyDAO.getMinPriceFromAllProduct();
        }
        if (maxPrice == null) {
            maxPrice = powerSupplyDAO.getMaxPriceFromAllProduct();
        }
        return powerSupplyDAO.filterAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice)
                .stream()
                .map(PowerSupplyDTO::initDTOFromEntity)
                .toList();
    }

    private List<RAMDTO> findAllRamsByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        if (minPrice == null) {
            minPrice = ramDAO.getMinPriceFromAllProduct();
        }
        if (maxPrice == null) {
            maxPrice = ramDAO.getMaxPriceFromAllProduct();
        }
        return ramDAO.filterAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice)
                .stream()
                .map(RAMDTO::initDTOFromEntity)
                .toList();
    }

    private List<ExternalHardDriveDTO> findAllExternalHardDrivesByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        if (minPrice == null) {
            minPrice = externalHardDriveDAO.getMinPriceFromAllProduct();
        }
        if (maxPrice == null) {
            maxPrice = externalHardDriveDAO.getMaxPriceFromAllProduct();
        }
        return externalHardDriveDAO.filterAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice)
                .stream()
                .map(ExternalHardDriveDTO::initDTOFromEntity)
                .toList();
    }

    private List<HeadsetDTO> findAllHeadsetsByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        if (minPrice == null) {
            minPrice = headsetDAO.getMinPriceFromAllProduct();
        }
        if (maxPrice == null) {
            maxPrice = headsetDAO.getMaxPriceFromAllProduct();
        }
        return headsetDAO.filterAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice)
                .stream()
                .map(HeadsetDTO::initDTOFromEntity)
                .toList();
    }

    private List<KeyboardDTO> findAllKeyboardsByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        if (minPrice == null) {
            minPrice = keyboardDAO.getMinPriceFromAllProduct();
        }
        if (maxPrice == null) {
            maxPrice = keyboardDAO.getMaxPriceFromAllProduct();
        }
        return keyboardDAO.filterAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice)
                .stream()
                .map(KeyboardDTO::initDTOFromEntity)
                .toList();
    }

    private List<MouseDTO> findAllMousesByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        if (minPrice == null) {
            minPrice = mouseDAO.getMinPriceFromAllProduct();
        }
        if (maxPrice == null) {
            maxPrice = mouseDAO.getMaxPriceFromAllProduct();
        }
        return mouseDAO.filterAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice)
                .stream()
                .map(MouseDTO::initDTOFromEntity)
                .toList();
    }

    private List<SpeakerDTO> findAllSpeakersByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        if (minPrice == null) {
            minPrice = speakerDAO.getMinPriceFromAllProduct();
        }
        if (maxPrice == null) {
            maxPrice = speakerDAO.getMaxPriceFromAllProduct();
        }
        return speakerDAO.filterAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice)
                .stream()
                .map(SpeakerDTO::initDTOFromEntity)
                .toList();
    }
}