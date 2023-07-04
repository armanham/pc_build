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
    private final Boolean isFullyConstructed;

    public ComputerDTO(final Computer entity) {
        this.id = entity.getId();
        if (entity.getACase() != null) {
            this.aCase = new CaseDTO(entity.getACase());
        }
        this.coolers = entity.getCoolers().stream().map(CoolerDTO::new).toList();
        if (entity.getCpuCooler() != null) {
            this.cpuCooler = new CPUCoolerDTO(entity.getCpuCooler());
        }
        if (entity.getCpu() != null) {
            this.cpu = new CPUDTO(entity.getCpu());
        }
        if (entity.getGpu() != null) {
            this.gpu = new GPUDTO(entity.getGpu());
        }
        this.internalHardDrives = entity.getInternalHardDrives().stream().map(InternalHardDriveDTO::new).toList();
        if (entity.getMotherboard() != null) {
            this.motherboard = new MotherboardDTO(entity.getMotherboard());
        }
        if (entity.getPowerSupply() != null) {
            this.powerSupply = new PowerSupplyDTO(entity.getPowerSupply());
        }
        this.rams = entity.getRams().stream().map(RAMDTO::new).toList();
        this.externalHardDrives = entity.getExternalHardDrives().stream().map(ExternalHardDriveDTO::new).toList();
        this.headsets = entity.getHeadsets().stream().map(HeadsetDTO::new).toList();
        this.keyboards = entity.getKeyboards().stream().map(KeyboardDTO::new).toList();
        this.monitors = entity.getMonitors().stream().map(MonitorDTO::new).toList();
        this.mice = entity.getMice().stream().map(MouseDTO::new).toList();
        this.speakers = entity.getSpeakers().stream().map(SpeakerDTO::new).toList();

        this.user = new UserDTO(entity.getUser());
        this.totalPrice = entity.getTotalPrice();
        this.isOrdered = entity.getIsOrdered();
        this.isFullyConstructed = entity.getIsFullyConstructed();
    }
}