package com.pearl.warehouse001.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.mapstruct.ap.internal.conversion.BigDecimalToBigIntegerConversion;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="zone" , uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "warehouse_id"})
})
@EntityListeners(AuditingEntityListener.class) // Enable automatic auditing
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    private Warehouse warehouse;

    @Column(nullable = false, length = 50)
    private String name;

      private String zoneType;

//    @CreatedDate // Automatically set by Spring instead of manually setting createdAt = OffsetDateTime.now()
    @CreationTimestamp
    @Column(name="created_at",updatable=false, nullable = false)
    private OffsetDateTime createdAt;

    @CreatedBy // Automatically captured if Spring Security is used
    @Column(name = "created_userid")
    private Long createdUserId;

//******************
    @OneToMany(mappedBy = "zone",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bin> bins = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZoneType() {
        return zoneType;
    }

    public void setZoneType(String zoneType) {
        this.zoneType = zoneType;
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

    public List<Bin> getBins() {return bins;}

    public void setBins(List<Bin> bins) {this.bins = bins;}

    public Zone(){

    }

    public Zone(Long id, Warehouse warehouse, String name, String zoneType, OffsetDateTime createdAt, Long createdUserId, List<Bin> bins) {
        this.id = id;
        this.warehouse = warehouse;
        this.name = name;
        this.zoneType = zoneType;
        this.createdAt = createdAt;
        this.createdUserId = createdUserId;
        this.bins = bins;
    }
}
