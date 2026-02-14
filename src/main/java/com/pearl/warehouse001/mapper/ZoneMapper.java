package com.pearl.warehouse001.mapper;

import com.pearl.warehouse001.dto.ZoneRequest;
import com.pearl.warehouse001.dto.ZoneResponse;
import com.pearl.warehouse001.entity.Zone;
import org.aspectj.lang.annotation.After;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface ZoneMapper {
    @Mapping(target = "warehouse", ignore = true)
    Zone toEntity(ZoneRequest zoneRequest);

    @Mapping(source = "warehouse.id", target = "warehouseId")
    @Mapping(source = "warehouse.name", target = "warehouseName")
    ZoneResponse toResponse(Zone zone);

    @AfterMapping
    default void linkBins(@MappingTarget Zone zone) {
        if(zone.getBins() != null) {
            zone.getBins().forEach(bin -> bin.setZone(zone));
        }
    }
}
