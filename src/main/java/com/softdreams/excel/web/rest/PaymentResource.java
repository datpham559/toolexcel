package com.softdreams.excel.web.rest;

import com.softdreams.excel.repository.CustomerRepository;
import com.softdreams.excel.service.CustomerService;
import com.softdreams.excel.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing {@link com.softdreams.excel.domain.Customer}.
 */
@RestController
@RequestMapping("/api")
public class PaymentResource {

    private final Logger log = LoggerFactory.getLogger(PaymentResource.class);

    private static final String ENTITY_NAME = "customer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PaymentService paymentService;


    public PaymentResource(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @GetMapping(value = "/export-payment")
    public ResponseEntity<Resource> exportExcel(@RequestParam("voucherTypeNo") int voucherTypeNo,@RequestParam("keyUUID") String keyUUID) {
        String filename = "Phieu Chi.xlsx";
        InputStreamResource file = new InputStreamResource(paymentService.exportExcel(voucherTypeNo,keyUUID));

        return ResponseEntity
            .ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
            .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
            .body(file);
    }
}
