package com.pearl.warehouse001.repository;

import com.pearl.warehouse001.entity.Zone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {
    Page<Zone> findByZoneType(String zoneType,Pageable pageable);


    // Optimized for Pagination + N+1 Problem
    @Query(value = "SELECT z from Zone z JOIN FETCH z.warehouse",
            countQuery= "SELECT count(z) FROM Zone z")
    Page<Zone> findAll(Pageable pageable);

    boolean existsByNameAndWarehouseId(String name,Long warehouseId);
}
