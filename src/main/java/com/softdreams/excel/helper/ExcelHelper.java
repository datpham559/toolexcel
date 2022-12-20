package com.softdreams.excel.helper;

import com.softdreams.excel.domain.Customer;
import com.softdreams.excel.domain.Synthetic;
import com.softdreams.excel.service.dto.SyntheticDTO;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static com.softdreams.excel.helper.ScaleConfig.scales;

public class ExcelHelper {

    public static String TYPE1 = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static String TYPE2 = "application/vnd.ms-excel";
    static String[] HEADERs_KHACH_HANG = {"Mã khách hàng", "Tên khách hàng", "Địa chỉ", "Nhóm KH, NCC", "Mã số thuế", "Quy mô", "Loại đối tượng", "Điện thoại", "Ngừng theo dõi",};
    static String[] HEADERs_BAO_NO = {"Loại báo nợ", "Vào sổ", "Ghi sổ", "Là chi phí mua hàng", "Số chứng từ", "Ngày chứng từ", "Ngày hạch toán", "Loại tiền", "Tỷ giá", "Tài khoản trả", "Nội dung TT", "Diễn giải HT", "TK Nợ", "TK Có", "Số tiền", "Số tiền QĐ", "Đối tượng HT", "Khoản mục CP", "Mục thu/chi", "Phòng ban", "Đối tượng THCP", "Mã thống kê", "Hợp đồng", "Diễn giải HT thuế", "TK thuế GTGT", "Thuế suất", "Tiền thuế GTGT", "Giá tính thuế", "Đối tượng HT thuế", "Mã số thuế ĐT hạch toán thuế", "Mẫu số hóa đơn", "Ký hiệu hóa đơn", "Số HĐ", "Ngày hóa đơn", "Nhóm HHDV mua vào",};
    static String SHEET_KHACH_HANG = "Khach_hang";
    static String SHEET_BAO_NO = "Bao_no";

    public static boolean hasExcelFormat(MultipartFile file) {
        if (TYPE1.equals(file.getContentType()) || TYPE2.equals(file.getContentType())) {
            return true;
        }
        return false;
    }

