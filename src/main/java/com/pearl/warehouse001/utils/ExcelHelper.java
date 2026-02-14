//package com.pearl.warehouse001.utils;
//
//import com.pearl.warehouse001.entity.Warehouse;
//import org.apache.commons.io.output.ByteArrayOutputStream;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//import static org.apache.poi.ss.usermodel.TableStyleType.headerRow;
//
//public class ExcelHelper {
//    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
//    static String[] HEADERS={"ID","NAME","LOCATION","TYPE","CREATED AT"};
//    static String SHEET="Warehouse";
//
//    // Convert List of Entities to Excel InputStream
//    public static ByteArrayInputStream warehouseToExcel(List<Warehouse> warehouseList) {
//     try(Workbook workbook=new XSSFWorkbook();
//         ByteArrayOutputStream out=new ByteArrayOutputStream();
//     ){
//            Sheet sheet=workbook.createSheet(SHEET);
//            Row headerRow=sheet.createRow(0);
//            for(int col=0;col<HEADERS.length;col++){
//                Cell cell = headerRow.createCell(col);
//                cell.setCellValue(HEADERS[col]);
//            }
//
//            int rowIdx=1;
//            for(Warehouse warehouse : warehouseList){
//                Row row=sheet.createRow(rowIdx++);
//                row.createCell(0).setCellValue(warehouse.getId());
//                row.createCell(1).setCellValue(warehouse.getName());
//                row.createCell(2).setCellValue(warehouse.getLocation());
//                row.createCell(3).setCellValue(warehouse.getTownship());
//
//                row.createCell(4).setCellValue(warehouse.getWarehouseType().toString());
//                //to handle OffSetDateTime
//                if(warehouse.getCreatedAt()!=null){
//                    row.createCell(5).setCellValue(warehouse.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
//                }
//
//            }
//            workbook.write(out);
//            return new ByteArrayInputStream(out.toByteArray());
//     }catch (IOException e) {
//         throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
//     }
//    }
//
//    public static List<Warehouse> excelToWarehouses(InputStream is) {
//        try (Workbook workbook = new XSSFWorkbook(is)) {
//            Sheet sheet = workbook.getSheet(SHEET);
//            Iterator<Row> rows = sheet.iterator();
//            List<Warehouse> warehouses = new ArrayList<>();
//
//            int rowNumber = 0;
//            while (rows.hasNext()) {
//                Row currentRow = rows.next();
//                if (rowNumber == 0) { rowNumber++; continue; } // Skip header
//
//                Warehouse warehouse = new Warehouse();
//
//                // Column 0: ID (Numeric)
//                warehouse.setId((long) currentRow.getCell(0).getNumericCellValue());
//
//                // Column 1-3: String fields
//                warehouse.setName(currentRow.getCell(1).getStringCellValue());
//                warehouse.setLocation(currentRow.getCell(2).getStringCellValue());
//                warehouse.setTownship(currentRow.getCell(3).getStringCellValue());
//
//                // Column 4: Enum (WarehouseType)
//                // Convert string from Excel back to Enum
//                String typeStr = currentRow.getCell(4).getStringCellValue();
//                warehouse.setWarehouseType(Warehouse.WarehouseType.valueOf(typeStr.toUpperCase()));
//
//                warehouses.add(warehouse);
//            }
//            return warehouses;
//        } catch (IOException e) {
//            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
//        }
//    }
//
//
//
//}
