package com.softdreams.excel.web.rest;

import com.softdreams.excel.domain.Congcudungcu;
import com.softdreams.excel.helper.ExcelHelper;
import com.softdreams.excel.message.ResponseMessage;
import com.softdreams.excel.repository.CongcudungcuRepository;
import com.softdreams.excel.service.CongcudungcuService;
import com.softdreams.excel.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.softdreams.excel.domain.Congcudungcu}.
 */
@RestController
@RequestMapping("/api/congcudungcu")
public class CongcudungcuResource {

    private final Logger log = LoggerFactory.getLogger(CongcudungcuResource.class);

    private static final String ENTITY_NAME = "congcudungcu";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CongcudungcuService congcudungcuService;

    private final CongcudungcuRepository congcudungcuRepository;

    public CongcudungcuResource(CongcudungcuService congcudungcuService, CongcudungcuRepository congcudungcuRepository) {
        this.congcudungcuService = congcudungcuService;
        this.congcudungcuRepository = congcudungcuRepository;
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadfile(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                congcudungcuService.saveCongcudungcu(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @GetMapping(value = "/export")
    public ResponseEntity<Resource> exportExcel() {
        String filename = "CCDC.xlsx";
        InputStreamResource file = new InputStreamResource(congcudungcuService.exportCongcudungcu());
        return ResponseEntity
            .ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
            .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
            .body(file);
    }
}
