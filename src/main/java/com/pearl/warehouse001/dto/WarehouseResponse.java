package com.pearl.warehouse001.dto;

import com.pearl.warehouse001.entity.Warehouse.WarehouseType;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
public class WarehouseResponse {
    Long id;
    String name;
    String location;
    String township;
    String displayAddress;
    WarehouseType warehouseType;
    OffsetDateTime createdAt;

    public WarehouseResponse(Long id, String name, String location, String township, String displayAddress, WarehouseType warehouseType, OffsetDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.township = township;
        this.displayAddress = displayAddress;
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

    public String getDisplayAddress() {
        return displayAddress;
    }

    public void setDisplayAddress(String displayAddress) {
        this.displayAddress = displayAddress;
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
