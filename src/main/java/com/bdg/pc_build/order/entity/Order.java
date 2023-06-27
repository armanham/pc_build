package com.bdg.pc_build.order.entity;

import com.bdg.pc_build.order.enumerations.OrderStatus;
import com.bdg.pc_build.product.model.entity.main_component.*;
import com.bdg.pc_build.product.model.entity.peripheral.*;
import com.bdg.pc_build.user.model.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "_order", schema = "_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToMany
    @JoinTable(name = "order_case", schema = "_order")
    private List<aCase> cases = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "order_cooler", schema = "_order")
    private List<Cooler> coolers = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "order_cpu", schema = "_order")
    private List<CPU> cpus = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "order_cpu_cooler", schema = "_order")
    private List<CPUCooler> cpuCoolers = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "order_gpu", schema = "_order")
    private List<GPU> gpus = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "order_internal_hard_drive", schema = "_order")
    private List<InternalHardDrive> internalHardDrives = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "order_motherboard", schema = "_order")
    private List<Motherboard> motherboards = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "order_power_supply", schema = "_order")
    private List<PowerSupply> powerSupplies = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "order_ram", schema = "_order")
    private List<RAM> rams = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "order_external_hard_drive", schema = "_order")
    private List<ExternalHardDrive> externalHardDrives = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "order_headset", schema = "_order")
    private List<Headset> headsets = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "order_keyboard", schema = "_order")
    private List<Keyboard> keyboards = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "order_monitor", schema = "_order")
    private List<Monitor> monitors = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "order_mouse", schema = "_order")
    private List<Mouse> mice = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "order_speaker", schema = "_order")
    private List<Speaker> speakers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "is_from_builder")
    private Boolean isFromBuilder = false;

    @Column(name = "status")
    private OrderStatus status = OrderStatus.NEW;

    public void addCase(aCase acase) {
        this.cases.add(acase);
    }

    public void addCooler(Cooler cooler) {
        this.coolers.add(cooler);
    }

    public void addCPU(CPU cpu) {
        this.cpus.add(cpu);
    }

    public void addCPUCooler(CPUCooler cpuCooler) {
        this.cpuCoolers.add(cpuCooler);
    }

    public void addGPU(GPU gpu) {
        this.gpus.add(gpu);
    }

    public void addInternalHardDrive(InternalHardDrive internalHardDrive) {
        this.internalHardDrives.add(internalHardDrive);
    }

    public void addMotherboard(Motherboard motherboard) {
        this.motherboards.add(motherboard);
    }

    public void addPowerSupply(PowerSupply powerSupply) {
        this.powerSupplies.add(powerSupply);
    }

    public void addRAM(RAM ram) {
        this.rams.add(ram);
    }

    public void addExternalHardDrive(ExternalHardDrive externalHardDrive) {
        this.externalHardDrives.add(externalHardDrive);
    }

    public void addHeadset(Headset headset) {
        this.headsets.add(headset);
    }

    public void addKeyboard(Keyboard keyboard) {
        this.keyboards.add(keyboard);
    }

    public void addMonitor(Monitor monitor) {
        this.monitors.add(monitor);
    }

    public void addMouse(Mouse mouse) {
        this.mice.add(mouse);
    }

    public void addSpeaker(Speaker speaker) {
        this.speakers.add(speaker);
    }
}