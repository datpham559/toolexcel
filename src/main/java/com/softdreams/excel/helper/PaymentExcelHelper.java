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
import java.io.InputStream;
import java.util.*;


public class PaymentExcelHelper {
    public static String TYPE1 = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static String TYPE2 = "application/vnd.ms-excel";
    static String[] HEADERs = {"Vào sổ", "Ghi sổ", "Là phí mua hàng", "Số chứng từ", "Ngày chứng từ", "Ngày hạch toán",
    "Loại tiền", "Tỷ giá", "Loại đối tượng", "Mã đối tượng", "Tên đối tượng", "Địa chỉ", "Mã số thuế", "Người nhận",
    "Lý do nộp", "Mã nhân viên", "Kèm theo", "Diễn giải HT", "TK Nợ", "TK Có", "Số tiền", "Số tiền QĐ", "Đối tượng HT",
    "TK ngân hàng", "Khoản mục CP", "Mục thu/chi", "Phòng ban", "Đối tượng THCP", "Mã thống kê", "Hợp đồng", "Diễn giải HT thuế",
    "TK thuế GTGT", "Thuế suất", "Tiền thuế GTGT", "Giá tính thuế", "Đối tượng HT thuế", "Mã số thuế ĐT hạch toán thuế", "Mẫu số hóa đơn", "Ký hiệu hóa đơn",
    "Số HĐ", "Ngày hóa đơn", "Nhóm HHDV mua vào"};

