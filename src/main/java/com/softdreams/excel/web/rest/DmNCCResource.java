package com.softdreams.excel.web.rest;

import com.softdreams.excel.domain.DmNCC;
import com.softdreams.excel.helper.DmNccExcelHelper;
import com.softdreams.excel.helper.ExcelHelper;
import com.softdreams.excel.message.ResponseMessage;
import com.softdreams.excel.repository.DmNCCRepository;
import com.softdreams.excel.service.DmNCCService;
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
 * REST controller for managing {@link com.softdreams.excel.domain.DmNCC}.
 */
@RestController
@RequestMapping("/api")
public class DmNCCResource {

    private final Logger log = LoggerFactory.getLogger(DmNCCResource.class);

    private static final String ENTITY_NAME = "dmNCC";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DmNCCService dmNCCService;

    private final DmNCCRepository dmNCCRepository;

    public DmNCCResource(DmNCCService dmNCCService, DmNCCRepository dmNCCRepository) {
        this.dmNCCService = dmNCCService;
        this.dmNCCRepository = dmNCCRepository;
    }

    /**
     * {@code POST  /dm-nccs} : Create a new dmNCC.
     *
     * @param dmNCC the dmNCC to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dmNCC, or with status {@code 400 (Bad Request)} if the dmNCC has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dm-nccs")
    public ResponseEntity<DmNCC> createDmNCC(@RequestBody DmNCC dmNCC) throws URISyntaxException {
        log.debug("REST request to save DmNCC : {}", dmNCC);
        if (dmNCC.getId() != null) {
            throw new BadRequestAlertException("A new dmNCC cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DmNCC result = dmNCCService.save(dmNCC);
        return ResponseEntity
            .created(new URI("/api/dm-nccs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dm-nccs/:id} : Updates an existing dmNCC.
     *
     * @param id the id of the dmNCC to save.
     * @param dmNCC the dmNCC to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dmNCC,
     * or with status {@code 400 (Bad Request)} if the dmNCC is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dmNCC couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dm-nccs/{id}")
    public ResponseEntity<DmNCC> updateDmNCC(@PathVariable(value = "id", required = false) final Long id, @RequestBody DmNCC dmNCC)
        throws URISyntaxException {
        log.debug("REST request to update DmNCC : {}, {}", id, dmNCC);
        if (dmNCC.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dmNCC.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dmNCCRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DmNCC result = dmNCCService.update(dmNCC);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, dmNCC.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /dm-nccs/:id} : Partial updates given fields of an existing dmNCC, field will ignore if it is null
     *
     * @param id the id of the dmNCC to save.
     * @param dmNCC the dmNCC to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dmNCC,
     * or with status {@code 400 (Bad Request)} if the dmNCC is not valid,
     * or with status {@code 404 (Not Found)} if the dmNCC is not found,
     * or with status {@code 500 (Internal Server Error)} if the dmNCC couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/dm-nccs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DmNCC> partialUpdateDmNCC(@PathVariable(value = "id", required = false) final Long id, @RequestBody DmNCC dmNCC)
        throws URISyntaxException {
        log.debug("REST request to partial update DmNCC partially : {}, {}", id, dmNCC);
        if (dmNCC.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dmNCC.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dmNCCRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DmNCC> result = dmNCCService.partialUpdate(dmNCC);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, dmNCC.getId().toString())
        );
    }

    /**
     * {@code GET  /dm-nccs} : get all the dmNCCS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dmNCCS in body.
     */
    @GetMapping("/dm-nccs")
    public List<DmNCC> getAllDmNCCS() {
        log.debug("REST request to get all DmNCCS");
        return dmNCCService.findAll();
    }

    /**
     * {@code GET  /dm-nccs/:id} : get the "id" dmNCC.
     *
     * @param id the id of the dmNCC to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dmNCC, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dm-nccs/{id}")
    public ResponseEntity<DmNCC> getDmNCC(@PathVariable Long id) {
        log.debug("REST request to get DmNCC : {}", id);
        Optional<DmNCC> dmNCC = dmNCCService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dmNCC);
    }

    /**
     * {@code DELETE  /dm-nccs/:id} : delete the "id" dmNCC.
     *
     * @param id the id of the dmNCC to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dm-nccs/{id}")
    public ResponseEntity<Void> deleteDmNCC(@PathVariable Long id) {
        log.debug("REST request to delete DmNCC : {}", id);
        dmNCCService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    @PostMapping("/import-ncc")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (DmNccExcelHelper.hasExcelFormat(file)) {
            try {
                dmNCCService.saveToDmNcc(file);

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
