package com.bdg.pc_build.order.service;

import com.bdg.pc_build.order.entity.Order;
import com.bdg.pc_build.order.repository.OrderDAO;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.dto.main_component.*;
import com.bdg.pc_build.product.model.dto.peripheral.*;
import com.bdg.pc_build.product.model.entity.main_component.*;
import com.bdg.pc_build.product.model.entity.peripheral.*;
import com.bdg.pc_build.product.repository.peripheral.MonitorDAO;
import com.bdg.pc_build.product.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    OrderDAO orderDAO;
    ProductService productService;
    MonitorDAO monitorDAO;

    @Override
    public Set<ProductDTO> save(Set<ProductDTO> products, BigDecimal totalPrice) {
        Order order = new Order();
        order.setTotalPrice(totalPrice);
        for (ProductDTO dto: products) {
            if(dto instanceof CaseDTO caseDTO){
                order.addCase(new aCase(caseDTO));
            }
            if(dto instanceof CoolerDTO coolerDTO){
                order.addCooler(new Cooler(coolerDTO));
            }
            if(dto instanceof CPUDTO cpuDTO){
                order.addCPU(new CPU(cpuDTO));
            }
            if(dto instanceof CPUCoolerDTO cpuCoolerDTO){
                order.addCPUCooler(new CPUCooler(cpuCoolerDTO));
            }
            if(dto instanceof GPUDTO gpuDTO){
                order.addGPU(new GPU(gpuDTO));
            }
            if(dto instanceof InternalHardDriveDTO internalHardDriveDTO){
                order.addInternalHardDrive(new InternalHardDrive(internalHardDriveDTO));
            }
            if(dto instanceof MotherboardDTO motherboardDTO){
                order.addMotherboard(new Motherboard(motherboardDTO));
            }
            if(dto instanceof PowerSupplyDTO powerSupplyDTO){
                order.addPowerSupply(new PowerSupply(powerSupplyDTO));
            }
            if(dto instanceof RAMDTO ramDTO){
                order.addRAM(new RAM(ramDTO));
            }
            if(dto instanceof ExternalHardDriveDTO externalHardDriveDTO){
                order.addExternalHardDrive(new ExternalHardDrive(externalHardDriveDTO));
            }
            if(dto instanceof HeadsetDTO headsetDTO){
                order.addHeadset(new Headset(headsetDTO));
            }
            if(dto instanceof KeyboardDTO keyboardDTO){
                order.addKeyboard(new Keyboard(keyboardDTO));
            }
            if(dto instanceof MonitorDTO monitorDTO){
                order.addMonitor(monitorDAO.findById(monitorDTO.getId()).get());
            }
            if(dto instanceof MouseDTO mouseDTO){
                order.addMouse(new Mouse(mouseDTO));
            }
            if(dto instanceof SpeakerDTO speakerDTO){
                order.addSpeaker(new Speaker(speakerDTO));
            }
        }
        orderDAO.save(order);
        return products;
    }
}