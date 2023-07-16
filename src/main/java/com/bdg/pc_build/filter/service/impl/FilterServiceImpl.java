package com.bdg.pc_build.filter.service.impl;

import com.bdg.pc_build.filter.model.dto.ProductFilterDTO;
import com.bdg.pc_build.filter.model.dto.main.*;
import com.bdg.pc_build.filter.model.dto.peripheral.*;
import com.bdg.pc_build.filter.model.filter_fileds_holder.main.*;
import com.bdg.pc_build.filter.model.filter_fileds_holder.peripheral.*;
import com.bdg.pc_build.filter.resolver.FilterFieldsValueResolver;
import com.bdg.pc_build.filter.service.FilterService;
import com.bdg.pc_build.product.enumerations.*;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.dto.main.*;
import com.bdg.pc_build.product.model.dto.peripheral.*;
import com.bdg.pc_build.product.model.entity.Product;
import com.bdg.pc_build.product.repository.ProductDAO;
import com.bdg.pc_build.product.repository.main.*;
import com.bdg.pc_build.product.repository.peripheral.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FilterServiceImpl implements FilterService {

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

    private final FilterFieldsValueResolver filterFieldsValueResolver;


    @Override
    public List<CaseDTO> filterAllCasesBasedOnSpecification(final CaseFilterDTO filterDTO) {
        CaseFilterFieldsHolderBasedOnFilterDTO caseFilterFieldsHolderBasedOnFilterDTO = filterFieldsValueResolver.resolveAndGetCaseFieldsValuesFromFilterDTO(filterDTO);

        List<CaseDTO> casesByName = caseDAO.findAllProductsByNameLike(caseFilterFieldsHolderBasedOnFilterDTO.name())
                .stream()
                .map(CaseDTO::new)
                .toList();

        List<CaseDTO> casesByPrice = caseDAO.findAllProductsByPriceBetween(caseFilterFieldsHolderBasedOnFilterDTO.minPrice(), caseFilterFieldsHolderBasedOnFilterDTO.maxPrice())
                .stream()
                .map(CaseDTO::new)
                .toList();

        List<CaseDTO> casesByCpuCoolerHeight = caseDAO.findAllByMaxCpuCoolerHeightBetween(caseFilterFieldsHolderBasedOnFilterDTO.minCpuCoolerHeight(), caseFilterFieldsHolderBasedOnFilterDTO.maxCpuCoolerHeight())
                .stream()
                .map(CaseDTO::new)
                .toList();

        List<CaseDTO> casesByGpuLength = caseDAO.findAllByMaxGpuLengthBetween(caseFilterFieldsHolderBasedOnFilterDTO.minGpuLength(), caseFilterFieldsHolderBasedOnFilterDTO.maxGpuLength())
                .stream()
                .map(CaseDTO::new)
                .toList();

        List<CaseDTO> casesByPreInstalledFans = caseDAO.findAllByPreInstalledFansBetween(caseFilterFieldsHolderBasedOnFilterDTO.minPreInstalledFans(), caseFilterFieldsHolderBasedOnFilterDTO.maxPreInstalledFans())
                .stream()
                .map(CaseDTO::new)
                .toList();

        List<CaseDTO> casesByTowerTypeList = new ArrayList<>();
        if (caseFilterFieldsHolderBasedOnFilterDTO.towerTypes() != null && !caseFilterFieldsHolderBasedOnFilterDTO.towerTypes().isEmpty()) {
            for (TowerType towerType : caseFilterFieldsHolderBasedOnFilterDTO.towerTypes()) {
                List<CaseDTO> casesByTowerType = caseDAO.findAllByTowerType(towerType)
                        .stream()
                        .map(CaseDTO::new)
                        .toList();
                casesByTowerTypeList.addAll(casesByTowerType);
            }
        } else {
            casesByTowerTypeList.addAll(caseDAO.findAll()
                    .stream()
                    .map(CaseDTO::new)
                    .toList()
            );
        }

        List<CaseDTO> intersection = new ArrayList<>(casesByName);

        List<List<CaseDTO>> listsToIntersect = Arrays.asList(
                casesByPrice,
                casesByCpuCoolerHeight,
                casesByGpuLength,
                casesByPreInstalledFans,
                casesByTowerTypeList
        );

        for (List<CaseDTO> list : listsToIntersect) {
            List<CaseDTO> tempList = new ArrayList<>(list);
            intersection.retainAll(tempList);
        }
        return intersection;
    }

    @Override
    public List<CoolerDTO> filterAllCoolersBasedOnSpecification(final CoolerFilterDTO filterDTO) {
        CoolerFilterFieldsHolderBasedOnFilterDTO coolerFilterFieldsHolderBasedOnFilterDTO = filterFieldsValueResolver.resolveAndGetCoolerFieldsValuesFromFilterDTO(filterDTO);

        List<CoolerDTO> coolersByName = coolerDAO.findAllProductsByNameLike(coolerFilterFieldsHolderBasedOnFilterDTO.name())
                .stream()
                .map(CoolerDTO::new)
                .toList();

        List<CoolerDTO> coolersByPrice = coolerDAO.findAllProductsByPriceBetween(coolerFilterFieldsHolderBasedOnFilterDTO.minPrice(), coolerFilterFieldsHolderBasedOnFilterDTO.maxPrice())
                .stream()
                .map(CoolerDTO::new)
                .toList();

        List<CoolerDTO> coolerByTdp = coolerDAO.findAllByTdpBetween(coolerFilterFieldsHolderBasedOnFilterDTO.minTdp(), coolerFilterFieldsHolderBasedOnFilterDTO.maxTdp())
                .stream()
                .map(CoolerDTO::new)
                .toList();

        List<CoolerDTO> intersection = new ArrayList<>(coolersByName);

        List<List<CoolerDTO>> listsToIntersect = Arrays.asList(
                coolersByPrice,
                coolerByTdp
        );

        for (List<CoolerDTO> list : listsToIntersect) {
            List<CoolerDTO> tempList = new ArrayList<>(list);
            intersection.retainAll(tempList);
        }

        return intersection;
    }

    @Override
    public List<CPUCoolerDTO> filterAllCpuCoolersBasedOnSpecification(final CPUCoolerFilterDTO filterDTO) {
        CpuCoolerFilterFieldsHolderBasedOnFilterDTO cpuCoolerFilterFieldsHolderBasedOnFilterDTO = filterFieldsValueResolver.resolveAndGetCpuCoolerFieldsValuesFromFilterDTO(filterDTO);

        List<CPUCoolerDTO> cpuCoolersByName = cpuCoolerDAO.findAllProductsByNameLike(cpuCoolerFilterFieldsHolderBasedOnFilterDTO.name())
                .stream()
                .map(CPUCoolerDTO::new)
                .toList();

        List<CPUCoolerDTO> cpuCoolersByPrice = cpuCoolerDAO.findAllProductsByPriceBetween(cpuCoolerFilterFieldsHolderBasedOnFilterDTO.minPrice(), cpuCoolerFilterFieldsHolderBasedOnFilterDTO.maxPrice())
                .stream()
                .map(CPUCoolerDTO::new)
                .toList();

        List<CPUCoolerDTO> cpuCoolersByFamRpm = cpuCoolerDAO.findAllByFanRpmBetween(cpuCoolerFilterFieldsHolderBasedOnFilterDTO.minFanRpm(), cpuCoolerFilterFieldsHolderBasedOnFilterDTO.maxFanRpm())
                .stream()
                .map(CPUCoolerDTO::new)
                .toList();

        List<CPUCoolerDTO> cpuCoolersByTdp = cpuCoolerDAO.findAllByTdpBetween(cpuCoolerFilterFieldsHolderBasedOnFilterDTO.minTdp(), cpuCoolerFilterFieldsHolderBasedOnFilterDTO.maxTdp())
                .stream()
                .map(CPUCoolerDTO::new)
                .toList();

        List<CPUCoolerDTO> cpuCoolersBySocketTypeList = new ArrayList<>();
        if (cpuCoolerFilterFieldsHolderBasedOnFilterDTO.socketTypes() != null && !cpuCoolerFilterFieldsHolderBasedOnFilterDTO.socketTypes().isEmpty()) {
            for (SocketType socketType : cpuCoolerFilterFieldsHolderBasedOnFilterDTO.socketTypes()) {
                List<CPUCoolerDTO> casesBySocketType = cpuCoolerDAO.findAllBySocketType(socketType)
                        .stream()
                        .map(CPUCoolerDTO::new)
                        .toList();
                cpuCoolersBySocketTypeList.addAll(casesBySocketType);
            }
        } else {
            cpuCoolersBySocketTypeList.addAll(cpuCoolerDAO.findAll()
                    .stream()
                    .map(CPUCoolerDTO::new)
                    .toList()
            );
        }


        List<CPUCoolerDTO> intersection = new ArrayList<>(cpuCoolersByName);

        List<List<CPUCoolerDTO>> listsToIntersect = Arrays.asList(
                cpuCoolersByPrice,
                cpuCoolersByFamRpm,
                cpuCoolersBySocketTypeList,
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
        CpuFilterFieldsHolderBasedOnFilterDTO cpuFilterFieldsHolderBasedOnFilterDTO = filterFieldsValueResolver.resolveAndGetCpuFieldsValuesFromFilterDTO(filterDTO);

        List<CPUDTO> cpusByName = cpuDAO.findAllProductsByNameLike(cpuFilterFieldsHolderBasedOnFilterDTO.name())
                .stream()
                .map(CPUDTO::new)
                .toList();

        List<CPUDTO> cpusByPrice = cpuDAO.findAllProductsByPriceBetween(cpuFilterFieldsHolderBasedOnFilterDTO.minPrice(), cpuFilterFieldsHolderBasedOnFilterDTO.maxPrice())
                .stream()
                .map(CPUDTO::new)
                .toList();

        List<CPUDTO> cpusByCoreCount = cpuDAO.findAllByCoreCountBetween(cpuFilterFieldsHolderBasedOnFilterDTO.minCoreCount(), cpuFilterFieldsHolderBasedOnFilterDTO.maxCoreCount())
                .stream()
                .map(CPUDTO::new)
                .toList();

        List<CPUDTO> cpusByCoreCLock = cpuDAO.findAllByCoreClockBetween(cpuFilterFieldsHolderBasedOnFilterDTO.minCoreClock(), cpuFilterFieldsHolderBasedOnFilterDTO.maxCoreClock())
                .stream()
                .map(CPUDTO::new)
                .toList();

        List<CPUDTO> cpusByBoostCLock = cpuDAO.findAllByBoostClockBetween(cpuFilterFieldsHolderBasedOnFilterDTO.minBoostClock(), cpuFilterFieldsHolderBasedOnFilterDTO.maxBoostClock())
                .stream()
                .map(CPUDTO::new)
                .toList();

        List<CPUDTO> cpusByTdp = cpuDAO.findAllByTdpBetween(cpuFilterFieldsHolderBasedOnFilterDTO.minTdp(), cpuFilterFieldsHolderBasedOnFilterDTO.maxTdp())
                .stream()
                .map(CPUDTO::new)
                .toList();

        List<CPUDTO> cpusBySocketTypeList = new ArrayList<>();
        if (cpuFilterFieldsHolderBasedOnFilterDTO.socketTypes() != null && !cpuFilterFieldsHolderBasedOnFilterDTO.socketTypes().isEmpty()) {
            for (SocketType socketType : filterDTO.getSocketTypes()) {
                List<CPUDTO> cpuDTOsBySocketType = cpuDAO.findAllBySocketType(socketType)
                        .stream()
                        .map(CPUDTO::new)
                        .toList();
                cpusBySocketTypeList.addAll(cpuDTOsBySocketType);
            }
        } else {
            cpusBySocketTypeList.addAll(cpuDAO.findAll()
                    .stream()
                    .map(CPUDTO::new)
                    .toList());
        }

        List<CPUDTO> cpusByIntergratedGraphicsList = new ArrayList<>();
        if (cpuFilterFieldsHolderBasedOnFilterDTO.integratedGraphics() != null && !cpuFilterFieldsHolderBasedOnFilterDTO.integratedGraphics().isEmpty()) {
            for (String integratedGraphics : filterDTO.getIntegratedGraphics()) {
                List<CPUDTO> cpuDTOsByIntegratedGraphics = cpuDAO.findAllByIntegratedGraphics(integratedGraphics)
                        .stream()
                        .map(CPUDTO::new)
                        .toList();
                cpusByIntergratedGraphicsList.addAll(cpuDTOsByIntegratedGraphics);
            }
        } else {
            cpusByIntergratedGraphicsList.addAll(cpuDAO.findAll()
                    .stream()
                    .map(CPUDTO::new)
                    .toList());
        }

        List<CPUDTO> intersection = new ArrayList<>(cpusByName);

        List<List<CPUDTO>> listsToIntersect = Arrays.asList(
                cpusByPrice,
                cpusByCoreCount,
                cpusByCoreCLock,
                cpusByBoostCLock,
                cpusByTdp,
                cpusBySocketTypeList,
                cpusByIntergratedGraphicsList
        );
        for (List<CPUDTO> list : listsToIntersect) {
            List<CPUDTO> tempList = new ArrayList<>(list);
            intersection.retainAll(tempList);
        }
        return intersection;

    }


    @Override
    public List<GPUDTO> filterAllGpusBasedOnSpecification(final GPUFilterDTO filterDTO) {
        GpuFilterFieldsHolderBasedOnFilterDTO gpuFilterFieldsHolderBasedOnFilterDTOFromFilterDTO = filterFieldsValueResolver.resolveAndGetGpuFieldsValuesFromFilterDTO(filterDTO);

        List<GPUDTO> gpusByName = gpuDAO.findAllProductsByNameLike(gpuFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.name())
                .stream()
                .map(GPUDTO::new)
                .toList();

        List<GPUDTO> gpusByPrice = gpuDAO.findAllProductsByPriceBetween(
                        gpuFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minPrice(), gpuFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxPrice())
                .stream()
                .map(GPUDTO::new)
                .toList();

        List<GPUDTO> gpusByMemory = gpuDAO.findAllByMemoryBetween(
                        gpuFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minMemory(), gpuFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxMemory())
                .stream()
                .map(GPUDTO::new)
                .toList();

        List<GPUDTO> gpusByCoreClock = gpuDAO.findAllByCoreClockBetween(
                        gpuFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minCoreClock(), gpuFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxCoreClock())
                .stream()
                .map(GPUDTO::new)
                .toList();

        List<GPUDTO> gpusByBoostClock = gpuDAO.findAllByBoostClockBetween(
                        gpuFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minBoostClock(), gpuFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxBoostClock())
                .stream()
                .map(GPUDTO::new)
                .toList();

        List<GPUDTO> gpusByLength = gpuDAO.findAllByLengthBetween(
                        gpuFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minLength(), gpuFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxLength())
                .stream()
                .map(GPUDTO::new)
                .toList();

        List<GPUDTO> gpusByTdp = gpuDAO.findAllByTdpBetween(
                        gpuFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minTdp(), gpuFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxTdp())
                .stream()
                .map(GPUDTO::new)
                .toList();

        List<GPUDTO> gpusByInterfaceTypeList = new ArrayList<>();
        if (gpuFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.gpuInterfaceTypes() != null && !gpuFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.gpuInterfaceTypes().isEmpty()) {
            for (GPUInterfaceType gpuInterfaceType : filterDTO.getGpuInterfaceTypes()) {
                List<GPUDTO> gpuDTOsByGpuInterfaceType = gpuDAO.findAllByGpuInterfaceType(gpuInterfaceType)
                        .stream()
                        .map(GPUDTO::new)
                        .toList();
                gpusByInterfaceTypeList.addAll(gpuDTOsByGpuInterfaceType);
            }
        } else {
            gpusByInterfaceTypeList.addAll(gpuDAO.findAll()
                    .stream()
                    .map(GPUDTO::new)
                    .toList());
        }

        List<GPUDTO> intersection = new ArrayList<>(gpusByName);

        List<List<GPUDTO>> listsToIntersect = Arrays.asList(
                gpusByPrice,
                gpusByMemory,
                gpusByCoreClock,
                gpusByBoostClock,
                gpusByLength,
                gpusByTdp,
                gpusByInterfaceTypeList
        );
        for (List<GPUDTO> list : listsToIntersect) {
            List<GPUDTO> tempList = new ArrayList<>(list);
            intersection.retainAll(tempList);
        }
        return intersection;
    }

    @Override
    public List<InternalHardDriveDTO> filterAllInternalHardDrivesBasedOnSpecification(final InternalHardDriveFilterDTO filterDTO) {
        InternalHardDriveFilterFieldsHolderBasedOnFilterDTO internalHardDriveFilterFieldsHolderBasedOnFilterDTOFromFilterDTO = filterFieldsValueResolver.resolveAndGetInternalHardDriveFieldsValuesFromFilterDTO(filterDTO);

        List<InternalHardDriveDTO> internalHardDrivesByName = internalHardDriveDAO.findAllProductsByNameLike(internalHardDriveFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.name())
                .stream()
                .map(InternalHardDriveDTO::new)
                .toList();

        List<InternalHardDriveDTO> internalHardDrivesByPrice = internalHardDriveDAO.findAllProductsByPriceBetween(internalHardDriveFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minPrice(), internalHardDriveFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxPrice())
                .stream()
                .map(InternalHardDriveDTO::new)
                .toList();

        List<InternalHardDriveDTO> internalHardDrivesByCapacity = internalHardDriveDAO.findAllByCapacityBetween(
                        internalHardDriveFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minCapacity(), internalHardDriveFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxCapacity())
                .stream()
                .map(InternalHardDriveDTO::new)
                .toList();

        List<InternalHardDriveDTO> internalHardDrivesByTdp = internalHardDriveDAO.findAllByTdpBetween(
                        internalHardDriveFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minTdp(), internalHardDriveFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxTdp())
                .stream()
                .map(InternalHardDriveDTO::new)
                .toList();

        List<InternalHardDriveDTO> internalHardDriveByInternalHardDRiveInterfaceTypeList = new ArrayList<>();
        if (internalHardDriveFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.internalHardDriveInterfaceTypes() != null && !internalHardDriveFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.internalHardDriveInterfaceTypes().isEmpty()) {
            for (InternalHardDriveInterfaceType internalHardDriveInterfaceType : filterDTO.getInternalHardDriveInterfaceTypes()) {
                List<InternalHardDriveDTO> internalHardDriveDTOsByInterfaceType = internalHardDriveDAO.findAllByInternalHardDriveInterfaceType(internalHardDriveInterfaceType)
                        .stream()
                        .map(InternalHardDriveDTO::new)
                        .toList();
                internalHardDriveByInternalHardDRiveInterfaceTypeList.addAll(internalHardDriveDTOsByInterfaceType);
            }
        } else {
            internalHardDriveByInternalHardDRiveInterfaceTypeList.addAll(internalHardDriveDAO.findAll()
                    .stream()
                    .map(InternalHardDriveDTO::new)
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
        MotherboardFilterFieldsHolderBasedOnFilterDTO motherboardFilterFieldsHolderBasedOnFilterDTOFromFilterDTO = filterFieldsValueResolver.resolveAndGetMotherboardFieldsValuesFromFilterDTO(filterDTO);

        List<MotherboardDTO> motherboardsByName = motherboardDAO.findAllProductsByNameLike(motherboardFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.name())
                .stream()
                .map(MotherboardDTO::new)
                .toList();

        List<MotherboardDTO> motherboardsByPrice = motherboardDAO.findAllProductsByPriceBetween(
                        motherboardFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minPrice(), motherboardFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxPrice())
                .stream()
                .map(MotherboardDTO::new)
                .toList();

        List<MotherboardDTO> motherboardsByMemoryMax = motherboardDAO.findAllByMemoryMaxBetween(
                        motherboardFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minMemory(), motherboardFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxMemory())
                .stream()
                .map(MotherboardDTO::new)
                .toList();

        List<MotherboardDTO> motherboardsByMemorySlots = motherboardDAO.findAllByMemorySlotsBetween(
                        motherboardFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minMemorySlots(), motherboardFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxMemorySlots())
                .stream()
                .map(MotherboardDTO::new)
                .toList();

        List<MotherboardDTO> motherboardsByTdp = motherboardDAO.findAllByTdpBetween(
                        motherboardFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minTdp(), motherboardFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxTdp())
                .stream()
                .map(MotherboardDTO::new)
                .toList();

        List<MotherboardDTO> motherboardsByIsM2List = new ArrayList<>();
        if (motherboardFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.isM2() != null && !motherboardFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.isM2().isEmpty()) {
            for (Boolean isM2 : filterDTO.getIsM2()) {
                List<MotherboardDTO> motherboardsByIsM2 = motherboardDAO.findAllByIsM2(isM2)
                        .stream()
                        .map(MotherboardDTO::new)
                        .toList();
                motherboardsByIsM2List.addAll(motherboardsByIsM2);
            }
        } else {
            motherboardsByIsM2List.addAll(motherboardDAO.findAll()
                    .stream()
                    .map(MotherboardDTO::new)
                    .toList());
        }

        List<MotherboardDTO> motherboardsByDdrTypeList = new ArrayList<>();
        if (motherboardFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.ddrTypes() != null && !motherboardFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.ddrTypes().isEmpty()) {
            for (DDRType ddrType : filterDTO.getDdrTypes()) {
                List<MotherboardDTO> motherboardsByDdrType = motherboardDAO.findAllByDdrType(ddrType)
                        .stream()
                        .map(MotherboardDTO::new)
                        .toList();
                motherboardsByDdrTypeList.addAll(motherboardsByDdrType);
            }
        } else {
            motherboardsByDdrTypeList.addAll(motherboardDAO.findAll()
                    .stream()
                    .map(MotherboardDTO::new)
                    .toList());
        }

        List<MotherboardDTO> motherboardsByGpuInterfaceTypeList = new ArrayList<>();
        if (motherboardFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.gpuInterfaceTypes() != null && !motherboardFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.gpuInterfaceTypes().isEmpty()) {
            for (GPUInterfaceType gpuInterfaceType : filterDTO.getGpuInterfaceTypes()) {
                List<MotherboardDTO> motherboardsByGpuInterfaceType = motherboardDAO.findAllByGpuInterfaceType(gpuInterfaceType)
                        .stream()
                        .map(MotherboardDTO::new)
                        .toList();
                motherboardsByGpuInterfaceTypeList.addAll(motherboardsByGpuInterfaceType);
            }
        } else {
            motherboardsByGpuInterfaceTypeList.addAll(motherboardDAO.findAll()
                    .stream()
                    .map(MotherboardDTO::new)
                    .toList());
        }

        List<MotherboardDTO> motherboardsBySocketTypeList = new ArrayList<>();
        if (motherboardFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.socketTypes() != null && !motherboardFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.socketTypes().isEmpty()) {
            for (SocketType socketType : filterDTO.getSocketTypes()) {
                List<MotherboardDTO> motherboardsBySocketType = motherboardDAO.findAllBySocketType(socketType)
                        .stream()
                        .map(MotherboardDTO::new)
                        .toList();
                motherboardsBySocketTypeList.addAll(motherboardsBySocketType);
            }
        } else {
            motherboardsBySocketTypeList.addAll(motherboardDAO.findAll()
                    .stream()
                    .map(MotherboardDTO::new)
                    .toList());
        }

        List<MotherboardDTO> motherboardsByAtxTypeList = new ArrayList<>();
        if (motherboardFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.atxTypes() != null && !motherboardFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.atxTypes().isEmpty()) {
            for (ATXType atxType : filterDTO.getAtxTypes()) {
                List<MotherboardDTO> motherboardsByAtxType = motherboardDAO.findAllByAtxType(atxType)
                        .stream()
                        .map(MotherboardDTO::new)
                        .toList();
                motherboardsByAtxTypeList.addAll(motherboardsByAtxType);
            }
        } else {
            motherboardsByAtxTypeList.addAll(motherboardDAO.findAll()
                    .stream()
                    .map(MotherboardDTO::new)
                    .toList());
        }

        List<MotherboardDTO> intersection = new ArrayList<>(motherboardsByName);

        List<List<MotherboardDTO>> listsToIntersect = Arrays.asList(
                motherboardsByPrice,
                motherboardsByMemoryMax,
                motherboardsByMemorySlots,
                motherboardsByTdp,
                motherboardsByIsM2List,
                motherboardsByDdrTypeList,
                motherboardsByGpuInterfaceTypeList,
                motherboardsBySocketTypeList,
                motherboardsByAtxTypeList
        );

        for (List<MotherboardDTO> list : listsToIntersect) {
            List<MotherboardDTO> tempList = new ArrayList<>(list);
            intersection.retainAll(tempList);
        }

        return intersection;

    }

    @Override
    public List<PowerSupplyDTO> filterAllPowerSuppliesBasedOnSpecification(final PowerSupplyFilterDTO filterDTO) {
        PowerSupplyFilterFieldsHolderBasedOnFilterDTO powerSupplyFilterFieldsHolderBasedOnFilterDTOFromFilterDTO = filterFieldsValueResolver.resolveAndGetPowerSupplyFieldsValuesFromFilterDTO(filterDTO);

        List<PowerSupplyDTO> powerSuppliesByName = powerSupplyDAO.findAllProductsByNameLike(powerSupplyFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.name())
                .stream()
                .map(PowerSupplyDTO::new)
                .toList();

        List<PowerSupplyDTO> powerSuppliesByPrice = powerSupplyDAO.findAllProductsByPriceBetween(
                        powerSupplyFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minPrice(), powerSupplyFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxPrice())
                .stream()
                .map(PowerSupplyDTO::new)
                .toList();

        List<PowerSupplyDTO> powerSuppliesByWattage = powerSupplyDAO.findAllByWattageBetween(
                        powerSupplyFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minWattage(), powerSupplyFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxWattage())
                .stream()
                .map(PowerSupplyDTO::new)
                .toList();

        List<PowerSupplyDTO> powerSuppliesByTdp = powerSupplyDAO.findAllByTdpBetween(
                        powerSupplyFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minTdp(), powerSupplyFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxTdp())
                .stream()
                .map(PowerSupplyDTO::new)
                .toList();

        List<PowerSupplyDTO> powerSuppliesByEfficiencyRatingList = new ArrayList<>();
        if (powerSupplyFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.efficiencyRatings() != null && !powerSupplyFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.efficiencyRatings().isEmpty()) {
            for (EfficiencyRating efficiencyRating : filterDTO.getEfficiencyRatings()) {
                List<PowerSupplyDTO> powerSuppliesByEfficiencyRating = powerSupplyDAO.findAllByEfficiencyRating(efficiencyRating)
                        .stream()
                        .map(PowerSupplyDTO::new)
                        .toList();
                powerSuppliesByEfficiencyRatingList.addAll(powerSuppliesByEfficiencyRating);
            }
        } else {
            powerSuppliesByEfficiencyRatingList.addAll(powerSupplyDAO.findAll()
                    .stream()
                    .map(PowerSupplyDTO::new)
                    .toList());
        }

        List<PowerSupplyDTO> powerSuppliesByModularTypeList = new ArrayList<>();
        if (powerSupplyFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.modularTypes() != null && !powerSupplyFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.modularTypes().isEmpty()) {
            for (ModularType modularType : filterDTO.getModularTypes()) {
                List<PowerSupplyDTO> powerSuppliesDTOsByModularType = powerSupplyDAO.findAllByModularType(modularType)
                        .stream()
                        .map(PowerSupplyDTO::new)
                        .toList();
                powerSuppliesByModularTypeList.addAll(powerSuppliesDTOsByModularType);
            }
        } else {
            powerSuppliesByModularTypeList.addAll(powerSupplyDAO.findAll()
                    .stream()
                    .map(PowerSupplyDTO::new)
                    .toList());
        }

        List<PowerSupplyDTO> intersection = new ArrayList<>(powerSuppliesByName);

        List<List<PowerSupplyDTO>> listsToIntersect = Arrays.asList(
                powerSuppliesByPrice,
                powerSuppliesByWattage,
                powerSuppliesByTdp,
                powerSuppliesByEfficiencyRatingList,
                powerSuppliesByModularTypeList
        );

        for (List<PowerSupplyDTO> list : listsToIntersect) {
            List<PowerSupplyDTO> tempList = new ArrayList<>(list);
            intersection.retainAll(tempList);
        }

        return intersection;

    }

    @Override
    public List<RAMDTO> filterAllRamsBasedOnSpecification(final RAMFilterDTO filterDTO) {
        RamFilterFieldsHolderBasedOnFilterDTO ramFilterFieldsHolderBasedOnFilterDTOFromFilterDTO = filterFieldsValueResolver.resolveAndGetRamFieldsValuesFromFilterDTO(filterDTO);

        List<RAMDTO> ramsByName = ramDAO.findAllProductsByNameLike(ramFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.name())
                .stream()
                .map(RAMDTO::new)
                .toList();

        List<RAMDTO> ramsByPrice = ramDAO.findAllProductsByPriceBetween(
                        ramFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minPrice(), ramFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxPrice())
                .stream()
                .map(RAMDTO::new)
                .toList();

        List<RAMDTO> ramsByCountOfRam = ramDAO.findAllByCountOfRamBetween(
                        ramFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minCountOfRam(), ramFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxCountOfRam())
                .stream()
                .map(RAMDTO::new)
                .toList();

        List<RAMDTO> ramsByGbOfRam = ramDAO.findAllByGbOfRamBetween(
                        ramFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minGbOfRam(), ramFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxGbOfRam())
                .stream()
                .map(RAMDTO::new)
                .toList();

        List<RAMDTO> ramsByTdp = ramDAO.findAllByTdpBetween(
                        ramFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minTdp(), ramFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxTdp())
                .stream()
                .map(RAMDTO::new)
                .toList();

        List<RAMDTO> ramsByDdrTypeList = new ArrayList<>();
        if (ramFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.ddrTypes() != null && !ramFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.ddrTypes().isEmpty()) {
            for (DDRType ddrType : filterDTO.getDdrTypes()) {
                List<RAMDTO> ramsByDdrType = ramDAO.findAllByDdrType(ddrType)
                        .stream()
                        .map(RAMDTO::new)
                        .toList();
                ramsByDdrTypeList.addAll(ramsByDdrType);
            }
        } else {
            ramsByDdrTypeList.addAll(ramDAO.findAll()
                    .stream()
                    .map(RAMDTO::new)
                    .toList());
        }

        List<RAMDTO> intersection = new ArrayList<>(ramsByName);

        List<List<RAMDTO>> listsToIntersect = Arrays.asList(
                ramsByPrice,
                ramsByCountOfRam,
                ramsByGbOfRam,
                ramsByTdp,
                ramsByDdrTypeList
        );

        for (List<RAMDTO> list : listsToIntersect) {
            List<RAMDTO> tempList = new ArrayList<>(list);
            intersection.retainAll(tempList);
        }

        return intersection;
    }

    @Override
    public List<ExternalHardDriveDTO> filterAllExternalHardDrivesBasedOnSpecification(final ExternalHardDriveFilterDTO filterDTO) {
        ExternalHardDriveFilterFieldsHolderBasedOnFilterDTO externalHardDriveFilterFieldsHolderBasedOnFilterDTOFromFilterDTO = filterFieldsValueResolver.resolveAndGetExternalHardDriveFieldsValuesFromFilterDTO(filterDTO);

        List<ExternalHardDriveDTO> externalHardDrivesByName = externalHardDriveDAO.findAllProductsByNameLike(externalHardDriveFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.name())
                .stream()
                .map(ExternalHardDriveDTO::new)
                .toList();

        List<ExternalHardDriveDTO> externalHardDrivesByPrice = externalHardDriveDAO.findAllProductsByPriceBetween(
                        externalHardDriveFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minPrice(), externalHardDriveFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxPrice())
                .stream()
                .map(ExternalHardDriveDTO::new)
                .toList();

        List<ExternalHardDriveDTO> externalHardDrivesByCapacity = externalHardDriveDAO.findAllByCapacityBetween(
                        externalHardDriveFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minCapacity(), externalHardDriveFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxCapacity())
                .stream()
                .map(ExternalHardDriveDTO::new)
                .toList();

        List<ExternalHardDriveDTO> externalHardDrivesByTdp = externalHardDriveDAO.findAllByTdpBetween(
                        externalHardDriveFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minTdp(), externalHardDriveFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxTdp())
                .stream()
                .map(ExternalHardDriveDTO::new)
                .toList();

        List<ExternalHardDriveDTO> intersection = new ArrayList<>(externalHardDrivesByName);

        List<List<ExternalHardDriveDTO>> listsToIntersect = Arrays.asList(
                externalHardDrivesByPrice,
                externalHardDrivesByCapacity,
                externalHardDrivesByTdp
        );
        for (List<ExternalHardDriveDTO> list : listsToIntersect) {
            List<ExternalHardDriveDTO> tempList = new ArrayList<>(list);
            intersection.retainAll(tempList);
        }
        return intersection;

    }

    @Override
    public List<HeadsetDTO> filterAllHeadsetsBasedOnSpecification(final HeadsetFilterDTO filterDTO) {
        HeadsetFilterFieldsHolderBasedOnFilterDTO headsetFilterFieldsHolderBasedOnFilterDTOFromFilterDTO = filterFieldsValueResolver.resolveAndGetHeadsetFieldsValuesFromFilterDTO(filterDTO);

        List<HeadsetDTO> headsetsByName = headsetDAO.findAllProductsByNameLike(headsetFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.name())
                .stream()
                .map(HeadsetDTO::new)
                .toList();

        List<HeadsetDTO> headsetsByPrice = headsetDAO.findAllProductsByPriceBetween(
                        headsetFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minPrice(), headsetFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxPrice())
                .stream()
                .map(HeadsetDTO::new)
                .toList();

        List<HeadsetDTO> headsetsByFrequency = headsetDAO.findAllByFrequencyBetween(
                        headsetFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minFrequency(), headsetFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxFrequency())
                .stream()
                .map(HeadsetDTO::new)
                .toList();

        List<HeadsetDTO> headsetsByCableLength = headsetDAO.findAllByCableLengthBetween(
                        headsetFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minCableLength(), headsetFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxCableLength())
                .stream()
                .map(HeadsetDTO::new)
                .toList();

        List<HeadsetDTO> headsetsByConnectivityTypeList = new ArrayList<>();
        if (headsetFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.connectivityTypes() != null && !headsetFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.connectivityTypes().isEmpty()) {
            for (ConnectivityType connectivityType : filterDTO.getConnectivityTypes()) {
                List<HeadsetDTO> headsetsByConnectivityType = headsetDAO.findAllByConnectivityType(connectivityType)
                        .stream()
                        .map(HeadsetDTO::new)
                        .toList();
                headsetsByConnectivityTypeList.addAll(headsetsByConnectivityType);
            }
        } else {
            headsetsByConnectivityTypeList.addAll(headsetDAO.findAll()
                    .stream()
                    .map(HeadsetDTO::new)
                    .toList());
        }

        List<HeadsetDTO> intersection = new ArrayList<>(headsetsByName);

        List<List<HeadsetDTO>> listsToIntersect = Arrays.asList(
                headsetsByPrice,
                headsetsByFrequency,
                headsetsByCableLength,
                headsetsByConnectivityTypeList
        );
        for (List<HeadsetDTO> list : listsToIntersect) {
            List<HeadsetDTO> tempList = new ArrayList<>(list);
            intersection.retainAll(tempList);
        }
        return intersection;


    }

    @Override
    public List<KeyboardDTO> filterAllKeyboardsBasedOnSpecification(final KeyboardFilterDTO filterDTO) {
        KeyboardFilterFieldsHolderBasedOnFilterDTO keyboardFilterFieldsHolderBasedOnFilterDTOFromFilterDTO = filterFieldsValueResolver.resolveAndGetKeyboardFieldsValuesFromFilterDTO(filterDTO);

        List<KeyboardDTO> keyboardsByName = keyboardDAO.findAllProductsByNameLike(keyboardFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.name())
                .stream()
                .map(KeyboardDTO::new)
                .toList();

        List<KeyboardDTO> keyboardsByPrice = keyboardDAO.findAllProductsByPriceBetween(
                        keyboardFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minPrice(), keyboardFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxPrice())
                .stream()
                .map(KeyboardDTO::new)
                .toList();

        List<KeyboardDTO> keyboardsByCableLength = keyboardDAO.findAllByCableLengthBetween(
                        keyboardFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minCableLength(), keyboardFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxCableLength())
                .stream()
                .map(KeyboardDTO::new)
                .toList();

        List<KeyboardDTO> keyboardsByWeight = keyboardDAO.findAllByWeightBetween(
                        keyboardFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minWeight(), keyboardFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxWeight())
                .stream()
                .map(KeyboardDTO::new)
                .toList();

        List<KeyboardDTO> keyboardsByDimensionList = new ArrayList<>();
        if (keyboardFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.dimensions() != null && !keyboardFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.dimensions().isEmpty()) {
            for (String dimension : filterDTO.getDimensions()) {
                List<KeyboardDTO> keyboardsByDimension = keyboardDAO.findAllByDimension(dimension)
                        .stream()
                        .map(KeyboardDTO::new)
                        .toList();
                keyboardsByDimensionList.addAll(keyboardsByDimension);
            }
        } else {
            keyboardsByDimensionList.addAll(keyboardDAO.findAll()
                    .stream()
                    .map(KeyboardDTO::new)
                    .toList());
        }

        List<KeyboardDTO> keyboardsByConnectivityTypeList = new ArrayList<>();
        if (keyboardFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.connectivityTypes() != null && !keyboardFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.connectivityTypes().isEmpty()) {
            for (ConnectivityType connectivityType : filterDTO.getConnectivityTypes()) {
                List<KeyboardDTO> keyboardsByConnectivityType = keyboardDAO.findAllByConnectivityType(connectivityType)
                        .stream()
                        .map(KeyboardDTO::new)
                        .toList();
                keyboardsByConnectivityTypeList.addAll(keyboardsByConnectivityType);
            }
        } else {
            keyboardsByConnectivityTypeList.addAll(keyboardDAO.findAll()
                    .stream()
                    .map(KeyboardDTO::new)
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
        MonitorFilterFieldsHolderBasedOnFilterDTO monitorFilterFieldsHolderBasedOnFilterDTOFromFilterDTO = filterFieldsValueResolver.resolveAndGetMonitorFieldsValuesFromFilterDTO(filterDTO);

        List<MonitorDTO> monitorsByName = monitorDAO.findAllProductsByNameLike(monitorFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.name())
                .stream()
                .map(MonitorDTO::new)
                .toList();

        List<MonitorDTO> monitorsByPrice = monitorDAO.findAllProductsByPriceBetween(
                        monitorFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minPrice(), monitorFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxPrice())
                .stream()
                .map(MonitorDTO::new)
                .toList();

        List<MonitorDTO> monitorsByScreenSize = monitorDAO.findAllByScreenSizeBetween(
                        monitorFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minScreenSize(), monitorFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxScreenSize())
                .stream()
                .map(MonitorDTO::new)
                .toList();

        List<MonitorDTO> monitorsByRefreshRate = monitorDAO.findAllByRefreshRateBetween(
                        monitorFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minRefreshRate(), monitorFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxRefreshRate())
                .stream()
                .map(MonitorDTO::new)
                .toList();

        List<MonitorDTO> monitorsByMonitorScreenTypeList = new ArrayList<>();
        if (monitorFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.monitorScreenTypes() != null && !monitorFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.monitorScreenTypes().isEmpty()) {
            for (MonitorScreenType monitorScreenType : filterDTO.getScreenTypes()) {
                List<MonitorDTO> monitorsByMonitorScreenType = monitorDAO.findAllByMonitorScreenType(monitorScreenType)
                        .stream()
                        .map(MonitorDTO::new)
                        .toList();
                monitorsByMonitorScreenTypeList.addAll(monitorsByMonitorScreenType);
            }
        } else {
            monitorsByMonitorScreenTypeList.addAll(monitorDAO.findAll()
                    .stream()
                    .map(MonitorDTO::new)
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
        MouseFilterFieldsHolderBasedOnFilterDTO mouseFilterFieldsHolderBasedOnFilterDTOFromFilterDTO = filterFieldsValueResolver.resolveAndGetMouseFieldsValuesFromFilterDTO(filterDTO);

        List<MouseDTO> miceByName = mouseDAO.findAllProductsByNameLike(mouseFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.name())
                .stream()
                .map(MouseDTO::new)
                .toList();

        List<MouseDTO> miceByPrice = mouseDAO.findAllProductsByPriceBetween(
                        mouseFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minPrice(), mouseFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxPrice())
                .stream()
                .map(MouseDTO::new)
                .toList();

        List<MouseDTO> miceByResolution = mouseDAO.findAllByMaxResolutionBetween(
                        mouseFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minResolution(), mouseFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxResolution())
                .stream()
                .map(MouseDTO::new)
                .toList();

        List<MouseDTO> miceByCableLength = mouseDAO.findAllByCableLengthBetween(
                        mouseFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minCableLength(), mouseFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxCableLength())
                .stream()
                .map(MouseDTO::new)
                .toList();

        List<MouseDTO> miceByWeight = mouseDAO.findAllByWeightBetween(
                        mouseFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minWeight(), mouseFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxWeight())
                .stream()
                .map(MouseDTO::new)
                .toList();

        List<MouseDTO> miceByConnectivityTypeList = new ArrayList<>();
        if (mouseFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.connectivityTypes() != null && !mouseFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.connectivityTypes().isEmpty()) {
            for (ConnectivityType connectivityType : filterDTO.getConnectivityTypes()) {
                List<MouseDTO> mouseDTOsByConnectivityType = mouseDAO.findAllByConnectivityType(connectivityType)
                        .stream()
                        .map(MouseDTO::new)
                        .toList();
                miceByConnectivityTypeList.addAll(mouseDTOsByConnectivityType);
            }
        } else {
            miceByConnectivityTypeList.addAll(mouseDAO.findAll()
                    .stream()
                    .map(MouseDTO::new)
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
        SpeakerFilterFieldsHolderBasedOnFilterDTO speakerFilterFieldsHolderBasedOnFilterDTOFromFilterDTO = filterFieldsValueResolver.resolveAndGetSpeakerFieldsValuesFromFilterDTO(filterDTO);

        List<SpeakerDTO> speakersByName = speakerDAO.findAllProductsByNameLike(speakerFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.name())
                .stream()
                .map(SpeakerDTO::new)
                .toList();

        List<SpeakerDTO> speakersByPrice = speakerDAO.findAllProductsByPriceBetween(
                        speakerFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minPrice(), speakerFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxPrice())
                .stream()
                .map(SpeakerDTO::new)
                .toList();

        List<SpeakerDTO> speakersByFrequency = speakerDAO.findAllByFrequencyBetween(
                        speakerFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minFrequency(), speakerFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxFrequency())
                .stream()
                .map(SpeakerDTO::new)
                .toList();

        List<SpeakerDTO> speakersByCableLength = speakerDAO.findAllByCableLengthBetween(
                        speakerFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.minCableLength(), speakerFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.maxCableLength())
                .stream()
                .map(SpeakerDTO::new)
                .toList();

        List<SpeakerDTO> speakersByDimensionList = new ArrayList<>();
        if (speakerFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.dimensions() != null && !speakerFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.dimensions().isEmpty()) {
            for (String dimension : filterDTO.getDimensions()) {
                List<SpeakerDTO> speakersByDimension = speakerDAO.findAllByDimension(dimension)
                        .stream()
                        .map(SpeakerDTO::new)
                        .toList();
                speakersByDimensionList.addAll(speakersByDimension);
            }
        } else {
            speakersByDimensionList.addAll(speakerDAO.findAll()
                    .stream()
                    .map(SpeakerDTO::new)
                    .toList());
        }

        List<SpeakerDTO> speakersByPowerSourceTypeList = new ArrayList<>();
        if (speakerFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.powerSourceTypes() != null && !speakerFilterFieldsHolderBasedOnFilterDTOFromFilterDTO.powerSourceTypes().isEmpty()) {
            for (PowerSourceType powerSourceType : filterDTO.getPowerSourceTypes()) {
                List<SpeakerDTO> speakersByPowerSourceType = speakerDAO.findAllByPowerSourceType(powerSourceType)
                        .stream()
                        .map(SpeakerDTO::new)
                        .toList();
                speakersByPowerSourceTypeList.addAll(speakersByPowerSourceType);
            }
        } else {
            speakersByPowerSourceTypeList.addAll(speakerDAO.findAll()
                    .stream()
                    .map(SpeakerDTO::new)
                    .toList());
        }

        List<SpeakerDTO> intersection = new ArrayList<>(speakersByName);

        List<List<SpeakerDTO>> listsToIntersect = Arrays.asList(
                speakersByPrice,
                speakersByFrequency,
                speakersByCableLength,
                speakersByPowerSourceTypeList,
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
        productList.addAll(findAllCpuCoolersBasedOnTerm(term));
        productList.addAll(findAllCPUsBasedOnTerm(term));
        productList.addAll(findAllGpusBasedOnTerm(term));
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
        if (term != null) {
            term = term.trim().toLowerCase();
        }
        return repository.findAllBasedOnTerm(term);
    }

    private List<MonitorDTO> findAllMonitorsBasedOnTerm(final String term) {
        return findAllProductsBasedOnTerm(term, monitorDAO)
                .stream()
                .map(MonitorDTO::new)
                .toList();
    }

    private List<CaseDTO> findAllCasesBasedOnTerm(final String term) {
        return findAllProductsBasedOnTerm(term, caseDAO)
                .stream()
                .map(CaseDTO::new)
                .toList();
    }

    private List<CoolerDTO> findAllCoolersBasedOnTerm(final String term) {
        return findAllProductsBasedOnTerm(term, coolerDAO)
                .stream()
                .map(CoolerDTO::new)
                .toList();
    }

    private List<CPUCoolerDTO> findAllCpuCoolersBasedOnTerm(final String term) {
        return findAllProductsBasedOnTerm(term, cpuCoolerDAO)
                .stream()
                .map(CPUCoolerDTO::new)
                .toList();
    }

    private List<CPUDTO> findAllCPUsBasedOnTerm(final String term) {
        return findAllProductsBasedOnTerm(term, cpuDAO)
                .stream()
                .map(CPUDTO::new)
                .toList();
    }

    private List<GPUDTO> findAllGpusBasedOnTerm(final String term) {
        return findAllProductsBasedOnTerm(term, gpuDAO)
                .stream()
                .map(GPUDTO::new)
                .toList();
    }

    private List<InternalHardDriveDTO> findAllInternalHardDrivesBasedOnTerm(final String term) {
        return findAllProductsBasedOnTerm(term, internalHardDriveDAO)
                .stream()
                .map(InternalHardDriveDTO::new)
                .toList();
    }

    private List<MotherboardDTO> findAllMotherboardsBasedOnTerm(final String term) {
        return findAllProductsBasedOnTerm(term, motherboardDAO)
                .stream()
                .map(MotherboardDTO::new)
                .toList();
    }

    private List<PowerSupplyDTO> findAllPowerSuppliesBasedOnTerm(final String term) {
        return findAllProductsBasedOnTerm(term, powerSupplyDAO)
                .stream()
                .map(PowerSupplyDTO::new)
                .toList();
    }

    private List<RAMDTO> findAllRamsBasedOnTerm(final String term) {
        return findAllProductsBasedOnTerm(term, ramDAO)
                .stream()
                .map(RAMDTO::new)
                .toList();
    }

    private List<ExternalHardDriveDTO> findAllExternalHardDrivesBasedOnTerm(final String term) {
        return findAllProductsBasedOnTerm(term, externalHardDriveDAO)
                .stream()
                .map(ExternalHardDriveDTO::new)
                .toList();
    }

    private List<HeadsetDTO> findAllHeadsetsBasedOnTerm(final String term) {
        return findAllProductsBasedOnTerm(term, headsetDAO)
                .stream()
                .map(HeadsetDTO::new)
                .toList();
    }

    private List<KeyboardDTO> findAllKeyboardsBasedOnTerm(final String term) {
        return findAllProductsBasedOnTerm(term, keyboardDAO)
                .stream()
                .map(KeyboardDTO::new)
                .toList();
    }

    private List<MouseDTO> findAllMousesBasedOnTerm(final String term) {
        return findAllProductsBasedOnTerm(term, mouseDAO)
                .stream()
                .map(MouseDTO::new)
                .toList();
    }

    private List<SpeakerDTO> findAllSpeakersBasedOnTerm(final String term) {
        return findAllProductsBasedOnTerm(term, speakerDAO)
                .stream()
                .map(SpeakerDTO::new)
                .toList();
    }

    @Override
    public List<ProductDTO> filterAllProductsByNameAndPrice(final ProductFilterDTO filterDTO) {
        List<ProductDTO> productDTOList = new ArrayList<>();
        productDTOList.addAll(findAllMonitorsByNameAndPrice(filterDTO.getName(), filterDTO.getMinPrice(), filterDTO.getMaxPrice()));
        productDTOList.addAll(findAllCasesByNameAndPrice(filterDTO.getName(), filterDTO.getMinPrice(), filterDTO.getMaxPrice()));
        productDTOList.addAll(findAllCoolersByNameAndPrice(filterDTO.getName(), filterDTO.getMinPrice(), filterDTO.getMaxPrice()));
        productDTOList.addAll(findAllCpusByNameAndPrice(filterDTO.getName(), filterDTO.getMinPrice(), filterDTO.getMaxPrice()));
        productDTOList.addAll(findAllCpuCoolersByNameAndPrice(filterDTO.getName(), filterDTO.getMinPrice(), filterDTO.getMaxPrice()));
        productDTOList.addAll(findAllGpusByNameAndPrice(filterDTO.getName(), filterDTO.getMinPrice(), filterDTO.getMaxPrice()));
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
                .map(MonitorDTO::new)
                .toList();
    }

    private List<CaseDTO> findAllCasesByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        return findAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice, caseDAO)
                .stream()
                .map(CaseDTO::new)
                .toList();
    }

    private List<CoolerDTO> findAllCoolersByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        return findAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice, coolerDAO)
                .stream()
                .map(CoolerDTO::new)
                .toList();
    }

    private List<CPUDTO> findAllCpusByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        return findAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice, cpuDAO)
                .stream()
                .map(CPUDTO::new)
                .toList();
    }

    private List<CPUCoolerDTO> findAllCpuCoolersByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        return findAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice, cpuCoolerDAO)
                .stream()
                .map(CPUCoolerDTO::new)
                .toList();
    }

    private List<GPUDTO> findAllGpusByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        return findAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice, gpuDAO)
                .stream()
                .map(GPUDTO::new)
                .toList();
    }

    private List<InternalHardDriveDTO> findAllInternalHardDrivesByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        return findAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice, internalHardDriveDAO)
                .stream()
                .map(InternalHardDriveDTO::new)
                .toList();
    }

    private List<MotherboardDTO> findAllMotherboardsByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        return findAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice, motherboardDAO)
                .stream()
                .map(MotherboardDTO::new)
                .toList();
    }

    private List<PowerSupplyDTO> findAllPowerSuppliesByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        return findAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice, powerSupplyDAO)
                .stream()
                .map(PowerSupplyDTO::new)
                .toList();
    }

    private List<RAMDTO> findAllRamsByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        return findAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice, ramDAO)
                .stream()
                .map(RAMDTO::new)
                .toList();
    }

    private List<ExternalHardDriveDTO> findAllExternalHardDrivesByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        return findAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice, externalHardDriveDAO)
                .stream()
                .map(ExternalHardDriveDTO::new)
                .toList();
    }

    private List<HeadsetDTO> findAllHeadsetsByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        return findAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice, headsetDAO)
                .stream()
                .map(HeadsetDTO::new)
                .toList();
    }

    private List<KeyboardDTO> findAllKeyboardsByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        return findAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice, keyboardDAO)
                .stream()
                .map(KeyboardDTO::new)
                .toList();
    }

    private List<MouseDTO> findAllMousesByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        return findAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice, mouseDAO)
                .stream()
                .map(MouseDTO::new)
                .toList();
    }

    private List<SpeakerDTO> findAllSpeakersByNameAndPrice(String name, Double minPrice, Double maxPrice) {
        return findAllProductsBasedOnNameAndPrice(name, minPrice, maxPrice, speakerDAO)
                .stream()
                .map(SpeakerDTO::new)
                .toList();
    }
}