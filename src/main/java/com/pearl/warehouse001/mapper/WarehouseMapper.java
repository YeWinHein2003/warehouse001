package com.pearl.warehouse001.mapper;

import com.pearl.warehouse001.dto.WarehouseRequest;
import com.pearl.warehouse001.dto.WarehouseResponse;
import com.pearl.warehouse001.entity.Warehouse;
import com.pearl.warehouse001.entity.Zone;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "Spring")
public interface WarehouseMapper {
    @Mapping(target="region", ignore = true)
    @Mapping(target = "township" , ignore = true)
    Warehouse toEntity(WarehouseRequest warehouseRequest) ;

    @Mapping(target = "regionName", source = "township.region.region_name")
    @Mapping(target = "townshipName", source= "township.township_name")
    WarehouseResponse toResponse(Warehouse warehouse);


    // for nested save with warehouse -> zone -> bin
    /**
     * After mapping the Warehouse, link each Zone to this Warehouse.
     */
    @AfterMapping
    default void linkZones(@MappingTarget Warehouse warehouse) {
        if(warehouse.getZones() != null) {
            warehouse.getZones().forEach(zone -> {
                zone.setWarehouse(warehouse);
                linkBins(zone);
            });
        }
    }

    /**
     * After mapping a Zone, link each Bin to that Zone.
     */
    @AfterMapping
    default void linkBins(@MappingTarget Zone zone) {
        if(zone.getBins() != null) {
            zone.getBins().forEach(bin -> {
                bin.setZone(zone);
            });
        }
    }


}
