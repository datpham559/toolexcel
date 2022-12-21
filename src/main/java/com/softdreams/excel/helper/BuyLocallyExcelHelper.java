package com.softdreams.excel.helper;

import com.softdreams.excel.service.dto.SyntheticDTO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class BuyLocallyExcelHelper {

    public static String TYPE1 = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static String TYPE2 = "application/vnd.ms-excel";
    static String[] HEADERs = {
        "Vào sổ",
        "Ghi sổ",
        "Loại mua hàng",
        "Phương thức thanh toán",
        "Hình thức mua hàng",
        "Lập kèm hóa đơn",
        "Đã nhận hóa đơn",
        "Số chứng từ",
        "Số phiếu nhập",
        "Số CT thanh toán",
        "Ngày chứng từ",
        "Ngày hạch toán",
        "Loại tiền",
        "Tỷ giá",
        "Mã đối tượng",
        "Tên đối tượng",
        "Địa chỉ",
        "Mã số thuế",
        "Người giao",
        "Hạn thanh toán",
        "Diễn giải",
        "Mã nhân viên",
        "Kèm theo",
        "Tài khoản trả",
        "Tài khoản nhận",
        "Mẫu số HĐ",
        "Ký hiệu HĐ",
        "Số HĐ",
        "Ngày HĐ",
        "Mã hàng",
        "Tên hàng",
        "Là chi phí mua hàng",
        "Kho",
        "TK Nợ",
        "TK Có",
        "ĐT hạch toán",
        "ĐVT",
        "Số lượng",
        "Đơn giá",
        "Đơn giá QĐ",
        "Thành tiền",
        "Thành tiền QĐ",
        "Tỷ lệ CK",
        "Tiền CK",
        "Tiền CK QĐ",
        "Chi phí mua",
        "Chi phí trước HQ",
        "Giá nhận",
        "Số lô",
        "Hạn dùng",
        "Diễn giải thuế",
        "Giá tính thuế",
        "% thuế NK",
        "Tiền thuế NK",
        "TK thuế NK",
        "% thuế TTĐB",
        "Tiền thuế TTĐB",
        "TK thuế TTĐB",
        "% thuế GTGT",
        "Tiền thuế GTGT",
        "Tiền thuế GTGT QĐ",
        "TK thuế GTGT",
        "TKĐƯ thuế GTGT",
        "Mẫu số HĐ (HT)",
        "Ký hiệu HĐ (HT)",
        "Số HĐ (HT)",
        "Ngày HĐ (HT)",
        "Nhóm HHDV mua vào",
        "Khoản mục CP",
        "Đối tượng THCP",
        "Hợp đồng",
        "Mục thu/chi",
        "Phòng ban",
        "Mã thống kê",
    };

    static String SHEET = "SHEET1";

    public static boolean hasExcelFormat(MultipartFile file) {
        if (TYPE1.equals(file.getContentType()) || TYPE2.equals(file.getContentType())) {
            return true;
        }
        return false;
    }

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
            cellStyleHeader2.setFillForegroundColor(IndexedColors.OLIVE_GREEN.getIndex());
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
            cellStyleHeader3.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
            cellStyleHeader3.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cellStyleHeader3.setBorderBottom(BorderStyle.THIN);
            cellStyleHeader3.setBorderTop(BorderStyle.THIN);
            cellStyleHeader3.setBorderRight(BorderStyle.THIN);
            cellStyleHeader3.setBorderLeft(BorderStyle.THIN);

            CellStyle cellStyleHeader4 = workbook.createCellStyle();
            //            fontHeader.setBold(true);
            fontHeader.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
            cellStyleHeader4.setFont(fontHeader);
            cellStyleHeader4.setAlignment(HorizontalAlignment.CENTER);
            cellStyleHeader4.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyleHeader4.setFillForegroundColor(IndexedColors.BROWN.getIndex());
            cellStyleHeader4.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cellStyleHeader4.setBorderBottom(BorderStyle.THIN);
            cellStyleHeader4.setBorderTop(BorderStyle.THIN);
            cellStyleHeader4.setBorderRight(BorderStyle.THIN);
            cellStyleHeader4.setBorderLeft(BorderStyle.THIN);

            CellStyle cellStyleHeader5 = workbook.createCellStyle();
            //            fontHeader.setBold(true);
            fontHeader.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
            cellStyleHeader5.setFont(fontHeader);
            cellStyleHeader5.setAlignment(HorizontalAlignment.CENTER);
            cellStyleHeader5.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyleHeader5.setFillForegroundColor(IndexedColors.VIOLET.getIndex());
            cellStyleHeader5.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cellStyleHeader5.setBorderBottom(BorderStyle.THIN);
            cellStyleHeader5.setBorderTop(BorderStyle.THIN);
            cellStyleHeader5.setBorderRight(BorderStyle.THIN);
            cellStyleHeader5.setBorderLeft(BorderStyle.THIN);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
                if (col < 20) {
                    cell.setCellStyle(cellStyleHeader);
                } else if (col > 19 && col < 24) {
                    cell.setCellStyle(cellStyleHeader2);
                } else if (col > 23 && col < 45) {
                    cell.setCellStyle(cellStyleHeader3);
                } else if (col > 44 && col < 63) {
                    cell.setCellStyle(cellStyleHeader4);
                } else {
                    cell.setCellStyle(cellStyleHeader5);
                }
                sheet.setColumnWidth(col, 20 * 400);
            }

            CellStyle cellBody = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            cellBody.setDataFormat(createHelper.createDataFormat().getFormat("DD/MM/YYYY"));

            CellStyle cellStyleNumber = workbook.createCellStyle();
            cellStyleNumber.setDataFormat(createHelper.createDataFormat().getFormat("#,##0.00"));

            int rowIdx = 1;
            StringBuilder temp = new StringBuilder(synthetics.get(0).getVoucherNo());
            boolean check = false;
            for (SyntheticDTO synthetic : synthetics) {
                Row row = sheet.createRow(rowIdx++);
                if (!check) {
                    row.createCell(0).setCellValue("Sổ tài chính");
                    row.createCell(1).setCellValue("Có"); //ghi sổ
                    row.createCell(2).setCellValue(""); //loại mua hàng
                    row.createCell(3).setCellValue(""); //phg thức thanh toán
                    row.createCell(4).setCellValue(""); //hình thức mua hàng
                    row.createCell(5).setCellValue(""); //lập kèm hóa đơn
                    row.createCell(6).setCellValue(""); // đã nhận hóa đơn
                    row.createCell(7).setCellValue(synthetic.getVoucherNo());
                    row.createCell(8).setCellValue(synthetic.getVoucherNo()); //số phiếu nhập
                    row.createCell(9).setCellValue(""); //số CT thanh toán

                    row.createCell(10).setCellValue(synthetic.getVoucherDate()); //ngày chứng từ
                    row.getCell(10).setCellStyle(cellBody);

                    row.createCell(11).setCellValue(synthetic.getAccountingDate()); //ngày hạch toán
                    row.getCell(11).setCellStyle(cellBody);

                    row.createCell(12).setCellValue(synthetic.getCurrencyType());

                    if (synthetic.getCurrencyType().equals("VND")) {
                        row.createCell(13).setCellValue(exchangeRate); //Tỷ giá
                    }

                    row.createCell(14).setCellValue(synthetic.getCreditObject()); // mã đối tượng
                    row.createCell(15).setCellValue(""); //tên đối tượng
                    row.createCell(16).setCellValue(""); //địa chỉ
                    row.createCell(17).setCellValue(""); //mã số thuế
                    row.createCell(18).setCellValue(""); //ng giao
                    row.createCell(19).setCellValue(""); //hạn thanh toán
                    row.createCell(20).setCellValue(""); //diễn giải
                    row.createCell(21).setCellValue(synthetic.getEmployee()); //mã nhân viên
                    row.createCell(22).setCellValue(""); //kèm theo
                    row.createCell(23).setCellValue(synthetic.getBankAccount()); //tài khoản trả
                    row.createCell(24).setCellValue(""); // tài khoản nhận
                    row.createCell(25).setCellValue(""); //mẫu số HĐ
                    row.createCell(26).setCellValue(""); //ký hiệu HĐ
                    row.createCell(27).setCellValue(synthetic.getInvoiceNo()); //số HĐ
                    row.createCell(28).setCellValue(synthetic.getInvoiceDate()); // ngày HĐ
                    row.createCell(29).setCellValue(synthetic.getMaterialGoodCode()); // mã hàng
                    row.createCell(30).setCellValue(synthetic.getMaterialGoodName()); //tên hàng
                    row.createCell(31).setCellValue(""); //là chi phí mua hàng
                    row.createCell(32).setCellValue(synthetic.getStorageIn()); //Kho
                    row.createCell(33).setCellValue(synthetic.getDebitAccount()); //TK nợ
                    row.createCell(34).setCellValue(synthetic.getCreditAccount()); //TK có
                    row.createCell(35).setCellValue(""); //ĐT hạch toán
                    row.createCell(36).setCellValue(synthetic.getCaculationUnit()); //ĐVT
                    row.createCell(37).setCellValue(synthetic.getAmount()); //số lượng
                    row.createCell(38).setCellValue(""); //đơn giá
                    row.createCell(39).setCellValue(synthetic.getPrice().doubleValue()); //đơn giá QĐ
                    row.createCell(40).setCellValue(synthetic.getCurrency().doubleValue()); //thành tiền
                    row.createCell(41).setCellValue(synthetic.getCurrency().doubleValue() * 1.0); //thành tiền QĐ
                    row.createCell(42).setCellValue(synthetic.getTranferRate().doubleValue()); //Tỷ lệ CK
                    row.createCell(43).setCellValue(""); //tiền Ck
                    row.createCell(44).setCellValue(synthetic.getMoneyTranfer().doubleValue()); //tiền Ck QĐ
                    row.createCell(45).setCellValue(""); //Chi phí mua
                    row.createCell(46).setCellValue(""); //Chi phí trước HQ
                    row.createCell(47).setCellValue(synthetic.getCurrency().doubleValue() * 1.0); //Giá nhập kho
                    row.createCell(48).setCellValue(""); //Số lô
                    row.createCell(49).setCellValue(""); //Hạn dùng
                    row.createCell(50).setCellValue(""); //diễn giải thuế
                    row.createCell(51).setCellValue(""); //giá tính thuế
                    row.createCell(52).setCellValue(""); //%thuế Nk
                    row.createCell(53).setCellValue(""); //tiền thuế Nk
                    row.createCell(54).setCellValue(""); //TK thuế Nk
                    row.createCell(55).setCellValue(""); //% thuế TTĐB
                    row.createCell(56).setCellValue(""); //tiền thuế TTĐB
                    row.createCell(57).setCellValue(""); //TK thuế TTĐB
                    row.createCell(58).setCellValue(""); //% thuế GTGT
                    row.createCell(59).setCellValue(synthetic.getCurrency().doubleValue()); //tiền thuế GTGT
                    row.createCell(60).setCellValue(""); //tiền thuế GTGT QĐ
                    row.createCell(61).setCellValue(synthetic.getDebitAccount()); //TK thuế GTGT
                    row.createCell(62).setCellValue(synthetic.getCreditAccount()); //TKĐƯ thuế GTGT
                    row.createCell(63).setCellValue(""); //mẫu số HĐ (HT)
                    row.createCell(64).setCellValue(""); //Ký hiệu HĐ (HT)
                    row.createCell(65).setCellValue(""); //Số HĐ (HT)
                    row.createCell(66).setCellValue(""); //Ngày HĐ (HT)
                    row.createCell(67).setCellValue("1"); //Nhóm HHDV mua vào
                    row.createCell(68).setCellValue(synthetic.getItemCost()); //Khoản mục CP
                    row.createCell(69).setCellValue(""); //Đối tượng THCP
                    row.createCell(70).setCellValue(""); //Hợp đồng
                    row.createCell(71).setCellValue(""); //Mục thu/chi
                    row.createCell(72).setCellValue(""); //phòng ban
                    row.createCell(73).setCellValue(""); //mã thống kê
                    check = true;
                    continue;
                }
                if (temp.toString().trim().equals(synthetic.getVoucherNo())) {
                    row.createCell(0).setCellValue("");
                    row.createCell(1).setCellValue(""); //ghi sổ
                    row.createCell(2).setCellValue(""); //là chi phí mua hàng
                    row.createCell(3).setCellValue("");
                    row.createCell(4).setCellValue("");
                    row.createCell(5).setCellValue("");
                    row.createCell(6).setCellValue("");
                    row.createCell(7).setCellValue(""); //tỷ giá
                    row.createCell(8).setCellValue("");
                    row.createCell(9).setCellValue("");
                    row.createCell(10).setCellValue(""); //tên đối tượng
                    row.createCell(11).setCellValue(""); //địa chỉ
                    row.createCell(12).setCellValue(""); // mã số thuế
                    row.createCell(13).setCellValue(""); //ng nhận
                    row.createCell(14).setCellValue(""); //lý do nộp
                    row.createCell(15).setCellValue("");
                    row.createCell(16).setCellValue(""); //kèm theo
                    row.createCell(17).setCellValue(""); //diễn giải HT
                    row.createCell(18).setCellValue(synthetic.getDebitAccount());
                    row.createCell(19).setCellValue(synthetic.getCreditAccount());
                    Cell cell20 = row.createCell(20);
                    cell20.setCellValue(synthetic.getCurrency().doubleValue());
                    cell20.setCellStyle(cellStyleNumber);
                    row.createCell(21).setCellValue(synthetic.getMoneyTranfer().doubleValue()); //số tiền quy đổi
                    row.createCell(22).setCellValue(synthetic.getCreditObject()); //đối tượng HT
                    row.createCell(23).setCellValue(synthetic.getBankAccount()); // Tk ngân hàng
                    row.createCell(24).setCellValue(""); //khoản mục CP
                    row.createCell(25).setCellValue(""); //mục thu/chi
                    row.createCell(26).setCellValue(""); //phòng ban
                    row.createCell(27).setCellValue(""); // đối tượng THCP
                    row.createCell(28).setCellValue(""); // mã thống kê
                    row.createCell(29).setCellValue(""); //hợp đồng
                    row.createCell(30).setCellValue(""); ////diễn giải HT thuế
                    row.createCell(31).setCellValue(""); //TK thuế GTGT
                    row.createCell(32).setCellValue(""); //thuế suất
                    row.createCell(33).setCellValue(""); //tiền thuế GTGT
                    row.createCell(34).setCellValue(""); //giá tính thuế
                    row.createCell(35).setCellValue(""); //đối tượng HT thuế
                    row.createCell(36).setCellValue(""); //Mã số thuế ĐT hạch toán thuế
                    row.createCell(37).setCellValue(""); //mẫu số hóa đơn
                    row.createCell(38).setCellValue(""); //ký hiệu hóa đơn
                    row.createCell(39).setCellValue(""); //số HĐ
                    row.createCell(40).setCellValue("");
                    row.createCell(41).setCellValue(""); //Nhóm HHDV mua vào
                } else {
                    temp.delete(0, temp.length());
                    temp.append(synthetic.getVoucherNo());
                    row.createCell(0).setCellValue("Sổ tài chính");
                    row.createCell(1).setCellValue("Có"); //ghi sổ
                    row.createCell(2).setCellValue("Có"); //là chi phí mua hàng
                    row.createCell(3).setCellValue(synthetic.getVoucherNo());

                    row.createCell(6).setCellValue(synthetic.getCurrencyType());
                    if (synthetic.getCurrencyType().equals("VND")) {
                        row.createCell(7).setCellValue(exchangeRate); //tỷ giá
                    }
                    row.createCell(8).setCellValue("");
                    row.createCell(9).setCellValue(synthetic.getCreditObject());
                    row.createCell(10).setCellValue(""); //tên đối tượng
                    row.createCell(11).setCellValue(""); //địa chỉ
                    row.createCell(12).setCellValue(""); // mã số thuế
                    row.createCell(13).setCellValue(""); //ng nhận
                    row.createCell(14).setCellValue(""); //lý do nộp
                    row.createCell(15).setCellValue(synthetic.getEmployee());
                    row.createCell(16).setCellValue(""); //kèm theo
                    row.createCell(17).setCellValue(""); //diễn giải HT
                    row.createCell(18).setCellValue(synthetic.getDebitAccount());
                    row.createCell(19).setCellValue(synthetic.getCreditAccount());
                    Cell cell20 = row.createCell(20);
                    cell20.setCellValue(synthetic.getCurrency().doubleValue());
                    cell20.setCellStyle(cellStyleNumber);
                    row.createCell(21).setCellValue(synthetic.getMoneyTranfer().doubleValue()); //số tiền quy đổi
                    row.createCell(22).setCellValue(synthetic.getCreditObject()); //đối tượng HT
                    row.createCell(23).setCellValue(synthetic.getBankAccount()); // Tk ngân hàng
                    row.createCell(24).setCellValue(""); //khoản mục CP
                    row.createCell(25).setCellValue(""); //mục thu/chi
                    row.createCell(26).setCellValue(""); //phòng ban
                    row.createCell(27).setCellValue(""); // đối tượng THCP
                    row.createCell(28).setCellValue(""); // mã thống kê
                    row.createCell(29).setCellValue(""); //hợp đồng
                    row.createCell(30).setCellValue(""); ////diễn giải HT thuế
                    row.createCell(31).setCellValue(""); //TK thuế GTGT

                    if (synthetic.getTaxPercent() != null) {
                        row.createCell(32).setCellValue(synthetic.getTaxPercent()); //thuế suất
                    } else {
                        row.createCell(32).setCellValue("");
                    }

                    if (synthetic.getCurrencyTax() != null) {
                        row.createCell(33).setCellValue(synthetic.getCurrencyTax().doubleValue()); //tiền thuế GTGT
                    } else {
                        row.createCell(33).setCellValue("");
                    }
                    //                    row.createCell(32).setCellValue("");//thuế suất
                    //                    row.createCell(33).setCellValue("");//tiền thuế GTGT
                    row.createCell(34).setCellValue(""); //giá tính thuế
                    row.createCell(35).setCellValue(""); //đối tượng HT thuế
                    row.createCell(36).setCellValue(""); //Mã số thuế ĐT hạch toán thuế
                    row.createCell(37).setCellValue(""); //mẫu số hóa đơn
                    row.createCell(38).setCellValue(""); //ký hiệu hóa đơn
                    row.createCell(39).setCellValue(""); //số HĐ
                    row.createCell(40).setCellValue(synthetic.getInvoiceDate());
                    row.createCell(41).setCellValue(""); //Nhóm HHDV mua vào
                }
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }
}
