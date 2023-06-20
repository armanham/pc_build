package com.bdg.pc_build.token;

import com.bdg.pc_build.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "token")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "token", unique = true)
    String token;

    @Enumerated(EnumType.STRING)
    @Column(name = "token_type")
    TokenType tokenType = TokenType.BEARER;

    @Column(name = "revoked")
    boolean revoked;

    @Column(name = "expired")
    boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;
}