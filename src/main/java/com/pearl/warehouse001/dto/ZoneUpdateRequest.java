package com.pearl.warehouse001.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ZoneUpdateRequest(

        @Size(max = 50, message = "Name must not exceed 50 character")
        @Pattern(regexp = "^[a-zA-Z0-9\\s\\-\\.]+$", message = "Name must me alphanumeric")
        String name,

        String zoneType,

        @NotNull(message = "Warehouse must be specified") Long warehouseId

) {
}
