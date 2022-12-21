package com.softdreams.excel.helper;

import com.softdreams.excel.domain.Congcudungcu;
import java.io.IOException;
import java.io.InputStream;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.*;

public class CongcudungcuHelper {

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
}