    static String SHEET = "SHEET1";
    public static boolean hasExcelFormat(MultipartFile file) {

        if (TYPE1.equals(file.getContentType()) || TYPE2.equals(file.getContentType())) {
            return true;
        }
        return false;
    }


//    public static List<Merchandise> excelToMerchandise(InputStream is) {
//        try {
//            Workbook workbook = WorkbookFactory.create(is);
//            Sheet sheet = workbook.getSheetAt(0);
//
//            if (sheet.getRow(sheet.getLastRowNum()).getCell(0).getStringCellValue().contains("Số dòng")) {
//                sheet.removeRow(sheet.getRow(sheet.getLastRowNum()));
//            }
////            Row rowDelete = sheet.getRow(sheet.getLastRowNum());
////
////            sheet.removeRow(rowDelete);
//
//            Iterator<Row> rows = sheet.iterator();
//            List<Merchandise> dmMerchandises = new ArrayList<>();
//
//
//            int rowNumber = 0;
//            while (rows.hasNext()) {
//                Row currentRow = rows.next();
//
//                // skip header
//                if (rowNumber == 0 || rowNumber == 1) {
//                    rowNumber++;
//                    continue;
//                }
//
//                Iterator<Cell> cellsInRow = currentRow.iterator();
//
//                Merchandise merchandise = new Merchandise();
//
//                int cellIdx = 0;
//                while (cellsInRow.hasNext()) {
//                    Cell currentCell = cellsInRow.next();
//
//                    switch (cellIdx) {
//                        case 0:
//                            merchandise.setCode(currentCell.getStringCellValue());
//                            break;
//
//                        case 1:
//                            merchandise.setName(currentCell.getStringCellValue());
//                            break;
//
//                        case 2:
//                            merchandise.setNature(currentCell.getStringCellValue());
//                            break;
//
//                        case 3:
//                            merchandise.setGroup_vthh(currentCell.getStringCellValue());
//                            break;
//                        case 4:
//                            merchandise.setDescribe(currentCell.getStringCellValue());
//                            break;
//                        case 5:
//                            merchandise.setExplain_buy(currentCell.getStringCellValue());
//                            break;
//                        case 6:
//                            merchandise.setExplain_sell(currentCell.getStringCellValue());
//                            break;
//                        case 7:
//                            merchandise.setMain_dvt(currentCell.getStringCellValue());
//                            break;
//                        case 8:
//                            merchandise.setWarranty_period(currentCell.getStringCellValue());
//                            break;
//                        case 9:
//                            merchandise.setQuantity_inventory(currentCell.getNumericCellValue());
//                            break;
//                        case 10:
//                            merchandise.setSource(currentCell.getStringCellValue());
//                            break;
//                        case 11:
//                            merchandise.setImplicitly_repository(currentCell.getStringCellValue());
//                            break;
//                        case 12:
//                            merchandise.setWarehouse_account(currentCell.getStringCellValue());
//                            break;
//                        case 13:
//                            merchandise.setExpense_account(currentCell.getStringCellValue());
//                            break;
//                        case 14:
//                            merchandise.setIncome_account(currentCell.getStringCellValue());
//                            break;
//                        case 15:
//                            merchandise.setDiscount_account(currentCell.getStringCellValue());
//                            break;
//                        case 16:
//                            merchandise.setSale_account(currentCell.getStringCellValue());
//                            break;
//                        case 17:
//                            merchandise.setReturn_account(currentCell.getStringCellValue());
//                            break;
//                        case 18:
//                            merchandise.setRate_ckmh(currentCell.getNumericCellValue());
//                            break;
//                        case 19:
//                            merchandise.setFixed_purchase_price(currentCell.getNumericCellValue());
//                            break;
//                        case 20:
//                            merchandise.setLatest_purchase_price(currentCell.getNumericCellValue());
//                            break;
//                        case 21:
//                            merchandise.setUnit_price_sell_1(currentCell.getNumericCellValue());
//                            break;
//                        case 22:
//                            merchandise.setUnit_price_sell_2(currentCell.getNumericCellValue());
//                            break;
//                        case 23:
//                            merchandise.setUnit_price_sell_3(currentCell.getNumericCellValue());
//                            break;
//                        case 24:
//                            merchandise.setFixed_unit_price(currentCell.getNumericCellValue());
//                            break;
//                        case 25:
//                            merchandise.setUnit_price_after_tax(currentCell.getNumericCellValue());
//                            break;
//                        case 26:
//                            merchandise.setTax_rate_gtgt(currentCell.getStringCellValue());
//                            break;
//                        case 27:
//                            merchandise.setTax_rate_nk(currentCell.getNumericCellValue());
//                            break;
//                        case 28:
//                            merchandise.setTax_rate_xk(currentCell.getNumericCellValue());
//                            break;
//                        case 29:
//                            merchandise.setGroup_hhdv_taxable_ttdb(currentCell.getStringCellValue());
//                            break;
//                        case 30:
//                            merchandise.setUnfollow(currentCell.getNumericCellValue());
//                            break;
//                        case 31:
//                            merchandise.setInventory_number(currentCell.getNumericCellValue());
//                            break;
//                        case 32:
//                            merchandise.setCharacteristic(currentCell.getStringCellValue());
//                            break;
//                        case 33:
//                            merchandise.setInventory_value(currentCell.getNumericCellValue());
//                            break;
//                        case 34:
//                            merchandise.setFollow(currentCell.getNumericCellValue());
//                            break;
//                        case 35:
//                            merchandise.setDiscount(currentCell.getNumericCellValue());
//                            break;
//                        case 36:
//                            merchandise.setFrom_amount(currentCell.getStringCellValue());
//                            break;
//                        case 37:
//                            merchandise.setTo_amount(currentCell.getStringCellValue());
//                            break;
//                        case 38:
//                            merchandise.setPercent_discount(currentCell.getNumericCellValue());
//                            break;
//                        case 39:
//                            merchandise.setDiscount_amount(currentCell.getNumericCellValue());
//                            break;
//                        case 40:
//                            merchandise.setConversion_unit(currentCell.getStringCellValue());
//                            break;
//                        case 41:
//                            merchandise.setPrimary_unit_conversion_rate(currentCell.getNumericCellValue());
//                            break;
//                        case 42:
//                            merchandise.setCalculation(currentCell.getStringCellValue());
//                            break;
//                        case 43:
//                            merchandise.setDescribe1(currentCell.getStringCellValue());
//                            break;
//                        case 44:
//                            merchandise.setUnit_price_1(currentCell.getNumericCellValue());
//                            break;
//                        case 45:
//                            merchandise.setUnit_price_2(currentCell.getNumericCellValue());
//                            break;
//                        case 46:
//                            merchandise.setUnit_price_3(currentCell.getNumericCellValue());
//                            break;
//                        case 47:
//                            merchandise.setFixed_unit_price1(currentCell.getNumericCellValue());
//                            break;
//                        case 48:
//                            merchandise.setMaterial_code(currentCell.getStringCellValue());
//                            break;
//                        case 49:
//                            merchandise.setMaterial_name(currentCell.getStringCellValue());
//                            break;
//                        case 50:
//                            merchandise.setDvt(currentCell.getStringCellValue());
//                            break;
//                        case 51:
//                            merchandise.setAmount(currentCell.getStringCellValue());
//                            break;
//                        case 52:
//                            merchandise.setSpecification_code(currentCell.getStringCellValue());
//                            break;
//                        case 53:
//                            merchandise.setDisplay_name(currentCell.getStringCellValue());
//                            break;
//                        case 54:
//                            merchandise.setAllow_same(currentCell.getStringCellValue());
//                            break;
//
//                        default:
//                            break;
//                    }
//
//                    cellIdx++;
//                }
//
//                dmMerchandises.add(merchandise);
//            }
//
//            workbook.close();
//
//            return dmMerchandises;
//        } catch (IOException e) {
//            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
//        }
//    }

