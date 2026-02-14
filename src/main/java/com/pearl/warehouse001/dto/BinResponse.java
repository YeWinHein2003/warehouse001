package com.pearl.warehouse001.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BinResponse {
    Long id;
    Long zoneId;
    String zoneName;
    int capacity;
    String code;

    public BinResponse(Long id, Long zoneId, String zoneName, int capacity, String code) {
        this.id = id;
        this.zoneId = zoneId;
        this.zoneName = zoneName;
        this.capacity = capacity;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getZoneId() {
        return zoneId;
    }

    public void setZoneId(Long zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
