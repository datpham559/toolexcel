package com.softdreams.excel.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.softdreams.excel.IntegrationTest;
import com.softdreams.excel.domain.Taisancodinh;
import com.softdreams.excel.repository.TaisancodinhRepository;
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
 * Integration tests for the {@link TaisancodinhResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TaisancodinhResourceIT {

    private static final String DEFAULT_MA_TSCD = "AAAAAAAAAA";
    private static final String UPDATED_MA_TSCD = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_TSCD = "AAAAAAAAAA";
    private static final String UPDATED_TEN_TSCD = "BBBBBBBBBB";

    private static final String DEFAULT_LOAI_TSCD = "AAAAAAAAAA";
    private static final String UPDATED_LOAI_TSCD = "BBBBBBBBBB";

    private static final String DEFAULT_DON_VI_SU_DUNG = "AAAAAAAAAA";
    private static final String UPDATED_DON_VI_SU_DUNG = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NGAY_GHI_TANG = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_GHI_TANG = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SO_CT_GHI_TANG = "AAAAAAAAAA";
    private static final String UPDATED_SO_CT_GHI_TANG = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NGAY_BAT_DAU_TINH_KH = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_BAT_DAU_TINH_KH = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_THOI_GIAN_SU_DUNG = 1D;
    private static final Double UPDATED_THOI_GIAN_SU_DUNG = 2D;

    private static final Double DEFAULT_THOI_GIAN_SU_DUNG_CON_LAI = 1D;
    private static final Double UPDATED_THOI_GIAN_SU_DUNG_CON_LAI = 2D;

    private static final Double DEFAULT_NGUYEN_GIA = 1D;
    private static final Double UPDATED_NGUYEN_GIA = 2D;

    private static final Double DEFAULT_GIA_TRI_TINH_KH = 1D;
    private static final Double UPDATED_GIA_TRI_TINH_KH = 2D;

    private static final Double DEFAULT_HAO_MON_TRONG_KY = 1D;
    private static final Double UPDATED_HAO_MON_TRONG_KY = 2D;

    private static final Double DEFAULT_HAO_MON_LUY_KE = 1D;
    private static final Double UPDATED_HAO_MON_LUY_KE = 2D;

    private static final Double DEFAULT_GIA_TRI_CON_LAI = 1D;
    private static final Double UPDATED_GIA_TRI_CON_LAI = 2D;

    private static final Double DEFAULT_GIA_TRI_KH_THANG = 1D;
    private static final Double UPDATED_GIA_TRI_KH_THANG = 2D;

    private static final String DEFAULT_TK_NGUYEN_GIA = "AAAAAAAAAA";
    private static final String UPDATED_TK_NGUYEN_GIA = "BBBBBBBBBB";

    private static final String DEFAULT_TK_KHAU_HAO = "AAAAAAAAAA";
    private static final String UPDATED_TK_KHAU_HAO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_KEY_UUID = "AAAAAAAAAA";
    private static final String UPDATED_KEY_UUID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/taisancodinhs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TaisancodinhRepository taisancodinhRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTaisancodinhMockMvc;

    private Taisancodinh taisancodinh;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Taisancodinh createEntity(EntityManager em) {
        Taisancodinh taisancodinh = new Taisancodinh()
            .ma_tscd(DEFAULT_MA_TSCD)
            .ten_tscd(DEFAULT_TEN_TSCD)
            .loai_tscd(DEFAULT_LOAI_TSCD)
            .don_vi_su_dung(DEFAULT_DON_VI_SU_DUNG)
            .ngay_ghi_tang(DEFAULT_NGAY_GHI_TANG)
            .so_ct_ghi_tang(DEFAULT_SO_CT_GHI_TANG)
            .ngay_bat_dau_tinh_kh(DEFAULT_NGAY_BAT_DAU_TINH_KH)
            .thoi_gian_su_dung(DEFAULT_THOI_GIAN_SU_DUNG)
            .thoi_gian_su_dung_con_lai(DEFAULT_THOI_GIAN_SU_DUNG_CON_LAI)
            .nguyen_gia(DEFAULT_NGUYEN_GIA)
            .gia_tri_tinh_kh(DEFAULT_GIA_TRI_TINH_KH)
            .hao_mon_trong_ky(DEFAULT_HAO_MON_TRONG_KY)
            .hao_mon_luy_ke(DEFAULT_HAO_MON_LUY_KE)
            .gia_tri_con_lai(DEFAULT_GIA_TRI_CON_LAI)
            .gia_tri_KH_thang(DEFAULT_GIA_TRI_KH_THANG)
            .tk_nguyen_gia(DEFAULT_TK_NGUYEN_GIA)
            .tk_khau_hao(DEFAULT_TK_KHAU_HAO)
            .createdDate(DEFAULT_CREATED_DATE)
            .key_uuid(DEFAULT_KEY_UUID);
        return taisancodinh;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Taisancodinh createUpdatedEntity(EntityManager em) {
        Taisancodinh taisancodinh = new Taisancodinh()
            .ma_tscd(UPDATED_MA_TSCD)
            .ten_tscd(UPDATED_TEN_TSCD)
            .loai_tscd(UPDATED_LOAI_TSCD)
            .don_vi_su_dung(UPDATED_DON_VI_SU_DUNG)
            .ngay_ghi_tang(UPDATED_NGAY_GHI_TANG)
            .so_ct_ghi_tang(UPDATED_SO_CT_GHI_TANG)
            .ngay_bat_dau_tinh_kh(UPDATED_NGAY_BAT_DAU_TINH_KH)
            .thoi_gian_su_dung(UPDATED_THOI_GIAN_SU_DUNG)
            .thoi_gian_su_dung_con_lai(UPDATED_THOI_GIAN_SU_DUNG_CON_LAI)
            .nguyen_gia(UPDATED_NGUYEN_GIA)
            .gia_tri_tinh_kh(UPDATED_GIA_TRI_TINH_KH)
            .hao_mon_trong_ky(UPDATED_HAO_MON_TRONG_KY)
            .hao_mon_luy_ke(UPDATED_HAO_MON_LUY_KE)
            .gia_tri_con_lai(UPDATED_GIA_TRI_CON_LAI)
            .gia_tri_KH_thang(UPDATED_GIA_TRI_KH_THANG)
            .tk_nguyen_gia(UPDATED_TK_NGUYEN_GIA)
            .tk_khau_hao(UPDATED_TK_KHAU_HAO)
            .createdDate(UPDATED_CREATED_DATE)
            .key_uuid(UPDATED_KEY_UUID);
        return taisancodinh;
    }

    @BeforeEach
    public void initTest() {
        taisancodinh = createEntity(em);
    }

    @Test
    @Transactional
    void createTaisancodinh() throws Exception {
        int databaseSizeBeforeCreate = taisancodinhRepository.findAll().size();
        // Create the Taisancodinh
        restTaisancodinhMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(taisancodinh)))
            .andExpect(status().isCreated());

        // Validate the Taisancodinh in the database
        List<Taisancodinh> taisancodinhList = taisancodinhRepository.findAll();
        assertThat(taisancodinhList).hasSize(databaseSizeBeforeCreate + 1);
        Taisancodinh testTaisancodinh = taisancodinhList.get(taisancodinhList.size() - 1);
        assertThat(testTaisancodinh.getMa_tscd()).isEqualTo(DEFAULT_MA_TSCD);
        assertThat(testTaisancodinh.getTen_tscd()).isEqualTo(DEFAULT_TEN_TSCD);
        assertThat(testTaisancodinh.getLoai_tscd()).isEqualTo(DEFAULT_LOAI_TSCD);
        assertThat(testTaisancodinh.getDon_vi_su_dung()).isEqualTo(DEFAULT_DON_VI_SU_DUNG);
        assertThat(testTaisancodinh.getNgay_ghi_tang()).isEqualTo(DEFAULT_NGAY_GHI_TANG);
        assertThat(testTaisancodinh.getSo_ct_ghi_tang()).isEqualTo(DEFAULT_SO_CT_GHI_TANG);
        assertThat(testTaisancodinh.getNgay_bat_dau_tinh_kh()).isEqualTo(DEFAULT_NGAY_BAT_DAU_TINH_KH);
        assertThat(testTaisancodinh.getThoi_gian_su_dung()).isEqualTo(DEFAULT_THOI_GIAN_SU_DUNG);
        assertThat(testTaisancodinh.getThoi_gian_su_dung_con_lai()).isEqualTo(DEFAULT_THOI_GIAN_SU_DUNG_CON_LAI);
        assertThat(testTaisancodinh.getNguyen_gia()).isEqualTo(DEFAULT_NGUYEN_GIA);
        assertThat(testTaisancodinh.getGia_tri_tinh_kh()).isEqualTo(DEFAULT_GIA_TRI_TINH_KH);
        assertThat(testTaisancodinh.getHao_mon_trong_ky()).isEqualTo(DEFAULT_HAO_MON_TRONG_KY);
        assertThat(testTaisancodinh.getHao_mon_luy_ke()).isEqualTo(DEFAULT_HAO_MON_LUY_KE);
        assertThat(testTaisancodinh.getGia_tri_con_lai()).isEqualTo(DEFAULT_GIA_TRI_CON_LAI);
        assertThat(testTaisancodinh.getGia_tri_KH_thang()).isEqualTo(DEFAULT_GIA_TRI_KH_THANG);
        assertThat(testTaisancodinh.getTk_nguyen_gia()).isEqualTo(DEFAULT_TK_NGUYEN_GIA);
        assertThat(testTaisancodinh.getTk_khau_hao()).isEqualTo(DEFAULT_TK_KHAU_HAO);
        assertThat(testTaisancodinh.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testTaisancodinh.getKey_uuid()).isEqualTo(DEFAULT_KEY_UUID);
    }

    @Test
    @Transactional
    void createTaisancodinhWithExistingId() throws Exception {
        // Create the Taisancodinh with an existing ID
        taisancodinh.setId(1L);

        int databaseSizeBeforeCreate = taisancodinhRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaisancodinhMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(taisancodinh)))
            .andExpect(status().isBadRequest());

        // Validate the Taisancodinh in the database
        List<Taisancodinh> taisancodinhList = taisancodinhRepository.findAll();
        assertThat(taisancodinhList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTaisancodinhs() throws Exception {
        // Initialize the database
        taisancodinhRepository.saveAndFlush(taisancodinh);

        // Get all the taisancodinhList
        restTaisancodinhMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(taisancodinh.getId().intValue())))
            .andExpect(jsonPath("$.[*].ma_tscd").value(hasItem(DEFAULT_MA_TSCD)))
            .andExpect(jsonPath("$.[*].ten_tscd").value(hasItem(DEFAULT_TEN_TSCD)))
            .andExpect(jsonPath("$.[*].loai_tscd").value(hasItem(DEFAULT_LOAI_TSCD)))
            .andExpect(jsonPath("$.[*].don_vi_su_dung").value(hasItem(DEFAULT_DON_VI_SU_DUNG)))
            .andExpect(jsonPath("$.[*].ngay_ghi_tang").value(hasItem(DEFAULT_NGAY_GHI_TANG.toString())))
            .andExpect(jsonPath("$.[*].so_ct_ghi_tang").value(hasItem(DEFAULT_SO_CT_GHI_TANG)))
            .andExpect(jsonPath("$.[*].ngay_bat_dau_tinh_kh").value(hasItem(DEFAULT_NGAY_BAT_DAU_TINH_KH.toString())))
            .andExpect(jsonPath("$.[*].thoi_gian_su_dung").value(hasItem(DEFAULT_THOI_GIAN_SU_DUNG.doubleValue())))
            .andExpect(jsonPath("$.[*].thoi_gian_su_dung_con_lai").value(hasItem(DEFAULT_THOI_GIAN_SU_DUNG_CON_LAI.doubleValue())))
            .andExpect(jsonPath("$.[*].nguyen_gia").value(hasItem(DEFAULT_NGUYEN_GIA.doubleValue())))
            .andExpect(jsonPath("$.[*].gia_tri_tinh_kh").value(hasItem(DEFAULT_GIA_TRI_TINH_KH.doubleValue())))
            .andExpect(jsonPath("$.[*].hao_mon_trong_ky").value(hasItem(DEFAULT_HAO_MON_TRONG_KY.doubleValue())))
            .andExpect(jsonPath("$.[*].hao_mon_luy_ke").value(hasItem(DEFAULT_HAO_MON_LUY_KE.doubleValue())))
            .andExpect(jsonPath("$.[*].gia_tri_con_lai").value(hasItem(DEFAULT_GIA_TRI_CON_LAI.doubleValue())))
            .andExpect(jsonPath("$.[*].gia_tri_KH_thang").value(hasItem(DEFAULT_GIA_TRI_KH_THANG.doubleValue())))
            .andExpect(jsonPath("$.[*].tk_nguyen_gia").value(hasItem(DEFAULT_TK_NGUYEN_GIA)))
            .andExpect(jsonPath("$.[*].tk_khau_hao").value(hasItem(DEFAULT_TK_KHAU_HAO)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].key_uuid").value(hasItem(DEFAULT_KEY_UUID)));
    }

    @Test
    @Transactional
    void getTaisancodinh() throws Exception {
        // Initialize the database
        taisancodinhRepository.saveAndFlush(taisancodinh);

        // Get the taisancodinh
        restTaisancodinhMockMvc
            .perform(get(ENTITY_API_URL_ID, taisancodinh.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(taisancodinh.getId().intValue()))
            .andExpect(jsonPath("$.ma_tscd").value(DEFAULT_MA_TSCD))
            .andExpect(jsonPath("$.ten_tscd").value(DEFAULT_TEN_TSCD))
            .andExpect(jsonPath("$.loai_tscd").value(DEFAULT_LOAI_TSCD))
            .andExpect(jsonPath("$.don_vi_su_dung").value(DEFAULT_DON_VI_SU_DUNG))
            .andExpect(jsonPath("$.ngay_ghi_tang").value(DEFAULT_NGAY_GHI_TANG.toString()))
            .andExpect(jsonPath("$.so_ct_ghi_tang").value(DEFAULT_SO_CT_GHI_TANG))
            .andExpect(jsonPath("$.ngay_bat_dau_tinh_kh").value(DEFAULT_NGAY_BAT_DAU_TINH_KH.toString()))
            .andExpect(jsonPath("$.thoi_gian_su_dung").value(DEFAULT_THOI_GIAN_SU_DUNG.doubleValue()))
            .andExpect(jsonPath("$.thoi_gian_su_dung_con_lai").value(DEFAULT_THOI_GIAN_SU_DUNG_CON_LAI.doubleValue()))
            .andExpect(jsonPath("$.nguyen_gia").value(DEFAULT_NGUYEN_GIA.doubleValue()))
            .andExpect(jsonPath("$.gia_tri_tinh_kh").value(DEFAULT_GIA_TRI_TINH_KH.doubleValue()))
            .andExpect(jsonPath("$.hao_mon_trong_ky").value(DEFAULT_HAO_MON_TRONG_KY.doubleValue()))
            .andExpect(jsonPath("$.hao_mon_luy_ke").value(DEFAULT_HAO_MON_LUY_KE.doubleValue()))
            .andExpect(jsonPath("$.gia_tri_con_lai").value(DEFAULT_GIA_TRI_CON_LAI.doubleValue()))
            .andExpect(jsonPath("$.gia_tri_KH_thang").value(DEFAULT_GIA_TRI_KH_THANG.doubleValue()))
            .andExpect(jsonPath("$.tk_nguyen_gia").value(DEFAULT_TK_NGUYEN_GIA))
            .andExpect(jsonPath("$.tk_khau_hao").value(DEFAULT_TK_KHAU_HAO))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.key_uuid").value(DEFAULT_KEY_UUID));
    }

    @Test
    @Transactional
    void getNonExistingTaisancodinh() throws Exception {
        // Get the taisancodinh
        restTaisancodinhMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTaisancodinh() throws Exception {
        // Initialize the database
        taisancodinhRepository.saveAndFlush(taisancodinh);

        int databaseSizeBeforeUpdate = taisancodinhRepository.findAll().size();

        // Update the taisancodinh
        Taisancodinh updatedTaisancodinh = taisancodinhRepository.findById(taisancodinh.getId()).get();
        // Disconnect from session so that the updates on updatedTaisancodinh are not directly saved in db
        em.detach(updatedTaisancodinh);
        updatedTaisancodinh
            .ma_tscd(UPDATED_MA_TSCD)
            .ten_tscd(UPDATED_TEN_TSCD)
            .loai_tscd(UPDATED_LOAI_TSCD)
            .don_vi_su_dung(UPDATED_DON_VI_SU_DUNG)
            .ngay_ghi_tang(UPDATED_NGAY_GHI_TANG)
            .so_ct_ghi_tang(UPDATED_SO_CT_GHI_TANG)
            .ngay_bat_dau_tinh_kh(UPDATED_NGAY_BAT_DAU_TINH_KH)
            .thoi_gian_su_dung(UPDATED_THOI_GIAN_SU_DUNG)
            .thoi_gian_su_dung_con_lai(UPDATED_THOI_GIAN_SU_DUNG_CON_LAI)
            .nguyen_gia(UPDATED_NGUYEN_GIA)
            .gia_tri_tinh_kh(UPDATED_GIA_TRI_TINH_KH)
            .hao_mon_trong_ky(UPDATED_HAO_MON_TRONG_KY)
            .hao_mon_luy_ke(UPDATED_HAO_MON_LUY_KE)
            .gia_tri_con_lai(UPDATED_GIA_TRI_CON_LAI)
            .gia_tri_KH_thang(UPDATED_GIA_TRI_KH_THANG)
            .tk_nguyen_gia(UPDATED_TK_NGUYEN_GIA)
            .tk_khau_hao(UPDATED_TK_KHAU_HAO)
            .createdDate(UPDATED_CREATED_DATE)
            .key_uuid(UPDATED_KEY_UUID);

        restTaisancodinhMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTaisancodinh.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTaisancodinh))
            )
            .andExpect(status().isOk());

        // Validate the Taisancodinh in the database
        List<Taisancodinh> taisancodinhList = taisancodinhRepository.findAll();
        assertThat(taisancodinhList).hasSize(databaseSizeBeforeUpdate);
        Taisancodinh testTaisancodinh = taisancodinhList.get(taisancodinhList.size() - 1);
        assertThat(testTaisancodinh.getMa_tscd()).isEqualTo(UPDATED_MA_TSCD);
        assertThat(testTaisancodinh.getTen_tscd()).isEqualTo(UPDATED_TEN_TSCD);
        assertThat(testTaisancodinh.getLoai_tscd()).isEqualTo(UPDATED_LOAI_TSCD);
        assertThat(testTaisancodinh.getDon_vi_su_dung()).isEqualTo(UPDATED_DON_VI_SU_DUNG);
        assertThat(testTaisancodinh.getNgay_ghi_tang()).isEqualTo(UPDATED_NGAY_GHI_TANG);
        assertThat(testTaisancodinh.getSo_ct_ghi_tang()).isEqualTo(UPDATED_SO_CT_GHI_TANG);
        assertThat(testTaisancodinh.getNgay_bat_dau_tinh_kh()).isEqualTo(UPDATED_NGAY_BAT_DAU_TINH_KH);
        assertThat(testTaisancodinh.getThoi_gian_su_dung()).isEqualTo(UPDATED_THOI_GIAN_SU_DUNG);
        assertThat(testTaisancodinh.getThoi_gian_su_dung_con_lai()).isEqualTo(UPDATED_THOI_GIAN_SU_DUNG_CON_LAI);
        assertThat(testTaisancodinh.getNguyen_gia()).isEqualTo(UPDATED_NGUYEN_GIA);
        assertThat(testTaisancodinh.getGia_tri_tinh_kh()).isEqualTo(UPDATED_GIA_TRI_TINH_KH);
        assertThat(testTaisancodinh.getHao_mon_trong_ky()).isEqualTo(UPDATED_HAO_MON_TRONG_KY);
        assertThat(testTaisancodinh.getHao_mon_luy_ke()).isEqualTo(UPDATED_HAO_MON_LUY_KE);
        assertThat(testTaisancodinh.getGia_tri_con_lai()).isEqualTo(UPDATED_GIA_TRI_CON_LAI);
        assertThat(testTaisancodinh.getGia_tri_KH_thang()).isEqualTo(UPDATED_GIA_TRI_KH_THANG);
        assertThat(testTaisancodinh.getTk_nguyen_gia()).isEqualTo(UPDATED_TK_NGUYEN_GIA);
        assertThat(testTaisancodinh.getTk_khau_hao()).isEqualTo(UPDATED_TK_KHAU_HAO);
        assertThat(testTaisancodinh.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testTaisancodinh.getKey_uuid()).isEqualTo(UPDATED_KEY_UUID);
    }

    @Test
    @Transactional
    void putNonExistingTaisancodinh() throws Exception {
        int databaseSizeBeforeUpdate = taisancodinhRepository.findAll().size();
        taisancodinh.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaisancodinhMockMvc
            .perform(
                put(ENTITY_API_URL_ID, taisancodinh.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taisancodinh))
            )
            .andExpect(status().isBadRequest());

        // Validate the Taisancodinh in the database
        List<Taisancodinh> taisancodinhList = taisancodinhRepository.findAll();
        assertThat(taisancodinhList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTaisancodinh() throws Exception {
        int databaseSizeBeforeUpdate = taisancodinhRepository.findAll().size();
        taisancodinh.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaisancodinhMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taisancodinh))
            )
            .andExpect(status().isBadRequest());

        // Validate the Taisancodinh in the database
        List<Taisancodinh> taisancodinhList = taisancodinhRepository.findAll();
        assertThat(taisancodinhList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTaisancodinh() throws Exception {
        int databaseSizeBeforeUpdate = taisancodinhRepository.findAll().size();
        taisancodinh.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaisancodinhMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(taisancodinh)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Taisancodinh in the database
        List<Taisancodinh> taisancodinhList = taisancodinhRepository.findAll();
        assertThat(taisancodinhList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTaisancodinhWithPatch() throws Exception {
        // Initialize the database
        taisancodinhRepository.saveAndFlush(taisancodinh);

        int databaseSizeBeforeUpdate = taisancodinhRepository.findAll().size();

        // Update the taisancodinh using partial update
        Taisancodinh partialUpdatedTaisancodinh = new Taisancodinh();
        partialUpdatedTaisancodinh.setId(taisancodinh.getId());

        partialUpdatedTaisancodinh
            .ma_tscd(UPDATED_MA_TSCD)
            .ten_tscd(UPDATED_TEN_TSCD)
            .loai_tscd(UPDATED_LOAI_TSCD)
            .don_vi_su_dung(UPDATED_DON_VI_SU_DUNG)
            .thoi_gian_su_dung(UPDATED_THOI_GIAN_SU_DUNG)
            .nguyen_gia(UPDATED_NGUYEN_GIA)
            .gia_tri_tinh_kh(UPDATED_GIA_TRI_TINH_KH)
            .gia_tri_con_lai(UPDATED_GIA_TRI_CON_LAI)
            .gia_tri_KH_thang(UPDATED_GIA_TRI_KH_THANG)
            .tk_khau_hao(UPDATED_TK_KHAU_HAO)
            .key_uuid(UPDATED_KEY_UUID);

        restTaisancodinhMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaisancodinh.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTaisancodinh))
            )
            .andExpect(status().isOk());

        // Validate the Taisancodinh in the database
        List<Taisancodinh> taisancodinhList = taisancodinhRepository.findAll();
        assertThat(taisancodinhList).hasSize(databaseSizeBeforeUpdate);
        Taisancodinh testTaisancodinh = taisancodinhList.get(taisancodinhList.size() - 1);
        assertThat(testTaisancodinh.getMa_tscd()).isEqualTo(UPDATED_MA_TSCD);
        assertThat(testTaisancodinh.getTen_tscd()).isEqualTo(UPDATED_TEN_TSCD);
        assertThat(testTaisancodinh.getLoai_tscd()).isEqualTo(UPDATED_LOAI_TSCD);
        assertThat(testTaisancodinh.getDon_vi_su_dung()).isEqualTo(UPDATED_DON_VI_SU_DUNG);
        assertThat(testTaisancodinh.getNgay_ghi_tang()).isEqualTo(DEFAULT_NGAY_GHI_TANG);
        assertThat(testTaisancodinh.getSo_ct_ghi_tang()).isEqualTo(DEFAULT_SO_CT_GHI_TANG);
        assertThat(testTaisancodinh.getNgay_bat_dau_tinh_kh()).isEqualTo(DEFAULT_NGAY_BAT_DAU_TINH_KH);
        assertThat(testTaisancodinh.getThoi_gian_su_dung()).isEqualTo(UPDATED_THOI_GIAN_SU_DUNG);
        assertThat(testTaisancodinh.getThoi_gian_su_dung_con_lai()).isEqualTo(DEFAULT_THOI_GIAN_SU_DUNG_CON_LAI);
        assertThat(testTaisancodinh.getNguyen_gia()).isEqualTo(UPDATED_NGUYEN_GIA);
        assertThat(testTaisancodinh.getGia_tri_tinh_kh()).isEqualTo(UPDATED_GIA_TRI_TINH_KH);
        assertThat(testTaisancodinh.getHao_mon_trong_ky()).isEqualTo(DEFAULT_HAO_MON_TRONG_KY);
        assertThat(testTaisancodinh.getHao_mon_luy_ke()).isEqualTo(DEFAULT_HAO_MON_LUY_KE);
        assertThat(testTaisancodinh.getGia_tri_con_lai()).isEqualTo(UPDATED_GIA_TRI_CON_LAI);
        assertThat(testTaisancodinh.getGia_tri_KH_thang()).isEqualTo(UPDATED_GIA_TRI_KH_THANG);
        assertThat(testTaisancodinh.getTk_nguyen_gia()).isEqualTo(DEFAULT_TK_NGUYEN_GIA);
        assertThat(testTaisancodinh.getTk_khau_hao()).isEqualTo(UPDATED_TK_KHAU_HAO);
        assertThat(testTaisancodinh.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testTaisancodinh.getKey_uuid()).isEqualTo(UPDATED_KEY_UUID);
    }

    @Test
    @Transactional
    void fullUpdateTaisancodinhWithPatch() throws Exception {
        // Initialize the database
        taisancodinhRepository.saveAndFlush(taisancodinh);

        int databaseSizeBeforeUpdate = taisancodinhRepository.findAll().size();

        // Update the taisancodinh using partial update
        Taisancodinh partialUpdatedTaisancodinh = new Taisancodinh();
        partialUpdatedTaisancodinh.setId(taisancodinh.getId());

        partialUpdatedTaisancodinh
            .ma_tscd(UPDATED_MA_TSCD)
            .ten_tscd(UPDATED_TEN_TSCD)
            .loai_tscd(UPDATED_LOAI_TSCD)
            .don_vi_su_dung(UPDATED_DON_VI_SU_DUNG)
            .ngay_ghi_tang(UPDATED_NGAY_GHI_TANG)
            .so_ct_ghi_tang(UPDATED_SO_CT_GHI_TANG)
            .ngay_bat_dau_tinh_kh(UPDATED_NGAY_BAT_DAU_TINH_KH)
            .thoi_gian_su_dung(UPDATED_THOI_GIAN_SU_DUNG)
            .thoi_gian_su_dung_con_lai(UPDATED_THOI_GIAN_SU_DUNG_CON_LAI)
            .nguyen_gia(UPDATED_NGUYEN_GIA)
            .gia_tri_tinh_kh(UPDATED_GIA_TRI_TINH_KH)
            .hao_mon_trong_ky(UPDATED_HAO_MON_TRONG_KY)
            .hao_mon_luy_ke(UPDATED_HAO_MON_LUY_KE)
            .gia_tri_con_lai(UPDATED_GIA_TRI_CON_LAI)
            .gia_tri_KH_thang(UPDATED_GIA_TRI_KH_THANG)
            .tk_nguyen_gia(UPDATED_TK_NGUYEN_GIA)
            .tk_khau_hao(UPDATED_TK_KHAU_HAO)
            .createdDate(UPDATED_CREATED_DATE)
            .key_uuid(UPDATED_KEY_UUID);

        restTaisancodinhMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaisancodinh.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTaisancodinh))
            )
            .andExpect(status().isOk());

        // Validate the Taisancodinh in the database
        List<Taisancodinh> taisancodinhList = taisancodinhRepository.findAll();
        assertThat(taisancodinhList).hasSize(databaseSizeBeforeUpdate);
        Taisancodinh testTaisancodinh = taisancodinhList.get(taisancodinhList.size() - 1);
        assertThat(testTaisancodinh.getMa_tscd()).isEqualTo(UPDATED_MA_TSCD);
        assertThat(testTaisancodinh.getTen_tscd()).isEqualTo(UPDATED_TEN_TSCD);
        assertThat(testTaisancodinh.getLoai_tscd()).isEqualTo(UPDATED_LOAI_TSCD);
        assertThat(testTaisancodinh.getDon_vi_su_dung()).isEqualTo(UPDATED_DON_VI_SU_DUNG);
        assertThat(testTaisancodinh.getNgay_ghi_tang()).isEqualTo(UPDATED_NGAY_GHI_TANG);
        assertThat(testTaisancodinh.getSo_ct_ghi_tang()).isEqualTo(UPDATED_SO_CT_GHI_TANG);
        assertThat(testTaisancodinh.getNgay_bat_dau_tinh_kh()).isEqualTo(UPDATED_NGAY_BAT_DAU_TINH_KH);
        assertThat(testTaisancodinh.getThoi_gian_su_dung()).isEqualTo(UPDATED_THOI_GIAN_SU_DUNG);
        assertThat(testTaisancodinh.getThoi_gian_su_dung_con_lai()).isEqualTo(UPDATED_THOI_GIAN_SU_DUNG_CON_LAI);
        assertThat(testTaisancodinh.getNguyen_gia()).isEqualTo(UPDATED_NGUYEN_GIA);
        assertThat(testTaisancodinh.getGia_tri_tinh_kh()).isEqualTo(UPDATED_GIA_TRI_TINH_KH);
        assertThat(testTaisancodinh.getHao_mon_trong_ky()).isEqualTo(UPDATED_HAO_MON_TRONG_KY);
        assertThat(testTaisancodinh.getHao_mon_luy_ke()).isEqualTo(UPDATED_HAO_MON_LUY_KE);
        assertThat(testTaisancodinh.getGia_tri_con_lai()).isEqualTo(UPDATED_GIA_TRI_CON_LAI);
        assertThat(testTaisancodinh.getGia_tri_KH_thang()).isEqualTo(UPDATED_GIA_TRI_KH_THANG);
        assertThat(testTaisancodinh.getTk_nguyen_gia()).isEqualTo(UPDATED_TK_NGUYEN_GIA);
        assertThat(testTaisancodinh.getTk_khau_hao()).isEqualTo(UPDATED_TK_KHAU_HAO);
        assertThat(testTaisancodinh.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testTaisancodinh.getKey_uuid()).isEqualTo(UPDATED_KEY_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingTaisancodinh() throws Exception {
        int databaseSizeBeforeUpdate = taisancodinhRepository.findAll().size();
        taisancodinh.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaisancodinhMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, taisancodinh.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(taisancodinh))
            )
            .andExpect(status().isBadRequest());

        // Validate the Taisancodinh in the database
        List<Taisancodinh> taisancodinhList = taisancodinhRepository.findAll();
        assertThat(taisancodinhList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTaisancodinh() throws Exception {
        int databaseSizeBeforeUpdate = taisancodinhRepository.findAll().size();
        taisancodinh.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaisancodinhMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(taisancodinh))
            )
            .andExpect(status().isBadRequest());

        // Validate the Taisancodinh in the database
        List<Taisancodinh> taisancodinhList = taisancodinhRepository.findAll();
        assertThat(taisancodinhList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTaisancodinh() throws Exception {
        int databaseSizeBeforeUpdate = taisancodinhRepository.findAll().size();
        taisancodinh.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaisancodinhMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(taisancodinh))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Taisancodinh in the database
        List<Taisancodinh> taisancodinhList = taisancodinhRepository.findAll();
        assertThat(taisancodinhList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTaisancodinh() throws Exception {
        // Initialize the database
        taisancodinhRepository.saveAndFlush(taisancodinh);

        int databaseSizeBeforeDelete = taisancodinhRepository.findAll().size();

        // Delete the taisancodinh
        restTaisancodinhMockMvc
            .perform(delete(ENTITY_API_URL_ID, taisancodinh.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Taisancodinh> taisancodinhList = taisancodinhRepository.findAll();
        assertThat(taisancodinhList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
