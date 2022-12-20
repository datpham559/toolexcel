package com.softdreams.excel.helper;

import com.softdreams.excel.domain.Synthetic;
import com.softdreams.excel.service.dto.SyntheticDTO;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


public class ReceiptsExcelHelper {
    public static String TYPE1 = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static String TYPE2 = "application/vnd.ms-excel";
    static String[] HEADERs = {"Vào sổ", "Ghi sổ", "Số chứng từ", "Ngày chứng từ", "Ngày hạch toán",
    "Loại tiền", "Tỷ giá", "Loại đối tượng", "Mã đối tượng", "Tên đối tượng", "Địa chỉ", "Mã số thuế", "Người nộp",
    "Lý do nộp", "Mã nhân viên", "Kèm theo", "Diễn giải HT", "TK Nợ", "TK Có", "Số tiền", "Số tiền QĐ", "Đối tượng HT",
    "TK ngân hàng", "Khoản mục CP", "Mục thu/chi", "Phòng ban", "Đối tượng THCP", "Mã thống kê", "Hợp đồng"};

    static String SHEET = "SHEET1";
    public static boolean hasExcelFormat(MultipartFile file) {

        if (TYPE1.equals(file.getContentType()) || TYPE2.equals(file.getContentType())) {
            return true;
        }
        return false;
    }


    public static ByteArrayInputStream receiptsToExcel(List<Synthetic> synthetics) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);

            Integer exchangeRate = 1;

            // Header
            Row headerRow = sheet.createRow(0);
            headerRow.setHeight((short) 400);
            CellStyle cellStyleHeader = workbook.createCellStyle();
            Font fontHeader = workbook.createFont();
//            fontHeader.setBold(true);
            fontHeader.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
            cellStyleHeader.setFont(fontHeader);
            cellStyleHeader.setAlignment(HorizontalAlignment.CENTER);
            cellStyleHeader.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyleHeader.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
            cellStyleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cellStyleHeader.setBorderBottom(BorderStyle.THIN);
            cellStyleHeader.setBorderTop(BorderStyle.THIN);
            cellStyleHeader.setBorderRight(BorderStyle.THIN);
            cellStyleHeader.setBorderLeft(BorderStyle.THIN);

            CellStyle cellStyleHeader2 = workbook.createCellStyle();
