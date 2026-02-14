package com.pearl.warehouse001.dto;

import lombok.Data;

@Data
public class TownshipResponse {
    private Long id;
    private String townshipCode;
    private String township_name;
    private String region_name;

    public TownshipResponse() {}

    public TownshipResponse(Long id, String townshipCode, String township_name, String region_name) {
        this.id = id;
        this.townshipCode = townshipCode;
        this.township_name = township_name;
        this.region_name = region_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTownshipCode() {
        return townshipCode;
    }

    public void setTownshipCode(String townshipCode) {
        this.townshipCode = townshipCode;
    }

    public String getTownship_name() {
        return township_name;
    }

    public void setTownship_name(String township_name) {
        this.township_name = township_name;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }
}
