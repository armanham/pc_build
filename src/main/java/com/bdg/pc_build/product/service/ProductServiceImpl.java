package com.bdg.pc_build.product.service;

import com.bdg.pc_build.checking.exception.ProductNotFoundException;
import com.bdg.pc_build.checking.exception.SameNameDifferentDescriptionException;
import com.bdg.pc_build.product.model.dto.main_component.*;
import com.bdg.pc_build.product.model.dto.peripheral.*;
import com.bdg.pc_build.product.model.entity.Product;
import com.bdg.pc_build.product.model.entity.main_component.*;
import com.bdg.pc_build.product.model.entity.peripheral.*;
import com.bdg.pc_build.product.repository.ProductDAO;
import com.bdg.pc_build.product.repository.main_component.*;
import com.bdg.pc_build.product.repository.peripheral.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {


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


    private <ENTITY extends Product> ENTITY save(
            final ENTITY product,
            final ProductDAO<ENTITY> repository
    ) {
        Optional<ENTITY> optionalENTITY = repository.findByName(product.getName());
        if (optionalENTITY.isPresent()) {
            ENTITY foundedProduct = optionalENTITY.get();
            if (!foundedProduct.equals(product)) {
                throw new SameNameDifferentDescriptionException(foundedProduct.getClass());
            }
            foundedProduct.setCount(foundedProduct.getCount() + product.getCount());
            return repository.save(foundedProduct);
        }
        return repository.save(product);
    }

    private <ENTITY extends Product> ENTITY findByName(
            final String name,
            final ProductDAO<ENTITY> repository
    ) {
        Optional<ENTITY> optionalENTITY = repository.findByName(name);
        if (optionalENTITY.isEmpty()) {
            throw new ProductNotFoundException(optionalENTITY.getClass(), name);
        }
        return optionalENTITY.get();
    }

    private <ENTITY extends Product> List<ENTITY> findAllByPrice(
            final Double minPrice,
            final Double maxPrice,
            final ProductDAO<ENTITY> repository
    ) {
        return repository.findAllProductsByPriceBetween(minPrice, maxPrice);
    }

    private <ENTITY extends Product> List<ENTITY> findAllByPurchasedPrice(
            final Double minPurchasedPrice,
            final Double maxPurchasedPrice,
            final ProductDAO<ENTITY> repository
    ) {
        return repository.findAllProductsByPurchasedPriceBetween(minPurchasedPrice, maxPurchasedPrice);
    }

    private <ENTITY extends Product> ENTITY updatePriceByName(
            final String name,
            final Double newPrice,
            final ProductDAO<ENTITY> repository
    ) {
        Optional<ENTITY> optionalENTITY = repository.findByName(name);
        if (optionalENTITY.isEmpty()) {
            throw new ProductNotFoundException(optionalENTITY.getClass(), name);
        }
        ENTITY foundedProduct = optionalENTITY.get();
        foundedProduct.setPrice(newPrice);
        return repository.save(foundedProduct);
    }

    private <ENTITY extends Product> ENTITY reduceCountByName(
            final String name,
            final Integer countToBeReduced,
            final ProductDAO<ENTITY> repository
    ) {
        Optional<ENTITY> optionalENTITY = repository.findByName(name);
        if (optionalENTITY.isEmpty()) {
            throw new ProductNotFoundException(optionalENTITY.getClass(), name);
        }

        ENTITY foundedProduct = optionalENTITY.get();

        if (foundedProduct.getCount() < countToBeReduced) {
            throw new ApranqyQichAException(foundedProduct.getClass(), name, foundedProduct.getCount());
        }
        foundedProduct.setCount(foundedProduct.getCount() - countToBeReduced);
        return repository.save(foundedProduct);
    }


    @Override
    public MonitorDTO saveMonitor(final MonitorDTO dto) {
        return MonitorDTO.initDTOFromEntity(save(new Monitor(dto), monitorDAO));
    }

    @Override
    public CaseDTO saveCase(final CaseDTO dto) {
        return CaseDTO.initDTOFromEntity(save(new aCase(dto), caseDAO));
    }

    @Override
    public CoolerDTO saveCooler(final CoolerDTO dto) {
        return CoolerDTO.initDTOFromEntity(save(new Cooler(dto), coolerDAO));
    }

    @Override
    public CPUCoolerDTO saveCpuCooler(final CPUCoolerDTO dto) {
        return CPUCoolerDTO.initDTOFromEntity(save(new CPUCooler(dto), cpuCoolerDAO));
    }

    @Override
    public CPUDTO saveCPU(final CPUDTO dto) {
        return CPUDTO.initDTOFromEntity(save(new CPU(dto), cpuDAO));
    }

    @Override
    public InternalHardDriveDTO saveInternalHardDrive(final InternalHardDriveDTO dto) {
        return InternalHardDriveDTO.initDTOFromEntity(save(new InternalHardDrive(dto), internalHardDriveDAO));
    }

    @Override
    public GPUDTO saveGPU(final GPUDTO dto) {
        return GPUDTO.initDTOFromEntity(save(new GPU(dto), gpuDAO));
    }

    @Override
    public ExternalHardDriveDTO saveExternalHardDrive(final ExternalHardDriveDTO dto) {
        return ExternalHardDriveDTO.initDTOFromEntity(save(new ExternalHardDrive(dto), externalHardDriveDAO));
    }

    @Override
    public MotherboardDTO saveMotherboard(final MotherboardDTO dto) {
        return MotherboardDTO.initDTOFromEntity(save(new Motherboard(dto), motherboardDAO));
    }

    @Override
    public PowerSupplyDTO savePowerSupply(final PowerSupplyDTO dto) {
        return PowerSupplyDTO.initDTOFromEntity(save(new PowerSupply(dto), powerSupplyDAO));
    }

    @Override
    public RAMDTO saveRAM(final RAMDTO dto) {
        return RAMDTO.initDTOFromEntity(save(new RAM(dto), ramDAO));
    }

    @Override
    public HeadsetDTO saveHeadset(final HeadsetDTO dto) {
        return HeadsetDTO.initDTOFromEntity(save(new Headset(dto), headsetDAO));
    }

    @Override
    public KeyboardDTO saveKeyboard(final KeyboardDTO dto) {
        return KeyboardDTO.initDTOFromEntity(save(new Keyboard(dto), keyboardDAO));
    }

    @Override
    public MouseDTO saveMouse(final MouseDTO dto) {
        return MouseDTO.initDTOFromEntity(save(new Mouse(dto), mouseDAO));
    }

    @Override
    public SpeakerDTO saveSpeaker(final SpeakerDTO dto) {
        return SpeakerDTO.initDTOFromEntity(save(new Speaker(dto), speakerDAO));
    }

    @Override
    public MonitorDTO findMonitorByName(final String name) {
        return MonitorDTO.initDTOFromEntity(findByName(name, monitorDAO));
    }

    @Override
    public List<MonitorDTO> findMonitorByPrice(final Double minPrice, final Double maxPrice) {
        return findAllByPrice(minPrice, maxPrice, monitorDAO)
                .stream()
                .map(MonitorDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public List<MonitorDTO> findMonitorByPurchasedPrice(final Double minPurchasedPrice, final Double maxPurchasedPrice) {
        return findAllByPurchasedPrice(minPurchasedPrice, maxPurchasedPrice, monitorDAO)
                .stream()
                .map(MonitorDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public CaseDTO findCaseByName(final String name) {
        return CaseDTO.initDTOFromEntity(findByName(name, caseDAO));
    }

    @Override
    public List<CaseDTO> findCaseByPrice(final Double minPrice, final Double maxPrice) {
        return findAllByPrice(minPrice, maxPrice, caseDAO)
                .stream()
                .map(CaseDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public List<CaseDTO> findCaseByPurchasedPrice(final Double minPurchasedPrice, final Double maxPurchasedPrice) {
        return findAllByPurchasedPrice(minPurchasedPrice, maxPurchasedPrice, caseDAO)
                .stream()
                .map(CaseDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public CoolerDTO findCoolerByName(final String name) {
        return CoolerDTO.initDTOFromEntity(findByName(name, coolerDAO));
    }

    @Override
    public List<CoolerDTO> findCoolerByPrice(final Double minPrice, final Double maxPrice) {
        return findAllByPrice(minPrice, maxPrice, coolerDAO)
                .stream()
                .map(CoolerDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public List<CoolerDTO> findCoolerByPurchasedPrice(final Double minPurchasedPrice, final Double maxPurchasedPrice) {
        return findAllByPurchasedPrice(minPurchasedPrice, maxPurchasedPrice, coolerDAO)
                .stream()
                .map(CoolerDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public CPUCoolerDTO findCPUCoolerByName(final String name) {
        return CPUCoolerDTO.initDTOFromEntity(findByName(name, cpuCoolerDAO));
    }

    @Override
    public List<CPUCoolerDTO> findCPUCoolerByPrice(final Double minPrice, final Double maxPrice) {
        return findAllByPrice(minPrice, maxPrice, cpuCoolerDAO)
                .stream()
                .map(CPUCoolerDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public List<CPUCoolerDTO> findCPUCoolerByPurchasedPrice(final Double minPurchasedPrice, final Double maxPurchasedPrice) {
        return findAllByPurchasedPrice(minPurchasedPrice, maxPurchasedPrice, cpuCoolerDAO)
                .stream()
                .map(CPUCoolerDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public CPUDTO findCPUByName(final String name) {
        return CPUDTO.initDTOFromEntity(findByName(name, cpuDAO));
    }

    @Override
    public List<CPUDTO> findCPUByPrice(final Double minPrice, final Double maxPrice) {
        return findAllByPrice(minPrice, maxPrice, cpuDAO)
                .stream()
                .map(CPUDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public List<CPUDTO> findCPUByPurchasedPrice(final Double minPurchasedPrice, final Double maxPurchasedPrice) {
        return findAllByPurchasedPrice(minPurchasedPrice, maxPurchasedPrice, cpuDAO)
                .stream()
                .map(CPUDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public InternalHardDriveDTO findInternalHardDriveByName(final String name) {
        return InternalHardDriveDTO.initDTOFromEntity(findByName(name, internalHardDriveDAO));
    }

    @Override
    public List<InternalHardDriveDTO> findInternalHardDriveByPrice(final Double minPrice, final Double maxPrice) {
        return findAllByPrice(minPrice, maxPrice, internalHardDriveDAO)
                .stream()
                .map(InternalHardDriveDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public List<InternalHardDriveDTO> findInternalHardDriveByPurchasedPrice(final Double minPurchasedPrice, final Double maxPurchasedPrice) {
        return findAllByPurchasedPrice(minPurchasedPrice, maxPurchasedPrice, internalHardDriveDAO)
                .stream()
                .map(InternalHardDriveDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public GPUDTO findGPUByName(final String name) {
        return GPUDTO.initDTOFromEntity(findByName(name, gpuDAO));
    }

    @Override
    public List<GPUDTO> findGPUByPrice(final Double minPrice, final Double maxPrice) {
        return findAllByPrice(minPrice, maxPrice, gpuDAO)
                .stream()
                .map(GPUDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public List<GPUDTO> findGPUByPurchasedPrice(final Double minPurchasedPrice, final Double maxPurchasedPrice) {
        return findAllByPurchasedPrice(minPurchasedPrice, maxPurchasedPrice, gpuDAO)
                .stream()
                .map(GPUDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public ExternalHardDriveDTO findExternalHardDriveByName(final String name) {
        return ExternalHardDriveDTO.initDTOFromEntity(findByName(name, externalHardDriveDAO));
    }

    @Override
    public List<ExternalHardDriveDTO> findExternalHardDriveByPrice(final Double minPrice, final Double maxPrice) {
        return findAllByPrice(minPrice, maxPrice, externalHardDriveDAO)
                .stream()
                .map(ExternalHardDriveDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public List<ExternalHardDriveDTO> findExternalHardDriveByPurchasedPrice(final Double minPurchasedPrice, final Double maxPurchasedPrice) {
        return findAllByPurchasedPrice(minPurchasedPrice, maxPurchasedPrice, externalHardDriveDAO)
                .stream()
                .map(ExternalHardDriveDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public MotherboardDTO findMotherboardByName(final String name) {
        return MotherboardDTO.initDTOFromEntity(findByName(name, motherboardDAO));
    }

    @Override
    public List<MotherboardDTO> findMotherboardByPrice(final Double minPrice, final Double maxPrice) {
        return findAllByPrice(minPrice, maxPrice, motherboardDAO)
                .stream()
                .map(MotherboardDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public List<MotherboardDTO> findMotherboardByPurchasedPrice(final Double minPurchasedPrice, final Double maxPurchasedPrice) {
        return findAllByPurchasedPrice(minPurchasedPrice, maxPurchasedPrice, motherboardDAO)
                .stream()
                .map(MotherboardDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public PowerSupplyDTO findPowerSupplyByName(final String name) {
        return PowerSupplyDTO.initDTOFromEntity(findByName(name, powerSupplyDAO));
    }

    @Override
    public List<PowerSupplyDTO> findPowerSupplyByPrice(final Double minPrice, final Double maxPrice) {
        return findAllByPrice(minPrice, maxPrice, powerSupplyDAO)
                .stream()
                .map(PowerSupplyDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public List<PowerSupplyDTO> findPowerSupplyByPurchasedPrice(final Double minPurchasedPrice, final Double maxPurchasedPrice) {
        return findAllByPurchasedPrice(minPurchasedPrice, maxPurchasedPrice, powerSupplyDAO)
                .stream()
                .map(PowerSupplyDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public RAMDTO findRAMByName(final String name) {
        return RAMDTO.initDTOFromEntity(findByName(name, ramDAO));
    }

    @Override
    public List<RAMDTO> findRAMByPrice(final Double minPrice, final Double maxPrice) {
        return findAllByPrice(minPrice, maxPrice, ramDAO)
                .stream()
                .map(RAMDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public List<RAMDTO> findRAMByPurchasedPrice(final Double minPurchasedPrice, final Double maxPurchasedPrice) {
        return findAllByPurchasedPrice(minPurchasedPrice, maxPurchasedPrice, ramDAO)
                .stream()
                .map(RAMDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public HeadsetDTO findHeadsetByName(final String name) {
        return HeadsetDTO.initDTOFromEntity(findByName(name, headsetDAO));
    }

    @Override
    public List<HeadsetDTO> findHeadsetByPrice(final Double minPrice, final Double maxPrice) {
        return findAllByPrice(minPrice, maxPrice, headsetDAO)
                .stream()
                .map(HeadsetDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public List<HeadsetDTO> findHeadsetByPurchasedPrice(final Double minPurchasedPrice, final Double maxPurchasedPrice) {
        return findAllByPurchasedPrice(minPurchasedPrice, maxPurchasedPrice, headsetDAO)
                .stream()
                .map(HeadsetDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public KeyboardDTO findKeyboardByName(final String name) {
        return KeyboardDTO.initDTOFromEntity(findByName(name, keyboardDAO));
    }

    @Override
    public List<KeyboardDTO> findKeyboardByPrice(final Double minPrice, final Double maxPrice) {
        return findAllByPrice(minPrice, maxPrice, keyboardDAO)
                .stream()
                .map(KeyboardDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public List<KeyboardDTO> findKeyboardByPurchasedPrice(final Double minPurchasedPrice, final Double maxPurchasedPrice) {
        return findAllByPurchasedPrice(minPurchasedPrice, maxPurchasedPrice, keyboardDAO)
                .stream()
                .map(KeyboardDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public MouseDTO findMouseByName(final String name) {
        return MouseDTO.initDTOFromEntity(findByName(name, mouseDAO));
    }

    @Override
    public List<MouseDTO> findMouseByPrice(final Double minPrice, final Double maxPrice) {
        return findAllByPrice(minPrice, maxPrice, mouseDAO)
                .stream()
                .map(MouseDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public List<MouseDTO> findMouseByPurchasedPrice(final Double minPurchasedPrice, final Double maxPurchasedPrice) {
        return findAllByPurchasedPrice(minPurchasedPrice, maxPurchasedPrice, mouseDAO)
                .stream()
                .map(MouseDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public SpeakerDTO findSpeakerByName(final String name) {
        return SpeakerDTO.initDTOFromEntity(findByName(name, speakerDAO));
    }

    @Override
    public List<SpeakerDTO> findSpeakerByPrice(final Double minPrice, final Double maxPrice) {
        return findAllByPrice(minPrice, maxPrice, speakerDAO)
                .stream()
                .map(SpeakerDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public List<SpeakerDTO> findSpeakerByPurchasedPrice(final Double minPurchasedPrice, final Double maxPurchasedPrice) {
        return findAllByPurchasedPrice(minPurchasedPrice, maxPurchasedPrice, speakerDAO)
                .stream()
                .map(SpeakerDTO::initDTOFromEntity)
                .toList();
    }


    @Override
    public MonitorDTO updateMonitorPriceByName(final String name, final Double newPrice) {
        return MonitorDTO.initDTOFromEntity(updatePriceByName(name, newPrice, monitorDAO));
    }

    @Override
    public CaseDTO updateCasePriceByName(final String name, final Double newPrice) {
        return CaseDTO.initDTOFromEntity(updatePriceByName(name, newPrice, caseDAO));
    }

    @Override
    public CoolerDTO updateCoolerPriceByName(final String name, final Double newPrice) {
        return CoolerDTO.initDTOFromEntity(updatePriceByName(name, newPrice, coolerDAO));
    }

    @Override
    public CPUDTO updateCPUPriceByName(final String name, final Double newPrice) {
        return CPUDTO.initDTOFromEntity(updatePriceByName(name, newPrice, cpuDAO));
    }

    @Override
    public CPUCoolerDTO updateCPUCoolerPriceByName(final String name, final Double newPrice) {
        return CPUCoolerDTO.initDTOFromEntity(updatePriceByName(name, newPrice, cpuCoolerDAO));
    }

    @Override
    public InternalHardDriveDTO updateInternalHardDrivePriceByName(final String name, final Double newPrice) {
        return InternalHardDriveDTO.initDTOFromEntity(updatePriceByName(name, newPrice, internalHardDriveDAO));
    }

    @Override
    public GPUDTO updateGPUPriceByName(final String name, final Double newPrice) {
        return GPUDTO.initDTOFromEntity(updatePriceByName(name, newPrice, gpuDAO));
    }

    @Override
    public ExternalHardDriveDTO updateExternalHardDrivePriceByName(final String name, final Double newPrice) {
        return ExternalHardDriveDTO.initDTOFromEntity(updatePriceByName(name, newPrice, externalHardDriveDAO));
    }

    @Override
    public MotherboardDTO updateMotherboardPriceByName(final String name, final Double newPrice) {
        return MotherboardDTO.initDTOFromEntity(updatePriceByName(name, newPrice, motherboardDAO));
    }

    @Override
    public PowerSupplyDTO updatePowerSupplyPriceByName(final String name, final Double newPrice) {
        return PowerSupplyDTO.initDTOFromEntity(updatePriceByName(name, newPrice, powerSupplyDAO));
    }

    @Override
    public RAMDTO updateRAMPriceByName(final String name, final Double newPrice) {
        return RAMDTO.initDTOFromEntity(updatePriceByName(name, newPrice, ramDAO));
    }

    @Override
    public HeadsetDTO updateHeadsetPriceByName(final String name, final Double newPrice) {
        return HeadsetDTO.initDTOFromEntity(updatePriceByName(name, newPrice, headsetDAO));
    }

    @Override
    public KeyboardDTO updateKeyboardPriceByName(final String name, final Double newPrice) {
        return KeyboardDTO.initDTOFromEntity(updatePriceByName(name, newPrice, keyboardDAO));
    }

    @Override
    public MouseDTO updateMousePriceByName(final String name, final Double newPrice) {
        return MouseDTO.initDTOFromEntity(updatePriceByName(name, newPrice, mouseDAO));
    }

    @Override
    public SpeakerDTO updateSpeakerPriceByName(final String name, final Double newPrice) {
        return SpeakerDTO.initDTOFromEntity(updatePriceByName(name, newPrice, speakerDAO));
    }

    @Override
    public MonitorDTO reduceMonitorCountByName(final String name, final Integer countToBeReduced) {
        return MonitorDTO.initDTOFromEntity(reduceCountByName(name, countToBeReduced, monitorDAO));
    }

    @Override
    public CaseDTO reduceCaseCountByName(final String name, final Integer countToBeReduced) {
        return CaseDTO.initDTOFromEntity(reduceCountByName(name, countToBeReduced, caseDAO));
    }

    @Override
    public CoolerDTO reduceCoolerCountByName(final String name, final Integer countToBeReduced) {
        return CoolerDTO.initDTOFromEntity(reduceCountByName(name, countToBeReduced, coolerDAO));
    }

    @Override
    public CPUDTO reduceCPUCountByName(final String name, final Integer countToBeReduced) {
        return CPUDTO.initDTOFromEntity(reduceCountByName(name, countToBeReduced, cpuDAO));
    }

    @Override
    public CPUCoolerDTO reduceCPUCoolerCountByName(final String name, final Integer countToBeReduced) {
        return CPUCoolerDTO.initDTOFromEntity(reduceCountByName(name, countToBeReduced, cpuCoolerDAO));
    }

    @Override
    public InternalHardDriveDTO reduceInternalHardDriveCountByName(final String name, final Integer countToBeReduced) {
        return InternalHardDriveDTO.initDTOFromEntity(reduceCountByName(name, countToBeReduced, internalHardDriveDAO));
    }

    @Override
    public GPUDTO reduceGPUCountByName(final String name, final Integer countToBeReduced) {
        return GPUDTO.initDTOFromEntity(reduceCountByName(name, countToBeReduced, gpuDAO));
    }

    @Override
    public ExternalHardDriveDTO reduceExternalHardDriveCountByName(final String name, final Integer countToBeReduced) {
        return ExternalHardDriveDTO.initDTOFromEntity(reduceCountByName(name, countToBeReduced, externalHardDriveDAO));
    }

    @Override
    public MotherboardDTO reduceMotherboardCountByName(final String name, final Integer countToBeReduced) {
        return MotherboardDTO.initDTOFromEntity(reduceCountByName(name, countToBeReduced, motherboardDAO));
    }

    @Override
    public PowerSupplyDTO reducePowerSupplyCountByName(final String name, final Integer countToBeReduced) {
        return PowerSupplyDTO.initDTOFromEntity(reduceCountByName(name, countToBeReduced, powerSupplyDAO));
    }

    @Override
    public RAMDTO reduceRAMCountByName(final String name, final Integer countToBeReduced) {
        return RAMDTO.initDTOFromEntity(reduceCountByName(name, countToBeReduced, ramDAO));
    }

    @Override
    public HeadsetDTO reduceHeadsetCountByName(final String name, final Integer countToBeReduced) {
        return HeadsetDTO.initDTOFromEntity(reduceCountByName(name, countToBeReduced, headsetDAO));
    }

    @Override
    public KeyboardDTO reduceKeyboardCountByName(final String name, final Integer countToBeReduced) {
        return KeyboardDTO.initDTOFromEntity(reduceCountByName(name, countToBeReduced, keyboardDAO));
    }

    @Override
    public MouseDTO reduceMouseCountByName(final String name, final Integer countToBeReduced) {
        return MouseDTO.initDTOFromEntity(reduceCountByName(name, countToBeReduced, mouseDAO));
    }

    @Override
    public SpeakerDTO reduceSpeakerCountByName(final String name, final Integer countToBeReduced) {
        return SpeakerDTO.initDTOFromEntity(reduceCountByName(name, countToBeReduced, speakerDAO));
    }
}