package com.bdg.pc_build.desire_log.model.entity;

import com.bdg.pc_build.desire_log.model.dto.DesireLogDTO;
import com.bdg.pc_build.user.model.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "desire_log")
public class DesireLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "component_type", updatable = false, length = 64)
    String componentType;

    @Column(name = "name", updatable = false, length = 64)
    String name;

    @Column(name = "description", updatable = false)
    String description;

    @Column(name = "count", nullable = false)
    Integer count;

    @Column(name = "checked", nullable = false)
    Boolean checked = false;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    Timestamp updatedAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_desire_log")
    Set<User> users = new LinkedHashSet<>();

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