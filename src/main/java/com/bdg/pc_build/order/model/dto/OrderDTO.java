package com.bdg.pc_build.order.model.dto;

import com.bdg.pc_build.order.enumerations.OrderStatus;
import com.bdg.pc_build.order.model.entity.Order;
import com.bdg.pc_build.product.model.dto.main_component.*;
import com.bdg.pc_build.product.model.dto.peripheral.*;
import com.bdg.pc_build.user.model.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@JsonRootName(value = "order")
@Getter
public class OrderDTO {

    private final Long id;
    private final List<CaseDTO> cases;
    private final List<CoolerDTO> coolers;
    private final List<CPUDTO> cpus;
    private final List<CPUCoolerDTO> cpuCoolers;
    private final List<GPUDTO> gpus;
    private final List<InternalHardDriveDTO> internalHardDrives;
    private final List<MotherboardDTO> motherboards;
    private final List<PowerSupplyDTO> powerSupplies;
    private final List<RAMDTO> rams;
    private final List<ExternalHardDriveDTO> externalHardDrives;
    private final List<HeadsetDTO> headsets;
    private final List<KeyboardDTO> keyboards;
    private final List<MonitorDTO> monitors;
    private final List<MouseDTO> mice;
    private final List<SpeakerDTO> speakers;
    private final UserDTO user;
    private final BigDecimal totalPrice;
    private final Boolean isFromBuilder;
    private final OrderStatus status;

    public OrderDTO(final Order entity) {
        this.id = entity.getId();
        this.cases = entity.getCases().stream().map(CaseDTO::new).toList();
        this.coolers = entity.getCoolers().stream().map(CoolerDTO::new).toList();
        this.cpuCoolers = entity.getCpuCoolers().stream().map(CPUCoolerDTO::new).toList();
        this.cpus = entity.getCpus().stream().map(CPUDTO::new).toList();
        this.gpus = entity.getGpus().stream().map(GPUDTO::new).toList();
        this.internalHardDrives = entity.getInternalHardDrives().stream().map(InternalHardDriveDTO::new).collect(Collectors.toList());
        this.motherboards = entity.getMotherboards().stream().map(MotherboardDTO::new).toList();
        this.powerSupplies = entity.getPowerSupplies().stream().map(PowerSupplyDTO::new).toList();
        this.rams = entity.getRams().stream().map(RAMDTO::new).toList();
        this.externalHardDrives = entity.getExternalHardDrives().stream().map(ExternalHardDriveDTO::new).toList();
        this.headsets = entity.getHeadsets().stream().map(HeadsetDTO::new).toList();
        this.keyboards = entity.getKeyboards().stream().map(KeyboardDTO::new).toList();
        this.monitors = entity.getMonitors().stream().map(MonitorDTO::new).toList();
        this.mice = entity.getMice().stream().map(MouseDTO::new).toList();
        this.speakers = entity.getSpeakers().stream().map(SpeakerDTO::new).toList();

        this.user = new UserDTO(entity.getUser());
        this.totalPrice = entity.getTotalPrice();
        this.isFromBuilder = entity.getIsFromBuilder();
        this.status = entity.getStatus();
    }
}