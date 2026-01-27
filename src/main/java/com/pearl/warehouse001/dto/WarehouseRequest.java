package com.pearl.warehouse001.dto;

import com.pearl.warehouse001.entity.Warehouse.WarehouseType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record WarehouseRequest (
    @NotBlank(message = "Name is required")
    String name,

    @NotBlank(message = "Location is required" )
    String location,

    @NotBlank(message="Township is required")
    String township,

    @NotNull(message = "Warehouse Type is required")
    WarehouseType warehouseType,

    Long createdUserId
){}
