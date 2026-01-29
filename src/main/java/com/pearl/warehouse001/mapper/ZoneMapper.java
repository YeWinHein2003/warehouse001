package com.pearl.warehouse001.mapper;

import com.pearl.warehouse001.dto.ZoneRequest;
import com.pearl.warehouse001.dto.ZoneResponse;
import com.pearl.warehouse001.entity.Zone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ZoneMapper {
    @Mapping(target = "warehouse", ignore = true)
    Zone toEntity(ZoneRequest zoneRequest);

    @Mapping(source = "warehouse.id", target = "warehouseId")
    @Mapping(source = "warehouse.name", target = "warehouseName")
    ZoneResponse toResponse(Zone zone);
}
