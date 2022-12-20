package com.softdreams.excel.web.rest;

import com.softdreams.excel.domain.BeginningInventory;
import com.softdreams.excel.helper.BankBalanceExcelHelper;
import com.softdreams.excel.helper.BeginningInventoryExcelHelper;
import com.softdreams.excel.message.ResponseMessage;
import com.softdreams.excel.repository.BeginningInventoryRepository;
import com.softdreams.excel.service.BeginningInventoryService;
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
 * REST controller for managing {@link com.softdreams.excel.domain.BeginningInventory}.
 */
@RestController
@RequestMapping("/api")
public class BeginningInventoryResource {

    private final Logger log = LoggerFactory.getLogger(BeginningInventoryResource.class);

    private static final String ENTITY_NAME = "beginningInventory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BeginningInventoryService beginningInventoryService;

    private final BeginningInventoryRepository beginningInventoryRepository;

    public BeginningInventoryResource(
        BeginningInventoryService beginningInventoryService,
        BeginningInventoryRepository beginningInventoryRepository
    ) {
        this.beginningInventoryService = beginningInventoryService;
        this.beginningInventoryRepository = beginningInventoryRepository;
    }

    /**
     * {@code POST  /beginning-inventories} : Create a new beginningInventory.
     *
     * @param beginningInventory the beginningInventory to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new beginningInventory, or with status {@code 400 (Bad Request)} if the beginningInventory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/beginning-inventories")
    public ResponseEntity<BeginningInventory> createBeginningInventory(@RequestBody BeginningInventory beginningInventory)
        throws URISyntaxException {
        log.debug("REST request to save BeginningInventory : {}", beginningInventory);
        if (beginningInventory.getId() != null) {
            throw new BadRequestAlertException("A new beginningInventory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BeginningInventory result = beginningInventoryService.save(beginningInventory);
        return ResponseEntity
            .created(new URI("/api/beginning-inventories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /beginning-inventories/:id} : Updates an existing beginningInventory.
     *
     * @param id the id of the beginningInventory to save.
     * @param beginningInventory the beginningInventory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated beginningInventory,
     * or with status {@code 400 (Bad Request)} if the beginningInventory is not valid,
     * or with status {@code 500 (Internal Server Error)} if the beginningInventory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/beginning-inventories/{id}")
    public ResponseEntity<BeginningInventory> updateBeginningInventory(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BeginningInventory beginningInventory
    ) throws URISyntaxException {
        log.debug("REST request to update BeginningInventory : {}, {}", id, beginningInventory);
        if (beginningInventory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, beginningInventory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!beginningInventoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BeginningInventory result = beginningInventoryService.update(beginningInventory);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, beginningInventory.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /beginning-inventories/:id} : Partial updates given fields of an existing beginningInventory, field will ignore if it is null
     *
     * @param id the id of the beginningInventory to save.
     * @param beginningInventory the beginningInventory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated beginningInventory,
     * or with status {@code 400 (Bad Request)} if the beginningInventory is not valid,
     * or with status {@code 404 (Not Found)} if the beginningInventory is not found,
     * or with status {@code 500 (Internal Server Error)} if the beginningInventory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/beginning-inventories/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BeginningInventory> partialUpdateBeginningInventory(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BeginningInventory beginningInventory
    ) throws URISyntaxException {
        log.debug("REST request to partial update BeginningInventory partially : {}, {}", id, beginningInventory);
        if (beginningInventory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, beginningInventory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!beginningInventoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BeginningInventory> result = beginningInventoryService.partialUpdate(beginningInventory);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, beginningInventory.getId().toString())
        );
    }

    /**
     * {@code GET  /beginning-inventories} : get all the beginningInventories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of beginningInventories in body.
     */
    @GetMapping("/beginning-inventories")
    public List<BeginningInventory> getAllBeginningInventories() {
        log.debug("REST request to get all BeginningInventories");
        return beginningInventoryService.findAll();
    }

    /**
     * {@code GET  /beginning-inventories/:id} : get the "id" beginningInventory.
     *
     * @param id the id of the beginningInventory to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the beginningInventory, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/beginning-inventories/{id}")
    public ResponseEntity<BeginningInventory> getBeginningInventory(@PathVariable Long id) {
        log.debug("REST request to get BeginningInventory : {}", id);
        Optional<BeginningInventory> beginningInventory = beginningInventoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(beginningInventory);
    }

    /**
     * {@code DELETE  /beginning-inventories/:id} : delete the "id" beginningInventory.
     *
     * @param id the id of the beginningInventory to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/beginning-inventories/{id}")
    public ResponseEntity<Void> deleteBeginningInventory(@PathVariable Long id) {
        log.debug("REST request to delete BeginningInventory : {}", id);
        beginningInventoryService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    @PostMapping("/import-beginning-inventory")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (BeginningInventoryExcelHelper.hasExcelFormat(file)) {
            try {
                beginningInventoryService.saveToBeginningInventory(file);

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
