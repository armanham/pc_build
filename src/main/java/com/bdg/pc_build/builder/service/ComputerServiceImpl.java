package com.bdg.pc_build.builder.service;

import com.bdg.pc_build.builder.model.dto.ComputerDTO;
import com.bdg.pc_build.builder.model.request.ComputerCreationRequest;
import com.bdg.pc_build.checking.exception.OutOfStockException;
import com.bdg.pc_build.checking.exception.ProductNotFoundException;
import com.bdg.pc_build.product.model.dto.main_component.*;
import com.bdg.pc_build.product.model.dto.peripheral.*;
import com.bdg.pc_build.product.model.entity.main_component.*;
import com.bdg.pc_build.product.model.entity.peripheral.*;
import com.bdg.pc_build.product.repository.main_component.*;
import com.bdg.pc_build.product.repository.peripheral.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class ComputerServiceImpl implements ComputerService {

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


    public ComputerDTO initComputerDTOFromRequest(final ComputerCreationRequest request) {
        ComputerDTO computerDTO = new ComputerDTO();
        double price = 0;

        setCase(request, computerDTO, price);
        setCooler(request, computerDTO, price);
        setCpu(request, computerDTO, price);
        setCpuCooler(request, computerDTO, price);
        setGpu(request, computerDTO, price);
        setInternalHardDrive(request, computerDTO, price);
        setMotherboard(request, computerDTO);
        setPowerSupply(request, computerDTO, price);
        setRam(request, computerDTO, price);
        setExternalHardDrive(request, computerDTO, price);
        setHeadset(request, computerDTO, price);
        setKeyboard(request, computerDTO, price);
        setMonitor(request, computerDTO, price);
        setMouse(request, computerDTO, price);
        setSpeaker(request, computerDTO, price);

//        computerDTO.setPrice(price);
        return computerDTO;
    }

    private void setCase(ComputerCreationRequest request, ComputerDTO computerDTO, double price) {
        if (request.getACaseName() != null) {
            Optional<aCase> optionalACase = caseDAO.findByName(request.getACaseName());
            if (optionalACase.isEmpty()) {
                throw new ProductNotFoundException(aCase.class, request.getACaseName());
            }
            CaseDTO caseDTO = CaseDTO.initDTOFromEntity(optionalACase.get());
            if (caseDTO.getCount() == 0) {
                throw new OutOfStockException(aCase.class, caseDTO.getName(), 0);
            }
            computerDTO.setACase(caseDTO);
            computerDTO.setPrice(computerDTO.getPrice() + caseDTO.getPrice());
        }
    }

    private void setCooler(ComputerCreationRequest request, ComputerDTO computerDTO, double price) {
        if (request.getCoolerNames() != null && !request.getCoolerNames().isEmpty()) {
            List<CoolerDTO> coolerDTOs = new ArrayList<>();

            for (String coolerName : request.getCoolerNames()) {
                Optional<Cooler> optionalCooler = coolerDAO.findByName(coolerName);
                if (optionalCooler.isEmpty()) {
                    throw new ProductNotFoundException(Cooler.class, coolerName);
                }
                CoolerDTO coolerDTO = CoolerDTO.initDTOFromEntity(optionalCooler.get());
                coolerDTOs.add(coolerDTO);
                price += coolerDTO.getPrice();
            }

            computerDTO.setCoolers(coolerDTOs);
        }
    }

    private void setCpu(ComputerCreationRequest request, ComputerDTO computerDTO, double price) {
        if (request.getCpuName() != null) {
            Optional<CPU> optionalCpu = cpuDAO.findByName(request.getCpuName());
            if (optionalCpu.isEmpty()) {
                throw new ProductNotFoundException(CPU.class, request.getCpuName());
            }
            CPUDTO cpuDTO = CPUDTO.initDTOFromEntity(optionalCpu.get());
            computerDTO.setCpu(cpuDTO);
            price += cpuDTO.getPrice();
        }
    }

    private void setCpuCooler(ComputerCreationRequest request, ComputerDTO computerDTO, double price) {
        if (request.getCpuCoolerName() != null) {
            Optional<CPUCooler> optionalCpuCooler = cpuCoolerDAO.findByName(request.getCpuCoolerName());
            if (optionalCpuCooler.isEmpty()) {
                throw new ProductNotFoundException(CPUCooler.class, request.getCpuCoolerName());
            }
            CPUCoolerDTO cpuCoolerDTO = CPUCoolerDTO.initDTOFromEntity(optionalCpuCooler.get());
            computerDTO.setCpuCooler(cpuCoolerDTO);
            price += cpuCoolerDTO.getPrice();
        }
    }

    private void setGpu(ComputerCreationRequest request, ComputerDTO computerDTO, double price) {
        if (request.getGpuName() != null) {
            Optional<GPU> optionalGpu = gpuDAO.findByName(request.getGpuName());
            if (optionalGpu.isEmpty()) {
                throw new ProductNotFoundException(GPU.class, request.getGpuName());
            }
            GPUDTO gpuDTO = GPUDTO.initDTOFromEntity(optionalGpu.get());
            computerDTO.setGpu(gpuDTO);
            computerDTO.setPrice(computerDTO.getPrice() + gpuDTO.getPrice());
        }
    }

    private void setInternalHardDrive(ComputerCreationRequest request, ComputerDTO computerDTO, double price) {
        if (request.getInternalHardDriveNames() != null && !request.getInternalHardDriveNames().isEmpty()) {
            List<InternalHardDriveDTO> internalHardDriveDTOs = new ArrayList<>();

            for (String internalHardDriveName : request.getInternalHardDriveNames()) {
                Optional<InternalHardDrive> optionalInternalHardDrive = internalHardDriveDAO.findByName(internalHardDriveName);
                if (optionalInternalHardDrive.isEmpty()) {
                    throw new ProductNotFoundException(InternalHardDrive.class, internalHardDriveName);
                }
                InternalHardDriveDTO internalHardDriveDTO = InternalHardDriveDTO.initDTOFromEntity(optionalInternalHardDrive.get());
                internalHardDriveDTOs.add(internalHardDriveDTO);
                price += internalHardDriveDTO.getPrice();
            }

            computerDTO.setInternalHardDrives(internalHardDriveDTOs);
        }
    }

    private void setMotherboard(ComputerCreationRequest request, ComputerDTO computerDTO) {
        if (request.getMotherboardName() != null) {
            Optional<Motherboard> optionalMotherboard = motherboardDAO.findByName(request.getMotherboardName());
            if (optionalMotherboard.isEmpty()) {
                throw new ProductNotFoundException(Motherboard.class, request.getMotherboardName());
            }
            MotherboardDTO motherboardDTO = MotherboardDTO.initDTOFromEntity(optionalMotherboard.get());
            computerDTO.setMotherboard(motherboardDTO);
            computerDTO.setPrice(computerDTO.getPrice() + motherboardDTO.getPrice());
        }
    }

    private void setPowerSupply(ComputerCreationRequest request, ComputerDTO computerDTO, double price) {
        if (request.getPowerSupplyName() != null) {
            Optional<PowerSupply> optionalPowerSupply = powerSupplyDAO.findByName(request.getPowerSupplyName());
            if (optionalPowerSupply.isEmpty()) {
                throw new ProductNotFoundException(PowerSupply.class, request.getPowerSupplyName());
            }
            PowerSupplyDTO powerSupplyDTO = PowerSupplyDTO.initDTOFromEntity(optionalPowerSupply.get());
            computerDTO.setPowerSupply(powerSupplyDTO);
            price += powerSupplyDTO.getPrice();
        }
    }

    private void setRam(ComputerCreationRequest request, ComputerDTO computerDTO, double price) {
        if (request.getRamNames() != null && !request.getRamNames().isEmpty()) {
            List<RAMDTO> ramDTOs = new ArrayList<>();

            for (String RamName : request.getRamNames()) {
                Optional<RAM> optionalRam = ramDAO.findByName(RamName);
                if (optionalRam.isEmpty()) {
                    throw new ProductNotFoundException(RAM.class, RamName);
                }
                RAMDTO ramDTO = RAMDTO.initDTOFromEntity(optionalRam.get());
                ramDTOs.add(ramDTO);
                price += ramDTO.getPrice();
            }

            computerDTO.setRams(ramDTOs);
        }
    }

    private void setExternalHardDrive(ComputerCreationRequest request, ComputerDTO computerDTO, double price) {
        if (request.getExternalHardDriveNames() != null && !request.getExternalHardDriveNames().isEmpty()) {
            List<ExternalHardDriveDTO> externalHardDriveDTOS = new ArrayList<>();

            for (String externalHardDriveName : request.getExternalHardDriveNames()) {
                Optional<ExternalHardDrive> optionalExternalHardDrive = externalHardDriveDAO.findByName(externalHardDriveName);
                if (optionalExternalHardDrive.isEmpty()) {
                    throw new ProductNotFoundException(ExternalHardDrive.class, externalHardDriveName);
                }
                ExternalHardDriveDTO externalHardDriveDTO = ExternalHardDriveDTO.initDTOFromEntity(optionalExternalHardDrive.get());
                externalHardDriveDTOS.add(externalHardDriveDTO);
                price += externalHardDriveDTO.getPrice();
            }

            computerDTO.setExternalHardDrives(externalHardDriveDTOS);
        }
    }

    private void setHeadset(ComputerCreationRequest request, ComputerDTO computerDTO, double price) {
        if (request.getHeadsetNames() != null && !request.getHeadsetNames().isEmpty()) {
            List<HeadsetDTO> headsetDTOs = new ArrayList<>();

            for (String headsetName : request.getHeadsetNames()) {
                Optional<Headset> optionalHeadset = headsetDAO.findByName(headsetName);
                if (optionalHeadset.isEmpty()) {
                    throw new ProductNotFoundException(Headset.class, headsetName);
                }
                HeadsetDTO headsetDTO = HeadsetDTO.initDTOFromEntity(optionalHeadset.get());
                headsetDTOs.add(headsetDTO);
                price += headsetDTO.getPrice();
            }

            computerDTO.setHeadsets(headsetDTOs);
        }
    }

    private void setKeyboard(ComputerCreationRequest request, ComputerDTO computerDTO, double price) {
        if (request.getKeyboardNames() != null && !request.getKeyboardNames().isEmpty()) {
            List<KeyboardDTO> keyboardDTOs = new ArrayList<>();

            for (String keyboardName : request.getKeyboardNames()) {
                Optional<Keyboard> optionalKeyboard = keyboardDAO.findByName(keyboardName);
                if (optionalKeyboard.isEmpty()) {
                    throw new ProductNotFoundException(Keyboard.class, keyboardName);
                }
                KeyboardDTO keyboardDTO = KeyboardDTO.initDTOFromEntity(optionalKeyboard.get());
                keyboardDTOs.add(keyboardDTO);
                price += keyboardDTO.getPrice();
            }

            computerDTO.setKeyboards(keyboardDTOs);
        }
    }

    private void setMonitor(ComputerCreationRequest request, ComputerDTO computerDTO, double price) {
        if (request.getMonitorNames() != null && !request.getMonitorNames().isEmpty()) {
            List<MonitorDTO> monitorDTOs = new ArrayList<>();

            for (String monitorName : request.getMonitorNames()) {
                Optional<Monitor> optionalMonitor = monitorDAO.findByName(monitorName);
                if (optionalMonitor.isEmpty()) {
                    throw new ProductNotFoundException(Monitor.class, monitorName);
                }
                MonitorDTO monitorDTO = MonitorDTO.initDTOFromEntity(optionalMonitor.get());
                monitorDTOs.add(monitorDTO);
                price += monitorDTO.getPrice();
            }

            computerDTO.setMonitors(monitorDTOs);
        }
    }

    private void setMouse(ComputerCreationRequest request, ComputerDTO computerDTO, double price) {
        if (request.getMouseNames() != null && !request.getMouseNames().isEmpty()) {
            List<MouseDTO> mouseDTOs = new ArrayList<>();

            for (String mouseName : request.getMouseNames()) {
                Optional<Mouse> optionalMouse = mouseDAO.findByName(mouseName);
                if (optionalMouse.isEmpty()) {
                    throw new ProductNotFoundException(Mouse.class, mouseName);
                }
                MouseDTO mouseDTO = MouseDTO.initDTOFromEntity(optionalMouse.get());
                mouseDTOs.add(mouseDTO);
                price += mouseDTO.getPrice();
            }


            computerDTO.setMouses(mouseDTOs);
        }
    }

    private void setSpeaker(ComputerCreationRequest request, ComputerDTO computerDTO, double price) {
        if (request.getSpeakerNames() != null && !request.getSpeakerNames().isEmpty()) {
            List<SpeakerDTO> speakerDTOs = new ArrayList<>();

            for (String speakerName : request.getSpeakerNames()) {
                Optional<Speaker> optionalSpeaker = speakerDAO.findByName(speakerName);
                if (optionalSpeaker.isEmpty()) {
                    throw new ProductNotFoundException(Speaker.class, speakerName);
                }
                SpeakerDTO speakerDTO = SpeakerDTO.initDTOFromEntity(optionalSpeaker.get());
                speakerDTOs.add(speakerDTO);
                price += speakerDTO.getPrice();
            }

            computerDTO.setSpeakers(speakerDTOs);
        }
    }
}