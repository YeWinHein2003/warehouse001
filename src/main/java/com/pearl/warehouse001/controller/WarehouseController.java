package com.pearl.warehouse001.controller;

import com.pearl.warehouse001.dto.api.ApiResponse;
import com.pearl.warehouse001.dto.WarehouseRequest;
import com.pearl.warehouse001.dto.WarehouseResponse;
import com.pearl.warehouse001.dto.WarehouseUpdateRequest;
import com.pearl.warehouse001.dto.api.Pagination;
import com.pearl.warehouse001.entity.Warehouse;
import com.pearl.warehouse001.service.WarehouseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }


    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<WarehouseResponse>>> getAllWarehouses() {
        List<WarehouseResponse> warehouses = warehouseService.getAllWarehouses();
        return ResponseEntity.ok(ApiResponse.success(warehouses, "Warehouses found Successfully"));
    }

    @GetMapping("/find-by-name")
    public Optional<Warehouse> findByName(@RequestParam("name") String name) {
        return warehouseService.getWarehouseByName(name);
    }

    @GetMapping("/list-paging")
    public ResponseEntity<ApiResponse<List<WarehouseResponse>>> getWarehouses(
            @RequestParam(required = false)String keyword,
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id", required = false) String sortBy,
            @RequestParam(defaultValue = "DESC", required = false) String direction) {

        // Assuming your service now accepts these primitive types like ProductService
        Page<WarehouseResponse> warehousePage = warehouseService.getWarehousesPaginated(keyword,name, page, size, sortBy, direction);

        // Manually build Pagination object to match your Product style
        Pagination pagination = new Pagination(
                warehousePage.getNumber(),
                warehousePage.getSize(),
                warehousePage.getTotalElements(),
                warehousePage.getTotalPages(),
                warehousePage.hasNext(),
                warehousePage.hasPrevious()
        );

        ApiResponse<List<WarehouseResponse>> response = ApiResponse.success(
                warehousePage.getContent(),
                "Warehouses fetched successfully"
        );
        response.setPagination(pagination);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<WarehouseResponse>> getById(@PathVariable Long id) {
        WarehouseResponse data = warehouseService.getWarehouseById(id);
        return ResponseEntity.ok(ApiResponse.success(data, "Warehouse Retrieved Successfully"));
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse<WarehouseResponse>> addWarehouse(@Valid @RequestBody  WarehouseRequest warehouse) {
        WarehouseResponse data=warehouseService.saveWarehouse(warehouse);
        return ResponseEntity.ok(ApiResponse.success(data, "Warehouse Added Successfully"));
    }

//    @PostMapping("/save")
//    public ResponseEntity<ApiResponse<Long>> addWarehouseWithId(@Valid @RequestBody  WarehouseRequest warehouse) {
//        Long id =warehouseService.saveWarehouseWithId(warehouse);
//        return ResponseEntity.ok(ApiResponse.success(id, "Warehouse Added Successfully"));
//    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<WarehouseResponse>> updateWarehouse(
            @PathVariable Long id,
            @Valid @RequestBody WarehouseUpdateRequest warehouseRequest) {
        WarehouseResponse data=warehouseService.updateWarehouse(id, warehouseRequest);
        return ResponseEntity.ok(ApiResponse.success(data, "Warehouse Updated Successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(warehouseService.deleteWarehouseById(id));
    }


}
