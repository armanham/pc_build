package com.bdg.pc_build.product.service;

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

    private <ENTITY extends Product> void updatePriceById(
            final Long id,
            final Double newPrice,
            final ProductDAO<ENTITY> repository
    ) {
        Optional<ENTITY> optionalENTITY = repository.findById(id);
        if (optionalENTITY.isEmpty()) {
            throw new IllegalArgumentException(); //todo
        }
        ENTITY foundedProduct = optionalENTITY.get();
        foundedProduct.setPrice(newPrice);
        repository.save(foundedProduct);
    }

    private <ENTITY extends Product> void reduceCountById(
            final Long id,
            final Integer countToBeReduced,
            final ProductDAO<ENTITY> repository
    ) {
        Optional<ENTITY> optionalENTITY = repository.findById(id);
        if (optionalENTITY.isEmpty()) {
            throw new IllegalArgumentException(); //todo
        }

        ENTITY foundedProduct = optionalENTITY.get();

        if (foundedProduct.getCount() < countToBeReduced) {
            throw new IllegalArgumentException(); //todo
        }
        foundedProduct.setCount(foundedProduct.getCount() - countToBeReduced);
        repository.save(foundedProduct);
    }

    private <ENTITY extends Product> List<ENTITY> findAll(final ProductDAO<ENTITY> dao) {
        return dao.findAll();
    }

    private <ENTITY extends Product> ENTITY findById(final Long id, final ProductDAO<ENTITY> dao) {
        Optional<ENTITY> result = dao.findById(id);
        return result.orElseThrow(() -> new ProductNotFoundException("aaa"));
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
    public void updatePriceById(final Long id, final Double newPrice) {
        if (id >= 1 && id <= 300) {
            updatePriceById(id, newPrice, caseDAO);
        }
        if (id >= 301 && id <= 600) {
            updatePriceById(id, newPrice, coolerDAO);
        }
        if (id >= 601 && id <= 900) {
            updatePriceById(id, newPrice, cpuDAO);
        }
        if (id >= 901 && id <= 1200) {
            updatePriceById(id, newPrice, cpuCoolerDAO);
        }
        if (id >= 1201 && id <= 1500) {
            updatePriceById(id, newPrice, gpuDAO);
        }
        if (id >= 1501 && id <= 1800) {
            updatePriceById(id, newPrice, internalHardDriveDAO);
        }
        if (id >= 1801 && id <= 2100) {
            updatePriceById(id, newPrice, motherboardDAO);
        }
        if (id >= 2101 && id <= 2400) {
            updatePriceById(id, newPrice, powerSupplyDAO);
        }
        if (id >= 2401 && id <= 2700) {
            updatePriceById(id, newPrice, ramDAO);
        }
        if (id >= 2701 && id <= 3000) {
            updatePriceById(id, newPrice, externalHardDriveDAO);
        }
        if (id >= 3001 && id <= 3300) {
            updatePriceById(id, newPrice, headsetDAO);
        }
        if (id >= 3301 && id <= 3600) {
            updatePriceById(id, newPrice, keyboardDAO);
        }
        if (id >= 3601 && id <= 3900) {
            updatePriceById(id, newPrice, monitorDAO);
        }
        if (id >= 3901 && id <= 4200) {
            updatePriceById(id, newPrice, mouseDAO);
        }
        if (id >= 4201 && id <= 4500) {
            updatePriceById(id, newPrice, speakerDAO);
        }
        throw new ProductNotFoundException("aaaaa"); // todo
    }

    @Override
    public void reduceCountById(final Long id, final Integer countToBeReduced) {
        if (id >= 1 && id <= 300) {
            reduceCountById(id, countToBeReduced, caseDAO);
        }
        if (id >= 301 && id <= 600) {
            reduceCountById(id, countToBeReduced, coolerDAO);
        }
        if (id >= 601 && id <= 900) {
            reduceCountById(id, countToBeReduced, cpuDAO);
        }
        if (id >= 901 && id <= 1200) {
            reduceCountById(id, countToBeReduced, cpuCoolerDAO);
        }
        if (id >= 1201 && id <= 1500) {
            reduceCountById(id, countToBeReduced, gpuDAO);
        }
        if (id >= 1501 && id <= 1800) {
            reduceCountById(id, countToBeReduced, internalHardDriveDAO);
        }
        if (id >= 1801 && id <= 2100) {
            reduceCountById(id, countToBeReduced, motherboardDAO);
        }
        if (id >= 2101 && id <= 2400) {
            reduceCountById(id, countToBeReduced, powerSupplyDAO);
        }
        if (id >= 2401 && id <= 2700) {
            reduceCountById(id, countToBeReduced, ramDAO);
        }
        if (id >= 2701 && id <= 3000) {
            reduceCountById(id, countToBeReduced, externalHardDriveDAO);
        }
        if (id >= 3001 && id <= 3300) {
            reduceCountById(id, countToBeReduced, headsetDAO);
        }
        if (id >= 3301 && id <= 3600) {
            reduceCountById(id, countToBeReduced, keyboardDAO);
        }
        if (id >= 3601 && id <= 3900) {
            reduceCountById(id, countToBeReduced, monitorDAO);
        }
        if (id >= 3901 && id <= 4200) {
            reduceCountById(id, countToBeReduced, mouseDAO);
        }
        if (id >= 4201 && id <= 4500) {
            reduceCountById(id, countToBeReduced, speakerDAO);
        }
        throw new ProductNotFoundException("aaaaa"); // todo
    }

    public ProductDTO findById(final Long id) {
        if (id >= 1 && id <= 300) {
            return CaseDTO.initDTOFromEntity(findById(id, caseDAO));
        }
        if (id >= 301 && id <= 600) {
            return CoolerDTO.initDTOFromEntity(findById(id, coolerDAO));
        }
        if (id >= 601 && id <= 900) {
            return CPUDTO.initDTOFromEntity(findById(id, cpuDAO));
        }
        if (id >= 901 && id <= 1200) {
            return CPUCoolerDTO.initDTOFromEntity(findById(id, cpuCoolerDAO));
        }
        if (id >= 1201 && id <= 1500) {
            return GPUDTO.initDTOFromEntity(findById(id, gpuDAO));
        }
        if (id >= 1501 && id <= 1800) {
            return InternalHardDriveDTO.initDTOFromEntity(findById(id, internalHardDriveDAO));
        }
        if (id >= 1801 && id <= 2100) {
            return MotherboardDTO.initDTOFromEntity(findById(id, motherboardDAO));
        }
        if (id >= 2101 && id <= 2400) {
            return PowerSupplyDTO.initDTOFromEntity(findById(id, powerSupplyDAO));
        }
        if (id >= 2401 && id <= 2700) {
            return RAMDTO.initDTOFromEntity(findById(id, ramDAO));
        }
        if (id >= 2701 && id <= 3000) {
            return ExternalHardDriveDTO.initDTOFromEntity(findById(id, externalHardDriveDAO));
        }
        if (id >= 3001 && id <= 3300) {
            return HeadsetDTO.initDTOFromEntity(findById(id, headsetDAO));
        }
        if (id >= 3301 && id <= 3600) {
            return KeyboardDTO.initDTOFromEntity(findById(id, keyboardDAO));
        }
        if (id >= 3601 && id <= 3900) {
            return MonitorDTO.initDTOFromEntity(findById(id, monitorDAO));
        }
        if (id >= 3901 && id <= 4200) {
            return MouseDTO.initDTOFromEntity(findById(id, mouseDAO));
        }
        if (id >= 4201 && id <= 4500) {
            return SpeakerDTO.initDTOFromEntity(findById(id, speakerDAO));
        }
        throw new ProductNotFoundException("aaaaa"); //todo
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