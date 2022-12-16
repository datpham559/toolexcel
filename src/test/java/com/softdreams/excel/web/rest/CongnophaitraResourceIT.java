package com.softdreams.excel.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.softdreams.excel.IntegrationTest;
import com.softdreams.excel.domain.Congnophaitra;
import com.softdreams.excel.repository.CongnophaitraRepository;
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
 * Integration tests for the {@link CongnophaitraResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CongnophaitraResourceIT {

    private static final String DEFAULT_MA_NCC = "AAAAAAAAAA";
    private static final String UPDATED_MA_NCC = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_NCC = "AAAAAAAAAA";
    private static final String UPDATED_TEN_NCC = "BBBBBBBBBB";

    private static final String DEFAULT_TK_CONGNO = "AAAAAAAAAA";
    private static final String UPDATED_TK_CONGNO = "BBBBBBBBBB";

    private static final Double DEFAULT_SO_DU_NO_DAU_KY = 1D;
    private static final Double UPDATED_SO_DU_NO_DAU_KY = 2D;

    private static final Double DEFAULT_SO_DU_CO_DAU_KY = 1D;
    private static final Double UPDATED_SO_DU_CO_DAU_KY = 2D;

    private static final Double DEFAULT_PHAT_SINH_NO = 1D;
    private static final Double UPDATED_PHAT_SINH_NO = 2D;

    private static final Double DEFAULT_PHAT_SINH_CO = 1D;
    private static final Double UPDATED_PHAT_SINH_CO = 2D;

    private static final Double DEFAULT_SO_DU_NO_CUOI_KY = 1D;
    private static final Double UPDATED_SO_DU_NO_CUOI_KY = 2D;

    private static final Double DEFAULT_SO_DU_CO_CUOI_KY = 1D;
    private static final Double UPDATED_SO_DU_CO_CUOI_KY = 2D;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_KEY_UUID = "AAAAAAAAAA";
    private static final String UPDATED_KEY_UUID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/congnophaitras";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CongnophaitraRepository congnophaitraRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCongnophaitraMockMvc;

    private Congnophaitra congnophaitra;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Congnophaitra createEntity(EntityManager em) {
        Congnophaitra congnophaitra = new Congnophaitra()
            .ma_ncc(DEFAULT_MA_NCC)
            .ten_ncc(DEFAULT_TEN_NCC)
            .tk_congno(DEFAULT_TK_CONGNO)
            .so_du_no_dau_ky(DEFAULT_SO_DU_NO_DAU_KY)
            .so_du_co_dau_ky(DEFAULT_SO_DU_CO_DAU_KY)
            .phat_sinh_no(DEFAULT_PHAT_SINH_NO)
            .phat_sinh_co(DEFAULT_PHAT_SINH_CO)
            .so_du_no_cuoi_ky(DEFAULT_SO_DU_NO_CUOI_KY)
            .so_du_co_cuoi_ky(DEFAULT_SO_DU_CO_CUOI_KY)
            .created_Date(DEFAULT_CREATED_DATE)
            .key_uuid(DEFAULT_KEY_UUID);
        return congnophaitra;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Congnophaitra createUpdatedEntity(EntityManager em) {
        Congnophaitra congnophaitra = new Congnophaitra()
            .ma_ncc(UPDATED_MA_NCC)
            .ten_ncc(UPDATED_TEN_NCC)
            .tk_congno(UPDATED_TK_CONGNO)
            .so_du_no_dau_ky(UPDATED_SO_DU_NO_DAU_KY)
            .so_du_co_dau_ky(UPDATED_SO_DU_CO_DAU_KY)
            .phat_sinh_no(UPDATED_PHAT_SINH_NO)
            .phat_sinh_co(UPDATED_PHAT_SINH_CO)
            .so_du_no_cuoi_ky(UPDATED_SO_DU_NO_CUOI_KY)
            .so_du_co_cuoi_ky(UPDATED_SO_DU_CO_CUOI_KY)
            .created_Date(UPDATED_CREATED_DATE)
            .key_uuid(UPDATED_KEY_UUID);
        return congnophaitra;
    }

    @BeforeEach
    public void initTest() {
        congnophaitra = createEntity(em);
    }

    @Test
    @Transactional
    void createCongnophaitra() throws Exception {
        int databaseSizeBeforeCreate = congnophaitraRepository.findAll().size();
        // Create the Congnophaitra
        restCongnophaitraMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(congnophaitra)))
            .andExpect(status().isCreated());

        // Validate the Congnophaitra in the database
        List<Congnophaitra> congnophaitraList = congnophaitraRepository.findAll();
        assertThat(congnophaitraList).hasSize(databaseSizeBeforeCreate + 1);
        Congnophaitra testCongnophaitra = congnophaitraList.get(congnophaitraList.size() - 1);
        assertThat(testCongnophaitra.getMa_ncc()).isEqualTo(DEFAULT_MA_NCC);
        assertThat(testCongnophaitra.getTen_ncc()).isEqualTo(DEFAULT_TEN_NCC);
        assertThat(testCongnophaitra.getTk_congno()).isEqualTo(DEFAULT_TK_CONGNO);
        assertThat(testCongnophaitra.getSo_du_no_dau_ky()).isEqualTo(DEFAULT_SO_DU_NO_DAU_KY);
        assertThat(testCongnophaitra.getSo_du_co_dau_ky()).isEqualTo(DEFAULT_SO_DU_CO_DAU_KY);
        assertThat(testCongnophaitra.getPhat_sinh_no()).isEqualTo(DEFAULT_PHAT_SINH_NO);
        assertThat(testCongnophaitra.getPhat_sinh_co()).isEqualTo(DEFAULT_PHAT_SINH_CO);
        assertThat(testCongnophaitra.getSo_du_no_cuoi_ky()).isEqualTo(DEFAULT_SO_DU_NO_CUOI_KY);
        assertThat(testCongnophaitra.getSo_du_co_cuoi_ky()).isEqualTo(DEFAULT_SO_DU_CO_CUOI_KY);
        assertThat(testCongnophaitra.getCreated_Date()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testCongnophaitra.getKey_uuid()).isEqualTo(DEFAULT_KEY_UUID);
    }

    @Test
    @Transactional
    void createCongnophaitraWithExistingId() throws Exception {
        // Create the Congnophaitra with an existing ID
        congnophaitra.setId(1L);

        int databaseSizeBeforeCreate = congnophaitraRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCongnophaitraMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(congnophaitra)))
            .andExpect(status().isBadRequest());

        // Validate the Congnophaitra in the database
        List<Congnophaitra> congnophaitraList = congnophaitraRepository.findAll();
        assertThat(congnophaitraList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCongnophaitras() throws Exception {
        // Initialize the database
        congnophaitraRepository.saveAndFlush(congnophaitra);

        // Get all the congnophaitraList
        restCongnophaitraMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(congnophaitra.getId().intValue())))
            .andExpect(jsonPath("$.[*].ma_ncc").value(hasItem(DEFAULT_MA_NCC)))
            .andExpect(jsonPath("$.[*].ten_ncc").value(hasItem(DEFAULT_TEN_NCC)))
            .andExpect(jsonPath("$.[*].tk_congno").value(hasItem(DEFAULT_TK_CONGNO)))
            .andExpect(jsonPath("$.[*].so_du_no_dau_ky").value(hasItem(DEFAULT_SO_DU_NO_DAU_KY.doubleValue())))
            .andExpect(jsonPath("$.[*].so_du_co_dau_ky").value(hasItem(DEFAULT_SO_DU_CO_DAU_KY.doubleValue())))
            .andExpect(jsonPath("$.[*].phat_sinh_no").value(hasItem(DEFAULT_PHAT_SINH_NO.doubleValue())))
            .andExpect(jsonPath("$.[*].phat_sinh_co").value(hasItem(DEFAULT_PHAT_SINH_CO.doubleValue())))
            .andExpect(jsonPath("$.[*].so_du_no_cuoi_ky").value(hasItem(DEFAULT_SO_DU_NO_CUOI_KY.doubleValue())))
            .andExpect(jsonPath("$.[*].so_du_co_cuoi_ky").value(hasItem(DEFAULT_SO_DU_CO_CUOI_KY.doubleValue())))
            .andExpect(jsonPath("$.[*].created_Date").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].key_uuid").value(hasItem(DEFAULT_KEY_UUID)));
    }

    @Test
    @Transactional
    void getCongnophaitra() throws Exception {
        // Initialize the database
        congnophaitraRepository.saveAndFlush(congnophaitra);

        // Get the congnophaitra
        restCongnophaitraMockMvc
            .perform(get(ENTITY_API_URL_ID, congnophaitra.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(congnophaitra.getId().intValue()))
            .andExpect(jsonPath("$.ma_ncc").value(DEFAULT_MA_NCC))
            .andExpect(jsonPath("$.ten_ncc").value(DEFAULT_TEN_NCC))
            .andExpect(jsonPath("$.tk_congno").value(DEFAULT_TK_CONGNO))
            .andExpect(jsonPath("$.so_du_no_dau_ky").value(DEFAULT_SO_DU_NO_DAU_KY.doubleValue()))
            .andExpect(jsonPath("$.so_du_co_dau_ky").value(DEFAULT_SO_DU_CO_DAU_KY.doubleValue()))
            .andExpect(jsonPath("$.phat_sinh_no").value(DEFAULT_PHAT_SINH_NO.doubleValue()))
            .andExpect(jsonPath("$.phat_sinh_co").value(DEFAULT_PHAT_SINH_CO.doubleValue()))
            .andExpect(jsonPath("$.so_du_no_cuoi_ky").value(DEFAULT_SO_DU_NO_CUOI_KY.doubleValue()))
            .andExpect(jsonPath("$.so_du_co_cuoi_ky").value(DEFAULT_SO_DU_CO_CUOI_KY.doubleValue()))
            .andExpect(jsonPath("$.created_Date").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.key_uuid").value(DEFAULT_KEY_UUID));
    }

    @Test
    @Transactional
    void getNonExistingCongnophaitra() throws Exception {
        // Get the congnophaitra
        restCongnophaitraMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCongnophaitra() throws Exception {
        // Initialize the database
        congnophaitraRepository.saveAndFlush(congnophaitra);

        int databaseSizeBeforeUpdate = congnophaitraRepository.findAll().size();

        // Update the congnophaitra
        Congnophaitra updatedCongnophaitra = congnophaitraRepository.findById(congnophaitra.getId()).get();
        // Disconnect from session so that the updates on updatedCongnophaitra are not directly saved in db
        em.detach(updatedCongnophaitra);
        updatedCongnophaitra
            .ma_ncc(UPDATED_MA_NCC)
            .ten_ncc(UPDATED_TEN_NCC)
            .tk_congno(UPDATED_TK_CONGNO)
            .so_du_no_dau_ky(UPDATED_SO_DU_NO_DAU_KY)
            .so_du_co_dau_ky(UPDATED_SO_DU_CO_DAU_KY)
            .phat_sinh_no(UPDATED_PHAT_SINH_NO)
            .phat_sinh_co(UPDATED_PHAT_SINH_CO)
            .so_du_no_cuoi_ky(UPDATED_SO_DU_NO_CUOI_KY)
            .so_du_co_cuoi_ky(UPDATED_SO_DU_CO_CUOI_KY)
            .created_Date(UPDATED_CREATED_DATE)
            .key_uuid(UPDATED_KEY_UUID);

        restCongnophaitraMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCongnophaitra.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedCongnophaitra))
            )
            .andExpect(status().isOk());

        // Validate the Congnophaitra in the database
        List<Congnophaitra> congnophaitraList = congnophaitraRepository.findAll();
        assertThat(congnophaitraList).hasSize(databaseSizeBeforeUpdate);
        Congnophaitra testCongnophaitra = congnophaitraList.get(congnophaitraList.size() - 1);
        assertThat(testCongnophaitra.getMa_ncc()).isEqualTo(UPDATED_MA_NCC);
        assertThat(testCongnophaitra.getTen_ncc()).isEqualTo(UPDATED_TEN_NCC);
        assertThat(testCongnophaitra.getTk_congno()).isEqualTo(UPDATED_TK_CONGNO);
        assertThat(testCongnophaitra.getSo_du_no_dau_ky()).isEqualTo(UPDATED_SO_DU_NO_DAU_KY);
        assertThat(testCongnophaitra.getSo_du_co_dau_ky()).isEqualTo(UPDATED_SO_DU_CO_DAU_KY);
        assertThat(testCongnophaitra.getPhat_sinh_no()).isEqualTo(UPDATED_PHAT_SINH_NO);
        assertThat(testCongnophaitra.getPhat_sinh_co()).isEqualTo(UPDATED_PHAT_SINH_CO);
        assertThat(testCongnophaitra.getSo_du_no_cuoi_ky()).isEqualTo(UPDATED_SO_DU_NO_CUOI_KY);
        assertThat(testCongnophaitra.getSo_du_co_cuoi_ky()).isEqualTo(UPDATED_SO_DU_CO_CUOI_KY);
        assertThat(testCongnophaitra.getCreated_Date()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCongnophaitra.getKey_uuid()).isEqualTo(UPDATED_KEY_UUID);
    }

    @Test
    @Transactional
    void putNonExistingCongnophaitra() throws Exception {
        int databaseSizeBeforeUpdate = congnophaitraRepository.findAll().size();
        congnophaitra.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCongnophaitraMockMvc
            .perform(
                put(ENTITY_API_URL_ID, congnophaitra.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(congnophaitra))
            )
            .andExpect(status().isBadRequest());

        // Validate the Congnophaitra in the database
        List<Congnophaitra> congnophaitraList = congnophaitraRepository.findAll();
        assertThat(congnophaitraList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCongnophaitra() throws Exception {
        int databaseSizeBeforeUpdate = congnophaitraRepository.findAll().size();
        congnophaitra.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCongnophaitraMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(congnophaitra))
            )
            .andExpect(status().isBadRequest());

        // Validate the Congnophaitra in the database
        List<Congnophaitra> congnophaitraList = congnophaitraRepository.findAll();
        assertThat(congnophaitraList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCongnophaitra() throws Exception {
        int databaseSizeBeforeUpdate = congnophaitraRepository.findAll().size();
        congnophaitra.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCongnophaitraMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(congnophaitra)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Congnophaitra in the database
        List<Congnophaitra> congnophaitraList = congnophaitraRepository.findAll();
        assertThat(congnophaitraList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCongnophaitraWithPatch() throws Exception {
        // Initialize the database
        congnophaitraRepository.saveAndFlush(congnophaitra);

        int databaseSizeBeforeUpdate = congnophaitraRepository.findAll().size();

        // Update the congnophaitra using partial update
        Congnophaitra partialUpdatedCongnophaitra = new Congnophaitra();
        partialUpdatedCongnophaitra.setId(congnophaitra.getId());

        partialUpdatedCongnophaitra
            .ma_ncc(UPDATED_MA_NCC)
            .ten_ncc(UPDATED_TEN_NCC)
            .so_du_co_dau_ky(UPDATED_SO_DU_CO_DAU_KY)
            .so_du_no_cuoi_ky(UPDATED_SO_DU_NO_CUOI_KY)
            .created_Date(UPDATED_CREATED_DATE)
            .key_uuid(UPDATED_KEY_UUID);

        restCongnophaitraMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCongnophaitra.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCongnophaitra))
            )
            .andExpect(status().isOk());

        // Validate the Congnophaitra in the database
        List<Congnophaitra> congnophaitraList = congnophaitraRepository.findAll();
        assertThat(congnophaitraList).hasSize(databaseSizeBeforeUpdate);
        Congnophaitra testCongnophaitra = congnophaitraList.get(congnophaitraList.size() - 1);
        assertThat(testCongnophaitra.getMa_ncc()).isEqualTo(UPDATED_MA_NCC);
        assertThat(testCongnophaitra.getTen_ncc()).isEqualTo(UPDATED_TEN_NCC);
        assertThat(testCongnophaitra.getTk_congno()).isEqualTo(DEFAULT_TK_CONGNO);
        assertThat(testCongnophaitra.getSo_du_no_dau_ky()).isEqualTo(DEFAULT_SO_DU_NO_DAU_KY);
        assertThat(testCongnophaitra.getSo_du_co_dau_ky()).isEqualTo(UPDATED_SO_DU_CO_DAU_KY);
        assertThat(testCongnophaitra.getPhat_sinh_no()).isEqualTo(DEFAULT_PHAT_SINH_NO);
        assertThat(testCongnophaitra.getPhat_sinh_co()).isEqualTo(DEFAULT_PHAT_SINH_CO);
        assertThat(testCongnophaitra.getSo_du_no_cuoi_ky()).isEqualTo(UPDATED_SO_DU_NO_CUOI_KY);
        assertThat(testCongnophaitra.getSo_du_co_cuoi_ky()).isEqualTo(DEFAULT_SO_DU_CO_CUOI_KY);
        assertThat(testCongnophaitra.getCreated_Date()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCongnophaitra.getKey_uuid()).isEqualTo(UPDATED_KEY_UUID);
    }

    @Test
    @Transactional
    void fullUpdateCongnophaitraWithPatch() throws Exception {
        // Initialize the database
        congnophaitraRepository.saveAndFlush(congnophaitra);

        int databaseSizeBeforeUpdate = congnophaitraRepository.findAll().size();

        // Update the congnophaitra using partial update
        Congnophaitra partialUpdatedCongnophaitra = new Congnophaitra();
        partialUpdatedCongnophaitra.setId(congnophaitra.getId());

        partialUpdatedCongnophaitra
            .ma_ncc(UPDATED_MA_NCC)
            .ten_ncc(UPDATED_TEN_NCC)
            .tk_congno(UPDATED_TK_CONGNO)
            .so_du_no_dau_ky(UPDATED_SO_DU_NO_DAU_KY)
            .so_du_co_dau_ky(UPDATED_SO_DU_CO_DAU_KY)
            .phat_sinh_no(UPDATED_PHAT_SINH_NO)
            .phat_sinh_co(UPDATED_PHAT_SINH_CO)
            .so_du_no_cuoi_ky(UPDATED_SO_DU_NO_CUOI_KY)
            .so_du_co_cuoi_ky(UPDATED_SO_DU_CO_CUOI_KY)
            .created_Date(UPDATED_CREATED_DATE)
            .key_uuid(UPDATED_KEY_UUID);

        restCongnophaitraMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCongnophaitra.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCongnophaitra))
            )
            .andExpect(status().isOk());

        // Validate the Congnophaitra in the database
        List<Congnophaitra> congnophaitraList = congnophaitraRepository.findAll();
        assertThat(congnophaitraList).hasSize(databaseSizeBeforeUpdate);
        Congnophaitra testCongnophaitra = congnophaitraList.get(congnophaitraList.size() - 1);
        assertThat(testCongnophaitra.getMa_ncc()).isEqualTo(UPDATED_MA_NCC);
        assertThat(testCongnophaitra.getTen_ncc()).isEqualTo(UPDATED_TEN_NCC);
        assertThat(testCongnophaitra.getTk_congno()).isEqualTo(UPDATED_TK_CONGNO);
        assertThat(testCongnophaitra.getSo_du_no_dau_ky()).isEqualTo(UPDATED_SO_DU_NO_DAU_KY);
        assertThat(testCongnophaitra.getSo_du_co_dau_ky()).isEqualTo(UPDATED_SO_DU_CO_DAU_KY);
        assertThat(testCongnophaitra.getPhat_sinh_no()).isEqualTo(UPDATED_PHAT_SINH_NO);
        assertThat(testCongnophaitra.getPhat_sinh_co()).isEqualTo(UPDATED_PHAT_SINH_CO);
        assertThat(testCongnophaitra.getSo_du_no_cuoi_ky()).isEqualTo(UPDATED_SO_DU_NO_CUOI_KY);
        assertThat(testCongnophaitra.getSo_du_co_cuoi_ky()).isEqualTo(UPDATED_SO_DU_CO_CUOI_KY);
        assertThat(testCongnophaitra.getCreated_Date()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCongnophaitra.getKey_uuid()).isEqualTo(UPDATED_KEY_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingCongnophaitra() throws Exception {
        int databaseSizeBeforeUpdate = congnophaitraRepository.findAll().size();
        congnophaitra.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCongnophaitraMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, congnophaitra.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(congnophaitra))
            )
            .andExpect(status().isBadRequest());

        // Validate the Congnophaitra in the database
        List<Congnophaitra> congnophaitraList = congnophaitraRepository.findAll();
        assertThat(congnophaitraList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCongnophaitra() throws Exception {
        int databaseSizeBeforeUpdate = congnophaitraRepository.findAll().size();
        congnophaitra.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCongnophaitraMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(congnophaitra))
            )
            .andExpect(status().isBadRequest());

        // Validate the Congnophaitra in the database
        List<Congnophaitra> congnophaitraList = congnophaitraRepository.findAll();
        assertThat(congnophaitraList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCongnophaitra() throws Exception {
        int databaseSizeBeforeUpdate = congnophaitraRepository.findAll().size();
        congnophaitra.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCongnophaitraMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(congnophaitra))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Congnophaitra in the database
        List<Congnophaitra> congnophaitraList = congnophaitraRepository.findAll();
        assertThat(congnophaitraList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCongnophaitra() throws Exception {
        // Initialize the database
        congnophaitraRepository.saveAndFlush(congnophaitra);

        int databaseSizeBeforeDelete = congnophaitraRepository.findAll().size();

        // Delete the congnophaitra
        restCongnophaitraMockMvc
            .perform(delete(ENTITY_API_URL_ID, congnophaitra.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Congnophaitra> congnophaitraList = congnophaitraRepository.findAll();
        assertThat(congnophaitraList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
