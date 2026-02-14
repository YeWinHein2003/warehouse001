package com.pearl.warehouse001.dto;

public record TownshipRequest(
        String township_code,
        String township_name,
        String region_id
){
}
