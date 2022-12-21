package com.softdreams.excel.web.rest;

import com.softdreams.excel.service.SaInvoiceService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/saInvoice")
public class SaInvoiceResource {

    private final SaInvoiceService saInvoiceService;

    public SaInvoiceResource(SaInvoiceService saInvoiceService) {
        this.saInvoiceService = saInvoiceService;
    }

    @GetMapping(value = "/export")
    public ResponseEntity<Resource> exportExcel(
        @RequestParam("voucherTypeNo") int voucherTypeNo,
        @RequestParam("keyUUID") String keyUUID,
        @RequestParam(value = "serialInvoice", required = false) String serialInvoice
    ) {
        String filename = "Chung_tu_ban_hang.xlsx";
        InputStreamResource file = new InputStreamResource(saInvoiceService.exportSaInvoice(voucherTypeNo, keyUUID, serialInvoice));

        return ResponseEntity
            .ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
            .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
            .body(file);
    }
}
