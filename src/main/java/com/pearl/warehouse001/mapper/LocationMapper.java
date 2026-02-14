package com.pearl.warehouse001.mapper;

import com.pearl.warehouse001.dto.RegionRequest;
import com.pearl.warehouse001.dto.RegionResponse;
import com.pearl.warehouse001.dto.TownshipRequest;
import com.pearl.warehouse001.dto.TownshipResponse;
import com.pearl.warehouse001.entity.Region;
import com.pearl.warehouse001.entity.Township;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    Region toRegionEntity(RegionRequest regionRequest);
    RegionResponse toRegionResponse(Region region);
    List<RegionResponse> toRegionResponseList(List<Region> regions);

    @Mapping(target="region",ignore = true)
    Township toTownshipEntity(TownshipRequest townshipRequest);

    @Mapping(target="region_name",source="region.region_name")
    TownshipResponse toTownshipResponse(Township township);
    List<TownshipResponse> toTownshipResponseList(List<Township> townships);
}
