package com.softdreams.excel.helper;

import com.softdreams.excel.domain.Congcudungcu;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CongcudungcuHelper {

    public static List<String> header_CCDC = List.of(
        "Vào sổ",
        "Mã CCDC(*)",
        "Tên CCDC(*)",
        "Ngày ghi tăng (*)",
        "Đơn vị tính",
        "Tổng số lượng",
        "Đơn giá",
        "Tổng giá trị",
        "Số kỳ phân bổ (*)",
        "Số kỳ PB còn lại",
        "Giá trị đã PB",
        "Giá trị còn lại",
        "Giá trị phân bổ/kỳ",
        "TK chờ PB",
        "Loại đối tượng PB(*)",
        "Đối tượng PB(*)",
        "Số lượng",
        "Tỷ lệ PB",
        "TK chi phí",
        "Khoản mục chi phí"
    );

    public static List<Congcudungcu> excelToCongcudungcu(InputStream inputStream) {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            if (sheet.getRow(sheet.getLastRowNum()).getCell(0).getStringCellValue().contains("Số dòng")) {
                sheet.removeRow(sheet.getRow(sheet.getLastRowNum()));
            }
            Iterator<Row> rows = sheet.iterator();
            List<Congcudungcu> list = new ArrayList<>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row current = rows.next();
                if (rowNumber < 3) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellIterator = current.cellIterator();
                Congcudungcu congcudungcu = new Congcudungcu();
                int cellIndex = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0:
                            congcudungcu.setMa_ccdc(cell.getStringCellValue());
                            break;
                        case 1:
                            congcudungcu.setTen_ccdc(cell.getStringCellValue());
                            break;
                        case 2:
                            congcudungcu.setLoai_ccdc(cell.getStringCellValue());
                            break;
                        case 3:
                            congcudungcu.setLy_do_ghi_tang(cell.getStringCellValue());
                            break;
                        case 4:
                            congcudungcu.setNgay_ghi_tang(cell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                            break;
                        case 5:
                            congcudungcu.setSo_ct_ghi_tang(cell.getStringCellValue());
                            break;
                        case 6:
                            congcudungcu.setSo_ky_phan_bo((int) cell.getNumericCellValue());
                            break;
                        case 7:
                            congcudungcu.setSo_ky_pb_con_lai((int) cell.getNumericCellValue());
                            break;
                        case 8:
                            congcudungcu.setDvt(cell.getStringCellValue());
                            break;
                        case 9:
                            congcudungcu.setSl_ghi_tang(cell.getNumericCellValue());
                            break;
                        case 10:
                            congcudungcu.setLuy_ke_sl_da_giam(cell.getNumericCellValue());
                            break;
                        case 11:
                            congcudungcu.setSl_con_lai(cell.getNumericCellValue());
                            break;
                        case 12:
                            congcudungcu.setGia_tri_ccdc(cell.getNumericCellValue());
                            break;
                        case 13:
                            congcudungcu.setGia_tri_PB_hang_ky(cell.getNumericCellValue());
                            break;
                        case 14:
                            congcudungcu.setPb_trong_ky(cell.getNumericCellValue());
                            break;
                        case 15:
                            congcudungcu.setLuy_ke_da_pb(cell.getNumericCellValue());
                            break;
                        case 16:
                            congcudungcu.setGia_tri_con_lai(cell.getNumericCellValue());
                            break;
                        case 17:
                            congcudungcu.setTk_cho_phan_bo(cell.getStringCellValue());
                            break;
                    }
                    cellIndex++;
                }
                list.add(congcudungcu);
            }
            return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ByteArrayInputStream ccdcToExcel(List<Congcudungcu> inventories) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet("CCDC");
            Row headerRow = sheet.createRow(0);
            headerRow.setHeight((short) 400);
            CellStyle cellStyleHeader = workbook.createCellStyle();
            Font fontHeader = workbook.createFont();
            fontHeader.setBold(true);
            cellStyleHeader.setFont(fontHeader);
            cellStyleHeader.setAlignment(HorizontalAlignment.CENTER);
            cellStyleHeader.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyleHeader.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
            cellStyleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cellStyleHeader.setBorderBottom(BorderStyle.THIN);
            cellStyleHeader.setBorderTop(BorderStyle.THIN);
            cellStyleHeader.setBorderRight(BorderStyle.THIN);
            cellStyleHeader.setBorderLeft(BorderStyle.THIN);

            for (int col = 0; col < header_CCDC.size(); col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(header_CCDC.get(col));
                cell.setCellStyle(cellStyleHeader);
                sheet.setColumnWidth(col, 20 * 250);
            }
            CellStyle cellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));
            CellStyle cellStyleNumber = workbook.createCellStyle();
            cellStyleNumber.setDataFormat(createHelper.createDataFormat().getFormat("#,##0.00"));
            int rowIdx = 1;
            for (Congcudungcu Congcudungcu : inventories) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue("Sổ tài chính");
                row.createCell(1).setCellValue(Congcudungcu.getMa_ccdc());
                row.createCell(2).setCellValue(Congcudungcu.getTen_ccdc());
                Cell cell = row.createCell(3);
                cell.setCellValue(Congcudungcu.getNgay_ghi_tang());
                cell.setCellStyle(cellStyle);
                row.createCell(4).setCellValue("");
                row.createCell(5).setCellValue("");
                row.createCell(6).setCellValue("");
                row.createCell(7).setCellValue("");
                row.createCell(8).setCellValue(Congcudungcu.getSo_ky_phan_bo());
                row.createCell(9).setCellValue(Congcudungcu.getSo_ky_pb_con_lai());
                cell = row.createCell(10);
                cell.setCellValue(Congcudungcu.getGia_tri_ccdc());
                cell.setCellStyle(cellStyleNumber);
                cell = row.createCell(11);
                cell.setCellValue(Congcudungcu.getGia_tri_con_lai());
                cell.setCellStyle(cellStyleNumber);
                cell = row.createCell(12);
                cell.setCellValue(Congcudungcu.getGia_tri_PB_hang_ky());
                cell.setCellStyle(cellStyleNumber);
                row.createCell(13).setCellValue(Congcudungcu.getTk_cho_phan_bo());
                row.createCell(14).setCellValue("");
                row.createCell(15).setCellValue("");
                row.createCell(16).setCellValue("");
                row.createCell(17).setCellValue("");
                row.createCell(18).setCellValue("");
                row.createCell(19).setCellValue("");
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }
}
