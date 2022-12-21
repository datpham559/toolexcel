package com.softdreams.excel.helper;

import com.softdreams.excel.domain.Taisancodinh;
import java.io.IOException;
import java.io.InputStream;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.*;

public class TaisancodinhHelper {

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
}
