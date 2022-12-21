package com.softdreams.excel.helper;

import com.softdreams.excel.domain.DmNCC;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class DmNccExcelHelper {
    public static String TYPE1 = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static String TYPE2 = "application/vnd.ms-excel";
    static String[] HEADERs = {"Mã NCC", "Tên NCC", "Quy mô", "Loại đối tượng", "Điện thoại", "Địa chỉ", "Mã số thuế", "Email",
            "Website", "Fax", "Nhóm NCC", "Tên người liên hệ", "Chức vụ người liên hệ", "Giới tính người liên hệ", "Địa chỉ người liên hệ",
            "ĐT di động người liên hệ", "Email người liên hệ", "ĐT nhà riêng", "ĐT cơ quan", "Số CMND người liên hệ", "Ngày cấp", "Nơi cấp",
            "Số tài khoản ngân hàng", "Tên ngân hàng", "Tên chi nhánh", "Tên chủ tài khoản"};
    static String SHEET = "NHACUNGCAP";

    public static boolean hasExcelFormat(MultipartFile file) {

        if (TYPE1.equals(file.getContentType()) || TYPE2.equals(file.getContentType())) {
            return true;
        }
        return false;
    }

    public static List<DmNCC> excelToDmNcc(InputStream is) {
        try {
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);

            if(sheet.getRow(sheet.getLastRowNum()).getCell(0).getStringCellValue().contains("Số dòng")){
                sheet.removeRow(sheet.getRow(sheet.getLastRowNum()));
            }
//            Row rowDelete = sheet.getRow(sheet.getLastRowNum());
//
//            sheet.removeRow(rowDelete);

            Iterator<Row> rows = sheet.iterator();
            List<DmNCC> dmNCCS = new ArrayList<>();


            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0 || rowNumber == 1) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                DmNCC dmNCC = new DmNCC();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            dmNCC.setSupplier_code(currentCell.getStringCellValue());
                            break;

                        case 1:
                            dmNCC.setSupplier_name(currentCell.getStringCellValue());
                            break;

                        case 2:
                            dmNCC.setAddress(currentCell.getStringCellValue());
                            break;

                        case 3:
                            dmNCC.setGroup_kh_ncc(currentCell.getStringCellValue());
                            break;
                        case 4:
                            dmNCC.setTax_code(currentCell.getStringCellValue());
                            break;
                        case 5:
                            dmNCC.setPhone_number(currentCell.getStringCellValue());
                            break;
                        case 6:
                            dmNCC.setUnfollow(currentCell.getBooleanCellValue());
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }

                dmNCCS.add(dmNCC);
            }

            workbook.close();

            return dmNCCS;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

//    public static ByteArrayInputStream supplierToExcel(List<Supplier> suppliers) {
//        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
//            Sheet sheet = workbook.createSheet(SHEET);
//
//            // Header
//            Row headerRow = sheet.createRow(0);
//            headerRow.setHeight((short) 400);
//            CellStyle cellStyleHeader = workbook.createCellStyle();
//            Font fontHeader = workbook.createFont();
//            fontHeader.setBold(true);
//            cellStyleHeader.setFont(fontHeader);
//            cellStyleHeader.setAlignment(HorizontalAlignment.CENTER);
//            cellStyleHeader.setVerticalAlignment(VerticalAlignment.CENTER);
//            cellStyleHeader.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
//            cellStyleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//            cellStyleHeader.setBorderBottom(BorderStyle.THIN);
//            cellStyleHeader.setBorderTop(BorderStyle.THIN);
//            cellStyleHeader.setBorderRight(BorderStyle.THIN);
//            cellStyleHeader.setBorderLeft(BorderStyle.THIN);
//
//            for (int col = 0; col < HEADERs.length; col++) {
//                Cell cell = headerRow.createCell(col);
//                cell.setCellValue(HEADERs[col]);
//                cell.setCellStyle(cellStyleHeader);
//                if (col == 1 || col == 5) {
//                    sheet.setColumnWidth(1, 25 * 800);
//                    sheet.setColumnWidth(5, 25 * 1100);
//                } else {
//                    sheet.setColumnWidth(col, 20 * 400);
//                }
//            }
//
//            int rowIdx = 1;
//            for (Supplier supplier : suppliers) {
//                Row row = sheet.createRow(rowIdx++);
//                row.createCell(0).setCellValue(supplier.getSupplierCode());
//                row.createCell(1).setCellValue(supplier.getSupplierName());
//                boolean checkContainScale = false;
//                for (String scale : scales) {
//                    if (supplier.getSupplierName().toLowerCase().contains(scale.toLowerCase())) {
//                        checkContainScale = true;
//                        break;
//                    }
//                }
//                if (checkContainScale) {
//                    row.createCell(2).setCellValue("Tổ chức");
//                } else {
//                    row.createCell(2).setCellValue("Cá nhân");
//                }
//                row.createCell(3).setCellValue("Nhà cung cấp");
//                row.createCell(4).setCellValue(supplier.getPhoneNumber());
//                row.createCell(5).setCellValue(supplier.getAddress());
//                row.createCell(6).setCellValue(supplier.getTaxCode());
//                row.createCell(7).setCellValue("");
//                row.createCell(8).setCellValue("");
//                row.createCell(9).setCellValue("");
//                row.createCell(10).setCellValue(supplier.getGroup());
//                row.createCell(11).setCellValue("");
//                row.createCell(12).setCellValue("");
//                row.createCell(13).setCellValue("");
//                row.createCell(14).setCellValue("");
//                row.createCell(15).setCellValue("");
//                row.createCell(16).setCellValue("");
//                row.createCell(17).setCellValue("");
//                row.createCell(18).setCellValue("");
//                row.createCell(19).setCellValue("");
//                row.createCell(20).setCellValue("");
//                row.createCell(21).setCellValue("");
//                row.createCell(22).setCellValue("");
//                row.createCell(23).setCellValue("");
//                row.createCell(24).setCellValue("");
//                row.createCell(25).setCellValue("");
//            }
//
//            workbook.write(out);
//            return new ByteArrayInputStream(out.toByteArray());
//        } catch (IOException e) {
//            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
//        }
//
//    }
}
