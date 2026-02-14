package com.pearl.warehouse001.repository;

import com.pearl.warehouse001.entity.Township;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TownshipRepository extends JpaRepository<Township, Long> {
    List<Township> findByRegionId(Long regionId);

    Township findByRegion_id(Long regionId);
}
