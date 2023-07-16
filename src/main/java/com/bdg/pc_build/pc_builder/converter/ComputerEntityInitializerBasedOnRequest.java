package com.bdg.pc_build.pc_builder.converter;

import com.bdg.pc_build.pc_builder.model.entity.Computer;
import com.bdg.pc_build.exception.IdOutOfScopeException;
import com.bdg.pc_build.exception.NoProductFoundInBuilderException;
import com.bdg.pc_build.exception.NotCompatibleException;
import com.bdg.pc_build.exception.ProductNotFoundException;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main.*;
import com.bdg.pc_build.product.model.entity.peripheral.*;
import com.bdg.pc_build.product.repository.main.*;
import com.bdg.pc_build.product.repository.peripheral.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

import static com.bdg.pc_build.util.InitialAndFinalIdValues.*;

@RequiredArgsConstructor
@Component
public class ComputerEntityInitializerBasedOnRequest {

    private final CaseDAO caseDAO;
    private final CoolerDAO coolerDAO;
    private final CPUCoolerDAO cpuCoolerDAO;
    private final CPUDAO cpuDAO;
    private final GPUDAO gpuDAO;
    private final InternalHardDriveDAO internalHardDriveDAO;
    private final MotherboardDAO motherboardDAO;
    private final PowerSupplyDAO powerSupplyDAO;
    private final RAMDAO ramDAO;
    private final ExternalHardDriveDAO externalHardDriveDAO;
    private final MonitorDAO monitorDAO;
    private final HeadsetDAO headsetDAO;
    private final KeyboardDAO keyboardDAO;
    private final MouseDAO mouseDAO;
    private final SpeakerDAO speakerDAO;


