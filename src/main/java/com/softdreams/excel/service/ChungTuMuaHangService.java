package com.softdreams.excel.service;

import java.io.ByteArrayInputStream;

public interface ChungTuMuaHangService {
    ByteArrayInputStream exportBuyServiceExcel(int voucherTypeNo, String keyUUID);
}
