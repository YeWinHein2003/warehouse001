package com.pearl.warehouse001.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ZoneRequest(
        @NotBlank(message = "Zone name is required")
        String name,

        @NotBlank(message = "Zone Type is required")
        String zoneType,

        @NotNull(message = "Warehouse Id is required")
        Long warehouseId,

        Long createdUserId
) {
}
