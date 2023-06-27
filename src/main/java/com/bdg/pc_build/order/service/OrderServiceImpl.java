package com.bdg.pc_build.order.service;

import com.bdg.pc_build.exception.IdOutOfScopeException;
import com.bdg.pc_build.exception.ProductNotFoundException;
import com.bdg.pc_build.order.entity.Order;
import com.bdg.pc_build.order.repository.OrderDAO;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.repository.main_component.*;
import com.bdg.pc_build.product.repository.peripheral.*;
import com.bdg.pc_build.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

import static com.bdg.pc_build.util.InitialAndFinalIdValues.*;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;

    private final CaseDAO caseDAO;
    private final CoolerDAO coolerDAO;
    private final CPUDAO cpuDAO;
    private final CPUCoolerDAO cpuCoolerDAO;
    private final GPUDAO gpudao;
    private final InternalHardDriveDAO internalHardDriveDAO;
    private final MotherboardDAO motherboardDAO;
    private final PowerSupplyDAO powerSupplyDAO;
    private final RAMDAO ramDAO;
    private final ExternalHardDriveDAO externalHardDriveDAO;
    private final HeadsetDAO headsetDAO;
    private final KeyboardDAO keyboardDAO;
    private final MonitorDAO monitorDAO;
    private final MouseDAO mouseDAO;
    private final SpeakerDAO speakerDAO;

    @Override
    public Order save(final Set<ProductDTO> products, final BigDecimal totalPrice, final User user, final Boolean isFromBuilder) {
        Order order = new Order();
        order.setTotalPrice(totalPrice);
        order.setIsFromBuilder(isFromBuilder);
        order.setUser(user);
        for (ProductDTO product : products) {
            if (product.getId() >= INITIAL_ID_VALUE_CASE && product.getId() <= FINAL_ID_VALUE_CASE) {
                order.addCase(caseDAO.findById(product.getId()).orElseThrow(() -> new ProductNotFoundException(product.getClass(), product.getId())));
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
                throw new IdOutOfScopeException(product.getId());
            }
        }
        return orderDAO.save(order);
    }
}