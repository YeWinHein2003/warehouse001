package com.pearl.warehouse001.service; // Lowercase package

import com.pearl.warehouse001.dto.ZoneRequest;
import com.pearl.warehouse001.dto.ZoneResponse;
import com.pearl.warehouse001.dto.ZoneUpdateRequest;
import com.pearl.warehouse001.entity.Warehouse;
import com.pearl.warehouse001.entity.Zone;
import com.pearl.warehouse001.exception.NameDuplicateException;
import com.pearl.warehouse001.mapper.ZoneMapper;
import com.pearl.warehouse001.repository.Specification.ZoneSpecification;
import com.pearl.warehouse001.repository.WarehouseRepository;
import com.pearl.warehouse001.repository.ZoneRepository;
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

import javax.naming.NameAlreadyBoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ZoneService {

    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of("id", "name", "zoneType");

    @Autowired
    private ZoneMapper zoneMapper;

    private final ZoneRepository zoneRepository;

    private final WarehouseRepository warehouseRepository;

    // Advanced Paging method consistent with WarehouseService
    @Transactional(readOnly = true)
    public Page<ZoneResponse> getZonesPaginated(
            String keyword,
            String name,
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

        Specification<Zone> spec = Specification.where(ZoneSpecification.hasName(name))
                .and(ZoneSpecification.globalSearch(keyword));

        return zoneRepository.findAll(spec,pageable).map(zoneMapper::toResponse);
    }

    public Optional<Zone> getByZoneName(String zoneName) {
        return zoneRepository.findByNameContaining(zoneName);
    }

    public ZoneService(ZoneRepository zoneRepository, WarehouseRepository warehouseRepository) {
        this.zoneRepository = zoneRepository;
        this.warehouseRepository = warehouseRepository;
    }

    public ZoneResponse addZone(ZoneRequest zoneRequest) {

        if (zoneRepository.existsByNameAndWarehouseId(zoneRequest.name(),zoneRequest.warehouseId())){
            throw new NameDuplicateException("Zone Name"+ zoneRequest.name()+" already exist");
        }

        Warehouse warehouse = warehouseRepository.findById(zoneRequest.warehouseId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Warehouse not found"));

        Zone zone = zoneMapper.toEntity(zoneRequest);
        zone.setWarehouse(warehouse);

        Zone saved = zoneRepository.save(zone);
        return zoneMapper.toResponse(saved);

    }

    public Page<ZoneResponse> getAllZones(Pageable pageable) {
        return zoneRepository.findAllZones(pageable)
                .map(zoneMapper::toResponse);
    }

    public ZoneResponse getZoneById(Long id) {
        Zone zone=zoneRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Zone not found"));
        return zoneMapper.toResponse(zone);
    }

    public ZoneResponse updateZone(Long id, ZoneUpdateRequest zoneRequest) {
        Zone zone = zoneRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Zone not found"));

        if(!zone.getName().equals(zoneRequest.name()) && zoneRepository.existsByNameAndWarehouseId(zoneRequest.name(),zoneRequest.warehouseId())){
            throw new NameDuplicateException("Zone name already exists in this warehouse.");
        }

        Warehouse warehouse = warehouseRepository.findById(zoneRequest.warehouseId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Warehouse not found"));

        // Update fields
        zone.setName(zoneRequest.name());
        zone.setZoneType(zoneRequest.zoneType());
        zone.setWarehouse(warehouse);


        return zoneMapper.toResponse(zoneRepository.save(zone));
    }

    public Boolean deleteZone(Long id) {
        if (!zoneRepository.existsById(id)) {
            throw new RuntimeException("Zone not found");
        }
        zoneRepository.deleteById(id);
        return true;
    }


    public Page<ZoneResponse> findByType(String zoneType,Pageable pageable) {
        return zoneRepository.findByZoneType(zoneType,pageable)
                .map(zoneMapper::toResponse);
    }
}
