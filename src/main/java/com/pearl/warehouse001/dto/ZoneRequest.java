package com.pearl.warehouse001.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ZoneRequest(
        @NotBlank(message = "Zone name is required")
        @Size(max = 50, message = "Name must not exceed 50 character")
        @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Name must me alphanumeric")
        String name,

        @NotBlank(message = "Zone Type is required")
        String zoneType,

        @NotNull(message = "Warehouse Id is required")
        Long warehouseId,

        Long createdUserId
) {
}
