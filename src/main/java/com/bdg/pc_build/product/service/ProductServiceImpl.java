package com.bdg.pc_build.product.service;

import com.bdg.pc_build.exception.ApranqyQichAException;
import com.bdg.pc_build.exception.ProductNotFoundException;
import com.bdg.pc_build.exception.SameNameDifferentDescriptionException;
import com.bdg.pc_build.product.model.dto.ProductDTO;
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

    private <ENTITY extends Product> List<ENTITY> findAll(final ProductDAO<ENTITY> dao) {
        return dao.findAll();
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
    public List<MonitorDTO> findMonitorByPurchasedPrice(final Double minPurchasedPrice, final Double maxPurchasedPrice) {
        return findAllByPurchasedPrice(minPurchasedPrice, maxPurchasedPrice, monitorDAO)
                .stream()
                .map(MonitorDTO::initDTOFromEntity)
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
    public List<CoolerDTO> findCoolerByPurchasedPrice(final Double minPurchasedPrice, final Double maxPurchasedPrice) {
        return findAllByPurchasedPrice(minPurchasedPrice, maxPurchasedPrice, coolerDAO)
                .stream()
                .map(CoolerDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public List<CPUCoolerDTO> findCpuCoolerByPurchasedPrice(final Double minPurchasedPrice, final Double maxPurchasedPrice) {
        return findAllByPurchasedPrice(minPurchasedPrice, maxPurchasedPrice, cpuCoolerDAO)
                .stream()
                .map(CPUCoolerDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public List<CPUDTO> findCpuByPurchasedPrice(final Double minPurchasedPrice, final Double maxPurchasedPrice) {
        return findAllByPurchasedPrice(minPurchasedPrice, maxPurchasedPrice, cpuDAO)
                .stream()
                .map(CPUDTO::initDTOFromEntity)
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
    public List<GPUDTO> findGpuByPurchasedPrice(final Double minPurchasedPrice, final Double maxPurchasedPrice) {
        return findAllByPurchasedPrice(minPurchasedPrice, maxPurchasedPrice, gpuDAO)
                .stream()
                .map(GPUDTO::initDTOFromEntity)
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
    public List<MotherboardDTO> findMotherboardByPurchasedPrice(final Double minPurchasedPrice, final Double maxPurchasedPrice) {
        return findAllByPurchasedPrice(minPurchasedPrice, maxPurchasedPrice, motherboardDAO)
                .stream()
                .map(MotherboardDTO::initDTOFromEntity)
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
    public List<RAMDTO> findRamByPurchasedPrice(final Double minPurchasedPrice, final Double maxPurchasedPrice) {
        return findAllByPurchasedPrice(minPurchasedPrice, maxPurchasedPrice, ramDAO)
                .stream()
                .map(RAMDTO::initDTOFromEntity)
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
    public List<KeyboardDTO> findKeyboardByPurchasedPrice(final Double minPurchasedPrice, final Double maxPurchasedPrice) {
        return findAllByPurchasedPrice(minPurchasedPrice, maxPurchasedPrice, keyboardDAO)
                .stream()
                .map(KeyboardDTO::initDTOFromEntity)
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
    public CPUDTO updateCpuPriceByName(final String name, final Double newPrice) {
        return CPUDTO.initDTOFromEntity(updatePriceByName(name, newPrice, cpuDAO));
    }

    @Override
    public CPUCoolerDTO updateCpuCoolerPriceByName(final String name, final Double newPrice) {
        return CPUCoolerDTO.initDTOFromEntity(updatePriceByName(name, newPrice, cpuCoolerDAO));
    }

    @Override
    public InternalHardDriveDTO updateInternalHardDrivePriceByName(final String name, final Double newPrice) {
        return InternalHardDriveDTO.initDTOFromEntity(updatePriceByName(name, newPrice, internalHardDriveDAO));
    }

    @Override
    public GPUDTO updateGpuPriceByName(final String name, final Double newPrice) {
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
    public RAMDTO updateRamPriceByName(final String name, final Double newPrice) {
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

    private <ENTITY extends Product> ENTITY findProductById(Long id, ProductDAO<ENTITY> dao) {
        Optional<ENTITY> result = dao.findById(id);
        return result.orElseThrow(() -> new ProductNotFoundException("aaa"));
    }

    public ProductDTO findById(final Long id) {
        if (id >= 1 && id <= 300) {
            return CaseDTO.initDTOFromEntity(findProductById(id, caseDAO));
        }
        if (id >= 301 && id <= 600) {
            return CoolerDTO.initDTOFromEntity(findProductById(id, coolerDAO));
        }
        if (id >= 601 && id <= 900) {
            return CPUDTO.initDTOFromEntity(findProductById(id, cpuDAO));
        }
        if (id >= 901 && id <= 1200) {
            return CPUCoolerDTO.initDTOFromEntity(findProductById(id, cpuCoolerDAO));
        }
        if (id >= 1201 && id <= 1500) {
            return GPUDTO.initDTOFromEntity(findProductById(id, gpuDAO));
        }
        if (id >= 1501 && id <= 1800) {
            return InternalHardDriveDTO.initDTOFromEntity(findProductById(id, internalHardDriveDAO));
        }
        if (id >= 1801 && id <= 2100) {
            return MotherboardDTO.initDTOFromEntity(findProductById(id, motherboardDAO));
        }
        if (id >= 2101 && id <= 2400) {
            return PowerSupplyDTO.initDTOFromEntity(findProductById(id, powerSupplyDAO));
        }
        if (id >= 2401 && id <= 2700) {
            return RAMDTO.initDTOFromEntity(findProductById(id, ramDAO));
        }
        if (id >= 2701 && id <= 3000) {
            return ExternalHardDriveDTO.initDTOFromEntity(findProductById(id, externalHardDriveDAO));
        }
        if (id >= 3001 && id <= 3300) {
            return HeadsetDTO.initDTOFromEntity(findProductById(id, headsetDAO));
        }
        if (id >= 3301 && id <= 3600) {
            return KeyboardDTO.initDTOFromEntity(findProductById(id, keyboardDAO));
        }
        if (id >= 3601 && id <= 3900) {
            return MonitorDTO.initDTOFromEntity(findProductById(id, monitorDAO));
        }
        if (id >= 3901 && id <= 4200) {
            return MouseDTO.initDTOFromEntity(findProductById(id, mouseDAO));
        }
        if (id >= 4201 && id <= 4500) {
            return SpeakerDTO.initDTOFromEntity(findProductById(id, speakerDAO));
        }
        throw new ProductNotFoundException("aaaaa");
    }

    @Override
    public List<CaseDTO> getAllCases() {
        return findAll(caseDAO).stream().map(CaseDTO::initDTOFromEntity).toList();
    }

    @Override
    public List<CoolerDTO> getAllCoolers() {
        return findAll(coolerDAO).stream().map(CoolerDTO::initDTOFromEntity).toList();
    }

    @Override
    public List<CPUCoolerDTO> getAllCpuCoolers() {
        return findAll(cpuCoolerDAO).stream().map(CPUCoolerDTO::initDTOFromEntity).toList();
    }

    @Override
    public List<CPUDTO> getAllCpus() {
        return findAll(cpuDAO).stream().map(CPUDTO::initDTOFromEntity).toList();
    }

    @Override
    public List<GPUDTO> getAllGpus() {
        return findAll(gpuDAO).stream().map(GPUDTO::initDTOFromEntity).toList();
    }

    @Override
    public List<InternalHardDriveDTO> getAllInternalHardDrives() {
        return findAll(internalHardDriveDAO).stream().map(InternalHardDriveDTO::initDTOFromEntity).toList();
    }

    @Override
    public List<MotherboardDTO> getAllMotherboards() {
        return findAll(motherboardDAO).stream().map(MotherboardDTO::initDTOFromEntity).toList();
    }

    @Override
    public List<PowerSupplyDTO> getAllPowerSupplies() {
        return findAll(powerSupplyDAO).stream().map(PowerSupplyDTO::initDTOFromEntity).toList();
    }

    @Override
    public List<RAMDTO> getAllRams() {
        return findAll(ramDAO).stream().map(RAMDTO::initDTOFromEntity).toList();
    }

    @Override
    public List<ExternalHardDriveDTO> getAllExternalHardDrives() {
        return findAll(externalHardDriveDAO).stream().map(ExternalHardDriveDTO::initDTOFromEntity).toList();
    }

    @Override
    public List<HeadsetDTO> getAllHeadsets() {
        return findAll(headsetDAO).stream().map(HeadsetDTO::initDTOFromEntity).toList();
    }

    @Override
    public List<KeyboardDTO> getAllKeyboards() {
        return findAll(keyboardDAO).stream().map(KeyboardDTO::initDTOFromEntity).toList();
    }

    @Override
    public List<MonitorDTO> getAllMonitors() {
        return findAll(monitorDAO).stream().map(MonitorDTO::initDTOFromEntity).toList();
    }

    @Override
    public List<MouseDTO> getAllMice() {
        return findAll(mouseDAO).stream().map(MouseDTO::initDTOFromEntity).toList();
    }

    @Override
    public List<SpeakerDTO> getAllSpeakers() {
        return findAll(speakerDAO).stream().map(SpeakerDTO::initDTOFromEntity).toList();
    }
}