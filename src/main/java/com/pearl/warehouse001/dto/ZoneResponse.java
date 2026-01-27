package com.pearl.warehouse001.dto;

import java.time.OffsetDateTime;

public record ZoneResponse(
        Long id,
        String name,
        String zoneType,
        Long warehouseId,
        String warehouseName,
        OffsetDateTime createdAt
) {
}
