package com.bdg.pc_build.product.model.entity.computer;

import com.bdg.pc_build.product.model.entity.display.Monitor;
import com.bdg.pc_build.product.model.entity.main_component.*;
import com.bdg.pc_build.product.model.entity.peripheral.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "computer")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "constructor", updatable = false)
    String constructor;

    @Column(name = "price")
    Double price;

    @Column(name = "constructed_at")
    @CreationTimestamp
    Timestamp constructedAt;

    @OneToOne
    @JoinColumn(name = "monitor", referencedColumnName = "name")
    Monitor monitor;

    @OneToOne
    @JoinColumn(name = "a_case", referencedColumnName = "name")
    com.bdg.pc_build.product.model.entity.main_component.aCase aCase;

    @OneToOne
    @JoinColumn(name = "cooler", referencedColumnName = "name")
    Cooler cooler;

    @OneToOne
    @JoinColumn(name = "cpu", referencedColumnName = "name")
    CPU cpu;

    @OneToOne
    @JoinColumn(name = "cpu_cooler", referencedColumnName = "name")
    CPUCooler cpuCooler;

    @OneToOne
    @JoinColumn(name = "external_hard_drive", referencedColumnName = "name")
    InternalHardDrive internalHardDrive;

    @OneToOne
    @JoinColumn(name = "gpu", referencedColumnName = "name")
    GPU gpu;

    @OneToOne
    @JoinColumn(name = "internal_hard_drive", referencedColumnName = "name")
    ExternalHardDrive externalHardDrive;

    @OneToOne
    @JoinColumn(name = "motherboard", referencedColumnName = "name")
    Motherboard motherboard;

    @OneToOne
    @JoinColumn(name = "power_supply", referencedColumnName = "name")
    PowerSupply powerSupply;

    @OneToOne
    @JoinColumn(name = "ram", referencedColumnName = "name")
    RAM ram;

    @OneToOne
    @JoinColumn(name = "headset", referencedColumnName = "name")
    Headset headset;

    @OneToOne
    @JoinColumn(name = "keyboard", referencedColumnName = "name")
    Keyboard keyboard;

    @OneToOne
    @JoinColumn(name = "mouse", referencedColumnName = "name")
    Mouse mouse;

    @OneToOne
    @JoinColumn(name = "speaker", referencedColumnName = "name")
    Speaker speaker;
}