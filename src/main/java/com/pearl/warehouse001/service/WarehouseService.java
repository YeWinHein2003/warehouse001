package com.pearl.warehouse001.service;

import com.pearl.warehouse001.dto.WarehouseRequest;
import com.pearl.warehouse001.dto.WarehouseResponse;
import com.pearl.warehouse001.entity.Warehouse;
import com.pearl.warehouse001.repository.WarehouseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public WarehouseResponse saveWarehouse(WarehouseRequest warehouseRequest) {

        if(warehouseRepository.existsByName(warehouseRequest.name())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Warehouse name :" + warehouseRequest.name() + " already exist");
        }

        Warehouse warehouse = new Warehouse();
        warehouse.setName(warehouseRequest.name());
        warehouse.setLocation(warehouseRequest.location());
        warehouse.setTownship(warehouseRequest.township());
        warehouse.setWarehouseType(warehouseRequest.warehouseType());
        warehouse.setCreatedUserId(warehouseRequest.createdUserId());

        Warehouse saved = warehouseRepository.save(warehouse);
        return mapToResponse(saved);
    }

    public Page<WarehouseResponse> getAllWarehouses(Pageable pageable) {
        return warehouseRepository.findAll(pageable)
                .map(this::mapToResponse);// Page has a built-in .map() method
    }


    public WarehouseResponse getWarehouseById(Long id) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Warehouse not found"));
        return mapToResponse(warehouse);
    }

    // Helper method to map Entity to Response DTO
    private WarehouseResponse mapToResponse(Warehouse warehouse) {
        return new WarehouseResponse(
                warehouse.getId(),
                warehouse.getName(),
                warehouse.getLocation(),
                warehouse.getTownship(),
                warehouse.getLocation() + "," + warehouse.getTownship(),
                warehouse.getWarehouseType(),
                warehouse.getCreatedAt()
        );
    }

    public void deleteWarehouseById(Long id) {
        warehouseRepository.deleteById(id);
    }

    public Page<WarehouseResponse> getWarehouseByName(String name,Pageable pageable) {
        return warehouseRepository.findByName(name, pageable)
                .map(this::mapToResponse);
    }

    public Page<WarehouseResponse> getWarehouseByTownship(String township,Pageable pageable) {
        return warehouseRepository.findByTownshipContainingIgnoreCase(township, pageable)
                .map(this::mapToResponse);
    }

    public Page<WarehouseResponse> getWarehouseByType(Warehouse.WarehouseType warehouseType, Pageable pageable) {
        return warehouseRepository.findByWarehouseType(warehouseType, pageable)
                .map(this::mapToResponse);
    }

    public Page<WarehouseResponse> getWarehouseByLocation(String location, Pageable pageable) {
        return warehouseRepository.findByLocation(location, pageable)
                .map(this::mapToResponse);
    }

}
