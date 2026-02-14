//package com.pearl.warehouse001.service;
//
//import com.pearl.warehouse001.entity.Warehouse;
//import com.pearl.warehouse001.repository.WarehouseRepository;
//import com.pearl.warehouse001.utils.ExcelHelper;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.util.List;
//
//@Service
//public class ExcelService {
//
//    private WarehouseRepository warehouseRepository;
//
//    public void save(MultipartFile file) {
//        try{
//            List<Warehouse> warehouses = ExcelHelper.excelToWarehouses(file.getInputStream());
//            warehouseRepository.saveAll(warehouses);
//        }catch (IOException e){
//            throw new RuntimeException("Failed to store excel file"+e.getMessage());
//        }
//    }
//
//    public ByteArrayInputStream load(){
//        List<Warehouse> warehouses = warehouseRepository.findAll();
//        return ExcelHelper.warehouseToExcel(warehouses);
//    }
//}
