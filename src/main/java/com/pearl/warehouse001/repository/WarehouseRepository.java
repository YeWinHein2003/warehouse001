package com.pearl.warehouse001.repository;

import com.pearl.warehouse001.entity.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    // Optimized with FETCH JOIN to get zones in ONE query
    @Query(value = "SELECT DISTINCT w FROM Warehouse w LEFT JOIN FETCH w.zones",
            countQuery = "SELECT count(w) FROM Warehouse w")
    Page<Warehouse> findAll(Pageable pageable);

    Page<Warehouse> findByName(String name, Pageable pageable);

    Page<Warehouse> findByLocation(String location, Pageable pageable);

    Page<Warehouse> findByTownshipContainingIgnoreCase(String township, Pageable pageable);

    Page<Warehouse> findByWarehouseType(Warehouse.WarehouseType warehouseType, Pageable pageable);

    boolean existsByName(String name);
}
