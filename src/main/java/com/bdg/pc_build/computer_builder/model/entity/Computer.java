package com.bdg.pc_build.computer_builder.model.entity;

import com.bdg.pc_build.product.model.entity.main_component.*;
import com.bdg.pc_build.product.model.entity.peripheral.*;
import com.bdg.pc_build.user.model.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "computer", schema = "computer")
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private aCase aCase;

    @ManyToMany
    @JsonIgnore
    private List<Cooler> coolers = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    private CPU cpu;

    @ManyToOne
    @JsonIgnore
    private CPUCooler cpuCooler;

    @ManyToOne
    @JsonIgnore
    private GPU gpu;

    @ManyToMany
    @JsonIgnore
    private List<InternalHardDrive> internalHardDrives = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    private Motherboard motherboard;

    @ManyToOne
    @JsonIgnore
    private PowerSupply powerSupply;

    @ManyToMany
    @JsonIgnore
    private List<RAM> rams = new ArrayList<>();

    @ManyToMany
    @JsonIgnore
    private List<ExternalHardDrive> externalHardDrives = new ArrayList<>();

    @ManyToMany
    @JsonIgnore
    private List<Headset> headsets = new ArrayList<>();

    @ManyToMany
    @JsonIgnore
    private List<Keyboard> keyboards = new ArrayList<>();

    @ManyToMany
    @JsonIgnore
    private List<Monitor> monitors = new ArrayList<>();

    @ManyToMany
    @JsonIgnore
    private List<Mouse> mice = new ArrayList<>();

    @ManyToMany
    @JsonIgnore
    private List<Speaker> speakers = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    private User user;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    private Boolean isOrdered = false;

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