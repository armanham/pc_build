package com.bdg.pc_build.filter.resolver;

import com.bdg.pc_build.filter.model.dto.main.*;
import com.bdg.pc_build.filter.model.dto.peripheral.*;
import com.bdg.pc_build.filter.model.filter_fileds_holder.main.*;
import com.bdg.pc_build.filter.model.filter_fileds_holder.peripheral.*;
import com.bdg.pc_build.product.model.entity.Product;
import com.bdg.pc_build.product.repository.ProductDAO;
import com.bdg.pc_build.product.repository.main.*;
import com.bdg.pc_build.product.repository.peripheral.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FilterFieldsValueResolver {

    private final CaseDAO caseDAO;
    private final CoolerDAO coolerDAO;
    private final CPUCoolerDAO cpuCoolerDAO;
    private final CPUDAO cpuDAO;
    private final GPUDAO gpuDAO;
    private final InternalHardDriveDAO internalHardDriveDAO;
    private final MotherboardDAO motherboardDAO;
    private final PowerSupplyDAO powerSupplyDAO;
    private final RAMDAO ramDAO;
    private final ExternalHardDriveDAO externalHardDriveDAO;
    private final MonitorDAO monitorDAO;
    private final HeadsetDAO headsetDAO;
    private final KeyboardDAO keyboardDAO;
    private final MouseDAO mouseDAO;
    private final SpeakerDAO speakerDAO;


    public CaseFilterFieldsHolderBasedOnFilterDTO resolveAndGetCaseFieldsValuesFromFilterDTO(final CaseFilterDTO filterDTO) {
        MinMaxPriceValuesHolder minMaxPriceValuesHolder = resolveAndGetMinMaxPriceValues(filterDTO.getMinPrice(), filterDTO.getMaxPrice(), caseDAO);

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

        return new CaseFilterFieldsHolderBasedOnFilterDTO(
                filterDTO.getName(),
                minMaxPriceValuesHolder.minPrice(), minMaxPriceValuesHolder.maxPrice(),
                minCpuCoolerHeight, maxCpuCoolerHeight,
                minGpuLength, maxGpuLength,
                minPreInstalledFans, maxPreInstalledFans,
                filterDTO.getTowerTypes()
        );
    }

    public CoolerFilterFieldsHolderBasedOnFilterDTO resolveAndGetCoolerFieldsValuesFromFilterDTO(final CoolerFilterDTO filterDTO) {
        MinMaxPriceValuesHolder minMaxPriceValuesHolder = resolveAndGetMinMaxPriceValues(filterDTO.getMinPrice(), filterDTO.getMaxPrice(), coolerDAO);

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

        return new CoolerFilterFieldsHolderBasedOnFilterDTO(
                filterDTO.getName(),
                minMaxPriceValuesHolder.minPrice(), minMaxPriceValuesHolder.maxPrice(),
                minTdp, maxTdp
        );
    }

    public CpuCoolerFilterFieldsHolderBasedOnFilterDTO resolveAndGetCpuCoolerFieldsValuesFromFilterDTO(final CPUCoolerFilterDTO filterDTO) {
        MinMaxPriceValuesHolder minMaxPriceValuesHolder = resolveAndGetMinMaxPriceValues(filterDTO.getMinPrice(), filterDTO.getMaxPrice(), cpuCoolerDAO);

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

        return new CpuCoolerFilterFieldsHolderBasedOnFilterDTO(
                filterDTO.getName(),
                minMaxPriceValuesHolder.minPrice(), minMaxPriceValuesHolder.maxPrice(),
                minFanRpm, maxFanRpm,
                minTdp, maxTdp,
                filterDTO.getSocketTypes()
        );
    }

    public CpuFilterFieldsHolderBasedOnFilterDTO resolveAndGetCpuFieldsValuesFromFilterDTO(final CPUFilterDTO filterDTO) {
        MinMaxPriceValuesHolder minMaxPriceValuesHolder = resolveAndGetMinMaxPriceValues(filterDTO.getMinPrice(), filterDTO.getMaxPrice(), cpuDAO);

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

        return new CpuFilterFieldsHolderBasedOnFilterDTO(
                filterDTO.getName(),
                minMaxPriceValuesHolder.minPrice(), minMaxPriceValuesHolder.maxPrice(),
                minCoreCount, maxCoreCount,
                minCoreClock, maxCoreClock,
                minBoostClock, maxBoostClock,
                minTdp, maxTdp,
                filterDTO.getIntegratedGraphics(),
                filterDTO.getSocketTypes()
        );
    }

    public GpuFilterFieldsHolderBasedOnFilterDTO resolveAndGetGpuFieldsValuesFromFilterDTO(final GPUFilterDTO filterDTO) {
        MinMaxPriceValuesHolder minMaxPriceValuesHolder = resolveAndGetMinMaxPriceValues(filterDTO.getMinPrice(), filterDTO.getMaxPrice(), gpuDAO);

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

        return new GpuFilterFieldsHolderBasedOnFilterDTO(
                filterDTO.getName(),
                minMaxPriceValuesHolder.minPrice(), minMaxPriceValuesHolder.maxPrice(),
                minMemory, maxMemory,
                minCoreClock, maxCoreClock,
                minBoostClock, maxBoostClock,
                minLength, maxLength,
                minTdp, maxTdp,
                filterDTO.getGpuInterfaceTypes()
        );
    }

    public InternalHardDriveFilterFieldsHolderBasedOnFilterDTO resolveAndGetInternalHardDriveFieldsValuesFromFilterDTO(final InternalHardDriveFilterDTO filterDTO) {
        MinMaxPriceValuesHolder minMaxPriceValuesHolder = resolveAndGetMinMaxPriceValues(filterDTO.getMinPrice(), filterDTO.getMaxPrice(), internalHardDriveDAO);

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

        return new InternalHardDriveFilterFieldsHolderBasedOnFilterDTO(
                filterDTO.getName(),
                minMaxPriceValuesHolder.minPrice(), minMaxPriceValuesHolder.maxPrice(),
                minCapacity, maxCapacity,
                minTdp, maxTdp,
                filterDTO.getInternalHardDriveInterfaceTypes()
        );
    }

    public MotherboardFilterFieldsHolderBasedOnFilterDTO resolveAndGetMotherboardFieldsValuesFromFilterDTO(final MotherboardFilterDTO filterDTO) {
        MinMaxPriceValuesHolder minMaxPriceValuesHolder = resolveAndGetMinMaxPriceValues(filterDTO.getMinPrice(), filterDTO.getMaxPrice(), motherboardDAO);

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

        return new MotherboardFilterFieldsHolderBasedOnFilterDTO(
                filterDTO.getName(),
                minMaxPriceValuesHolder.minPrice(), minMaxPriceValuesHolder.maxPrice(),
                minMemory, maxMemory,
                minMemorySlots, maxMemorySlots,
                minTdp, maxTdp,
                filterDTO.getIsM2(),
                filterDTO.getDdrTypes(),
                filterDTO.getGpuInterfaceTypes(),
                filterDTO.getSocketTypes(),
                filterDTO.getAtxTypes()
        );
    }

    public PowerSupplyFilterFieldsHolderBasedOnFilterDTO resolveAndGetPowerSupplyFieldsValuesFromFilterDTO(final PowerSupplyFilterDTO filterDTO) {
        MinMaxPriceValuesHolder minMaxPriceValuesHolder = resolveAndGetMinMaxPriceValues(filterDTO.getMinPrice(), filterDTO.getMaxPrice(), powerSupplyDAO);

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

        return new PowerSupplyFilterFieldsHolderBasedOnFilterDTO(
                filterDTO.getName(),
                minMaxPriceValuesHolder.minPrice(), minMaxPriceValuesHolder.maxPrice(),
                minWattage, maxWattage,
                minTdp, maxTdp,
                filterDTO.getModularTypes(),
                filterDTO.getEfficiencyRatings()
        );
    }

    public RamFilterFieldsHolderBasedOnFilterDTO resolveAndGetRamFieldsValuesFromFilterDTO(final RAMFilterDTO filterDTO) {
        MinMaxPriceValuesHolder minMaxPriceValuesHolder = resolveAndGetMinMaxPriceValues(filterDTO.getMinPrice(), filterDTO.getMaxPrice(), ramDAO);

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

        Integer minGbOfRam;
        Integer maxGbOfRam;
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

        return new RamFilterFieldsHolderBasedOnFilterDTO(
                filterDTO.getName(),
                minMaxPriceValuesHolder.minPrice(), minMaxPriceValuesHolder.maxPrice(),
                minCountOfRam, maxCountOfRam,
                minGbOfRam, maxGbOfRam,
                minTdp, maxTdp,
                filterDTO.getDdrTypes()
        );
    }

    public ExternalHardDriveFilterFieldsHolderBasedOnFilterDTO resolveAndGetExternalHardDriveFieldsValuesFromFilterDTO(final ExternalHardDriveFilterDTO filterDTO) {
        MinMaxPriceValuesHolder minMaxPriceValuesHolder = resolveAndGetMinMaxPriceValues(filterDTO.getMinPrice(), filterDTO.getMaxPrice(), externalHardDriveDAO);

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

        return new ExternalHardDriveFilterFieldsHolderBasedOnFilterDTO(
                filterDTO.getName(),
                minMaxPriceValuesHolder.minPrice(), minMaxPriceValuesHolder.maxPrice(),
                minCapacity, maxCapacity,
                minTdp, maxTdp
        );
    }

    public HeadsetFilterFieldsHolderBasedOnFilterDTO resolveAndGetHeadsetFieldsValuesFromFilterDTO(final HeadsetFilterDTO filterDTO) {
        MinMaxPriceValuesHolder minMaxPriceValuesHolder = resolveAndGetMinMaxPriceValues(filterDTO.getMinPrice(), filterDTO.getMaxPrice(), headsetDAO);

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

        return new HeadsetFilterFieldsHolderBasedOnFilterDTO(
                filterDTO.getName(),
                minMaxPriceValuesHolder.minPrice(), minMaxPriceValuesHolder.maxPrice(),
                minFrequency, maxFrequency,
                minCableLength, maxCableLength,
                filterDTO.getConnectivityTypes()
        );
    }

    public KeyboardFilterFieldsHolderBasedOnFilterDTO resolveAndGetKeyboardFieldsValuesFromFilterDTO(final KeyboardFilterDTO filterDTO) {
        MinMaxPriceValuesHolder minMaxPriceValuesHolder = resolveAndGetMinMaxPriceValues(filterDTO.getMinPrice(), filterDTO.getMaxPrice(), keyboardDAO);

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

        return new KeyboardFilterFieldsHolderBasedOnFilterDTO(
                filterDTO.getName(),
                minMaxPriceValuesHolder.minPrice(), minMaxPriceValuesHolder.maxPrice(),
                minCableLength, maxCableLength,
                minWeight, maxWeight,
                filterDTO.getDimensions(),
                filterDTO.getConnectivityTypes()
        );
    }

    public MonitorFilterFieldsHolderBasedOnFilterDTO resolveAndGetMonitorFieldsValuesFromFilterDTO(final MonitorFilterDTO filterDTO) {
        MinMaxPriceValuesHolder minMaxPriceValuesHolder = resolveAndGetMinMaxPriceValues(filterDTO.getMinPrice(), filterDTO.getMaxPrice(), monitorDAO);

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

        return new MonitorFilterFieldsHolderBasedOnFilterDTO(
                filterDTO.getName(),
                minMaxPriceValuesHolder.minPrice(), minMaxPriceValuesHolder.maxPrice(),
                minScreenSize, maxScreenSize,
                minRefreshRate, maxRefreshRate,
                filterDTO.getScreenTypes()
        );
    }

    public MouseFilterFieldsHolderBasedOnFilterDTO resolveAndGetMouseFieldsValuesFromFilterDTO(final MouseFilterDTO filterDTO) {
        MinMaxPriceValuesHolder minMaxPriceValuesHolder = resolveAndGetMinMaxPriceValues(filterDTO.getMinPrice(), filterDTO.getMaxPrice(), mouseDAO);

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

        return new MouseFilterFieldsHolderBasedOnFilterDTO(
                filterDTO.getName(),
                minMaxPriceValuesHolder.minPrice(), minMaxPriceValuesHolder.maxPrice(),
                minResolution, maxResolution,
                minCableLength, maxCableLength,
                minWeight, maxWeight,
                filterDTO.getConnectivityTypes()
        );
    }

    public SpeakerFilterFieldsHolderBasedOnFilterDTO resolveAndGetSpeakerFieldsValuesFromFilterDTO(final SpeakerFilterDTO filterDTO) {
        MinMaxPriceValuesHolder minMaxPriceValuesHolder = resolveAndGetMinMaxPriceValues(filterDTO.getMinPrice(), filterDTO.getMaxPrice(), speakerDAO);

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

        return new SpeakerFilterFieldsHolderBasedOnFilterDTO(
                filterDTO.getName(),
                minMaxPriceValuesHolder.minPrice(), minMaxPriceValuesHolder.maxPrice(),
                minFrequency, maxFrequency,
                minCableLength, maxCableLength,
                filterDTO.getDimensions(),
                filterDTO.getPowerSourceTypes()
        );
    }

    private <ENTITY extends Product> MinMaxPriceValuesHolder resolveAndGetMinMaxPriceValues(
            Double minPrice, Double maxPrice, final ProductDAO<ENTITY> dao
    ) {
        if (minPrice == null) {
            minPrice = dao.getMinPriceFromAllProducts();
        }
        if (maxPrice == null) {
            maxPrice = dao.getMaxPriceFromAllProducts();
        }
        return new MinMaxPriceValuesHolder(minPrice, maxPrice);
    }

    private record MinMaxPriceValuesHolder(Double minPrice, Double maxPrice) {
    }
}