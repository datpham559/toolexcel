package com.softdreams.excel.helper;

import com.softdreams.excel.domain.Tonkhodauky;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.*;

public class TonkhodaukyHelper {

    public static List<Tonkhodauky> excelToTonkhodauky(InputStream inputStream) {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            if (sheet.getRow(sheet.getLastRowNum()).getCell(1).getStringCellValue().contains("Số dòng")) {
                sheet.removeRow(sheet.getRow(sheet.getLastRowNum()));
            }
            Iterator<Row> rows = sheet.iterator();
            List<Tonkhodauky> list = new ArrayList<>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row current = rows.next();
                if (rowNumber < 4) {
                    rowNumber++;
                    continue;
                }
                if (current.getFirstCellNum() == 0 && current.getCell(0).getStringCellValue().contains("Tên kho")) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellIterator = current.cellIterator();
                Tonkhodauky Tonkhodauky = new Tonkhodauky();
                int cellIndex = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0:
                            Tonkhodauky.setTen_kho(cell.getStringCellValue());
                            break;
                        case 1:
                            Tonkhodauky.setMa_hang(cell.getStringCellValue());
                            break;
                        case 2:
                            Tonkhodauky.setTen_hang(cell.getStringCellValue());
                            break;
                        case 3:
                            Tonkhodauky.setDvt(cell.getStringCellValue());
                            break;
                        case 4:
                            Tonkhodauky.setDau_ky_so_luong(cell.getNumericCellValue());
                            break;
                        case 5:
                            Tonkhodauky.setDau_ky_gia_tri(cell.getNumericCellValue());
                            break;
                        case 6:
                            Tonkhodauky.setNhap_kho_so_luong(cell.getNumericCellValue());
                            break;
                        case 7:
                            Tonkhodauky.setNhap_kho_gia_tri(cell.getNumericCellValue());
                            break;
                        case 8:
                            Tonkhodauky.setXuat_kho_so_luong(cell.getNumericCellValue());
                            break;
                        case 9:
                            Tonkhodauky.setXuat_kho_gia_tri(cell.getNumericCellValue());
                            break;
                        case 10:
                            Tonkhodauky.setCuoi_ky_so_luong(cell.getNumericCellValue());
                            break;
                        case 11:
                            Tonkhodauky.setCuoi_ky_gia_tri(cell.getNumericCellValue());
                            break;
                        case 12:
                            Tonkhodauky.setDon_gia_binh_quan(cell.getNumericCellValue());
                            break;
                    }
                    cellIndex++;
                }
                list.add(Tonkhodauky);
            }
            return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
