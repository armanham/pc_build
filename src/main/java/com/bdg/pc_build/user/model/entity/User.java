package com.bdg.pc_build.user.model.entity;

import com.bdg.pc_build.computer_builder.model.entity.Computer;
import com.bdg.pc_build.desire_log.model.entity.DesireLog;
import com.bdg.pc_build.order.entity.Order;
import com.bdg.pc_build.token.Token;
import com.bdg.pc_build.user.enumerations.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "first_name", nullable = false)
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "email", nullable = false, unique = true)
    String email;

    @Column(name = "password", nullable = false)
    String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    Role role;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    Timestamp created_at;

    @UpdateTimestamp
    @Column(name = "updated_at")
    Timestamp updated_at;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    List<Token> tokens;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
    List<DesireLog> desireLogs = new ArrayList<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    List<Order> orders;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy =  "user")
    List<Computer> computers;

    @Builder
    public User(
            String firstName,
            String lastName,
            String email,
            String password,
            Role role
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}