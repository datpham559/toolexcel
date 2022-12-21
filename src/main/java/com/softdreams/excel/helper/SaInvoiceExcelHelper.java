package com.softdreams.excel.helper;

import com.softdreams.excel.service.dto.SyntheticDTO;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static com.softdreams.excel.helper.SheetConfig.*;

public class SaInvoiceExcelHelper {

    public static ByteArrayInputStream saInvoiceToExcel(List<SyntheticDTO> synthetics)  {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET_CHUNG_TU_BAN_HANG);

            Integer exchangeRate = 1;

            // Header
            Row headerRow = sheet.createRow(0);
            headerRow.setHeight((short) 400);
            CellStyle cellStyleHeader = workbook.createCellStyle();

            CellStyle cellBody = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            cellBody.setDataFormat(createHelper.createDataFormat().getFormat("DD/MM/YYYY"));

            Font fontHeader = workbook.createFont();
            fontHeader.setBold(true);

            for (int col = 0; col < HEADERs_BAN_HANG.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs_BAN_HANG[col]);
                sheet.setColumnWidth(col, 20 * 250);
                if (col <= 31) {
                    CellStyle light_blue = workbook.createCellStyle();
                    light_blue.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
                    light_blue.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    light_blue.setFont(fontHeader);
                    light_blue.setAlignment(HorizontalAlignment.CENTER);
                    light_blue.setVerticalAlignment(VerticalAlignment.CENTER);
                    light_blue.setBorderBottom(BorderStyle.THIN);
                    light_blue.setBorderTop(BorderStyle.THIN);
                    light_blue.setBorderRight(BorderStyle.THIN);
                    light_blue.setBorderLeft(BorderStyle.THIN);
                    cell.setCellStyle(light_blue);
                }
                if (col >= 32 && col <= 37) {
                    CellStyle green = workbook.createCellStyle();

                    green.setFillForegroundColor(IndexedColors.GREEN.getIndex());
                    green.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    green.setFont(fontHeader);
                    green.setAlignment(HorizontalAlignment.CENTER);
                    green.setVerticalAlignment(VerticalAlignment.CENTER);
                    green.setBorderBottom(BorderStyle.THIN);
                    green.setBorderTop(BorderStyle.THIN);
                    green.setBorderRight(BorderStyle.THIN);
                    green.setBorderLeft(BorderStyle.THIN);
                    cell.setCellStyle(green);
                }
                if (col > 37 && col <= 56) {
                    CellStyle orange = workbook.createCellStyle();

                    orange.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
                    orange.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    orange.setFont(fontHeader);
                    orange.setAlignment(HorizontalAlignment.CENTER);
                    orange.setVerticalAlignment(VerticalAlignment.CENTER);
                    orange.setBorderBottom(BorderStyle.THIN);
                    orange.setBorderTop(BorderStyle.THIN);
                    orange.setBorderRight(BorderStyle.THIN);
                    orange.setBorderLeft(BorderStyle.THIN);
                    cell.setCellStyle(orange);
                }
                if (col > 56 && col <= 72) {
                    CellStyle green = workbook.createCellStyle();

                    green.setFillForegroundColor(IndexedColors.BROWN.getIndex());
                    green.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    green.setFont(fontHeader);
                    green.setAlignment(HorizontalAlignment.CENTER);
                    green.setVerticalAlignment(VerticalAlignment.CENTER);
                    green.setBorderBottom(BorderStyle.THIN);
                    green.setBorderTop(BorderStyle.THIN);
                    green.setBorderRight(BorderStyle.THIN);
                    green.setBorderLeft(BorderStyle.THIN);
                    cell.setCellStyle(green);
                }
                if (col > 72 && col <= HEADERs_BAN_HANG.length) {
                    CellStyle green = workbook.createCellStyle();

                    green.setFillForegroundColor(IndexedColors.LAVENDER.getIndex());
                    green.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    green.setFont(fontHeader);
                    green.setAlignment(HorizontalAlignment.CENTER);
                    green.setVerticalAlignment(VerticalAlignment.CENTER);
                    green.setBorderBottom(BorderStyle.THIN);
                    green.setBorderTop(BorderStyle.THIN);
                    green.setBorderRight(BorderStyle.THIN);
                    green.setBorderLeft(BorderStyle.THIN);
                    cell.setCellStyle(green);
                }
            }
            int rowIdx = 1;
            StringBuilder term = new StringBuilder();
            if (synthetics.size() >0 ) {
                term.append(synthetics.get(0).getVoucherNo());
            }
            for (SyntheticDTO synthetic : synthetics) {
                Row row = sheet.createRow(rowIdx++);
                if (synthetic != synthetics.get(0)) {
                    if (term.toString().equals(synthetic.getVoucherNo())) {
                        row.createCell(0).setCellValue("");
                        row.createCell(1).setCellValue("");
                        row.createCell(2).setCellValue("");
                        row.createCell(3).setCellValue("");
                        row.createCell(4).setCellValue("");
                        row.createCell(5).setCellValue("");
                        row.createCell(6).setCellValue("");
                        row.createCell(7).setCellValue("");
                        row.createCell(8).setCellValue("");
                        row.createCell(9).setCellValue("");
                        row.createCell(10).setCellValue("");
                        row.createCell(11).setCellValue("");
                        row.createCell(12).setCellValue("");
                        row.createCell(13).setCellValue(""); // tỷ giá
                        row.createCell(14).setCellValue(synthetic.getCreditObject()); // mã đối tượng
                        row.createCell(15).setCellValue("");
                        row.createCell(16).setCellValue("");
                        row.createCell(17).setCellValue("");
                        row.createCell(18).setCellValue("");//mst
                        row.createCell(19).setCellValue("");
                        row.createCell(20).setCellValue(synthetic.getExplanation());
                        row.createCell(21).setCellValue("");
                        row.createCell(22).setCellValue("");
                        row.createCell(23).setCellValue("");
                        row.createCell(24).setCellValue("");//kèm theo
                        row.createCell(25).setCellValue("");
                        row.createCell(26).setCellValue("");
                        row.createCell(27).setCellValue("");
                        row.createCell(28).setCellValue("");
                        row.createCell(29).setCellValue("");
                        row.createCell(30).setCellValue("");// mặt hàng chung
                        row.createCell(31).setCellValue("");
                        row.createCell(32).setCellValue("");//ký hiệu hđ
                        row.createCell(33).setCellValue(synthetic.getInvoiceNo());
                        row.createCell(34).setCellValue(synthetic.getInvoiceDate());
                        row.getCell(34).setCellStyle(cellBody);
                        row.createCell(35).setCellValue("Chưa thanh toán");
                        row.createCell(36).setCellValue("Hóa đơn mới tạo lập");
                        row.createCell(37).setCellValue(synthetic.getMaterialGoodCode());
                        row.createCell(38).setCellValue(synthetic.getMaterialGoodName());
                        row.createCell(39).setCellValue("Là hàng KM");
                        row.createCell(40).setCellValue(synthetic.getStorageOut());//Kho
                        row.createCell(41).setCellValue(synthetic.getDebitAccount());
                        row.createCell(42).setCellValue(synthetic.getCreditAccount());
                        row.createCell(43).setCellValue("");
                        row.createCell(44).setCellValue(synthetic.getCaculationUnit());
                        row.createCell(45).setCellValue(synthetic.getAmount());
                        row.createCell(46).setCellValue(synthetic.getPrice().doubleValue());
                        row.createCell(47).setCellValue(synthetic.getPrice().multiply(BigDecimal.valueOf(exchangeRate)).doubleValue());//đơn giá quy đổi
                        row.createCell(48).setCellValue(synthetic.getCurrency().doubleValue());
                        row.createCell(49).setCellValue(synthetic.getCurrency().multiply(BigDecimal.valueOf(exchangeRate)).doubleValue());
                        row.createCell(50).setCellValue("");
                        row.createCell(51).setCellValue("");
                        row.createCell(52).setCellValue("");
                        row.createCell(53).setCellValue("");
                        row.createCell(54).setCellValue("");
                        row.createCell(55).setCellValue("");
                        row.createCell(56).setCellValue("");
                        row.createCell(57).setCellValue("");
                        row.createCell(58).setCellValue("");
                        row.createCell(59).setCellValue("");
                        row.createCell(60).setCellValue("");
                        if(synthetic.getTaxPercent() == null){
                            row.createCell(61).setCellValue("");
                        } else if (synthetic.getTaxPercent() == 8 ||synthetic.getTaxPercent() ==10 ||synthetic.getTaxPercent() ==5){
                            row.createCell(61).setCellValue(synthetic.getTaxPercent()+"%");
                        }else{
                            row.createCell(61).setCellValue(10+"%");
                        }
                        if (synthetic.getCurrencyTax() !=null){
                            row.createCell(62).setCellValue(synthetic.getCurrencyTax().doubleValue());
                        }else {
                            row.createCell(62).setCellValue("");
                        }
                        row.createCell(63).setCellValue("");//thuế quy đổi
                        row.createCell(64).setCellValue("");
                        row.createCell(65).setCellValue("");
                        row.createCell(66).setCellValue("");
                        row.createCell(67).setCellValue("");
                        row.createCell(68).setCellValue("");
                        row.createCell(69).setCellValue("");
                        row.createCell(70).setCellValue("");
                        row.createCell(71).setCellValue("");
                        row.createCell(72).setCellValue("");
                        row.createCell(73).setCellValue("");
                        row.createCell(74).setCellValue("");
                        row.createCell(75).setCellValue("");
                        row.createCell(76).setCellValue("");
                        row.createCell(77).setCellValue("");
                    } else {
                        term.delete(0, term.length());
                        term.append(synthetic.getVoucherNo());
                        createRowSaInvoice(row,synthetic,cellBody);
                    }
                } else {
                    createRowSaInvoice(row,synthetic,cellBody);
                }
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }

    public static void createRowSaInvoice(Row row,SyntheticDTO synthetic,CellStyle cellBody){
        Integer exchangeRate = 1;
        row.createCell(0).setCellValue("Sổ tài chính");
        row.createCell(1).setCellValue("Có");
        row.createCell(2).setCellValue("Chưa thu tiền");
        row.createCell(3).setCellValue("Trong nước");
        row.createCell(4).setCellValue("");
        row.createCell(5).setCellValue("Có");
        row.createCell(6).setCellValue("Không");
        row.createCell(7).setCellValue(synthetic.getVoucherNo());
        row.createCell(8).setCellValue(synthetic.getVoucherNoXK());
        row.createCell(9).setCellValue("");
        row.createCell(10).setCellValue(synthetic.getVoucherDate());
        row.getCell(10).setCellStyle(cellBody);
        row.createCell(11).setCellValue(synthetic.getInvoiceDate());
        row.getCell(11).setCellStyle(cellBody);
        row.createCell(12).setCellValue(synthetic.getCurrencyType());
        if (synthetic.getCurrencyType().equals("VND")){
            row.createCell(13).setCellValue(exchangeRate);
        }else {
            row.createCell(13).setCellValue("");
        }
        row.createCell(14).setCellValue(synthetic.getCreditObject()); // mã đối tượng
        row.createCell(15).setCellValue("");
        row.createCell(16).setCellValue("");
        row.createCell(17).setCellValue("");
        row.createCell(18).setCellValue("");//mst
        row.createCell(19).setCellValue("");
        row.createCell(20).setCellValue(synthetic.getExplanation());
        row.createCell(21).setCellValue("");
        row.createCell(22).setCellValue("");
        row.createCell(23).setCellValue("");
        row.createCell(24).setCellValue("");//kèm theo
        row.createCell(25).setCellValue("");
        row.createCell(26).setCellValue("");
        row.createCell(27).setCellValue("");
        row.createCell(28).setCellValue("");
        row.createCell(29).setCellValue("");
        row.createCell(30).setCellValue("");// mặt hàng chung
        row.createCell(31).setCellValue("");
        row.createCell(32).setCellValue("");//ký hiệu hđ
        row.createCell(33).setCellValue(synthetic.getInvoiceNo());
        row.createCell(34).setCellValue(synthetic.getInvoiceDate());
        row.getCell(34).setCellStyle(cellBody);
        row.createCell(35).setCellValue("Chưa thanh toán");
        row.createCell(36).setCellValue("Hóa đơn mới tạo lập");
        row.createCell(37).setCellValue(synthetic.getMaterialGoodCode());
        row.createCell(38).setCellValue(synthetic.getMaterialGoodName());
        row.createCell(39).setCellValue("Là hàng KM");
        row.createCell(40).setCellValue(synthetic.getStorageOut());//Kho
        row.createCell(41).setCellValue(synthetic.getDebitAccount());
        row.createCell(42).setCellValue(synthetic.getCreditAccount());
        row.createCell(43).setCellValue("");
        row.createCell(44).setCellValue(synthetic.getCaculationUnit());
        row.createCell(45).setCellValue(synthetic.getAmount());
        row.createCell(46).setCellValue(synthetic.getPrice().doubleValue());
        row.createCell(47).setCellValue(synthetic.getPrice().multiply(BigDecimal.valueOf(exchangeRate)).doubleValue());//đơn giá quy đổi
        row.createCell(48).setCellValue(synthetic.getCurrency().doubleValue());
        row.createCell(49).setCellValue(synthetic.getCurrency().multiply(BigDecimal.valueOf(exchangeRate)).doubleValue());//thành tiền quy đổi
        row.createCell(50).setCellValue("");
        row.createCell(51).setCellValue("");
        row.createCell(52).setCellValue("");
        row.createCell(53).setCellValue("");
        row.createCell(54).setCellValue("");
        row.createCell(55).setCellValue("");
        row.createCell(56).setCellValue("");
        row.createCell(57).setCellValue("");
        row.createCell(58).setCellValue("");
        if (synthetic.getCurrencyXK() !=null) {
            row.createCell(59).setCellValue(synthetic.getCurrencyXK().doubleValue());
        }else {
            row.createCell(59).setCellValue("");
        }
        row.createCell(60).setCellValue("");
        if(synthetic.getTaxPercent() == null){
            row.createCell(61).setCellValue("");
        } else if (synthetic.getTaxPercent() == 8 ||synthetic.getTaxPercent() ==10 ||synthetic.getTaxPercent() ==5){
            row.createCell(61).setCellValue(synthetic.getTaxPercent()+"%");
        }else{
            row.createCell(61).setCellValue(10+"%");
        }
        if (synthetic.getCurrencyTax() !=null) {
            row.createCell(62).setCellValue(synthetic.getCurrencyTax().doubleValue());
            row.createCell(63).setCellValue(synthetic.getCurrencyTax().multiply(BigDecimal.valueOf(exchangeRate)).doubleValue());//thuế quy đổi
        }else {
            row.createCell(62).setCellValue("");
            row.createCell(63).setCellValue("");//thuế quy đổi
        }
        row.createCell(64).setCellValue("");
        row.createCell(65).setCellValue("");
        if (synthetic.getCreditAccountXK() !=null) {
            row.createCell(66).setCellValue(synthetic.getCreditAccountXK());
        }else {
            row.createCell(66).setCellValue("");

        }
        if (synthetic.getDebitAccountXK() !=null) {
            row.createCell(67).setCellValue(synthetic.getDebitAccountXK());
        }else {
            row.createCell(67).setCellValue("");
        }
        if (synthetic.getPriceXK() !=null) {
            row.createCell(68).setCellValue(synthetic.getPriceXK().doubleValue());
        }else {
            row.createCell(68).setCellValue("");
        }
        if (synthetic.getCurrencyXK() !=null) {
            row.createCell(69).setCellValue(synthetic.getCurrencyXK().doubleValue());
        }else {
            row.createCell(69).setCellValue("");
        }
        row.createCell(70).setCellValue("");
        row.createCell(71).setCellValue("");
        row.createCell(72).setCellValue("");
        row.createCell(73).setCellValue("");
        row.createCell(74).setCellValue("");
        row.createCell(75).setCellValue("");
        row.createCell(76).setCellValue("");
        row.createCell(77).setCellValue("");
    }
}
