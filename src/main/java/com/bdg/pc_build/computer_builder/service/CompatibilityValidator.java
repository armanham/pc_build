package com.bdg.pc_build.computer_builder.service;

import com.bdg.pc_build.computer_builder.model.dto.ComputerDTO;
import com.bdg.pc_build.exception.NotCompatibleException;
import com.bdg.pc_build.product.model.dto.main_component.*;
import com.bdg.pc_build.product.enumerations.ATXType;
import com.bdg.pc_build.product.enumerations.InternalHardDriveInterfaceType;
import com.bdg.pc_build.product.enumerations.TowerType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public class CompatibilityValidator {

    ComputerDTO computerDTOToCompatibilityCheck;

    public CompatibilityValidator(final ComputerDTO computerDTO) {
        validateMotherboardWithRam(computerDTO.getMotherboard(), computerDTO.getRams());
        validateMotherboardWithCpu(computerDTO.getMotherboard(), computerDTO.getCpu());
        validateMotherboardWithCpuCooler(computerDTO.getMotherboard(), computerDTO.getCpuCooler());
        validateCpuWithCpuCooler(computerDTO.getCpu(), computerDTO.getCpuCooler());
        validateMotherboardWithCase(computerDTO.getMotherboard(), computerDTO.getACase());
        validateMotherboardWithInternalHardDrive(computerDTO.getMotherboard(), computerDTO.getInternalHardDrives());
        validateMotherboardWithGpu(computerDTO.getMotherboard(), computerDTO.getGpu());
        validateSumOfTdpsAndPowerSupplyWattage(computerDTO);

        this.computerDTOToCompatibilityCheck = computerDTO;
    }

    private void validateMotherboardWithRam(MotherboardDTO motherboardDTO, List<RAMDTO> ramDTOs) {
        if (motherboardDTO != null && (ramDTOs != null && !ramDTOs.isEmpty())) {
            int countOfRams = 0;
            int sumOfGbOfRams = 0;

            for (RAMDTO ramDTO : ramDTOs) {
                if (!motherboardDTO.getDdrType().equals(ramDTO.getDdrType())) {
                    //TODO NotCompatibleException
                    throw new NotCompatibleException(" Motherboard not compatible with  " + ramDTO.getDdrType());
                }

                countOfRams += ramDTO.getCountOfRam();
                sumOfGbOfRams += ramDTO.getGbOfRam() * ramDTO.getCountOfRam();
            }

            if (motherboardDTO.getMemorySlots() != countOfRams) {
                throw new IllegalArgumentException();
            }

            if (motherboardDTO.getMemoryMax() < sumOfGbOfRams) {
                throw new IllegalArgumentException();
            }
        }
    }

    private void validateMotherboardWithCpu(MotherboardDTO motherboardDTO, CPUDTO cpuDTO) {
        if (motherboardDTO != null && cpuDTO != null) {
            if (!motherboardDTO.getSocketType().equals(cpuDTO.getSocketType())) {
                throw new NotCompatibleException(" Motherboard not compatible with  cpu  " );
            }
        }
    }

    private void validateCpuWithCpuCooler(CPUDTO cpuDTO, CPUCoolerDTO cpuCoolerDTO) {
        if (cpuDTO != null && cpuCoolerDTO != null) {
            if (!cpuDTO.getSocketType().equals(cpuCoolerDTO.getSocketType())) {
                throw new NotCompatibleException(" cpu not compatible with  cpu cooler " );
            }
        }
    }

    private void validateMotherboardWithCpuCooler(MotherboardDTO motherboardDTO, CPUCoolerDTO CpuCoolerDTO) {
        if (motherboardDTO != null && CpuCoolerDTO != null) {
            if (!motherboardDTO.getSocketType().equals(CpuCoolerDTO.getSocketType())) {
                throw new NotCompatibleException(" motherboard not compatible with  cpu cooler");
            }
        }
    }

    private void validateMotherboardWithCase(MotherboardDTO motherboardDTO, CaseDTO caseDTO) {
        if (motherboardDTO != null && caseDTO != null) {
            if (caseDTO.getTowerType().equals(TowerType.FULL) && motherboardDTO.getAtxType().equals(ATXType.M_ATX)) {
                throw new NotCompatibleException(" case not compatible with  motherboard");
            }
            if (caseDTO.getTowerType().equals(TowerType.MID) && !motherboardDTO.getAtxType().equals(ATXType.ATX)) {
                throw new NotCompatibleException(" case not compatible with  motherboard");
            }
            if (caseDTO.getTowerType().equals(TowerType.MINI) && !motherboardDTO.getAtxType().equals(ATXType.M_ATX)) {
                throw new NotCompatibleException(" case not compatible with  motherboard");
            }
        }
    }

    private void validateMotherboardWithInternalHardDrive(MotherboardDTO motherboardDTO, List<InternalHardDriveDTO> internalHardDriveDTOs) {
        if (motherboardDTO != null && (internalHardDriveDTOs != null && !internalHardDriveDTOs.isEmpty())) {
            for (InternalHardDriveDTO internalHardDriveDTO : internalHardDriveDTOs) {
                if (!motherboardDTO.getIsM2() && internalHardDriveDTO.getInternalHardDriveInterfaceType().equals(InternalHardDriveInterfaceType.SSD_M2)) {
                    throw new NotCompatibleException(" motherboard not compatible with  internal hard drive");
                }
            }
        }
    }


    private void validateMotherboardWithGpu(MotherboardDTO motherboardDTO, GPUDTO gpuDTO) {
        if (motherboardDTO != null && gpuDTO != null) {
            if (!motherboardDTO.getGpuInterfaceType().equals(gpuDTO.getGpuInterfaceType())) {
                throw new NotCompatibleException(" Motherboard not compatible with  GPU interface");
            }
        }
    }

    private void validateSumOfTdpsAndPowerSupplyWattage(ComputerDTO computerDTO) {
        if (computerDTO.getPowerSupply() != null) {
            int sumOfTdpsOfAllComponents = computerDTO.getPowerSupply().getTdp();

            if (computerDTO.getCpu() != null) {
                sumOfTdpsOfAllComponents += computerDTO.getCpu().getTdp();
            }
            if (computerDTO.getCpuCooler() != null) {
                sumOfTdpsOfAllComponents += computerDTO.getCpuCooler().getTdp();
            }
            if (computerDTO.getMotherboard() != null) {
                sumOfTdpsOfAllComponents += computerDTO.getMotherboard().getTdp();
            }
            if (computerDTO.getGpu() != null) {
                sumOfTdpsOfAllComponents += computerDTO.getGpu().getTdp();
            }
            if (computerDTO.getCoolers() != null && !computerDTO.getCoolers().isEmpty()) {
                for (CoolerDTO coolerDTO : computerDTO.getCoolers()) {
                    sumOfTdpsOfAllComponents += coolerDTO.getTdp();
                }
            }
            if (computerDTO.getRams() != null && !computerDTO.getRams().isEmpty()) {
                for (RAMDTO ramDTO : computerDTO.getRams()) {
                    sumOfTdpsOfAllComponents += ramDTO.getTdp();
                }
            }
            if (computerDTO.getInternalHardDrives() != null && !computerDTO.getInternalHardDrives().isEmpty()) {
                for (InternalHardDriveDTO internalHardDriveDTO : computerDTO.getInternalHardDrives()) {
                    sumOfTdpsOfAllComponents += internalHardDriveDTO.getTdp();
                }
            }

            if (sumOfTdpsOfAllComponents > computerDTO.getPowerSupply().getWattage()) {
                throw new NotCompatibleException("your power supply is weak for these materials");
            }
        }
    }
}