package com.pearl.warehouse001.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Entity
@Table(name="warehouse")
@EntityListeners(AuditingEntityListener.class) // Enable automatic auditing
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Better for PostgreSQL
    private Long id;

    @Column(nullable = false, unique = true,length = 50) //unique = true, to prevent duplication
    private String name;
    @Column(nullable = false, length = 50)
    private String location;
    @Column(nullable = false, length = 50)
    private String township;

    @Enumerated(EnumType.STRING)
    @Column(name="warehouse_type")
    private WarehouseType warehouseType;

    public enum WarehouseType {
        WAREHOUSE,
        SHOP
    }

//    @CreatedDate // Automatically set by Spring instead of manually setting createdAt = OffsetDateTime.now()
    @CreationTimestamp
    @Column(name="created_at",updatable=false, nullable = false)
    private OffsetDateTime createdAt;

    @CreatedBy // Automatically captured if Spring Security is used
    @Column(name = "created_userid")
    private Long createdUserId;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Zone> zones;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTownship() {
        return township;
    }

    public void setTownship(String township) {
        this.township = township;
    }

    public WarehouseType getWarehouseType() {
        return warehouseType;
    }

    public void setWarehouseType(WarehouseType warehouseType) {
        this.warehouseType = warehouseType;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
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

    public Warehouse(){

    }

    public Warehouse(Long id, String name, String location, String township, WarehouseType warehouseType, OffsetDateTime createdAt, Long createdUserId, List<Zone> zones) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.township = township;
        this.warehouseType = warehouseType;
        this.createdAt = createdAt;
        this.createdUserId = createdUserId;
        this.zones = zones;
    }
}


