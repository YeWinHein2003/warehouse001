package com.pearl.warehouse001.repository;

import com.pearl.warehouse001.entity.Bin;
import com.pearl.warehouse001.entity.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BinRepository extends JpaRepository<Bin, Long>, JpaSpecificationExecutor<Bin> {

//    to solve N+1 problems
    @Override
    @EntityGraph(attributePaths = "zone")
    Page<Bin> findAll(Specification<Bin> spec,Pageable pageable);

//    boolean existsByCode(String code);


//    boolean existsByCodeAndZoneId(String code, Long zoneId);

    boolean existsByCodeAndZone_WarehouseId(String code, Long warehouseId);
}
