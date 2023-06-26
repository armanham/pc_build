package com.bdg.pc_build.computer_builder.model.entity;

import com.bdg.pc_build.product.model.entity.main_component.*;
import com.bdg.pc_build.product.model.entity.peripheral.*;
import com.bdg.pc_build.user.model.entity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "computer")
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    aCase aCase;

    @ManyToMany
    List<Cooler> coolers = new ArrayList<>();

    @ManyToOne
    CPU cpu;

    @ManyToOne
    CPUCooler cpuCooler;

    @ManyToOne
    GPU gpu;

    @ManyToMany
    List<InternalHardDrive> internalHardDrives = new ArrayList<>();

    @ManyToOne
    Motherboard motherboard;

    @ManyToOne
    PowerSupply powerSupply;

    @ManyToMany
    List<RAM> rams = new ArrayList<>();

    @ManyToMany
    List<ExternalHardDrive> externalHardDrives = new ArrayList<>();

    @ManyToMany
    List<Headset> headsets = new ArrayList<>();

    @ManyToMany
    List<Keyboard> keyboards = new ArrayList<>();

    @ManyToMany
    List<Monitor> monitors = new ArrayList<>();

    @ManyToMany
    List<Mouse> mice = new ArrayList<>();

    @ManyToMany
    List<Speaker> speakers = new ArrayList<>();

    @ManyToOne
    User user;

    @Column(name = "total_price", nullable = false)
    BigDecimal totalPrice;

    Boolean aBoolean = false;

    public void addCooler(Cooler cooler) {
        this.coolers.add(cooler);
    }

    public void addInternalHardDrive(InternalHardDrive internalHardDrive) {
        this.internalHardDrives.add(internalHardDrive);
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