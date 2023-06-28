package com.bdg.pc_build.order.model.entity;

import com.bdg.pc_build.order.enumerations.OrderStatus;
import com.bdg.pc_build.product.model.entity.main_component.*;
import com.bdg.pc_build.product.model.entity.peripheral.*;
import com.bdg.pc_build.user.model.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    @JoinTable(
            name = "order_case", schema = "_order",
            joinColumns = @JoinColumn(
                    name = "order_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_order_case_order_id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "case_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_order_case_case_id")
            )
    )
    private List<aCase> cases = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "order_cooler", schema = "_order",
            joinColumns = @JoinColumn(
                    name = "order_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_order_cooler_order_id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "cooler_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_order_cooler_cooler_id")
            )
    )
    private List<Cooler> coolers = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "order_cpu", schema = "_order",
            joinColumns = @JoinColumn(
                    name = "order_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_order_cpu_order_id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "cpu_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_order_cpu_cpu_id")
            )
    )
    private List<CPU> cpus = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "order_cpu_cooler", schema = "_order",
            joinColumns = @JoinColumn(
                    name = "order_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_order_cooler_order_id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "cpu_cooler_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_order_cooler_cooler_id")
            )
    )
    private List<CPUCooler> cpuCoolers = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "order_gpu", schema = "_order",
            joinColumns = @JoinColumn(
                    name = "order_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_order_gpu_order_id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "gpu_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_order_gpu_gpu_id")
            )
    )
    private List<GPU> gpus = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "order_internal_hard_drive", schema = "_order",
            joinColumns = @JoinColumn(
                    name = "order_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_order_internal_hard_drive_order_id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "internal_hard_drive_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_order_internal_hard_drive_internal_hard_drive_id")
            )
    )
    private List<InternalHardDrive> internalHardDrives = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "order_motherboard", schema = "_order",
            joinColumns = @JoinColumn(
                    name = "order_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_order_motherboard_order_id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "motherboard_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_order_motherboard_motherboard_id")
            )
    )
    private List<Motherboard> motherboards = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "order_power_supply", schema = "_order",
            joinColumns = @JoinColumn(
                    name = "order_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_order_power_supply_order_id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "power_supply_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_order_power_supply_power_supply_id")
            )
    )
    private List<PowerSupply> powerSupplies = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "order_ram", schema = "_order",
            joinColumns = @JoinColumn(
                    name = "order_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_order_ram_order_id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "ram_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_order_ram_ram_id")
            )
    )
    private List<RAM> rams = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "order_external_hard_drive", schema = "_order",
            joinColumns = @JoinColumn(
                    name = "order_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_order_external_hard_drive_order_id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "external_hard_drive_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_order_external_hard_drive_external_hard_drive_id")
            )
    )
    private List<ExternalHardDrive> externalHardDrives = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "order_headset", schema = "_order",
            joinColumns = @JoinColumn(
                    name = "order_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_order_headset_order_id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "headset_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_order_headset_headset_id")
            )
    )
    private List<Headset> headsets = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "order_keyboard", schema = "_order",
            joinColumns = @JoinColumn(
                    name = "order_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_order_keyboard_order_id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "keyboard_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_order_keyboard_keyboard_id")
            )
    )
    private List<Keyboard> keyboards = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "order_monitor", schema = "_order",
            joinColumns = @JoinColumn(
                    name = "order_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_order_monitor_order_id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "monitor_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_order_monitor_monitor_id")
            )
    )
    private List<Monitor> monitors = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "order_mouse", schema = "_order",
            joinColumns = @JoinColumn(
                    name = "order_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_order_mouse_order_id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "mouse_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_order_mouse_mouse_id")
            )
    )
    private List<Mouse> mice = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "order_speaker", schema = "_order",
            joinColumns = @JoinColumn(
                    name = "order_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_order_speaker_order_id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "speaker_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_order_speaker_speaker_id")
            )
    )
    private List<Speaker> speakers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_order_user_id"))
    private User user;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "is_from_builder", nullable = false)
    private Boolean isFromBuilder = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
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