    public static List<Customer> excelToCustomers(InputStream is) {
        try {
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);

            if (sheet.getRow(sheet.getLastRowNum()).getCell(0).getStringCellValue().contains("Số dòng")) {
                sheet.removeRow(sheet.getRow(sheet.getLastRowNum()));
            }

            Iterator<Row> rows = sheet.iterator();
            List<Customer> customers = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0 || rowNumber == 1) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Customer customer = new Customer();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            customer.setCustomerCode(currentCell.getStringCellValue());
                            break;
                        case 1:
                            customer.setCustomerName(currentCell.getStringCellValue());
                            break;
                        case 2:
                            customer.setAddress(currentCell.getStringCellValue());
                            break;
                        case 3:
                            customer.setCustomerGroup(currentCell.getStringCellValue());
                            break;
                        case 4:
                            customer.setTax(currentCell.getStringCellValue());
                            break;
                        case 5:
                            customer.setPhoneNumber(currentCell.getStringCellValue());
                            break;
                        case 6:
                            customer.setUnfollow(currentCell.getBooleanCellValue());
                            break;
                        default:
                            break;
                    }

                    cellIdx++;
                }
                customers.add(customer);
            }
            workbook.close();
            return customers;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<Synthetic> excelToSynthetic(InputStream is) {
        try {
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);
            if (sheet.getRow(sheet.getLastRowNum()).getCell(0).getCellType().name().equals("STRING")) {
                if (sheet.getRow(sheet.getLastRowNum()).getCell(0).getStringCellValue().contains("Số dòng")) {
                    sheet.removeRow(sheet.getRow(sheet.getLastRowNum()));
                }
            }

            Iterator<Row> rows = sheet.iterator();
            List<Synthetic> synthetics = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();

                Synthetic synthetic = new Synthetic();

                Cell cell1 = currentRow.getCell(1);
                if (cell1.getStringCellValue().equals("Chứng từ nghiệp vụ khác")) {
                    synthetic.setVoucherTypeNo(1);
                } else if (cell1.getStringCellValue().equals("Bán hàng hóa, dịch vụ trong nước chưa thu tiền")) {
                    synthetic.setVoucherTypeNo(2);
                } else if (cell1.getStringCellValue().equals("Mua hàng trong nước nhập kho chưa thanh toán")) {
                    synthetic.setVoucherTypeNo(3);
                } else if (cell1.getStringCellValue().equals("Bảng tính khấu hao tài sản cố định")) {
                    synthetic.setVoucherTypeNo(4);
                } else if (cell1.getStringCellValue().equals("Chứng từ mua dịch vụ chưa thanh toán")) {
                    synthetic.setVoucherTypeNo(5);
                } else if (cell1.getStringCellValue().equals("Kết chuyển lãi,lỗ")) {
                    synthetic.setVoucherTypeNo(6);
                } else if (cell1.getStringCellValue().equals("Phiếu chi")) {
                    synthetic.setVoucherTypeNo(7);
                } else if (cell1.getStringCellValue().equals("Phiếu thu")) {
                    synthetic.setVoucherTypeNo(8);
                } else if (cell1.getStringCellValue().equals("Thu tiền gửi")) {
                    synthetic.setVoucherTypeNo(9);
                } else if (cell1.getStringCellValue().equals("Nhận hóa đơn hàng hóa")) {
                    synthetic.setVoucherTypeNo(10);
                } else if (cell1.getStringCellValue().equals("Xuất kho bán hàng")) {
                    synthetic.setVoucherTypeNo(11);
                } else if (cell1.getStringCellValue().equals("Phân bổ chi phí CCDC")) {
                    synthetic.setVoucherTypeNo(12);
                } else if (cell1.getStringCellValue().equals("Ủy nhiệm chi")) {
                    synthetic.setVoucherTypeNo(13);
                }

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 1:
                            synthetic.setVoucherType(currentCell.getStringCellValue());
                            break;
                        case 2:
                            synthetic.setVoucherNo(currentCell.getStringCellValue());
                            break;
                        case 3:
                            synthetic.setVoucherDate(convertToLocalDate(currentCell.getDateCellValue()));
                            break;
                        case 4:
                            synthetic.setAccountingDate(convertToLocalDate(currentCell.getDateCellValue()));
                            break;
                        case 5:
                            synthetic.setInvoiceNo(currentCell.getStringCellValue());
                            break;
                        case 6:
                            synthetic.setInvoiceDate(convertToLocalDate(currentCell.getDateCellValue()));
                            break;
                        case 7:
                            synthetic.setDebitAccount(currentCell.getStringCellValue());
                            break;
                        case 8:
                            synthetic.setCreditAccount(currentCell.getStringCellValue());
                            break;
                        case 9:
                            synthetic.setCurrencyType(currentCell.getStringCellValue());
                            break;
                        case 10:
                            synthetic.setCurrency((BigDecimal.valueOf(currentCell.getNumericCellValue())));
                            break;
                        case 11:
                            synthetic.setMaterialGoodCode(currentCell.getStringCellValue());
                            break;
                        case 12:
                            synthetic.setMaterialGoodName(currentCell.getStringCellValue());
                            break;
                        case 13:
                            synthetic.setStorageIn(currentCell.getStringCellValue());
                            break;
                        case 14:
                            synthetic.setStorageOut(currentCell.getStringCellValue());
                            break;
                        case 15:
                            synthetic.setCaculationUnit(currentCell.getStringCellValue());
                            break;
                        case 16:
                            synthetic.setAmount((float) currentCell.getNumericCellValue());
                            break;
                        case 17:
                            synthetic.setPrice((BigDecimal.valueOf(currentCell.getNumericCellValue())));
                            break;
                        case 18:
                            synthetic.setTranferRate((BigDecimal.valueOf(currentCell.getNumericCellValue())));
                            break;
                        case 19:
                            synthetic.setMoneyTranfer((BigDecimal.valueOf(currentCell.getNumericCellValue())));
                            break;
                        case 20:
                            synthetic.setFixedAssetsType(currentCell.getStringCellValue());
                            break;
                        case 21:
                            synthetic.setFixedAssetsCode(currentCell.getStringCellValue());
                            break;
                        case 22:
                            synthetic.setToolsCode(currentCell.getStringCellValue());
                            break;
                        case 23:
                            synthetic.setDebitObject(currentCell.getStringCellValue());
                            break;
                        case 24:
                            synthetic.setCreditObject(currentCell.getStringCellValue());
                            break;
                        case 25:
                            synthetic.setUnit(currentCell.getStringCellValue());
                            break;
                        case 26:
                            synthetic.setEmployee(currentCell.getStringCellValue());
                            break;
                        case 27:
                            synthetic.setBankAccount(currentCell.getStringCellValue());
                            break;
                        case 28:
                            synthetic.setItemCost(currentCell.getStringCellValue());
                            break;
                        case 29:
                            synthetic.setConstruction(currentCell.getStringCellValue());
                            break;
                        case 30:
                            synthetic.setCostSet(currentCell.getStringCellValue());
                            break;
                        case 31:
                            synthetic.setPurchaseOrder(currentCell.getStringCellValue());
                            break;
                        case 32:
                            synthetic.setBuyOrder(currentCell.getStringCellValue());
                            break;
                        case 33:
                            synthetic.setPurchaseContract(currentCell.getStringCellValue());
                            break;
                        case 34:
                            synthetic.setSaleContract(currentCell.getStringCellValue());
                            break;
                        case 35:
                            synthetic.setStatsCode(currentCell.getStringCellValue());
                            break;
                        case 36:
                            synthetic.setExplanation(currentCell.getStringCellValue());
                            break;
                        case 37:
                            synthetic.setExplanationDetail(currentCell.getStringCellValue());
                            break;
                        case 38:
                            synthetic.setRecordStatus(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }

                    cellIdx++;
                }
                synthetics.add(synthetic);
            }
            workbook.close();
            return synthetics;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static LocalDate convertToLocalDate(Date dateToConvert) {
        if (dateToConvert == null) {
            return null;
        }
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static ByteArrayInputStream customersToExcel(List<Customer> customers) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET_KHACH_HANG);

            // Header
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

            for (int col = 0; col < HEADERs_KHACH_HANG.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs_KHACH_HANG[col]);
                cell.setCellStyle(cellStyleHeader);
                if (col == 1 || col == 2) {
                    sheet.setColumnWidth(1, 25 * 700);
                    sheet.setColumnWidth(2, 25 * 1000);
                } else {
                    sheet.setColumnWidth(col, 20 * 250);
                }
            }

            int rowIdx = 1;
            for (Customer customer : customers) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(customer.getCustomerCode());
                row.createCell(1).setCellValue(customer.getCustomerName());
                row.createCell(2).setCellValue(customer.getAddress());
                row.createCell(3).setCellValue(customer.getCustomerGroup());
                row.createCell(4).setCellValue(customer.getTax());
                boolean checkContainScale = false;
                for (String scale : scales) {
                    if (customer.getCustomerName().toLowerCase().contains(scale.toLowerCase())) {
                        checkContainScale = true;
                        break;
                    }
                }
                if (checkContainScale) {
                    row.createCell(5).setCellValue("Tổ chức");
                } else {
                    row.createCell(5).setCellValue("Cá nhân");
                }
                row.createCell(6).setCellValue("Khách hàng");
                row.createCell(7).setCellValue(customer.getPhoneNumber());
                if (customer.getUnfollow() == null) {
                    row.createCell(8).setCellValue("");
                } else {
                    row.createCell(8).setCellValue(customer.getUnfollow());
                }
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream debitNoteToExcel(List<SyntheticDTO> synthetics) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET_BAO_NO);

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

            for (int col = 0; col < HEADERs_BAO_NO.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs_BAO_NO[col]);
                sheet.setColumnWidth(col, 20 * 250);
                if (col <= 11) {
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
                if (col >= 12 && col <= 24) {
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
                if (col > 24 && col <= HEADERs_BAO_NO.length) {
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

            }

            int rowIdx = 1;
            StringBuilder term = new StringBuilder();
            term.append(synthetics.get(0).getVoucherNo());
            for (SyntheticDTO synthetic : synthetics) {

                BigDecimal moneyChange = synthetic.getCurrency().multiply(BigDecimal.valueOf(exchangeRate));
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
                        if (synthetic.getCurrencyType().equals("VND")) {
                            row.createCell(8).setCellValue("");
                        }
                        row.createCell(9).setCellValue("");
                        row.createCell(10).setCellValue("");
                        row.createCell(11).setCellValue("");
                        row.createCell(12).setCellValue(synthetic.getDebitAccount());
                        row.createCell(13).setCellValue(synthetic.getCreditAccount());
                        row.createCell(14).setCellValue(synthetic.getCurrency().doubleValue());
                        row.createCell(15).setCellValue(moneyChange.doubleValue());
                        row.createCell(16).setCellValue(synthetic.getCreditObject());
                        row.createCell(17).setCellValue(synthetic.getItemCost());
                        row.createCell(18).setCellValue("");
                        row.createCell(19).setCellValue(synthetic.getUnit());
                        row.createCell(20).setCellValue(synthetic.getCostSet());
                        row.createCell(21).setCellValue(synthetic.getStatsCode());
                        row.createCell(22).setCellValue(synthetic.getSaleContract());
                        row.createCell(23).setCellValue("");
                        row.createCell(24).setCellValue("");
                        if (synthetic.getTaxPercent() !=null){
                            row.createCell(25).setCellValue(synthetic.getTaxPercent());
                        }else{
                            row.createCell(25).setCellValue("");
                        }
                        if (synthetic.getCurrencyTax() !=null){
                            row.createCell(26).setCellValue(synthetic.getCurrencyTax().doubleValue());
                        }else{
                            row.createCell(26).setCellValue("");
                        }
                        row.createCell(27).setCellValue("");
                        row.createCell(28).setCellValue("");
                        row.createCell(29).setCellValue("");
                        row.createCell(30).setCellValue("");
                        row.createCell(31).setCellValue("");
                        row.createCell(32).setCellValue("");
                        row.createCell(33).setCellValue("");
                        row.createCell(34).setCellValue("");
                        row.createCell(35).setCellValue("");
                    } else {

                        term.delete(0, term.length());
                        term.append(synthetic.getVoucherNo());
                        row.createCell(0).setCellValue("Ủy nhiệm chi");
                        row.createCell(1).setCellValue("Sổ tài chính");
                        row.createCell(2).setCellValue("Có");
                        row.createCell(3).setCellValue("Là chi phí mua hàng");
                        row.createCell(4).setCellValue(synthetic.getVoucherNo());
                        row.createCell(5).setCellValue(synthetic.getVoucherDate());
                        row.getCell(5).setCellStyle(cellBody);
                        row.createCell(6).setCellValue(synthetic.getAccountingDate());
                        row.getCell(6).setCellStyle(cellBody);
                        row.createCell(7).setCellValue(synthetic.getCurrencyType());
                        if (synthetic.getCurrencyType().equals("VND")) {
                            row.createCell(8).setCellValue(exchangeRate);
                        }
                        row.createCell(9).setCellValue(synthetic.getBankAccount());
                        row.createCell(10).setCellValue(synthetic.getExplanation());
                        row.createCell(11).setCellValue("");
                        row.createCell(12).setCellValue(synthetic.getDebitAccount());
                        row.createCell(13).setCellValue(synthetic.getCreditAccount());
                        row.createCell(14).setCellValue(synthetic.getCurrency().doubleValue());
                        row.createCell(15).setCellValue(moneyChange.doubleValue());
                        row.createCell(16).setCellValue(synthetic.getCreditObject());
                        row.createCell(17).setCellValue(synthetic.getItemCost());
                        row.createCell(18).setCellValue("");
                        row.createCell(19).setCellValue(synthetic.getUnit());
                        row.createCell(20).setCellValue(synthetic.getCostSet());
                        row.createCell(21).setCellValue(synthetic.getStatsCode());
                        row.createCell(22).setCellValue(synthetic.getSaleContract());
                        row.createCell(23).setCellValue("");
                        row.createCell(24).setCellValue("");
                        if (synthetic.getTaxPercent() !=null){
                            row.createCell(25).setCellValue(synthetic.getTaxPercent());
                        }else{
                            row.createCell(25).setCellValue("");
                        }
                        if (synthetic.getCurrencyTax() !=null){
                            row.createCell(26).setCellValue(synthetic.getCurrencyTax().doubleValue());
                        }else{
                            row.createCell(26).setCellValue("");
                        }
                        row.createCell(27).setCellValue("");
                        row.createCell(28).setCellValue("");
                        row.createCell(29).setCellValue("");
                        row.createCell(30).setCellValue("");
                        row.createCell(31).setCellValue("");
                        row.createCell(32).setCellValue("");
                        row.createCell(33).setCellValue("");
                        row.createCell(34).setCellValue("");
                        row.createCell(35).setCellValue("");
                    }
                } else {
                    row.createCell(0).setCellValue("Ủy nhiệm chi");
                    row.createCell(1).setCellValue("Sổ tài chính");
                    row.createCell(2).setCellValue("Có");
                    row.createCell(3).setCellValue("Là chi phí mua hàng");
                    row.createCell(4).setCellValue(synthetic.getVoucherNo());
                    row.createCell(5).setCellValue(synthetic.getVoucherDate());
                    row.getCell(5).setCellStyle(cellBody);
                    row.createCell(6).setCellValue(synthetic.getAccountingDate());
                    row.getCell(6).setCellStyle(cellBody);
                    row.createCell(7).setCellValue(synthetic.getCurrencyType());
                    if (synthetic.getCurrencyType().equals("VND")) {
                        row.createCell(8).setCellValue(exchangeRate);
                    }

                    row.createCell(9).setCellValue(synthetic.getBankAccount());
                    row.createCell(10).setCellValue(synthetic.getExplanation());
                    row.createCell(11).setCellValue("");
                    row.createCell(12).setCellValue(synthetic.getDebitAccount());
                    row.createCell(13).setCellValue(synthetic.getCreditAccount());
                    row.createCell(14).setCellValue(synthetic.getCurrency().doubleValue());
                    row.createCell(15).setCellValue(moneyChange.doubleValue());
                    row.createCell(16).setCellValue(synthetic.getCreditObject());
                    row.createCell(17).setCellValue(synthetic.getItemCost());
                    row.createCell(18).setCellValue("");
                    row.createCell(19).setCellValue(synthetic.getUnit());
                    row.createCell(20).setCellValue(synthetic.getCostSet());
                    row.createCell(21).setCellValue(synthetic.getStatsCode());
                    row.createCell(22).setCellValue(synthetic.getSaleContract());
                    row.createCell(23).setCellValue("");
                    row.createCell(24).setCellValue("");
                    if (synthetic.getTaxPercent() !=null){
                        row.createCell(25).setCellValue(synthetic.getTaxPercent());
                    }else{
                        row.createCell(25).setCellValue("");
                    }
                    if (synthetic.getCurrencyTax() !=null){
                        row.createCell(26).setCellValue(synthetic.getCurrencyTax().doubleValue());
                    }else{
                        row.createCell(26).setCellValue("");
                    }
                    row.createCell(27).setCellValue("");
                    row.createCell(28).setCellValue("");
                    row.createCell(29).setCellValue("");
                    row.createCell(30).setCellValue("");
                    row.createCell(31).setCellValue("");
                    row.createCell(32).setCellValue("");
                    row.createCell(33).setCellValue("");
                    row.createCell(34).setCellValue("");
                    row.createCell(35).setCellValue("");
                }
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }
}
