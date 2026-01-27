package com.pearl.warehouse001.controller;

import com.pearl.warehouse001.dto.ApiResponse;
import com.pearl.warehouse001.dto.WarehouseRequest;
import com.pearl.warehouse001.dto.WarehouseResponse;
import com.pearl.warehouse001.entity.Warehouse;
import com.pearl.warehouse001.service.WarehouseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }


    @GetMapping
    public ApiResponse<Page<WarehouseResponse>> getAll(
            @org.springframework.data.web.PageableDefault(size = 10, sort = "name")Pageable pageable) {
       Page<WarehouseResponse> data =  warehouseService.getAllWarehouses(pageable);
       return new ApiResponse<>(true, "All Warehouses retrieved Successfully", data);
    }

    @GetMapping("/{id}")
    public ApiResponse<WarehouseResponse> getById(@PathVariable Long id) {
        WarehouseResponse data = warehouseService.getWarehouseById(id);
        return new ApiResponse<>(true, "Warehouse retrieved Successfully", data);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<WarehouseResponse> addWarehouse(@Valid @RequestBody  WarehouseRequest warehouse) {
        WarehouseResponse data=warehouseService.saveWarehouse(warehouse);
        return new ApiResponse<>(true, "Warehouse Added Successfully", data);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteById(@PathVariable Long id){

        warehouseService.deleteWarehouseById(id);
        return new ApiResponse<>(true,"Deleted Successfully", null);
    }


}
