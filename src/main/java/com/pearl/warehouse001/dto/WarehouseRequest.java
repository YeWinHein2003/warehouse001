package com.pearl.warehouse001.dto;

import com.pearl.warehouse001.entity.Warehouse.WarehouseType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record WarehouseRequest (
    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Name must not exceed 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$" , message = "Name must be alphanumeric")
    String name,

    @NotBlank(message = "Location is required" )
    String location,

    @NotBlank(message="Township is required")
    String township,

    @NotNull(message = "Warehouse Type is required")
    WarehouseType warehouseType,

    Long createdUserId
){}
