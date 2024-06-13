package com.purwadhika.montrackv2.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@Table(name = "wallets", schema = "montrack")
@SQLRestriction("deleted_at IS NULL")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wallets_id_gen")
    @SequenceGenerator(name = "wallets_id_gen",sequenceName = "wallets_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "Please enter your wallet's name")
    @NotBlank(message = "Please enter your wallet's name")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "balance", nullable = false)
    @Min(value = 0)
    private Double balance;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @NotNull(message = "Currency is required")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    private Currency currency;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

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
