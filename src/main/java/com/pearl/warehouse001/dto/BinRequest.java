package com.pearl.warehouse001.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record BinRequest(
        // as it is for both nested save for zone and single save for bin, remove @NotNull9
        Long zoneId,

        @NotNull(message="Capacity is required")
        int capacity,

        @NotBlank(message="Code is required")
        @Pattern(regexp = "^[a-zA-Z0-9\\s\\-]+$")
        String code
) {
}
