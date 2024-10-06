package com.bilalbererk.Dukkan.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract  class BaseEntity implements Serializable {
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @Column(name = "last_modified_at")
    private LocalDateTime lastModifiedAt;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @PrePersist
    public void onCreate(){
        this.setCreatedAt(LocalDateTime.now());
        this.setLastModifiedAt(LocalDateTime.now());
        this.setCreatedBy("Bilal Berek");
        this.setLastModifiedBy("Bilal Berek");
    }
    @PreUpdate
    public void onUpdate(){
        this.setLastModifiedAt(LocalDateTime.now());
        this.setLastModifiedBy("Bilal Berek");
    }
}
