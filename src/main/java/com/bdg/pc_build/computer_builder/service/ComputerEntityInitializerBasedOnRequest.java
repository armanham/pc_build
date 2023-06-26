package com.bdg.pc_build.computer_builder.service;

import com.bdg.pc_build.cart.model.CartItem;
import com.bdg.pc_build.computer_builder.model.entity.Computer;
import com.bdg.pc_build.computer_builder.model.request.ComputerCreationRequest;
import com.bdg.pc_build.exception.ProductNotFoundException;
import com.bdg.pc_build.product.model.entity.main_component.*;
import com.bdg.pc_build.product.model.entity.peripheral.*;
import com.bdg.pc_build.product.repository.main_component.*;
import com.bdg.pc_build.product.repository.peripheral.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static com.bdg.pc_build.product.model.InitialAndMaxValues.*;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Component
public class ComputerEntityInitializerBasedOnRequest {

    //Main component repositories
    CaseDAO caseDAO;
    CoolerDAO coolerDAO;
    CPUCoolerDAO cpuCoolerDAO;
    CPUDAO cpuDAO;
    GPUDAO gpuDAO;
    InternalHardDriveDAO internalHardDriveDAO;
    MotherboardDAO motherboardDAO;
    PowerSupplyDAO powerSupplyDAO;
    RAMDAO ramDAO;

    //Peripheral repositories
    ExternalHardDriveDAO externalHardDriveDAO;
    MonitorDAO monitorDAO;
    HeadsetDAO headsetDAO;
    KeyboardDAO keyboardDAO;
    MouseDAO mouseDAO;
    SpeakerDAO speakerDAO;


