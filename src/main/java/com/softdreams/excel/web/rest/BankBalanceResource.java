package com.softdreams.excel.web.rest;

import com.softdreams.excel.domain.BankBalance;
import com.softdreams.excel.helper.BankBalanceExcelHelper;
import com.softdreams.excel.helper.ExcelHelper;
import com.softdreams.excel.message.ResponseMessage;
import com.softdreams.excel.repository.BankBalanceRepository;
import com.softdreams.excel.service.BankBalanceService;
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
 * REST controller for managing {@link com.softdreams.excel.domain.BankBalance}.
 */
@RestController
@RequestMapping("/api")
public class BankBalanceResource {

    private final Logger log = LoggerFactory.getLogger(BankBalanceResource.class);

    private static final String ENTITY_NAME = "bankBalance";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BankBalanceService bankBalanceService;

    private final BankBalanceRepository bankBalanceRepository;

    public BankBalanceResource(BankBalanceService bankBalanceService, BankBalanceRepository bankBalanceRepository) {
        this.bankBalanceService = bankBalanceService;
        this.bankBalanceRepository = bankBalanceRepository;
    }

    /**
     * {@code POST  /bank-balances} : Create a new bankBalance.
     *
     * @param bankBalance the bankBalance to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bankBalance, or with status {@code 400 (Bad Request)} if the bankBalance has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bank-balances")
    public ResponseEntity<BankBalance> createBankBalance(@RequestBody BankBalance bankBalance) throws URISyntaxException {
        log.debug("REST request to save BankBalance : {}", bankBalance);
        if (bankBalance.getId() != null) {
            throw new BadRequestAlertException("A new bankBalance cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BankBalance result = bankBalanceService.save(bankBalance);
        return ResponseEntity
            .created(new URI("/api/bank-balances/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bank-balances/:id} : Updates an existing bankBalance.
     *
     * @param id the id of the bankBalance to save.
     * @param bankBalance the bankBalance to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bankBalance,
     * or with status {@code 400 (Bad Request)} if the bankBalance is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bankBalance couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bank-balances/{id}")
    public ResponseEntity<BankBalance> updateBankBalance(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BankBalance bankBalance
    ) throws URISyntaxException {
        log.debug("REST request to update BankBalance : {}, {}", id, bankBalance);
        if (bankBalance.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bankBalance.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bankBalanceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BankBalance result = bankBalanceService.update(bankBalance);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bankBalance.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bank-balances/:id} : Partial updates given fields of an existing bankBalance, field will ignore if it is null
     *
     * @param id the id of the bankBalance to save.
     * @param bankBalance the bankBalance to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bankBalance,
     * or with status {@code 400 (Bad Request)} if the bankBalance is not valid,
     * or with status {@code 404 (Not Found)} if the bankBalance is not found,
     * or with status {@code 500 (Internal Server Error)} if the bankBalance couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bank-balances/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BankBalance> partialUpdateBankBalance(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BankBalance bankBalance
    ) throws URISyntaxException {
        log.debug("REST request to partial update BankBalance partially : {}, {}", id, bankBalance);
        if (bankBalance.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bankBalance.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bankBalanceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BankBalance> result = bankBalanceService.partialUpdate(bankBalance);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bankBalance.getId().toString())
        );
    }

    /**
     * {@code GET  /bank-balances} : get all the bankBalances.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bankBalances in body.
     */
    @GetMapping("/bank-balances")
    public List<BankBalance> getAllBankBalances() {
        log.debug("REST request to get all BankBalances");
        return bankBalanceService.findAll();
    }

    /**
     * {@code GET  /bank-balances/:id} : get the "id" bankBalance.
     *
     * @param id the id of the bankBalance to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bankBalance, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bank-balances/{id}")
    public ResponseEntity<BankBalance> getBankBalance(@PathVariable Long id) {
        log.debug("REST request to get BankBalance : {}", id);
        Optional<BankBalance> bankBalance = bankBalanceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bankBalance);
    }

    /**
     * {@code DELETE  /bank-balances/:id} : delete the "id" bankBalance.
     *
     * @param id the id of the bankBalance to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bank-balances/{id}")
    public ResponseEntity<Void> deleteBankBalance(@PathVariable Long id) {
        log.debug("REST request to delete BankBalance : {}", id);
        bankBalanceService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    @PostMapping("/import-bank-balance")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (BankBalanceExcelHelper.hasExcelFormat(file)) {
            try {
                bankBalanceService.saveToBankBlance(file);

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