    public Computer initEntityFromRequest(final Map<ProductDTO, Integer> components) {
        if (components.isEmpty()) {
            throw new NoProductFoundInBuilderException(HttpStatus.BAD_REQUEST);
        }

        Computer computer = new Computer();
        BigDecimal totalPrice = computer.getTotalPrice();

        for (ProductDTO component : components.keySet()) {
            if (component.getId() >= INITIAL_ID_VALUE_CASE && component.getId() <= FINAL_ID_VALUE_CASE) {
                if (components.get(component) != 1) {
                    throw new NotCompatibleException(HttpStatus.OK, "Count of Case should not exceed 1: ");
                }
                if (computer.getACase() != null) {
                    throw new NotCompatibleException(HttpStatus.OK, "Case is already selected: ");
                }
                aCase aCase = caseDAO.findById(component.getId()).orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, aCase.class, component.getId()));
                computer.setACase(aCase);
                totalPrice = totalPrice.add(BigDecimal.valueOf(aCase.getPrice()));
            } else if (component.getId() >= INITIAL_ID_VALUE_COOLER && component.getId() <= FINAL_ID_VALUE_COOLER) {
                Cooler cooler = coolerDAO.findById(component.getId()).orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, Cooler.class, component.getId()));
                for (int i = 0; i < components.get(component); i++) {
                    computer.addCooler(cooler);
                    totalPrice = totalPrice.add(BigDecimal.valueOf(cooler.getPrice()));
                }
            } else if (component.getId() >= INITIAL_ID_VALUE_CPU && component.getId() <= FINAL_ID_VALUE_CPU) {
                if (components.get(component) != 1) {
                    throw new NotCompatibleException(HttpStatus.OK, "Count of CPU should not exceed 1: ");
                }
                if (computer.getCpu() != null) {
                    throw new NotCompatibleException(HttpStatus.OK, "CPU is already selected: ");
                }
                CPU cpu = cpuDAO.findById(component.getId()).orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, CPU.class, component.getId()));
                computer.setCpu(cpu);
                totalPrice = totalPrice.add(BigDecimal.valueOf(cpu.getPrice()));
            } else if (component.getId() >= INITIAL_ID_VALUE_CPU_COOLER && component.getId() <= FINAL_ID_VALUE_CPU_COOLER) {
                if (components.get(component) != 1) {
                    throw new NotCompatibleException(HttpStatus.OK, "Count of CPU Cooler should not exceed 1: ");
                }
                if (computer.getCpuCooler() != null) {
                    throw new NotCompatibleException(HttpStatus.OK, "CPU Cooler is already selected: ");
                }
                CPUCooler cpuCooler = cpuCoolerDAO.findById(component.getId()).orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, CPUCooler.class, component.getId()));
                computer.setCpuCooler(cpuCooler);
                totalPrice = totalPrice.add(BigDecimal.valueOf(cpuCooler.getPrice()));
            } else if (component.getId() >= INITIAL_ID_VALUE_GPU && component.getId() <= FINAL_ID_VALUE_GPU) {
                if (components.get(component) != 1) {
                    throw new NotCompatibleException(HttpStatus.OK, "Count of GPU should not exceed 1: ");
                }
                if (computer.getGpu() != null) {
                    throw new NotCompatibleException(HttpStatus.OK, "GPU is already selected: ");
                }
                GPU gpu = gpuDAO.findById(component.getId()).orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, GPU.class, component.getId()));
                computer.setGpu(gpu);
                totalPrice = totalPrice.add(BigDecimal.valueOf(gpu.getPrice()));
            } else if (component.getId() >= INITIAL_ID_VALUE_INTERNAL_HARD_DRIVE && component.getId() <= FINAL_ID_VALUE_INTERNAL_HARD_DRIVE) {
                InternalHardDrive internalHardDrive = internalHardDriveDAO.findById(component.getId()).orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, InternalHardDrive.class, component.getId()));
                for (int i = 0; i < components.get(component); i++) {
                    computer.addInternalHardDrive(internalHardDrive);
                    totalPrice = totalPrice.add(BigDecimal.valueOf(internalHardDrive.getPrice()));
                }
            } else if (component.getId() >= INITIAL_ID_VALUE_MOTHERBOARD && component.getId() <= FINAL_ID_VALUE_MOTHERBOARD) {
                if (components.get(component) != 1) {
                    throw new NotCompatibleException(HttpStatus.OK, "Count of Motherboard should not exceed 1: ");
                }
                if (computer.getMotherboard() != null) {
                    throw new NotCompatibleException(HttpStatus.OK, "Motherboard is already selected: ");
                }
                Motherboard motherboard = motherboardDAO.findById(component.getId()).orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, Motherboard.class, component.getId()));
                computer.setMotherboard(motherboard);
                totalPrice = totalPrice.add(BigDecimal.valueOf(motherboard.getPrice()));
            } else if (component.getId() >= INITIAL_ID_VALUE_POWER_SUPPLY && component.getId() <= FINAL_ID_VALUE_POWER_SUPPLY) {
                if (components.get(component) != 1) {
                    throw new NotCompatibleException(HttpStatus.OK, "Count of Power Supply should not exceed 1: ");
                }
                if (computer.getPowerSupply() != null) {
                    throw new NotCompatibleException(HttpStatus.OK, "Power Supply is already selected: ");
                }
                PowerSupply powerSupply = powerSupplyDAO.findById(component.getId()).orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, PowerSupply.class, component.getId()));
                computer.setPowerSupply(powerSupply);
                totalPrice = totalPrice.add(BigDecimal.valueOf(powerSupply.getPrice()));
            } else if (component.getId() >= INITIAL_ID_VALUE_RAM && component.getId() <= FINAL_ID_VALUE_RAM) {
                RAM ram = ramDAO.findById(component.getId()).orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, RAM.class, component.getId()));
                for (int i = 0; i < components.get(component); i++) {
                    computer.addRam(ram);
                    totalPrice = totalPrice.add(BigDecimal.valueOf(ram.getPrice()));
                }
            } else if (component.getId() >= INITIAL_ID_VALUE_EXTERNAL_HARD_DRIVE && component.getId() <= FINAL_ID_VALUE_EXTERNAL_HARD_DRIVE) {
                ExternalHardDrive externalHardDrive = externalHardDriveDAO.findById(component.getId()).orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, ExternalHardDrive.class, component.getId()));
                for (int i = 0; i < components.get(component); i++) {
                    computer.addExternalHardDrive(externalHardDrive);
                    totalPrice = totalPrice.add(BigDecimal.valueOf(externalHardDrive.getPrice()));
                }
            } else if (component.getId() >= INITIAL_ID_VALUE_HEADSET && component.getId() <= FINAL_ID_VALUE_HEADSET) {
                Headset headset = headsetDAO.findById(component.getId()).orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, Headset.class, component.getId()));
                for (int i = 0; i < components.get(component); i++) {
                    computer.addHeadset(headset);
                    totalPrice = totalPrice.add(BigDecimal.valueOf(headset.getPrice()));
                }
            } else if (component.getId() >= INITIAL_ID_VALUE_KEYBOARD && component.getId() <= FINAL_ID_VALUE_KEYBOARD) {
                Keyboard keyboard = keyboardDAO.findById(component.getId()).orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, Keyboard.class, component.getId()));
                for (int i = 0; i < components.get(component); i++) {
                    computer.addKeyboard(keyboard);
                    totalPrice = totalPrice.add(BigDecimal.valueOf(keyboard.getPrice()));
                }
            } else if (component.getId() >= INITIAL_ID_VALUE_MONITOR && component.getId() <= FINAL_ID_VALUE_MONITOR) {
                Monitor monitor = monitorDAO.findById(component.getId()).orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, Monitor.class, component.getId()));
                for (int i = 0; i < components.get(component); i++) {
                    computer.addMonitor(monitor);
                    totalPrice = totalPrice.add(BigDecimal.valueOf(monitor.getPrice()));
                }
            } else if (component.getId() >= INITIAL_ID_VALUE_MOUSE && component.getId() <= FINAL_ID_VALUE_MOUSE) {
                Mouse mouse = mouseDAO.findById(component.getId()).orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, Mouse.class, component.getId()));
                for (int i = 0; i < components.get(component); i++) {
                    computer.addMouse(mouse);
                    totalPrice = totalPrice.add(BigDecimal.valueOf(mouse.getPrice()));
                }
            } else if (component.getId() >= INITIAL_ID_VALUE_SPEAKER && component.getId() <= FINAL_ID_VALUE_SPEAKER) {
                Speaker speaker = speakerDAO.findById(component.getId()).orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, Speaker.class, component.getId()));
                for (int i = 0; i < components.get(component); i++) {
                    computer.addSpeaker(speaker);
                    totalPrice = totalPrice.add(BigDecimal.valueOf(speaker.getPrice()));
                }
            } else {
                throw new IdOutOfScopeException(HttpStatus.CONFLICT, component.getId());
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