package com.purwadhika.montrackv2.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "languages", schema = "montrack")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "languages_id_gen")
    @SequenceGenerator(name = "languages_id_gen", sequenceName = "languages_id_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "lang", nullable = false)
    private String name;

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
