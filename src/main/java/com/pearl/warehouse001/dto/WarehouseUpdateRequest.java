package com.pearl.warehouse001.dto;

import com.pearl.warehouse001.entity.Warehouse.WarehouseType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record WarehouseUpdateRequest(
        @NotBlank String name,
        @NotBlank String location,
        @NotBlank String township,
        @NotNull WarehouseType warehouseType
) {
}
