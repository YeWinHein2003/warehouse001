package com.pearl.warehouse001.dto;

import lombok.Data;

@Data
public class RegionResponse {
    private Long id;
    private String region_code;
    private String region_name;

    public RegionResponse() {}

    public RegionResponse(Long id, String region_code, String region_name) {
        this.id = id;
        this.region_code = region_code;
        this.region_name = region_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegion_code() {
        return region_code;
    }

    public void setRegion_code(String region_code) {
        this.region_code = region_code;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }
}
