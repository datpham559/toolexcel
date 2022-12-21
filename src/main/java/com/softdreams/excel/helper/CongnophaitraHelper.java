package com.softdreams.excel.helper;

import com.softdreams.excel.domain.Congnophaitra;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.*;

public class CongnophaitraHelper {

    public static List<Congnophaitra> excelToCong_no_phai_tra(InputStream inputStream) {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            if (sheet.getRow(sheet.getLastRowNum()).getCell(0).getStringCellValue().contains("Số dòng")) {
                sheet.removeRow(sheet.getRow(sheet.getLastRowNum()));
            }
            Iterator<Row> rows = sheet.iterator();
            List<Congnophaitra> list = new ArrayList<>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row current = rows.next();
                if (rowNumber < 4) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellIterator = current.cellIterator();
                Congnophaitra Congnophaitra = new Congnophaitra();
                int cellIndex = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0:
                            Congnophaitra.setMa_ncc(cell.getStringCellValue());
                            break;
                        case 1:
                            Congnophaitra.setTen_ncc(cell.getStringCellValue());
                            break;
                        case 2:
                            Congnophaitra.setTk_congno(cell.getStringCellValue());
                            break;
                        case 3:
                            Congnophaitra.setSo_du_no_dau_ky(cell.getNumericCellValue());
                            break;
                        case 4:
                            Congnophaitra.setSo_du_co_dau_ky(cell.getNumericCellValue());
                            break;
                        case 5:
                            Congnophaitra.setPhat_sinh_no(cell.getNumericCellValue());
                            break;
                        case 6:
                            Congnophaitra.setPhat_sinh_co(cell.getNumericCellValue());
                            break;
                        case 7:
                            Congnophaitra.setSo_du_no_cuoi_ky(cell.getNumericCellValue());
                            break;
                        case 8:
                            Congnophaitra.setSo_du_co_cuoi_ky(cell.getNumericCellValue());
                            break;
                    }
                    cellIndex++;
                }
                list.add(Congnophaitra);
            }
            return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
