package com.purwadhika.montrackv2.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "pockets", schema = "montrack")
public class Pocket {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pockets_id_gen")
    @SequenceGenerator(name = "pockets_id_gen", sequenceName = "pockets_id_seq", allocationSize = 1)
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @NotBlank
    @Column(name = "description")
    private String description;

    @NotNull
    @Min(value = 0)
    @Column(name = "budget", nullable = false)
    private Double budget;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wallet_id", referencedColumnName = "id", nullable = false)
    private Wallet wallet;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "currency_id", referencedColumnName = "id", nullable = false)
    private Currency currency;

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
