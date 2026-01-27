package com.pearl.warehouse001.service; // Lowercase package

import com.pearl.warehouse001.dto.ZoneRequest;
import com.pearl.warehouse001.dto.ZoneResponse;
import com.pearl.warehouse001.entity.Warehouse;
import com.pearl.warehouse001.entity.Zone;
import com.pearl.warehouse001.repository.WarehouseRepository;
import com.pearl.warehouse001.repository.ZoneRepository;
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
public class ZoneService {

    private final ZoneRepository zoneRepository;
    private final WarehouseRepository warehouseRepository;

    public ZoneService(ZoneRepository zoneRepository, WarehouseRepository warehouseRepository) {
        this.zoneRepository = zoneRepository;
        this.warehouseRepository = warehouseRepository;
    }

    public ZoneResponse addZone(ZoneRequest zoneRequest) {

        if (zoneRepository.existsByNameAndWarehouseId(zoneRequest.name(),zoneRequest.warehouseId())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Zone name: "+zoneRequest.name()+" already exists in this warehouse.");
        }

        Warehouse warehouse = warehouseRepository.findById(zoneRequest.warehouseId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Warehouse not found"));

        Zone zone = new Zone();
        zone.setName(zoneRequest.name());
        zone.setZoneType(zoneRequest.zoneType());
        zone.setWarehouse(warehouse);
        zone.setCreatedUserId(zoneRequest.createdUserId());
        Zone saved = zoneRepository.save(zone);

        return mapToResponse(saved);
    }

    public Page<ZoneResponse> getAllZones(Pageable pageable) {
        return zoneRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    public ZoneResponse getZoneById(Long id) {
        Zone zone=zoneRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Zone not found"));
        return mapToResponse(zone);
    }

    private ZoneResponse mapToResponse(Zone zone) {
        return new ZoneResponse(
                zone.getId(),
                zone.getName(),
                zone.getZoneType(),
                zone.getWarehouse() != null ? zone.getWarehouse().getId() : null,
                zone.getWarehouse() != null ? zone.getWarehouse().getName() : "N/A",
                zone.getCreatedAt()
        );
    }


    public void deleteZone(Long id) {
        if (!zoneRepository.existsById(id)) {
            throw new RuntimeException("Cannot delete: Zone not found with id: " + id);
        }
        zoneRepository.deleteById(id);
    }

    public Page<ZoneResponse> findByType(String zoneType,Pageable pageable) {
        return zoneRepository.findByZoneType(zoneType,pageable)
                .map(this::mapToResponse);
    }
}
