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
    private String regionName;
    private String townshipName;

    private WarehouseType warehouseType;
    private OffsetDateTime createdAt;

    public WarehouseResponse(Long id, String name, String regionName, String townshipName, WarehouseType warehouseType, OffsetDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.regionName = regionName;
        this.townshipName = townshipName;
        this.warehouseType = warehouseType;
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

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getTownshipName() {
        return townshipName;
    }

    public void setTownshipName(String townshipName) {
        this.townshipName = townshipName;
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
}
