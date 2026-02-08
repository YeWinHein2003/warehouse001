package com.pearl.warehouse001.repository;

import com.pearl.warehouse001.entity.Zone;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long>, JpaSpecificationExecutor<Zone> {
    Page<Zone> findByZoneType(String zoneType,Pageable pageable);


    // Optimized for Pagination + N+1 Problem
    @Query(value = "SELECT z from Zone z JOIN FETCH z.warehouse",
            countQuery= "SELECT count(z) FROM Zone z")
    Page<Zone> findAllZones(Pageable pageable);

    Optional<Zone> findByNameContaining( String zoneName);

    boolean existsByNameAndWarehouseId(String name,Long warehouseId);

    boolean existsByName( String name);
}
