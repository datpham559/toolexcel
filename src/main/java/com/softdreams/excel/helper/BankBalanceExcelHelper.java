package com.softdreams.excel.helper;

import com.softdreams.excel.domain.BankBalance;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class BankBalanceExcelHelper {
    public static String TYPE1 = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static String TYPE2 = "application/vnd.ms-excel";
    static String[] HEADERs = {"Mã khách hàng", "Tên khách hàng", "Nhóm KH, NCC", "Mã số thuế", "Điện thoại", "Ngừng theo dõi"};

    public static boolean hasExcelFormat(MultipartFile file) {

        if (TYPE1.equals(file.getContentType()) || TYPE2.equals(file.getContentType())) {
            return true;
        }
        return false;
    }

    public static List<BankBalance> excelToBankBalance(InputStream is) {
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
            List<BankBalance> bankBalances = new ArrayList<>();


            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber < 3) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                BankBalance bankBalance = new BankBalance();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            bankBalance.setBank_account(currentCell.getStringCellValue().trim());
                            break;

                        case 1:
                            bankBalance.setBank_name(currentCell.getStringCellValue());
                            break;

                        case 2:
                            bankBalance.setBranch(currentCell.getStringCellValue());
                            break;

                        case 3:
                            bankBalance.setOpening_balance(currentCell.getNumericCellValue());
                            break;
                        case 4:
                            bankBalance.setDebt_incurred(currentCell.getNumericCellValue());
                            break;
                        case 5:
                            bankBalance.setIncurred(currentCell.getNumericCellValue());
                            break;
                        case 6:
                            bankBalance.setEnding_balance(currentCell.getNumericCellValue());
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }

                bankBalances.add(bankBalance);
            }

            workbook.close();

            return bankBalances;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