    public static ByteArrayInputStream paymentToExcel(List<SyntheticDTO> synthetics) {
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

            CellStyle cellStyleHeader3 = workbook.createCellStyle();
//            fontHeader.setBold(true);
            fontHeader.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
            cellStyleHeader3.setFont(fontHeader);
            cellStyleHeader3.setAlignment(HorizontalAlignment.CENTER);
            cellStyleHeader3.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyleHeader3.setFillForegroundColor(IndexedColors.DARK_GREEN.getIndex());
            cellStyleHeader3.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cellStyleHeader3.setBorderBottom(BorderStyle.THIN);
            cellStyleHeader3.setBorderTop(BorderStyle.THIN);
            cellStyleHeader3.setBorderRight(BorderStyle.THIN);
            cellStyleHeader3.setBorderLeft(BorderStyle.THIN);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
                if (col < 17){
                    cell.setCellStyle(cellStyleHeader);
                }else if (col > 16 && col < 30){
                    cell.setCellStyle(cellStyleHeader2);
                }else {
                    cell.setCellStyle(cellStyleHeader3);
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
            for (SyntheticDTO synthetic : synthetics) {
                Row row = sheet.createRow(rowIdx++);
                if (!check){
                    row.createCell(0).setCellValue("Sổ tài chính");
                    row.createCell(1).setCellValue("Có");//ghi sổ
                    row.createCell(2).setCellValue("Có");//là chi phí mua hàng
                    row.createCell(3).setCellValue(synthetic.getVoucherNo());
                    Cell cell4 = row.createCell(4);
                    cell4.setCellValue(synthetic.getVoucherDate());
                    cell4.setCellStyle(cellStyle);

                    Cell cell5 = row.createCell(5);
                    cell5.setCellValue(synthetic.getAccountingDate());
                    cell5.setCellStyle(cellStyle);

                    row.createCell(6).setCellValue(synthetic.getCurrencyType());
                    if (synthetic.getCurrencyType().equals("VND")) {
                        row.createCell(7).setCellValue(exchangeRate);
                    }

                    row.createCell(8).setCellValue("");//loại đối tượng
                    row.createCell(9).setCellValue(synthetic.getCreditObject());
                    row.createCell(10).setCellValue("");//tên đối tượng
                    row.createCell(11).setCellValue("");//địa chỉ
                    row.createCell(12).setCellValue("");// mã số thuế
                    row.createCell(13).setCellValue("");//ng nhận
                    row.createCell(14).setCellValue("");//lý do nộp
                    row.createCell(15).setCellValue(synthetic.getEmployee());
                    row.createCell(16).setCellValue("");//kèm theo
                    row.createCell(17).setCellValue("");//diễn giải HT
                    row.createCell(18).setCellValue(synthetic.getDebitAccount());
                    row.createCell(19).setCellValue(synthetic.getCreditAccount());

                    Cell cell20 = row.createCell(20);
                    cell20.setCellValue(synthetic.getCurrency().doubleValue());
                    cell20.setCellStyle(cellStyleNumber);

                    row.createCell(21).setCellValue(synthetic.getMoneyTranfer().doubleValue());
                    row.createCell(22).setCellValue(synthetic.getCreditObject());//đối tượng HT
                    row.createCell(23).setCellValue(synthetic.getBankAccount());// Tk ngân hàng
                    row.createCell(24).setCellValue("");//khoản mục CP
                    row.createCell(25).setCellValue("");//mục thu/chi
                    row.createCell(26).setCellValue("");//phòng ban
                    row.createCell(27).setCellValue("");// đối tượng THCP
                    row.createCell(28).setCellValue("");// mã thống kê
                    row.createCell(29).setCellValue("");//hợp đồng
                    row.createCell(30).setCellValue("");////diễn giải HT thuế
                    row.createCell(31).setCellValue("");//TK thuế GTGT
                    if (synthetic.getTaxPercent() !=null){
                        row.createCell(32).setCellValue(synthetic.getTaxPercent());//thuế suất
                    }else{
                        row.createCell(32).setCellValue("");
                    }

                    if (synthetic.getCurrencyTax() !=null){
                        row.createCell(33).setCellValue(synthetic.getCurrencyTax().doubleValue());//tiền thuế GTGT
                    }else{
                        row.createCell(33).setCellValue("");
                    }
                    row.createCell(34).setCellValue("");//giá tính thuế
                    row.createCell(35).setCellValue("");//đối tượng HT thuế
                    row.createCell(36).setCellValue("");//Mã số thuế ĐT hạch toán thuế
                    row.createCell(37).setCellValue("");//mẫu số hóa đơn
                    row.createCell(38).setCellValue("");//ký hiệu hóa đơn
                    row.createCell(39).setCellValue("");//số HĐ
                    row.createCell(40).setCellValue(synthetic.getInvoiceDate());
                    row.createCell(41).setCellValue("");//Nhóm HHDV mua vào
                    check = true;
                    continue;
                }
                if (temp.toString().trim().equals(synthetic.getVoucherNo())){
                    row.createCell(0).setCellValue("");
                    row.createCell(1).setCellValue("");//ghi sổ
                    row.createCell(2).setCellValue("");//là chi phí mua hàng
                    row.createCell(3).setCellValue("");
                    row.createCell(4).setCellValue("");
                    row.createCell(5).setCellValue("");
                    row.createCell(6).setCellValue("");
                    row.createCell(7).setCellValue("");//tỷ giá
                    row.createCell(8).setCellValue("");
                    row.createCell(9).setCellValue("");
                    row.createCell(10).setCellValue("");//tên đối tượng
                    row.createCell(11).setCellValue("");//địa chỉ
                    row.createCell(12).setCellValue("");// mã số thuế
                    row.createCell(13).setCellValue("");//ng nhận
                    row.createCell(14).setCellValue("");//lý do nộp
                    row.createCell(15).setCellValue("");
                    row.createCell(16).setCellValue("");//kèm theo
                    row.createCell(17).setCellValue("");//diễn giải HT
                    row.createCell(18).setCellValue(synthetic.getDebitAccount());
                    row.createCell(19).setCellValue(synthetic.getCreditAccount());
                    Cell cell20 = row.createCell(20);
                    cell20.setCellValue(synthetic.getCurrency().doubleValue());
                    cell20.setCellStyle(cellStyleNumber);
                    row.createCell(21).setCellValue(synthetic.getMoneyTranfer().doubleValue());//số tiền quy đổi
                    row.createCell(22).setCellValue(synthetic.getCreditObject());//đối tượng HT
                    row.createCell(23).setCellValue(synthetic.getBankAccount());// Tk ngân hàng
                    row.createCell(24).setCellValue("");//khoản mục CP
                    row.createCell(25).setCellValue("");//mục thu/chi
                    row.createCell(26).setCellValue("");//phòng ban
                    row.createCell(27).setCellValue("");// đối tượng THCP
                    row.createCell(28).setCellValue("");// mã thống kê
                    row.createCell(29).setCellValue("");//hợp đồng
                    row.createCell(30).setCellValue("");////diễn giải HT thuế
                    row.createCell(31).setCellValue("");//TK thuế GTGT
                    row.createCell(32).setCellValue("");//thuế suất
                    row.createCell(33).setCellValue("");//tiền thuế GTGT
                    row.createCell(34).setCellValue("");//giá tính thuế
                    row.createCell(35).setCellValue("");//đối tượng HT thuế
                    row.createCell(36).setCellValue("");//Mã số thuế ĐT hạch toán thuế
                    row.createCell(37).setCellValue("");//mẫu số hóa đơn
                    row.createCell(38).setCellValue("");//ký hiệu hóa đơn
                    row.createCell(39).setCellValue("");//số HĐ
                    row.createCell(40).setCellValue("");
                    row.createCell(41).setCellValue("");//Nhóm HHDV mua vào
                }else {
                    temp.delete(0,temp.length());
                    temp.append(synthetic.getVoucherNo());
                    row.createCell(0).setCellValue("Sổ tài chính");
                    row.createCell(1).setCellValue("Có");//ghi sổ
                    row.createCell(2).setCellValue("Có");//là chi phí mua hàng
                    row.createCell(3).setCellValue(synthetic.getVoucherNo());
                    Cell cell4 = row.createCell(4);
                    cell4.setCellValue(synthetic.getVoucherDate());
                    cell4.setCellStyle(cellStyle);

                    Cell cell5 = row.createCell(5);
                    cell5.setCellValue(synthetic.getAccountingDate());
                    cell5.setCellStyle(cellStyle);
                    row.createCell(6).setCellValue(synthetic.getCurrencyType());
                    if (synthetic.getCurrencyType().equals("VND")) {
                        row.createCell(7).setCellValue(exchangeRate);//tỷ giá
                    }
                    row.createCell(8).setCellValue("");
                    row.createCell(9).setCellValue(synthetic.getCreditObject());
                    row.createCell(10).setCellValue("");//tên đối tượng
                    row.createCell(11).setCellValue("");//địa chỉ
                    row.createCell(12).setCellValue("");// mã số thuế
                    row.createCell(13).setCellValue("");//ng nhận
                    row.createCell(14).setCellValue("");//lý do nộp
                    row.createCell(15).setCellValue(synthetic.getEmployee());
                    row.createCell(16).setCellValue("");//kèm theo
                    row.createCell(17).setCellValue("");//diễn giải HT
                    row.createCell(18).setCellValue(synthetic.getDebitAccount());
                    row.createCell(19).setCellValue(synthetic.getCreditAccount());
                    Cell cell20 = row.createCell(20);
                    cell20.setCellValue(synthetic.getCurrency().doubleValue());
                    cell20.setCellStyle(cellStyleNumber);
                    row.createCell(21).setCellValue(synthetic.getMoneyTranfer().doubleValue());//số tiền quy đổi
                    row.createCell(22).setCellValue(synthetic.getCreditObject());//đối tượng HT
                    row.createCell(23).setCellValue(synthetic.getBankAccount());// Tk ngân hàng
                    row.createCell(24).setCellValue("");//khoản mục CP
                    row.createCell(25).setCellValue("");//mục thu/chi
                    row.createCell(26).setCellValue("");//phòng ban
                    row.createCell(27).setCellValue("");// đối tượng THCP
                    row.createCell(28).setCellValue("");// mã thống kê
                    row.createCell(29).setCellValue("");//hợp đồng
                    row.createCell(30).setCellValue("");////diễn giải HT thuế
                    row.createCell(31).setCellValue("");//TK thuế GTGT

                    if (synthetic.getTaxPercent() !=null){
                        row.createCell(32).setCellValue(synthetic.getTaxPercent());//thuế suất
                    }else{
                        row.createCell(32).setCellValue("");
                    }

                    if (synthetic.getCurrencyTax() !=null){
                        row.createCell(33).setCellValue(synthetic.getCurrencyTax().doubleValue());//tiền thuế GTGT
                    }else{
                        row.createCell(33).setCellValue("");
                    }
//                    row.createCell(32).setCellValue("");//thuế suất
//                    row.createCell(33).setCellValue("");//tiền thuế GTGT
                    row.createCell(34).setCellValue("");//giá tính thuế
                    row.createCell(35).setCellValue("");//đối tượng HT thuế
                    row.createCell(36).setCellValue("");//Mã số thuế ĐT hạch toán thuế
                    row.createCell(37).setCellValue("");//mẫu số hóa đơn
                    row.createCell(38).setCellValue("");//ký hiệu hóa đơn
                    row.createCell(39).setCellValue("");//số HĐ
                    row.createCell(40).setCellValue(synthetic.getInvoiceDate());
                    row.createCell(41).setCellValue("");//Nhóm HHDV mua vào
                }

            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }

    }
}
