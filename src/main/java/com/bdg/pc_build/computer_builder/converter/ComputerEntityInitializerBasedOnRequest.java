package com.bdg.pc_build.computer_builder.converter;

import com.bdg.pc_build.cart.model.CartItem;
import com.bdg.pc_build.computer_builder.model.entity.Computer;
import com.bdg.pc_build.computer_builder.model.request.ComputerCreationRequest;
import com.bdg.pc_build.exception.IdOutOfScopeException;
import com.bdg.pc_build.exception.NoProductFoundInBuilderException;
import com.bdg.pc_build.exception.NotCompatibleException;
import com.bdg.pc_build.exception.ProductNotFoundException;
import com.bdg.pc_build.product.model.entity.main.*;
import com.bdg.pc_build.product.model.entity.peripheral.*;
import com.bdg.pc_build.product.repository.main.*;
import com.bdg.pc_build.product.repository.peripheral.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.bdg.pc_build.util.InitialAndFinalIdValues.*;

@RequiredArgsConstructor
@Component
public class ComputerEntityInitializerBasedOnRequest {

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


    public Computer initEntityFromRequest(final ComputerCreationRequest request) {
        if (request.getCartItems().isEmpty()) {
            throw new NoProductFoundInBuilderException(HttpStatus.BAD_REQUEST);
        }

        Computer computer = new Computer();
        BigDecimal totalPrice = computer.getTotalPrice();

        for (CartItem cartItem : request.getCartItems()) {
            if (cartItem.productId() >= INITIAL_ID_VALUE_CASE && cartItem.productId() <= FINAL_ID_VALUE_CASE) {
                if (cartItem.quantity() != 1) {
                    throw new NotCompatibleException(HttpStatus.OK, "Count of Case should not exceed 1: ");
                }
                aCase aCase = caseDAO.findById(cartItem.productId()).orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, aCase.class, cartItem.productId()));
                computer.setACase(aCase);
                totalPrice = totalPrice.add(BigDecimal.valueOf(aCase.getPrice()));
            } else if (cartItem.productId() >= INITIAL_ID_VALUE_COOLER && cartItem.productId() <= FINAL_ID_VALUE_COOLER) {
                Cooler cooler = coolerDAO.findById(cartItem.productId()).orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, Cooler.class, cartItem.productId()));
                computer.addCooler(cooler);
                totalPrice = totalPrice.add(BigDecimal.valueOf(cooler.getPrice()));
            } else if (cartItem.productId() >= INITIAL_ID_VALUE_CPU && cartItem.productId() <= FINAL_ID_VALUE_CPU) {
                if (cartItem.quantity() != 1) {
                    throw new NotCompatibleException(HttpStatus.OK, "Count of CPU should not exceed 1: ");
                }
                CPU cpu = cpuDAO.findById(cartItem.productId()).orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, CPU.class, cartItem.productId()));
                computer.setCpu(cpu);
                totalPrice = totalPrice.add(BigDecimal.valueOf(cpu.getPrice()));
            } else if (cartItem.productId() >= INITIAL_ID_VALUE_CPU_COOLER && cartItem.productId() <= FINAL_ID_VALUE_CPU_COOLER) {
                if (cartItem.quantity() != 1) {
                    throw new NotCompatibleException(HttpStatus.OK, "Count of CPU Cooler should not exceed 1: ");
                }
                CPUCooler cpuCooler = cpuCoolerDAO.findById(cartItem.productId()).orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, CPUCooler.class, cartItem.productId()));
                computer.setCpuCooler(cpuCooler);
                totalPrice = totalPrice.add(BigDecimal.valueOf(cpuCooler.getPrice()));
            } else if (cartItem.productId() >= INITIAL_ID_VALUE_GPU && cartItem.productId() <= FINAL_ID_VALUE_GPU) {
                if (cartItem.quantity() != 1) {
                    throw new NotCompatibleException(HttpStatus.OK, "Count of GPU should not exceed 1: ");
                }
                GPU gpu = gpuDAO.findById(cartItem.productId()).orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, GPU.class, cartItem.productId()));
                computer.setGpu(gpu);
                totalPrice = totalPrice.add(BigDecimal.valueOf(gpu.getPrice()));
            } else if (cartItem.productId() >= INITIAL_ID_VALUE_INTERNAL_HARD_DRIVE && cartItem.productId() <= FINAL_ID_VALUE_INTERNAL_HARD_DRIVE) {
                InternalHardDrive internalHardDrive = internalHardDriveDAO.findById(cartItem.productId()).orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, InternalHardDrive.class, cartItem.productId()));
                computer.addInternalHardDrive(internalHardDrive);
                totalPrice = totalPrice.add(BigDecimal.valueOf(internalHardDrive.getPrice()));
            } else if (cartItem.productId() >= INITIAL_ID_VALUE_MOTHERBOARD && cartItem.productId() <= FINAL_ID_VALUE_MOTHERBOARD) {
                if (cartItem.quantity() != 1) {
                    throw new NotCompatibleException(HttpStatus.OK, "Count of Motherboard should not exceed 1: ");
                }
                Motherboard motherboard = motherboardDAO.findById(cartItem.productId()).orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, Motherboard.class, cartItem.productId()));
                computer.setMotherboard(motherboard);
                totalPrice = totalPrice.add(BigDecimal.valueOf(motherboard.getPrice()));
            } else if (cartItem.productId() >= INITIAL_ID_VALUE_POWER_SUPPLY && cartItem.productId() <= FINAL_ID_VALUE_POWER_SUPPLY) {
                if (cartItem.quantity() != 1) {
                    throw new NotCompatibleException(HttpStatus.OK, "Count of Power Supply should not exceed 1: ");
                }
                PowerSupply powerSupply = powerSupplyDAO.findById(cartItem.productId()).orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, PowerSupply.class, cartItem.productId()));
                computer.setPowerSupply(powerSupply);
                totalPrice = totalPrice.add(BigDecimal.valueOf(powerSupply.getPrice()));
            } else if (cartItem.productId() >= INITIAL_ID_VALUE_RAM && cartItem.productId() <= FINAL_ID_VALUE_RAM) {
                RAM ram = ramDAO.findById(cartItem.productId()).orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, RAM.class, cartItem.productId()));
                computer.addRAM(ram);
                totalPrice = totalPrice.add(BigDecimal.valueOf(ram.getPrice()));
            } else if (cartItem.productId() >= INITIAL_ID_VALUE_EXTERNAL_HARD_DRIVE && cartItem.productId() <= FINAL_ID_VALUE_EXTERNAL_HARD_DRIVE) {
                ExternalHardDrive externalHardDrive = externalHardDriveDAO.findById(cartItem.productId()).orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, ExternalHardDrive.class, cartItem.productId()));
                computer.addExternalHardDrive(externalHardDrive);
                totalPrice = totalPrice.add(BigDecimal.valueOf(externalHardDrive.getPrice()));
            } else if (cartItem.productId() >= INITIAL_ID_VALUE_HEADSET && cartItem.productId() <= FINAL_ID_VALUE_HEADSET) {
                Headset headset = headsetDAO.findById(cartItem.productId()).orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, Headset.class, cartItem.productId()));
                computer.addHeadset(headset);
                totalPrice = totalPrice.add(BigDecimal.valueOf(headset.getPrice()));
            } else if (cartItem.productId() >= INITIAL_ID_VALUE_KEYBOARD && cartItem.productId() <= FINAL_ID_VALUE_KEYBOARD) {
                Keyboard keyboard = keyboardDAO.findById(cartItem.productId()).orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, Keyboard.class, cartItem.productId()));
                computer.addKeyboard(keyboard);
                totalPrice = totalPrice.add(BigDecimal.valueOf(keyboard.getPrice()));
            } else if (cartItem.productId() >= INITIAL_ID_VALUE_MONITOR && cartItem.productId() <= FINAL_ID_VALUE_MONITOR) {
                Monitor monitor = monitorDAO.findById(cartItem.productId()).orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, Monitor.class, cartItem.productId()));
                computer.addMonitor(monitor);
                totalPrice = totalPrice.add(BigDecimal.valueOf(monitor.getPrice()));
            } else if (cartItem.productId() >= INITIAL_ID_VALUE_MOUSE && cartItem.productId() <= FINAL_ID_VALUE_MOUSE) {
                Mouse mouse = mouseDAO.findById(cartItem.productId()).orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, Mouse.class, cartItem.productId()));
                computer.addMouse(mouse);
                totalPrice = totalPrice.add(BigDecimal.valueOf(mouse.getPrice()));
            } else if (cartItem.productId() >= INITIAL_ID_VALUE_SPEAKER && cartItem.productId() <= FINAL_ID_VALUE_SPEAKER) {
                Speaker speaker = speakerDAO.findById(cartItem.productId()).orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, Speaker.class, cartItem.productId()));
                computer.addSpeaker(speaker);
                totalPrice = totalPrice.add(BigDecimal.valueOf(speaker.getPrice()));
            } else {
                throw new IdOutOfScopeException(HttpStatus.CONFLICT, cartItem.productId());
            }
        }

        computer.setIsFullyConstructed(isFullyConstructedResolver(computer));
        computer.setTotalPrice(totalPrice);
        return computer;
    }

    private boolean isFullyConstructedResolver(final Computer computer) {
        return computer.getACase() != null
                && computer.getCpu() != null
                && computer.getCpuCooler() != null
                && !computer.getCoolers().isEmpty()
                && computer.getGpu() != null
                && !computer.getInternalHardDrives().isEmpty()
                && computer.getMotherboard() != null
                && computer.getPowerSupply() != null
                && !computer.getRams().isEmpty();
    }
}