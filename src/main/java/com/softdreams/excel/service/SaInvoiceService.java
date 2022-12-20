package com.softdreams.excel.service;

import java.io.ByteArrayInputStream;

public interface SaInvoiceService {
    ByteArrayInputStream exportSaInvoice(int voucherTypeNo, String keyUUID);

}
