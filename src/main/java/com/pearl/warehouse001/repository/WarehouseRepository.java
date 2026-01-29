package com.pearl.warehouse001.repository;

import com.pearl.warehouse001.entity.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
//JpaSpecificationExecutor to allow dynamic filters without writing dozens of repository methods
public interface WarehouseRepository extends JpaRepository<Warehouse, Long>, JpaSpecificationExecutor<Warehouse> {

    // Optimized with FETCH JOIN to get zones in ONE query
    @Query(value = "SELECT DISTINCT w FROM Warehouse w LEFT JOIN FETCH w.zones",
            countQuery = "SELECT count(w) FROM Warehouse w")
    Page<Warehouse> findAllWarehousesWithZones(Pageable pageable);

    Optional<Warehouse> findByName(String name);

    Page<Warehouse> findByLocation(String location, Pageable pageable);

    Page<Warehouse> findByTownshipContainingIgnoreCase(String township, Pageable pageable);

    Page<Warehouse> findByWarehouseType(Warehouse.WarehouseType warehouseType, Pageable pageable);

    boolean existsByName(String name);
}
