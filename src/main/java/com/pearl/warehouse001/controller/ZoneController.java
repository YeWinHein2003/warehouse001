package com.pearl.warehouse001.controller;

import com.pearl.warehouse001.dto.ApiResponse;
import com.pearl.warehouse001.dto.ZoneRequest;
import com.pearl.warehouse001.dto.ZoneResponse;
import com.pearl.warehouse001.entity.Zone;
import com.pearl.warehouse001.service.ZoneService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/zones")
public class ZoneController {

    private final ZoneService zoneService;

    public ZoneController(ZoneService zoneService) {
        this.zoneService = zoneService;
    }

    @GetMapping
    public ApiResponse<Page<ZoneResponse>> getAll(
            @org.springframework.data.web.PageableDefault(size = 10, sort = "name") Pageable pageable) {
        Page<ZoneResponse> data= zoneService.getAllZones(pageable);
        return new ApiResponse<>(true,"All zones retrieved Successfully",data);
    }

    @GetMapping("/{id}")
    public ApiResponse<ZoneResponse> getById(@PathVariable Long id) {
        ZoneResponse data = zoneService.getZoneById(id);
        return new ApiResponse<>(true,"Zone retrieved Successfully",data);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ZoneResponse> addZone(@Valid @RequestBody ZoneRequest zone) {
        ZoneResponse data= zoneService.addZone(zone);
        return new ApiResponse<>(true,"Zone Added Successfully",data);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteZone(@PathVariable Long id) {
        zoneService.deleteZone(id);
        return new ApiResponse<>(true,"Zone Deleted Successfully",null);
    }
}
