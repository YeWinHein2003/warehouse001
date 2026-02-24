package com.pearl.warehouse001.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="warehouse")
@EntityListeners(AuditingEntityListener.class) // Enable automatic auditing
//@SQLRestriction("deleted_at IS NULL")   // to filter out warehouse which have deleted_at = Null
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Better for PostgreSQL
    private Long id;

    @Column(nullable = false, unique = true,length = 50) //unique = true, to prevent duplication
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "township_id",referencedColumnName = "id")
    private Township township;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="region_id", referencedColumnName = "id")
    private Region region;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name="warehouse_type")
    private WarehouseType warehouseType;

    // new 2.22.2026
    @Column(name = "status", nullable = false)
    private String status;

    public enum WarehouseType {
        WAREHOUSE,
        SHOP
    }

//    @CreatedDate // Automatically set by Spring instead of manually setting createdAt = OffsetDateTime.now()
    @CreationTimestamp
    @Column(name="created_at",updatable=false, nullable = false)
    private OffsetDateTime createdAt;


    //new at 2.22.2026
    @UpdateTimestamp
    @Column(name = "last_modified_at")
    private OffsetDateTime lastModifiedAt;

    // new at 2.22.2026
    @Column(name = "deleted_at")
    private OffsetDateTime deletedAt;

    @CreatedBy // Automatically captured if Spring Security is used
    @Column(name = "created_userid")
    private Long createdUserId;

    //new at 2.22.2026
    @LastModifiedBy
    @Column(name = "last_modified_user_id")
    private Long lastModifiedUserId;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Zone> zones = new ArrayList<>();

    public Warehouse(){}

    public Warehouse(Long id, String name, Township township, Region region, WarehouseType warehouseType, String status, OffsetDateTime createdAt, OffsetDateTime lastModifiedAt, OffsetDateTime deletedAt ,Long createdUserId, Long lastModifiedUserId, List<Zone> zones) {
        this.id = id;
        this.name = name;
        this.township = township;
        this.region = region;
        this.warehouseType = warehouseType;
        this.status = status;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
        this.deletedAt = deletedAt;
        this.createdUserId = createdUserId;
        this.lastModifiedUserId = lastModifiedUserId;
        this.zones = zones;
    }

    public Long getLastModifiedUserId() {
        return lastModifiedUserId;
    }

    public void setLastModifiedUserId(Long lastModifiedUserId) {
        this.lastModifiedUserId = lastModifiedUserId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Township getTownship() {
        return township;
    }

    public void setTownship(Township township) {
        this.township = township;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public WarehouseType getWarehouseType() {
        return warehouseType;
    }

    public void setWarehouseType(WarehouseType warehouseType) {
        this.warehouseType = warehouseType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(OffsetDateTime lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    public OffsetDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(OffsetDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Long getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(Long createdUserId) {
        this.createdUserId = createdUserId;
    }

    public List<Zone> getZones() {
        return zones;
    }

    public void setZones(List<Zone> zones) {
        this.zones = zones;
    }
}


