package com.bdg.pc_build.product.service;

import com.bdg.pc_build.product.model.dto.display.MonitorDTO;
import com.bdg.pc_build.product.model.dto.main_component.*;
import com.bdg.pc_build.product.model.dto.peripheral.HeadsetDTO;
import com.bdg.pc_build.product.model.dto.peripheral.KeyboardDTO;
import com.bdg.pc_build.product.model.dto.peripheral.MouseDTO;
import com.bdg.pc_build.product.model.dto.peripheral.SpeakerDTO;
import com.bdg.pc_build.product.model.entity.Product;
import com.bdg.pc_build.product.model.entity.display.Monitor;
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

import java.util.Optional;


@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
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


    private <T extends Product> T save(T product, BaseProductRepository<T> repository) {
        Optional<T> optional = repository.findByName(product.getName());
        if(optional.isPresent()){
            T foundedProduct = optional.get();
            if(!foundedProduct.equals(product)){
                //todo
                throw new IllegalArgumentException();
            }
            foundedProduct.setCount(foundedProduct.getCount() + product.getCount());
            return repository.save(foundedProduct);
        }
        return repository.save(product);
    }

    @Override
    public MonitorDTO saveMonitor(final MonitorDTO dto) {
        return MonitorDTO.initDTOFromEntity(save(new Monitor(dto), monitorRepository));
    }

    @Override
    public CaseDTO saveCase(CaseDTO dto) {
        return null;
    }

    @Override
    public CoolerDTO saveCooler(CoolerDTO dto) {
        return null;
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

//    public CPU saveCpu(CPU cpu){
//        return save(cpu, cpuRepository);
//    }





}
