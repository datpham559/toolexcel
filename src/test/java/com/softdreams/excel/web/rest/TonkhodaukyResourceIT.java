package com.softdreams.excel.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.softdreams.excel.IntegrationTest;
import com.softdreams.excel.domain.Tonkhodauky;
import com.softdreams.excel.repository.TonkhodaukyRepository;
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
 * Integration tests for the {@link TonkhodaukyResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TonkhodaukyResourceIT {

    private static final String DEFAULT_TEN_KHO = "AAAAAAAAAA";
    private static final String UPDATED_TEN_KHO = "BBBBBBBBBB";

    private static final String DEFAULT_MA_HANG = "AAAAAAAAAA";
    private static final String UPDATED_MA_HANG = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_HANG = "AAAAAAAAAA";
    private static final String UPDATED_TEN_HANG = "BBBBBBBBBB";

    private static final String DEFAULT_DVT = "AAAAAAAAAA";
    private static final String UPDATED_DVT = "BBBBBBBBBB";

    private static final Double DEFAULT_DAU_KY_SO_LUONG = 1D;
    private static final Double UPDATED_DAU_KY_SO_LUONG = 2D;

    private static final Double DEFAULT_DAU_KY_GIA_TRI = 1D;
    private static final Double UPDATED_DAU_KY_GIA_TRI = 2D;

    private static final Double DEFAULT_NHAP_KHO_SO_LUONG = 1D;
    private static final Double UPDATED_NHAP_KHO_SO_LUONG = 2D;

    private static final Double DEFAULT_NHAP_KHO_GIA_TRI = 1D;
    private static final Double UPDATED_NHAP_KHO_GIA_TRI = 2D;

    private static final Double DEFAULT_XUAT_KHO_SO_LUONG = 1D;
    private static final Double UPDATED_XUAT_KHO_SO_LUONG = 2D;

    private static final Double DEFAULT_XUAT_KHO_GIA_TRI = 1D;
    private static final Double UPDATED_XUAT_KHO_GIA_TRI = 2D;

    private static final Double DEFAULT_CUOI_KY_SO_LUONG = 1D;
    private static final Double UPDATED_CUOI_KY_SO_LUONG = 2D;

    private static final Double DEFAULT_CUOI_KY_GIA_TRI = 1D;
    private static final Double UPDATED_CUOI_KY_GIA_TRI = 2D;

    private static final Double DEFAULT_DON_GIA_BINH_QUAN = 1D;
    private static final Double UPDATED_DON_GIA_BINH_QUAN = 2D;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_KEY_UUID = "AAAAAAAAAA";
    private static final String UPDATED_KEY_UUID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/tonkhodaukies";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TonkhodaukyRepository tonkhodaukyRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTonkhodaukyMockMvc;

    private Tonkhodauky tonkhodauky;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tonkhodauky createEntity(EntityManager em) {
        Tonkhodauky tonkhodauky = new Tonkhodauky()
            .ten_kho(DEFAULT_TEN_KHO)
            .ma_hang(DEFAULT_MA_HANG)
            .ten_hang(DEFAULT_TEN_HANG)
            .dvt(DEFAULT_DVT)
            .dau_ky_so_luong(DEFAULT_DAU_KY_SO_LUONG)
            .dau_ky_gia_tri(DEFAULT_DAU_KY_GIA_TRI)
            .nhap_kho_so_luong(DEFAULT_NHAP_KHO_SO_LUONG)
            .nhap_kho_gia_tri(DEFAULT_NHAP_KHO_GIA_TRI)
            .xuat_kho_so_luong(DEFAULT_XUAT_KHO_SO_LUONG)
            .xuat_kho_gia_tri(DEFAULT_XUAT_KHO_GIA_TRI)
            .cuoi_ky_so_luong(DEFAULT_CUOI_KY_SO_LUONG)
            .cuoi_ky_gia_tri(DEFAULT_CUOI_KY_GIA_TRI)
            .don_gia_binh_quan(DEFAULT_DON_GIA_BINH_QUAN)
            .createdDate(DEFAULT_CREATED_DATE)
            .key_uuid(DEFAULT_KEY_UUID);
        return tonkhodauky;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tonkhodauky createUpdatedEntity(EntityManager em) {
        Tonkhodauky tonkhodauky = new Tonkhodauky()
            .ten_kho(UPDATED_TEN_KHO)
            .ma_hang(UPDATED_MA_HANG)
            .ten_hang(UPDATED_TEN_HANG)
            .dvt(UPDATED_DVT)
            .dau_ky_so_luong(UPDATED_DAU_KY_SO_LUONG)
            .dau_ky_gia_tri(UPDATED_DAU_KY_GIA_TRI)
            .nhap_kho_so_luong(UPDATED_NHAP_KHO_SO_LUONG)
            .nhap_kho_gia_tri(UPDATED_NHAP_KHO_GIA_TRI)
            .xuat_kho_so_luong(UPDATED_XUAT_KHO_SO_LUONG)
            .xuat_kho_gia_tri(UPDATED_XUAT_KHO_GIA_TRI)
            .cuoi_ky_so_luong(UPDATED_CUOI_KY_SO_LUONG)
            .cuoi_ky_gia_tri(UPDATED_CUOI_KY_GIA_TRI)
            .don_gia_binh_quan(UPDATED_DON_GIA_BINH_QUAN)
            .createdDate(UPDATED_CREATED_DATE)
            .key_uuid(UPDATED_KEY_UUID);
        return tonkhodauky;
    }

    @BeforeEach
    public void initTest() {
        tonkhodauky = createEntity(em);
    }

    @Test
    @Transactional
    void createTonkhodauky() throws Exception {
        int databaseSizeBeforeCreate = tonkhodaukyRepository.findAll().size();
        // Create the Tonkhodauky
        restTonkhodaukyMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tonkhodauky)))
            .andExpect(status().isCreated());

        // Validate the Tonkhodauky in the database
        List<Tonkhodauky> tonkhodaukyList = tonkhodaukyRepository.findAll();
        assertThat(tonkhodaukyList).hasSize(databaseSizeBeforeCreate + 1);
        Tonkhodauky testTonkhodauky = tonkhodaukyList.get(tonkhodaukyList.size() - 1);
        assertThat(testTonkhodauky.getTen_kho()).isEqualTo(DEFAULT_TEN_KHO);
        assertThat(testTonkhodauky.getMa_hang()).isEqualTo(DEFAULT_MA_HANG);
        assertThat(testTonkhodauky.getTen_hang()).isEqualTo(DEFAULT_TEN_HANG);
        assertThat(testTonkhodauky.getDvt()).isEqualTo(DEFAULT_DVT);
        assertThat(testTonkhodauky.getDau_ky_so_luong()).isEqualTo(DEFAULT_DAU_KY_SO_LUONG);
        assertThat(testTonkhodauky.getDau_ky_gia_tri()).isEqualTo(DEFAULT_DAU_KY_GIA_TRI);
        assertThat(testTonkhodauky.getNhap_kho_so_luong()).isEqualTo(DEFAULT_NHAP_KHO_SO_LUONG);
        assertThat(testTonkhodauky.getNhap_kho_gia_tri()).isEqualTo(DEFAULT_NHAP_KHO_GIA_TRI);
        assertThat(testTonkhodauky.getXuat_kho_so_luong()).isEqualTo(DEFAULT_XUAT_KHO_SO_LUONG);
        assertThat(testTonkhodauky.getXuat_kho_gia_tri()).isEqualTo(DEFAULT_XUAT_KHO_GIA_TRI);
        assertThat(testTonkhodauky.getCuoi_ky_so_luong()).isEqualTo(DEFAULT_CUOI_KY_SO_LUONG);
        assertThat(testTonkhodauky.getCuoi_ky_gia_tri()).isEqualTo(DEFAULT_CUOI_KY_GIA_TRI);
        assertThat(testTonkhodauky.getDon_gia_binh_quan()).isEqualTo(DEFAULT_DON_GIA_BINH_QUAN);
        assertThat(testTonkhodauky.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testTonkhodauky.getKey_uuid()).isEqualTo(DEFAULT_KEY_UUID);
    }

    @Test
    @Transactional
    void createTonkhodaukyWithExistingId() throws Exception {
        // Create the Tonkhodauky with an existing ID
        tonkhodauky.setId(1L);

        int databaseSizeBeforeCreate = tonkhodaukyRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTonkhodaukyMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tonkhodauky)))
            .andExpect(status().isBadRequest());

        // Validate the Tonkhodauky in the database
        List<Tonkhodauky> tonkhodaukyList = tonkhodaukyRepository.findAll();
        assertThat(tonkhodaukyList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTonkhodaukies() throws Exception {
        // Initialize the database
        tonkhodaukyRepository.saveAndFlush(tonkhodauky);

        // Get all the tonkhodaukyList
        restTonkhodaukyMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tonkhodauky.getId().intValue())))
            .andExpect(jsonPath("$.[*].ten_kho").value(hasItem(DEFAULT_TEN_KHO)))
            .andExpect(jsonPath("$.[*].ma_hang").value(hasItem(DEFAULT_MA_HANG)))
            .andExpect(jsonPath("$.[*].ten_hang").value(hasItem(DEFAULT_TEN_HANG)))
            .andExpect(jsonPath("$.[*].dvt").value(hasItem(DEFAULT_DVT)))
            .andExpect(jsonPath("$.[*].dau_ky_so_luong").value(hasItem(DEFAULT_DAU_KY_SO_LUONG.doubleValue())))
            .andExpect(jsonPath("$.[*].dau_ky_gia_tri").value(hasItem(DEFAULT_DAU_KY_GIA_TRI.doubleValue())))
            .andExpect(jsonPath("$.[*].nhap_kho_so_luong").value(hasItem(DEFAULT_NHAP_KHO_SO_LUONG.doubleValue())))
            .andExpect(jsonPath("$.[*].nhap_kho_gia_tri").value(hasItem(DEFAULT_NHAP_KHO_GIA_TRI.doubleValue())))
            .andExpect(jsonPath("$.[*].xuat_kho_so_luong").value(hasItem(DEFAULT_XUAT_KHO_SO_LUONG.doubleValue())))
            .andExpect(jsonPath("$.[*].xuat_kho_gia_tri").value(hasItem(DEFAULT_XUAT_KHO_GIA_TRI.doubleValue())))
            .andExpect(jsonPath("$.[*].cuoi_ky_so_luong").value(hasItem(DEFAULT_CUOI_KY_SO_LUONG.doubleValue())))
            .andExpect(jsonPath("$.[*].cuoi_ky_gia_tri").value(hasItem(DEFAULT_CUOI_KY_GIA_TRI.doubleValue())))
            .andExpect(jsonPath("$.[*].don_gia_binh_quan").value(hasItem(DEFAULT_DON_GIA_BINH_QUAN.doubleValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].key_uuid").value(hasItem(DEFAULT_KEY_UUID)));
    }

    @Test
    @Transactional
    void getTonkhodauky() throws Exception {
        // Initialize the database
        tonkhodaukyRepository.saveAndFlush(tonkhodauky);

        // Get the tonkhodauky
        restTonkhodaukyMockMvc
            .perform(get(ENTITY_API_URL_ID, tonkhodauky.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tonkhodauky.getId().intValue()))
            .andExpect(jsonPath("$.ten_kho").value(DEFAULT_TEN_KHO))
            .andExpect(jsonPath("$.ma_hang").value(DEFAULT_MA_HANG))
            .andExpect(jsonPath("$.ten_hang").value(DEFAULT_TEN_HANG))
            .andExpect(jsonPath("$.dvt").value(DEFAULT_DVT))
            .andExpect(jsonPath("$.dau_ky_so_luong").value(DEFAULT_DAU_KY_SO_LUONG.doubleValue()))
            .andExpect(jsonPath("$.dau_ky_gia_tri").value(DEFAULT_DAU_KY_GIA_TRI.doubleValue()))
            .andExpect(jsonPath("$.nhap_kho_so_luong").value(DEFAULT_NHAP_KHO_SO_LUONG.doubleValue()))
            .andExpect(jsonPath("$.nhap_kho_gia_tri").value(DEFAULT_NHAP_KHO_GIA_TRI.doubleValue()))
            .andExpect(jsonPath("$.xuat_kho_so_luong").value(DEFAULT_XUAT_KHO_SO_LUONG.doubleValue()))
            .andExpect(jsonPath("$.xuat_kho_gia_tri").value(DEFAULT_XUAT_KHO_GIA_TRI.doubleValue()))
            .andExpect(jsonPath("$.cuoi_ky_so_luong").value(DEFAULT_CUOI_KY_SO_LUONG.doubleValue()))
            .andExpect(jsonPath("$.cuoi_ky_gia_tri").value(DEFAULT_CUOI_KY_GIA_TRI.doubleValue()))
            .andExpect(jsonPath("$.don_gia_binh_quan").value(DEFAULT_DON_GIA_BINH_QUAN.doubleValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.key_uuid").value(DEFAULT_KEY_UUID));
    }

    @Test
    @Transactional
    void getNonExistingTonkhodauky() throws Exception {
        // Get the tonkhodauky
        restTonkhodaukyMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTonkhodauky() throws Exception {
        // Initialize the database
        tonkhodaukyRepository.saveAndFlush(tonkhodauky);

        int databaseSizeBeforeUpdate = tonkhodaukyRepository.findAll().size();

        // Update the tonkhodauky
        Tonkhodauky updatedTonkhodauky = tonkhodaukyRepository.findById(tonkhodauky.getId()).get();
        // Disconnect from session so that the updates on updatedTonkhodauky are not directly saved in db
        em.detach(updatedTonkhodauky);
        updatedTonkhodauky
            .ten_kho(UPDATED_TEN_KHO)
            .ma_hang(UPDATED_MA_HANG)
            .ten_hang(UPDATED_TEN_HANG)
            .dvt(UPDATED_DVT)
            .dau_ky_so_luong(UPDATED_DAU_KY_SO_LUONG)
            .dau_ky_gia_tri(UPDATED_DAU_KY_GIA_TRI)
            .nhap_kho_so_luong(UPDATED_NHAP_KHO_SO_LUONG)
            .nhap_kho_gia_tri(UPDATED_NHAP_KHO_GIA_TRI)
            .xuat_kho_so_luong(UPDATED_XUAT_KHO_SO_LUONG)
            .xuat_kho_gia_tri(UPDATED_XUAT_KHO_GIA_TRI)
            .cuoi_ky_so_luong(UPDATED_CUOI_KY_SO_LUONG)
            .cuoi_ky_gia_tri(UPDATED_CUOI_KY_GIA_TRI)
            .don_gia_binh_quan(UPDATED_DON_GIA_BINH_QUAN)
            .createdDate(UPDATED_CREATED_DATE)
            .key_uuid(UPDATED_KEY_UUID);

        restTonkhodaukyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTonkhodauky.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTonkhodauky))
            )
            .andExpect(status().isOk());

        // Validate the Tonkhodauky in the database
        List<Tonkhodauky> tonkhodaukyList = tonkhodaukyRepository.findAll();
        assertThat(tonkhodaukyList).hasSize(databaseSizeBeforeUpdate);
        Tonkhodauky testTonkhodauky = tonkhodaukyList.get(tonkhodaukyList.size() - 1);
        assertThat(testTonkhodauky.getTen_kho()).isEqualTo(UPDATED_TEN_KHO);
        assertThat(testTonkhodauky.getMa_hang()).isEqualTo(UPDATED_MA_HANG);
        assertThat(testTonkhodauky.getTen_hang()).isEqualTo(UPDATED_TEN_HANG);
        assertThat(testTonkhodauky.getDvt()).isEqualTo(UPDATED_DVT);
        assertThat(testTonkhodauky.getDau_ky_so_luong()).isEqualTo(UPDATED_DAU_KY_SO_LUONG);
        assertThat(testTonkhodauky.getDau_ky_gia_tri()).isEqualTo(UPDATED_DAU_KY_GIA_TRI);
        assertThat(testTonkhodauky.getNhap_kho_so_luong()).isEqualTo(UPDATED_NHAP_KHO_SO_LUONG);
        assertThat(testTonkhodauky.getNhap_kho_gia_tri()).isEqualTo(UPDATED_NHAP_KHO_GIA_TRI);
        assertThat(testTonkhodauky.getXuat_kho_so_luong()).isEqualTo(UPDATED_XUAT_KHO_SO_LUONG);
        assertThat(testTonkhodauky.getXuat_kho_gia_tri()).isEqualTo(UPDATED_XUAT_KHO_GIA_TRI);
        assertThat(testTonkhodauky.getCuoi_ky_so_luong()).isEqualTo(UPDATED_CUOI_KY_SO_LUONG);
        assertThat(testTonkhodauky.getCuoi_ky_gia_tri()).isEqualTo(UPDATED_CUOI_KY_GIA_TRI);
        assertThat(testTonkhodauky.getDon_gia_binh_quan()).isEqualTo(UPDATED_DON_GIA_BINH_QUAN);
        assertThat(testTonkhodauky.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testTonkhodauky.getKey_uuid()).isEqualTo(UPDATED_KEY_UUID);
    }

    @Test
    @Transactional
    void putNonExistingTonkhodauky() throws Exception {
        int databaseSizeBeforeUpdate = tonkhodaukyRepository.findAll().size();
        tonkhodauky.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTonkhodaukyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tonkhodauky.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tonkhodauky))
            )
            .andExpect(status().isBadRequest());

        // Validate the Tonkhodauky in the database
        List<Tonkhodauky> tonkhodaukyList = tonkhodaukyRepository.findAll();
        assertThat(tonkhodaukyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTonkhodauky() throws Exception {
        int databaseSizeBeforeUpdate = tonkhodaukyRepository.findAll().size();
        tonkhodauky.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTonkhodaukyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tonkhodauky))
            )
            .andExpect(status().isBadRequest());

        // Validate the Tonkhodauky in the database
        List<Tonkhodauky> tonkhodaukyList = tonkhodaukyRepository.findAll();
        assertThat(tonkhodaukyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTonkhodauky() throws Exception {
        int databaseSizeBeforeUpdate = tonkhodaukyRepository.findAll().size();
        tonkhodauky.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTonkhodaukyMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tonkhodauky)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Tonkhodauky in the database
        List<Tonkhodauky> tonkhodaukyList = tonkhodaukyRepository.findAll();
        assertThat(tonkhodaukyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTonkhodaukyWithPatch() throws Exception {
        // Initialize the database
        tonkhodaukyRepository.saveAndFlush(tonkhodauky);

        int databaseSizeBeforeUpdate = tonkhodaukyRepository.findAll().size();

        // Update the tonkhodauky using partial update
        Tonkhodauky partialUpdatedTonkhodauky = new Tonkhodauky();
        partialUpdatedTonkhodauky.setId(tonkhodauky.getId());

        partialUpdatedTonkhodauky
            .ten_kho(UPDATED_TEN_KHO)
            .ten_hang(UPDATED_TEN_HANG)
            .nhap_kho_so_luong(UPDATED_NHAP_KHO_SO_LUONG)
            .cuoi_ky_so_luong(UPDATED_CUOI_KY_SO_LUONG)
            .don_gia_binh_quan(UPDATED_DON_GIA_BINH_QUAN);

        restTonkhodaukyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTonkhodauky.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTonkhodauky))
            )
            .andExpect(status().isOk());

        // Validate the Tonkhodauky in the database
        List<Tonkhodauky> tonkhodaukyList = tonkhodaukyRepository.findAll();
        assertThat(tonkhodaukyList).hasSize(databaseSizeBeforeUpdate);
        Tonkhodauky testTonkhodauky = tonkhodaukyList.get(tonkhodaukyList.size() - 1);
        assertThat(testTonkhodauky.getTen_kho()).isEqualTo(UPDATED_TEN_KHO);
        assertThat(testTonkhodauky.getMa_hang()).isEqualTo(DEFAULT_MA_HANG);
        assertThat(testTonkhodauky.getTen_hang()).isEqualTo(UPDATED_TEN_HANG);
        assertThat(testTonkhodauky.getDvt()).isEqualTo(DEFAULT_DVT);
        assertThat(testTonkhodauky.getDau_ky_so_luong()).isEqualTo(DEFAULT_DAU_KY_SO_LUONG);
        assertThat(testTonkhodauky.getDau_ky_gia_tri()).isEqualTo(DEFAULT_DAU_KY_GIA_TRI);
        assertThat(testTonkhodauky.getNhap_kho_so_luong()).isEqualTo(UPDATED_NHAP_KHO_SO_LUONG);
        assertThat(testTonkhodauky.getNhap_kho_gia_tri()).isEqualTo(DEFAULT_NHAP_KHO_GIA_TRI);
        assertThat(testTonkhodauky.getXuat_kho_so_luong()).isEqualTo(DEFAULT_XUAT_KHO_SO_LUONG);
        assertThat(testTonkhodauky.getXuat_kho_gia_tri()).isEqualTo(DEFAULT_XUAT_KHO_GIA_TRI);
        assertThat(testTonkhodauky.getCuoi_ky_so_luong()).isEqualTo(UPDATED_CUOI_KY_SO_LUONG);
        assertThat(testTonkhodauky.getCuoi_ky_gia_tri()).isEqualTo(DEFAULT_CUOI_KY_GIA_TRI);
        assertThat(testTonkhodauky.getDon_gia_binh_quan()).isEqualTo(UPDATED_DON_GIA_BINH_QUAN);
        assertThat(testTonkhodauky.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testTonkhodauky.getKey_uuid()).isEqualTo(DEFAULT_KEY_UUID);
    }

    @Test
    @Transactional
    void fullUpdateTonkhodaukyWithPatch() throws Exception {
        // Initialize the database
        tonkhodaukyRepository.saveAndFlush(tonkhodauky);

        int databaseSizeBeforeUpdate = tonkhodaukyRepository.findAll().size();

        // Update the tonkhodauky using partial update
        Tonkhodauky partialUpdatedTonkhodauky = new Tonkhodauky();
        partialUpdatedTonkhodauky.setId(tonkhodauky.getId());

        partialUpdatedTonkhodauky
            .ten_kho(UPDATED_TEN_KHO)
            .ma_hang(UPDATED_MA_HANG)
            .ten_hang(UPDATED_TEN_HANG)
            .dvt(UPDATED_DVT)
            .dau_ky_so_luong(UPDATED_DAU_KY_SO_LUONG)
            .dau_ky_gia_tri(UPDATED_DAU_KY_GIA_TRI)
            .nhap_kho_so_luong(UPDATED_NHAP_KHO_SO_LUONG)
            .nhap_kho_gia_tri(UPDATED_NHAP_KHO_GIA_TRI)
            .xuat_kho_so_luong(UPDATED_XUAT_KHO_SO_LUONG)
            .xuat_kho_gia_tri(UPDATED_XUAT_KHO_GIA_TRI)
            .cuoi_ky_so_luong(UPDATED_CUOI_KY_SO_LUONG)
            .cuoi_ky_gia_tri(UPDATED_CUOI_KY_GIA_TRI)
            .don_gia_binh_quan(UPDATED_DON_GIA_BINH_QUAN)
            .createdDate(UPDATED_CREATED_DATE)
            .key_uuid(UPDATED_KEY_UUID);

        restTonkhodaukyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTonkhodauky.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTonkhodauky))
            )
            .andExpect(status().isOk());

        // Validate the Tonkhodauky in the database
        List<Tonkhodauky> tonkhodaukyList = tonkhodaukyRepository.findAll();
        assertThat(tonkhodaukyList).hasSize(databaseSizeBeforeUpdate);
        Tonkhodauky testTonkhodauky = tonkhodaukyList.get(tonkhodaukyList.size() - 1);
        assertThat(testTonkhodauky.getTen_kho()).isEqualTo(UPDATED_TEN_KHO);
        assertThat(testTonkhodauky.getMa_hang()).isEqualTo(UPDATED_MA_HANG);
        assertThat(testTonkhodauky.getTen_hang()).isEqualTo(UPDATED_TEN_HANG);
        assertThat(testTonkhodauky.getDvt()).isEqualTo(UPDATED_DVT);
        assertThat(testTonkhodauky.getDau_ky_so_luong()).isEqualTo(UPDATED_DAU_KY_SO_LUONG);
        assertThat(testTonkhodauky.getDau_ky_gia_tri()).isEqualTo(UPDATED_DAU_KY_GIA_TRI);
        assertThat(testTonkhodauky.getNhap_kho_so_luong()).isEqualTo(UPDATED_NHAP_KHO_SO_LUONG);
        assertThat(testTonkhodauky.getNhap_kho_gia_tri()).isEqualTo(UPDATED_NHAP_KHO_GIA_TRI);
        assertThat(testTonkhodauky.getXuat_kho_so_luong()).isEqualTo(UPDATED_XUAT_KHO_SO_LUONG);
        assertThat(testTonkhodauky.getXuat_kho_gia_tri()).isEqualTo(UPDATED_XUAT_KHO_GIA_TRI);
        assertThat(testTonkhodauky.getCuoi_ky_so_luong()).isEqualTo(UPDATED_CUOI_KY_SO_LUONG);
        assertThat(testTonkhodauky.getCuoi_ky_gia_tri()).isEqualTo(UPDATED_CUOI_KY_GIA_TRI);
        assertThat(testTonkhodauky.getDon_gia_binh_quan()).isEqualTo(UPDATED_DON_GIA_BINH_QUAN);
        assertThat(testTonkhodauky.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testTonkhodauky.getKey_uuid()).isEqualTo(UPDATED_KEY_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingTonkhodauky() throws Exception {
        int databaseSizeBeforeUpdate = tonkhodaukyRepository.findAll().size();
        tonkhodauky.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTonkhodaukyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tonkhodauky.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tonkhodauky))
            )
            .andExpect(status().isBadRequest());

        // Validate the Tonkhodauky in the database
        List<Tonkhodauky> tonkhodaukyList = tonkhodaukyRepository.findAll();
        assertThat(tonkhodaukyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTonkhodauky() throws Exception {
        int databaseSizeBeforeUpdate = tonkhodaukyRepository.findAll().size();
        tonkhodauky.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTonkhodaukyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tonkhodauky))
            )
            .andExpect(status().isBadRequest());

        // Validate the Tonkhodauky in the database
        List<Tonkhodauky> tonkhodaukyList = tonkhodaukyRepository.findAll();
        assertThat(tonkhodaukyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTonkhodauky() throws Exception {
        int databaseSizeBeforeUpdate = tonkhodaukyRepository.findAll().size();
        tonkhodauky.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTonkhodaukyMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tonkhodauky))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Tonkhodauky in the database
        List<Tonkhodauky> tonkhodaukyList = tonkhodaukyRepository.findAll();
        assertThat(tonkhodaukyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTonkhodauky() throws Exception {
        // Initialize the database
        tonkhodaukyRepository.saveAndFlush(tonkhodauky);

        int databaseSizeBeforeDelete = tonkhodaukyRepository.findAll().size();

        // Delete the tonkhodauky
        restTonkhodaukyMockMvc
            .perform(delete(ENTITY_API_URL_ID, tonkhodauky.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Tonkhodauky> tonkhodaukyList = tonkhodaukyRepository.findAll();
        assertThat(tonkhodaukyList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
