package com.softdreams.excel.helper;

import com.softdreams.excel.domain.Congnophaithu;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.*;

public class CongnophaithuHelper {

    public static List<Congnophaithu> excelToCong_no_phai_thu(InputStream inputStream) {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            if (sheet.getRow(sheet.getLastRowNum()).getCell(0).getStringCellValue().contains("Số dòng")) {
                sheet.removeRow(sheet.getRow(sheet.getLastRowNum()));
            }
            Iterator<Row> rows = sheet.iterator();
            List<Congnophaithu> list = new ArrayList<>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row current = rows.next();
                if (rowNumber < 4) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellIterator = current.cellIterator();
                Congnophaithu Congnophaithu = new Congnophaithu();
                int cellIndex = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0:
                            Congnophaithu.setMakh(cell.getStringCellValue());
                            break;
                        case 1:
                            Congnophaithu.setTenkh(cell.getStringCellValue());
                            break;
                        case 2:
                            Congnophaithu.setTkcongno(cell.getStringCellValue());
                            break;
                        case 3:
                            Congnophaithu.setSodunodauky(cell.getNumericCellValue());
                            break;
                        case 4:
                            Congnophaithu.setSoducodauky(cell.getNumericCellValue());
                            break;
                        case 5:
                            Congnophaithu.setSonophatsinh(cell.getNumericCellValue());
                            break;
                        case 6:
                            Congnophaithu.setSocophatsinh(cell.getNumericCellValue());
                            break;
                        case 7:
                            Congnophaithu.setSodunocuoiky(cell.getNumericCellValue());
                            break;
                        case 8:
                            Congnophaithu.setSoducocuoiky(cell.getNumericCellValue());
                            break;
                    }
                    cellIndex++;
                }
                list.add(Congnophaithu);
            }
            return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
