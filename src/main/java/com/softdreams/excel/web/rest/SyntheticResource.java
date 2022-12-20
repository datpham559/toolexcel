package com.softdreams.excel.web.rest;

import com.softdreams.excel.domain.Synthetic;
import com.softdreams.excel.helper.ExcelHelper;
import com.softdreams.excel.message.ResponseMessage;
import com.softdreams.excel.repository.SyntheticRepository;
import com.softdreams.excel.service.SyntheticService;
import com.softdreams.excel.web.rest.errors.BadRequestAlertException;
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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * REST controller for managing {@link com.softdreams.excel.domain.Synthetic}.
 */
@RestController
@RequestMapping("/api/synthetic")
public class SyntheticResource {

    private final Logger log = LoggerFactory.getLogger(SyntheticResource.class);

    private static final String ENTITY_NAME = "synthetic";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SyntheticService syntheticService;

    private final SyntheticRepository syntheticRepository;

    public SyntheticResource(SyntheticService syntheticService, SyntheticRepository syntheticRepository) {
        this.syntheticService = syntheticService;
        this.syntheticRepository = syntheticRepository;
    }

    /**
     * {@code POST  /synthetics} : Create a new synthetic.
     *
     * @param synthetic the synthetic to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new synthetic, or with status {@code 400 (Bad Request)} if the synthetic has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/synthetics")
    public ResponseEntity<Synthetic> createSynthetic(@RequestBody Synthetic synthetic) throws URISyntaxException {
        log.debug("REST request to save Synthetic : {}", synthetic);
        if (synthetic.getId() != null) {
            throw new BadRequestAlertException("A new synthetic cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Synthetic result = syntheticService.save(synthetic);
        return ResponseEntity
            .created(new URI("/api/synthetics/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /synthetics/:id} : Updates an existing synthetic.
     *
     * @param id        the id of the synthetic to save.
     * @param synthetic the synthetic to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated synthetic,
     * or with status {@code 400 (Bad Request)} if the synthetic is not valid,
     * or with status {@code 500 (Internal Server Error)} if the synthetic couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/synthetics/{id}")
    public ResponseEntity<Synthetic> updateSynthetic(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Synthetic synthetic
    ) throws URISyntaxException {
        log.debug("REST request to update Synthetic : {}, {}", id, synthetic);
        if (synthetic.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, synthetic.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!syntheticRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Synthetic result = syntheticService.update(synthetic);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, synthetic.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /synthetics/:id} : Partial updates given fields of an existing synthetic, field will ignore if it is null
     *
     * @param id        the id of the synthetic to save.
     * @param synthetic the synthetic to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated synthetic,
     * or with status {@code 400 (Bad Request)} if the synthetic is not valid,
     * or with status {@code 404 (Not Found)} if the synthetic is not found,
     * or with status {@code 500 (Internal Server Error)} if the synthetic couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/synthetics/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<Synthetic> partialUpdateSynthetic(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Synthetic synthetic
    ) throws URISyntaxException {
        log.debug("REST request to partial update Synthetic partially : {}, {}", id, synthetic);
        if (synthetic.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, synthetic.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!syntheticRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Synthetic> result = syntheticService.partialUpdate(synthetic);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, synthetic.getId().toString())
        );
    }

    /**
     * {@code GET  /synthetics} : get all the synthetics.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of synthetics in body.
     */
    @GetMapping("/synthetics")
    public List<Synthetic> getAllSynthetics() {
        log.debug("REST request to get all Synthetics");
        return syntheticService.findAll();
    }

    /**
     * {@code GET  /synthetics/:id} : get the "id" synthetic.
     *
     * @param id the id of the synthetic to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the synthetic, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/synthetics/{id}")
    public ResponseEntity<Synthetic> getSynthetic(@PathVariable Long id) {
        log.debug("REST request to get Synthetic : {}", id);
        Optional<Synthetic> synthetic = syntheticService.findOne(id);
        return ResponseUtil.wrapOrNotFound(synthetic);
    }

    /**
     * {@code DELETE  /synthetics/:id} : delete the "id" synthetic.
     *
     * @param id the id of the synthetic to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/synthetics/{id}")
    public ResponseEntity<Void> deleteSynthetic(@PathVariable Long id) {
        log.debug("REST request to delete Synthetic : {}", id);
        syntheticService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    @PostMapping("/import")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                syntheticService.saveToSynthetic(file);

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
    public ResponseEntity<Resource> exportExcel(@RequestParam("voucherTypeNo") int voucherTypeNo,@RequestParam("keyUUID") String keyUUID) {
        String filename = "Bao_No.xlsx";
        InputStreamResource file = new InputStreamResource(syntheticService.exportDebitNote(voucherTypeNo,keyUUID));

        return ResponseEntity
            .ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
            .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
            .body(file);
    }
}
