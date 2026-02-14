//package com.pearl.warehouse001.controller;
//
//import com.pearl.warehouse001.service.ExcelService;
//import com.pearl.warehouse001.utils.ExcelHelper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.InputStreamResource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//@RestController
//@RequestMapping("/api/v1/excel")
//public class ExcelController {
//
//    @Autowired
//    private ExcelService fileService;
//
//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
//        if(ExcelHelper.TYPE.equals(file.getContentType())) {
//            fileService.save(file);
//            return ResponseEntity.status(HttpStatus.OK).body("Upload Successfully: " + file.getOriginalFilename());
//        }
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Upload Failed");
//    }
//
//    @GetMapping("/download")
//    public ResponseEntity<InputStreamResource> getFile() {
//        String fileName = "warehouse.xlsx";
//
//        InputStreamResource file = new InputStreamResource(fileService.load());
//
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
//                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
//                .body(file);
//    }
//
//}
