package com.pearl.warehouse001.dto;

import com.pearl.warehouse001.entity.Warehouse.WarehouseType;

import java.time.OffsetDateTime;

public record WarehouseResponse (
        Long id,
        String name,
        String location,
        String township,
        String displayAddress,
        WarehouseType warehouseType,
        OffsetDateTime createdAt
){
}
