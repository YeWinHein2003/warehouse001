package com.pearl.warehouse001.controller;

import com.pearl.warehouse001.dto.BinRequest;
import com.pearl.warehouse001.dto.BinResponse;
import com.pearl.warehouse001.dto.api.ApiResponse;
import com.pearl.warehouse001.dto.api.Pagination;
import com.pearl.warehouse001.service.BinService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bins")
public class BinController {

    private final BinService binService;
    public BinController(BinService binService) {
        this.binService = binService;
    }

    @GetMapping("list-paging")
    public ResponseEntity<ApiResponse<List<BinResponse>>> getAll(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String zoneName,
            @RequestParam(defaultValue="0") int page,
            @RequestParam(defaultValue="10") int size,
            @RequestParam(defaultValue="id")String sortBy,
            @RequestParam(defaultValue="DESC") String direction){

                Page<BinResponse> binPage= binService.getBinPaginated(code,zoneName,page, size, sortBy, direction);

        Pagination pagination = new Pagination(
                binPage.getNumber(),
                binPage.getSize(),
                binPage.getTotalElements(),
                binPage.getTotalPages(),
                binPage.hasNext(),
                binPage.hasPrevious()
        );

        ApiResponse<List<BinResponse>> response = ApiResponse.success(
                binPage.getContent(),
                "Bins fetched successfully"
        );
        response.setPagination(pagination);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<BinResponse>> saveBin(@Valid @RequestBody BinRequest bin){
        BinResponse data = binService.addBin(bin);
        return ResponseEntity.ok(ApiResponse.success(data,"Bin added successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteBin(@PathVariable Long id){
        return ResponseEntity.ok(binService.deleteBin(id));
    }

}
