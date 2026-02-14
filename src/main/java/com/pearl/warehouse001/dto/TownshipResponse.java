package com.pearl.warehouse001.dto;

import lombok.Data;

@Data
public class TownshipResponse {
    private Long id;
    private String townshipCode;
    private String townshipName;
    private String region_name;

    public TownshipResponse() {}

    public TownshipResponse(Long id, String townshipCode, String townshipName, String region_name) {
        this.id = id;
        this.townshipCode = townshipCode;
        this.townshipName = townshipName;
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

    public String getTownshipName() {
        return townshipName;
    }

    public void setTownshipName(String townshipName) {
        this.townshipName = townshipName;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }
}
