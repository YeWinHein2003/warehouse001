package com.pearl.warehouse001.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ZoneUpdateRequest(
        @NotBlank String name,
        @NotNull String zoneType,
        @NotNull(message = "Warehouse must be specified") Long warehouseId

) {
}
