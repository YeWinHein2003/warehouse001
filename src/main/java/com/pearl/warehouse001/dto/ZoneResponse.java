package com.pearl.warehouse001.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
public class ZoneResponse {
     Long id;
     String name;
     String zoneType;
     Long warehouseId;
     String warehouseName;
     OffsetDateTime createdAt;

    public ZoneResponse(Long id, String name, String zoneType, Long warehouseId, String warehouseName, OffsetDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.zoneType = zoneType;
        this.warehouseId = warehouseId;
        this.warehouseName = warehouseName;
        this.createdAt = createdAt;
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

    public String getZoneType() {
        return zoneType;
    }

    public void setZoneType(String zoneType) {
        this.zoneType = zoneType;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
