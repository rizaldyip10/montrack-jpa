package com.purwadhika.montrackv2.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "wallet_histories", schema = "montrack")
public class Trx {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wallet_histories_id_gen")
    @SequenceGenerator(name = "wallet_histories_id_gen", sequenceName = "wallet_histories_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "amount", nullable = false)
    @Min(value = 0)
    private Double amount;

    @NotBlank
    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull(message = "Currency is required")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_type_id", referencedColumnName = "id")
    private TrxType transactionType;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "wallet_id", referencedColumnName = "id", nullable = false)
    private Wallet wallet;

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
