package com.pearl.warehouse001.dto;

import com.pearl.warehouse001.entity.Warehouse.WarehouseType;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
public class WarehouseResponse {
    private Long id;
    private String name;
    private String status;
    private String address;
    private WarehouseType warehouseType;
    private OffsetDateTime createdAt;
    private OffsetDateTime lastModifiedAt;
    // ADD THIS FIELD
    private OffsetDateTime deletedAt;

    public WarehouseResponse(Long id, String name, String status, String address, WarehouseType warehouseType, OffsetDateTime createdAt, OffsetDateTime lastModifiedAt, OffsetDateTime deletedAt) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.address = address;
        this.warehouseType = warehouseType;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
        this.deletedAt = deletedAt;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
}
