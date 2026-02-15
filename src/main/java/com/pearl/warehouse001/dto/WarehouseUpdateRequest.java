package com.pearl.warehouse001.dto;

import com.pearl.warehouse001.entity.Warehouse.WarehouseType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record WarehouseUpdateRequest(

        @Size(max = 50, message = "Name must not exceed 50 characters")
        @Pattern(regexp = "^[a-zA-Z0-9\\s\\-\\.]+$", message = "Name must be alphanumeric")
        String name,

        WarehouseType warehouseType
) {
}
