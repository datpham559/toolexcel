package com.softdreams.excel.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.softdreams.excel.IntegrationTest;
import com.softdreams.excel.domain.BeginningInventory;
import com.softdreams.excel.repository.BeginningInventoryRepository;
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
 * Integration tests for the {@link BeginningInventoryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BeginningInventoryResourceIT {

    private static final String DEFAULT_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NAME = "BBBBBBBBBB";

    private static final Double DEFAULT_FIRST_DEBT = 1D;
    private static final Double UPDATED_FIRST_DEBT = 2D;

    private static final Double DEFAULT_FIRST_YES = 1D;
    private static final Double UPDATED_FIRST_YES = 2D;

    private static final Double DEFAULT_DEBT_ARISES = 1D;
    private static final Double UPDATED_DEBT_ARISES = 2D;

    private static final Double DEFAULT_ARISES_YES = 1D;
    private static final Double UPDATED_ARISES_YES = 2D;

    private static final Double DEFAULT_ACCUMULATED_DEBT = 1D;
    private static final Double UPDATED_ACCUMULATED_DEBT = 2D;

    private static final Double DEFAULT_ACCUMULATED_YES = 1D;
    private static final Double UPDATED_ACCUMULATED_YES = 2D;

    private static final Double DEFAULT_LAST_DEBT = 1D;
    private static final Double UPDATED_LAST_DEBT = 2D;

    private static final Double DEFAULT_LAST_YES = 1D;
    private static final Double UPDATED_LAST_YES = 2D;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_KEY_UUID = "AAAAAAAAAA";
    private static final String UPDATED_KEY_UUID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/beginning-inventories";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BeginningInventoryRepository beginningInventoryRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBeginningInventoryMockMvc;

    private BeginningInventory beginningInventory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BeginningInventory createEntity(EntityManager em) {
        BeginningInventory beginningInventory = new BeginningInventory()
            .account_number(DEFAULT_ACCOUNT_NUMBER)
            .account_name(DEFAULT_ACCOUNT_NAME)
            .first_debt(DEFAULT_FIRST_DEBT)
            .first_yes(DEFAULT_FIRST_YES)
            .debt_arises(DEFAULT_DEBT_ARISES)
            .arises_yes(DEFAULT_ARISES_YES)
            .accumulated_debt(DEFAULT_ACCUMULATED_DEBT)
            .accumulated_yes(DEFAULT_ACCUMULATED_YES)
            .last_debt(DEFAULT_LAST_DEBT)
            .last_yes(DEFAULT_LAST_YES)
            .created_date(DEFAULT_CREATED_DATE)
            .keyUUID(DEFAULT_KEY_UUID);
        return beginningInventory;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BeginningInventory createUpdatedEntity(EntityManager em) {
        BeginningInventory beginningInventory = new BeginningInventory()
            .account_number(UPDATED_ACCOUNT_NUMBER)
            .account_name(UPDATED_ACCOUNT_NAME)
            .first_debt(UPDATED_FIRST_DEBT)
            .first_yes(UPDATED_FIRST_YES)
            .debt_arises(UPDATED_DEBT_ARISES)
            .arises_yes(UPDATED_ARISES_YES)
            .accumulated_debt(UPDATED_ACCUMULATED_DEBT)
            .accumulated_yes(UPDATED_ACCUMULATED_YES)
            .last_debt(UPDATED_LAST_DEBT)
            .last_yes(UPDATED_LAST_YES)
            .created_date(UPDATED_CREATED_DATE)
            .keyUUID(UPDATED_KEY_UUID);
        return beginningInventory;
    }

    @BeforeEach
    public void initTest() {
        beginningInventory = createEntity(em);
    }

    @Test
    @Transactional
    void createBeginningInventory() throws Exception {
        int databaseSizeBeforeCreate = beginningInventoryRepository.findAll().size();
        // Create the BeginningInventory
        restBeginningInventoryMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(beginningInventory))
            )
            .andExpect(status().isCreated());

        // Validate the BeginningInventory in the database
        List<BeginningInventory> beginningInventoryList = beginningInventoryRepository.findAll();
        assertThat(beginningInventoryList).hasSize(databaseSizeBeforeCreate + 1);
        BeginningInventory testBeginningInventory = beginningInventoryList.get(beginningInventoryList.size() - 1);
        assertThat(testBeginningInventory.getAccount_number()).isEqualTo(DEFAULT_ACCOUNT_NUMBER);
        assertThat(testBeginningInventory.getAccount_name()).isEqualTo(DEFAULT_ACCOUNT_NAME);
        assertThat(testBeginningInventory.getFirst_debt()).isEqualTo(DEFAULT_FIRST_DEBT);
        assertThat(testBeginningInventory.getFirst_yes()).isEqualTo(DEFAULT_FIRST_YES);
        assertThat(testBeginningInventory.getDebt_arises()).isEqualTo(DEFAULT_DEBT_ARISES);
        assertThat(testBeginningInventory.getArises_yes()).isEqualTo(DEFAULT_ARISES_YES);
        assertThat(testBeginningInventory.getAccumulated_debt()).isEqualTo(DEFAULT_ACCUMULATED_DEBT);
        assertThat(testBeginningInventory.getAccumulated_yes()).isEqualTo(DEFAULT_ACCUMULATED_YES);
        assertThat(testBeginningInventory.getLast_debt()).isEqualTo(DEFAULT_LAST_DEBT);
        assertThat(testBeginningInventory.getLast_yes()).isEqualTo(DEFAULT_LAST_YES);
        assertThat(testBeginningInventory.getCreated_date()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testBeginningInventory.getKeyUUID()).isEqualTo(DEFAULT_KEY_UUID);
    }

    @Test
    @Transactional
    void createBeginningInventoryWithExistingId() throws Exception {
        // Create the BeginningInventory with an existing ID
        beginningInventory.setId(1L);

        int databaseSizeBeforeCreate = beginningInventoryRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBeginningInventoryMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(beginningInventory))
            )
            .andExpect(status().isBadRequest());

        // Validate the BeginningInventory in the database
        List<BeginningInventory> beginningInventoryList = beginningInventoryRepository.findAll();
        assertThat(beginningInventoryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBeginningInventories() throws Exception {
        // Initialize the database
        beginningInventoryRepository.saveAndFlush(beginningInventory);

        // Get all the beginningInventoryList
        restBeginningInventoryMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(beginningInventory.getId().intValue())))
            .andExpect(jsonPath("$.[*].account_number").value(hasItem(DEFAULT_ACCOUNT_NUMBER)))
            .andExpect(jsonPath("$.[*].account_name").value(hasItem(DEFAULT_ACCOUNT_NAME)))
            .andExpect(jsonPath("$.[*].first_debt").value(hasItem(DEFAULT_FIRST_DEBT.doubleValue())))
            .andExpect(jsonPath("$.[*].first_yes").value(hasItem(DEFAULT_FIRST_YES.doubleValue())))
            .andExpect(jsonPath("$.[*].debt_arises").value(hasItem(DEFAULT_DEBT_ARISES.doubleValue())))
            .andExpect(jsonPath("$.[*].arises_yes").value(hasItem(DEFAULT_ARISES_YES.doubleValue())))
            .andExpect(jsonPath("$.[*].accumulated_debt").value(hasItem(DEFAULT_ACCUMULATED_DEBT.doubleValue())))
            .andExpect(jsonPath("$.[*].accumulated_yes").value(hasItem(DEFAULT_ACCUMULATED_YES.doubleValue())))
            .andExpect(jsonPath("$.[*].last_debt").value(hasItem(DEFAULT_LAST_DEBT.doubleValue())))
            .andExpect(jsonPath("$.[*].last_yes").value(hasItem(DEFAULT_LAST_YES.doubleValue())))
            .andExpect(jsonPath("$.[*].created_date").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].keyUUID").value(hasItem(DEFAULT_KEY_UUID)));
    }

    @Test
    @Transactional
    void getBeginningInventory() throws Exception {
        // Initialize the database
        beginningInventoryRepository.saveAndFlush(beginningInventory);

        // Get the beginningInventory
        restBeginningInventoryMockMvc
            .perform(get(ENTITY_API_URL_ID, beginningInventory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(beginningInventory.getId().intValue()))
            .andExpect(jsonPath("$.account_number").value(DEFAULT_ACCOUNT_NUMBER))
            .andExpect(jsonPath("$.account_name").value(DEFAULT_ACCOUNT_NAME))
            .andExpect(jsonPath("$.first_debt").value(DEFAULT_FIRST_DEBT.doubleValue()))
            .andExpect(jsonPath("$.first_yes").value(DEFAULT_FIRST_YES.doubleValue()))
            .andExpect(jsonPath("$.debt_arises").value(DEFAULT_DEBT_ARISES.doubleValue()))
            .andExpect(jsonPath("$.arises_yes").value(DEFAULT_ARISES_YES.doubleValue()))
            .andExpect(jsonPath("$.accumulated_debt").value(DEFAULT_ACCUMULATED_DEBT.doubleValue()))
            .andExpect(jsonPath("$.accumulated_yes").value(DEFAULT_ACCUMULATED_YES.doubleValue()))
            .andExpect(jsonPath("$.last_debt").value(DEFAULT_LAST_DEBT.doubleValue()))
            .andExpect(jsonPath("$.last_yes").value(DEFAULT_LAST_YES.doubleValue()))
            .andExpect(jsonPath("$.created_date").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.keyUUID").value(DEFAULT_KEY_UUID));
    }

    @Test
    @Transactional
    void getNonExistingBeginningInventory() throws Exception {
        // Get the beginningInventory
        restBeginningInventoryMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingBeginningInventory() throws Exception {
        // Initialize the database
        beginningInventoryRepository.saveAndFlush(beginningInventory);

        int databaseSizeBeforeUpdate = beginningInventoryRepository.findAll().size();

        // Update the beginningInventory
        BeginningInventory updatedBeginningInventory = beginningInventoryRepository.findById(beginningInventory.getId()).get();
        // Disconnect from session so that the updates on updatedBeginningInventory are not directly saved in db
        em.detach(updatedBeginningInventory);
        updatedBeginningInventory
            .account_number(UPDATED_ACCOUNT_NUMBER)
            .account_name(UPDATED_ACCOUNT_NAME)
            .first_debt(UPDATED_FIRST_DEBT)
            .first_yes(UPDATED_FIRST_YES)
            .debt_arises(UPDATED_DEBT_ARISES)
            .arises_yes(UPDATED_ARISES_YES)
            .accumulated_debt(UPDATED_ACCUMULATED_DEBT)
            .accumulated_yes(UPDATED_ACCUMULATED_YES)
            .last_debt(UPDATED_LAST_DEBT)
            .last_yes(UPDATED_LAST_YES)
            .created_date(UPDATED_CREATED_DATE)
            .keyUUID(UPDATED_KEY_UUID);

        restBeginningInventoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedBeginningInventory.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedBeginningInventory))
            )
            .andExpect(status().isOk());

        // Validate the BeginningInventory in the database
        List<BeginningInventory> beginningInventoryList = beginningInventoryRepository.findAll();
        assertThat(beginningInventoryList).hasSize(databaseSizeBeforeUpdate);
        BeginningInventory testBeginningInventory = beginningInventoryList.get(beginningInventoryList.size() - 1);
        assertThat(testBeginningInventory.getAccount_number()).isEqualTo(UPDATED_ACCOUNT_NUMBER);
        assertThat(testBeginningInventory.getAccount_name()).isEqualTo(UPDATED_ACCOUNT_NAME);
        assertThat(testBeginningInventory.getFirst_debt()).isEqualTo(UPDATED_FIRST_DEBT);
        assertThat(testBeginningInventory.getFirst_yes()).isEqualTo(UPDATED_FIRST_YES);
        assertThat(testBeginningInventory.getDebt_arises()).isEqualTo(UPDATED_DEBT_ARISES);
        assertThat(testBeginningInventory.getArises_yes()).isEqualTo(UPDATED_ARISES_YES);
        assertThat(testBeginningInventory.getAccumulated_debt()).isEqualTo(UPDATED_ACCUMULATED_DEBT);
        assertThat(testBeginningInventory.getAccumulated_yes()).isEqualTo(UPDATED_ACCUMULATED_YES);
        assertThat(testBeginningInventory.getLast_debt()).isEqualTo(UPDATED_LAST_DEBT);
        assertThat(testBeginningInventory.getLast_yes()).isEqualTo(UPDATED_LAST_YES);
        assertThat(testBeginningInventory.getCreated_date()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBeginningInventory.getKeyUUID()).isEqualTo(UPDATED_KEY_UUID);
    }

    @Test
    @Transactional
    void putNonExistingBeginningInventory() throws Exception {
        int databaseSizeBeforeUpdate = beginningInventoryRepository.findAll().size();
        beginningInventory.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBeginningInventoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, beginningInventory.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(beginningInventory))
            )
            .andExpect(status().isBadRequest());

        // Validate the BeginningInventory in the database
        List<BeginningInventory> beginningInventoryList = beginningInventoryRepository.findAll();
        assertThat(beginningInventoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBeginningInventory() throws Exception {
        int databaseSizeBeforeUpdate = beginningInventoryRepository.findAll().size();
        beginningInventory.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBeginningInventoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(beginningInventory))
            )
            .andExpect(status().isBadRequest());

        // Validate the BeginningInventory in the database
        List<BeginningInventory> beginningInventoryList = beginningInventoryRepository.findAll();
        assertThat(beginningInventoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBeginningInventory() throws Exception {
        int databaseSizeBeforeUpdate = beginningInventoryRepository.findAll().size();
        beginningInventory.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBeginningInventoryMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(beginningInventory))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BeginningInventory in the database
        List<BeginningInventory> beginningInventoryList = beginningInventoryRepository.findAll();
        assertThat(beginningInventoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBeginningInventoryWithPatch() throws Exception {
        // Initialize the database
        beginningInventoryRepository.saveAndFlush(beginningInventory);

        int databaseSizeBeforeUpdate = beginningInventoryRepository.findAll().size();

        // Update the beginningInventory using partial update
        BeginningInventory partialUpdatedBeginningInventory = new BeginningInventory();
        partialUpdatedBeginningInventory.setId(beginningInventory.getId());

        partialUpdatedBeginningInventory
            .account_number(UPDATED_ACCOUNT_NUMBER)
            .first_debt(UPDATED_FIRST_DEBT)
            .first_yes(UPDATED_FIRST_YES)
            .debt_arises(UPDATED_DEBT_ARISES)
            .accumulated_debt(UPDATED_ACCUMULATED_DEBT)
            .accumulated_yes(UPDATED_ACCUMULATED_YES)
            .last_yes(UPDATED_LAST_YES)
            .created_date(UPDATED_CREATED_DATE)
            .keyUUID(UPDATED_KEY_UUID);

        restBeginningInventoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBeginningInventory.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBeginningInventory))
            )
            .andExpect(status().isOk());

        // Validate the BeginningInventory in the database
        List<BeginningInventory> beginningInventoryList = beginningInventoryRepository.findAll();
        assertThat(beginningInventoryList).hasSize(databaseSizeBeforeUpdate);
        BeginningInventory testBeginningInventory = beginningInventoryList.get(beginningInventoryList.size() - 1);
        assertThat(testBeginningInventory.getAccount_number()).isEqualTo(UPDATED_ACCOUNT_NUMBER);
        assertThat(testBeginningInventory.getAccount_name()).isEqualTo(DEFAULT_ACCOUNT_NAME);
        assertThat(testBeginningInventory.getFirst_debt()).isEqualTo(UPDATED_FIRST_DEBT);
        assertThat(testBeginningInventory.getFirst_yes()).isEqualTo(UPDATED_FIRST_YES);
        assertThat(testBeginningInventory.getDebt_arises()).isEqualTo(UPDATED_DEBT_ARISES);
        assertThat(testBeginningInventory.getArises_yes()).isEqualTo(DEFAULT_ARISES_YES);
        assertThat(testBeginningInventory.getAccumulated_debt()).isEqualTo(UPDATED_ACCUMULATED_DEBT);
        assertThat(testBeginningInventory.getAccumulated_yes()).isEqualTo(UPDATED_ACCUMULATED_YES);
        assertThat(testBeginningInventory.getLast_debt()).isEqualTo(DEFAULT_LAST_DEBT);
        assertThat(testBeginningInventory.getLast_yes()).isEqualTo(UPDATED_LAST_YES);
        assertThat(testBeginningInventory.getCreated_date()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBeginningInventory.getKeyUUID()).isEqualTo(UPDATED_KEY_UUID);
    }

    @Test
    @Transactional
    void fullUpdateBeginningInventoryWithPatch() throws Exception {
        // Initialize the database
        beginningInventoryRepository.saveAndFlush(beginningInventory);

        int databaseSizeBeforeUpdate = beginningInventoryRepository.findAll().size();

        // Update the beginningInventory using partial update
        BeginningInventory partialUpdatedBeginningInventory = new BeginningInventory();
        partialUpdatedBeginningInventory.setId(beginningInventory.getId());

        partialUpdatedBeginningInventory
            .account_number(UPDATED_ACCOUNT_NUMBER)
            .account_name(UPDATED_ACCOUNT_NAME)
            .first_debt(UPDATED_FIRST_DEBT)
            .first_yes(UPDATED_FIRST_YES)
            .debt_arises(UPDATED_DEBT_ARISES)
            .arises_yes(UPDATED_ARISES_YES)
            .accumulated_debt(UPDATED_ACCUMULATED_DEBT)
            .accumulated_yes(UPDATED_ACCUMULATED_YES)
            .last_debt(UPDATED_LAST_DEBT)
            .last_yes(UPDATED_LAST_YES)
            .created_date(UPDATED_CREATED_DATE)
            .keyUUID(UPDATED_KEY_UUID);

        restBeginningInventoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBeginningInventory.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBeginningInventory))
            )
            .andExpect(status().isOk());

        // Validate the BeginningInventory in the database
        List<BeginningInventory> beginningInventoryList = beginningInventoryRepository.findAll();
        assertThat(beginningInventoryList).hasSize(databaseSizeBeforeUpdate);
        BeginningInventory testBeginningInventory = beginningInventoryList.get(beginningInventoryList.size() - 1);
        assertThat(testBeginningInventory.getAccount_number()).isEqualTo(UPDATED_ACCOUNT_NUMBER);
        assertThat(testBeginningInventory.getAccount_name()).isEqualTo(UPDATED_ACCOUNT_NAME);
        assertThat(testBeginningInventory.getFirst_debt()).isEqualTo(UPDATED_FIRST_DEBT);
        assertThat(testBeginningInventory.getFirst_yes()).isEqualTo(UPDATED_FIRST_YES);
        assertThat(testBeginningInventory.getDebt_arises()).isEqualTo(UPDATED_DEBT_ARISES);
        assertThat(testBeginningInventory.getArises_yes()).isEqualTo(UPDATED_ARISES_YES);
        assertThat(testBeginningInventory.getAccumulated_debt()).isEqualTo(UPDATED_ACCUMULATED_DEBT);
        assertThat(testBeginningInventory.getAccumulated_yes()).isEqualTo(UPDATED_ACCUMULATED_YES);
        assertThat(testBeginningInventory.getLast_debt()).isEqualTo(UPDATED_LAST_DEBT);
        assertThat(testBeginningInventory.getLast_yes()).isEqualTo(UPDATED_LAST_YES);
        assertThat(testBeginningInventory.getCreated_date()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBeginningInventory.getKeyUUID()).isEqualTo(UPDATED_KEY_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingBeginningInventory() throws Exception {
        int databaseSizeBeforeUpdate = beginningInventoryRepository.findAll().size();
        beginningInventory.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBeginningInventoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, beginningInventory.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(beginningInventory))
            )
            .andExpect(status().isBadRequest());

        // Validate the BeginningInventory in the database
        List<BeginningInventory> beginningInventoryList = beginningInventoryRepository.findAll();
        assertThat(beginningInventoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBeginningInventory() throws Exception {
        int databaseSizeBeforeUpdate = beginningInventoryRepository.findAll().size();
        beginningInventory.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBeginningInventoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(beginningInventory))
            )
            .andExpect(status().isBadRequest());

        // Validate the BeginningInventory in the database
        List<BeginningInventory> beginningInventoryList = beginningInventoryRepository.findAll();
        assertThat(beginningInventoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBeginningInventory() throws Exception {
        int databaseSizeBeforeUpdate = beginningInventoryRepository.findAll().size();
        beginningInventory.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBeginningInventoryMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(beginningInventory))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BeginningInventory in the database
        List<BeginningInventory> beginningInventoryList = beginningInventoryRepository.findAll();
        assertThat(beginningInventoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBeginningInventory() throws Exception {
        // Initialize the database
        beginningInventoryRepository.saveAndFlush(beginningInventory);

        int databaseSizeBeforeDelete = beginningInventoryRepository.findAll().size();

        // Delete the beginningInventory
        restBeginningInventoryMockMvc
            .perform(delete(ENTITY_API_URL_ID, beginningInventory.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BeginningInventory> beginningInventoryList = beginningInventoryRepository.findAll();
        assertThat(beginningInventoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
