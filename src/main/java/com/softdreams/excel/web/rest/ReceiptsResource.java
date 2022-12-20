package com.softdreams.excel.web.rest;

import com.softdreams.excel.service.PaymentService;
import com.softdreams.excel.service.ReceiptsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing {@link com.softdreams.excel.domain.Customer}.
 */
@RestController
@RequestMapping("/api")
public class ReceiptsResource {

    private final Logger log = LoggerFactory.getLogger(ReceiptsResource.class);

    private static final String ENTITY_NAME = "customer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReceiptsService receiptsService;


    public ReceiptsResource(ReceiptsService receiptsService) {
        this.receiptsService = receiptsService;
    }


    @GetMapping(value = "/export-receipts")
    public ResponseEntity<Resource> exportExcel() {
        String filename = "Phieu Thu.xlsx";
        InputStreamResource file = new InputStreamResource(receiptsService.exportExcel());

        return ResponseEntity
            .ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
            .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
            .body(file);
    }
}
