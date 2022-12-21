package com.softdreams.excel.helper;

import com.softdreams.excel.domain.Taisancodinh;
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

public class TaisancodinhHelper {

    public static List<String> header_TSCD = List.of(
        "Vào sổ",
        "Mã TSCĐ(*)",
        "Tên TSCĐ(*)",
        "Phòng ban(*)",
        "Loại(*)",
        "Ngày ghi tăng(*)",
        "Ngày bđ tính khấu hao(*)",
        "Thời gian sử dụng",
        "Tháng/Năm TGSD",
        "Thời gian SD còn lại",
        "Tháng/Năm TGSDCL",
        "Nguyên giá",
        "Giá trị tính KH",
        "Hao mòn lũy kế",
        "Giá trị còn lại",
        "Giá trị KH tháng",
        "TK nguyên giá(*)",
        "TK chi phí(*)",
        "TK khấu hao(*)",
        "ĐT tập hợp CP",
        "Mục thu-chi",
        "Mã thống kê",
        "Loại đối tượng PV(*)",
        "Đối tượng PB(*)",
        "Tỷ lệ PB",
        "TK phân bổ chi phí",
        "Khoản mục chi phí"
    );

    public static List<Taisancodinh> excelToTaisancodinh(InputStream inputStream) {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            if (sheet.getRow(sheet.getLastRowNum()).getCell(0).getStringCellValue().contains("Số dòng")) {
                sheet.removeRow(sheet.getRow(sheet.getLastRowNum()));
            }
            Iterator<Row> rows = sheet.iterator();
            List<Taisancodinh> list = new ArrayList<>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row current = rows.next();
                if (rowNumber < 3) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellIterator = current.cellIterator();
                Taisancodinh Taisancodinh = new Taisancodinh();
                int cellIndex = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0:
                            Taisancodinh.setMa_tscd(cell.getStringCellValue());
                            break;
                        case 1:
                            Taisancodinh.setTen_tscd(cell.getStringCellValue());
                            break;
                        case 2:
                            Taisancodinh.setLoai_tscd(cell.getStringCellValue());
                            break;
                        case 3:
                            Taisancodinh.setDon_vi_su_dung(cell.getStringCellValue());
                            break;
                        case 4:
                            Taisancodinh.setNgay_ghi_tang(cell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                            break;
                        case 5:
                            Taisancodinh.setSo_ct_ghi_tang(cell.getStringCellValue());
                            break;
                        case 6:
                            Taisancodinh.setNgay_bat_dau_tinh_kh(
                                cell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                            );
                            break;
                        case 7:
                            Taisancodinh.setThoi_gian_su_dung(cell.getNumericCellValue());
                            break;
                        case 8:
                            Taisancodinh.setThoi_gian_su_dung_con_lai(cell.getNumericCellValue());
                            break;
                        case 9:
                            Taisancodinh.setNguyen_gia(cell.getNumericCellValue());
                            break;
                        case 10:
                            Taisancodinh.setGia_tri_tinh_kh(cell.getNumericCellValue());
                            break;
                        case 11:
                            Taisancodinh.setHao_mon_trong_ky(cell.getNumericCellValue());
                            break;
                        case 12:
                            Taisancodinh.setHao_mon_luy_ke(cell.getNumericCellValue());
                            break;
                        case 13:
                            Taisancodinh.setGia_tri_con_lai(cell.getNumericCellValue());
                            break;
                        case 14:
                            Taisancodinh.setGia_tri_KH_thang(cell.getNumericCellValue());
                            break;
                        case 15:
                            Taisancodinh.setTk_nguyen_gia(cell.getStringCellValue());
                            break;
                        case 16:
                            Taisancodinh.setTk_khau_hao(cell.getStringCellValue());
                            break;
                    }
                    cellIndex++;
                }
                list.add(Taisancodinh);
            }
            return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ByteArrayInputStream tscdToExcel(List<Taisancodinh> Taisancodinhs) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet("TSCD");
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
            for (int col = 0; col < header_TSCD.size(); col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(header_TSCD.get(col));
                cell.setCellStyle(cellStyleHeader);
                sheet.setColumnWidth(col, 20 * 250);
            }
            CellStyle cellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));
            CellStyle cellStyleNumber = workbook.createCellStyle();
            cellStyleNumber.setDataFormat(createHelper.createDataFormat().getFormat("#,##0.00"));
            int rowIdx = 1;
            for (Taisancodinh Taisancodinh : Taisancodinhs) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue("Sổ tài chính");
                row.createCell(1).setCellValue(Taisancodinh.getMa_tscd());
                row.createCell(2).setCellValue(Taisancodinh.getTen_tscd());
                row.createCell(3).setCellValue("");
                row.createCell(4).setCellValue(Taisancodinh.getLoai_tscd());
                Cell cell = row.createCell(5);
                cell.setCellValue(Taisancodinh.getNgay_ghi_tang());
                cell.setCellStyle(cellStyle);
                cell = row.createCell(6);
                cell.setCellValue(Taisancodinh.getNgay_bat_dau_tinh_kh());
                cell.setCellStyle(cellStyle);
                row.createCell(7).setCellValue(Taisancodinh.getThoi_gian_su_dung());
                row.createCell(8).setCellValue("tháng");
                row.createCell(9).setCellValue(Taisancodinh.getThoi_gian_su_dung_con_lai());
                row.createCell(10).setCellValue("tháng");
                cell = row.createCell(11);
                cell.setCellValue(Taisancodinh.getNguyen_gia());
                cell.setCellStyle(cellStyleNumber);
                cell = row.createCell(12);
                cell.setCellValue(Taisancodinh.getGia_tri_tinh_kh());
                cell.setCellStyle(cellStyleNumber);
                cell = row.createCell(13);
                cell.setCellValue(Taisancodinh.getHao_mon_luy_ke());
                cell.setCellStyle(cellStyleNumber);
                cell = row.createCell(14);
                cell.setCellValue(Taisancodinh.getGia_tri_con_lai());
                cell.setCellStyle(cellStyleNumber);
                cell = row.createCell(15);
                cell.setCellValue(Taisancodinh.getGia_tri_KH_thang());
                cell.setCellStyle(cellStyleNumber);
                row.createCell(16).setCellValue(Taisancodinh.getTk_nguyen_gia());
                row.createCell(17).setCellValue("");
                row.createCell(18).setCellValue(Taisancodinh.getTk_khau_hao());
                row.createCell(19).setCellValue("");
                row.createCell(20).setCellValue("");
                row.createCell(21).setCellValue("");
                row.createCell(22).setCellValue("");
                row.createCell(23).setCellValue("");
                row.createCell(24).setCellValue("");
                row.createCell(25).setCellValue("");
                row.createCell(26).setCellValue("");
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }
}