    public Computer initEntityFromRequest(final ComputerCreationRequest request) {
        Computer computer = new Computer();

        for (CartItem cartItem : request.getCartItems()) {
            if (cartItem.productId() >= INITIAL_ID_VALUE_CASE && cartItem.productId() <= FINAL_ID_VALUE_CASE) {
                if (cartItem.quantity() != 1) {
                    throw new IllegalArgumentException(); //todo
                }
                aCase aCase = caseDAO.findById(cartItem.productId()).orElseThrow(() -> new ProductNotFoundException(aCase.class, cartItem.productId()));
                computer.setACase(aCase);
            } else if (cartItem.productId() >= INITIAL_ID_VALUE_COOLER && cartItem.productId() <= FINAL_ID_VALUE_COOLER) {
                Cooler cooler = coolerDAO.findById(cartItem.productId()).orElseThrow(() -> new ProductNotFoundException(Cooler.class, cartItem.productId()));
                computer.addCooler(cooler);
            } else if (cartItem.productId() >= INITIAL_ID_VALUE_CPU && cartItem.productId() <= FINAL_ID_VALUE_CPU) {
                if (cartItem.quantity() != 1) {
                    throw new IllegalArgumentException(); //todo
                }
                CPU cpu = cpuDAO.findById(cartItem.productId()).orElseThrow(() -> new ProductNotFoundException(CPU.class, cartItem.productId()));
                computer.setCpu(cpu);
            } else if (cartItem.productId() >= INITIAL_ID_VALUE_CPU_COOLER && cartItem.productId() <= FINAL_ID_VALUE_CPU_COOLER) {
                if (cartItem.quantity() != 1) {
                    throw new IllegalArgumentException(); //todo
                }
                CPUCooler cpuCooler = cpuCoolerDAO.findById(cartItem.productId()).orElseThrow(() -> new ProductNotFoundException(CPUCooler.class, cartItem.productId()));
                computer.setCpuCooler(cpuCooler);
            } else if (cartItem.productId() >= INITIAL_ID_VALUE_GPU && cartItem.productId() <= FINAL_ID_VALUE_GPU) {
                if (cartItem.quantity() != 1) {
                    throw new IllegalArgumentException(); //todo
                }
                GPU gpu = gpuDAO.findById(cartItem.productId()).orElseThrow(() -> new ProductNotFoundException(GPU.class, cartItem.productId()));
                computer.setGpu(gpu);
            } else if (cartItem.productId() >= INITIAL_ID_VALUE_INTERNAL_HARD_DRIVE && cartItem.productId() <= FINAL_ID_VALUE_INTERNAL_HARD_DRIVE) {
                InternalHardDrive internalHardDrive = internalHardDriveDAO.findById(cartItem.productId()).orElseThrow(() -> new ProductNotFoundException(InternalHardDrive.class, cartItem.productId()));
                computer.addInternalHardDrive(internalHardDrive);
            } else if (cartItem.productId() >= INITIAL_ID_VALUE_MOTHERBOARD && cartItem.productId() <= FINAL_ID_VALUE_MOTHERBOARD) {
                if (cartItem.quantity() != 1) {
                    throw new IllegalArgumentException(); //todo
                }
                Motherboard motherboard = motherboardDAO.findById(cartItem.productId()).orElseThrow(() -> new ProductNotFoundException(Motherboard.class, cartItem.productId()));
                computer.setMotherboard(motherboard);
            } else if (cartItem.productId() >= INITIAL_ID_VALUE_POWER_SUPPLY && cartItem.productId() <= FINAL_ID_VALUE_POWER_SUPPLY) {
                if (cartItem.quantity() != 1) {
                    throw new IllegalArgumentException(); //todo
                }
                PowerSupply powerSupply = powerSupplyDAO.findById(cartItem.productId()).orElseThrow(() -> new ProductNotFoundException(PowerSupply.class, cartItem.productId()));
                computer.setPowerSupply(powerSupply);
            } else if (cartItem.productId() >= INITIAL_ID_VALUE_RAM && cartItem.productId() <= FINAL_ID_VALUE_RAM) {
                RAM ram = ramDAO.findById(cartItem.productId()).orElseThrow(() -> new ProductNotFoundException(RAM.class, cartItem.productId()));
                computer.addRAM(ram);
            } else if (cartItem.productId() >= INITIAL_ID_VALUE_EXTERNAL_HARD_DRIVE && cartItem.productId() <= FINAL_ID_VALUE_EXTERNAL_HARD_DRIVE) {
                ExternalHardDrive externalHardDrive = externalHardDriveDAO.findById(cartItem.productId()).orElseThrow(() -> new ProductNotFoundException(ExternalHardDrive.class, cartItem.productId()));
                computer.addExternalHardDrive(externalHardDrive);
            } else if (cartItem.productId() >= INITIAL_ID_VALUE_HEADSET && cartItem.productId() <= FINAL_ID_VALUE_HEADSET) {
                Headset headset = headsetDAO.findById(cartItem.productId()).orElseThrow(() -> new ProductNotFoundException(Headset.class, cartItem.productId()));
                computer.addHeadset(headset);
            } else if (cartItem.productId() >= INITIAL_ID_VALUE_KEYBOARD && cartItem.productId() <= FINAL_ID_VALUE_KEYBOARD) {
                Keyboard keyboard = keyboardDAO.findById(cartItem.productId()).orElseThrow(() -> new ProductNotFoundException(Keyboard.class, cartItem.productId()));
                computer.addKeyboard(keyboard);
            } else if (cartItem.productId() >= INITIAL_ID_VALUE_MONITOR && cartItem.productId() <= FINAL_ID_VALUE_MONITOR) {
                Monitor monitor = monitorDAO.findById(cartItem.productId()).orElseThrow(() -> new ProductNotFoundException(Monitor.class, cartItem.productId()));
                computer.addMonitor(monitor);
            } else if (cartItem.productId() >= INITIAL_ID_VALUE_MOUSE && cartItem.productId() <= FINAL_ID_VALUE_MOUSE) {
                Mouse mouse = mouseDAO.findById(cartItem.productId()).orElseThrow(() -> new ProductNotFoundException(Mouse.class, cartItem.productId()));
                computer.addMouse(mouse);
            } else if (cartItem.productId() >= INITIAL_ID_VALUE_SPEAKER && cartItem.productId() <= FINAL_ID_VALUE_SPEAKER) {
                Speaker speaker = speakerDAO.findById(cartItem.productId()).orElseThrow(() -> new ProductNotFoundException(Speaker.class, cartItem.productId()));
                computer.addSpeaker(speaker);
            } else {
                throw new IllegalArgumentException(); //todo
            }
        }
        return computer;
    }
}