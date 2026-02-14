package com.pearl.warehouse001.controller;

import com.pearl.warehouse001.dto.RegionResponse;
import com.pearl.warehouse001.dto.TownshipResponse;
import com.pearl.warehouse001.dto.api.ApiResponse;
import com.pearl.warehouse001.entity.Region;
import com.pearl.warehouse001.service.LocationService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/locations")
public class LocationController {


    public final LocationService locationService;
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/regions")
    public ResponseEntity<ApiResponse<List<RegionResponse>>> getRegions() {
        List<RegionResponse> regions = locationService.getAllRegions();
        return ResponseEntity.ok(ApiResponse.success(regions, "Regions found Successfully"));
    }

    @GetMapping("/regions/{regionId}/townships")
    public  ResponseEntity<ApiResponse<List<TownshipResponse>>> getTownships(@PathVariable  Long regionId) {
        List<TownshipResponse> townships = locationService.getTownshipsByRegion(regionId);
        return ResponseEntity.ok(ApiResponse.success(townships, "Townships found Successfully"));
    }
}
