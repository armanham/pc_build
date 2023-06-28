package com.bdg.pc_build.computer_builder.model.dto;


import com.bdg.pc_build.computer_builder.model.entity.Computer;
import com.bdg.pc_build.product.model.dto.main_component.*;
import com.bdg.pc_build.product.model.dto.peripheral.*;
import com.bdg.pc_build.user.model.dto.UserDTO;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class ComputerDTO {

    private final Long id;
    private CaseDTO aCase;
    private final List<CoolerDTO> coolers;
    private CPUDTO cpu;
    private CPUCoolerDTO cpuCooler;
    private GPUDTO gpu;
    private final List<InternalHardDriveDTO> internalHardDrives;
    private MotherboardDTO motherboard;
    private PowerSupplyDTO powerSupply;
    private final List<RAMDTO> rams;
    private final List<ExternalHardDriveDTO> externalHardDrives;
    private final List<HeadsetDTO> headsets;
    private final List<KeyboardDTO> keyboards;
    private final List<MonitorDTO> monitors;
    private final List<MouseDTO> mice;
    private final List<SpeakerDTO> speakers;
    private final UserDTO user;
    private final BigDecimal totalPrice;
    private final Boolean isOrdered;

    public ComputerDTO(final Computer entity) {
        this.id = entity.getId();
        if (entity.getACase() != null) {
            this.aCase = CaseDTO.initDTOFromEntity(entity.getACase());
        }
        this.coolers = entity.getCoolers().stream().map(CoolerDTO::initDTOFromEntity).toList();
        if (entity.getCpuCooler() != null) {
            this.cpuCooler = CPUCoolerDTO.initDTOFromEntity(entity.getCpuCooler());
        }
        if (entity.getCpu() != null) {
            this.cpu = CPUDTO.initDTOFromEntity(entity.getCpu());
        }
        if (entity.getGpu() != null) {
            this.gpu = GPUDTO.initDTOFromEntity(entity.getGpu());
        }
        this.internalHardDrives = entity.getInternalHardDrives().stream().map(InternalHardDriveDTO::initDTOFromEntity).toList();
        if (entity.getMotherboard() != null) {
            this.motherboard = MotherboardDTO.initDTOFromEntity(entity.getMotherboard());
        }
        if (entity.getPowerSupply() != null) {
            this.powerSupply = PowerSupplyDTO.initDTOFromEntity(entity.getPowerSupply());
        }
        this.rams = entity.getRams().stream().map(RAMDTO::initDTOFromEntity).toList();
        this.externalHardDrives = entity.getExternalHardDrives().stream().map(ExternalHardDriveDTO::initDTOFromEntity).toList();
        this.headsets = entity.getHeadsets().stream().map(HeadsetDTO::initDTOFromEntity).toList();
        this.keyboards = entity.getKeyboards().stream().map(KeyboardDTO::initDTOFromEntity).toList();
        this.monitors = entity.getMonitors().stream().map(MonitorDTO::initDTOFromEntity).toList();
        this.mice = entity.getMice().stream().map(MouseDTO::initDTOFromEntity).toList();
        this.speakers = entity.getSpeakers().stream().map(SpeakerDTO::initDTOFromEntity).toList();

        this.user = new UserDTO(entity.getUser());
        this.totalPrice = entity.getTotalPrice();
        this.isOrdered = entity.getIsOrdered();
    }
}