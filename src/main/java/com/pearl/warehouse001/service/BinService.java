package com.pearl.warehouse001.service;

import com.pearl.warehouse001.dto.BinRequest;
import com.pearl.warehouse001.dto.BinResponse;
import com.pearl.warehouse001.entity.Bin;
import com.pearl.warehouse001.entity.Zone;
import com.pearl.warehouse001.exception.NameDuplicateException;
import com.pearl.warehouse001.mapper.BinMapper;
import com.pearl.warehouse001.repository.BinRepository;
import com.pearl.warehouse001.repository.Specification.BinSpecification;
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

import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class BinService {
    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of("id","capacity","code");

    @Autowired
    private BinMapper binMapper;

    private final BinRepository binRepository;

    private final ZoneRepository zoneRepository;

    public BinService(BinRepository binRepository, ZoneRepository zoneRepository) {
        this.binRepository = binRepository;
        this.zoneRepository = zoneRepository;
    }

    @Transactional(readOnly = true)
    public Page<BinResponse> getBinPaginated(
            String code,
            String zoneName,
            int page,
            int size,
            String sortBy,
            String direction
    ){
        if(!ALLOWED_SORT_FIELDS.contains(sortBy)){
            sortBy = "id";
        }
        Sort sort= "DESC".equalsIgnoreCase(direction)
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Specification<Bin> spec = BinSpecification.filterBins(code, zoneName);

        return binRepository.findAll(spec,pageable).map(binMapper::toResponse);
    }

    public BinResponse addBin(BinRequest binRequest){

//        if(binRepository.existsByCodeAndZoneId(binRequest.code(), binRequest.zoneId())){
//            throw new NameDuplicateException("Bin with code:"+ binRequest.code()+" already exist");
//        }
//
//        if(binRepository.existsByCode(binRequest.code())){
//            throw new NameDuplicateException("Bin with code: "+ binRequest.code()+" already exist");
//        }


        Zone zone=zoneRepository.findById(binRequest.zoneId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Zone not found"));

        // Use the traversal method to check uniqueness within THIS warehouse
        if (binRepository.existsByCodeAndZone_WarehouseId(binRequest.code(), zone.getWarehouse().getId())) {
            throw new NameDuplicateException("Bin code '" + binRequest.code() + "' already exists in this warehouse.");
        }

        Bin bin = binMapper.toEntity(binRequest);
        bin.setZone(zone);

        return binMapper.toResponse(binRepository.save(bin));
    }

    public Boolean deleteBin(Long id){
        if(!binRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Bin not found By Id: "+ id);
        }
        binRepository.deleteById(id);
        return true;
    }



}
