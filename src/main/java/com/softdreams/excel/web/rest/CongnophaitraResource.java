package com.softdreams.excel.web.rest;

import com.softdreams.excel.domain.Congnophaitra;
import com.softdreams.excel.helper.ExcelHelper;
import com.softdreams.excel.message.ResponseMessage;
import com.softdreams.excel.repository.CongnophaitraRepository;
import com.softdreams.excel.service.CongnophaitraService;
import com.softdreams.excel.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.softdreams.excel.domain.Congnophaitra}.
 */
@RestController
@RequestMapping("/api/congnophaitra")
public class CongnophaitraResource {

    private final Logger log = LoggerFactory.getLogger(CongnophaitraResource.class);

    private static final String ENTITY_NAME = "congnophaitra";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CongnophaitraService congnophaitraService;

    private final CongnophaitraRepository congnophaitraRepository;

    public CongnophaitraResource(CongnophaitraService congnophaitraService, CongnophaitraRepository congnophaitraRepository) {
        this.congnophaitraService = congnophaitraService;
        this.congnophaitraRepository = congnophaitraRepository;
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadfile(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                congnophaitraService.saveCongnophaitra(file);
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
}
