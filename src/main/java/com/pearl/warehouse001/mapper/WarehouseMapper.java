package com.pearl.warehouse001.mapper;

import com.pearl.warehouse001.dto.WarehouseRequest;
import com.pearl.warehouse001.dto.WarehouseResponse;
import com.pearl.warehouse001.entity.Warehouse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "Spring")
public interface WarehouseMapper {
    Warehouse toEntity(WarehouseRequest warehouseRequest) ;

    @Mapping(target = "displayAddress", expression = "java(warehouse.getLocation() + \", \" + warehouse.getTownship())")
    WarehouseResponse toResponse(Warehouse warehouse);




}
