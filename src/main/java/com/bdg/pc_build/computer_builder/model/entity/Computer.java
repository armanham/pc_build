package com.bdg.pc_build.computer_builder.model.entity;

import com.bdg.pc_build.product.model.entity.main_component.*;
import com.bdg.pc_build.product.model.entity.peripheral.*;
import com.bdg.pc_build.user.model.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private aCase aCase;

    @ManyToMany
    private List<Cooler> coolers = new ArrayList<>();

    @ManyToOne
    private CPU cpu;

    @ManyToOne
    private CPUCooler cpuCooler;

    @ManyToOne
    private GPU gpu;

    @ManyToMany
    private List<InternalHardDrive> internalHardDrives = new ArrayList<>();

    @ManyToOne
    private Motherboard motherboard;

    @ManyToOne
    private PowerSupply powerSupply;

    @ManyToMany
    private List<RAM> rams = new ArrayList<>();

    @ManyToMany
    private List<ExternalHardDrive> externalHardDrives = new ArrayList<>();

    @ManyToMany
    private List<Headset> headsets = new ArrayList<>();

    @ManyToMany
    private List<Keyboard> keyboards = new ArrayList<>();

    @ManyToMany
    private List<Monitor> monitors = new ArrayList<>();

    @ManyToMany
    private List<Mouse> mice = new ArrayList<>();

    @ManyToMany
    private List<Speaker> speakers = new ArrayList<>();

    @ManyToOne
    private User user;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice = new BigDecimal(0);

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