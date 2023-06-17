package com.bdg.pc_build.filter.service;

import com.bdg.pc_build.filter.model.dto.ProductFilterDTO;
import com.bdg.pc_build.filter.model.dto.main_component.*;
import com.bdg.pc_build.filter.model.dto.peripheral.*;
import com.bdg.pc_build.filter.service.FilterFieldsValueResolver.*;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.dto.main_component.*;
import com.bdg.pc_build.product.model.dto.peripheral.*;
import com.bdg.pc_build.product.model.entity.Product;
import com.bdg.pc_build.product.model.enumerations.*;
import com.bdg.pc_build.product.repository.ProductDAO;
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

    @Override
    public List<CPUCoolerDTO> filterAllCpuCoolersBasedOnSpecification(final CPUCoolerFilterDTO filterDTO) {
        CpuCoolerFieldsHolderBasedOnFilterDTO cpuCoolerFieldsHolderBasedOnFilterDTO = filterFieldsValueResolver.resolveAndGetCpuCoolerFieldsValuesFromFilterDTO(filterDTO);

        List<CPUCoolerDTO> cpuCoolersByName = cpuCoolerDAO.findAllProductsByNameLike(cpuCoolerFieldsHolderBasedOnFilterDTO.name())
               .stream()
               .map(CPUCoolerDTO::initDTOFromEntity)
               .toList();

        List<CPUCoolerDTO> cpuCoolersByPrice = cpuCoolerDAO.findAllProductsByPriceBetween(cpuCoolerFieldsHolderBasedOnFilterDTO.minPrice(), cpuCoolerFieldsHolderBasedOnFilterDTO.maxPrice())
               .stream()
               .map(CPUCoolerDTO::initDTOFromEntity)
               .toList();

        List<CPUCoolerDTO> cpuCoolersByFamRpm = cpuCoolerDAO.findAllByFanRpmBetween(cpuCoolerFieldsHolderBasedOnFilterDTO.minFanRpm(), cpuCoolerFieldsHolderBasedOnFilterDTO.maxFanRpm())
                .stream()
                .map(CPUCoolerDTO::initDTOFromEntity)
                .toList();

        List<CPUCoolerDTO> cpuCoolersByTdp = cpuCoolerDAO.findAllByTdpBetween(cpuCoolerFieldsHolderBasedOnFilterDTO.minTdp(), cpuCoolerFieldsHolderBasedOnFilterDTO.maxTdp())
                .stream()
                .map(CPUCoolerDTO::initDTOFromEntity)
                .toList();

        List<CPUCoolerDTO> intersection = new ArrayList<>(cpuCoolersByName);

        List<List<CPUCoolerDTO>> listsToIntersect = Arrays.asList(
                cpuCoolersByName,
                cpuCoolersByPrice,
                cpuCoolersByFamRpm,
                cpuCoolersByTdp
        );

        for (List<CPUCoolerDTO> list : listsToIntersect) {
            List<CPUCoolerDTO> tempList = new ArrayList<>(list);
            intersection.retainAll(tempList);
        }

        return intersection;

    }

    @Override
    public List<CPUDTO> filterAllCpusBasedOnSpecification(final CPUFilterDTO filterDTO) {
        CpuFieldsHolderBasedOnFilterDTO cpuFieldsHolderBasedOnFilterDTO = filterFieldsValueResolver.resolveAndGetCpuFieldsValuesFromFilterDTO(filterDTO);

        List<CPUDTO> cpusByName = cpuDAO.findAllProductsByNameLike(cpuFieldsHolderBasedOnFilterDTO.name())
              .stream()
              .map(CPUDTO::initDTOFromEntity)
              .toList();

        List<CPUDTO> cpusByPrice = cpuDAO.findAllProductsByPriceBetween(cpuFieldsHolderBasedOnFilterDTO.minPrice(), cpuFieldsHolderBasedOnFilterDTO.maxPrice())
              .stream()
              .map(CPUDTO::initDTOFromEntity)
              .toList();

        List<CPUDTO> cpusByCoreCount = cpuDAO.findAllByCoreCountBetween(cpuFieldsHolderBasedOnFilterDTO.minCoreCount(), cpuFieldsHolderBasedOnFilterDTO.maxCoreCount())
                .stream()
                .map(CPUDTO::initDTOFromEntity)
                .toList();

        List<CPUDTO> cpusByCoreCLock = cpuDAO.findAllByCoreClockBetween(cpuFieldsHolderBasedOnFilterDTO.minCoreClock(), cpuFieldsHolderBasedOnFilterDTO.maxCoreClock())
                .stream()
                .map(CPUDTO::initDTOFromEntity)
                .toList();

        List<CPUDTO> cpusByBoostCLock = cpuDAO.findAllByBoostClockBetween(cpuFieldsHolderBasedOnFilterDTO.minBoostClock(), cpuFieldsHolderBasedOnFilterDTO.maxBoostClock())
                .stream()
                .map(CPUDTO::initDTOFromEntity)
                .toList();

        List<CPUDTO> cpusByTdp = cpuDAO.findAllByTdpBetween(cpuFieldsHolderBasedOnFilterDTO.minTdp(), cpuFieldsHolderBasedOnFilterDTO.maxTdp())
                .stream()
                .map(CPUDTO::initDTOFromEntity)
                .toList();

        List<CPUDTO> cpusBySocketTypeList = new ArrayList<>();
        if (cpuFieldsHolderBasedOnFilterDTO.socketTypes() != null && !cpuFieldsHolderBasedOnFilterDTO.socketTypes().isEmpty()) {
            for (SocketType socketType : filterDTO.getSocketTypes()) {
                List<CPUDTO> cpuDTOsBySocketType = cpuDAO.findAllBySocketType(socketType)
                        .stream()
                        .map(CPUDTO::initDTOFromEntity)
                        .toList();
                cpusBySocketTypeList.addAll(cpuDTOsBySocketType);
            }
        } else {
            cpusBySocketTypeList.addAll(cpuDAO.findAll()
                    .stream()
                    .map(CPUDTO::initDTOFromEntity)
                    .toList());
        }

        List<CPUDTO> cpusByInterfaceTypeList = new ArrayList<>();
        if (cpuFieldsHolderBasedOnFilterDTO.integratedGraphics() != null && !cpuFieldsHolderBasedOnFilterDTO.integratedGraphics().isEmpty()) {
            for (String integratedGraphics : filterDTO.getIntegratedGraphics()) {
                List<CPUDTO> cpuDTOsByIntegratedGraphics = cpuDAO.findAllByIntegratedGraphics(integratedGraphics)
                        .stream()
                        .map(CPUDTO::initDTOFromEntity)
                        .toList();
                cpusByInterfaceTypeList.addAll(cpuDTOsByIntegratedGraphics);
            }
        } else {
            cpusByInterfaceTypeList.addAll(cpuDAO.findAll()
                    .stream()
                    .map(CPUDTO::initDTOFromEntity)
                    .toList());
        }

        List<CPUDTO> intersection = new ArrayList<>(cpusByName);

        List<List<CPUDTO>> listsToIntersect = Arrays.asList(
                cpusByName,
                cpusByPrice,
                cpusByCoreCount,
                cpusByCoreCLock,
                cpusByBoostCLock,
                cpusByTdp,
                cpusBySocketTypeList,
                cpusByInterfaceTypeList
        );
        for (List<CPUDTO> list : listsToIntersect) {
            List<CPUDTO> tempList = new ArrayList<>(list);
            intersection.retainAll(tempList);
        }
        return intersection;

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

    @Override
    public List<InternalHardDriveDTO> filterAllInternalHardDrivesBasedOnSpecification(final InternalHardDriveFilterDTO filterDTO) {
        InternalHardDriveFieldsHolderBasedOnFilterDTO internalHardDriveFieldsHolderBasedOnFilterDTOFromFilterDTO = filterFieldsValueResolver.resolveAndGetInternalHardDriveFieldsValuesFromFilterDTO(filterDTO);

        List<InternalHardDriveDTO> internalHardDrivesByName = internalHardDriveDAO.findAllProductsByNameLike(internalHardDriveFieldsHolderBasedOnFilterDTOFromFilterDTO.name())
               .stream()
               .map(InternalHardDriveDTO::initDTOFromEntity)
               .toList();

        List<InternalHardDriveDTO> internalHardDrivesByPrice = internalHardDriveDAO.findAllProductsByPriceBetween(
                        internalHardDriveFieldsHolderBasedOnFilterDTOFromFilterDTO.minPrice(), internalHardDriveFieldsHolderBasedOnFilterDTOFromFilterDTO.maxPrice())
               .stream()
               .map(InternalHardDriveDTO::initDTOFromEntity)
               .toList();

        List<InternalHardDriveDTO> internalHardDrivesByCapacity = internalHardDriveDAO.findAllByCapacityBetween(
                        internalHardDriveFieldsHolderBasedOnFilterDTOFromFilterDTO.minCapacity(), internalHardDriveFieldsHolderBasedOnFilterDTOFromFilterDTO.maxCapacity())
                .stream()
                .map(InternalHardDriveDTO::initDTOFromEntity)
                .toList();

        List<InternalHardDriveDTO> internalHardDrivesByTdp = internalHardDriveDAO.findAllByTdpBetween(
                        internalHardDriveFieldsHolderBasedOnFilterDTOFromFilterDTO.minTdp(), internalHardDriveFieldsHolderBasedOnFilterDTOFromFilterDTO.maxTdp())
                .stream()
                .map(InternalHardDriveDTO::initDTOFromEntity)
                .toList();

        List<InternalHardDriveDTO> internalHardDriveByInternalHardDRiveInterfaceTypeList = new ArrayList<>();
        if (internalHardDriveFieldsHolderBasedOnFilterDTOFromFilterDTO.internalHardDriveInterfaceTypes() != null && !internalHardDriveFieldsHolderBasedOnFilterDTOFromFilterDTO.internalHardDriveInterfaceTypes().isEmpty()) {
            for (InternalHardDriveInterfaceType internalHardDriveInterfaceType : filterDTO.getInternalHardDriveInterfaceTypes()) {
                List<InternalHardDriveDTO> internalHardDriveDTOsByIsM2 = internalHardDriveDAO.findAllByInternalHardDriveInterfaceType(internalHardDriveInterfaceType)
                        .stream()
                        .map(InternalHardDriveDTO::initDTOFromEntity)
                        .toList();
                internalHardDriveByInternalHardDRiveInterfaceTypeList.addAll(internalHardDriveDTOsByIsM2); //
            }
        } else {
            internalHardDriveByInternalHardDRiveInterfaceTypeList.addAll(internalHardDriveDAO.findAll()
                    .stream()
                    .map(InternalHardDriveDTO::initDTOFromEntity)
                    .toList());
        }

        List<InternalHardDriveDTO> intersection = new ArrayList<>(internalHardDrivesByName);

        List<List<InternalHardDriveDTO>> listsToIntersect = Arrays.asList(
                internalHardDrivesByPrice,
                internalHardDrivesByCapacity,
                internalHardDrivesByTdp,
                internalHardDriveByInternalHardDRiveInterfaceTypeList
        );

        for (List<InternalHardDriveDTO> list : listsToIntersect) {
            List<InternalHardDriveDTO> tempList = new ArrayList<>(list);
            intersection.retainAll(tempList);
        }

        return intersection;
    }

    @Override
    public List<MotherboardDTO> filterAllMotherboardsBasedOnSpecification(final MotherboardFilterDTO filterDTO) {
        MotherboardFieldsHolderBasedOnFilterDTO motherboardFieldsHolderBasedOnFilterDTOFromFilterDTO = filterFieldsValueResolver.resolveAndGetMotherboardFieldsValuesFromFilterDTO(filterDTO);

        List<MotherboardDTO> motherboardsByName = motherboardDAO.findAllProductsByNameLike(motherboardFieldsHolderBasedOnFilterDTOFromFilterDTO.name())
              .stream()
              .map(MotherboardDTO::initDTOFromEntity)
              .toList();

        List<MotherboardDTO> motherboardsByPrice = motherboardDAO.findAllProductsByPriceBetween(
                        motherboardFieldsHolderBasedOnFilterDTOFromFilterDTO.minPrice(), motherboardFieldsHolderBasedOnFilterDTOFromFilterDTO.maxPrice())
              .stream()
              .map(MotherboardDTO::initDTOFromEntity)
              .toList();

        List<MotherboardDTO> motherboardsByMemoryMax = motherboardDAO.findAllByMemoryMaxBetween(
                        motherboardFieldsHolderBasedOnFilterDTOFromFilterDTO.minMemory(), motherboardFieldsHolderBasedOnFilterDTOFromFilterDTO.maxMemory())
                .stream()
                .map(MotherboardDTO::initDTOFromEntity)
                .toList();

        List<MotherboardDTO> motherboardsByMemorySlots = motherboardDAO.findAllByMemorySlotsBetween(
                        motherboardFieldsHolderBasedOnFilterDTOFromFilterDTO.minMemorySlots(), motherboardFieldsHolderBasedOnFilterDTOFromFilterDTO.maxMemorySlots())
                .stream()
                .map(MotherboardDTO::initDTOFromEntity)
                .toList();

        List<MotherboardDTO> motherboardsByTdp = motherboardDAO.findAllByTdpBetween(
                        motherboardFieldsHolderBasedOnFilterDTOFromFilterDTO.minTdp(), motherboardFieldsHolderBasedOnFilterDTOFromFilterDTO.maxTdp())
                .stream()
                .map(MotherboardDTO::initDTOFromEntity)
                .toList();

        List<MotherboardDTO> motherboardByIsM2List = new ArrayList<>();
        if (motherboardFieldsHolderBasedOnFilterDTOFromFilterDTO.isM2() != null && !motherboardFieldsHolderBasedOnFilterDTOFromFilterDTO.isM2().isEmpty()) {
            for (Boolean isM2 : filterDTO.getIsM2()) {
                List<MotherboardDTO> motherboardDTOsByIsM2 = motherboardDAO.findAllByIsM2(isM2)
                        .stream()
                        .map(MotherboardDTO::initDTOFromEntity)
                        .toList();
                motherboardByIsM2List.addAll(motherboardDTOsByIsM2); //
            }
        } else {
            motherboardByIsM2List.addAll(motherboardDAO.findAll()
                    .stream()
                    .map(MotherboardDTO::initDTOFromEntity)
                    .toList());
        }

        List<MotherboardDTO> motherboardByDdrTypeList = new ArrayList<>();
        if (motherboardFieldsHolderBasedOnFilterDTOFromFilterDTO.isM2() != null && !motherboardFieldsHolderBasedOnFilterDTOFromFilterDTO.ddrTypes().isEmpty()) {
            for (DDRType ddrType : filterDTO.getDdrTypes()) {
                List<MotherboardDTO> motherboardDTOsByDdrType = motherboardDAO.findAllByDdrType(ddrType)
                        .stream()
                        .map(MotherboardDTO::initDTOFromEntity)
                        .toList();
                motherboardByDdrTypeList.addAll(motherboardDTOsByDdrType); //
            }
        } else {
            motherboardByDdrTypeList.addAll(motherboardDAO.findAll()
                    .stream()
                    .map(MotherboardDTO::initDTOFromEntity)
                    .toList());
        }

        List<MotherboardDTO> motherboardByGpuInterfaceTypeList = new ArrayList<>();
        if (motherboardFieldsHolderBasedOnFilterDTOFromFilterDTO.gpuInterfaceTypes() != null && !motherboardFieldsHolderBasedOnFilterDTOFromFilterDTO.gpuInterfaceTypes().isEmpty()) {
            for (GPUInterfaceType gpuInterfaceType : filterDTO.getGpuInterfaceTypes()) {
                List<MotherboardDTO> motherboardDTOsByGpuInterfaceType = motherboardDAO.findAllByGpuInterfaceType(gpuInterfaceType)
                        .stream()
                        .map(MotherboardDTO::initDTOFromEntity)
                        .toList();
                motherboardByGpuInterfaceTypeList.addAll(motherboardDTOsByGpuInterfaceType); //
            }
        } else {
            motherboardByGpuInterfaceTypeList.addAll(motherboardDAO.findAll()
                    .stream()
                    .map(MotherboardDTO::initDTOFromEntity)
                    .toList());
        }

        List<MotherboardDTO> motherboardBySocketTypeList = new ArrayList<>();
        if (motherboardFieldsHolderBasedOnFilterDTOFromFilterDTO.socketTypes() != null && !motherboardFieldsHolderBasedOnFilterDTOFromFilterDTO.socketTypes().isEmpty()) {
            for (SocketType socketType : filterDTO.getSocketTypes()) {
                List<MotherboardDTO> motherboardDTOsBySocketType = motherboardDAO.findAllBySocketType(socketType)
                        .stream()
                        .map(MotherboardDTO::initDTOFromEntity)
                        .toList();
                motherboardBySocketTypeList.addAll(motherboardDTOsBySocketType); //
            }
        } else {
            motherboardBySocketTypeList.addAll(motherboardDAO.findAll()
                    .stream()
                    .map(MotherboardDTO::initDTOFromEntity)
                    .toList());
        }

        List<MotherboardDTO> motherboardByAtxTypeList = new ArrayList<>();
        if (motherboardFieldsHolderBasedOnFilterDTOFromFilterDTO.atxTypes() != null && !motherboardFieldsHolderBasedOnFilterDTOFromFilterDTO.atxTypes().isEmpty()) {
            for (ATXType atxType : filterDTO.getAtxTypes()) {
                List<MotherboardDTO> motherboardDTOsByAtxType = motherboardDAO.findAllByAtxType(atxType)
                        .stream()
                        .map(MotherboardDTO::initDTOFromEntity)
                        .toList();
                motherboardByAtxTypeList.addAll(motherboardDTOsByAtxType); //
            }
        } else {
            motherboardByAtxTypeList.addAll(motherboardDAO.findAll()
                    .stream()
                    .map(MotherboardDTO::initDTOFromEntity)
                    .toList());
        }

        List<MotherboardDTO> intersection = new ArrayList<>(motherboardsByName);

        List<List<MotherboardDTO>> listsToIntersect = Arrays.asList(
                motherboardsByName,
                motherboardsByMemorySlots,
                motherboardsByTdp,
                motherboardByIsM2List,
                motherboardByDdrTypeList,
                motherboardByGpuInterfaceTypeList,
                motherboardBySocketTypeList,
                motherboardByAtxTypeList
        );

        for (List<MotherboardDTO> list : listsToIntersect) {
            List<MotherboardDTO> tempList = new ArrayList<>(list);
            intersection.retainAll(tempList);
        }

        return intersection;

    }

    @Override
    public List<PowerSupplyDTO> filterAllPowerSuppliesBasedOnSpecification(final PowerSupplyFilterDTO filterDTO) {
        PowerSupplyFieldsHolderBasedOnFilterDTO powerSupplyFieldsHolderBasedOnFilterDTOFromFilterDTO = filterFieldsValueResolver.resolveAndGetPowerSupplyFieldsValuesFromFilterDTO(filterDTO);

        List<PowerSupplyDTO> powerSuppliesByName = powerSupplyDAO.findAllProductsByNameLike(powerSupplyFieldsHolderBasedOnFilterDTOFromFilterDTO.name())
               .stream()
               .map(PowerSupplyDTO::initDTOFromEntity)
               .toList();

        List<PowerSupplyDTO> powerSuppliesByPrice = powerSupplyDAO.findAllProductsByPriceBetween(
                        powerSupplyFieldsHolderBasedOnFilterDTOFromFilterDTO.minPrice(), powerSupplyFieldsHolderBasedOnFilterDTOFromFilterDTO.maxPrice())
               .stream()
               .map(PowerSupplyDTO::initDTOFromEntity)
               .toList();

        List<PowerSupplyDTO> powerSuppliesByWattage = powerSupplyDAO.findAllByWattageBetween(
                        powerSupplyFieldsHolderBasedOnFilterDTOFromFilterDTO.minWattage(), powerSupplyFieldsHolderBasedOnFilterDTOFromFilterDTO.maxWattage())
                .stream()
                .map(PowerSupplyDTO::initDTOFromEntity)
                .toList();

        List<PowerSupplyDTO> powerSuppliesByTdp = powerSupplyDAO.findAllByTdpBetween(
                        powerSupplyFieldsHolderBasedOnFilterDTOFromFilterDTO.minTdp(), powerSupplyFieldsHolderBasedOnFilterDTOFromFilterDTO.maxTdp())
                .stream()
                .map(PowerSupplyDTO::initDTOFromEntity)
                .toList();

        List<PowerSupplyDTO> powerSupplyByEfficiencyRatingList = new ArrayList<>();
        if (powerSupplyFieldsHolderBasedOnFilterDTOFromFilterDTO.efficiencyRatings() != null && !powerSupplyFieldsHolderBasedOnFilterDTOFromFilterDTO.efficiencyRatings().isEmpty()) {
            for (EfficiencyRating efficiencyRating : filterDTO.getEfficiencyRatings()) {
                List<PowerSupplyDTO> powerSupplyDTOsByEfficiencyRating = powerSupplyDAO.findAllByEfficiencyRating(efficiencyRating)
                        .stream()
                        .map(PowerSupplyDTO::initDTOFromEntity)
                        .toList();
                powerSupplyByEfficiencyRatingList.addAll(powerSupplyDTOsByEfficiencyRating); //
            }
        } else {
            powerSupplyByEfficiencyRatingList.addAll(powerSupplyDAO.findAll()
                    .stream()
                    .map(PowerSupplyDTO::initDTOFromEntity)
                    .toList());
        }

        List<PowerSupplyDTO> powerSupplyByModularTypeList = new ArrayList<>();
        if (powerSupplyFieldsHolderBasedOnFilterDTOFromFilterDTO.modularTypes() != null && !powerSupplyFieldsHolderBasedOnFilterDTOFromFilterDTO.modularTypes().isEmpty()) {
            for (ModularType modularType : filterDTO.getModularTypes()) {
                List<PowerSupplyDTO> powerSupplyDTOsByModularType = powerSupplyDAO.findAllByModularType(modularType)
                        .stream()
                        .map(PowerSupplyDTO::initDTOFromEntity)
                        .toList();
                powerSupplyByModularTypeList.addAll(powerSupplyDTOsByModularType); //
            }
        } else {
            powerSupplyByModularTypeList.addAll(powerSupplyDAO.findAll()
                    .stream()
                    .map(PowerSupplyDTO::initDTOFromEntity)
                    .toList());
        }

        List<PowerSupplyDTO> intersection = new ArrayList<>(powerSuppliesByName);

        List<List<PowerSupplyDTO>> listsToIntersect = Arrays.asList(
                powerSuppliesByPrice,
                powerSuppliesByWattage,
                powerSuppliesByTdp,
                powerSupplyByEfficiencyRatingList,
                powerSupplyByModularTypeList
        );

        for (List<PowerSupplyDTO> list : listsToIntersect) {
            List<PowerSupplyDTO> tempList = new ArrayList<>(list);
            intersection.retainAll(tempList);
        }

        return intersection;

    }

    @Override
    public List<RAMDTO> filterAllRamsBasedOnSpecification(final RAMFilterDTO filterDTO) {
        RamFieldsHolderBasedOnFilterDTO ramFieldsHolderBasedOnFilterDTOFromFilterDTO = filterFieldsValueResolver.resolveAndGetRamFieldsValuesFromFilterDTO(filterDTO);

        List<RAMDTO> ramsByName = ramDAO.findAllProductsByNameLike(ramFieldsHolderBasedOnFilterDTOFromFilterDTO.name())
              .stream()
              .map(RAMDTO::initDTOFromEntity)
              .toList();

        List<RAMDTO> ramsByPrice = ramDAO.findAllProductsByPriceBetween(
                        ramFieldsHolderBasedOnFilterDTOFromFilterDTO.minPrice(), ramFieldsHolderBasedOnFilterDTOFromFilterDTO.maxPrice())
              .stream()
              .map(RAMDTO::initDTOFromEntity)
              .toList();

        List<RAMDTO> ramsByCapacity = ramDAO.findAllByCountOfRamBetween(
                        ramFieldsHolderBasedOnFilterDTOFromFilterDTO.minCountOfRam(), ramFieldsHolderBasedOnFilterDTOFromFilterDTO.maxCountOfRam())
                .stream()
                .map(RAMDTO::initDTOFromEntity)
                .toList();

        List<RAMDTO> ramsByGbOfRam = ramDAO.findAllByGbOfRamBetween(
                        ramFieldsHolderBasedOnFilterDTOFromFilterDTO.minGbOfRam(), ramFieldsHolderBasedOnFilterDTOFromFilterDTO.maxGbOfRam())
                .stream()
                .map(RAMDTO::initDTOFromEntity)
                .toList();

        List<RAMDTO> ramsByTdp = ramDAO.findAllByTdpBetween(
                        ramFieldsHolderBasedOnFilterDTOFromFilterDTO.minTdp(), ramFieldsHolderBasedOnFilterDTOFromFilterDTO.maxTdp())
                .stream()
                .map(RAMDTO::initDTOFromEntity)
                .toList();

        List<RAMDTO> ramByDdrTypeList = new ArrayList<>();
        if (ramFieldsHolderBasedOnFilterDTOFromFilterDTO.ddrTypes() != null && !ramFieldsHolderBasedOnFilterDTOFromFilterDTO.ddrTypes().isEmpty()) {
            for (DDRType ddrType : filterDTO.getDdrTypes()) {
                List<RAMDTO> ramDTOsByDdrType = ramDAO.findAllByDdrType(ddrType)
                        .stream()
                        .map(RAMDTO::initDTOFromEntity)
                        .toList();
                ramByDdrTypeList.addAll(ramDTOsByDdrType); //
            }
        } else {
            ramByDdrTypeList.addAll(ramDAO.findAll()
                    .stream()
                    .map(RAMDTO::initDTOFromEntity)
                    .toList());
        }

        List<RAMDTO> intersection = new ArrayList<>(ramsByName);

        List<List<RAMDTO>> listsToIntersect = Arrays.asList(
                ramsByPrice,
                ramsByCapacity,
                ramsByGbOfRam,
                ramsByTdp,
                ramByDdrTypeList
        );

        for (List<RAMDTO> list : listsToIntersect) {
            List<RAMDTO> tempList = new ArrayList<>(list);
            intersection.retainAll(tempList);
        }

        return intersection;
    }

    @Override
    public List<ExternalHardDriveDTO> filterAllExternalHardDrivesBasedOnSpecification(final ExternalHardDriveFilterDTO filterDTO) {
        ExternalHardDriveFieldsHolderBasedOnFilterDTO externalHardDriveFieldsHolderBasedOnFilterDTOFromFilterDTO = filterFieldsValueResolver.resolveAndGetExternalHardDriveFieldsValuesFromFilterDTO(filterDTO);

        List<ExternalHardDriveDTO> externalHardDrivesByName = externalHardDriveDAO.findAllProductsByNameLike(externalHardDriveFieldsHolderBasedOnFilterDTOFromFilterDTO.name())
               .stream()
               .map(ExternalHardDriveDTO::initDTOFromEntity)
               .toList();

        List<ExternalHardDriveDTO> externalHardDrivesByPrice = externalHardDriveDAO.findAllProductsByPriceBetween(
                        externalHardDriveFieldsHolderBasedOnFilterDTOFromFilterDTO.minPrice(), externalHardDriveFieldsHolderBasedOnFilterDTOFromFilterDTO.maxPrice())
               .stream()
               .map(ExternalHardDriveDTO::initDTOFromEntity)
               .toList();

        List<ExternalHardDriveDTO> headsetsByCapacity = externalHardDriveDAO.findAllByCapacityBetween(
                        externalHardDriveFieldsHolderBasedOnFilterDTOFromFilterDTO.minCapacity(), externalHardDriveFieldsHolderBasedOnFilterDTOFromFilterDTO.maxCapacity())
                .stream()
                .map(ExternalHardDriveDTO::initDTOFromEntity)
                .toList();

        List<ExternalHardDriveDTO> headsetsByTdp = externalHardDriveDAO.findAllByTdpBetween(
                        externalHardDriveFieldsHolderBasedOnFilterDTOFromFilterDTO.minTdp(), externalHardDriveFieldsHolderBasedOnFilterDTOFromFilterDTO.maxTdp())
                .stream()
                .map(ExternalHardDriveDTO::initDTOFromEntity)
                .toList();

        List<ExternalHardDriveDTO> intersection = new ArrayList<>(externalHardDrivesByName);

        List<List<ExternalHardDriveDTO>> listsToIntersect = Arrays.asList(
                externalHardDrivesByPrice,
                headsetsByCapacity,
                headsetsByTdp
        );
        for (List<ExternalHardDriveDTO> list : listsToIntersect) {
            List<ExternalHardDriveDTO> tempList = new ArrayList<>(list);
            intersection.retainAll(tempList);
        }
        return intersection;

    }

    @Override
    public List<HeadsetDTO> filterAllHeadsetsBasedOnSpecification(final HeadsetFilterDTO filterDTO) {
        HeadsetFieldsHolderBasedOnFilterDTO headsetFieldsHolderBasedOnFilterDTOFromFilterDTO = filterFieldsValueResolver.resolveAndGetHeadsetFieldsValuesFromFilterDTO(filterDTO);

        List<HeadsetDTO> headsetsByName =headsetDAO.findAllProductsByNameLike(headsetFieldsHolderBasedOnFilterDTOFromFilterDTO.name())
                .stream()
                .map(HeadsetDTO::initDTOFromEntity)
                .toList();

        List<HeadsetDTO> headsetsByPrice = headsetDAO.findAllProductsByPriceBetween(
                        headsetFieldsHolderBasedOnFilterDTOFromFilterDTO.minPrice(), headsetFieldsHolderBasedOnFilterDTOFromFilterDTO.maxPrice())
                .stream()
                .map(HeadsetDTO::initDTOFromEntity)
                .toList();

        List<HeadsetDTO> headsetsByFrequency = headsetDAO.findAllByFrequencyBetween(
                        headsetFieldsHolderBasedOnFilterDTOFromFilterDTO.minFrequency(), headsetFieldsHolderBasedOnFilterDTOFromFilterDTO.maxFrequency())
                .stream()
                .map(HeadsetDTO::initDTOFromEntity)
                .toList();

        List<HeadsetDTO> headsetsByCableLength = headsetDAO.findAllByCableLengthBetween(
                        headsetFieldsHolderBasedOnFilterDTOFromFilterDTO.minCableLength(), headsetFieldsHolderBasedOnFilterDTOFromFilterDTO.maxCableLength())
                .stream()
                .map(HeadsetDTO::initDTOFromEntity)
                .toList();

        List<HeadsetDTO> headsetByConnectivityTypeList = new ArrayList<>();
        if (headsetFieldsHolderBasedOnFilterDTOFromFilterDTO.connectivityTypes() != null && !headsetFieldsHolderBasedOnFilterDTOFromFilterDTO.connectivityTypes().isEmpty()) {
            for (ConnectivityType connectivityType : filterDTO.getConnectivityTypes()) {
                List<HeadsetDTO> headsetDTOsByConnectivityType = headsetDAO.findAllByConnectivityType(connectivityType)
                        .stream()
                        .map(HeadsetDTO::initDTOFromEntity)
                        .toList();
                headsetByConnectivityTypeList.addAll(headsetDTOsByConnectivityType); //
            }
        } else {
            headsetByConnectivityTypeList.addAll(headsetDAO.findAll()
                    .stream()
                    .map(HeadsetDTO::initDTOFromEntity)
                    .toList());
        }

        List<HeadsetDTO> intersection = new ArrayList<>(headsetsByName);

        List<List<HeadsetDTO>> listsToIntersect = Arrays.asList(
                headsetsByPrice,
                headsetsByFrequency,
                headsetsByCableLength,
                headsetByConnectivityTypeList
        );
        for (List<HeadsetDTO> list : listsToIntersect) {
            List<HeadsetDTO> tempList = new ArrayList<>(list);
            intersection.retainAll(tempList);
        }
        return intersection;


    }
    @Override
    public List<KeyboardDTO> filterAllKeyboardsBasedOnSpecification(final KeyboardFilterDTO filterDTO) {
        KeyboardFieldsHolderBasedOnFilterDTO keyboardFieldsHolderBasedOnFilterDTOFromFilterDTO = filterFieldsValueResolver.resolveAndGetKeyboardFieldsValuesFromFilterDTO(filterDTO);

        List<KeyboardDTO> keyboardsByName = keyboardDAO.findAllProductsByNameLike(keyboardFieldsHolderBasedOnFilterDTOFromFilterDTO.name())
                .stream()
                .map(KeyboardDTO::initDTOFromEntity)
                .toList();

        List<KeyboardDTO> keyboardsByPrice = keyboardDAO.findAllProductsByPriceBetween(
                        keyboardFieldsHolderBasedOnFilterDTOFromFilterDTO.minPrice(), keyboardFieldsHolderBasedOnFilterDTOFromFilterDTO.maxPrice())
                .stream()
                .map(KeyboardDTO::initDTOFromEntity)
                .toList();

        List<KeyboardDTO> keyboardsByCableLength = keyboardDAO.findAllByCableLengthBetween(
                        keyboardFieldsHolderBasedOnFilterDTOFromFilterDTO.minCableLength(), keyboardFieldsHolderBasedOnFilterDTOFromFilterDTO.maxCableLength())
                .stream()
                .map(KeyboardDTO::initDTOFromEntity)
                .toList();

        List<KeyboardDTO> keyboardsByWeight = keyboardDAO.findAllByWeightBetween(
                        keyboardFieldsHolderBasedOnFilterDTOFromFilterDTO.minWeight(), keyboardFieldsHolderBasedOnFilterDTOFromFilterDTO.maxWeight())
                .stream()
                .map(KeyboardDTO::initDTOFromEntity)
                .toList();

        List<KeyboardDTO> keyboardsByDimensionList = new ArrayList<>();
        if (keyboardFieldsHolderBasedOnFilterDTOFromFilterDTO.dimensions() != null && !keyboardFieldsHolderBasedOnFilterDTOFromFilterDTO.dimensions().isEmpty()) {
            for (String dimension : filterDTO.getDimensions()) {
                List<KeyboardDTO> keyboardDTOsByDimension = keyboardDAO.findAllByDimension(dimension)
                        .stream()
                        .map(KeyboardDTO::initDTOFromEntity)
                        .toList();
                keyboardsByDimensionList.addAll(keyboardDTOsByDimension);
            }
        } else {
            keyboardsByDimensionList.addAll(keyboardDAO.findAll()
                    .stream()
                    .map(KeyboardDTO::initDTOFromEntity)
                    .toList());
        }

        List<KeyboardDTO> keyboardsByConnectivityTypeList = new ArrayList<>();
        if (keyboardFieldsHolderBasedOnFilterDTOFromFilterDTO.connectivityTypes() != null && !keyboardFieldsHolderBasedOnFilterDTOFromFilterDTO.connectivityTypes().isEmpty()) {
            for (ConnectivityType connectivityType : filterDTO.getConnectivityTypes()) {
                List<KeyboardDTO> keyboardDTOsByConnectivityType = keyboardDAO.findAllByConnectivityType(connectivityType)
                        .stream()
                        .map(KeyboardDTO::initDTOFromEntity)
                        .toList();
                keyboardsByConnectivityTypeList.addAll(keyboardDTOsByConnectivityType);
            }
        } else {
            keyboardsByConnectivityTypeList.addAll(keyboardDAO.findAll()
                    .stream()
                    .map(KeyboardDTO::initDTOFromEntity)
                    .toList());
        }

        List<KeyboardDTO> intersection = new ArrayList<>(keyboardsByName);

        List<List<KeyboardDTO>> listsToIntersect = Arrays.asList(
                keyboardsByPrice,
                keyboardsByCableLength,
                keyboardsByWeight,
                keyboardsByDimensionList,
                keyboardsByConnectivityTypeList
        );
        for (List<KeyboardDTO> list : listsToIntersect) {
            List<KeyboardDTO> tempList = new ArrayList<>(list);
            intersection.retainAll(tempList);
        }
        return intersection;

    }


    @Override
    public List<MonitorDTO> filterAllMonitorsBasedOnSpecification(final MonitorFilterDTO filterDTO) {
        MonitorFieldsHolderBasedOnFilterDTO monitorFieldsHolderBasedOnFilterDTOFromFilterDTO = filterFieldsValueResolver.resolveAndGetMonitorFieldsValuesFromFilterDTO(filterDTO);

        List<MonitorDTO> monitorsByName = monitorDAO.findAllProductsByNameLike(monitorFieldsHolderBasedOnFilterDTOFromFilterDTO.name())
                .stream()
                .map(MonitorDTO::initDTOFromEntity)
                .toList();

        List<MonitorDTO> monitorsByPrice = monitorDAO.findAllProductsByPriceBetween(
                        monitorFieldsHolderBasedOnFilterDTOFromFilterDTO.minPrice(), monitorFieldsHolderBasedOnFilterDTOFromFilterDTO.maxPrice())
                .stream()
                .map(MonitorDTO::initDTOFromEntity)
                .toList();

        List<MonitorDTO> monitorsByScreenSize = monitorDAO.findAllByScreenSizeBetween(
                        monitorFieldsHolderBasedOnFilterDTOFromFilterDTO.minScreenSize(), monitorFieldsHolderBasedOnFilterDTOFromFilterDTO.maxScreenSize())
                .stream()
                .map(MonitorDTO::initDTOFromEntity)
                .toList();

        List<MonitorDTO> monitorsByRefreshRate = monitorDAO.findAllByRefreshRateBetween(
                        monitorFieldsHolderBasedOnFilterDTOFromFilterDTO.minRefreshRate(), monitorFieldsHolderBasedOnFilterDTOFromFilterDTO.maxRefreshRate())
                .stream()
                .map(MonitorDTO::initDTOFromEntity)
                .toList();

        List<MonitorDTO> monitorsByMonitorScreenTypeList = new ArrayList<>();
        if (monitorFieldsHolderBasedOnFilterDTOFromFilterDTO.monitorScreenTypes() != null && !monitorFieldsHolderBasedOnFilterDTOFromFilterDTO.monitorScreenTypes().isEmpty()) {
            for (MonitorScreenType monitorScreenType : filterDTO.getScreenTypes()) {
                List<MonitorDTO> monitorDTOsByMonitorScreenType = monitorDAO.findAllByMonitorScreenType(monitorScreenType)
                        .stream()
                        .map(MonitorDTO::initDTOFromEntity)
                        .toList();
                monitorsByMonitorScreenTypeList.addAll(monitorDTOsByMonitorScreenType); //
            }
        } else {
            monitorsByMonitorScreenTypeList.addAll(monitorDAO.findAll()
                    .stream()
                    .map(MonitorDTO::initDTOFromEntity)
                    .toList());
        }

        List<MonitorDTO> intersection = new ArrayList<>(monitorsByName);

        List<List<MonitorDTO>> listsToIntersect = Arrays.asList(
               monitorsByPrice,
                monitorsByScreenSize,
                monitorsByRefreshRate,
                monitorsByMonitorScreenTypeList
        );
        for (List<MonitorDTO> list : listsToIntersect) {
            List<MonitorDTO> tempList = new ArrayList<>(list);
            intersection.retainAll(tempList);
        }
        return intersection;

    }

    @Override
    public List<MouseDTO> filterAllMiceBasedOnSpecification(final MouseFilterDTO filterDTO) {
        MouseFieldsHolderBasedOnFilterDTO mouseFieldsHolderBasedOnFilterDTOFromFilterDTO = filterFieldsValueResolver.resolveAndGetMouseFieldsValuesFromFilterDTO(filterDTO);

        List<MouseDTO> miceByName = mouseDAO.findAllProductsByNameLike(mouseFieldsHolderBasedOnFilterDTOFromFilterDTO.name())
                .stream()
                .map(MouseDTO::initDTOFromEntity)
                .toList();

        List<MouseDTO> miceByPrice = mouseDAO.findAllProductsByPriceBetween(
                        mouseFieldsHolderBasedOnFilterDTOFromFilterDTO.minPrice(), mouseFieldsHolderBasedOnFilterDTOFromFilterDTO.maxPrice())
                .stream()
                .map(MouseDTO::initDTOFromEntity)
                .toList();

        List<MouseDTO> miceByResolution = mouseDAO.findAllByMaxResolutionBetween(
                        mouseFieldsHolderBasedOnFilterDTOFromFilterDTO.minResolution(), mouseFieldsHolderBasedOnFilterDTOFromFilterDTO.maxResolution())
                .stream()
                .map(MouseDTO::initDTOFromEntity)
                .toList();

        List<MouseDTO> miceByCableLength = mouseDAO.findAllByCableLengthBetween(
                        mouseFieldsHolderBasedOnFilterDTOFromFilterDTO.minCableLength(), mouseFieldsHolderBasedOnFilterDTOFromFilterDTO.maxCableLength())
                .stream()
                .map(MouseDTO::initDTOFromEntity)
                .toList();

        List<MouseDTO> miceByWeight = mouseDAO.findAllByWeightBetween(
                        mouseFieldsHolderBasedOnFilterDTOFromFilterDTO.minWeight(), mouseFieldsHolderBasedOnFilterDTOFromFilterDTO.maxWeight())
                .stream()
                .map(MouseDTO::initDTOFromEntity)
                .toList();

        List<MouseDTO> miceByConnectivityTypeList = new ArrayList<>();
        if (mouseFieldsHolderBasedOnFilterDTOFromFilterDTO.connectivityTypes() != null && !mouseFieldsHolderBasedOnFilterDTOFromFilterDTO.connectivityTypes().isEmpty()) {
            for (ConnectivityType connectivityType : filterDTO.getConnectivityTypes()) {
                List<MouseDTO> mouseDTOsByConnectivityType = mouseDAO.findAllByConnectivityType(connectivityType)
                        .stream()
                        .map(MouseDTO::initDTOFromEntity)
                        .toList();
                miceByConnectivityTypeList.addAll(mouseDTOsByConnectivityType); //
            }
        } else {
            miceByConnectivityTypeList.addAll(mouseDAO.findAll()
                    .stream()
                    .map(MouseDTO::initDTOFromEntity)
                    .toList());
        }

        List<MouseDTO> intersection = new ArrayList<>(miceByName);

        List<List<MouseDTO>> listsToIntersect = Arrays.asList(
                miceByPrice,
                miceByResolution,
                miceByCableLength,
                miceByWeight,
                miceByConnectivityTypeList
        );
        for (List<MouseDTO> list : listsToIntersect) {
            List<MouseDTO> tempList = new ArrayList<>(list);
            intersection.retainAll(tempList);
        }
        return intersection;
    }

    @Override
    public List<SpeakerDTO> filterAllSpeakersBasedOnSpecification(final SpeakerFilterDTO filterDTO) {
        SpeakerFieldsHolderBasedOnFilterDTO speakerFieldsHolderBasedOnFilterDTOFromFilterDTO = filterFieldsValueResolver.resolveAndGetSpeakerFieldsValuesFromFilterDTO(filterDTO);

        List<SpeakerDTO> speakersByName = speakerDAO.findAllProductsByNameLike(speakerFieldsHolderBasedOnFilterDTOFromFilterDTO.name())
                .stream()
                .map(SpeakerDTO::initDTOFromEntity)
                .toList();

        List<SpeakerDTO> speakersByPrice = speakerDAO.findAllProductsByPriceBetween(
                        speakerFieldsHolderBasedOnFilterDTOFromFilterDTO.minPrice(), speakerFieldsHolderBasedOnFilterDTOFromFilterDTO.maxPrice())
                .stream()
                .map(SpeakerDTO::initDTOFromEntity)
                .toList();

        List<SpeakerDTO> speakersByFrequency = speakerDAO.findAllByFrequencyBetween(
                        speakerFieldsHolderBasedOnFilterDTOFromFilterDTO.minFrequency(), speakerFieldsHolderBasedOnFilterDTOFromFilterDTO.maxFrequency())
                .stream()
                .map(SpeakerDTO::initDTOFromEntity)
                .toList();

        List<SpeakerDTO> speakersByCableLength = speakerDAO.findAllByCableLengthBetween(
                        speakerFieldsHolderBasedOnFilterDTOFromFilterDTO.minCableLength(), speakerFieldsHolderBasedOnFilterDTOFromFilterDTO.maxCableLength())
                .stream()
                .map(SpeakerDTO::initDTOFromEntity)
                .toList();

        List<SpeakerDTO> speakersByDimensionList = new ArrayList<>();
        if (speakerFieldsHolderBasedOnFilterDTOFromFilterDTO.dimensions() != null && !speakerFieldsHolderBasedOnFilterDTOFromFilterDTO.dimensions().isEmpty()) {
            for (String dimension : filterDTO.getDimensions()) {
                List<SpeakerDTO> speakerDTOsByDimension = speakerDAO.findAllByDimension(dimension)
                        .stream()
                        .map(SpeakerDTO::initDTOFromEntity)
                        .toList();
                speakersByDimensionList.addAll(speakerDTOsByDimension);
            }
        } else {
            speakersByDimensionList.addAll(speakerDAO.findAll()
                    .stream()
                    .map(SpeakerDTO::initDTOFromEntity)
                    .toList());
        }

        List<SpeakerDTO> intersection = new ArrayList<>(speakersByName);

        List<List<SpeakerDTO>> listsToIntersect = Arrays.asList(
                speakersByPrice,
                speakersByFrequency,
                speakersByCableLength,
                speakersByDimensionList
        );
        for (List<SpeakerDTO> list : listsToIntersect) {
            List<SpeakerDTO> tempList = new ArrayList<>(list);
            intersection.retainAll(tempList);
        }
        return intersection;
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
    private <ENTITY extends Product> List<ENTITY> findAllProductsBasedOnTerm(
            String term,
            final ProductDAO<ENTITY> repository
    ) {

        if(term != null) {
            term = term.trim().toLowerCase();
        }

        return repository.findAllBasedOnTerm(term);
    }
    private List<MonitorDTO> findAllMonitorsBasedOnTerm(final String term) {
        return findAllProductsBasedOnTerm(term, monitorDAO)
                .stream()
                .map(MonitorDTO::initDTOFromEntity)
                .toList();
    }
    private List<CaseDTO> findAllCasesBasedOnTerm(final String term) {
        return findAllProductsBasedOnTerm(term, caseDAO)
                .stream()
                .map(CaseDTO::initDTOFromEntity)
                .toList();
    }
    private List<CoolerDTO> findAllCoolersBasedOnTerm(final String term) {
        return findAllProductsBasedOnTerm(term, coolerDAO)
                .stream()
                .map(CoolerDTO::initDTOFromEntity)
                .toList();
    }
    private List<CPUCoolerDTO> findAllCPUCoolersBasedOnTerm(final String term) {
        return findAllProductsBasedOnTerm(term, cpuCoolerDAO)
                .stream()
                .map(CPUCoolerDTO::initDTOFromEntity)
                .toList();
    }
    private List<CPUDTO> findAllCPUsBasedOnTerm(final String term) {
        return findAllProductsBasedOnTerm(term, cpuDAO)
                .stream()
                .map(CPUDTO::initDTOFromEntity)
                .toList();
    }
    private List<GPUDTO> findAllGPUsBasedOnTerm(final String term) {
        return findAllProductsBasedOnTerm(term, gpuDAO)
                .stream()
                .map(GPUDTO::initDTOFromEntity)
                .toList();
    }
    private List<InternalHardDriveDTO> findAllInternalHardDrivesBasedOnTerm(final String term) {
        return findAllProductsBasedOnTerm(term, internalHardDriveDAO)
                .stream()
                .map(InternalHardDriveDTO::initDTOFromEntity)
                .toList();
    }
    private List<MotherboardDTO> findAllMotherboardsBasedOnTerm(final String term) {
        return findAllProductsBasedOnTerm(term, motherboardDAO)
                .stream()
                .map(MotherboardDTO::initDTOFromEntity)
                .toList();
    }
    private List<PowerSupplyDTO> findAllPowerSuppliesBasedOnTerm(final String term) {
        return findAllProductsBasedOnTerm(term, powerSupplyDAO)
                .stream()
                .map(PowerSupplyDTO::initDTOFromEntity)
                .toList();
    }
    private List<RAMDTO> findAllRamsBasedOnTerm(final String term) {
        return findAllProductsBasedOnTerm(term, ramDAO)
                .stream()
                .map(RAMDTO::initDTOFromEntity)
                .toList();
    }
    private List<ExternalHardDriveDTO> findAllExternalHardDrivesBasedOnTerm(final String term) {
        return findAllProductsBasedOnTerm(term, externalHardDriveDAO)
                .stream()
                .map(ExternalHardDriveDTO::initDTOFromEntity)
                .toList();
    }
    private List<HeadsetDTO> findAllHeadsetsBasedOnTerm(final String term) {
        return findAllProductsBasedOnTerm(term, headsetDAO)
                .stream()
                .map(HeadsetDTO::initDTOFromEntity)
                .toList();
    }
    private List<KeyboardDTO> findAllKeyboardsBasedOnTerm(final String term) {
        return findAllProductsBasedOnTerm(term, keyboardDAO)
                .stream()
                .map(KeyboardDTO::initDTOFromEntity)
                .toList();
    }
    private List<MouseDTO> findAllMousesBasedOnTerm(final String term) {
        return findAllProductsBasedOnTerm(term, mouseDAO)
                .stream()
                .map(MouseDTO::initDTOFromEntity)
                .toList();
    }
    private List<SpeakerDTO> findAllSpeakersBasedOnTerm(final String term) {
        return findAllProductsBasedOnTerm(term, speakerDAO)
                .stream()
                .map(SpeakerDTO::initDTOFromEntity)
                .toList();
    }
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
    private <ENTITY extends Product> List<ENTITY> findAllProductsBasedOnNameAndPrice(
            final String name,
            Double minPrice,
            Double maxPrice,
            final ProductDAO<ENTITY> repository
    ) {
        if (minPrice == null) {
            minPrice = repository.getMinPriceFromAllProducts();
        }
        if (maxPrice == null) {
            maxPrice = repository.getMaxPriceFromAllProducts();
        }
        return repository.filterAllBasedOnNameAndPrice(name, minPrice, maxPrice);
    }
    private List<MonitorDTO> findAllMonitorsByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        return findAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice, monitorDAO)
                .stream()
                .map(MonitorDTO::initDTOFromEntity)
                .toList();
    }
    private List<CaseDTO> findAllCasesByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        return findAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice, caseDAO)
                .stream()
                .map(CaseDTO::initDTOFromEntity)
                .toList();
    }
    private List<CoolerDTO> findAllCoolersByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        return findAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice, coolerDAO)
                .stream()
                .map(CoolerDTO::initDTOFromEntity)
                .toList();
    }
    private List<CPUDTO> findAllCPUsByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        return findAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice, cpuDAO)
                .stream()
                .map(CPUDTO::initDTOFromEntity)
                .toList();
    }
    private List<CPUCoolerDTO> findAllCPUCoolersByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        return findAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice, cpuCoolerDAO)
                .stream()
                .map(CPUCoolerDTO::initDTOFromEntity)
                .toList();
    }
    private List<GPUDTO> findAllGPUsByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        return findAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice, gpuDAO)
                .stream()
                .map(GPUDTO::initDTOFromEntity)
                .toList();
    }
    private List<InternalHardDriveDTO> findAllInternalHardDrivesByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        return findAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice, internalHardDriveDAO)
                .stream()
                .map(InternalHardDriveDTO::initDTOFromEntity)
                .toList();
    }
    private List<MotherboardDTO> findAllMotherboardsByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        return findAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice, motherboardDAO)
                .stream()
                .map(MotherboardDTO::initDTOFromEntity)
                .toList();
    }
    private List<PowerSupplyDTO> findAllPowerSuppliesByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        return findAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice, powerSupplyDAO)
                .stream()
                .map(PowerSupplyDTO::initDTOFromEntity)
                .toList();
    }
    private List<RAMDTO> findAllRamsByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        return findAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice, ramDAO)
                .stream()
                .map(RAMDTO::initDTOFromEntity)
                .toList();
    }
    private List<ExternalHardDriveDTO> findAllExternalHardDrivesByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        return findAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice, externalHardDriveDAO)
                .stream()
                .map(ExternalHardDriveDTO::initDTOFromEntity)
                .toList();
    }
    private List<HeadsetDTO> findAllHeadsetsByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        return findAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice, headsetDAO)
                .stream()
                .map(HeadsetDTO::initDTOFromEntity)
                .toList();
    }
    private List<KeyboardDTO> findAllKeyboardsByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        return findAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice, keyboardDAO)
                .stream()
                .map(KeyboardDTO::initDTOFromEntity)
                .toList();
    }
    private List<MouseDTO> findAllMousesByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        return findAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice, mouseDAO)
                .stream()
                .map(MouseDTO::initDTOFromEntity)
                .toList();
    }
    private List<SpeakerDTO> findAllSpeakersByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        return findAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice, speakerDAO)
                .stream()
                .map(SpeakerDTO::initDTOFromEntity)
                .toList();
    }


}