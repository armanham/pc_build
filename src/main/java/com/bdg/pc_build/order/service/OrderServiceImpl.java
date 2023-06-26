package com.bdg.pc_build.order.service;

import com.bdg.pc_build.exception.ProductNotFoundException;
import com.bdg.pc_build.order.entity.Order;
import com.bdg.pc_build.order.repository.OrderDAO;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.dto.main_component.CaseDTO;
import com.bdg.pc_build.product.repository.main_component.*;
import com.bdg.pc_build.product.repository.peripheral.*;
import com.bdg.pc_build.user.model.entity.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

import static com.bdg.pc_build.product.model.InitialAndMaxValues.*;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    OrderDAO orderDAO;

    CaseDAO caseDAO;
    CoolerDAO coolerDAO;
    CPUDAO cpuDAO;
    CPUCoolerDAO cpuCoolerDAO;
    GPUDAO gpudao;
    InternalHardDriveDAO internalHardDriveDAO;
    MotherboardDAO motherboardDAO;
    PowerSupplyDAO powerSupplyDAO;
    RAMDAO ramDAO;
    ExternalHardDriveDAO externalHardDriveDAO;
    HeadsetDAO headsetDAO;
    KeyboardDAO keyboardDAO;
    MonitorDAO monitorDAO;
    MouseDAO mouseDAO;
    SpeakerDAO speakerDAO;

    @Override
    public Order save(final Set<ProductDTO> products, final BigDecimal totalPrice, final User user) {
        Order order = new Order();
        order.setTotalPrice(totalPrice);
        order.setUser(user);
        for (ProductDTO product : products) {
            if (product.getId() >= INITIAL_ID_VALUE_CASE && product.getId() <= FINAL_ID_VALUE_CASE) {
                order.addCase(caseDAO.findById(product.getId()).orElseThrow(() -> new ProductNotFoundException(product.getClass(), product.getId())));//todo
            } else if (product.getId() >= INITIAL_ID_VALUE_COOLER && product.getId() <= FINAL_ID_VALUE_COOLER) {
                order.addCooler(coolerDAO.findById(product.getId()).orElseThrow(() -> new ProductNotFoundException(product.getClass(), product.getId())));
            } else if (product.getId() >= INITIAL_ID_VALUE_CPU && product.getId() <= FINAL_ID_VALUE_CPU) {
                order.addCPU(cpuDAO.findById(product.getId()).orElseThrow(() -> new ProductNotFoundException(product.getClass(), product.getId())));
            } else if (product.getId() >= INITIAL_ID_VALUE_CPU_COOLER && product.getId() <= FINAL_ID_VALUE_CPU_COOLER) {
                order.addCPUCooler(cpuCoolerDAO.findById(product.getId()).orElseThrow(() -> new ProductNotFoundException(product.getClass(), product.getId())));
            } else if (product.getId() >= INITIAL_ID_VALUE_GPU && product.getId() <= FINAL_ID_VALUE_GPU) {
                order.addGPU(gpudao.findById(product.getId()).orElseThrow(() -> new ProductNotFoundException(product.getClass(), product.getId())));
            } else if (product.getId() >= INITIAL_ID_VALUE_INTERNAL_HARD_DRIVE && product.getId() <= FINAL_ID_VALUE_INTERNAL_HARD_DRIVE) {
                order.addInternalHardDrive(internalHardDriveDAO.findById(product.getId()).orElseThrow(() -> new ProductNotFoundException(product.getClass(), product.getId())));
            } else if (product.getId() >= INITIAL_ID_VALUE_MOTHERBOARD && product.getId() <= FINAL_ID_VALUE_MOTHERBOARD) {
                order.addMotherboard(motherboardDAO.findById(product.getId()).orElseThrow(() -> new ProductNotFoundException(product.getClass(), product.getId())));
            } else if (product.getId() >= INITIAL_ID_VALUE_POWER_SUPPLY && product.getId() <= FINAL_ID_VALUE_POWER_SUPPLY) {
                order.addPowerSupply(powerSupplyDAO.findById(product.getId()).orElseThrow(() -> new ProductNotFoundException(product.getClass(), product.getId())));
            } else if (product.getId() >= INITIAL_ID_VALUE_RAM && product.getId() <= FINAL_ID_VALUE_RAM) {
                order.addRAM(ramDAO.findById(product.getId()).orElseThrow(() -> new ProductNotFoundException(product.getClass(), product.getId())));
            } else if (product.getId() >= INITIAL_ID_VALUE_EXTERNAL_HARD_DRIVE && product.getId() <= FINAL_ID_VALUE_EXTERNAL_HARD_DRIVE) {
                order.addExternalHardDrive(externalHardDriveDAO.findById(product.getId()).orElseThrow(() -> new ProductNotFoundException(product.getClass(), product.getId())));
            } else if (product.getId() >= INITIAL_ID_VALUE_HEADSET && product.getId() <= FINAL_ID_VALUE_HEADSET) {
                order.addHeadset(headsetDAO.findById(product.getId()).orElseThrow(() -> new ProductNotFoundException(product.getClass(), product.getId())));
            } else if (product.getId() >= INITIAL_ID_VALUE_KEYBOARD && product.getId() <= FINAL_ID_VALUE_KEYBOARD) {
                order.addKeyboard(keyboardDAO.findById(product.getId()).orElseThrow(() -> new ProductNotFoundException(product.getClass(), product.getId())));
            } else if (product.getId() >= INITIAL_ID_VALUE_MONITOR && product.getId() <= FINAL_ID_VALUE_MONITOR) {
                order.addMonitor(monitorDAO.findById(product.getId()).orElseThrow(() -> new ProductNotFoundException(product.getClass(), product.getId())));
            } else if (product.getId() >= INITIAL_ID_VALUE_MOUSE && product.getId() <= FINAL_ID_VALUE_MOUSE) {
                order.addMouse(mouseDAO.findById(product.getId()).orElseThrow(() -> new ProductNotFoundException(product.getClass(), product.getId())));
            } else if (product.getId() >= INITIAL_ID_VALUE_SPEAKER && product.getId() <= FINAL_ID_VALUE_SPEAKER) {
                order.addSpeaker(speakerDAO.findById(product.getId()).orElseThrow(() -> new ProductNotFoundException(product.getClass(), product.getId())));
            } else {
                throw new ProductNotFoundException(product.getId());//todo
            }
        }
        return orderDAO.save(order);
    }
}