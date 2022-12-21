package com.softdreams.excel.helper;

import com.softdreams.excel.domain.Synthetic;
import com.softdreams.excel.service.dto.SyntheticDTO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import org.apache.poi.common.usermodel.fonts.FontHeader;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportExcelHelper {

    static String SHEET_BAO_CO = "Bao_co";

    static String[] HEADERs_bao_co = {
        "Vào sổ",
        "Ghi sổ",
        "Số chứng từ",
        "Ngày chứng từ",
        "Ngày hạch toán",
        "Loại tiền",
        "Tỷ giá",
        "Nộp vào TK",
        "Diễn giải HT",
        "TK Nợ",
        "TK Có",
        "Số tiền",
        "Số tiền QĐ",
        "Đối tượng HT",
        "Khoản mục CP",
        "Mục thu/chi",
        "Phòng ban",
    };

    static String[] Header_chung_tu_mua_dich_vu = {
        "Vào sổ",
        "Ghi sổ",
        "Phương thức thanh toán",
        "Là chi phí mua hàng",
        "Lập kèm hóa đơn",
        "Đã nhận hóa đơn",
        "Số chứng từ",
        "Số CT thanh toán",
        "Ngày chứng từ",
        "Ngày hạch toán",
        "Loại tiền",
        "Tỷ giá",
        "Mã đối tượng",
        "Tên đối tượng",
        "Địa chỉ",
        "Mã số thuế",
        "Người liên hệ",
        "Diễn giải",
        "Hạn thanh toán",
        "Kèm theo",
        "Mã nhân viên",
        "Tài khoản trả",
        "Tài khoản nhận",
        "Mẫu số HĐ",
        "Ký hiệu HĐ",
        "Số HĐ",
        "Ngày HĐ",
        "Mã dịch vụ",
        "Tên dịch vụ",
        "ĐVT",
        "TK Nợ",
        "TK Có",
        "ĐT Hạch Toán",
        "Số Lượng",
        "Đơn Giá",
        "Đơn Giá QĐ",
        "Thành Tiền",
        "Thành Tiền QĐ",
        "Tỷ lệ CK",
        "Tiền CK",
        "Tiền CK Quy Đổi",
        "Diễn giải thuế",
        "% thuế GTGT",
        "Tiền thuế GTGT",
        "Tiền thuế GTGT QĐ",
        "TK thuế GTGT",
        "TKĐƯ thuế GTGT",
        "Mẫu số HĐ(HT)",
        "Ký hiệu HĐ(HT)",
        "Số HĐ(HT)",
        "Ngày HĐ(HT)",
        "Nhóm HHDV mua vào",
        "Khoản mục CP",
        "Đối tượng THCP",
        "Hợp đồng",
        "Mục thu/ chi",
        "Phòng ban",
        "Mã thống kê",
    };

    private static HashMap<String, Double> createHashMap() {
        HashMap<String, Double> hashMap = new HashMap<>();
        hashMap.put("VND", 1.0);
        return hashMap;
    }

    private static CellStyle createCellStyle(
        Workbook workbook,
        Font font,
        short fillForegroundColor,
        FillPatternType fillPattern,
        HorizontalAlignment alignment,
        VerticalAlignment verticalAlignment,
        BorderStyle borderStyle
    ) {
        CellStyle cellBody = workbook.createCellStyle();
        cellBody.setFillForegroundColor(fillForegroundColor);
        cellBody.setFillPattern(fillPattern);
        cellBody.setFont(font);
        cellBody.setAlignment(alignment);
        cellBody.setVerticalAlignment(verticalAlignment);
        cellBody.setBorderBottom(borderStyle);
        cellBody.setBorderTop(borderStyle);
        cellBody.setBorderRight(borderStyle);
        cellBody.setBorderLeft(borderStyle);
        return cellBody;
    }

    private static void createRow(Row row, SyntheticDTO synthetic, CellStyle cellStyle, boolean check) {
        BigDecimal moneyChange = synthetic.getCurrency().multiply(BigDecimal.valueOf(createHashMap().get("VND")));
        row.createCell(47).setCellValue("");
        row.createCell(48).setCellValue("");
        row.createCell(49).setCellValue("");
        row.createCell(50).setCellValue("");
        row.createCell(51).setCellValue(1);
        row.createCell(52).setCellValue(synthetic.getItemCost());
        row.createCell(53).setCellValue(synthetic.getCostSet());
        row.createCell(54).setCellValue("");
        row.createCell(55).setCellValue("");
        row.createCell(56).setCellValue("");
        row.createCell(57).setCellValue("");
        if (!check) {
            row.createCell(0).setCellValue("");
            row.createCell(1).setCellValue("");
            row.createCell(2).setCellValue("");
            row.createCell(3).setCellValue("");
            row.createCell(4).setCellValue("1 hóa đơn");
            row.createCell(5).setCellValue("Không");
            row.createCell(6).setCellValue("");
            row.createCell(7).setCellValue("");
            row.createCell(8).setCellValue("");
            row.createCell(9).setCellValue("");
            row.createCell(10).setCellValue("");
            row.createCell(11).setCellValue("");
            row.createCell(12).setCellValue("");
            row.createCell(13).setCellValue("");
            row.createCell(14).setCellValue("");
            row.createCell(15).setCellValue("");
            row.createCell(16).setCellValue("");
            row.createCell(17).setCellValue("");
            row.createCell(18).setCellValue("");
            row.createCell(19).setCellValue("");
            row.createCell(20).setCellValue("");
            row.createCell(21).setCellValue("");
            row.createCell(22).setCellValue("");
            row.createCell(23).setCellValue("");
            row.createCell(24).setCellValue("");
            row.createCell(25).setCellValue("");
            row.createCell(26).setCellValue("");
            row.createCell(27).setCellValue(synthetic.getMaterialGoodCode());
            row.createCell(28).setCellValue(synthetic.getMaterialGoodName());
            row.createCell(29).setCellValue("");
            row.createCell(30).setCellValue(synthetic.getDebitAccount());
            row.createCell(31).setCellValue(synthetic.getCreditAccount());
            row.createCell(32).setCellValue(synthetic.getDebitObject());
            row.createCell(33).setCellValue(synthetic.getAmount());
            row.createCell(34).setCellValue(synthetic.getPrice().doubleValue());
            row.createCell(35).setCellValue(synthetic.getPrice().multiply(new BigDecimal(createHashMap().get("VND"))).doubleValue());
            row.createCell(36).setCellValue(synthetic.getCurrency().doubleValue());
            row.createCell(37).setCellValue(moneyChange.doubleValue());
            row.createCell(38).setCellValue(synthetic.getTranferRate().doubleValue());
            row.createCell(39).setCellValue(synthetic.getMoneyTranfer().doubleValue());
            row.createCell(40).setCellValue(synthetic.getMoneyTranfer().doubleValue());
            row.createCell(41).setCellValue("Thuế GTGT");
            if (synthetic.getTaxPercent() != null) {
                row.createCell(42).setCellValue(synthetic.getTaxPercent());
            } else {
                row.createCell(42).setCellValue("");
            }
            if (synthetic.getCurrencyTax() != null) {
                row.createCell(43).setCellValue(synthetic.getCurrencyTax().doubleValue());
            } else {
                row.createCell(43).setCellValue("");
            }
            row.createCell(44).setCellValue(synthetic.getCurrencyTax().multiply(new BigDecimal(createHashMap().get("VND"))).doubleValue());
            row.createCell(45).setCellValue("");
            row.createCell(46).setCellValue("");
        } else {
            row.createCell(0).setCellValue("Sổ tài chính");
            row.createCell(1).setCellValue("Có");
            row.createCell(2).setCellValue("Chưa thanh toán");
            row.createCell(3).setCellValue("");
            row.createCell(4).setCellValue("1 hóa đơn");
            row.createCell(5).setCellValue("Không");
            row.createCell(6).setCellValue(synthetic.getVoucherNo());
            row.createCell(7).setCellValue("");
            Cell cell = row.createCell(8);
            cell.setCellValue(synthetic.getVoucherDate());
            cell.setCellStyle(cellStyle);
            cell = row.createCell(9);
            cell.setCellValue(synthetic.getAccountingDate());
            cell.setCellStyle(cellStyle);
            row.createCell(10).setCellValue(synthetic.getCurrencyType());
            if (synthetic.getCurrencyType().equals("VND")) {
                row.createCell(11).setCellValue(createHashMap().get("VND"));
            }
            row.createCell(12).setCellValue(synthetic.getDebitObject());
            row.createCell(13).setCellValue("");
            row.createCell(14).setCellValue("");
            row.createCell(15).setCellValue("");
            row.createCell(16).setCellValue("");
            row.createCell(17).setCellValue(synthetic.getExplanation());
            row.createCell(18).setCellValue("");
            row.createCell(19).setCellValue("");
            row.createCell(20).setCellValue(synthetic.getEmployee());
            row.createCell(21).setCellValue(synthetic.getBankAccount());
            row.createCell(22).setCellValue("");
            row.createCell(23).setCellValue("");
            row.createCell(24).setCellValue("");
            row.createCell(25).setCellValue(synthetic.getInvoiceNo());
            cell = row.createCell(26);
            cell.setCellValue(synthetic.getInvoiceDate());
            cell.setCellStyle(cellStyle);
            row.createCell(27).setCellValue(synthetic.getMaterialGoodCode());
            row.createCell(28).setCellValue(synthetic.getMaterialGoodName());
            row.createCell(29).setCellValue(synthetic.getCaculationUnit());
            row.createCell(30).setCellValue(synthetic.getDebitAccount());
            row.createCell(31).setCellValue(synthetic.getCreditAccount());
            row.createCell(32).setCellValue(synthetic.getDebitObject());
            row.createCell(33).setCellValue(synthetic.getAmount());
            row.createCell(34).setCellValue("");
            row.createCell(35).setCellValue(synthetic.getPrice().doubleValue());
            row.createCell(36).setCellValue(synthetic.getCurrency().doubleValue());
            row.createCell(37).setCellValue("");
            row.createCell(38).setCellValue(synthetic.getTranferRate().doubleValue());
            row.createCell(39).setCellValue("");
            row.createCell(40).setCellValue(synthetic.getMoneyTranfer().doubleValue());
            row.createCell(41).setCellValue("");
            if (synthetic.getTaxPercent() != null) {
                row.createCell(42).setCellValue(synthetic.getTaxPercent());
            } else {
                row.createCell(42).setCellValue("");
            }
            if (synthetic.getCurrencyTax() != null) {
                row.createCell(43).setCellValue(synthetic.getCurrencyTax().doubleValue());
                row.createCell(44).setCellValue(synthetic.getCurrencyTax().doubleValue());
            } else {
                row.createCell(43).setCellValue("");
                row.createCell(44).setCellValue(synthetic.getCurrencyTax().doubleValue());
            }
            row.createCell(45).setCellValue(synthetic.getDebitAccountTax());
            row.createCell(46).setCellValue(synthetic.getCreditAccountTax());
        }
    }

    public static ByteArrayInputStream creditTransferToExcel(List<Synthetic> synthetics) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET_BAO_CO);
            Row headerRow = sheet.createRow(0);
            headerRow.setHeight((short) 400);
            CellStyle cellStyleHeader1 = workbook.createCellStyle();
            Font fontHeader = workbook.createFont();
            fontHeader.setBold(true);
            cellStyleHeader1.setFont(fontHeader);
            cellStyleHeader1.setAlignment(HorizontalAlignment.CENTER);
            cellStyleHeader1.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyleHeader1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cellStyleHeader1.setBorderBottom(BorderStyle.THIN);
            cellStyleHeader1.setBorderTop(BorderStyle.THIN);
            cellStyleHeader1.setBorderRight(BorderStyle.THIN);
            cellStyleHeader1.setBorderLeft(BorderStyle.THIN);
            cellStyleHeader1.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
            CellStyle cellStyleHeader2 = workbook.createCellStyle();
            fontHeader.setBold(true);
            cellStyleHeader2.setFont(fontHeader);
            cellStyleHeader2.setAlignment(HorizontalAlignment.CENTER);
            cellStyleHeader2.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyleHeader2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cellStyleHeader2.setBorderBottom(BorderStyle.THIN);
            cellStyleHeader2.setBorderTop(BorderStyle.THIN);
            cellStyleHeader2.setBorderRight(BorderStyle.THIN);
            cellStyleHeader2.setBorderLeft(BorderStyle.THIN);
            cellStyleHeader2.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
            CellStyle cellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yy"));
            CellStyle cellStyleNumber = workbook.createCellStyle();
            cellStyleNumber.setDataFormat(createHelper.createDataFormat().getFormat("#,##0.00"));
            for (int col = 0; col < HEADERs_bao_co.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs_bao_co[col]);
                if (col > 7) {
                    cell.setCellStyle(cellStyleHeader2);
                } else {
                    cell.setCellStyle(cellStyleHeader1);
                }

                sheet.setColumnWidth(col, 20 * 250);
            }

            int rowIdx = 1;
            StringBuilder temp = new StringBuilder(synthetics.get(0).getVoucherNo());
            boolean check = false;
            for (Synthetic synthetic : synthetics) {
                Row row = sheet.createRow(rowIdx++);
                if (!check) {
                    row.createCell(0).setCellValue("Sổ tài chính");
                    row.createCell(1).setCellValue("Có");
                    row.createCell(2).setCellValue(synthetic.getVoucherNo());
                    Cell cell = row.createCell(3);
                    cell.setCellValue(synthetic.getVoucherDate());
                    cell.setCellStyle(cellStyle);
                    cell = row.createCell(4);
                    cell.setCellValue(synthetic.getAccountingDate());
                    cell.setCellStyle(cellStyle);
                    row.createCell(5).setCellValue(synthetic.getCurrencyType());
                    cell = row.createCell(6);
                    cell.setCellValue(createHashMap().get(synthetic.getCurrencyType()));
                    cell.setCellStyle(cellStyleNumber);
                    row.createCell(7).setCellValue(synthetic.getBankAccount());
                    row.createCell(8).setCellValue(synthetic.getExplanation());
                    row.createCell(9).setCellValue(synthetic.getDebitAccount());
                    row.createCell(10).setCellValue(synthetic.getCreditAccount());
                    cell = row.createCell(11);
                    cell.setCellValue(synthetic.getCurrency().doubleValue());
                    cell.setCellStyle(cellStyleNumber);
                    cell = row.createCell(12);
                    cell.setCellValue(
                        synthetic.getCurrency().multiply(new BigDecimal(createHashMap().get(synthetic.getCurrencyType()))).doubleValue()
                    );
                    cell.setCellStyle(cellStyleNumber);
                    row.createCell(13).setCellValue(synthetic.getCreditObject());
                    row.createCell(14).setCellValue(synthetic.getItemCost());
                    row.createCell(15).setCellValue("");
                    row.createCell(16).setCellValue("");
                    row.createCell(17).setCellValue(synthetic.getItemCost());
                    row.createCell(18).setCellValue("");
                    row.createCell(19).setCellValue("");
                    check = true;
                    continue;
                }
                if (temp.toString().trim().equals(synthetic.getVoucherNo())) {
                    row.createCell(0).setCellValue("");
                    row.createCell(1).setCellValue("");
                    row.createCell(2).setCellValue("");
                    row.createCell(3).setCellValue("");
                    row.createCell(4).setCellValue("");
                    row.createCell(5).setCellValue("");
                    row.createCell(6).setCellValue("");
                    row.createCell(7).setCellValue("");
                    row.createCell(8).setCellValue("");
                    row.createCell(9).setCellValue(synthetic.getDebitAccount());
                    row.createCell(10).setCellValue(synthetic.getCreditAccount());
                    Cell cell = row.createCell(11);
                    cell.setCellValue(synthetic.getCurrency().doubleValue());
                    cell.setCellStyle(cellStyleNumber);
                    cell = row.createCell(12);
                    cell.setCellValue(
                        synthetic.getCurrency().multiply(new BigDecimal(createHashMap().get(synthetic.getCurrencyType()))).doubleValue()
                    );
                    cell.setCellStyle(cellStyleNumber);
                    row.createCell(13).setCellValue(synthetic.getCreditObject());
                    row.createCell(14).setCellValue("");
                    row.createCell(15).setCellValue("");
                    row.createCell(16).setCellValue("");
                    row.createCell(17).setCellValue("");
                    row.createCell(18).setCellValue("");
                    row.createCell(19).setCellValue("");
                } else {
                    temp.delete(0, temp.length());
                    temp.append(synthetic.getVoucherNo());
                    row.createCell(0).setCellValue("Sổ tài chính");
                    row.createCell(1).setCellValue("Có");
                    row.createCell(2).setCellValue(synthetic.getVoucherNo());
                    Cell cell = row.createCell(3);
                    cell.setCellValue(synthetic.getVoucherDate());
                    cell.setCellStyle(cellStyle);
                    cell = row.createCell(4);
                    cell.setCellValue(synthetic.getAccountingDate());
                    cell.setCellStyle(cellStyle);
                    row.createCell(5).setCellValue(synthetic.getCurrencyType());
                    cell = row.createCell(6);
                    cell.setCellValue(createHashMap().get(synthetic.getCurrencyType()));
                    cell.setCellStyle(cellStyleNumber);
                    row.createCell(7).setCellValue(synthetic.getBankAccount());
                    row.createCell(8).setCellValue(synthetic.getExplanation());
                    row.createCell(9).setCellValue(synthetic.getDebitAccount());
                    row.createCell(10).setCellValue(synthetic.getCreditAccount());
                    cell = row.createCell(11);
                    cell.setCellValue(synthetic.getCurrency().doubleValue());
                    cell.setCellStyle(cellStyleNumber);
                    cell = row.createCell(12);
                    cell.setCellValue(
                        synthetic.getCurrency().multiply(new BigDecimal(createHashMap().get(synthetic.getCurrencyType()))).doubleValue()
                    );
                    cell.setCellStyle(cellStyleNumber);
                    row.createCell(13).setCellValue(synthetic.getCreditObject());
                    row.createCell(14).setCellValue(synthetic.getItemCost());
                    row.createCell(15).setCellValue("");
                    row.createCell(16).setCellValue("");
                    row.createCell(17).setCellValue(synthetic.getItemCost());
                    row.createCell(18).setCellValue("");
                    row.createCell(19).setCellValue("");
                }
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream chung_tu_mua_dich_vuToExcel(List<SyntheticDTO> synthetics) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet("Chung_tu_mua_dich_vu");
            Integer exchangeRate = 1;
            // Header
            Row headerRow = sheet.createRow(0);
            headerRow.setHeight((short) 400);
            CellStyle cellBody = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            cellBody.setDataFormat(createHelper.createDataFormat().getFormat("DD/MM/YYYY"));
            Font fontHeader = workbook.createFont();
            fontHeader.setBold(true);
            for (int col = 0; col < Header_chung_tu_mua_dich_vu.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(Header_chung_tu_mua_dich_vu[col]);
                sheet.setColumnWidth(col, 20 * 250);
                if (col <= 22) {
                    cell.setCellStyle(
                        createCellStyle(
                            workbook,
                            fontHeader,
                            IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex(),
                            FillPatternType.SOLID_FOREGROUND,
                            HorizontalAlignment.CENTER,
                            VerticalAlignment.CENTER,
                            BorderStyle.THIN
                        )
                    );
                } else if (col <= 26) {
                    cell.setCellStyle(
                        createCellStyle(
                            workbook,
                            fontHeader,
                            IndexedColors.GREEN.getIndex(),
                            FillPatternType.SOLID_FOREGROUND,
                            HorizontalAlignment.CENTER,
                            VerticalAlignment.CENTER,
                            BorderStyle.THIN
                        )
                    );
                } else if (col <= 40) {
                    cell.setCellStyle(
                        createCellStyle(
                            workbook,
                            fontHeader,
                            IndexedColors.GREEN.getIndex(),
                            FillPatternType.SOLID_FOREGROUND,
                            HorizontalAlignment.CENTER,
                            VerticalAlignment.CENTER,
                            BorderStyle.THIN
                        )
                    );
                } else if (col <= 52) {
                    cell.setCellStyle(
                        createCellStyle(
                            workbook,
                            fontHeader,
                            IndexedColors.DARK_YELLOW.getIndex(),
                            FillPatternType.SOLID_FOREGROUND,
                            HorizontalAlignment.CENTER,
                            VerticalAlignment.CENTER,
                            BorderStyle.THIN
                        )
                    );
                } else {
                    cell.setCellStyle(
                        createCellStyle(
                            workbook,
                            fontHeader,
                            IndexedColors.VIOLET.getIndex(),
                            FillPatternType.SOLID_FOREGROUND,
                            HorizontalAlignment.CENTER,
                            VerticalAlignment.CENTER,
                            BorderStyle.THIN
                        )
                    );
                }
            }
            int rowIdx = 1;
            StringBuilder term = new StringBuilder();
            term.append(synthetics.get(0).getVoucherNo());
            for (SyntheticDTO synthetic : synthetics) {
                Row row = sheet.createRow(rowIdx++);
                if (synthetic != synthetics.get(0)) {
                    if (term.toString().equals(synthetic.getVoucherNo())) {
                        createRow(row, synthetic, cellBody, false);
                    } else {
                        term.delete(0, term.length());
                        term.append(synthetic.getVoucherNo());
                        createRow(row, synthetic, cellBody, true);
                    }
                } else {
                    createRow(row, synthetic, cellBody, true);
                }
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }
}
