package com.pearl.warehouse001.service;

import com.pearl.warehouse001.dto.WarehouseRequest;
import com.pearl.warehouse001.dto.WarehouseResponse;
import com.pearl.warehouse001.dto.WarehouseUpdateRequest;
import com.pearl.warehouse001.entity.Warehouse;
import com.pearl.warehouse001.exception.NameDuplicateException;
import com.pearl.warehouse001.mapper.WarehouseMapper;
import com.pearl.warehouse001.repository.WarehouseRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;
import java.util.Set;


@Service
@Transactional
public class WarehouseService {

    // Define allowed sort fields for pagination
    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of(
            "id", "name", "location", "township", "warehouseType"
    );

    @Autowired
    private  WarehouseRepository warehouseRepository;

    @Autowired
    private WarehouseMapper warehouseMapper;

    /**
     * Advanced Search/Filter method similar to your ProductService
     */
    public Page<WarehouseResponse> getWarehouses(
            int page,
            int size,
            String sortBy,
            String direction,
            Specification<Warehouse> spec) {

        // Validate Sort Field
        if (!ALLOWED_SORT_FIELDS.contains(sortBy)) {
            sortBy = "id";
        }

        Sort sort = "DESC".equalsIgnoreCase(direction)
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        // Uses JpaSpecificationExecutor for dynamic filtering
        Page<Warehouse> warehousePage = warehouseRepository.findAll(spec, pageable);

        return warehousePage.map(warehouseMapper::toResponse);
    }




//    public WarehouseService(WarehouseRepository warehouseRepository) {
//        this.warehouseRepository = warehouseRepository;
//    }

    public Page<WarehouseResponse> getAllWarehouses(Pageable pageable) {
        return warehouseRepository.findAllWarehousesWithZones(pageable)
                .map(warehouseMapper::toResponse);
    }


    public WarehouseResponse saveWarehouse(WarehouseRequest warehouseRequest) {

        if(warehouseRepository.existsByName(warehouseRequest.name())){
            throw new NameDuplicateException("Warehouse name already exists");
        }
        Warehouse warehouse = warehouseMapper.toEntity(warehouseRequest);
        Warehouse savedWarehouse = warehouseRepository.save(warehouse);
        return warehouseMapper.toResponse(savedWarehouse);
    }

    @Transactional
    public Long saveWarehouseWithId(WarehouseRequest request) {
        if (warehouseRepository.existsByName(request.name())) {
            throw new NameDuplicateException("Warehouse name already exists");
        }

        Warehouse warehouse = warehouseMapper.toEntity(request);
        Warehouse saved = warehouseRepository.save(warehouse);
        return saved.getId();
    }

    public WarehouseResponse updateWarehouse(Long id, WarehouseUpdateRequest warehouseRequest) {

        Warehouse existing= warehouseRepository.findById(id)
                .orElseThrow(() -> new NameDuplicateException("Warehouse not found"));

        if(!existing.getName().equals(warehouseRequest.name()) && warehouseRepository.existsByName(warehouseRequest.name())){
            throw new NameDuplicateException("Warehouse name already exists");
        }
        existing.setName(warehouseRequest.name());
        existing.setLocation(warehouseRequest.location());
        existing.setTownship(warehouseRequest.township());
        existing.setWarehouseType(warehouseRequest.warehouseType());

        Warehouse updated = warehouseRepository.save(existing);
        return warehouseMapper.toResponse(updated);
    }




    public WarehouseResponse getWarehouseById(Long id) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Warehouse not found"));
        return warehouseMapper.toResponse(warehouse);
    }



    public Boolean deleteWarehouseById(Long id) {
        warehouseRepository.deleteById(id);
        return true;
    }

    public Page<WarehouseResponse> getWarehousesPaginated(
            int page,
            int size,
            String sortBy,
            String direction) {

        if (!ALLOWED_SORT_FIELDS.contains(sortBy)) {
            sortBy = "id";
        }

        Sort sort = "DESC".equalsIgnoreCase(direction)
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Warehouse> warehousePage = warehouseRepository.findAll(pageable);

        // Map entire page to Response DTOs
        return warehousePage.map(warehouseMapper::toResponse);
    }

}
