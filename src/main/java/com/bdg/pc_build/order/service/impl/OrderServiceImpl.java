package com.bdg.pc_build.order.service.impl;

import com.bdg.pc_build.exception.IdOutOfScopeException;
import com.bdg.pc_build.exception.ProductNotFoundException;
import com.bdg.pc_build.order.enumerations.OrderStatus;
import com.bdg.pc_build.order.model.dto.OrderDTO;
import com.bdg.pc_build.order.model.entity.Order;
import com.bdg.pc_build.order.repository.OrderDAO;
import com.bdg.pc_build.order.service.OrderService;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main.*;
import com.bdg.pc_build.product.model.entity.peripheral.*;
import com.bdg.pc_build.product.repository.main.*;
import com.bdg.pc_build.product.repository.peripheral.*;
import com.bdg.pc_build.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
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
    public OrderDTO saveOrder(final Set<ProductDTO> products, final BigDecimal totalPrice, final User user, final Boolean isFromBuilder) {
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
        return new OrderDTO(orderDAO.save(order));
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderDAO.findAll()
                .stream()
                .map(OrderDTO::new)
                .toList();
    }

    @Override
    public List<OrderDTO> getAllOrdersByStatus(final OrderStatus status) {
        return orderDAO.findAllByStatus(status)
                .stream()
                .map(OrderDTO::new)
                .toList();
    }

    @Override
    public void markOrderAsInProcessById(final Long id) {
        Order order = orderDAO.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found: " + id));
        if (!order.getStatus().equals(OrderStatus.NEW)) {
            throw new IllegalStateException("Order status cant be changed"); //todo
        }

        for (aCase orderCase : order.getCases()) {
            orderCase.setCount(orderCase.getCount() - 1);
        }
        for (Cooler orderCooler : order.getCoolers()) {
            orderCooler.setCount(orderCooler.getCount() - 1);
        }
        for (CPU orderCpu : order.getCpus()) {
            orderCpu.setCount(orderCpu.getCount() - 1);
        }
        for (CPUCooler orderCpuCooler : order.getCpuCoolers()) {
            orderCpuCooler.setCount(orderCpuCooler.getCount() - 1);
        }
        for (GPU orderGpu : order.getGpus()) {
            orderGpu.setCount(orderGpu.getCount() - 1);
        }
        for (InternalHardDrive orderInternalHardDrive : order.getInternalHardDrives()) {
            orderInternalHardDrive.setCount(orderInternalHardDrive.getCount() - 1);
        }
        for (Motherboard orderMotherboard : order.getMotherboards()) {
            orderMotherboard.setCount(orderMotherboard.getCount() - 1);
        }
        for (PowerSupply orderPowerSupply : order.getPowerSupplies()) {
            orderPowerSupply.setCount(orderPowerSupply.getCount() - 1);
        }
        for (RAM orderRam : order.getRams()) {
            orderRam.setCount(orderRam.getCount() - 1);
        }
        for (ExternalHardDrive orderExternalHardDrive : order.getExternalHardDrives()) {
            orderExternalHardDrive.setCount(orderExternalHardDrive.getCount() - 1);
        }
        for (Headset orderHeadset : order.getHeadsets()) {
            orderHeadset.setCount(orderHeadset.getCount() - 1);
        }
        for (Keyboard orderKeyboard : order.getKeyboards()) {
            orderKeyboard.setCount(orderKeyboard.getCount() - 1);
        }
        for (Monitor orderMonitor : order.getMonitors()) {
            orderMonitor.setCount(orderMonitor.getCount() - 1);
        }
        for (Mouse orderMouse : order.getMice()) {
            orderMouse.setCount(orderMouse.getCount() - 1);
        }
        for (Speaker orderSpeaker : order.getSpeakers()) {
            orderSpeaker.setCount(orderSpeaker.getCount() - 1);
        }
        order.setStatus(OrderStatus.IN_PROCESS);
        orderDAO.save(order);
    }

    @Override
    public void markOrderAsCompletedById(final Long id) {
        Order order = orderDAO.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found: " + id));
        if (!order.getStatus().equals(OrderStatus.IN_PROCESS)) {
            throw new IllegalStateException("Order status cant be changed"); //todo
        }
        order.setStatus(OrderStatus.COMPLETED);
        orderDAO.save(order);
    }

    @Override
    public void markOrderAsCanceledById(final Long id) {
        Order order = orderDAO.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found: " + id));
        if (order.getStatus().equals(OrderStatus.COMPLETED)) {
            throw new IllegalArgumentException(); //todo
        }
        if (order.getStatus().equals(OrderStatus.CANCELED)) {
            throw new IllegalStateException(); //todo
        }

        if (order.getStatus().equals(OrderStatus.NEW)) {
            order.setStatus(OrderStatus.CANCELED);
            orderDAO.save(order);
        }

        for (aCase orderCase : order.getCases()) {
            orderCase.setCount(orderCase.getCount() + 1);
        }
        for (Cooler orderCooler : order.getCoolers()) {
            orderCooler.setCount(orderCooler.getCount() + 1);
        }
        for (CPU orderCpu : order.getCpus()) {
            orderCpu.setCount(orderCpu.getCount() + 1);
        }
        for (CPUCooler orderCpuCooler : order.getCpuCoolers()) {
            orderCpuCooler.setCount(orderCpuCooler.getCount() + 1);
        }
        for (GPU orderGpu : order.getGpus()) {
            orderGpu.setCount(orderGpu.getCount() + 1);
        }
        for (InternalHardDrive orderInternalHardDrive : order.getInternalHardDrives()) {
            orderInternalHardDrive.setCount(orderInternalHardDrive.getCount() + 1);
        }
        for (Motherboard orderMotherboard : order.getMotherboards()) {
            orderMotherboard.setCount(orderMotherboard.getCount() + 1);
        }
        for (PowerSupply orderPowerSupply : order.getPowerSupplies()) {
            orderPowerSupply.setCount(orderPowerSupply.getCount() + 1);
        }
        for (RAM orderRam : order.getRams()) {
            orderRam.setCount(orderRam.getCount() + 1);
        }
        for (ExternalHardDrive orderExternalHardDrive : order.getExternalHardDrives()) {
            orderExternalHardDrive.setCount(orderExternalHardDrive.getCount() + 1);
        }
        for (Headset orderHeadset : order.getHeadsets()) {
            orderHeadset.setCount(orderHeadset.getCount() + 1);
        }
        for (Keyboard orderKeyboard : order.getKeyboards()) {
            orderKeyboard.setCount(orderKeyboard.getCount() + 1);
        }
        for (Monitor orderMonitor : order.getMonitors()) {
            orderMonitor.setCount(orderMonitor.getCount() + 1);
        }
        for (Mouse orderMouse : order.getMice()) {
            orderMouse.setCount(orderMouse.getCount() + 1);
        }
        for (Speaker orderSpeaker : order.getSpeakers()) {
            orderSpeaker.setCount(orderSpeaker.getCount() + 1);
        }
        order.setStatus(OrderStatus.CANCELED);
        orderDAO.save(order);
    }
}