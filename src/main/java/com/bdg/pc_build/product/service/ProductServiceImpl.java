package com.bdg.pc_build.product.service;

import com.bdg.pc_build.product.model.dto.display.MonitorDTO;
import com.bdg.pc_build.product.model.dto.main_component.*;
import com.bdg.pc_build.product.model.dto.peripheral.HeadsetDTO;
import com.bdg.pc_build.product.model.dto.peripheral.KeyboardDTO;
import com.bdg.pc_build.product.model.dto.peripheral.MouseDTO;
import com.bdg.pc_build.product.model.dto.peripheral.SpeakerDTO;
import com.bdg.pc_build.product.model.entity.Product;
import com.bdg.pc_build.product.model.entity.display.Monitor;
import com.bdg.pc_build.product.model.entity.main_component.Case;
import com.bdg.pc_build.product.model.entity.main_component.Cooler;
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
    public CPUCoolerDTO saveCpuCooler(CPUCoolerDTO dto) {
        return null;
    }

    @Override
    public CPUDTO saveCPU(CPUDTO dto) {
        return null;
    }

    @Override
    public ExternalHardDriveDTO saveExternalHardDrive(ExternalHardDriveDTO dto) {
        return null;
    }

    @Override
    public GPUDTO saveGPU(GPUDTO dto) {
        return null;
    }

    @Override
    public InternalHardDriveDTO saveInternalHardDrive(InternalHardDriveDTO dto) {
        return null;
    }

    @Override
    public MotherboardDTO saveMotherBoard(MotherboardDTO dto) {
        return null;
    }

    @Override
    public PowerSupplyDTO savePowerSupply(PowerSupplyDTO dto) {
        return null;
    }

    @Override
    public RAMDTO saveRAM(RAMDTO dto) {
        return null;
    }

    @Override
    public HeadsetDTO saveHeadSet(HeadsetDTO dto) {
        return null;
    }

    @Override
    public KeyboardDTO saveKeyboard(KeyboardDTO dto) {
        return null;
    }

    @Override
    public MouseDTO saveMouse(MouseDTO dto) {
        return null;
    }

    @Override
    public SpeakerDTO saveSpeaker(SpeakerDTO dto) {
        return null;
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
    public List<CaseDTO> findCaseByPrice(Double minPrice, Double maxPrice) {
        return null;
    }

    @Override
    public List<CaseDTO> findCaseByPurchasedPrice(Double minPurchasedPrice, Double maxPurchasedPrice) {
        return null;
    }

    @Override
    public MonitorDTO updateMonitorPriceByName(final String name, final Double newPrice) {
        return MonitorDTO.initDTOFromEntity(updatePriceByName(name, newPrice, monitorRepository));
    }

    @Override
    public MonitorDTO reduceMonitorCountByName(String name, Integer countToBeReduced) {
        return MonitorDTO.initDTOFromEntity(reduceCountByName(name, countToBeReduced, monitorRepository));
    }
}