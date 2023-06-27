package com.bdg.pc_build.computer_builder.model.entity;

import com.bdg.pc_build.product.model.entity.main_component.*;
import com.bdg.pc_build.product.model.entity.peripheral.*;
import com.bdg.pc_build.user.model.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "computer", schema = "computer")
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JsonIgnore
    aCase aCase;

    @ManyToMany
    @JsonIgnore
    List<Cooler> coolers = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    CPU cpu;

    @ManyToOne
    @JsonIgnore
    CPUCooler cpuCooler;

    @ManyToOne
    @JsonIgnore
    GPU gpu;

    @ManyToMany
    @JsonIgnore
    List<InternalHardDrive> internalHardDrives = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    Motherboard motherboard;

    @ManyToOne
    @JsonIgnore
    PowerSupply powerSupply;

    @ManyToMany
    @JsonIgnore
    List<RAM> rams = new ArrayList<>();

    @ManyToMany
    @JsonIgnore
    List<ExternalHardDrive> externalHardDrives = new ArrayList<>();

    @ManyToMany
    @JsonIgnore
    List<Headset> headsets = new ArrayList<>();

    @ManyToMany
    @JsonIgnore
    List<Keyboard> keyboards = new ArrayList<>();

    @ManyToMany
    @JsonIgnore
    List<Monitor> monitors = new ArrayList<>();

    @ManyToMany
    @JsonIgnore
    List<Mouse> mice = new ArrayList<>();

    @ManyToMany
    @JsonIgnore
    List<Speaker> speakers = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    User user;

    @Column(name = "total_price", nullable = false)
    BigDecimal totalPrice;

    Boolean isOrdered = false;

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