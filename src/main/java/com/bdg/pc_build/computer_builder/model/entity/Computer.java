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
    @JoinColumn(name = "case_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_computer_case_id"))
    private aCase aCase;

    @ManyToMany
    @JoinTable(
            name = "computer_cooler", schema = "computer",
            joinColumns = @JoinColumn(
                    name = "computer_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_computer_cooler_computer_id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "cooler_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_computer_cooler_cooler_id")
            )
    )
    private List<Cooler> coolers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "cpu_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_computer_cpu_id"))
    private CPU cpu;

    @ManyToOne
    @JoinColumn(name = "cpu_cooler_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_computer_cpu_cooler_id"))
    private CPUCooler cpuCooler;

    @ManyToOne
    @JoinColumn(name = "gpu_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_computer_gpu_id"))
    private GPU gpu;

    @ManyToMany
    @JoinTable(
            name = "computer_internal_hard_drive", schema = "computer",
            joinColumns = @JoinColumn(
                    name = "computer_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_computer_internal_hard_drive_computer_id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "internal_hard_drive_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_computer_internal_hard_drive_internal_hard_drive_id")
            )
    )
    private List<InternalHardDrive> internalHardDrives = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "motherboard_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_computer_motherboard_id"))
    private Motherboard motherboard;

    @ManyToOne
    @JoinColumn(name = "power_supply_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_computer_power_supply_id"))
    private PowerSupply powerSupply;

    @ManyToMany
    @JoinTable(
            name = "computer_ram", schema = "computer",
            joinColumns = @JoinColumn(
                    name = "computer_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_computer_ram_computer_id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "ram_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_computer_ram_ram_id")
            )
    )
    private List<RAM> rams = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "computer_external_hard_drive", schema = "computer",
            joinColumns = @JoinColumn(
                    name = "computer_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_computer_external_hard_drive_computer_id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "external_hard_drive_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_computer_external_hard_drive_external_hard_drive_id")
            )
    )
    private List<ExternalHardDrive> externalHardDrives = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "computer_headset", schema = "computer",
            joinColumns = @JoinColumn(
                    name = "computer_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_computer_headset_computer_id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "headset_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_computer_headset_headset_id")
            )
    )
    private List<Headset> headsets = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "computer_keyboard", schema = "computer",
            joinColumns = @JoinColumn(
                    name = "computer_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_computer_keyboard_computer_id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "keyboard_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_computer_keyboard_keyboard_id")
            )
    )
    private List<Keyboard> keyboards = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "computer_monitor", schema = "computer",
            joinColumns = @JoinColumn(
                    name = "computer_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_computer_monitor_computer_id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "monitor_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_computer_monitor_monitor_id")
            )
    )
    private List<Monitor> monitors = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "computer_mouse", schema = "computer",
            joinColumns = @JoinColumn(
                    name = "computer_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_computer_mouse_computer_id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "mouse_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_computer_mouse_mouse_id")
            )
    )
    private List<Mouse> mice = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "computer_speaker", schema = "computer",
            joinColumns = @JoinColumn(
                    name = "computer_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_computer_speaker_computer_id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "speaker_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_computer_speaker_speaker_id")
            )
    )
    private List<Speaker> speakers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_computer_user_id"))
    private User user;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice = new BigDecimal(0);

    @Column(name = "is_ordered", nullable = false)
    private Boolean isOrdered = false;

    @Column(name = "is_fully", nullable = false)
    private Boolean isFully = false;

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