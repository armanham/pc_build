package com.bdg.pc_build.desire_log.model.entity;

import com.bdg.pc_build.desire_log.model.dto.DesireLogDTO;
import com.bdg.pc_build.user.model.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "desire_log", schema = "desire_log")
public class DesireLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "component_type", updatable = false, length = 64)
    private String componentType;

    @Column(name = "name", updatable = false, length = 64)
    private String name;

    @Column(name = "description", updatable = false)
    private String description;

    @Column(name = "count", nullable = false)
    private Integer count;

    @Column(name = "checked", nullable = false)
    private Boolean checked = false;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_desire_log")
    private Set<User> users = new LinkedHashSet<>();

    public void addUser(User user){
        users.add(user);
        user.getDesireLogs().add(this);
    }

    public DesireLog(final DesireLogDTO dto) {
        this.componentType = dto.getComponentType();
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.count = dto.getCount();
    }
}