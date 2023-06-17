package com.bdg.pc_build.filter.service;

import com.bdg.pc_build.filter.model.dto.ProductFilterDTO;
import com.bdg.pc_build.filter.model.dto.main_component.*;
import com.bdg.pc_build.filter.model.dto.peripheral.*;
import com.bdg.pc_build.filter.service.FilterFieldsValueResolver.CaseFieldsHolderBasedOnFilterDTO;
import com.bdg.pc_build.filter.service.FilterFieldsValueResolver.GpuFieldsHolderBasedOnFilterDTO;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.dto.main_component.*;
import com.bdg.pc_build.product.model.dto.peripheral.*;
import com.bdg.pc_build.product.model.enumerations.GPUInterfaceType;
import com.bdg.pc_build.product.repository.main_component.*;
import com.bdg.pc_build.product.repository.peripheral.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class FilterServiceImpl implements FilterService {

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
    MonitorDAO monitorDAO;
    HeadsetDAO headsetDAO;
    KeyboardDAO keyboardDAO;
    MouseDAO mouseDAO;
    SpeakerDAO speakerDAO;

    FilterFieldsValueResolver filterFieldsValueResolver;

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


    public List<CaseDTO> filterAllCasesBasedOnSpecification(final CaseFilterDTO filterDTO) {
        CaseFieldsHolderBasedOnFilterDTO caseFieldsHolderBasedOnFilterDTO = filterFieldsValueResolver.resolveAndGetCaseFieldsValuesFromFilterDTO(filterDTO);

        List<CaseDTO> cases = new ArrayList<>(
                caseDAO.findAllProductsByNameLike(caseFieldsHolderBasedOnFilterDTO.name())
                        .stream()
                        .map(CaseDTO::initDTOFromEntity)
                        .toList()
        );
        return null;
    }

    public List<CoolerDTO> filterAllCoolersBasedOnSpecification(final CoolerFilterDTO filterDTO) {



        return null;
    }

    public List<CPUCoolerDTO> filterAllCpuCoolersBasedOnSpecification(final CPUCoolerFilterDTO filterDTO) {



        return null;
    }

    public List<CPUDTO> filterAllCpusBasedOnSpecification(final CPUFilterDTO filterDTO) {


        return null;
    }


    @Override
    public List<GPUDTO> filterAllGpusBasedOnSpecification(final GPUFilterDTO filterDTO) {
        GpuFieldsHolderBasedOnFilterDTO gpuFieldsHolderBasedOnFilterDTOFromFilterDTO = filterFieldsValueResolver.resolveAndGetGpuFieldsValuesFromFilterDTO(filterDTO);

        List<GPUDTO> gpuDTOsByName = gpuDAO.findAllProductsByNameLike(gpuFieldsHolderBasedOnFilterDTOFromFilterDTO.name())
                .stream()
                .map(GPUDTO::initDTOFromEntity)
                .toList();

        List<GPUDTO> gpuDTOsByPrice = gpuDAO.findAllByPriceBetween(
                        gpuFieldsHolderBasedOnFilterDTOFromFilterDTO.minPrice(), gpuFieldsHolderBasedOnFilterDTOFromFilterDTO.maxPrice())
                .stream()
                .map(GPUDTO::initDTOFromEntity)
                .toList();

        List<GPUDTO> gpuDTOsByMemory = gpuDAO.findAllByMemoryBetween(
                        gpuFieldsHolderBasedOnFilterDTOFromFilterDTO.minMemory(), gpuFieldsHolderBasedOnFilterDTOFromFilterDTO.maxMemory())
                .stream()
                .map(GPUDTO::initDTOFromEntity)
                .toList();

        List<GPUDTO> gpuDTOsByCoreClock = gpuDAO.findAllByCoreClockBetween(
                        gpuFieldsHolderBasedOnFilterDTOFromFilterDTO.minCoreClock(), gpuFieldsHolderBasedOnFilterDTOFromFilterDTO.maxCoreClock())
                .stream()
                .map(GPUDTO::initDTOFromEntity)
                .toList();

        List<GPUDTO> gpuDTOsByBoostClock = gpuDAO.findAllByBoostClockBetween(gpuFieldsHolderBasedOnFilterDTOFromFilterDTO.minBoostClock(), gpuFieldsHolderBasedOnFilterDTOFromFilterDTO.maxBoostClock())
                .stream()
                .map(GPUDTO::initDTOFromEntity)
                .toList();

        List<GPUDTO> gpuDTOsByLength = gpuDAO.findAllByLengthBetween(gpuFieldsHolderBasedOnFilterDTOFromFilterDTO.minLength(), gpuFieldsHolderBasedOnFilterDTOFromFilterDTO.maxLength())
                .stream()
                .map(GPUDTO::initDTOFromEntity)
                .toList();

        List<GPUDTO> gpuDTOsByTdp = gpuDAO.findAllByTdpBetween(gpuFieldsHolderBasedOnFilterDTOFromFilterDTO.minTdp(), gpuFieldsHolderBasedOnFilterDTOFromFilterDTO.maxTdp())
                .stream()
                .map(GPUDTO::initDTOFromEntity)
                .toList();

        List<GPUDTO> gpuDTOsByInterfaceTypeList = new ArrayList<>();
        if (gpuFieldsHolderBasedOnFilterDTOFromFilterDTO.gpuInterfaceTypes() != null && !gpuFieldsHolderBasedOnFilterDTOFromFilterDTO.gpuInterfaceTypes().isEmpty()) {
            for (GPUInterfaceType gpuInterfaceType : filterDTO.getGpuInterfaceTypes()) {
                List<GPUDTO> gpuDTOsByGpuInterfaceType = gpuDAO.findAllByGpuInterfaceType(gpuInterfaceType)
                        .stream()
                        .map(GPUDTO::initDTOFromEntity)
                        .toList();
                gpuDTOsByInterfaceTypeList.addAll(gpuDTOsByGpuInterfaceType);
            }
        } else {
            gpuDTOsByInterfaceTypeList.addAll(gpuDAO.findAll()
                    .stream()
                    .map(GPUDTO::initDTOFromEntity)
                    .toList());
        }

        List<GPUDTO> intersection = new ArrayList<>(gpuDTOsByName); // Start with the first list

        List<List<GPUDTO>> listsToIntersect = Arrays.asList(
                gpuDTOsByPrice,
                gpuDTOsByMemory,
                gpuDTOsByCoreClock,
                gpuDTOsByBoostClock,
                gpuDTOsByLength,
                gpuDTOsByTdp,
                gpuDTOsByInterfaceTypeList
        );

        for (List<GPUDTO> list : listsToIntersect) {
            List<GPUDTO> tempList = new ArrayList<>(list);
            intersection.retainAll(tempList);
        }

        return intersection;
    }


    public List<InternalHardDriveDTO> filterAllInternalHardDrivesBasedOnSpecification(final InternalHardDriveFilterDTO filterDTO) {


        return null;
    }

    public List<MotherboardDTO> filterAllMotherboardsBasedOnSpecification(final MotherboardFilterDTO filterDTO) {

        return null;
    }

    public List<PowerSupplyDTO> filterAllPowerSuppliesBasedOnSpecification(final PowerSupplyFilterDTO filterDTO) {


        return null;
    }

    public List<RAMDTO> filterAllRamsBasedOnSpecification(final RAMFilterDTO filterDTO) {


        return null;
    }

    public List<ExternalHardDriveDTO> filterAllExternalHardDrivesBasedOnSpecification(final ExternalHardDriveFilterDTO filterDTO) {


        return null;
    }

    public List<HeadsetDTO> filterAllHeadsetsBasedOnSpecification(final HeadsetFilterDTO filterDTO) {
        return null;
    }

    public List<KeyboardDTO> filterAllKeyboardsBasedOnSpecification(final KeyboardFilterDTO filterDTO) {
        return null;
    }


    @Override
    public List<MonitorDTO> filterAllMonitorsBasedOnSpecification(final MonitorFilterDTO filterDTO) {

        return null;
    }

    public List<MouseDTO> filterAllMiceBasedOnSpecification(final MouseFilterDTO filterDTO) {


       return null;
    }

    public List<SpeakerDTO> filterAllSpeakersBasedOnSpecification(final SpeakerFilterDTO filterDTO) {


        return null;
    }


    @Override
    public List<ProductDTO> findAllProductsBasedOnTerm(final String term) {
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
        return cpuCoolerDAO.findAllCpuCoolersBasedOnTerm(term.trim())
                .stream()
                .map(CPUCoolerDTO::initDTOFromEntity)
                .toList();
    }

    private List<CPUDTO> findAllCPUsBasedOnTerm(final String term) {
        return cpuDAO.findAllCpusBasedOnTerm(term.trim())
                .stream()
                .map(CPUDTO::initDTOFromEntity)
                .toList();
    }

    private List<GPUDTO> findAllGPUsBasedOnTerm(final String term) {
        return gpuDAO.findAllGpusBasedOnTerm(term.trim())
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
        return mouseDAO.findAllMiceBasedOnTerm(term.trim())
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
            minPrice = monitorDAO.getMinPriceFromAllProducts();
        }
        if (maxPrice == null) {
            maxPrice = monitorDAO.getMaxPriceFromAllProducts();
        }
        return monitorDAO.filterAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice)
                .stream()
                .map(MonitorDTO::initDTOFromEntity)
                .toList();
    }

    private List<CaseDTO> findAllCasesByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        if (minPrice == null) {
            minPrice = caseDAO.getMinPriceFromAllProducts();
        }
        if (maxPrice == null) {
            maxPrice = caseDAO.getMaxPriceFromAllProducts();
        }
        return caseDAO.filterAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice)
                .stream()
                .map(CaseDTO::initDTOFromEntity)
                .toList();
    }

    private List<CoolerDTO> findAllCoolersByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        if (minPrice == null) {
            minPrice = coolerDAO.getMinPriceFromAllProducts();
        }
        if (maxPrice == null) {
            maxPrice = coolerDAO.getMaxPriceFromAllProducts();
        }
        return coolerDAO.filterAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice)
                .stream()
                .map(CoolerDTO::initDTOFromEntity)
                .toList();
    }

    private List<CPUDTO> findAllCPUsByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        if (minPrice == null) {
            minPrice = cpuDAO.getMinPriceFromAllProducts();
        }
        if (maxPrice == null) {
            maxPrice = cpuDAO.getMaxPriceFromAllProducts();
        }
        return cpuDAO.filterAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice)
                .stream()
                .map(CPUDTO::initDTOFromEntity)
                .toList();
    }

    private List<CPUCoolerDTO> findAllCPUCoolersByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        if (minPrice == null) {
            minPrice = cpuCoolerDAO.getMinPriceFromAllProducts();
        }
        if (maxPrice == null) {
            maxPrice = cpuCoolerDAO.getMaxPriceFromAllProducts();
        }
        return cpuCoolerDAO.filterAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice)
                .stream()
                .map(CPUCoolerDTO::initDTOFromEntity)
                .toList();
    }

    private List<GPUDTO> findAllGPUsByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        if (minPrice == null) {
            minPrice = gpuDAO.getMinPriceFromAllProducts();
        }
        if (maxPrice == null) {
            maxPrice = gpuDAO.getMaxPriceFromAllProducts();
        }
        return gpuDAO.filterAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice)
                .stream()
                .map(GPUDTO::initDTOFromEntity)
                .toList();
    }

    private List<InternalHardDriveDTO> findAllInternalHardDrivesByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        if (minPrice == null) {
            minPrice = internalHardDriveDAO.getMinPriceFromAllProducts();
        }
        if (maxPrice == null) {
            maxPrice = internalHardDriveDAO.getMaxPriceFromAllProducts();
        }
        return internalHardDriveDAO.filterAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice)
                .stream()
                .map(InternalHardDriveDTO::initDTOFromEntity)
                .toList();
    }

    private List<MotherboardDTO> findAllMotherboardsByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        if (minPrice == null) {
            minPrice = motherboardDAO.getMinPriceFromAllProducts();
        }
        if (maxPrice == null) {
            maxPrice = motherboardDAO.getMaxPriceFromAllProducts();
        }
        return motherboardDAO.filterAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice)
                .stream()
                .map(MotherboardDTO::initDTOFromEntity)
                .toList();
    }

    private List<PowerSupplyDTO> findAllPowerSuppliesByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        if (minPrice == null) {
            minPrice = powerSupplyDAO.getMinPriceFromAllProducts();
        }
        if (maxPrice == null) {
            maxPrice = powerSupplyDAO.getMaxPriceFromAllProducts();
        }
        return powerSupplyDAO.filterAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice)
                .stream()
                .map(PowerSupplyDTO::initDTOFromEntity)
                .toList();
    }

    private List<RAMDTO> findAllRamsByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        if (minPrice == null) {
            minPrice = ramDAO.getMinPriceFromAllProducts();
        }
        if (maxPrice == null) {
            maxPrice = ramDAO.getMaxPriceFromAllProducts();
        }
        return ramDAO.filterAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice)
                .stream()
                .map(RAMDTO::initDTOFromEntity)
                .toList();
    }

    private List<ExternalHardDriveDTO> findAllExternalHardDrivesByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        if (minPrice == null) {
            minPrice = externalHardDriveDAO.getMinPriceFromAllProducts();
        }
        if (maxPrice == null) {
            maxPrice = externalHardDriveDAO.getMaxPriceFromAllProducts();
        }
        return externalHardDriveDAO.filterAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice)
                .stream()
                .map(ExternalHardDriveDTO::initDTOFromEntity)
                .toList();
    }

    private List<HeadsetDTO> findAllHeadsetsByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        if (minPrice == null) {
            minPrice = headsetDAO.getMinPriceFromAllProducts();
        }
        if (maxPrice == null) {
            maxPrice = headsetDAO.getMaxPriceFromAllProducts();
        }
        return headsetDAO.filterAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice)
                .stream()
                .map(HeadsetDTO::initDTOFromEntity)
                .toList();
    }

    private List<KeyboardDTO> findAllKeyboardsByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        if (minPrice == null) {
            minPrice = keyboardDAO.getMinPriceFromAllProducts();
        }
        if (maxPrice == null) {
            maxPrice = keyboardDAO.getMaxPriceFromAllProducts();
        }
        return keyboardDAO.filterAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice)
                .stream()
                .map(KeyboardDTO::initDTOFromEntity)
                .toList();
    }

    private List<MouseDTO> findAllMousesByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        if (minPrice == null) {
            minPrice = mouseDAO.getMinPriceFromAllProducts();
        }
        if (maxPrice == null) {
            maxPrice = mouseDAO.getMaxPriceFromAllProducts();
        }
        return mouseDAO.filterAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice)
                .stream()
                .map(MouseDTO::initDTOFromEntity)
                .toList();
    }

    private List<SpeakerDTO> findAllSpeakersByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        if (minPrice == null) {
            minPrice = speakerDAO.getMinPriceFromAllProducts();
        }
        if (maxPrice == null) {
            maxPrice = speakerDAO.getMaxPriceFromAllProducts();
        }
        return speakerDAO.filterAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice)
                .stream()
                .map(SpeakerDTO::initDTOFromEntity)
                .toList();
    }
}