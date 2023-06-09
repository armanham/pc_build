package com.bdg.pc_build.product.service;

import com.bdg.pc_build.product.model.dto.display.MonitorDTO;
import com.bdg.pc_build.product.model.dto.main_component.*;
import com.bdg.pc_build.product.model.dto.peripheral.HeadsetDTO;
import com.bdg.pc_build.product.model.dto.peripheral.KeyboardDTO;
import com.bdg.pc_build.product.model.dto.peripheral.MouseDTO;
import com.bdg.pc_build.product.model.dto.peripheral.SpeakerDTO;
import com.bdg.pc_build.product.model.entity.Product;
import com.bdg.pc_build.product.model.entity.display.Monitor;
import com.bdg.pc_build.product.model.entity.main_component.*;
import com.bdg.pc_build.product.model.entity.peripheral.Headset;
import com.bdg.pc_build.product.model.entity.peripheral.Keyboard;
import com.bdg.pc_build.product.model.entity.peripheral.Mouse;
import com.bdg.pc_build.product.model.entity.peripheral.Speaker;
import com.bdg.pc_build.product.repository.BaseProductRepository;
import com.bdg.pc_build.product.repository.display.MonitorRepository;
import com.bdg.pc_build.product.repository.main_component.*;
import com.bdg.pc_build.product.repository.peripheral.HeadsetRepository;
import com.bdg.pc_build.product.repository.peripheral.KeyboardRepository;
import com.bdg.pc_build.product.repository.peripheral.MouseRepository;
import com.bdg.pc_build.product.repository.peripheral.SpeakerRepository;
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

    //Display repositories
    MonitorRepository monitorRepository;

    //Main component repositories
    CaseRepository caseRepository;
    CoolerRepository coolerRepository;
    CPUCoolerRepository cpuCoolerRepository;
    CPURepository cpuRepository;
    ExternalHardDriveRepository externalHardDriveRepository;
    GPURepository gpuRepository;
    InternalHardDriveRepository internalHardDriveRepository;
    MotherboardRepository motherboardRepository;
    PowerSupplyRepository powerSupplyRepository;
    RAMRepository ramRepository;

    //Peripheral repositories
    HeadsetRepository headsetRepository;
    KeyboardRepository keyboardRepository;
    MouseRepository mouseRepository;
    SpeakerRepository speakerRepository;


    private <ENTITY extends Product> ENTITY save(
            final ENTITY product,
            final BaseProductRepository<ENTITY> repository
    ) {
        Optional<ENTITY> optionalENTITY = repository.findByName(product.getName());
        if (optionalENTITY.isPresent()) {
            ENTITY foundedProduct = optionalENTITY.get();
            if (!foundedProduct.equals(product)) {
                //todo DiferentProdEx
                throw new IllegalArgumentException();
            }
            foundedProduct.setCount(foundedProduct.getCount() + product.getCount());
            return repository.save(foundedProduct);
        }
        return repository.save(product);
    }


    private <ENTITY extends Product> ENTITY findByName(
            final String name,
            final BaseProductRepository<ENTITY> repository
    ) {
        Optional<ENTITY> optionalENTITY = repository.findByName(name);
        if (optionalENTITY.isEmpty()) {
            //TODO NotFoundEx
            throw new IllegalArgumentException();
        }
        return optionalENTITY.get();
    }


    private <ENTITY extends Product> List<ENTITY> findAllByPrice(
            final Double minPrice,
            final Double maxPrice,
            final BaseProductRepository<ENTITY> repository
    ) {
        return repository.findAllByPriceBetween(minPrice, maxPrice);
    }

    private <ENTITY extends Product> List<ENTITY> findAllByPurchasedPrice(
            final Double minPurchasedPrice,
            final Double maxPurchasedPrice,
            final BaseProductRepository<ENTITY> repository
    ) {
        return repository.findAllByPurchasedPriceBetween(minPurchasedPrice, maxPurchasedPrice);
    }

    private <ENTITY extends Product> ENTITY updatePriceByName(
            final String name,
            final Double newPrice,
            final BaseProductRepository<ENTITY> repository
    ) {
        Optional<ENTITY> optionalENTITY = repository.findByName(name);
        if (optionalENTITY.isEmpty()) {
            //TODO notFoundEx
            throw new IllegalArgumentException();
        }
        ENTITY foundedProduct = optionalENTITY.get();
        foundedProduct.setPrice(newPrice);
        return repository.save(foundedProduct);
    }


    private <ENTITY extends Product> ENTITY reduceCountByName(
            final String name,
            final Integer countToBeReduced,
            final BaseProductRepository<ENTITY> repository
    ) {
        Optional<ENTITY> optionalENTITY = repository.findByName(name);
        if (optionalENTITY.isEmpty()) {
            //TODO notFoundEx
            throw new IllegalArgumentException();
        }

        ENTITY foundedProduct = optionalENTITY.get();

        if (foundedProduct.getCount() < countToBeReduced) {
            //todo customEx
            throw new IllegalArgumentException("aaaaaaaa");
        }
        foundedProduct.setCount(foundedProduct.getCount() - countToBeReduced);
        return repository.save(foundedProduct);
    }


    @Override
    public MonitorDTO saveMonitor(final MonitorDTO dto) {
        return MonitorDTO.initDTOFromEntity(save(new Monitor(dto), monitorRepository));
    }

    @Override
    public CaseDTO saveCase(final CaseDTO dto) {
        return CaseDTO.initDTOFromEntity(save(new Case(dto), caseRepository));
    }

    @Override
    public CoolerDTO saveCooler(final CoolerDTO dto) {
        return CoolerDTO.initDTOFromEntity(save(new Cooler(dto), coolerRepository));
    }

    @Override
    public CPUCoolerDTO saveCpuCooler(final CPUCoolerDTO dto) {
        return CPUCoolerDTO.initDTOFromEntity(save(new CPUCooler(dto), cpuCoolerRepository));
    }

    @Override
    public CPUDTO saveCPU(final CPUDTO dto) {
        return CPUDTO.initDTOFromEntity(save(new CPU(dto), cpuRepository));
    }

    @Override
    public ExternalHardDriveDTO saveExternalHardDrive(final ExternalHardDriveDTO dto) {
        return ExternalHardDriveDTO.initDTOFromEntity(save(new ExternalHardDrive(dto), externalHardDriveRepository));
    }

    @Override
    public GPUDTO saveGPU(final GPUDTO dto) {
        return GPUDTO.initDTOFromEntity(save(new GPU(dto), gpuRepository));
    }

    @Override
    public InternalHardDriveDTO saveInternalHardDrive(final InternalHardDriveDTO dto) {
        return InternalHardDriveDTO.initDTOFromEntity(save(new InternalHardDrive(dto), internalHardDriveRepository));
    }

    @Override
    public MotherboardDTO saveMotherBoard(final MotherboardDTO dto) {
        return MotherboardDTO.initDTOFromEntity(save(new Motherboard(dto), motherboardRepository));
    }

    @Override
    public PowerSupplyDTO savePowerSupply(final PowerSupplyDTO dto) {
        return PowerSupplyDTO.initDTOFromEntity(save(new PowerSupply(dto), powerSupplyRepository));
    }

    @Override
    public RAMDTO saveRAM(final RAMDTO dto) {
        return RAMDTO.initDTOFromEntity(save(new RAM(dto), ramRepository));
    }

    @Override
    public HeadsetDTO saveHeadSet(final HeadsetDTO dto) {
        return HeadsetDTO.initDTOFromEntity(save(new Headset(dto), headsetRepository));
    }

    @Override
    public KeyboardDTO saveKeyboard(final KeyboardDTO dto) {
        return KeyboardDTO.initDTOFromEntity(save(new Keyboard(dto), keyboardRepository));
    }

    @Override
    public MouseDTO saveMouse(final MouseDTO dto) {
        return MouseDTO.initDTOFromEntity(save(new Mouse(dto), mouseRepository));
    }

    @Override
    public SpeakerDTO saveSpeaker(final SpeakerDTO dto) {
        return SpeakerDTO.initDTOFromEntity(save(new Speaker(dto), speakerRepository));
    }

    @Override
    public MonitorDTO findMonitorByName(final String name) {
        return MonitorDTO.initDTOFromEntity(findByName(name, monitorRepository));
    }

    @Override
    public List<MonitorDTO> findMonitorByPrice(final Double minPrice, final Double maxPrice) {
        return findAllByPrice(minPrice, maxPrice, monitorRepository)
                .stream()
                .map(MonitorDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public List<MonitorDTO> findMonitorByPurchasedPrice(final Double minPurchasedPrice, final Double maxPurchasedPrice) {
        return findAllByPurchasedPrice(minPurchasedPrice, maxPurchasedPrice, monitorRepository)
                .stream()
                .map(MonitorDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public CaseDTO findCaseByName(final String name) {
        return CaseDTO.initDTOFromEntity(findByName(name, caseRepository));
    }

    @Override
    public List<CaseDTO> findCaseByPrice(final Double minPrice, final Double maxPrice) {
        return findAllByPrice(minPrice, maxPrice, caseRepository)
                .stream()
                .map(CaseDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public List<CaseDTO> findCaseByPurchasedPrice(final Double minPurchasedPrice, final Double maxPurchasedPrice) {
        return findAllByPurchasedPrice(minPurchasedPrice, maxPurchasedPrice, caseRepository)
                .stream()
                .map(CaseDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public CoolerDTO findCoolerByName(final String name) {
        return CoolerDTO.initDTOFromEntity(findByName(name, coolerRepository));
    }

    @Override
    public List<CoolerDTO> findCoolerByPrice(final Double minPrice, final Double maxPrice) {
        return findAllByPrice(minPrice, maxPrice, coolerRepository)
                .stream()
                .map(CoolerDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public List<CoolerDTO> findCoolerByPurchasedPrice(final Double minPurchasedPrice, final Double maxPurchasedPrice) {
        return findAllByPurchasedPrice(minPurchasedPrice, maxPurchasedPrice, coolerRepository)
                .stream()
                .map(CoolerDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public CPUCoolerDTO findCPUCoolerByName(final String name) {
        return CPUCoolerDTO.initDTOFromEntity(findByName(name, cpuCoolerRepository));
    }

    @Override
    public List<CPUCoolerDTO> findCPUCoolerByPrice(final Double minPrice, final Double maxPrice) {
        return findAllByPrice(minPrice, maxPrice, cpuCoolerRepository)
                .stream()
                .map(CPUCoolerDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public List<CPUCoolerDTO> findCPUCoolerByPurchasedPrice(final Double minPurchasedPrice, final Double maxPurchasedPrice) {
        return findAllByPurchasedPrice(minPurchasedPrice, maxPurchasedPrice, cpuCoolerRepository)
                .stream()
                .map(CPUCoolerDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public CPUDTO findCPUByName(final String name) {
        return CPUDTO.initDTOFromEntity(findByName(name, cpuRepository));
    }

    @Override
    public List<CPUDTO> findCPUByPrice(final Double minPrice, final Double maxPrice) {
        return findAllByPrice(minPrice, maxPrice, cpuRepository)
                .stream()
                .map(CPUDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public List<CPUDTO> findCPUByPurchasedPrice(final Double minPurchasedPrice, final Double maxPurchasedPrice) {
        return findAllByPurchasedPrice(minPurchasedPrice, maxPurchasedPrice, cpuRepository)
                .stream()
                .map(CPUDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public ExternalHardDriveDTO findExternalHardDriveByName(final String name) {
        return ExternalHardDriveDTO.initDTOFromEntity(findByName(name, externalHardDriveRepository));
    }

    @Override
    public List<ExternalHardDriveDTO> findExternalHardDriveByPrice(final Double minPrice, final Double maxPrice) {
        return findAllByPrice(minPrice, maxPrice, externalHardDriveRepository)
                .stream()
                .map(ExternalHardDriveDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public List<ExternalHardDriveDTO> findExternalHardDriveByPurchasedPrice(final Double minPurchasedPrice, final Double maxPurchasedPrice) {
        return findAllByPurchasedPrice(minPurchasedPrice, maxPurchasedPrice, externalHardDriveRepository)
                .stream()
                .map(ExternalHardDriveDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public GPUDTO findGPUByName(final String name) {
        return GPUDTO.initDTOFromEntity(findByName(name, gpuRepository));
    }

    @Override
    public List<GPUDTO> findGPUByPrice(final Double minPrice, final Double maxPrice) {
        return findAllByPrice(minPrice, maxPrice, gpuRepository)
                .stream()
                .map(GPUDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public List<GPUDTO> findGPUByPurchasedPrice(final Double minPurchasedPrice, final Double maxPurchasedPrice) {
        return findAllByPurchasedPrice(minPurchasedPrice, maxPurchasedPrice, gpuRepository)
                .stream()
                .map(GPUDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public InternalHardDriveDTO findInternalHardDriveByName(final String name) {
        return InternalHardDriveDTO.initDTOFromEntity(findByName(name, internalHardDriveRepository));
    }

    @Override
    public List<InternalHardDriveDTO> findInternalHardDriveByPrice(final Double minPrice, final Double maxPrice) {
        return findAllByPrice(minPrice, maxPrice, internalHardDriveRepository)
                .stream()
                .map(InternalHardDriveDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public List<InternalHardDriveDTO> findInternalHardDriveByPurchasedPrice(final Double minPurchasedPrice, final Double maxPurchasedPrice) {
        return findAllByPurchasedPrice(minPurchasedPrice, maxPurchasedPrice, internalHardDriveRepository)
                .stream()
                .map(InternalHardDriveDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public MotherboardDTO findMotherboardByName(final String name) {
        return MotherboardDTO.initDTOFromEntity(findByName(name, motherboardRepository));
    }

    @Override
    public List<MotherboardDTO> findMotherboardByPrice(final Double minPrice, final Double maxPrice) {
        return findAllByPrice(minPrice, maxPrice, motherboardRepository)
                .stream()
                .map(MotherboardDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public List<MotherboardDTO> findMotherboardByPurchasedPrice(final Double minPurchasedPrice, final Double maxPurchasedPrice) {
        return findAllByPurchasedPrice(minPurchasedPrice, maxPurchasedPrice, motherboardRepository)
                .stream()
                .map(MotherboardDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public PowerSupplyDTO findPowerSupplyByName(final String name) {
        return PowerSupplyDTO.initDTOFromEntity(findByName(name, powerSupplyRepository));
    }

    @Override
    public List<PowerSupplyDTO> findPowerSupplyByPrice(final Double minPrice, final Double maxPrice) {
        return findAllByPrice(minPrice, maxPrice, powerSupplyRepository)
                .stream()
                .map(PowerSupplyDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public List<PowerSupplyDTO> findPowerSupplyByPurchasedPrice(final Double minPurchasedPrice, final Double maxPurchasedPrice) {
        return findAllByPurchasedPrice(minPurchasedPrice, maxPurchasedPrice, powerSupplyRepository)
                .stream()
                .map(PowerSupplyDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public RAMDTO findRAMByName(final String name) {
        return RAMDTO.initDTOFromEntity(findByName(name, ramRepository));
    }

    @Override
    public List<RAMDTO> findRAMByPrice(final Double minPrice, final Double maxPrice) {
        return findAllByPrice(minPrice, maxPrice, ramRepository)
                .stream()
                .map(RAMDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public List<RAMDTO> findRAMByPurchasedPrice(final Double minPurchasedPrice, final Double maxPurchasedPrice) {
        return findAllByPurchasedPrice(minPurchasedPrice, maxPurchasedPrice, ramRepository)
                .stream()
                .map(RAMDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public HeadsetDTO findHeadsetByName(final String name) {
        return HeadsetDTO.initDTOFromEntity(findByName(name, headsetRepository));
    }

    @Override
    public List<HeadsetDTO> findHeadsetByPrice(final Double minPrice, final Double maxPrice) {
        return findAllByPrice(minPrice, maxPrice, headsetRepository)
                .stream()
                .map(HeadsetDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public List<HeadsetDTO> findHeadsetByPurchasedPrice(final Double minPurchasedPrice, final Double maxPurchasedPrice) {
        return findAllByPurchasedPrice(minPurchasedPrice, maxPurchasedPrice, headsetRepository)
                .stream()
                .map(HeadsetDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public KeyboardDTO findKeyboardByName(final String name) {
        return KeyboardDTO.initDTOFromEntity(findByName(name, keyboardRepository));
    }

    @Override
    public List<KeyboardDTO> findKeyboardByPrice(final Double minPrice, final Double maxPrice) {
        return findAllByPrice(minPrice, maxPrice, keyboardRepository)
                .stream()
                .map(KeyboardDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public List<KeyboardDTO> findKeyboardByPurchasedPrice(final Double minPurchasedPrice, final Double maxPurchasedPrice) {
        return findAllByPurchasedPrice(minPurchasedPrice, maxPurchasedPrice, keyboardRepository)
                .stream()
                .map(KeyboardDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public MouseDTO findMouseByName(final String name) {
        return MouseDTO.initDTOFromEntity(findByName(name, mouseRepository));
    }

    @Override
    public List<MouseDTO> findMouseByPrice(final Double minPrice, final Double maxPrice) {
        return findAllByPrice(minPrice, maxPrice, mouseRepository)
                .stream()
                .map(MouseDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public List<MouseDTO> findMouseByPurchasedPrice(final Double minPurchasedPrice, final Double maxPurchasedPrice) {
        return findAllByPurchasedPrice(minPurchasedPrice, maxPurchasedPrice, mouseRepository)
                .stream()
                .map(MouseDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public SpeakerDTO findSpeakerByName(final String name) {
        return SpeakerDTO.initDTOFromEntity(findByName(name, speakerRepository));
    }

    @Override
    public List<SpeakerDTO> findSpeakerByPrice(final Double minPrice, final Double maxPrice) {
        return findAllByPrice(minPrice, maxPrice, speakerRepository)
                .stream()
                .map(SpeakerDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public List<SpeakerDTO> findSpeakerByPurchasedPrice(final Double minPurchasedPrice, final Double maxPurchasedPrice) {
        return findAllByPurchasedPrice(minPurchasedPrice, maxPurchasedPrice, speakerRepository)
                .stream()
                .map(SpeakerDTO::initDTOFromEntity)
                .toList();
    }

    @Override
    public MonitorDTO updateMonitorPriceByName(final String name, final Double newPrice) {
        return MonitorDTO.initDTOFromEntity(updatePriceByName(name, newPrice, monitorRepository));
    }

    @Override
    public CaseDTO updateCasePriceByName(final String name, final Double newPrice) {
        return CaseDTO.initDTOFromEntity(updatePriceByName(name, newPrice, caseRepository));
    }

    @Override
    public CoolerDTO updateCoolerPriceByName(final String name, final Double newPrice) {
        return CoolerDTO.initDTOFromEntity(updatePriceByName(name, newPrice, coolerRepository));
    }

    @Override
    public CPUDTO updateCPUPriceByName(final String name, final Double newPrice) {
        return CPUDTO.initDTOFromEntity(updatePriceByName(name, newPrice, cpuRepository));
    }

    @Override
    public CPUCoolerDTO updateCPUCoolerPriceByName(final String name, final Double newPrice) {
        return CPUCoolerDTO.initDTOFromEntity(updatePriceByName(name, newPrice, cpuCoolerRepository));
    }

    @Override
    public ExternalHardDriveDTO updateExternalHardDrivePriceByName(final String name, final Double newPrice) {
        return ExternalHardDriveDTO.initDTOFromEntity(updatePriceByName(name, newPrice, externalHardDriveRepository));
    }

    @Override
    public GPUDTO updateGPUPriceByName(final String name, final Double newPrice) {
        return GPUDTO.initDTOFromEntity(updatePriceByName(name, newPrice, gpuRepository));
    }

    @Override
    public InternalHardDriveDTO updateInternalHardDrivePriceByName(final String name, final Double newPrice) {
        return InternalHardDriveDTO.initDTOFromEntity(updatePriceByName(name, newPrice, internalHardDriveRepository));
    }

    @Override
    public MotherboardDTO updateMotherboardPriceByName(final String name, final Double newPrice) {
        return MotherboardDTO.initDTOFromEntity(updatePriceByName(name, newPrice, motherboardRepository));
    }

    @Override
    public PowerSupplyDTO updatePowerSupplyPriceByName(final String name, final Double newPrice) {
        return PowerSupplyDTO.initDTOFromEntity(updatePriceByName(name, newPrice, powerSupplyRepository));
    }

    @Override
    public RAMDTO updateRAMPriceByName(final String name, final Double newPrice) {
        return RAMDTO.initDTOFromEntity(updatePriceByName(name, newPrice, ramRepository));
    }

    @Override
    public HeadsetDTO updateHeadsetPriceByName(final String name, final Double newPrice) {
        return HeadsetDTO.initDTOFromEntity(updatePriceByName(name, newPrice, headsetRepository));
    }

    @Override
    public KeyboardDTO updateKeyboardPriceByName(final String name, final Double newPrice) {
        return KeyboardDTO.initDTOFromEntity(updatePriceByName(name, newPrice, keyboardRepository));
    }

    @Override
    public MouseDTO updateMousePriceByName(final String name, final Double newPrice) {
        return MouseDTO.initDTOFromEntity(updatePriceByName(name, newPrice, mouseRepository));
    }

    @Override
    public SpeakerDTO updateSpeakerPriceByName(final String name, final Double newPrice) {
        return SpeakerDTO.initDTOFromEntity(updatePriceByName(name, newPrice, speakerRepository));
    }

    @Override
    public MonitorDTO reduceMonitorCountByName(final String name, final Integer countToBeReduced) {
        return MonitorDTO.initDTOFromEntity(reduceCountByName(name, countToBeReduced, monitorRepository));
    }

    @Override
    public CaseDTO reduceCaseCountByName(final String name, final Integer countToBeReduced) {
        return CaseDTO.initDTOFromEntity(reduceCountByName(name, countToBeReduced, caseRepository));
    }

    @Override
    public CoolerDTO reduceCoolerCountByName(final String name, final Integer countToBeReduced) {
        return CoolerDTO.initDTOFromEntity(reduceCountByName(name, countToBeReduced, coolerRepository));
    }

    @Override
    public CPUDTO reduceCPUCountByName(final String name, final Integer countToBeReduced) {
        return CPUDTO.initDTOFromEntity(reduceCountByName(name, countToBeReduced, cpuRepository));
    }

    @Override
    public CPUCoolerDTO reduceCPUCoolerCountByName(final String name, final Integer countToBeReduced) {
        return CPUCoolerDTO.initDTOFromEntity(reduceCountByName(name, countToBeReduced, cpuCoolerRepository));
    }

    @Override
    public ExternalHardDriveDTO reduceExternalHardDriveCountByName(final String name, final Integer countToBeReduced) {
        return ExternalHardDriveDTO.initDTOFromEntity(reduceCountByName(name, countToBeReduced, externalHardDriveRepository));
    }

    @Override
    public GPUDTO reduceGPUCountByName(final String name, final Integer countToBeReduced) {
        return GPUDTO.initDTOFromEntity(reduceCountByName(name, countToBeReduced, gpuRepository));
    }

    @Override
    public InternalHardDriveDTO reduceInternalHardDriveCountByName(final String name, final Integer countToBeReduced) {
        return InternalHardDriveDTO.initDTOFromEntity(reduceCountByName(name, countToBeReduced, internalHardDriveRepository));
    }

    @Override
    public MotherboardDTO reduceMotherboardCountByName(final String name, final Integer countToBeReduced) {
        return MotherboardDTO.initDTOFromEntity(reduceCountByName(name, countToBeReduced, motherboardRepository));
    }

    @Override
    public PowerSupplyDTO reducePowerSupplyCountByName(final String name, final Integer countToBeReduced) {
        return PowerSupplyDTO.initDTOFromEntity(reduceCountByName(name, countToBeReduced, powerSupplyRepository));
    }

    @Override
    public RAMDTO reduceRAMCountByName(final String name, final Integer countToBeReduced) {
        return RAMDTO.initDTOFromEntity(reduceCountByName(name, countToBeReduced, ramRepository));
    }

    @Override
    public HeadsetDTO reduceHeadsetCountByName(final String name, final Integer countToBeReduced) {
        return HeadsetDTO.initDTOFromEntity(reduceCountByName(name, countToBeReduced, headsetRepository));
    }

    @Override
    public KeyboardDTO reduceKeyboardCountByName(final String name, final Integer countToBeReduced) {
        return KeyboardDTO.initDTOFromEntity(reduceCountByName(name, countToBeReduced, keyboardRepository));
    }

    @Override
    public MouseDTO reduceMouseCountByName(final String name, final Integer countToBeReduced) {
        return MouseDTO.initDTOFromEntity(reduceCountByName(name, countToBeReduced, mouseRepository));
    }

    @Override
    public SpeakerDTO reduceSpeakerCountByName(final String name, final Integer countToBeReduced) {
        return SpeakerDTO.initDTOFromEntity(reduceCountByName(name, countToBeReduced, speakerRepository));
    }
}