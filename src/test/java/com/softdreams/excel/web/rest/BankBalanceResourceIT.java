package com.softdreams.excel.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.softdreams.excel.IntegrationTest;
import com.softdreams.excel.domain.BankBalance;
import com.softdreams.excel.repository.BankBalanceRepository;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link BankBalanceResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BankBalanceResourceIT {

    private static final String DEFAULT_BANK_ACCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_BANK_ACCOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BANK_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH = "BBBBBBBBBB";

    private static final Double DEFAULT_OPENING_BALANCE = 1D;
    private static final Double UPDATED_OPENING_BALANCE = 2D;

    private static final Double DEFAULT_DEBT_INCURRED = 1D;
    private static final Double UPDATED_DEBT_INCURRED = 2D;

    private static final Double DEFAULT_INCURRED = 1D;
    private static final Double UPDATED_INCURRED = 2D;

    private static final Double DEFAULT_ENDING_BALANCE = 1D;
    private static final Double UPDATED_ENDING_BALANCE = 2D;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_KEY_UUID = "AAAAAAAAAA";
    private static final String UPDATED_KEY_UUID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/bank-balances";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BankBalanceRepository bankBalanceRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBankBalanceMockMvc;

    private BankBalance bankBalance;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BankBalance createEntity(EntityManager em) {
        BankBalance bankBalance = new BankBalance()
            .bank_account(DEFAULT_BANK_ACCOUNT)
            .bank_name(DEFAULT_BANK_NAME)
            .branch(DEFAULT_BRANCH)
            .opening_balance(DEFAULT_OPENING_BALANCE)
            .debt_incurred(DEFAULT_DEBT_INCURRED)
            .incurred(DEFAULT_INCURRED)
            .ending_balance(DEFAULT_ENDING_BALANCE)
            .created_date(DEFAULT_CREATED_DATE)
            .keyUUID(DEFAULT_KEY_UUID);
        return bankBalance;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BankBalance createUpdatedEntity(EntityManager em) {
        BankBalance bankBalance = new BankBalance()
            .bank_account(UPDATED_BANK_ACCOUNT)
            .bank_name(UPDATED_BANK_NAME)
            .branch(UPDATED_BRANCH)
            .opening_balance(UPDATED_OPENING_BALANCE)
            .debt_incurred(UPDATED_DEBT_INCURRED)
            .incurred(UPDATED_INCURRED)
            .ending_balance(UPDATED_ENDING_BALANCE)
            .created_date(UPDATED_CREATED_DATE)
            .keyUUID(UPDATED_KEY_UUID);
        return bankBalance;
    }

    @BeforeEach
    public void initTest() {
        bankBalance = createEntity(em);
    }

    @Test
    @Transactional
    void createBankBalance() throws Exception {
        int databaseSizeBeforeCreate = bankBalanceRepository.findAll().size();
        // Create the BankBalance
        restBankBalanceMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bankBalance)))
            .andExpect(status().isCreated());

        // Validate the BankBalance in the database
        List<BankBalance> bankBalanceList = bankBalanceRepository.findAll();
        assertThat(bankBalanceList).hasSize(databaseSizeBeforeCreate + 1);
        BankBalance testBankBalance = bankBalanceList.get(bankBalanceList.size() - 1);
        assertThat(testBankBalance.getBank_account()).isEqualTo(DEFAULT_BANK_ACCOUNT);
        assertThat(testBankBalance.getBank_name()).isEqualTo(DEFAULT_BANK_NAME);
        assertThat(testBankBalance.getBranch()).isEqualTo(DEFAULT_BRANCH);
        assertThat(testBankBalance.getOpening_balance()).isEqualTo(DEFAULT_OPENING_BALANCE);
        assertThat(testBankBalance.getDebt_incurred()).isEqualTo(DEFAULT_DEBT_INCURRED);
        assertThat(testBankBalance.getIncurred()).isEqualTo(DEFAULT_INCURRED);
        assertThat(testBankBalance.getEnding_balance()).isEqualTo(DEFAULT_ENDING_BALANCE);
        assertThat(testBankBalance.getCreated_date()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testBankBalance.getKeyUUID()).isEqualTo(DEFAULT_KEY_UUID);
    }

    @Test
    @Transactional
    void createBankBalanceWithExistingId() throws Exception {
        // Create the BankBalance with an existing ID
        bankBalance.setId(1L);

        int databaseSizeBeforeCreate = bankBalanceRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBankBalanceMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bankBalance)))
            .andExpect(status().isBadRequest());

        // Validate the BankBalance in the database
        List<BankBalance> bankBalanceList = bankBalanceRepository.findAll();
        assertThat(bankBalanceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBankBalances() throws Exception {
        // Initialize the database
        bankBalanceRepository.saveAndFlush(bankBalance);

        // Get all the bankBalanceList
        restBankBalanceMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bankBalance.getId().intValue())))
            .andExpect(jsonPath("$.[*].bank_account").value(hasItem(DEFAULT_BANK_ACCOUNT)))
            .andExpect(jsonPath("$.[*].bank_name").value(hasItem(DEFAULT_BANK_NAME)))
            .andExpect(jsonPath("$.[*].branch").value(hasItem(DEFAULT_BRANCH)))
            .andExpect(jsonPath("$.[*].opening_balance").value(hasItem(DEFAULT_OPENING_BALANCE.doubleValue())))
            .andExpect(jsonPath("$.[*].debt_incurred").value(hasItem(DEFAULT_DEBT_INCURRED.doubleValue())))
            .andExpect(jsonPath("$.[*].incurred").value(hasItem(DEFAULT_INCURRED.doubleValue())))
            .andExpect(jsonPath("$.[*].ending_balance").value(hasItem(DEFAULT_ENDING_BALANCE.doubleValue())))
            .andExpect(jsonPath("$.[*].created_date").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].keyUUID").value(hasItem(DEFAULT_KEY_UUID)));
    }

    @Test
    @Transactional
    void getBankBalance() throws Exception {
        // Initialize the database
        bankBalanceRepository.saveAndFlush(bankBalance);

        // Get the bankBalance
        restBankBalanceMockMvc
            .perform(get(ENTITY_API_URL_ID, bankBalance.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bankBalance.getId().intValue()))
            .andExpect(jsonPath("$.bank_account").value(DEFAULT_BANK_ACCOUNT))
            .andExpect(jsonPath("$.bank_name").value(DEFAULT_BANK_NAME))
            .andExpect(jsonPath("$.branch").value(DEFAULT_BRANCH))
            .andExpect(jsonPath("$.opening_balance").value(DEFAULT_OPENING_BALANCE.doubleValue()))
            .andExpect(jsonPath("$.debt_incurred").value(DEFAULT_DEBT_INCURRED.doubleValue()))
            .andExpect(jsonPath("$.incurred").value(DEFAULT_INCURRED.doubleValue()))
            .andExpect(jsonPath("$.ending_balance").value(DEFAULT_ENDING_BALANCE.doubleValue()))
            .andExpect(jsonPath("$.created_date").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.keyUUID").value(DEFAULT_KEY_UUID));
    }

    @Test
    @Transactional
    void getNonExistingBankBalance() throws Exception {
        // Get the bankBalance
        restBankBalanceMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingBankBalance() throws Exception {
        // Initialize the database
        bankBalanceRepository.saveAndFlush(bankBalance);

        int databaseSizeBeforeUpdate = bankBalanceRepository.findAll().size();

        // Update the bankBalance
        BankBalance updatedBankBalance = bankBalanceRepository.findById(bankBalance.getId()).get();
        // Disconnect from session so that the updates on updatedBankBalance are not directly saved in db
        em.detach(updatedBankBalance);
        updatedBankBalance
            .bank_account(UPDATED_BANK_ACCOUNT)
            .bank_name(UPDATED_BANK_NAME)
            .branch(UPDATED_BRANCH)
            .opening_balance(UPDATED_OPENING_BALANCE)
            .debt_incurred(UPDATED_DEBT_INCURRED)
            .incurred(UPDATED_INCURRED)
            .ending_balance(UPDATED_ENDING_BALANCE)
            .created_date(UPDATED_CREATED_DATE)
            .keyUUID(UPDATED_KEY_UUID);

        restBankBalanceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedBankBalance.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedBankBalance))
            )
            .andExpect(status().isOk());

        // Validate the BankBalance in the database
        List<BankBalance> bankBalanceList = bankBalanceRepository.findAll();
        assertThat(bankBalanceList).hasSize(databaseSizeBeforeUpdate);
        BankBalance testBankBalance = bankBalanceList.get(bankBalanceList.size() - 1);
        assertThat(testBankBalance.getBank_account()).isEqualTo(UPDATED_BANK_ACCOUNT);
        assertThat(testBankBalance.getBank_name()).isEqualTo(UPDATED_BANK_NAME);
        assertThat(testBankBalance.getBranch()).isEqualTo(UPDATED_BRANCH);
        assertThat(testBankBalance.getOpening_balance()).isEqualTo(UPDATED_OPENING_BALANCE);
        assertThat(testBankBalance.getDebt_incurred()).isEqualTo(UPDATED_DEBT_INCURRED);
        assertThat(testBankBalance.getIncurred()).isEqualTo(UPDATED_INCURRED);
        assertThat(testBankBalance.getEnding_balance()).isEqualTo(UPDATED_ENDING_BALANCE);
        assertThat(testBankBalance.getCreated_date()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBankBalance.getKeyUUID()).isEqualTo(UPDATED_KEY_UUID);
    }

    @Test
    @Transactional
    void putNonExistingBankBalance() throws Exception {
        int databaseSizeBeforeUpdate = bankBalanceRepository.findAll().size();
        bankBalance.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBankBalanceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bankBalance.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bankBalance))
            )
            .andExpect(status().isBadRequest());

        // Validate the BankBalance in the database
        List<BankBalance> bankBalanceList = bankBalanceRepository.findAll();
        assertThat(bankBalanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBankBalance() throws Exception {
        int databaseSizeBeforeUpdate = bankBalanceRepository.findAll().size();
        bankBalance.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBankBalanceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bankBalance))
            )
            .andExpect(status().isBadRequest());

        // Validate the BankBalance in the database
        List<BankBalance> bankBalanceList = bankBalanceRepository.findAll();
        assertThat(bankBalanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBankBalance() throws Exception {
        int databaseSizeBeforeUpdate = bankBalanceRepository.findAll().size();
        bankBalance.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBankBalanceMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bankBalance)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the BankBalance in the database
        List<BankBalance> bankBalanceList = bankBalanceRepository.findAll();
        assertThat(bankBalanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBankBalanceWithPatch() throws Exception {
        // Initialize the database
        bankBalanceRepository.saveAndFlush(bankBalance);

        int databaseSizeBeforeUpdate = bankBalanceRepository.findAll().size();

        // Update the bankBalance using partial update
        BankBalance partialUpdatedBankBalance = new BankBalance();
        partialUpdatedBankBalance.setId(bankBalance.getId());

        partialUpdatedBankBalance.opening_balance(UPDATED_OPENING_BALANCE).created_date(UPDATED_CREATED_DATE);

        restBankBalanceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBankBalance.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBankBalance))
            )
            .andExpect(status().isOk());

        // Validate the BankBalance in the database
        List<BankBalance> bankBalanceList = bankBalanceRepository.findAll();
        assertThat(bankBalanceList).hasSize(databaseSizeBeforeUpdate);
        BankBalance testBankBalance = bankBalanceList.get(bankBalanceList.size() - 1);
        assertThat(testBankBalance.getBank_account()).isEqualTo(DEFAULT_BANK_ACCOUNT);
        assertThat(testBankBalance.getBank_name()).isEqualTo(DEFAULT_BANK_NAME);
        assertThat(testBankBalance.getBranch()).isEqualTo(DEFAULT_BRANCH);
        assertThat(testBankBalance.getOpening_balance()).isEqualTo(UPDATED_OPENING_BALANCE);
        assertThat(testBankBalance.getDebt_incurred()).isEqualTo(DEFAULT_DEBT_INCURRED);
        assertThat(testBankBalance.getIncurred()).isEqualTo(DEFAULT_INCURRED);
        assertThat(testBankBalance.getEnding_balance()).isEqualTo(DEFAULT_ENDING_BALANCE);
        assertThat(testBankBalance.getCreated_date()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBankBalance.getKeyUUID()).isEqualTo(DEFAULT_KEY_UUID);
    }

    @Test
    @Transactional
    void fullUpdateBankBalanceWithPatch() throws Exception {
        // Initialize the database
        bankBalanceRepository.saveAndFlush(bankBalance);

        int databaseSizeBeforeUpdate = bankBalanceRepository.findAll().size();

        // Update the bankBalance using partial update
        BankBalance partialUpdatedBankBalance = new BankBalance();
        partialUpdatedBankBalance.setId(bankBalance.getId());

        partialUpdatedBankBalance
            .bank_account(UPDATED_BANK_ACCOUNT)
            .bank_name(UPDATED_BANK_NAME)
            .branch(UPDATED_BRANCH)
            .opening_balance(UPDATED_OPENING_BALANCE)
            .debt_incurred(UPDATED_DEBT_INCURRED)
            .incurred(UPDATED_INCURRED)
            .ending_balance(UPDATED_ENDING_BALANCE)
            .created_date(UPDATED_CREATED_DATE)
            .keyUUID(UPDATED_KEY_UUID);

        restBankBalanceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBankBalance.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBankBalance))
            )
            .andExpect(status().isOk());

        // Validate the BankBalance in the database
        List<BankBalance> bankBalanceList = bankBalanceRepository.findAll();
        assertThat(bankBalanceList).hasSize(databaseSizeBeforeUpdate);
        BankBalance testBankBalance = bankBalanceList.get(bankBalanceList.size() - 1);
        assertThat(testBankBalance.getBank_account()).isEqualTo(UPDATED_BANK_ACCOUNT);
        assertThat(testBankBalance.getBank_name()).isEqualTo(UPDATED_BANK_NAME);
        assertThat(testBankBalance.getBranch()).isEqualTo(UPDATED_BRANCH);
        assertThat(testBankBalance.getOpening_balance()).isEqualTo(UPDATED_OPENING_BALANCE);
        assertThat(testBankBalance.getDebt_incurred()).isEqualTo(UPDATED_DEBT_INCURRED);
        assertThat(testBankBalance.getIncurred()).isEqualTo(UPDATED_INCURRED);
        assertThat(testBankBalance.getEnding_balance()).isEqualTo(UPDATED_ENDING_BALANCE);
        assertThat(testBankBalance.getCreated_date()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBankBalance.getKeyUUID()).isEqualTo(UPDATED_KEY_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingBankBalance() throws Exception {
        int databaseSizeBeforeUpdate = bankBalanceRepository.findAll().size();
        bankBalance.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBankBalanceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, bankBalance.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(bankBalance))
            )
            .andExpect(status().isBadRequest());

        // Validate the BankBalance in the database
        List<BankBalance> bankBalanceList = bankBalanceRepository.findAll();
        assertThat(bankBalanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBankBalance() throws Exception {
        int databaseSizeBeforeUpdate = bankBalanceRepository.findAll().size();
        bankBalance.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBankBalanceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(bankBalance))
            )
            .andExpect(status().isBadRequest());

        // Validate the BankBalance in the database
        List<BankBalance> bankBalanceList = bankBalanceRepository.findAll();
        assertThat(bankBalanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBankBalance() throws Exception {
        int databaseSizeBeforeUpdate = bankBalanceRepository.findAll().size();
        bankBalance.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBankBalanceMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(bankBalance))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BankBalance in the database
        List<BankBalance> bankBalanceList = bankBalanceRepository.findAll();
        assertThat(bankBalanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBankBalance() throws Exception {
        // Initialize the database
        bankBalanceRepository.saveAndFlush(bankBalance);

        int databaseSizeBeforeDelete = bankBalanceRepository.findAll().size();

        // Delete the bankBalance
        restBankBalanceMockMvc
            .perform(delete(ENTITY_API_URL_ID, bankBalance.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BankBalance> bankBalanceList = bankBalanceRepository.findAll();
        assertThat(bankBalanceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
