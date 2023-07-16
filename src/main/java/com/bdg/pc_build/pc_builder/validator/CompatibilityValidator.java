package com.bdg.pc_build.pc_builder.validator;

import com.bdg.pc_build.pc_builder.model.entity.Computer;
import com.bdg.pc_build.exception.NotCompatibleException;
import com.bdg.pc_build.product.enumerations.ATXType;
import com.bdg.pc_build.product.enumerations.InternalHardDriveInterfaceType;
import com.bdg.pc_build.product.enumerations.TowerType;
import com.bdg.pc_build.product.model.entity.main.*;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Component
public class CompatibilityValidator {

    public void validateComputer(final Computer computer) {
        validateMotherboardWithRam(computer.getMotherboard(), computer.getRams());
        validateMotherboardWithCpu(computer.getMotherboard(), computer.getCpu());
        validateMotherboardWithCpuCooler(computer.getMotherboard(), computer.getCpuCooler());
        validateCpuWithCpuCooler(computer.getCpu(), computer.getCpuCooler());
        validateMotherboardWithCase(computer.getMotherboard(), computer.getACase());
        validateMotherboardWithInternalHardDrive(computer.getMotherboard(), computer.getInternalHardDrives());
        validateMotherboardWithGpu(computer.getMotherboard(), computer.getGpu());
        validateSumOfTdpsAndPowerSupplyWattage(computer);
    }

    private void validateMotherboardWithRam(Motherboard motherboardDTO, List<RAM> ramDTOs) {
        if (motherboardDTO != null && (ramDTOs != null && !ramDTOs.isEmpty())) {
            int countOfRams = 0;
            int sumOfGbOfRams = 0;

            for (RAM ramDTO : ramDTOs) {
                if (!motherboardDTO.getDdrType().equals(ramDTO.getDdrType())) {
                    throw new NotCompatibleException(HttpStatus.OK, "DDR type of Motherboard not compatible with DDR type of RAM: ");
                }

                countOfRams += ramDTO.getCountOfRam();
                sumOfGbOfRams += ramDTO.getGbOfRam() * ramDTO.getCountOfRam();
            }

            if (motherboardDTO.getMemorySlots() != countOfRams) {
                throw new NotCompatibleException(HttpStatus.OK, "Memory slots of the Motherboard not equal to count of rams of RAM: ");
            }

            if (motherboardDTO.getMemoryMax() < sumOfGbOfRams) {
                throw new NotCompatibleException(HttpStatus.OK, "Memory max of Motherboard is greater than sum of gb of RAM or equal to sum of gb of RAM: ");
            }
        }
    }

    private void validateMotherboardWithCpu(Motherboard motherboardDTO, CPU cpuDTO) {
        if (motherboardDTO != null && cpuDTO != null) {
            if (!motherboardDTO.getSocketType().equals(cpuDTO.getSocketType())) {
                throw new NotCompatibleException(HttpStatus.OK, "Socket type of Motherboard not compatible with Socket type of CPU: ");
            }
        }
    }

    private void validateCpuWithCpuCooler(CPU cpuDTO, CPUCooler cpuCoolerDTO) {
        if (cpuDTO != null && cpuCoolerDTO != null) {
            if (!cpuDTO.getSocketType().equals(cpuCoolerDTO.getSocketType())) {
                throw new NotCompatibleException(HttpStatus.OK, "Socket type of CPU not compatible with Socket type of CPU Cooler: ");
            }
        }
    }

    private void validateMotherboardWithCpuCooler(Motherboard motherboardDTO, CPUCooler CpuCoolerDTO) {
        if (motherboardDTO != null && CpuCoolerDTO != null) {
            if (!motherboardDTO.getSocketType().equals(CpuCoolerDTO.getSocketType())) {
                throw new NotCompatibleException(HttpStatus.OK, "Socket type of Motherboard not compatible with Socket type of CPU Cooler: ");
            }
        }
    }

    private void validateMotherboardWithCase(Motherboard motherboardDTO, aCase caseDTO) {
        if (motherboardDTO != null && caseDTO != null) {
            if (caseDTO.getTowerType().equals(TowerType.FULL) && motherboardDTO.getAtxType().equals(ATXType.M_ATX)) {
                throw new NotCompatibleException(HttpStatus.OK, "Tower type of Motherboard not compatible with Tower type of Case: ");
            }
            if (caseDTO.getTowerType().equals(TowerType.MID) && !motherboardDTO.getAtxType().equals(ATXType.ATX)) {
                throw new NotCompatibleException(HttpStatus.OK, "Tower type of Motherboard not compatible with Tower type of Case: ");
            }
            if (caseDTO.getTowerType().equals(TowerType.MINI) && !motherboardDTO.getAtxType().equals(ATXType.M_ATX)) {
                throw new NotCompatibleException(HttpStatus.OK, "Tower type of Motherboard not compatible with Tower type of Case: ");
            }
        }
    }

    private void validateMotherboardWithInternalHardDrive(Motherboard motherboardDTO, List<InternalHardDrive> internalHardDriveDTOs) {
        if (motherboardDTO != null && (internalHardDriveDTOs != null && !internalHardDriveDTOs.isEmpty())) {
            for (InternalHardDrive internalHardDriveDTO : internalHardDriveDTOs) {
                if (!motherboardDTO.getIsM2() && internalHardDriveDTO.getInternalHardDriveInterfaceType().equals(InternalHardDriveInterfaceType.SSD_M2)) {
                    throw new NotCompatibleException(HttpStatus.OK, "isM2 of Motherboard not compatible with Internal Hard Drive Interface type of Internal Hard Drive: ");
                }
            }
        }
    }


    private void validateMotherboardWithGpu(Motherboard motherboardDTO, GPU gpuDTO) {
        if (motherboardDTO != null && gpuDTO != null) {
            if (!motherboardDTO.getGpuInterfaceType().equals(gpuDTO.getGpuInterfaceType())) {
                throw new NotCompatibleException(HttpStatus.OK, "Gpu Interface type of Motherboard not compatible with Gpu Interface type of GPU: ");
            }
        }
    }

    private void validateSumOfTdpsAndPowerSupplyWattage(Computer computerDTO) {
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
                for (Cooler coolerDTO : computerDTO.getCoolers()) {
                    sumOfTdpsOfAllComponents += coolerDTO.getTdp();
                }
            }
            if (computerDTO.getRams() != null && !computerDTO.getRams().isEmpty()) {
                for (RAM ramDTO : computerDTO.getRams()) {
                    sumOfTdpsOfAllComponents += ramDTO.getTdp();
                }
            }
            if (computerDTO.getInternalHardDrives() != null && !computerDTO.getInternalHardDrives().isEmpty()) {
                for (InternalHardDrive internalHardDriveDTO : computerDTO.getInternalHardDrives()) {
                    sumOfTdpsOfAllComponents += internalHardDriveDTO.getTdp();
                }
            }

            if (sumOfTdpsOfAllComponents > computerDTO.getPowerSupply().getWattage()) {
                throw new NotCompatibleException(HttpStatus.OK, "Power Supply is weak for these materials: Wattage of Power Supply must be granter than sum of Tdps of all materials: ");
            }
        }
    }
}