package com.bdg.pc_build.filter.service;

import com.bdg.pc_build.filter.model.dto.ProductFilterDTO;
import com.bdg.pc_build.filter.model.dto.main_component.*;
import com.bdg.pc_build.filter.model.dto.peripheral.*;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.dto.main_component.*;
import com.bdg.pc_build.product.model.dto.peripheral.*;
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
        Double minPrice;
        Double maxPrice;
        if (filterDTO.getMinPrice() != null) {
            minPrice = filterDTO.getMinPrice();
        } else {
            minPrice = caseDAO.getMinPriceFromAllProducts();
        }
        if (filterDTO.getMaxPrice() != null) {
            maxPrice = filterDTO.getMaxPrice();
        } else {
            maxPrice = caseDAO.getMaxPriceFromAllProducts();
        }

        Double minCpuCoolerHeight;
        Double maxCpuCoolerHeight;
        if (filterDTO.getMinCpuCoolerHeight() != null) {
            minCpuCoolerHeight = filterDTO.getMinCpuCoolerHeight();
        } else {
            minCpuCoolerHeight = caseDAO.getMinCpuCoolerHeightOfCases();
        }
        if (filterDTO.getMaxCpuCoolerHeight() != null) {
            maxCpuCoolerHeight = filterDTO.getMaxCpuCoolerHeight();
        } else {
            maxCpuCoolerHeight = caseDAO.getMaxCpuCoolerHeightOfCases();
        }

        Double minGpuLength;
        Double maxGpuLength;
        if (filterDTO.getMinGpuLength() != null) {
            minGpuLength = filterDTO.getMinGpuLength();
        } else {
            minGpuLength = caseDAO.getMinGpuLengthOfCases();
        }
        if (filterDTO.getMaxGpuLength() != null) {
            maxGpuLength = filterDTO.getMaxGpuLength();
        } else {
            maxGpuLength = caseDAO.getMaxGpuLengthOfCases();
        }

        Integer minPreInstalledFans;
        Integer maxPreInstalledFans;
        if (filterDTO.getMinPreInstalledFans() != null) {
            minPreInstalledFans = filterDTO.getMinPreInstalledFans();
        } else {
            minPreInstalledFans = caseDAO.getMinPreInstalledFansOfCases();
        }
        if (filterDTO.getMaxPreInstalledFans() != null) {
            maxPreInstalledFans = filterDTO.getMaxPreInstalledFans();
        } else {
            maxPreInstalledFans = caseDAO.getMaxPreInstalledFansOfCases();
        }

        return caseDAO.filterAllCasesBasedOnSpecification(
                        filterDTO.getName(),
                        minPrice, maxPrice,
                        minCpuCoolerHeight, maxCpuCoolerHeight,
                        minGpuLength, maxGpuLength,
                        minPreInstalledFans, maxPreInstalledFans,
                        filterDTO.getTowerTypes()
                )
                .stream()
                .map(CaseDTO::initDTOFromEntity)
                .toList();
    }

    public List<CoolerDTO> filterAllCoolersBasedOnSpecification(final CoolerFilterDTO filterDTO) {
        Double minPrice;
        Double maxPrice;
        if (filterDTO.getMinPrice() != null) {
            minPrice = filterDTO.getMinPrice();
        } else {
            minPrice = coolerDAO.getMinPriceFromAllProducts();
        }
        if (filterDTO.getMaxPrice() != null) {
            maxPrice = filterDTO.getMaxPrice();
        } else {
            maxPrice = coolerDAO.getMaxPriceFromAllProducts();
        }

        Integer minTdp;
        Integer maxTdp;
        if (filterDTO.getMinTdp() != null) {
            minTdp = filterDTO.getMinTdp();
        } else {
            minTdp = coolerDAO.getMinTdpOfCoolers();
        }
        if (filterDTO.getMaxTdp() != null) {
            maxTdp = filterDTO.getMaxTdp();
        } else {
            maxTdp = coolerDAO.getMaxTdpOfCoolers();
        }

        return coolerDAO.filterAllCoolersBasedOnSpecification(
                        filterDTO.getName(),
                        minPrice, maxPrice,
                        minTdp, maxTdp
                )
                .stream()
                .map(CoolerDTO::initDTOFromEntity)
                .toList();
    }

    public List<CPUCoolerDTO> filterAllCpuCoolersBasedOnSpecification(final CPUCoolerFilterDTO filterDTO) {
        Double minPrice;
        Double maxPrice;
        if (filterDTO.getMinPrice() != null) {
            minPrice = filterDTO.getMinPrice();
        } else {
            minPrice = cpuCoolerDAO.getMinPriceFromAllProducts();
        }
        if (filterDTO.getMaxPrice() != null) {
            maxPrice = filterDTO.getMaxPrice();
        } else {
            maxPrice = cpuCoolerDAO.getMaxPriceFromAllProducts();
        }

        Integer minFanRpm;
        Integer maxFanRpm;
        if (filterDTO.getMinFanRpm() != null) {
            minFanRpm = filterDTO.getMinFanRpm();
        } else {
            minFanRpm = cpuCoolerDAO.getMinFanRpmOfCpuCoolers();
        }
        if (filterDTO.getMaxFanRpm() != null) {
            maxFanRpm = filterDTO.getMaxFanRpm();
        } else {
            maxFanRpm = cpuCoolerDAO.getMaxFanRpmOfCpuCoolers();
        }

        Integer minTdp;
        Integer maxTdp;
        if (filterDTO.getMinTdp() != null) {
            minTdp = filterDTO.getMinTdp();
        } else {
            minTdp = cpuCoolerDAO.getMinTdpOfCpuCoolers();
        }
        if (filterDTO.getMaxTdp() != null) {
            maxTdp = filterDTO.getMaxTdp();
        } else {
            maxTdp = cpuCoolerDAO.getMaxTdpOfCpuCoolers();
        }

        return cpuCoolerDAO.filterAllCpuCoolersBasedOnSpecification(
                        filterDTO.getName(),
                        minPrice, maxPrice,
                        minFanRpm, maxFanRpm,
                        minTdp, maxTdp,
                        filterDTO.getSocketTypes()
                )
                .stream()
                .map(CPUCoolerDTO::initDTOFromEntity)
                .toList();
    }

    public List<CPUDTO> filterAllCpusBasedOnSpecification(final CPUFilterDTO filterDTO) {
        Double minPrice;
        Double maxPrice;
        if (filterDTO.getMinPrice() != null) {
            minPrice = filterDTO.getMinPrice();
        } else {
            minPrice = cpuDAO.getMinPriceFromAllProducts();
        }
        if (filterDTO.getMaxPrice() != null) {
            maxPrice = filterDTO.getMaxPrice();
        } else {
            maxPrice = cpuDAO.getMaxPriceFromAllProducts();
        }

        Integer minCoreCount;
        Integer maxCoreCount;
        if (filterDTO.getMinCoreCount() != null) {
            minCoreCount = filterDTO.getMinCoreCount();
        } else {
            minCoreCount = cpuDAO.getMinCoreCountOfCpus();
        }
        if (filterDTO.getMaxCoreCount() != null) {
            maxCoreCount = filterDTO.getMaxCoreCount();
        } else {
            maxCoreCount = cpuDAO.getMaxCoreCountOfCpus();
        }

        Double minCoreClock;
        Double maxCoreClock;
        if (filterDTO.getMinCoreClock() != null) {
            minCoreClock = filterDTO.getMinCoreClock();
        } else {
            minCoreClock = cpuDAO.getMinCoreClockOfCpus();
        }
        if (filterDTO.getMaxCoreClock() != null) {
            maxCoreClock = filterDTO.getMaxCoreClock();
        } else {
            maxCoreClock = cpuDAO.getMaxCoreClockOfCpus();
        }

        Double minBoostClock;
        Double maxBoostClock;
        if (filterDTO.getMinBoostClock() != null) {
            minBoostClock = filterDTO.getMinBoostClock();
        } else {
            minBoostClock = cpuDAO.getMinBoostClockOfCpus();
        }
        if (filterDTO.getMaxBoostClock() != null) {
            maxBoostClock = filterDTO.getMaxBoostClock();
        } else {
            maxBoostClock = cpuDAO.getMaxBoostClockOfCpus();
        }

        Integer minTdp;
        Integer maxTdp;
        if (filterDTO.getMinTdp() != null) {
            minTdp = filterDTO.getMinTdp();
        } else {
            minTdp = cpuDAO.getMinTdpOfCpus();
        }
        if (filterDTO.getMaxTdp() != null) {
            maxTdp = filterDTO.getMaxTdp();
        } else {
            maxTdp = cpuDAO.getMaxTdpOfCpus();
        }

        return cpuDAO.filterAllCpusBasedOnSpecification(
                        filterDTO.getName(),
                        minPrice, maxPrice,
                        minCoreCount, maxCoreCount,
                        minCoreClock, maxCoreClock,
                        minBoostClock, maxBoostClock,
                        minTdp, maxTdp,
                        filterDTO.getIntegratedGraphics(),
                        filterDTO.getSocketTypes()
                )
                .stream()
                .map(CPUDTO::initDTOFromEntity)
                .toList();
    }

    public List<GPUDTO> filterAllGpusBasedOnSpecification(final GPUFilterDTO filterDTO) {
        Double minPrice;
        Double maxPrice;
        if (filterDTO.getMinPrice() != null) {
            minPrice = filterDTO.getMinPrice();
        } else {
            minPrice = gpuDAO.getMinPriceFromAllProducts();
        }
        if (filterDTO.getMaxPrice() != null) {
            maxPrice = filterDTO.getMaxPrice();
        } else {
            maxPrice = gpuDAO.getMaxPriceFromAllProducts();
        }

        Integer minMemory;
        Integer maxMemory;
        if (filterDTO.getMinMemory() != null) {
            minMemory = filterDTO.getMinMemory();
        } else {
            minMemory = gpuDAO.getMinMemoryOfGpus();
        }
        if (filterDTO.getMaxMemory() != null) {
            maxMemory = filterDTO.getMaxMemory();
        } else {
            maxMemory = gpuDAO.getMaxMemoryOfGpus();
        }

        Double minCoreClock;
        Double maxCoreClock;
        if (filterDTO.getMinCoreClock() != null) {
            minCoreClock = filterDTO.getMinCoreClock();
        } else {
            minCoreClock = gpuDAO.getMinCoreClockOfGpus();
        }
        if (filterDTO.getMaxCoreClock() != null) {
            maxCoreClock = filterDTO.getMaxCoreClock();
        } else {
            maxCoreClock = gpuDAO.getMaxCoreClockOfGpus();
        }

        Double minBoostClock;
        Double maxBoostClock;
        if (filterDTO.getMinBoostClock() != null) {
            minBoostClock = filterDTO.getMinBoostClock();
        } else {
            minBoostClock = gpuDAO.getMinBoostClockOfGpus();
        }
        if (filterDTO.getMaxBoostClock() != null) {
            maxBoostClock = filterDTO.getMaxBoostClock();
        } else {
            maxBoostClock = gpuDAO.getMaxBoostClockOfGpus();
        }

        Double minLength;
        Double maxLength;
        if (filterDTO.getMinLength() != null) {
            minLength = filterDTO.getMinLength();
        } else {
            minLength = gpuDAO.getMinLengthOfGpus();
        }
        if (filterDTO.getMaxLength() != null) {
            maxLength = filterDTO.getMaxLength();
        } else {
            maxLength = gpuDAO.getMaxLengthOfGpus();
        }

        Integer minTdp;
        Integer maxTdp;
        if (filterDTO.getMinTdp() != null) {
            minTdp = filterDTO.getMinTdp();
        } else {
            minTdp = gpuDAO.getMinTdpOfGpus();
        }
        if (filterDTO.getMaxTdp() != null) {
            maxTdp = filterDTO.getMaxTdp();
        } else {
            maxTdp = gpuDAO.getMaxTdpOfGpus();
        }

        return gpuDAO.filterAllGpusBasedOnSpecification(
                        filterDTO.getName(),
                        minPrice, maxPrice,
                        minMemory, maxMemory,
                        minCoreClock, maxCoreClock,
                        minBoostClock, maxBoostClock,
                        minLength, maxLength,
                        minTdp, maxTdp,
                        filterDTO.getGpuInterfaceTypes()
                )
                .stream()
                .map(GPUDTO::initDTOFromEntity)
                .toList();
    }

    public List<InternalHardDriveDTO> filterAllInternalHardDrivesBasedOnSpecification(final InternalHardDriveFilterDTO filterDTO) {
        Double minPrice;
        Double maxPrice;
        if (filterDTO.getMinPrice() != null) {
            minPrice = filterDTO.getMinPrice();
        } else {
            minPrice = internalHardDriveDAO.getMinPriceFromAllProducts();
        }
        if (filterDTO.getMaxPrice() != null) {
            maxPrice = filterDTO.getMaxPrice();
        } else {
            maxPrice = internalHardDriveDAO.getMaxPriceFromAllProducts();
        }

        Integer minCapacity;
        Integer maxCapacity;
        if (filterDTO.getMinCapacity() != null) {
            minCapacity = filterDTO.getMinCapacity();
        } else {
            minCapacity = internalHardDriveDAO.getMinCapacityOfInternalHardDrives();
        }
        if (filterDTO.getMaxCapacity() != null) {
            maxCapacity = filterDTO.getMaxCapacity();
        } else {
            maxCapacity = internalHardDriveDAO.getMaxCapacityOfInternalHardDrives();
        }

        Integer minTdp;
        Integer maxTdp;
        if (filterDTO.getMinTdp() != null) {
            minTdp = filterDTO.getMinTdp();
        } else {
            minTdp = internalHardDriveDAO.getMinTdpOfInternalHardDrives();
        }
        if (filterDTO.getMaxTdp() != null) {
            maxTdp = filterDTO.getMaxTdp();
        } else {
            maxTdp = internalHardDriveDAO.getMaxTdpOfInternalHardDrives();
        }

        return internalHardDriveDAO.filterAllInternalHardDrivesBasedOnSpecification(
                        filterDTO.getName(),
                        minPrice, maxPrice,
                        minCapacity, maxCapacity,
                        minTdp, maxTdp,
                        filterDTO.getInternalHardDriveInterfaceTypes()
                )
                .stream()
                .map(InternalHardDriveDTO::initDTOFromEntity)
                .toList();
    }

    public List<MotherboardDTO> filterAllMotherboardsBasedOnSpecification(final MotherboardFilterDTO filterDTO) {
        Double minPrice;
        Double maxPrice;
        if (filterDTO.getMinPrice() != null) {
            minPrice = filterDTO.getMinPrice();
        } else {
            minPrice = motherboardDAO.getMinPriceFromAllProducts();
        }
        if (filterDTO.getMaxPrice() != null) {
            maxPrice = filterDTO.getMaxPrice();
        } else {
            maxPrice = motherboardDAO.getMaxPriceFromAllProducts();
        }

        Integer minMemory;
        Integer maxMemory;
        if (filterDTO.getMinMemory() != null) {
            minMemory = filterDTO.getMinMemory();
        } else {
            minMemory = motherboardDAO.getMinMemoryOfMotherboards();
        }
        if (filterDTO.getMaxMemory() != null) {
            maxMemory = filterDTO.getMaxMemory();
        } else {
            maxMemory = motherboardDAO.getMaxMemoryOfMotherboards();
        }

        Integer minMemorySlots;
        Integer maxMemorySlots;
        if (filterDTO.getMinMemorySlots() != null) {
            minMemorySlots = filterDTO.getMinMemorySlots();
        } else {
            minMemorySlots = motherboardDAO.getMinMemorySlotsOfMotherboards();
        }
        if (filterDTO.getMaxMemorySlots() != null) {
            maxMemorySlots = filterDTO.getMaxMemorySlots();
        } else {
            maxMemorySlots = motherboardDAO.getMaxMemorySlotsOfMotherboards();
        }

        Integer minTdp;
        Integer maxTdp;
        if (filterDTO.getMinTdp() != null) {
            minTdp = filterDTO.getMinTdp();
        } else {
            minTdp = motherboardDAO.getMinTdpOfMotherboards();
        }
        if (filterDTO.getMaxTdp() != null) {
            maxTdp = filterDTO.getMaxTdp();
        } else {
            maxTdp = motherboardDAO.getMaxTdpOfMotherboards();
        }

        return motherboardDAO.filterAllMotherboardsBasedOnSpecification(
                        filterDTO.getName(),
                        minPrice, maxPrice,
                        minMemory, maxMemory,
                        minMemorySlots, maxMemorySlots,
                        minTdp, maxTdp,
                        filterDTO.getIsM2(),
                        filterDTO.getDdrTypes(),
                        filterDTO.getGpuInterfaceTypes(),
                        filterDTO.getSocketTypes(),
                        filterDTO.getAtxTypes()
                )
                .stream()
                .map(MotherboardDTO::initDTOFromEntity)
                .toList();
    }

    public List<PowerSupplyDTO> filterAllPowerSuppliesBasedOnSpecification(final PowerSupplyFilterDTO filterDTO) {
        Double minPrice;
        Double maxPrice;
        if (filterDTO.getMinPrice() != null) {
            minPrice = filterDTO.getMinPrice();
        } else {
            minPrice = powerSupplyDAO.getMinPriceFromAllProducts();
        }
        if (filterDTO.getMaxPrice() != null) {
            maxPrice = filterDTO.getMaxPrice();
        } else {
            maxPrice = powerSupplyDAO.getMaxPriceFromAllProducts();
        }

        Integer minWattage;
        Integer maxWattage;
        if (filterDTO.getMinWattage() != null) {
            minWattage = filterDTO.getMinWattage();
        } else {
            minWattage = powerSupplyDAO.getMinWattageOfPowerSupplies();
        }
        if (filterDTO.getMaxWattage() != null) {
            maxWattage = filterDTO.getMaxWattage();
        } else {
            maxWattage = powerSupplyDAO.getMaxWattageOfPowerSupplies();
        }

        Integer minTdp;
        Integer maxTdp;
        if (filterDTO.getMinTdp() != null) {
            minTdp = filterDTO.getMinTdp();
        } else {
            minTdp = powerSupplyDAO.getMinTdpOfPowerSupplies();
        }
        if (filterDTO.getMaxTdp() != null) {
            maxTdp = filterDTO.getMaxTdp();
        } else {
            maxTdp = powerSupplyDAO.getMaxTdpOfPowerSupplies();
        }

        return powerSupplyDAO.filterAllPowerSuppliesBasedOnSpecification(
                        filterDTO.getName(),
                        minPrice, maxPrice,
                        minWattage, maxWattage,
                        minTdp, maxTdp,
                        filterDTO.getIsModular(),
                        filterDTO.getEfficiencyRatings()
                )
                .stream()
                .map(PowerSupplyDTO::initDTOFromEntity)
                .toList();
    }

    public List<RAMDTO> filterAllRamsBasedOnSpecification(final RAMFilterDTO filterDTO) {
        Double minPrice;
        Double maxPrice;
        if (filterDTO.getMinPrice() != null) {
            minPrice = filterDTO.getMinPrice();
        } else {
            minPrice = ramDAO.getMinPriceFromAllProducts();
        }
        if (filterDTO.getMaxPrice() != null) {
            maxPrice = filterDTO.getMaxPrice();
        } else {
            maxPrice = ramDAO.getMaxPriceFromAllProducts();
        }

        Integer minCountOfRam;
        Integer maxCountOfRam;
        if (filterDTO.getMinCountOfRam() != null) {
            minCountOfRam = filterDTO.getMinCountOfRam();
        } else {
            minCountOfRam = ramDAO.getMinCountOfRamOfRams();
        }
        if (filterDTO.getMaxCountOfRam() != null) {
            maxCountOfRam = filterDTO.getMaxCountOfRam();
        } else {
            maxCountOfRam = ramDAO.getMaxCountOfRamOfRams();
        }

        Double minGbOfRam;
        Double maxGbOfRam;
        if (filterDTO.getMinGbOfRam() != null) {
            minGbOfRam = filterDTO.getMinGbOfRam();
        } else {
            minGbOfRam = ramDAO.getMinGbOfRamOfRams();
        }
        if (filterDTO.getMaxGbOfRam() != null) {
            maxGbOfRam = filterDTO.getMaxGbOfRam();
        } else {
            maxGbOfRam = ramDAO.getMaxGbOfRamOfRams();
        }

        Integer minTdp;
        Integer maxTdp;
        if (filterDTO.getMinTdp() != null) {
            minTdp = filterDTO.getMinTdp();
        } else {
            minTdp = ramDAO.getMinTdpOfRams();
        }
        if (filterDTO.getMaxTdp() != null) {
            maxTdp = filterDTO.getMaxTdp();
        } else {
            maxTdp = ramDAO.getMaxTdpOfRams();
        }

        return ramDAO.filterAllRamsBasedOnSpecification(
                        filterDTO.getName(),
                        minPrice, maxPrice,
                        minCountOfRam, maxCountOfRam,
                        minGbOfRam, maxGbOfRam,
                        minTdp, maxTdp,
                        filterDTO.getDdrTypes()
                )
                .stream()
                .map(RAMDTO::initDTOFromEntity)
                .toList();
    }

    public List<ExternalHardDriveDTO> filterAllExternalHardDrivesBasedOnSpecification(final ExternalHardDriveFilterDTO filterDTO) {
        Double minPrice;
        Double maxPrice;
        if (filterDTO.getMinPrice() != null) {
            minPrice = filterDTO.getMinPrice();
        } else {
            minPrice = externalHardDriveDAO.getMinPriceFromAllProducts();
        }
        if (filterDTO.getMaxPrice() != null) {
            maxPrice = filterDTO.getMaxPrice();
        } else {
            maxPrice = externalHardDriveDAO.getMaxPriceFromAllProducts();
        }

        Integer minCapacity;
        Integer maxCapacity;
        if (filterDTO.getMinCapacity() != null) {
            minCapacity = filterDTO.getMinCapacity();
        } else {
            minCapacity = externalHardDriveDAO.getMinCapacityOfExternalHardDrives();
        }
        if (filterDTO.getMaxCapacity() != null) {
            maxCapacity = filterDTO.getMaxCapacity();
        } else {
            maxCapacity = externalHardDriveDAO.getMaxCapacityOfExternalHardDrives();
        }

        Integer minTdp;
        Integer maxTdp;
        if (filterDTO.getMinTdp() != null) {
            minTdp = filterDTO.getMinTdp();
        } else {
            minTdp = externalHardDriveDAO.getMinTdpOfExternalHardDrives();
        }
        if (filterDTO.getMaxTdp() != null) {
            maxTdp = filterDTO.getMaxTdp();
        } else {
            maxTdp = externalHardDriveDAO.getMaxTdpOfExternalHardDrives();
        }

        return externalHardDriveDAO.filterAllExternalHardDrivesBasedOnSpecification(
                        filterDTO.getName(),
                        minPrice, maxPrice,
                        minCapacity, maxCapacity,
                        minTdp, maxTdp
                )
                .stream()
                .map(ExternalHardDriveDTO::initDTOFromEntity)
                .toList();
    }

    public List<HeadsetDTO> filterAllHeadsetsBasedOnSpecification(final HeadsetFilterDTO filterDTO) {
        Double minPrice;
        Double maxPrice;
        if (filterDTO.getMinPrice() != null) {
            minPrice = filterDTO.getMinPrice();
        } else {
            minPrice = headsetDAO.getMinPriceFromAllProducts();
        }
        if (filterDTO.getMaxPrice() != null) {
            maxPrice = filterDTO.getMaxPrice();
        } else {
            maxPrice = headsetDAO.getMaxPriceFromAllProducts();
        }

        Integer minFrequency;
        Integer maxFrequency;
        if (filterDTO.getMinFrequency() != null) {
            minFrequency = filterDTO.getMinFrequency();
        } else {
            minFrequency = headsetDAO.getMinFrequencyOfHeadset();
        }
        if (filterDTO.getMaxFrequency() != null) {
            maxFrequency = filterDTO.getMaxFrequency();
        } else {
            maxFrequency = headsetDAO.getMaxFrequencyOfHeadset();
        }

        Double minCableLength;
        Double maxCableLength;
        if (filterDTO.getMinCableLength() != null) {
            minCableLength = filterDTO.getMinCableLength();
        } else {
            minCableLength = headsetDAO.getMinCableLengthOfHeadset();
        }
        if (filterDTO.getMaxCableLength() != null) {
            maxCableLength = filterDTO.getMaxCableLength();
        } else {
            maxCableLength = headsetDAO.getMaxCableLengthOfHeadset();
        }

        return headsetDAO.filterAllHeadsetsBasedOnSpecification(
                        filterDTO.getName(),
                        minPrice, maxPrice,
                        minFrequency, maxFrequency,
                        minCableLength, maxCableLength,
                        filterDTO.getConnectivityTypes()
                )
                .stream()
                .map(HeadsetDTO::initDTOFromEntity)
                .toList();
    }

    public List<KeyboardDTO> filterAllKeyboardsBasedOnSpecification(final KeyboardFilterDTO filterDTO) {
        Double minPrice;
        Double maxPrice;
        if (filterDTO.getMinPrice() != null) {
            minPrice = filterDTO.getMinPrice();
        } else {
            minPrice = keyboardDAO.getMinPriceFromAllProducts();
        }
        if (filterDTO.getMaxPrice() != null) {
            maxPrice = filterDTO.getMaxPrice();
        } else {
            maxPrice = keyboardDAO.getMaxPriceFromAllProducts();
        }

        Double minCableLength;
        Double maxCableLength;
        if (filterDTO.getMinCableLength() != null) {
            minCableLength = filterDTO.getMinCableLength();
        } else {
            minCableLength = keyboardDAO.getMinCableLengthOfKeyboard();
        }
        if (filterDTO.getMaxCableLength() != null) {
            maxCableLength = filterDTO.getMaxCableLength();
        } else {
            maxCableLength = keyboardDAO.getMaxCableLengthOfKeyboard();
        }

        Double minWeight;
        Double maxWeight;
        if (filterDTO.getMinWeight() != null) {
            minWeight = filterDTO.getMinWeight();
        } else {
            minWeight = keyboardDAO.getMinWeightOfKeyboard();
        }
        if (filterDTO.getMaxWeight() != null) {
            maxWeight = filterDTO.getMaxWeight();
        } else {
            maxWeight = keyboardDAO.getMaxWeightOfKeyboard();
        }

        return keyboardDAO.filterAllKeyboardsBasedOnSpecification(
                        filterDTO.getName(),
                        minPrice, maxPrice,
                        minCableLength, maxCableLength,
                        minWeight, maxWeight,
                        filterDTO.getDimensions(),
                        filterDTO.getConnectivityTypes()
                )
                .stream()
                .map(KeyboardDTO::initDTOFromEntity)
                .toList();
    }


    @Override
    public List<MonitorDTO> filterAllMonitorsBasedOnSpecification(final MonitorFilterDTO filterDTO) {
        Double minPrice;
        Double maxPrice;
        if (filterDTO.getMinPrice() != null) {
            minPrice = filterDTO.getMinPrice();
        } else {
            minPrice = monitorDAO.getMinPriceFromAllProducts();
        }
        if (filterDTO.getMaxPrice() != null) {
            maxPrice = filterDTO.getMaxPrice();
        } else {
            maxPrice = monitorDAO.getMaxPriceFromAllProducts();
        }

        Double minScreenSize;
        Double maxScreenSize;
        if (filterDTO.getMinScreenSize() != null) {
            minScreenSize = filterDTO.getMinScreenSize();
        } else {
            minScreenSize = monitorDAO.getMinScreenSizeOfMonitors();
        }
        if (filterDTO.getMaxScreenSize() != null) {
            maxScreenSize = filterDTO.getMaxScreenSize();
        } else {
            maxScreenSize = monitorDAO.getMaxScreenSizeOfMonitors();
        }

        Integer minRefreshRate;
        Integer maxRefreshRate;
        if (filterDTO.getMinRefreshRate() != null) {
            minRefreshRate = filterDTO.getMinRefreshRate();
        } else {
            minRefreshRate = monitorDAO.getMinRefreshRateOfMonitors();
        }
        if (filterDTO.getMaxRefreshRate() != null) {
            maxRefreshRate = filterDTO.getMaxRefreshRate();
        } else {
            maxRefreshRate = monitorDAO.getMaxRefreshRateOfMonitors();
        }

        return monitorDAO.filterAllMonitorsBasedOnSpecification(
                        filterDTO.getName(),
                        minPrice, maxPrice,
                        minScreenSize, maxScreenSize,
                        minRefreshRate, maxRefreshRate,
                        filterDTO.getScreenTypes()
                )
                .stream()
                .map(MonitorDTO::initDTOFromEntity)
                .toList();
    }

    public List<MouseDTO> filterAllMiceBasedOnSpecification(final MouseFilterDTO filterDTO) {
        Double minPrice;
        Double maxPrice;
        if (filterDTO.getMinPrice() != null) {
            minPrice = filterDTO.getMinPrice();
        } else {
            minPrice = mouseDAO.getMinPriceFromAllProducts();
        }
        if (filterDTO.getMaxPrice() != null) {
            maxPrice = filterDTO.getMaxPrice();
        } else {
            maxPrice = mouseDAO.getMaxPriceFromAllProducts();
        }

        Integer minResolution;
        Integer maxResolution;
        if (filterDTO.getMinResolution() != null) {
            minResolution = filterDTO.getMinResolution();
        } else {
            minResolution = mouseDAO.getMinResolutionOfMice();
        }
        if (filterDTO.getMaxResolution() != null) {
            maxResolution = filterDTO.getMaxResolution();
        } else {
            maxResolution = mouseDAO.getMaxResolutionOfMice();
        }

        Double minCableLength;
        Double maxCableLength;
        if (filterDTO.getMinCableLength() != null) {
            minCableLength = filterDTO.getMinCableLength();
        } else {
            minCableLength = mouseDAO.getMinCableLengthOfMice();
        }
        if (filterDTO.getMaxCableLength() != null) {
            maxCableLength = filterDTO.getMaxCableLength();
        } else {
            maxCableLength = mouseDAO.getMaxCableLengthOfMice();
        }

        Double minWeight;
        Double maxWeight;
        if (filterDTO.getMinWeight() != null) {
            minWeight = filterDTO.getMinWeight();
        } else {
            minWeight = mouseDAO.getMinWeightOfMice();
        }
        if (filterDTO.getMaxWeight() != null) {
            maxWeight = filterDTO.getMaxWeight();
        } else {
            maxWeight = mouseDAO.getMaxWeightOfMice();
        }

        return mouseDAO.filterAllMiceBasedOnSpecification(
                        filterDTO.getName(),
                        minPrice, maxPrice,
                        minResolution, maxResolution,
                        minCableLength, maxCableLength,
                        minWeight, maxWeight,
                        filterDTO.getConnectivityTypes()
                )
                .stream()
                .map(MouseDTO::initDTOFromEntity)
                .toList();
    }

    public List<SpeakerDTO> filterAllSpeakersBasedOnSpecification(final SpeakerFilterDTO filterDTO) {
        Double minPrice;
        Double maxPrice;
        if (filterDTO.getMinPrice() != null) {
            minPrice = filterDTO.getMinPrice();
        } else {
            minPrice = speakerDAO.getMinPriceFromAllProducts();
        }
        if (filterDTO.getMaxPrice() != null) {
            maxPrice = filterDTO.getMaxPrice();
        } else {
            maxPrice = speakerDAO.getMaxPriceFromAllProducts();
        }

        Integer minFrequency;
        Integer maxFrequency;
        if (filterDTO.getMinFrequency() != null) {
            minFrequency = filterDTO.getMinFrequency();
        } else {
            minFrequency = speakerDAO.getMinFrequencyOfSpeakers();
        }
        if (filterDTO.getMaxFrequency() != null) {
            maxFrequency = filterDTO.getMaxFrequency();
        } else {
            maxFrequency = speakerDAO.getMaxFrequencyOfSpeakers();
        }

        Double minCableLength;
        Double maxCableLength;
        if (filterDTO.getMinCableLength() != null) {
            minCableLength = filterDTO.getMinCableLength();
        } else {
            minCableLength = speakerDAO.getMinCableLengthOfSpeakers();
        }
        if (filterDTO.getMaxCableLength() != null) {
            maxCableLength = filterDTO.getMaxCableLength();
        } else {
            maxCableLength = speakerDAO.getMaxCableLengthOfSpeakers();
        }

        return speakerDAO.filterAllSpeakersBasedOnSpecification(
                        filterDTO.getName(),
                        minPrice, maxPrice,
                        minFrequency, maxFrequency,
                        minCableLength, maxCableLength,
                        filterDTO.getDimensions(),
                        filterDTO.getPowerSourceTypes()
                )
                .stream()
                .map(SpeakerDTO::initDTOFromEntity)
                .toList();
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