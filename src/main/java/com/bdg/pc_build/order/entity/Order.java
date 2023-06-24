package com.bdg.pc_build.order.entity;

import com.bdg.pc_build.product.model.entity.main_component.*;
import com.bdg.pc_build.product.model.entity.peripheral.*;
import com.bdg.pc_build.user.model.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToMany
    @JoinTable(name = "order_case")
    List<aCase> cases;

    @ManyToMany
    @JoinTable(name = "order_cooler")
    List<Cooler> coolers;

    @ManyToMany
    @JoinTable(name = "order_cpu")
    List<CPU> cpus;

    @ManyToMany
    @JoinTable(name = "order_cpu_cooler")
    List<CPUCooler> cpuCoolers;

    @ManyToMany
    @JoinTable(name = "order_gpu")
    List<GPU> gpus;

    @ManyToMany
    @JoinTable(name = "order_internal_hard_drive")
    List<InternalHardDrive> internalHardDrives;

    @ManyToMany
    @JoinTable(name = "order_motherboard")
    List<Motherboard> motherboards;

    @ManyToMany
    @JoinTable(name = "order_power_supply")
    List<PowerSupply> powerSupplies;

    @ManyToMany
    @JoinTable(name = "order_ram")
    List<RAM> rams;

    @ManyToMany
    @JoinTable(name = "order_external_hard_drive")
    List<ExternalHardDrive> externalHardDrives;

    @ManyToMany
    @JoinTable(name = "order_headset")
    List<Headset> headsets = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "order_keyboard")
    List<Keyboard> keyboards;

    @ManyToMany
    @JoinTable(name = "order_monitor")
    List<Monitor> monitors = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "order_mouse")
    List<Mouse> mice;

    @ManyToMany
    @JoinTable(name = "order_speaker")
    List<Speaker> speakers;

//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
//    User user;

    @Column(name = "total_price", nullable = false)
    BigDecimal totalPrice;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    Timestamp createdAt;


    public void addCase(aCase acase){
        this.cases.add(acase);
    }
    public void addCooler(Cooler cooler){
        this.coolers.add(cooler);
    }
    public void addCPU(CPU cpu){
        this.cpus.add(cpu);
    }
    public void addCPUCooler(CPUCooler cpuCooler){
        this.cpuCoolers.add(cpuCooler);
    }
    public void addGPU(GPU gpu){
        this.gpus.add(gpu);
    }
    public void addInternalHardDrive(InternalHardDrive internalHardDrive){
        this.internalHardDrives.add(internalHardDrive);
    }
    public void addMotherboard(Motherboard motherboard){
        this.motherboards.add(motherboard);
    }
    public void addPowerSupply(PowerSupply powerSupply){
        this.powerSupplies.add(powerSupply);
    }
    public void addRAM(RAM ram){
        this.rams.add(ram);
    }
    public void addExternalHardDrive(ExternalHardDrive externalHardDrive){
        this.externalHardDrives.add(externalHardDrive);
    }
    public void addHeadset(Headset headset){
        this.headsets.add(headset);
    }
    public void addKeyboard(Keyboard keyboard){
        this.keyboards.add(keyboard);
    }

    public void addMonitor(Monitor monitor){
        this.monitors.add(monitor);
    }
    public void addMouse(Mouse mouse){
        this.mice.add(mouse);
    }
    public void addSpeaker(Speaker speaker){
        this.speakers.add(speaker);
    }

}