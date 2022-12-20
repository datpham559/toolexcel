package com.softdreams.excel.web.rest;

import com.softdreams.excel.domain.Merchandise;
import com.softdreams.excel.helper.DmNccExcelHelper;
import com.softdreams.excel.helper.MerchandiseExcelHelper;
import com.softdreams.excel.message.ResponseMessage;
import com.softdreams.excel.repository.MerchandiseRepository;
import com.softdreams.excel.service.MerchandiseService;
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
 * REST controller for managing {@link com.softdreams.excel.domain.Merchandise}.
 */
@RestController
@RequestMapping("/api")
public class MerchandiseResource {

    private final Logger log = LoggerFactory.getLogger(MerchandiseResource.class);

    private static final String ENTITY_NAME = "merchandise";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MerchandiseService merchandiseService;

    private final MerchandiseRepository merchandiseRepository;

    public MerchandiseResource(MerchandiseService merchandiseService, MerchandiseRepository merchandiseRepository) {
        this.merchandiseService = merchandiseService;
        this.merchandiseRepository = merchandiseRepository;
    }

    /**
     * {@code POST  /merchandises} : Create a new merchandise.
     *
     * @param merchandise the merchandise to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new merchandise, or with status {@code 400 (Bad Request)} if the merchandise has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/merchandises")
    public ResponseEntity<Merchandise> createMerchandise(@RequestBody Merchandise merchandise) throws URISyntaxException {
        log.debug("REST request to save Merchandise : {}", merchandise);
        if (merchandise.getId() != null) {
            throw new BadRequestAlertException("A new merchandise cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Merchandise result = merchandiseService.save(merchandise);
        return ResponseEntity
            .created(new URI("/api/merchandises/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /merchandises/:id} : Updates an existing merchandise.
     *
     * @param id the id of the merchandise to save.
     * @param merchandise the merchandise to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated merchandise,
     * or with status {@code 400 (Bad Request)} if the merchandise is not valid,
     * or with status {@code 500 (Internal Server Error)} if the merchandise couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/merchandises/{id}")
    public ResponseEntity<Merchandise> updateMerchandise(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Merchandise merchandise
    ) throws URISyntaxException {
        log.debug("REST request to update Merchandise : {}, {}", id, merchandise);
        if (merchandise.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, merchandise.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!merchandiseRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Merchandise result = merchandiseService.update(merchandise);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, merchandise.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /merchandises/:id} : Partial updates given fields of an existing merchandise, field will ignore if it is null
     *
     * @param id the id of the merchandise to save.
     * @param merchandise the merchandise to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated merchandise,
     * or with status {@code 400 (Bad Request)} if the merchandise is not valid,
     * or with status {@code 404 (Not Found)} if the merchandise is not found,
     * or with status {@code 500 (Internal Server Error)} if the merchandise couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/merchandises/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Merchandise> partialUpdateMerchandise(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Merchandise merchandise
    ) throws URISyntaxException {
        log.debug("REST request to partial update Merchandise partially : {}, {}", id, merchandise);
        if (merchandise.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, merchandise.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!merchandiseRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Merchandise> result = merchandiseService.partialUpdate(merchandise);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, merchandise.getId().toString())
        );
    }

    /**
     * {@code GET  /merchandises} : get all the merchandises.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of merchandises in body.
     */
    @GetMapping("/merchandises")
    public List<Merchandise> getAllMerchandises() {
        log.debug("REST request to get all Merchandises");
        return merchandiseService.findAll();
    }

    /**
     * {@code GET  /merchandises/:id} : get the "id" merchandise.
     *
     * @param id the id of the merchandise to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the merchandise, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/merchandises/{id}")
    public ResponseEntity<Merchandise> getMerchandise(@PathVariable Long id) {
        log.debug("REST request to get Merchandise : {}", id);
        Optional<Merchandise> merchandise = merchandiseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(merchandise);
    }

    /**
     * {@code DELETE  /merchandises/:id} : delete the "id" merchandise.
     *
     * @param id the id of the merchandise to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/merchandises/{id}")
    public ResponseEntity<Void> deleteMerchandise(@PathVariable Long id) {
        log.debug("REST request to delete Merchandise : {}", id);
        merchandiseService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    @PostMapping("/import-merchandise")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (MerchandiseExcelHelper.hasExcelFormat(file)) {
            try {
                merchandiseService.saveToMerchandise(file);

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
