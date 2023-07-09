package com.bdg.pc_build.product.service.impl;

import com.bdg.pc_build.exception.IdOutOfScopeException;
import com.bdg.pc_build.exception.NotEnoughInStockException;
import com.bdg.pc_build.exception.ProductNotFoundException;
import com.bdg.pc_build.exception.SameNameDifferentDescriptionException;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.dto.main.*;
import com.bdg.pc_build.product.model.dto.peripheral.*;
import com.bdg.pc_build.product.model.entity.Product;
import com.bdg.pc_build.product.model.entity.main.*;
import com.bdg.pc_build.product.model.entity.peripheral.*;
import com.bdg.pc_build.product.repository.ProductDAO;
import com.bdg.pc_build.product.repository.main.*;
import com.bdg.pc_build.product.repository.peripheral.*;
import com.bdg.pc_build.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.bdg.pc_build.util.InitialAndFinalIdValues.*;

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
                throw new SameNameDifferentDescriptionException(HttpStatus.NOT_ACCEPTABLE, foundedProduct.getClass(), foundedProduct.getName());
            }
            foundedProduct.setCount(foundedProduct.getCount() + product.getCount());
            return repository.save(foundedProduct);
        }
        return repository.save(product);
    }

    private <ENTITY extends Product> void updatePriceById(
            final Long id,
            final Double newPrice,
            final ProductDAO<ENTITY> repository
    ) {
        Optional<ENTITY> optionalENTITY = repository.findById(id);
        if (optionalENTITY.isEmpty()) {
            throw new ProductNotFoundException(HttpStatus.NOT_FOUND, id);
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
            throw new ProductNotFoundException(HttpStatus.NOT_FOUND, id);
        }

        ENTITY foundedProduct = optionalENTITY.get();

        if (foundedProduct.getCount() < countToBeReduced) {
            throw new NotEnoughInStockException(HttpStatus.OK, foundedProduct.getClass(), foundedProduct.getName(), foundedProduct.getCount());
        }
        foundedProduct.setCount(foundedProduct.getCount() - countToBeReduced);
        repository.save(foundedProduct);
    }

    private <ENTITY extends Product> void increaseCountById(
            final Long id,
            final Integer countToBeIncreased,
            final ProductDAO<ENTITY> repository
    ) {
        Optional<ENTITY> optionalENTITY = repository.findById(id);
        if (optionalENTITY.isEmpty()) {
            throw new ProductNotFoundException(HttpStatus.NOT_FOUND, id);
        }

        ENTITY foundedProduct = optionalENTITY.get();

        foundedProduct.setCount(foundedProduct.getCount() + countToBeIncreased);
        repository.save(foundedProduct);
    }

    private <ENTITY extends Product> List<ENTITY> findAll(final ProductDAO<ENTITY> dao) {
        return dao.findAll();
    }

    private <ENTITY extends Product> ENTITY findById(final Long id, final ProductDAO<ENTITY> dao) {
        Optional<ENTITY> result = dao.findById(id);
        return result.orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, id));
    }

    @Override
    public MonitorDTO saveMonitor(final MonitorDTO dto) {
        Monitor toSave = save(new Monitor(dto), monitorDAO);
        if (toSave.getId() < INITIAL_ID_VALUE_MONITOR || toSave.getId() > FINAL_ID_VALUE_MONITOR) {
            throw new IdOutOfScopeException(HttpStatus.CONFLICT, toSave.getClass(), toSave.getId());
        }
        return new MonitorDTO(toSave);
    }

    @Override
    public CaseDTO saveCase(final CaseDTO dto) {
        aCase toSave = save(new aCase(dto), caseDAO);
        if (toSave.getId() < INITIAL_ID_VALUE_CASE || toSave.getId() > FINAL_ID_VALUE_CASE) {
            throw new IdOutOfScopeException(HttpStatus.CONFLICT, toSave.getClass(), toSave.getId());
        }
        return new CaseDTO(toSave);
    }

    @Override
    public CoolerDTO saveCooler(final CoolerDTO dto) {
        Cooler toSave = save(new Cooler(dto), coolerDAO);
        if (toSave.getId() < INITIAL_ID_VALUE_COOLER || toSave.getId() > FINAL_ID_VALUE_COOLER) {
            throw new IdOutOfScopeException(HttpStatus.CONFLICT, toSave.getClass(), toSave.getId());
        }
        return new CoolerDTO(toSave);
    }

    @Override
    public CPUCoolerDTO saveCpuCooler(final CPUCoolerDTO dto) {
        CPUCooler toSave = save(new CPUCooler(dto), cpuCoolerDAO);
        if (toSave.getId() < INITIAL_ID_VALUE_CPU_COOLER || toSave.getId() > FINAL_ID_VALUE_CPU_COOLER) {
            throw new IdOutOfScopeException(HttpStatus.CONFLICT, toSave.getClass(), toSave.getId());
        }
        return new CPUCoolerDTO(toSave);
    }

    @Override
    public CPUDTO saveCpu(final CPUDTO dto) {
        CPU toSave = save(new CPU(dto), cpuDAO);
        if (toSave.getId() < INITIAL_ID_VALUE_CPU || toSave.getId() > FINAL_ID_VALUE_CPU) {
            throw new IdOutOfScopeException(HttpStatus.CONFLICT, toSave.getClass(), toSave.getId());
        }
        return new CPUDTO(toSave);
    }

    @Override
    public InternalHardDriveDTO saveInternalHardDrive(final InternalHardDriveDTO dto) {
        InternalHardDrive toSave = save(new InternalHardDrive(dto), internalHardDriveDAO);
        if (toSave.getId() < INITIAL_ID_VALUE_INTERNAL_HARD_DRIVE || toSave.getId() > FINAL_ID_VALUE_INTERNAL_HARD_DRIVE) {
            throw new IdOutOfScopeException(HttpStatus.CONFLICT, toSave.getClass(), toSave.getId());
        }
        return new InternalHardDriveDTO(toSave);
    }

    @Override
    public GPUDTO saveGpu(final GPUDTO dto) {
        GPU toSave = save(new GPU(dto), gpuDAO);
        if (toSave.getId() < INITIAL_ID_VALUE_GPU || toSave.getId() > FINAL_ID_VALUE_GPU) {
            throw new IdOutOfScopeException(HttpStatus.CONFLICT, toSave.getClass(), toSave.getId());
        }
        return new GPUDTO(toSave);
    }

    @Override
    public ExternalHardDriveDTO saveExternalHardDrive(final ExternalHardDriveDTO dto) {
        ExternalHardDrive toSave = save(new ExternalHardDrive(dto), externalHardDriveDAO);
        if (toSave.getId() < INITIAL_ID_VALUE_EXTERNAL_HARD_DRIVE || toSave.getId() > FINAL_ID_VALUE_EXTERNAL_HARD_DRIVE) {
            throw new IdOutOfScopeException(HttpStatus.CONFLICT, toSave.getClass(), toSave.getId());
        }
        return new ExternalHardDriveDTO(toSave);
    }

    @Override
    public MotherboardDTO saveMotherboard(final MotherboardDTO dto) {
        Motherboard toSave = save(new Motherboard(dto), motherboardDAO);
        if (toSave.getId() < INITIAL_ID_VALUE_MOTHERBOARD || toSave.getId() > FINAL_ID_VALUE_MOTHERBOARD) {
            throw new IdOutOfScopeException(HttpStatus.CONFLICT, toSave.getClass(), toSave.getId());
        }
        return new MotherboardDTO(toSave);
    }

    @Override
    public PowerSupplyDTO savePowerSupply(final PowerSupplyDTO dto) {
        PowerSupply toSave = save(new PowerSupply(dto), powerSupplyDAO);
        if (toSave.getId() < INITIAL_ID_VALUE_POWER_SUPPLY || toSave.getId() > FINAL_ID_VALUE_POWER_SUPPLY) {
            throw new IdOutOfScopeException(HttpStatus.CONFLICT, toSave.getClass(), toSave.getId());
        }
        return new PowerSupplyDTO(toSave);
    }

    @Override
    public RAMDTO saveRam(final RAMDTO dto) {
        RAM toSave = save(new RAM(dto), ramDAO);
        if (toSave.getId() < INITIAL_ID_VALUE_RAM || toSave.getId() > FINAL_ID_VALUE_RAM) {
            throw new IdOutOfScopeException(HttpStatus.CONFLICT, toSave.getClass(), toSave.getId());
        }
        return new RAMDTO(toSave);
    }

    @Override
    public HeadsetDTO saveHeadset(final HeadsetDTO dto) {
        Headset toSave = save(new Headset(dto), headsetDAO);
        if (toSave.getId() < INITIAL_ID_VALUE_HEADSET || toSave.getId() > FINAL_ID_VALUE_HEADSET) {
            throw new IdOutOfScopeException(HttpStatus.CONFLICT, toSave.getClass(), toSave.getId());
        }
        return new HeadsetDTO(toSave);
    }

    @Override
    public KeyboardDTO saveKeyboard(final KeyboardDTO dto) {
        Keyboard toSave = save(new Keyboard(dto), keyboardDAO);
        if (toSave.getId() < INITIAL_ID_VALUE_KEYBOARD || toSave.getId() > FINAL_ID_VALUE_KEYBOARD) {
            throw new IdOutOfScopeException(HttpStatus.CONFLICT, toSave.getClass(), toSave.getId());
        }
        return new KeyboardDTO(toSave);
    }

    @Override
    public MouseDTO saveMouse(final MouseDTO dto) {
        Mouse toSave = save(new Mouse(dto), mouseDAO);
        if (toSave.getId() < INITIAL_ID_VALUE_MOUSE || toSave.getId() > FINAL_ID_VALUE_MOUSE) {
            throw new IdOutOfScopeException(HttpStatus.CONFLICT, toSave.getClass(), toSave.getId());
        }
        return new MouseDTO(toSave);
    }

    @Override
    public SpeakerDTO saveSpeaker(final SpeakerDTO dto) {
        Speaker toSave = save(new Speaker(dto), speakerDAO);
        if (toSave.getId() < INITIAL_ID_VALUE_SPEAKER || toSave.getId() > FINAL_ID_VALUE_SPEAKER) {
            throw new IdOutOfScopeException(HttpStatus.CONFLICT, toSave.getClass(), toSave.getId());
        }
        return new SpeakerDTO(toSave);
    }

    @Override
    public void updatePriceById(final Long id, final Double newPrice) {
        if (id >= INITIAL_ID_VALUE_CASE && id <= FINAL_ID_VALUE_CASE) {
            updatePriceById(id, newPrice, caseDAO);
        } else if (id >= INITIAL_ID_VALUE_COOLER && id <= FINAL_ID_VALUE_COOLER) {
            updatePriceById(id, newPrice, coolerDAO);
        } else if (id >= INITIAL_ID_VALUE_CPU && id <= FINAL_ID_VALUE_CPU) {
            updatePriceById(id, newPrice, cpuDAO);
        } else if (id >= INITIAL_ID_VALUE_CPU_COOLER && id <= FINAL_ID_VALUE_CPU_COOLER) {
            updatePriceById(id, newPrice, cpuCoolerDAO);
        } else if (id >= INITIAL_ID_VALUE_GPU && id <= FINAL_ID_VALUE_GPU) {
            updatePriceById(id, newPrice, gpuDAO);
        } else if (id >= INITIAL_ID_VALUE_INTERNAL_HARD_DRIVE && id <= FINAL_ID_VALUE_INTERNAL_HARD_DRIVE) {
            updatePriceById(id, newPrice, internalHardDriveDAO);
        } else if (id >= INITIAL_ID_VALUE_MOTHERBOARD && id <= FINAL_ID_VALUE_MOTHERBOARD) {
            updatePriceById(id, newPrice, motherboardDAO);
        } else if (id >= INITIAL_ID_VALUE_POWER_SUPPLY && id <= FINAL_ID_VALUE_POWER_SUPPLY) {
            updatePriceById(id, newPrice, powerSupplyDAO);
        } else if (id >= INITIAL_ID_VALUE_RAM && id <= FINAL_ID_VALUE_RAM) {
            updatePriceById(id, newPrice, ramDAO);
        } else if (id >= INITIAL_ID_VALUE_EXTERNAL_HARD_DRIVE && id <= FINAL_ID_VALUE_EXTERNAL_HARD_DRIVE) {
            updatePriceById(id, newPrice, externalHardDriveDAO);
        } else if (id >= INITIAL_ID_VALUE_HEADSET && id <= FINAL_ID_VALUE_HEADSET) {
            updatePriceById(id, newPrice, headsetDAO);
        } else if (id >= INITIAL_ID_VALUE_KEYBOARD && id <= FINAL_ID_VALUE_KEYBOARD) {
            updatePriceById(id, newPrice, keyboardDAO);
        } else if (id >= INITIAL_ID_VALUE_MONITOR && id <= FINAL_ID_VALUE_MONITOR) {
            updatePriceById(id, newPrice, monitorDAO);
        } else if (id >= INITIAL_ID_VALUE_MOUSE && id <= FINAL_ID_VALUE_MOUSE) {
            updatePriceById(id, newPrice, mouseDAO);
        } else if (id >= INITIAL_ID_VALUE_SPEAKER && id <= FINAL_ID_VALUE_SPEAKER) {
            updatePriceById(id, newPrice, speakerDAO);
        } else {
            throw new IdOutOfScopeException(HttpStatus.CONFLICT, id);
        }
    }

    @Override
    public void reduceCountById(final Long id, final Integer countToBeReduced) {
        if (id >= INITIAL_ID_VALUE_CASE && id <= FINAL_ID_VALUE_CASE) {
            reduceCountById(id, countToBeReduced, caseDAO);
        } else if (id >= INITIAL_ID_VALUE_COOLER && id <= FINAL_ID_VALUE_COOLER) {
            reduceCountById(id, countToBeReduced, coolerDAO);
        } else if (id >= INITIAL_ID_VALUE_CPU && id <= FINAL_ID_VALUE_CPU) {
            reduceCountById(id, countToBeReduced, cpuDAO);
        } else if (id >= INITIAL_ID_VALUE_CPU_COOLER && id <= FINAL_ID_VALUE_CPU_COOLER) {
            reduceCountById(id, countToBeReduced, cpuCoolerDAO);
        } else if (id >= INITIAL_ID_VALUE_GPU && id <= FINAL_ID_VALUE_GPU) {
            reduceCountById(id, countToBeReduced, gpuDAO);
        } else if (id >= INITIAL_ID_VALUE_INTERNAL_HARD_DRIVE && id <= FINAL_ID_VALUE_INTERNAL_HARD_DRIVE) {
            reduceCountById(id, countToBeReduced, internalHardDriveDAO);
        } else if (id >= INITIAL_ID_VALUE_MOTHERBOARD && id <= FINAL_ID_VALUE_MOTHERBOARD) {
            reduceCountById(id, countToBeReduced, motherboardDAO);
        } else if (id >= INITIAL_ID_VALUE_POWER_SUPPLY && id <= FINAL_ID_VALUE_POWER_SUPPLY) {
            reduceCountById(id, countToBeReduced, powerSupplyDAO);
        } else if (id >= INITIAL_ID_VALUE_RAM && id <= FINAL_ID_VALUE_RAM) {
            reduceCountById(id, countToBeReduced, ramDAO);
        } else if (id >= INITIAL_ID_VALUE_EXTERNAL_HARD_DRIVE && id <= FINAL_ID_VALUE_EXTERNAL_HARD_DRIVE) {
            reduceCountById(id, countToBeReduced, externalHardDriveDAO);
        } else if (id >= INITIAL_ID_VALUE_HEADSET && id <= FINAL_ID_VALUE_HEADSET) {
            reduceCountById(id, countToBeReduced, headsetDAO);
        } else if (id >= INITIAL_ID_VALUE_KEYBOARD && id <= FINAL_ID_VALUE_KEYBOARD) {
            reduceCountById(id, countToBeReduced, keyboardDAO);
        } else if (id >= INITIAL_ID_VALUE_MONITOR && id <= FINAL_ID_VALUE_MONITOR) {
            reduceCountById(id, countToBeReduced, monitorDAO);
        } else if (id >= INITIAL_ID_VALUE_MOUSE && id <= FINAL_ID_VALUE_MOUSE) {
            reduceCountById(id, countToBeReduced, mouseDAO);
        } else if (id >= INITIAL_ID_VALUE_SPEAKER && id <= FINAL_ID_VALUE_SPEAKER) {
            reduceCountById(id, countToBeReduced, speakerDAO);
        } else {
            throw new IdOutOfScopeException(HttpStatus.CONFLICT, id);
        }
    }

    @Override
    public void increaseCountById(final Long id, final Integer countToBeIncreased) {
        if (id >= INITIAL_ID_VALUE_CASE && id <= FINAL_ID_VALUE_CASE) {
            increaseCountById(id, countToBeIncreased, caseDAO);
        } else if (id >= INITIAL_ID_VALUE_COOLER && id <= FINAL_ID_VALUE_COOLER) {
            increaseCountById(id, countToBeIncreased, coolerDAO);
        } else if (id >= INITIAL_ID_VALUE_CPU && id <= FINAL_ID_VALUE_CPU) {
            increaseCountById(id, countToBeIncreased, cpuDAO);
        } else if (id >= INITIAL_ID_VALUE_CPU_COOLER && id <= FINAL_ID_VALUE_CPU_COOLER) {
            increaseCountById(id, countToBeIncreased, cpuCoolerDAO);
        } else if (id >= INITIAL_ID_VALUE_GPU && id <= FINAL_ID_VALUE_GPU) {
            increaseCountById(id, countToBeIncreased, gpuDAO);
        } else if (id >= INITIAL_ID_VALUE_INTERNAL_HARD_DRIVE && id <= FINAL_ID_VALUE_INTERNAL_HARD_DRIVE) {
            increaseCountById(id, countToBeIncreased, internalHardDriveDAO);
        } else if (id >= INITIAL_ID_VALUE_MOTHERBOARD && id <= FINAL_ID_VALUE_MOTHERBOARD) {
            increaseCountById(id, countToBeIncreased, motherboardDAO);
        } else if (id >= INITIAL_ID_VALUE_POWER_SUPPLY && id <= FINAL_ID_VALUE_POWER_SUPPLY) {
            increaseCountById(id, countToBeIncreased, powerSupplyDAO);
        } else if (id >= INITIAL_ID_VALUE_RAM && id <= FINAL_ID_VALUE_RAM) {
            increaseCountById(id, countToBeIncreased, ramDAO);
        } else if (id >= INITIAL_ID_VALUE_EXTERNAL_HARD_DRIVE && id <= FINAL_ID_VALUE_EXTERNAL_HARD_DRIVE) {
            increaseCountById(id, countToBeIncreased, externalHardDriveDAO);
        } else if (id >= INITIAL_ID_VALUE_HEADSET && id <= FINAL_ID_VALUE_HEADSET) {
            increaseCountById(id, countToBeIncreased, headsetDAO);
        } else if (id >= INITIAL_ID_VALUE_KEYBOARD && id <= FINAL_ID_VALUE_KEYBOARD) {
            increaseCountById(id, countToBeIncreased, keyboardDAO);
        } else if (id >= INITIAL_ID_VALUE_MONITOR && id <= FINAL_ID_VALUE_MONITOR) {
            increaseCountById(id, countToBeIncreased, monitorDAO);
        } else if (id >= INITIAL_ID_VALUE_MOUSE && id <= FINAL_ID_VALUE_MOUSE) {
            increaseCountById(id, countToBeIncreased, mouseDAO);
        } else if (id >= INITIAL_ID_VALUE_SPEAKER && id <= FINAL_ID_VALUE_SPEAKER) {
            increaseCountById(id, countToBeIncreased, speakerDAO);
        } else {
            throw new IdOutOfScopeException(HttpStatus.CONFLICT, id);
        }
    }

    public ProductDTO findById(final Long id) {
        if (id >= INITIAL_ID_VALUE_CASE && id <= FINAL_ID_VALUE_CASE) {
            return new CaseDTO(findById(id, caseDAO));
        }
        if (id >= INITIAL_ID_VALUE_COOLER && id <= FINAL_ID_VALUE_COOLER) {
            return new CoolerDTO(findById(id, coolerDAO));
        }
        if (id >= INITIAL_ID_VALUE_CPU && id <= FINAL_ID_VALUE_CPU) {
            return new CPUDTO(findById(id, cpuDAO));
        }
        if (id >= INITIAL_ID_VALUE_CPU_COOLER && id <= FINAL_ID_VALUE_CPU_COOLER) {
            return new CPUCoolerDTO(findById(id, cpuCoolerDAO));
        }
        if (id >= INITIAL_ID_VALUE_GPU && id <= FINAL_ID_VALUE_GPU) {
            return new GPUDTO(findById(id, gpuDAO));
        }
        if (id >= INITIAL_ID_VALUE_INTERNAL_HARD_DRIVE && id <= FINAL_ID_VALUE_INTERNAL_HARD_DRIVE) {
            return new InternalHardDriveDTO(findById(id, internalHardDriveDAO));
        }
        if (id >= INITIAL_ID_VALUE_MOTHERBOARD && id <= FINAL_ID_VALUE_MOTHERBOARD) {
            return new MotherboardDTO(findById(id, motherboardDAO));
        }
        if (id >= INITIAL_ID_VALUE_POWER_SUPPLY && id <= FINAL_ID_VALUE_POWER_SUPPLY) {
            return new PowerSupplyDTO(findById(id, powerSupplyDAO));
        }
        if (id >= INITIAL_ID_VALUE_RAM && id <= FINAL_ID_VALUE_RAM) {
            return new RAMDTO(findById(id, ramDAO));
        }
        if (id >= INITIAL_ID_VALUE_EXTERNAL_HARD_DRIVE && id <= FINAL_ID_VALUE_EXTERNAL_HARD_DRIVE) {
            return new ExternalHardDriveDTO(findById(id, externalHardDriveDAO));
        }
        if (id >= INITIAL_ID_VALUE_HEADSET && id <= FINAL_ID_VALUE_HEADSET) {
            return new HeadsetDTO(findById(id, headsetDAO));
        }
        if (id >= INITIAL_ID_VALUE_KEYBOARD && id <= FINAL_ID_VALUE_KEYBOARD) {
            return new KeyboardDTO(findById(id, keyboardDAO));
        }
        if (id >= INITIAL_ID_VALUE_MONITOR && id <= FINAL_ID_VALUE_MONITOR) {
            return new MonitorDTO(findById(id, monitorDAO));
        }
        if (id >= INITIAL_ID_VALUE_MOUSE && id <= FINAL_ID_VALUE_MOUSE) {
            return new MouseDTO(findById(id, mouseDAO));
        }
        if (id >= INITIAL_ID_VALUE_SPEAKER && id <= FINAL_ID_VALUE_SPEAKER) {
            return new SpeakerDTO(findById(id, speakerDAO));
        }
        throw new IdOutOfScopeException(HttpStatus.CONFLICT, id);
    }

    @Override
    public List<CaseDTO> getAllCases() {
        return findAll(caseDAO).stream().map(CaseDTO::new).toList();
    }

    @Override
    public List<CoolerDTO> getAllCoolers() {
        return findAll(coolerDAO).stream().map(CoolerDTO::new).toList();
    }

    @Override
    public List<CPUCoolerDTO> getAllCpuCoolers() {
        return findAll(cpuCoolerDAO).stream().map(CPUCoolerDTO::new).toList();
    }

    @Override
    public List<CPUDTO> getAllCpus() {
        return findAll(cpuDAO).stream().map(CPUDTO::new).toList();
    }

    @Override
    public List<GPUDTO> getAllGpus() {
        return findAll(gpuDAO).stream().map(GPUDTO::new).toList();
    }

    @Override
    public List<InternalHardDriveDTO> getAllInternalHardDrives() {
        return findAll(internalHardDriveDAO).stream().map(InternalHardDriveDTO::new).toList();
    }

    @Override
    public List<MotherboardDTO> getAllMotherboards() {
        return findAll(motherboardDAO).stream().map(MotherboardDTO::new).toList();
    }

    @Override
    public List<PowerSupplyDTO> getAllPowerSupplies() {
        return findAll(powerSupplyDAO).stream().map(PowerSupplyDTO::new).toList();
    }

    @Override
    public List<RAMDTO> getAllRams() {
        return findAll(ramDAO).stream().map(RAMDTO::new).toList();
    }

    @Override
    public List<ExternalHardDriveDTO> getAllExternalHardDrives() {
        return findAll(externalHardDriveDAO).stream().map(ExternalHardDriveDTO::new).toList();
    }

    @Override
    public List<HeadsetDTO> getAllHeadsets() {
        return findAll(headsetDAO).stream().map(HeadsetDTO::new).toList();
    }

    @Override
    public List<KeyboardDTO> getAllKeyboards() {
        return findAll(keyboardDAO).stream().map(KeyboardDTO::new).toList();
    }

    @Override
    public List<MonitorDTO> getAllMonitors() {
        return findAll(monitorDAO).stream().map(MonitorDTO::new).toList();
    }

    @Override
    public List<MouseDTO> getAllMice() {
        return findAll(mouseDAO).stream().map(MouseDTO::new).toList();
    }

    @Override
    public List<SpeakerDTO> getAllSpeakers() {
        return findAll(speakerDAO).stream().map(SpeakerDTO::new).toList();
    }
}