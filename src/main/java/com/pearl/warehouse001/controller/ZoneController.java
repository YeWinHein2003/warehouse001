package com.pearl.warehouse001.controller;

import com.pearl.warehouse001.dto.ZoneUpdateRequest;
import com.pearl.warehouse001.dto.api.ApiResponse;
import com.pearl.warehouse001.dto.ZoneRequest;
import com.pearl.warehouse001.dto.ZoneResponse;
import com.pearl.warehouse001.dto.api.Pagination;
import com.pearl.warehouse001.entity.Zone;
import com.pearl.warehouse001.service.ZoneService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/zones")
public class ZoneController {

    private final ZoneService zoneService;

    public ZoneController(ZoneService zoneService) {
        this.zoneService = zoneService;
    }

    @GetMapping("list-paging")
    public ResponseEntity<ApiResponse<List<ZoneResponse>>> getAll(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "DESC") String direction) {

        Page<ZoneResponse> zonePage = zoneService.getZonesPaginated(keyword,name,page, size, sortBy, direction);

        // Map Spring Page to your custom Pagination DTO
        Pagination pagination = new Pagination(
                zonePage.getNumber(),
                zonePage.getSize(),
                zonePage.getTotalElements(),
                zonePage.getTotalPages(),
                zonePage.hasNext(),
                zonePage.hasPrevious()
        );

        ApiResponse<List<ZoneResponse>> response = ApiResponse.success(
                zonePage.getContent(),
                "Zones fetched successfully"
        );
        response.setPagination(pagination);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ZoneResponse>> getById(@PathVariable Long id) {
        ZoneResponse data = zoneService.getZoneById(id);
        return ResponseEntity.ok(ApiResponse.success(data,"Zone retrieved Successfully"));
    }

    @GetMapping("/find-by-name")
    public Optional<Zone> findByZoneName(@RequestParam String name) {
        return zoneService.getByZoneName(name);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse<ZoneResponse>> addZone(@Valid @RequestBody ZoneRequest zone) {
        ZoneResponse data= zoneService.addZone(zone);
        return ResponseEntity.ok(ApiResponse.success(data,"Zone Added Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<ZoneResponse>> updateZone(@PathVariable Long id, @Valid @RequestBody ZoneUpdateRequest zone) {
        ZoneResponse data= zoneService.updateZone(id, zone);
        return ResponseEntity.ok(ApiResponse.success(data,"Zone Updated Successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteZone(@PathVariable Long id) {
        return ResponseEntity.ok(zoneService.deleteZone(id));
    }
}
