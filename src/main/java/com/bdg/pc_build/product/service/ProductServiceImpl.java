package com.bdg.pc_build.product.service;

import com.bdg.pc_build.exception.NotEnoughInStockException;
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.bdg.pc_build.product.model.InitialAndFinalIdValues.*;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    //Main component repositories
    private final CaseDAO caseDAO;
    private final CoolerDAO coolerDAO;
    private final CPUCoolerDAO cpuCoolerDAO;
    private final CPUDAO cpuDAO;
    private final GPUDAO gpuDAO;
    private final InternalHardDriveDAO internalHardDriveDAO;
    private final MotherboardDAO motherboardDAO;
    private final PowerSupplyDAO powerSupplyDAO;
    private final RAMDAO ramDAO;

    //Peripheral repositories
    private final ExternalHardDriveDAO externalHardDriveDAO;
    private final MonitorDAO monitorDAO;
    private final HeadsetDAO headsetDAO;
    private final KeyboardDAO keyboardDAO;
    private final MouseDAO mouseDAO;
    private final SpeakerDAO speakerDAO;


    private <ENTITY extends Product> ENTITY save(
            final ENTITY product,
            final ProductDAO<ENTITY> repository
    ) {
        Optional<ENTITY> optionalENTITY = repository.findByName(product.getName());
        if (optionalENTITY.isPresent()) {
            ENTITY foundedProduct = optionalENTITY.get();
            if (!foundedProduct.equals(product)) {
                throw new SameNameDifferentDescriptionException(foundedProduct.getClass(), foundedProduct.getName());
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
            throw new ProductNotFoundException(id); //todo
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
            throw new ProductNotFoundException(id); //todo
        }

        ENTITY foundedProduct = optionalENTITY.get();

        if (foundedProduct.getCount() < countToBeReduced) {
            throw new NotEnoughInStockException(foundedProduct.getClass(), foundedProduct.getName(), foundedProduct.getCount()); //todo
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
        Monitor toSave = save(new Monitor(dto), monitorDAO);
        if (toSave.getId() < INITIAL_ID_VALUE_MONITOR || toSave.getId() > FINAL_ID_VALUE_MONITOR) {
            throw new IllegalArgumentException(); //todo
        }
        return MonitorDTO.initDTOFromEntity(toSave);
    }

    @Override
    public CaseDTO saveCase(final CaseDTO dto) {
        aCase toSave = save(new aCase(dto), caseDAO);
        if (toSave.getId() < INITIAL_ID_VALUE_CASE || toSave.getId() > FINAL_ID_VALUE_CASE) {
            throw new IllegalArgumentException(); //todo
        }
        return CaseDTO.initDTOFromEntity(toSave);
    }

    @Override
    public CoolerDTO saveCooler(final CoolerDTO dto) {
        Cooler toSave = save(new Cooler(dto), coolerDAO);
        if (toSave.getId() < INITIAL_ID_VALUE_COOLER || toSave.getId() > FINAL_ID_VALUE_COOLER) {
            throw new IllegalArgumentException(); //todo
        }
        return CoolerDTO.initDTOFromEntity(toSave);
    }

    @Override
    public CPUCoolerDTO saveCpuCooler(final CPUCoolerDTO dto) {
        CPUCooler toSave = save(new CPUCooler(dto), cpuCoolerDAO);
        if (toSave.getId() < INITIAL_ID_VALUE_CPU_COOLER || toSave.getId() > FINAL_ID_VALUE_CPU_COOLER) {
            throw new IllegalArgumentException(); //todo
        }
        return CPUCoolerDTO.initDTOFromEntity(toSave);
    }

    @Override
    public CPUDTO saveCPU(final CPUDTO dto) {
        CPU toSave = save(new CPU(dto), cpuDAO);
        if (toSave.getId() < INITIAL_ID_VALUE_CPU || toSave.getId() > FINAL_ID_VALUE_CPU) {
            throw new IllegalArgumentException(); //todo
        }
        return CPUDTO.initDTOFromEntity(toSave);
    }

    @Override
    public InternalHardDriveDTO saveInternalHardDrive(final InternalHardDriveDTO dto) {
        InternalHardDrive toSave = save(new InternalHardDrive(dto), internalHardDriveDAO);
        if (toSave.getId() < INITIAL_ID_VALUE_INTERNAL_HARD_DRIVE || toSave.getId() > FINAL_ID_VALUE_INTERNAL_HARD_DRIVE) {
            throw new IllegalArgumentException(); //todo
        }
        return InternalHardDriveDTO.initDTOFromEntity(toSave);
    }

    @Override
    public GPUDTO saveGPU(final GPUDTO dto) {
        GPU toSave = save(new GPU(dto), gpuDAO);
        if (toSave.getId() < INITIAL_ID_VALUE_GPU || toSave.getId() > FINAL_ID_VALUE_GPU) {
            throw new IllegalArgumentException(); //todo
        }
        return GPUDTO.initDTOFromEntity(toSave);
    }

    @Override
    public ExternalHardDriveDTO saveExternalHardDrive(final ExternalHardDriveDTO dto) {
        ExternalHardDrive toSave = save(new ExternalHardDrive(dto), externalHardDriveDAO);
        if (toSave.getId() < INITIAL_ID_VALUE_EXTERNAL_HARD_DRIVE || toSave.getId() > FINAL_ID_VALUE_EXTERNAL_HARD_DRIVE) {
            throw new IllegalArgumentException(); //todo
        }
        return ExternalHardDriveDTO.initDTOFromEntity(toSave);
    }

    @Override
    public MotherboardDTO saveMotherboard(final MotherboardDTO dto) {
        Motherboard toSave = save(new Motherboard(dto), motherboardDAO);
        if (toSave.getId() < INITIAL_ID_VALUE_MOTHERBOARD || toSave.getId() > FINAL_ID_VALUE_MOTHERBOARD) {
            throw new IllegalArgumentException(); //todo
        }
        return MotherboardDTO.initDTOFromEntity(toSave);
    }

    @Override
    public PowerSupplyDTO savePowerSupply(final PowerSupplyDTO dto) {
        PowerSupply toSave = save(new PowerSupply(dto), powerSupplyDAO);
        if (toSave.getId() < INITIAL_ID_VALUE_POWER_SUPPLY || toSave.getId() > FINAL_ID_VALUE_POWER_SUPPLY) {
            throw new IllegalArgumentException(); //todo
        }
        return PowerSupplyDTO.initDTOFromEntity(toSave);
    }

    @Override
    public RAMDTO saveRAM(final RAMDTO dto) {
        RAM toSave = save(new RAM(dto), ramDAO);
        if (toSave.getId() < INITIAL_ID_VALUE_RAM || toSave.getId() > FINAL_ID_VALUE_RAM) {
            throw new IllegalArgumentException(); //todo
        }
        return RAMDTO.initDTOFromEntity(toSave);
    }

    @Override
    public HeadsetDTO saveHeadset(final HeadsetDTO dto) {
        Headset toSave = save(new Headset(dto), headsetDAO);
        if (toSave.getId() < INITIAL_ID_VALUE_HEADSET || toSave.getId() > FINAL_ID_VALUE_HEADSET) {
            throw new IllegalArgumentException(); //todo
        }
        return HeadsetDTO.initDTOFromEntity(toSave);
    }

    @Override
    public KeyboardDTO saveKeyboard(final KeyboardDTO dto) {
        Keyboard toSave = save(new Keyboard(dto), keyboardDAO);
        if (toSave.getId() < INITIAL_ID_VALUE_KEYBOARD || toSave.getId() > FINAL_ID_VALUE_KEYBOARD) {
            throw new IllegalArgumentException(); //todo
        }
        return KeyboardDTO.initDTOFromEntity(toSave);
    }

    @Override
    public MouseDTO saveMouse(final MouseDTO dto) {
        Mouse toSave = save(new Mouse(dto), mouseDAO);
        if (toSave.getId() < INITIAL_ID_VALUE_MOUSE || toSave.getId() > FINAL_ID_VALUE_MOUSE) {
            throw new IllegalArgumentException(); //todo
        }
        return MouseDTO.initDTOFromEntity(toSave);
    }

    @Override
    public SpeakerDTO saveSpeaker(final SpeakerDTO dto) {
        Speaker toSave = save(new Speaker(dto), speakerDAO);
        if (toSave.getId() < INITIAL_ID_VALUE_SPEAKER || toSave.getId() > FINAL_ID_VALUE_SPEAKER) {
            throw new IllegalArgumentException(); //todo
        }
        return SpeakerDTO.initDTOFromEntity(toSave);
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
        if (id >= INITIAL_ID_VALUE_CASE && id <= FINAL_ID_VALUE_CASE) {
            updatePriceById(id, newPrice, caseDAO);
        }
        if (id >= INITIAL_ID_VALUE_COOLER && id <= FINAL_ID_VALUE_COOLER) {
            updatePriceById(id, newPrice, coolerDAO);
        }
        if (id >= INITIAL_ID_VALUE_CPU && id <= FINAL_ID_VALUE_CPU) {
            updatePriceById(id, newPrice, cpuDAO);
        }
        if (id >= INITIAL_ID_VALUE_CPU_COOLER && id <= FINAL_ID_VALUE_CPU_COOLER) {
            updatePriceById(id, newPrice, cpuCoolerDAO);
        }
        if (id >= INITIAL_ID_VALUE_GPU && id <= FINAL_ID_VALUE_GPU) {
            updatePriceById(id, newPrice, gpuDAO);
        }
        if (id >= INITIAL_ID_VALUE_INTERNAL_HARD_DRIVE && id <= FINAL_ID_VALUE_INTERNAL_HARD_DRIVE) {
            updatePriceById(id, newPrice, internalHardDriveDAO);
        }
        if (id >= INITIAL_ID_VALUE_MOTHERBOARD && id <= FINAL_ID_VALUE_MOTHERBOARD) {
            updatePriceById(id, newPrice, motherboardDAO);
        }
        if (id >= INITIAL_ID_VALUE_POWER_SUPPLY && id <= FINAL_ID_VALUE_POWER_SUPPLY) {
            updatePriceById(id, newPrice, powerSupplyDAO);
        }
        if (id >= INITIAL_ID_VALUE_RAM && id <= FINAL_ID_VALUE_RAM) {
            updatePriceById(id, newPrice, ramDAO);
        }
        if (id >= INITIAL_ID_VALUE_EXTERNAL_HARD_DRIVE && id <= FINAL_ID_VALUE_EXTERNAL_HARD_DRIVE) {
            updatePriceById(id, newPrice, externalHardDriveDAO);
        }
        if (id >= INITIAL_ID_VALUE_HEADSET && id <= FINAL_ID_VALUE_HEADSET) {
            updatePriceById(id, newPrice, headsetDAO);
        }
        if (id >= INITIAL_ID_VALUE_KEYBOARD && id <= FINAL_ID_VALUE_KEYBOARD) {
            updatePriceById(id, newPrice, keyboardDAO);
        }
        if (id >= INITIAL_ID_VALUE_MONITOR && id <= FINAL_ID_VALUE_MONITOR) {
            updatePriceById(id, newPrice, monitorDAO);
        }
        if (id >= INITIAL_ID_VALUE_MOUSE && id <= FINAL_ID_VALUE_MOUSE) {
            updatePriceById(id, newPrice, mouseDAO);
        }
        if (id >= INITIAL_ID_VALUE_SPEAKER && id <= FINAL_ID_VALUE_SPEAKER) {
            updatePriceById(id, newPrice, speakerDAO);
        }
        throw new ProductNotFoundException(id); // todo
    }

    @Override
    public void reduceCountById(final Long id, final Integer countToBeReduced) {
        if (id >= INITIAL_ID_VALUE_CASE && id <= FINAL_ID_VALUE_CASE) {
            reduceCountById(id, countToBeReduced, caseDAO);
        }
        else if (id >= INITIAL_ID_VALUE_COOLER && id <= FINAL_ID_VALUE_COOLER) {
            reduceCountById(id, countToBeReduced, coolerDAO);
        }
        else if (id >= INITIAL_ID_VALUE_CPU && id <= FINAL_ID_VALUE_CPU) {
            reduceCountById(id, countToBeReduced, cpuDAO);
        }
        else if (id >= INITIAL_ID_VALUE_CPU_COOLER && id <= FINAL_ID_VALUE_CPU_COOLER) {
            reduceCountById(id, countToBeReduced, cpuCoolerDAO);
        }
        else if (id >= INITIAL_ID_VALUE_GPU && id <= FINAL_ID_VALUE_GPU) {
            reduceCountById(id, countToBeReduced, gpuDAO);
        }
        else if (id >= INITIAL_ID_VALUE_INTERNAL_HARD_DRIVE && id <= FINAL_ID_VALUE_INTERNAL_HARD_DRIVE) {
            reduceCountById(id, countToBeReduced, internalHardDriveDAO);
        }
        else if (id >= INITIAL_ID_VALUE_MOTHERBOARD && id <= FINAL_ID_VALUE_MOTHERBOARD) {
            reduceCountById(id, countToBeReduced, motherboardDAO);
        }
        else if (id >= INITIAL_ID_VALUE_POWER_SUPPLY && id <= FINAL_ID_VALUE_POWER_SUPPLY) {
            reduceCountById(id, countToBeReduced, powerSupplyDAO);
        }
        else if (id >= INITIAL_ID_VALUE_RAM && id <= FINAL_ID_VALUE_RAM) {
            reduceCountById(id, countToBeReduced, ramDAO);
        }
        else if (id >= INITIAL_ID_VALUE_EXTERNAL_HARD_DRIVE && id <= FINAL_ID_VALUE_EXTERNAL_HARD_DRIVE) {
            reduceCountById(id, countToBeReduced, externalHardDriveDAO);
        }
        else if (id >= INITIAL_ID_VALUE_HEADSET && id <= FINAL_ID_VALUE_HEADSET) {
            reduceCountById(id, countToBeReduced, headsetDAO);
        }
        else if (id >= INITIAL_ID_VALUE_KEYBOARD && id <= FINAL_ID_VALUE_KEYBOARD) {
            reduceCountById(id, countToBeReduced, keyboardDAO);
        }
        else if (id >= INITIAL_ID_VALUE_MONITOR && id <= FINAL_ID_VALUE_MONITOR) {
            reduceCountById(id, countToBeReduced, monitorDAO);
        }
        else if (id >= INITIAL_ID_VALUE_MOUSE && id <= FINAL_ID_VALUE_MOUSE) {
            reduceCountById(id, countToBeReduced, mouseDAO);
        }
        else if (id >= INITIAL_ID_VALUE_SPEAKER && id <= FINAL_ID_VALUE_SPEAKER) {
            reduceCountById(id, countToBeReduced, speakerDAO);
        }
        else {
            throw new ProductNotFoundException(id); // todo
        }
    }

    public ProductDTO findById(final Long id) {
        if (id >= INITIAL_ID_VALUE_CASE && id <= FINAL_ID_VALUE_CASE) {
            return CaseDTO.initDTOFromEntity(findById(id, caseDAO));
        }
        if (id >= INITIAL_ID_VALUE_COOLER && id <= FINAL_ID_VALUE_COOLER) {
            return CoolerDTO.initDTOFromEntity(findById(id, coolerDAO));
        }
        if (id >= INITIAL_ID_VALUE_CPU && id <= FINAL_ID_VALUE_CPU) {
            return CPUDTO.initDTOFromEntity(findById(id, cpuDAO));
        }
        if (id >= INITIAL_ID_VALUE_CPU_COOLER && id <= FINAL_ID_VALUE_CPU_COOLER) {
            return CPUCoolerDTO.initDTOFromEntity(findById(id, cpuCoolerDAO));
        }
        if (id >= INITIAL_ID_VALUE_GPU && id <= FINAL_ID_VALUE_GPU) {
            return GPUDTO.initDTOFromEntity(findById(id, gpuDAO));
        }
        if (id >= INITIAL_ID_VALUE_INTERNAL_HARD_DRIVE && id <= FINAL_ID_VALUE_INTERNAL_HARD_DRIVE) {
            return InternalHardDriveDTO.initDTOFromEntity(findById(id, internalHardDriveDAO));
        }
        if (id >= INITIAL_ID_VALUE_MOTHERBOARD && id <= FINAL_ID_VALUE_MOTHERBOARD) {
            return MotherboardDTO.initDTOFromEntity(findById(id, motherboardDAO));
        }
        if (id >= INITIAL_ID_VALUE_POWER_SUPPLY && id <= FINAL_ID_VALUE_POWER_SUPPLY) {
            return PowerSupplyDTO.initDTOFromEntity(findById(id, powerSupplyDAO));
        }
        if (id >= INITIAL_ID_VALUE_RAM && id <= FINAL_ID_VALUE_RAM) {
            return RAMDTO.initDTOFromEntity(findById(id, ramDAO));
        }
        if (id >= INITIAL_ID_VALUE_EXTERNAL_HARD_DRIVE && id <= FINAL_ID_VALUE_EXTERNAL_HARD_DRIVE) {
            return ExternalHardDriveDTO.initDTOFromEntity(findById(id, externalHardDriveDAO));
        }
        if (id >= INITIAL_ID_VALUE_HEADSET && id <= FINAL_ID_VALUE_HEADSET) {
            return HeadsetDTO.initDTOFromEntity(findById(id, headsetDAO));
        }
        if (id >= INITIAL_ID_VALUE_KEYBOARD && id <= FINAL_ID_VALUE_KEYBOARD) {
            return KeyboardDTO.initDTOFromEntity(findById(id, keyboardDAO));
        }
        if (id >= INITIAL_ID_VALUE_MONITOR && id <= FINAL_ID_VALUE_MONITOR) {
            return MonitorDTO.initDTOFromEntity(findById(id, monitorDAO));
        }
        if (id >= INITIAL_ID_VALUE_MOUSE && id <= FINAL_ID_VALUE_MOUSE) {
            return MouseDTO.initDTOFromEntity(findById(id, mouseDAO));
        }
        if (id >= INITIAL_ID_VALUE_SPEAKER && id <= FINAL_ID_VALUE_SPEAKER) {
            return SpeakerDTO.initDTOFromEntity(findById(id, speakerDAO));
        }

        throw new ProductNotFoundException(id); //todo

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