//            fontHeader.setBold(true);
            fontHeader.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
            cellStyleHeader2.setFont(fontHeader);
            cellStyleHeader2.setAlignment(HorizontalAlignment.CENTER);
            cellStyleHeader2.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyleHeader2.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
            cellStyleHeader2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cellStyleHeader2.setBorderBottom(BorderStyle.THIN);
            cellStyleHeader2.setBorderTop(BorderStyle.THIN);
            cellStyleHeader2.setBorderRight(BorderStyle.THIN);
            cellStyleHeader2.setBorderLeft(BorderStyle.THIN);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
                if (col < 17){
                    cell.setCellStyle(cellStyleHeader);
                }else {
                    cell.setCellStyle(cellStyleHeader2);
                }
                sheet.setColumnWidth(col, 20 * 400);
            }
            CellStyle cellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            cellStyle.setDataFormat(
                createHelper.createDataFormat().getFormat("dd/MM/yyyy"));

            CellStyle cellStyleNumber = workbook.createCellStyle();
            cellStyleNumber.setDataFormat(createHelper.createDataFormat().getFormat("#,##0.00"));

            int rowIdx = 1;
            StringBuilder temp = new StringBuilder(synthetics.get(0).getVoucherNo());
            boolean check = false;
            for (Synthetic synthetic : synthetics) {
                Row row = sheet.createRow(rowIdx++);
                if (!check){
                    row.createCell(0).setCellValue("Sổ tài chính");
                    row.createCell(1).setCellValue("Có");//ghi sổ
                    row.createCell(2).setCellValue(synthetic.getVoucherNo());

                    Cell cell4 = row.createCell(3);
                    cell4.setCellValue(synthetic.getVoucherDate());
                    cell4.setCellStyle(cellStyle);

                    Cell cell5 = row.createCell(4);
                    cell5.setCellValue(synthetic.getAccountingDate());
                    cell5.setCellStyle(cellStyle);

                    row.createCell(5).setCellValue(synthetic.getCurrencyType());

                    if (synthetic.getCurrencyType().equals("VND")) {
                        row.createCell(6).setCellValue(exchangeRate);
                    }

                    row.createCell(7).setCellValue("");//loại đối tượng
                    row.createCell(8).setCellValue(synthetic.getCreditObject());
                    row.createCell(9).setCellValue("");//tên đối tượng
                    row.createCell(10).setCellValue("");//địa chỉ
                    row.createCell(11).setCellValue("");// mã số thuế
                    row.createCell(12).setCellValue("");//ng nộp
                    row.createCell(13).setCellValue("");//lý do nộp
                    row.createCell(14).setCellValue(synthetic.getEmployee());
                    row.createCell(15).setCellValue("");//kèm theo
                    row.createCell(16).setCellValue("");//diễn giải HT
                    row.createCell(17).setCellValue(synthetic.getDebitAccount());
                    row.createCell(18).setCellValue(synthetic.getCreditAccount());

                    Cell cell19 = row.createCell(19);
                    cell19.setCellValue(synthetic.getCurrency().doubleValue());
                    cell19.setCellStyle(cellStyleNumber);

                    row.createCell(20).setCellValue(synthetic.getMoneyTranfer().doubleValue());//số tiền quy đổi
                    row.createCell(21).setCellValue(synthetic.getCreditObject());//đối tượng HT
                    row.createCell(22).setCellValue(synthetic.getBankAccount());// Tk ngân hàng
                    row.createCell(23).setCellValue("");//khoản mục CP
                    row.createCell(24).setCellValue("");//mục thu/chi
                    row.createCell(25).setCellValue("");//phòng ban
                    row.createCell(26).setCellValue("");// đối tượng THCP
                    row.createCell(27).setCellValue("");// mã thống kê
                    row.createCell(28).setCellValue("");//hợp đồng

                    check = true;
                    continue;
                }
                if (temp.toString().trim().equals(synthetic.getVoucherNo())){
                    row.createCell(0).setCellValue("");
                    row.createCell(1).setCellValue("");//ghi sổ
                    row.createCell(2).setCellValue("");//số chứng từ
                    row.createCell(3).setCellValue("");//ngày chứng từ
                    row.createCell(4).setCellValue("");
                    row.createCell(5).setCellValue("");
                    row.createCell(6).setCellValue("");//tỷ giá
                    row.createCell(7).setCellValue("");
                    row.createCell(8).setCellValue("");
                    row.createCell(9).setCellValue("");//tên đối tượng
                    row.createCell(10).setCellValue("");//địa chỉ
                    row.createCell(11).setCellValue("");// mã số thuế
                    row.createCell(12).setCellValue("");//ng nộp
                    row.createCell(13).setCellValue("");//lý do nộp
                    row.createCell(14).setCellValue("");//mã nhân viên
                    row.createCell(15).setCellValue("");//kèm theo
                    row.createCell(16).setCellValue("");//diễn giải HT
                    row.createCell(17).setCellValue(synthetic.getDebitAccount());
                    row.createCell(18).setCellValue(synthetic.getCreditAccount());
                    row.createCell(19).setCellValue(synthetic.getCurrency().doubleValue());
                    row.createCell(20).setCellValue(synthetic.getMoneyTranfer().doubleValue());//số tiền quy đổi
                    row.createCell(21).setCellValue(synthetic.getCreditObject());//đối tượng HT
                    row.createCell(22).setCellValue(synthetic.getBankAccount());// Tk ngân hàng
                    row.createCell(23).setCellValue("");//khoản mục CP
                    row.createCell(24).setCellValue("");//mục thu/chi
                    row.createCell(25).setCellValue("");//phòng ban
                    row.createCell(26).setCellValue("");// đối tượng THCP
                    row.createCell(27).setCellValue("");// mã thống kê
                    row.createCell(28).setCellValue("");//hợp đồng

                }else {
                    temp.delete(0,temp.length());
                    temp.append(synthetic.getVoucherNo());
                    row.createCell(0).setCellValue("Sổ tài chính");
                    row.createCell(1).setCellValue("Có");//ghi sổ
                    row.createCell(2).setCellValue(synthetic.getVoucherNo());

                    Cell cell4 = row.createCell(3);
                    cell4.setCellValue(synthetic.getVoucherDate());
                    cell4.setCellStyle(cellStyle);

                    Cell cell5 = row.createCell(4);
                    cell5.setCellValue(synthetic.getAccountingDate());
                    cell5.setCellStyle(cellStyle);

                    row.createCell(5).setCellValue(synthetic.getCurrencyType());

                    if (synthetic.getCurrencyType().equals("VND")) {
                        row.createCell(6).setCellValue(exchangeRate);
                    }

                    row.createCell(7).setCellValue("");//loại đối tượng
                    row.createCell(8).setCellValue(synthetic.getCreditObject());
                    row.createCell(9).setCellValue("");//tên đối tượng
                    row.createCell(10).setCellValue("");//địa chỉ
                    row.createCell(11).setCellValue("");// mã số thuế
                    row.createCell(12).setCellValue("");//ng nộp
                    row.createCell(13).setCellValue("");//lý do nộp
                    row.createCell(14).setCellValue(synthetic.getEmployee());
                    row.createCell(15).setCellValue("");//kèm theo
                    row.createCell(16).setCellValue("");//diễn giải HT
                    row.createCell(17).setCellValue(synthetic.getDebitAccount());
                    row.createCell(18).setCellValue(synthetic.getCreditAccount());

                    Cell cell19 = row.createCell(19);
                    cell19.setCellValue(synthetic.getCurrency().doubleValue());
                    cell19.setCellStyle(cellStyleNumber);

                    row.createCell(20).setCellValue(synthetic.getMoneyTranfer().doubleValue());//số tiền quy đổi
                    row.createCell(21).setCellValue(synthetic.getCreditObject());//đối tượng HT
                    row.createCell(22).setCellValue(synthetic.getBankAccount());// Tk ngân hàng
                    row.createCell(23).setCellValue("");//khoản mục CP
                    row.createCell(24).setCellValue("");//mục thu/chi
                    row.createCell(25).setCellValue("");//phòng ban
                    row.createCell(26).setCellValue("");// đối tượng THCP
                    row.createCell(27).setCellValue("");// mã thống kê
                    row.createCell(28).setCellValue("");//hợp đồng
                }

            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }

    }
}
