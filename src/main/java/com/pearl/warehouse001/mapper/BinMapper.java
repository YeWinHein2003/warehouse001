package com.pearl.warehouse001.mapper;

import com.pearl.warehouse001.dto.BinRequest;
import com.pearl.warehouse001.dto.BinResponse;
import com.pearl.warehouse001.entity.Bin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "Spring")
public interface BinMapper {
    @Mapping(target= "zone", ignore = true)
    Bin toEntity(BinRequest binRequest);

    @Mapping(source="zone.id", target="zoneId")
    @Mapping(source="zone.name",target="zoneName")
    BinResponse toResponse(Bin bin);
}
