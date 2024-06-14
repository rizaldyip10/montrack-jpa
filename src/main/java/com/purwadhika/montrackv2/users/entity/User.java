package com.purwadhika.montrackv2.users.entity;

import com.purwadhika.montrackv2.wallets.entitiy.Wallet;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "users", schema = "montrack")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_gen")
    @SequenceGenerator(name = "users_id_gen", sequenceName = "users_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "Your name is required")
    @NotBlank(message = "Please enter your name")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull(message = "Email is required")
    @NotBlank(message = "Please enter your name")
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull(message = "Password is required")
    @NotBlank(message = "Please enter your password")
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull(message = "PIN is required")
    @NotBlank(message = "Please enter your PIN")
    @Column(name = "pin", nullable = false)
    private String pin;

    @Column(name = "profile_img")
    private String profileImg;

    @Column(name = "quotes")
    private String quotes;

    @Transient
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Wallet> wallets;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_at")
    private Date deletedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        updatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    @PreRemove
    protected void onRemove() {
        this.deletedAt = new Date();
    }
}
