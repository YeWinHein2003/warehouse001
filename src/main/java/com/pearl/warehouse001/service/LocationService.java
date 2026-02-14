package com.pearl.warehouse001.service;

import com.pearl.warehouse001.dto.RegionResponse;
import com.pearl.warehouse001.dto.TownshipResponse;
import com.pearl.warehouse001.entity.Region;
import com.pearl.warehouse001.mapper.LocationMapper;
import com.pearl.warehouse001.repository.RegionRepository;
import com.pearl.warehouse001.repository.TownshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private TownshipRepository townshipRepository;
    @Autowired
    private LocationMapper locationMapper;

    public List<RegionResponse> getAllRegions() {
        return regionRepository.findAll().stream()
                .map(locationMapper::toRegionResponse).toList();
    }

    public List<TownshipResponse> getTownshipsByRegion(Long regionId) {
        return townshipRepository.findByRegionId(regionId).stream()
                .map(locationMapper::toTownshipResponse).toList();
    }
}
