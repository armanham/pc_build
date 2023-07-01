package com.bdg.pc_build.token.model.entity;

import com.bdg.pc_build.token.enumerations.TokenType;
import com.bdg.pc_build.user.model.entity.User;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "token", schema = "_user")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token", unique = true)
    private String token;

    @Enumerated(EnumType.STRING)
    @Column(name = "token_type")
    private TokenType tokenType = TokenType.BEARER;

    @Column(name = "revoked")
    private boolean revoked;

    @Column(name = "expired")
    private boolean expired;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_token_user_id"),
            nullable = false
    )
    private User user;
}