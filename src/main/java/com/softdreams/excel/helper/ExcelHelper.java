package com.softdreams.excel.helper;

import com.softdreams.excel.domain.*;
import java.io.IOException;
import java.io.InputStream;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

public class ExcelHelper {

    public static String TYPE1 = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static String TYPE2 = "application/vnd.ms-excel";
    static String[] HEADERs = {
        "Mã khách hàng",
        "Tên khách hàng",
        "Địa chỉ",
        "Nhóm KH, NCC",
        "Mã số thuế",
        "Quy mô",
        "Loại đối tượng",
        "Điện thoại",
        "Ngừng theo dõi",
    };
    static String SHEET = "Khach_hang";

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
                        //                        case 6:
                        //                            customer.setUnfollow(currentCell.getStringCellValue());
                        //                            break;

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

    //    public static List<Synthetic> excelToSynthetic(InputStream is) {
    //        try {
    //            Workbook workbook = WorkbookFactory.create(is);
    //            Sheet sheet = workbook.getSheetAt(0);
    //
    //            if (sheet.getRow(sheet.getLastRowNum()).getCell(0).getStringCellValue().contains("Số dòng")) {
    //                sheet.removeRow(sheet.getRow(sheet.getLastRowNum()));
    //            }
    //
    //            Iterator<Row> rows = sheet.iterator();
    //            List<Synthetic> synthetics = new ArrayList<>();
    //
    //
    //            int rowNumber = 0;
    //            while (rows.hasNext()) {
    //                Row currentRow = rows.next();
    //
    //                // skip header
    //                if (rowNumber == 0) {
    //                    rowNumber++;
    //                    continue;
    //                }
    //                Iterator<Cell> cellsInRow = currentRow.iterator();
    //
    //                Synthetic synthetic = new Synthetic();
    //
    //                Cell cell1 = currentRow.getCell(1);
    //                if (cell1.getStringCellValue().equals("Chứng từ nghiệp vụ khác")) {
    //                    synthetic.setVoucherTypeNo(1);
    //                } else if (cell1.getStringCellValue().equals("Bán hàng hóa, dịch vụ trong nước chưa thu tiền")) {
    //                    synthetic.setVoucherTypeNo(2);
    //                } else if (cell1.getStringCellValue().equals("Mua hàng trong nước nhập kho chưa thanh toán")) {
    //                    synthetic.setVoucherTypeNo(3);
    //                } else if (cell1.getStringCellValue().equals("Bảng tính khấu hao tài sản cố định")) {
    //                    synthetic.setVoucherTypeNo(4);
    //                } else if (cell1.getStringCellValue().equals("Chứng từ mua dịch vụ chưa thanh toán")) {
    //                    synthetic.setVoucherTypeNo(5);
    //                } else if (cell1.getStringCellValue().equals("Kết chuyển lãi,lỗ")) {
    //                    synthetic.setVoucherTypeNo(6);
    //                } else if (cell1.getStringCellValue().equals("Phiếu chi")) {
    //                    synthetic.setVoucherTypeNo(7);
    //                } else if (cell1.getStringCellValue().equals("Phiếu thu")) {
    //                    synthetic.setVoucherTypeNo(8);
    //                } else if (cell1.getStringCellValue().equals("Thu tiền gửi")) {
    //                    synthetic.setVoucherTypeNo(9);
    //                } else if (cell1.getStringCellValue().equals("Nhận hóa đơn hàng hóa")) {
    //                    synthetic.setVoucherTypeNo(10);
    //                } else if (cell1.getStringCellValue().equals("Xuất kho bán hàng")) {
    //                    synthetic.setVoucherTypeNo(11);
    //                } else if (cell1.getStringCellValue().equals("Phân bổ chi phí CCDC")) {
    //                    synthetic.setVoucherTypeNo(12);
    //                } else if (cell1.getStringCellValue().equals("Uỷ nhiệm chi")) {
    //                    synthetic.setVoucherTypeNo(13);
    //                }
    //
    //                int cellIdx = 0;
    //                while (cellsInRow.hasNext()) {
    //                    Cell currentCell = cellsInRow.next();
    //
    //                    switch (cellIdx) {
    //                        case 1:
    //                            synthetic.setVoucherType(currentCell.getStringCellValue());
    //                            break;
    //                        case 2:
    //                            synthetic.setVoucherNo(currentCell.getStringCellValue());
    //                            break;
    //                        case 3:
    //                            synthetic.setVoucherDate(convertToLocalDate(currentCell.getDateCellValue()));
    //                            break;
    //                        case 4:
    //                            synthetic.setAccountingDate(convertToLocalDate(currentCell.getDateCellValue()));
    //                            break;
    //                        case 5:
    //                            synthetic.setInvoiceNo(currentCell.getStringCellValue());
    //                            break;
    //                        case 6:
    //                            synthetic.setInvoiceDate(convertToLocalDate(currentCell.getDateCellValue()));
    //                            break;
    //                        case 7:
    //                            synthetic.setDebitAccount(currentCell.getStringCellValue());
    //                            break;
    //                        case 8:
    //                            synthetic.setCreditAccount(currentCell.getStringCellValue());
    //                            break;
    //                        case 9:
    //                            synthetic.setCurrencyType(currentCell.getStringCellValue());
    //                            break;
    //                        case 10:
    //                            synthetic.setCurrency((long) currentCell.getNumericCellValue());
    //                            break;
    //                        case 11:
    //                            synthetic.setMaterialGoodCode(currentCell.getStringCellValue());
    //                            break;
    //                        case 12:
    //                            synthetic.setMaterialGoodName(currentCell.getStringCellValue());
    //                            break;
    //                        case 13:
    //                            synthetic.setStorageIn(currentCell.getStringCellValue());
    //                            break;
    //                        case 14:
    //                            synthetic.setStorageOut(currentCell.getStringCellValue());
    //                            break;
    //                        case 15:
    //                            synthetic.setCaculationUnit(currentCell.getStringCellValue());
    //                            break;
    //                        case 16:
    //                            synthetic.setAmount((long) currentCell.getNumericCellValue());
    //                            break;
    //                        case 17:
    //                            synthetic.setPrice((long) currentCell.getNumericCellValue());
    //                            break;
    //                        case 18:
    //                            synthetic.setTranferRate((float) currentCell.getNumericCellValue());
    //                            break;
    //                        case 19:
    //                            synthetic.setMoneyTranfer((long) currentCell.getNumericCellValue());
    //                            break;
    //                        case 20:
    //                            synthetic.setFixedAssetsType(currentCell.getStringCellValue());
    //                            break;
    //                        case 21:
    //                            synthetic.setFixedAssetsCode(currentCell.getStringCellValue());
    //                            break;
    //                        case 22:
    //                            synthetic.setToolsCode(currentCell.getStringCellValue());
    //                            break;
    //                        case 23:
    //                            synthetic.setDebitObject(currentCell.getStringCellValue());
    //                            break;
    //                        case 24:
    //                            synthetic.setCreditObject(currentCell.getStringCellValue());
    //                            break;
    //                        case 25:
    //                            synthetic.setUnit(currentCell.getStringCellValue());
    //                            break;
    //                        case 26:
    //                            synthetic.setEmployee(currentCell.getStringCellValue());
    //                            break;
    //                        case 27:
    //                            synthetic.setBankAccount(currentCell.getStringCellValue());
    //                            break;
    //                        case 28:
    //                            synthetic.setItemCost(currentCell.getStringCellValue());
    //                            break;
    //                        case 29:
    //                            synthetic.setConstruction(currentCell.getStringCellValue());
    //                            break;
    //                        case 30:
    //                            synthetic.setCostSet(currentCell.getStringCellValue());
    //                            break;
    //                        case 31:
    //                            synthetic.setPurchaseOrder(currentCell.getStringCellValue());
    //                            break;
    //                        case 32:
    //                            synthetic.setBuyOrder(currentCell.getStringCellValue());
    //                            break;
    //                        case 33:
    //                            synthetic.setPurchaseContract(currentCell.getStringCellValue());
    //                            break;
    //                        case 34:
    //                            synthetic.setSaleContract(currentCell.getStringCellValue());
    //                            break;
    //                        case 35:
    //                            synthetic.setStatsCode(currentCell.getStringCellValue());
    //                            break;
    //                        case 36:
    //                            synthetic.setExplanation(currentCell.getStringCellValue());
    //                            break;
    //                        case 37:
    //                            synthetic.setExplanationDetail(currentCell.getStringCellValue());
    //                            break;
    //                        case 38:
    //                            synthetic.setRecordStatus(currentCell.getStringCellValue());
    //                            break;
    //                        default:
    //                            break;
    //                    }
    //
    //                    cellIdx++;
    //                }
    //                synthetics.add(synthetic);
    //            }
    //            workbook.close();
    //            return synthetics;
    //        } catch (IOException e) {
    //            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
    //        }
    //    }
    //
    //    public static LocalDate convertToLocalDate(Date dateToConvert) {
    //        if (dateToConvert == null) {
    //            return null;
    //        }
    //        return dateToConvert.toInstant()
    //                .atZone(ZoneId.systemDefault())
    //                .toLocalDate();
    //    }
    //
    //
    //    public static ByteArrayInputStream customersToExcel(List<Customer> customers) {
    //        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
    //            Sheet sheet = workbook.createSheet(SHEET);
    //
    //            // Header
    //            Row headerRow = sheet.createRow(0);
    //            headerRow.setHeight((short) 400);
    //            CellStyle cellStyleHeader = workbook.createCellStyle();
    //            Font fontHeader = workbook.createFont();
    //            fontHeader.setBold(true);
    //            cellStyleHeader.setFont(fontHeader);
    //            cellStyleHeader.setAlignment(HorizontalAlignment.CENTER);
    //            cellStyleHeader.setVerticalAlignment(VerticalAlignment.CENTER);
    //            cellStyleHeader.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
    //            cellStyleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    //            cellStyleHeader.setBorderBottom(BorderStyle.THIN);
    //            cellStyleHeader.setBorderTop(BorderStyle.THIN);
    //            cellStyleHeader.setBorderRight(BorderStyle.THIN);
    //            cellStyleHeader.setBorderLeft(BorderStyle.THIN);
    //
    //            for (int col = 0; col < HEADERs.length; col++) {
    //                Cell cell = headerRow.createCell(col);
    //                cell.setCellValue(HEADERs[col]);
    //                cell.setCellStyle(cellStyleHeader);
    //                if (col == 1 || col == 2) {
    //                    sheet.setColumnWidth(1, 25 * 700);
    //                    sheet.setColumnWidth(2, 25 * 1000);
    //                } else {
    //                    sheet.setColumnWidth(col, 20 * 250);
    //                }
    //            }
    //
    //            int rowIdx = 1;
    //            for (Customer customer : customers) {
    //                Row row = sheet.createRow(rowIdx++);
    //                row.createCell(0).setCellValue(customer.getCustomerCode());
    //                row.createCell(1).setCellValue(customer.getCustomerName());
    //                row.createCell(2).setCellValue(customer.getAddress());
    //                row.createCell(3).setCellValue(customer.getCustomerGroup());
    //                row.createCell(4).setCellValue(customer.getTax());
    //                boolean checkContainScale = false;
    //                for (String scale : scales) {
    //                    if (customer.getCustomerName().toLowerCase().contains(scale.toLowerCase())) {
    //                        checkContainScale = true;
    //                        break;
    //                    }
    //                }
    //                if (checkContainScale) {
    //                    row.createCell(5).setCellValue("Tổ chức");
    //                } else {
    //                    row.createCell(5).setCellValue("Cá nhân");
    //                }
    //                row.createCell(6).setCellValue("Khách hàng");
    //                row.createCell(7).setCellValue(customer.getPhoneNumber());
    //                row.createCell(8).setCellValue(customer.isUnfollow());
    //
    //            }
    //
    //            workbook.write(out);
    //            return new ByteArrayInputStream(out.toByteArray());
    //        } catch (IOException e) {
    //            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
    //        }
    //    }
    public static List<Congnophaithu> excelToCong_no_phai_thu(InputStream is) {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(is);
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

    public static List<Congnophaitra> excelToCong_no_phai_tra(InputStream is) {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(is);
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

    public static List<Congcudungcu> excelToCongcudungcu(InputStream is) {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(is);
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
                Congcudungcu Congcudungcu = new Congcudungcu();
                int cellIndex = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0:
                            Congcudungcu.setMa_ccdc(cell.getStringCellValue());
                            break;
                        case 1:
                            Congcudungcu.setTen_ccdc(cell.getStringCellValue());
                            break;
                        case 2:
                            Congcudungcu.setLoai_ccdc(cell.getStringCellValue());
                            break;
                        case 3:
                            Congcudungcu.setLy_do_ghi_tang(cell.getStringCellValue());
                            break;
                        case 4:
                            Congcudungcu.setNgay_ghi_tang(cell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                            break;
                        case 5:
                            Congcudungcu.setSo_ct_ghi_tang(cell.getStringCellValue());
                            break;
                        case 6:
                            Congcudungcu.setSo_ky_phan_bo((int) cell.getNumericCellValue());
                            break;
                        case 7:
                            Congcudungcu.setSo_ky_pb_con_lai((int) cell.getNumericCellValue());
                            break;
                        case 8:
                            Congcudungcu.setDvt(cell.getStringCellValue());
                            break;
                        case 9:
                            Congcudungcu.setSl_ghi_tang(cell.getNumericCellValue());
                            break;
                        case 10:
                            Congcudungcu.setLuy_ke_sl_da_giam(cell.getNumericCellValue());
                            break;
                        case 11:
                            Congcudungcu.setSl_con_lai(cell.getNumericCellValue());
                            break;
                        case 12:
                            Congcudungcu.setGia_tri_ccdc(cell.getNumericCellValue());
                            break;
                        case 13:
                            Congcudungcu.setGia_tri_PB_hang_ky(cell.getNumericCellValue());
                            break;
                        case 14:
                            Congcudungcu.setPb_trong_ky(cell.getNumericCellValue());
                            break;
                        case 15:
                            Congcudungcu.setLuy_ke_da_pb(cell.getNumericCellValue());
                            break;
                        case 16:
                            Congcudungcu.setGia_tri_con_lai(cell.getNumericCellValue());
                            break;
                        case 17:
                            Congcudungcu.setTk_cho_phan_bo(cell.getStringCellValue());
                            break;
                    }
                    cellIndex++;
                }
                list.add(Congcudungcu);
            }
            return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Taisancodinh> excelToTaisancodinh(InputStream is) {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);
            if (sheet.getRow(sheet.getLastRowNum()).getCell(0).getStringCellValue().contains("Số dòng")) {
                sheet.removeRow(sheet.getRow(sheet.getLastRowNum()));
            }
            Iterator<Row> rows = sheet.iterator();
            List<Taisancodinh> list = new ArrayList<>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row current = rows.next();
                if (rowNumber < 3) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellIterator = current.cellIterator();
                Taisancodinh Taisancodinh = new Taisancodinh();
                int cellIndex = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0:
                            Taisancodinh.setMa_tscd(cell.getStringCellValue());
                            break;
                        case 1:
                            Taisancodinh.setTen_tscd(cell.getStringCellValue());
                            break;
                        case 2:
                            Taisancodinh.setLoai_tscd(cell.getStringCellValue());
                            break;
                        case 3:
                            Taisancodinh.setDon_vi_su_dung(cell.getStringCellValue());
                            break;
                        case 4:
                            Taisancodinh.setNgay_ghi_tang(cell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                            break;
                        case 5:
                            Taisancodinh.setSo_ct_ghi_tang(cell.getStringCellValue());
                            break;
                        case 6:
                            Taisancodinh.setNgay_bat_dau_tinh_kh(
                                cell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                            );
                            break;
                        case 7:
                            Taisancodinh.setThoi_gian_su_dung(cell.getNumericCellValue());
                            break;
                        case 8:
                            Taisancodinh.setThoi_gian_su_dung_con_lai(cell.getNumericCellValue());
                            break;
                        case 9:
                            Taisancodinh.setNguyen_gia(cell.getNumericCellValue());
                            break;
                        case 10:
                            Taisancodinh.setGia_tri_tinh_kh(cell.getNumericCellValue());
                            break;
                        case 11:
                            Taisancodinh.setHao_mon_trong_ky(cell.getNumericCellValue());
                            break;
                        case 12:
                            Taisancodinh.setHao_mon_luy_ke(cell.getNumericCellValue());
                            break;
                        case 13:
                            Taisancodinh.setGia_tri_con_lai(cell.getNumericCellValue());
                            break;
                        case 14:
                            Taisancodinh.setGia_tri_KH_thang(cell.getNumericCellValue());
                            break;
                        case 15:
                            Taisancodinh.setTk_nguyen_gia(cell.getStringCellValue());
                            break;
                        case 16:
                            Taisancodinh.setTk_khau_hao(cell.getStringCellValue());
                            break;
                    }
                    cellIndex++;
                }
                list.add(Taisancodinh);
            }
            return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Tonkhodauky> excelToTonkhodauky(InputStream is) {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);
            if (sheet.getRow(sheet.getLastRowNum()).getCell(1).getStringCellValue().contains("Số dòng")) {
                sheet.removeRow(sheet.getRow(sheet.getLastRowNum()));
            }
            Iterator<Row> rows = sheet.iterator();
            List<Tonkhodauky> list = new ArrayList<>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row current = rows.next();
                if (rowNumber < 4) {
                    rowNumber++;
                    continue;
                }
                if (current.getFirstCellNum() == 0 && current.getCell(0).getStringCellValue().contains("Tên kho")) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellIterator = current.cellIterator();
                Tonkhodauky Tonkhodauky = new Tonkhodauky();
                int cellIndex = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0:
                            Tonkhodauky.setTen_kho(cell.getStringCellValue());
                            break;
                        case 1:
                            Tonkhodauky.setMa_hang(cell.getStringCellValue());
                            break;
                        case 2:
                            Tonkhodauky.setTen_hang(cell.getStringCellValue());
                            break;
                        case 3:
                            Tonkhodauky.setDvt(cell.getStringCellValue());
                            break;
                        case 4:
                            Tonkhodauky.setDau_ky_so_luong(cell.getNumericCellValue());
                            break;
                        case 5:
                            Tonkhodauky.setDau_ky_gia_tri(cell.getNumericCellValue());
                            break;
                        case 6:
                            Tonkhodauky.setNhap_kho_so_luong(cell.getNumericCellValue());
                            break;
                        case 7:
                            Tonkhodauky.setNhap_kho_gia_tri(cell.getNumericCellValue());
                            break;
                        case 8:
                            Tonkhodauky.setXuat_kho_so_luong(cell.getNumericCellValue());
                            break;
                        case 9:
                            Tonkhodauky.setXuat_kho_gia_tri(cell.getNumericCellValue());
                            break;
                        case 10:
                            Tonkhodauky.setCuoi_ky_so_luong(cell.getNumericCellValue());
                            break;
                        case 11:
                            Tonkhodauky.setCuoi_ky_gia_tri(cell.getNumericCellValue());
                            break;
                        case 12:
                            Tonkhodauky.setDon_gia_binh_quan(cell.getNumericCellValue());
                            break;
                    }
                    cellIndex++;
                }
                list.add(Tonkhodauky);
            }
            return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
