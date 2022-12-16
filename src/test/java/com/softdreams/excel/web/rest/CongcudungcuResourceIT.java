package com.softdreams.excel.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.softdreams.excel.IntegrationTest;
import com.softdreams.excel.domain.Congcudungcu;
import com.softdreams.excel.repository.CongcudungcuRepository;
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
 * Integration tests for the {@link CongcudungcuResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CongcudungcuResourceIT {

    private static final String DEFAULT_MA_CCDC = "AAAAAAAAAA";
    private static final String UPDATED_MA_CCDC = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_CCDC = "AAAAAAAAAA";
    private static final String UPDATED_TEN_CCDC = "BBBBBBBBBB";

    private static final String DEFAULT_LOAI_CCDC = "AAAAAAAAAA";
    private static final String UPDATED_LOAI_CCDC = "BBBBBBBBBB";

    private static final String DEFAULT_LY_DO_GHI_TANG = "AAAAAAAAAA";
    private static final String UPDATED_LY_DO_GHI_TANG = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NGAY_GHI_TANG = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_GHI_TANG = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SO_CT_GHI_TANG = "AAAAAAAAAA";
    private static final String UPDATED_SO_CT_GHI_TANG = "BBBBBBBBBB";

    private static final Integer DEFAULT_SO_KY_PHAN_BO = 1;
    private static final Integer UPDATED_SO_KY_PHAN_BO = 2;

    private static final Integer DEFAULT_SO_KY_PB_CON_LAI = 1;
    private static final Integer UPDATED_SO_KY_PB_CON_LAI = 2;

    private static final String DEFAULT_DVT = "AAAAAAAAAA";
    private static final String UPDATED_DVT = "BBBBBBBBBB";

    private static final Double DEFAULT_SL_GHI_TANG = 1D;
    private static final Double UPDATED_SL_GHI_TANG = 2D;

    private static final Double DEFAULT_LUY_KE_SL_DA_GIAM = 1D;
    private static final Double UPDATED_LUY_KE_SL_DA_GIAM = 2D;

    private static final Double DEFAULT_SL_CON_LAI = 1D;
    private static final Double UPDATED_SL_CON_LAI = 2D;

    private static final Double DEFAULT_GIA_TRI_CCDC = 1D;
    private static final Double UPDATED_GIA_TRI_CCDC = 2D;

    private static final Double DEFAULT_GIA_TRI_PB_HANG_KY = 1D;
    private static final Double UPDATED_GIA_TRI_PB_HANG_KY = 2D;

    private static final Double DEFAULT_PB_TRONG_KY = 1D;
    private static final Double UPDATED_PB_TRONG_KY = 2D;

    private static final Double DEFAULT_LUY_KE_DA_PB = 1D;
    private static final Double UPDATED_LUY_KE_DA_PB = 2D;

    private static final Double DEFAULT_GIA_TRI_CON_LAI = 1D;
    private static final Double UPDATED_GIA_TRI_CON_LAI = 2D;

    private static final String DEFAULT_TK_CHO_PHAN_BO = "AAAAAAAAAA";
    private static final String UPDATED_TK_CHO_PHAN_BO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_KEY_UUID = "AAAAAAAAAA";
    private static final String UPDATED_KEY_UUID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/congcudungcus";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CongcudungcuRepository congcudungcuRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCongcudungcuMockMvc;

    private Congcudungcu congcudungcu;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Congcudungcu createEntity(EntityManager em) {
        Congcudungcu congcudungcu = new Congcudungcu()
            .ma_ccdc(DEFAULT_MA_CCDC)
            .ten_ccdc(DEFAULT_TEN_CCDC)
            .loai_ccdc(DEFAULT_LOAI_CCDC)
            .ly_do_ghi_tang(DEFAULT_LY_DO_GHI_TANG)
            .ngay_ghi_tang(DEFAULT_NGAY_GHI_TANG)
            .so_ct_ghi_tang(DEFAULT_SO_CT_GHI_TANG)
            .so_ky_phan_bo(DEFAULT_SO_KY_PHAN_BO)
            .so_ky_pb_con_lai(DEFAULT_SO_KY_PB_CON_LAI)
            .dvt(DEFAULT_DVT)
            .sl_ghi_tang(DEFAULT_SL_GHI_TANG)
            .luy_ke_sl_da_giam(DEFAULT_LUY_KE_SL_DA_GIAM)
            .sl_con_lai(DEFAULT_SL_CON_LAI)
            .gia_tri_ccdc(DEFAULT_GIA_TRI_CCDC)
            .gia_tri_PB_hang_ky(DEFAULT_GIA_TRI_PB_HANG_KY)
            .pb_trong_ky(DEFAULT_PB_TRONG_KY)
            .luy_ke_da_pb(DEFAULT_LUY_KE_DA_PB)
            .gia_tri_con_lai(DEFAULT_GIA_TRI_CON_LAI)
            .tk_cho_phan_bo(DEFAULT_TK_CHO_PHAN_BO)
            .createdDate(DEFAULT_CREATED_DATE)
            .key_uuid(DEFAULT_KEY_UUID);
        return congcudungcu;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Congcudungcu createUpdatedEntity(EntityManager em) {
        Congcudungcu congcudungcu = new Congcudungcu()
            .ma_ccdc(UPDATED_MA_CCDC)
            .ten_ccdc(UPDATED_TEN_CCDC)
            .loai_ccdc(UPDATED_LOAI_CCDC)
            .ly_do_ghi_tang(UPDATED_LY_DO_GHI_TANG)
            .ngay_ghi_tang(UPDATED_NGAY_GHI_TANG)
            .so_ct_ghi_tang(UPDATED_SO_CT_GHI_TANG)
            .so_ky_phan_bo(UPDATED_SO_KY_PHAN_BO)
            .so_ky_pb_con_lai(UPDATED_SO_KY_PB_CON_LAI)
            .dvt(UPDATED_DVT)
            .sl_ghi_tang(UPDATED_SL_GHI_TANG)
            .luy_ke_sl_da_giam(UPDATED_LUY_KE_SL_DA_GIAM)
            .sl_con_lai(UPDATED_SL_CON_LAI)
            .gia_tri_ccdc(UPDATED_GIA_TRI_CCDC)
            .gia_tri_PB_hang_ky(UPDATED_GIA_TRI_PB_HANG_KY)
            .pb_trong_ky(UPDATED_PB_TRONG_KY)
            .luy_ke_da_pb(UPDATED_LUY_KE_DA_PB)
            .gia_tri_con_lai(UPDATED_GIA_TRI_CON_LAI)
            .tk_cho_phan_bo(UPDATED_TK_CHO_PHAN_BO)
            .createdDate(UPDATED_CREATED_DATE)
            .key_uuid(UPDATED_KEY_UUID);
        return congcudungcu;
    }

    @BeforeEach
    public void initTest() {
        congcudungcu = createEntity(em);
    }

    @Test
    @Transactional
    void createCongcudungcu() throws Exception {
        int databaseSizeBeforeCreate = congcudungcuRepository.findAll().size();
        // Create the Congcudungcu
        restCongcudungcuMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(congcudungcu)))
            .andExpect(status().isCreated());

        // Validate the Congcudungcu in the database
        List<Congcudungcu> congcudungcuList = congcudungcuRepository.findAll();
        assertThat(congcudungcuList).hasSize(databaseSizeBeforeCreate + 1);
        Congcudungcu testCongcudungcu = congcudungcuList.get(congcudungcuList.size() - 1);
        assertThat(testCongcudungcu.getMa_ccdc()).isEqualTo(DEFAULT_MA_CCDC);
        assertThat(testCongcudungcu.getTen_ccdc()).isEqualTo(DEFAULT_TEN_CCDC);
        assertThat(testCongcudungcu.getLoai_ccdc()).isEqualTo(DEFAULT_LOAI_CCDC);
        assertThat(testCongcudungcu.getLy_do_ghi_tang()).isEqualTo(DEFAULT_LY_DO_GHI_TANG);
        assertThat(testCongcudungcu.getNgay_ghi_tang()).isEqualTo(DEFAULT_NGAY_GHI_TANG);
        assertThat(testCongcudungcu.getSo_ct_ghi_tang()).isEqualTo(DEFAULT_SO_CT_GHI_TANG);
        assertThat(testCongcudungcu.getSo_ky_phan_bo()).isEqualTo(DEFAULT_SO_KY_PHAN_BO);
        assertThat(testCongcudungcu.getSo_ky_pb_con_lai()).isEqualTo(DEFAULT_SO_KY_PB_CON_LAI);
        assertThat(testCongcudungcu.getDvt()).isEqualTo(DEFAULT_DVT);
        assertThat(testCongcudungcu.getSl_ghi_tang()).isEqualTo(DEFAULT_SL_GHI_TANG);
        assertThat(testCongcudungcu.getLuy_ke_sl_da_giam()).isEqualTo(DEFAULT_LUY_KE_SL_DA_GIAM);
        assertThat(testCongcudungcu.getSl_con_lai()).isEqualTo(DEFAULT_SL_CON_LAI);
        assertThat(testCongcudungcu.getGia_tri_ccdc()).isEqualTo(DEFAULT_GIA_TRI_CCDC);
        assertThat(testCongcudungcu.getGia_tri_PB_hang_ky()).isEqualTo(DEFAULT_GIA_TRI_PB_HANG_KY);
        assertThat(testCongcudungcu.getPb_trong_ky()).isEqualTo(DEFAULT_PB_TRONG_KY);
        assertThat(testCongcudungcu.getLuy_ke_da_pb()).isEqualTo(DEFAULT_LUY_KE_DA_PB);
        assertThat(testCongcudungcu.getGia_tri_con_lai()).isEqualTo(DEFAULT_GIA_TRI_CON_LAI);
        assertThat(testCongcudungcu.getTk_cho_phan_bo()).isEqualTo(DEFAULT_TK_CHO_PHAN_BO);
        assertThat(testCongcudungcu.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testCongcudungcu.getKey_uuid()).isEqualTo(DEFAULT_KEY_UUID);
    }

    @Test
    @Transactional
    void createCongcudungcuWithExistingId() throws Exception {
        // Create the Congcudungcu with an existing ID
        congcudungcu.setId(1L);

        int databaseSizeBeforeCreate = congcudungcuRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCongcudungcuMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(congcudungcu)))
            .andExpect(status().isBadRequest());

        // Validate the Congcudungcu in the database
        List<Congcudungcu> congcudungcuList = congcudungcuRepository.findAll();
        assertThat(congcudungcuList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCongcudungcus() throws Exception {
        // Initialize the database
        congcudungcuRepository.saveAndFlush(congcudungcu);

        // Get all the congcudungcuList
        restCongcudungcuMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(congcudungcu.getId().intValue())))
            .andExpect(jsonPath("$.[*].ma_ccdc").value(hasItem(DEFAULT_MA_CCDC)))
            .andExpect(jsonPath("$.[*].ten_ccdc").value(hasItem(DEFAULT_TEN_CCDC)))
            .andExpect(jsonPath("$.[*].loai_ccdc").value(hasItem(DEFAULT_LOAI_CCDC)))
            .andExpect(jsonPath("$.[*].ly_do_ghi_tang").value(hasItem(DEFAULT_LY_DO_GHI_TANG)))
            .andExpect(jsonPath("$.[*].ngay_ghi_tang").value(hasItem(DEFAULT_NGAY_GHI_TANG.toString())))
            .andExpect(jsonPath("$.[*].so_ct_ghi_tang").value(hasItem(DEFAULT_SO_CT_GHI_TANG)))
            .andExpect(jsonPath("$.[*].so_ky_phan_bo").value(hasItem(DEFAULT_SO_KY_PHAN_BO)))
            .andExpect(jsonPath("$.[*].so_ky_pb_con_lai").value(hasItem(DEFAULT_SO_KY_PB_CON_LAI)))
            .andExpect(jsonPath("$.[*].dvt").value(hasItem(DEFAULT_DVT)))
            .andExpect(jsonPath("$.[*].sl_ghi_tang").value(hasItem(DEFAULT_SL_GHI_TANG.doubleValue())))
            .andExpect(jsonPath("$.[*].luy_ke_sl_da_giam").value(hasItem(DEFAULT_LUY_KE_SL_DA_GIAM.doubleValue())))
            .andExpect(jsonPath("$.[*].sl_con_lai").value(hasItem(DEFAULT_SL_CON_LAI.doubleValue())))
            .andExpect(jsonPath("$.[*].gia_tri_ccdc").value(hasItem(DEFAULT_GIA_TRI_CCDC.doubleValue())))
            .andExpect(jsonPath("$.[*].gia_tri_PB_hang_ky").value(hasItem(DEFAULT_GIA_TRI_PB_HANG_KY.doubleValue())))
            .andExpect(jsonPath("$.[*].pb_trong_ky").value(hasItem(DEFAULT_PB_TRONG_KY.doubleValue())))
            .andExpect(jsonPath("$.[*].luy_ke_da_pb").value(hasItem(DEFAULT_LUY_KE_DA_PB.doubleValue())))
            .andExpect(jsonPath("$.[*].gia_tri_con_lai").value(hasItem(DEFAULT_GIA_TRI_CON_LAI.doubleValue())))
            .andExpect(jsonPath("$.[*].tk_cho_phan_bo").value(hasItem(DEFAULT_TK_CHO_PHAN_BO)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].key_uuid").value(hasItem(DEFAULT_KEY_UUID)));
    }

    @Test
    @Transactional
    void getCongcudungcu() throws Exception {
        // Initialize the database
        congcudungcuRepository.saveAndFlush(congcudungcu);

        // Get the congcudungcu
        restCongcudungcuMockMvc
            .perform(get(ENTITY_API_URL_ID, congcudungcu.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(congcudungcu.getId().intValue()))
            .andExpect(jsonPath("$.ma_ccdc").value(DEFAULT_MA_CCDC))
            .andExpect(jsonPath("$.ten_ccdc").value(DEFAULT_TEN_CCDC))
            .andExpect(jsonPath("$.loai_ccdc").value(DEFAULT_LOAI_CCDC))
            .andExpect(jsonPath("$.ly_do_ghi_tang").value(DEFAULT_LY_DO_GHI_TANG))
            .andExpect(jsonPath("$.ngay_ghi_tang").value(DEFAULT_NGAY_GHI_TANG.toString()))
            .andExpect(jsonPath("$.so_ct_ghi_tang").value(DEFAULT_SO_CT_GHI_TANG))
            .andExpect(jsonPath("$.so_ky_phan_bo").value(DEFAULT_SO_KY_PHAN_BO))
            .andExpect(jsonPath("$.so_ky_pb_con_lai").value(DEFAULT_SO_KY_PB_CON_LAI))
            .andExpect(jsonPath("$.dvt").value(DEFAULT_DVT))
            .andExpect(jsonPath("$.sl_ghi_tang").value(DEFAULT_SL_GHI_TANG.doubleValue()))
            .andExpect(jsonPath("$.luy_ke_sl_da_giam").value(DEFAULT_LUY_KE_SL_DA_GIAM.doubleValue()))
            .andExpect(jsonPath("$.sl_con_lai").value(DEFAULT_SL_CON_LAI.doubleValue()))
            .andExpect(jsonPath("$.gia_tri_ccdc").value(DEFAULT_GIA_TRI_CCDC.doubleValue()))
            .andExpect(jsonPath("$.gia_tri_PB_hang_ky").value(DEFAULT_GIA_TRI_PB_HANG_KY.doubleValue()))
            .andExpect(jsonPath("$.pb_trong_ky").value(DEFAULT_PB_TRONG_KY.doubleValue()))
            .andExpect(jsonPath("$.luy_ke_da_pb").value(DEFAULT_LUY_KE_DA_PB.doubleValue()))
            .andExpect(jsonPath("$.gia_tri_con_lai").value(DEFAULT_GIA_TRI_CON_LAI.doubleValue()))
            .andExpect(jsonPath("$.tk_cho_phan_bo").value(DEFAULT_TK_CHO_PHAN_BO))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.key_uuid").value(DEFAULT_KEY_UUID));
    }

    @Test
    @Transactional
    void getNonExistingCongcudungcu() throws Exception {
        // Get the congcudungcu
        restCongcudungcuMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCongcudungcu() throws Exception {
        // Initialize the database
        congcudungcuRepository.saveAndFlush(congcudungcu);

        int databaseSizeBeforeUpdate = congcudungcuRepository.findAll().size();

        // Update the congcudungcu
        Congcudungcu updatedCongcudungcu = congcudungcuRepository.findById(congcudungcu.getId()).get();
        // Disconnect from session so that the updates on updatedCongcudungcu are not directly saved in db
        em.detach(updatedCongcudungcu);
        updatedCongcudungcu
            .ma_ccdc(UPDATED_MA_CCDC)
            .ten_ccdc(UPDATED_TEN_CCDC)
            .loai_ccdc(UPDATED_LOAI_CCDC)
            .ly_do_ghi_tang(UPDATED_LY_DO_GHI_TANG)
            .ngay_ghi_tang(UPDATED_NGAY_GHI_TANG)
            .so_ct_ghi_tang(UPDATED_SO_CT_GHI_TANG)
            .so_ky_phan_bo(UPDATED_SO_KY_PHAN_BO)
            .so_ky_pb_con_lai(UPDATED_SO_KY_PB_CON_LAI)
            .dvt(UPDATED_DVT)
            .sl_ghi_tang(UPDATED_SL_GHI_TANG)
            .luy_ke_sl_da_giam(UPDATED_LUY_KE_SL_DA_GIAM)
            .sl_con_lai(UPDATED_SL_CON_LAI)
            .gia_tri_ccdc(UPDATED_GIA_TRI_CCDC)
            .gia_tri_PB_hang_ky(UPDATED_GIA_TRI_PB_HANG_KY)
            .pb_trong_ky(UPDATED_PB_TRONG_KY)
            .luy_ke_da_pb(UPDATED_LUY_KE_DA_PB)
            .gia_tri_con_lai(UPDATED_GIA_TRI_CON_LAI)
            .tk_cho_phan_bo(UPDATED_TK_CHO_PHAN_BO)
            .createdDate(UPDATED_CREATED_DATE)
            .key_uuid(UPDATED_KEY_UUID);

        restCongcudungcuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCongcudungcu.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedCongcudungcu))
            )
            .andExpect(status().isOk());

        // Validate the Congcudungcu in the database
        List<Congcudungcu> congcudungcuList = congcudungcuRepository.findAll();
        assertThat(congcudungcuList).hasSize(databaseSizeBeforeUpdate);
        Congcudungcu testCongcudungcu = congcudungcuList.get(congcudungcuList.size() - 1);
        assertThat(testCongcudungcu.getMa_ccdc()).isEqualTo(UPDATED_MA_CCDC);
        assertThat(testCongcudungcu.getTen_ccdc()).isEqualTo(UPDATED_TEN_CCDC);
        assertThat(testCongcudungcu.getLoai_ccdc()).isEqualTo(UPDATED_LOAI_CCDC);
        assertThat(testCongcudungcu.getLy_do_ghi_tang()).isEqualTo(UPDATED_LY_DO_GHI_TANG);
        assertThat(testCongcudungcu.getNgay_ghi_tang()).isEqualTo(UPDATED_NGAY_GHI_TANG);
        assertThat(testCongcudungcu.getSo_ct_ghi_tang()).isEqualTo(UPDATED_SO_CT_GHI_TANG);
        assertThat(testCongcudungcu.getSo_ky_phan_bo()).isEqualTo(UPDATED_SO_KY_PHAN_BO);
        assertThat(testCongcudungcu.getSo_ky_pb_con_lai()).isEqualTo(UPDATED_SO_KY_PB_CON_LAI);
        assertThat(testCongcudungcu.getDvt()).isEqualTo(UPDATED_DVT);
        assertThat(testCongcudungcu.getSl_ghi_tang()).isEqualTo(UPDATED_SL_GHI_TANG);
        assertThat(testCongcudungcu.getLuy_ke_sl_da_giam()).isEqualTo(UPDATED_LUY_KE_SL_DA_GIAM);
        assertThat(testCongcudungcu.getSl_con_lai()).isEqualTo(UPDATED_SL_CON_LAI);
        assertThat(testCongcudungcu.getGia_tri_ccdc()).isEqualTo(UPDATED_GIA_TRI_CCDC);
        assertThat(testCongcudungcu.getGia_tri_PB_hang_ky()).isEqualTo(UPDATED_GIA_TRI_PB_HANG_KY);
        assertThat(testCongcudungcu.getPb_trong_ky()).isEqualTo(UPDATED_PB_TRONG_KY);
        assertThat(testCongcudungcu.getLuy_ke_da_pb()).isEqualTo(UPDATED_LUY_KE_DA_PB);
        assertThat(testCongcudungcu.getGia_tri_con_lai()).isEqualTo(UPDATED_GIA_TRI_CON_LAI);
        assertThat(testCongcudungcu.getTk_cho_phan_bo()).isEqualTo(UPDATED_TK_CHO_PHAN_BO);
        assertThat(testCongcudungcu.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCongcudungcu.getKey_uuid()).isEqualTo(UPDATED_KEY_UUID);
    }

    @Test
    @Transactional
    void putNonExistingCongcudungcu() throws Exception {
        int databaseSizeBeforeUpdate = congcudungcuRepository.findAll().size();
        congcudungcu.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCongcudungcuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, congcudungcu.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(congcudungcu))
            )
            .andExpect(status().isBadRequest());

        // Validate the Congcudungcu in the database
        List<Congcudungcu> congcudungcuList = congcudungcuRepository.findAll();
        assertThat(congcudungcuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCongcudungcu() throws Exception {
        int databaseSizeBeforeUpdate = congcudungcuRepository.findAll().size();
        congcudungcu.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCongcudungcuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(congcudungcu))
            )
            .andExpect(status().isBadRequest());

        // Validate the Congcudungcu in the database
        List<Congcudungcu> congcudungcuList = congcudungcuRepository.findAll();
        assertThat(congcudungcuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCongcudungcu() throws Exception {
        int databaseSizeBeforeUpdate = congcudungcuRepository.findAll().size();
        congcudungcu.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCongcudungcuMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(congcudungcu)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Congcudungcu in the database
        List<Congcudungcu> congcudungcuList = congcudungcuRepository.findAll();
        assertThat(congcudungcuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCongcudungcuWithPatch() throws Exception {
        // Initialize the database
        congcudungcuRepository.saveAndFlush(congcudungcu);

        int databaseSizeBeforeUpdate = congcudungcuRepository.findAll().size();

        // Update the congcudungcu using partial update
        Congcudungcu partialUpdatedCongcudungcu = new Congcudungcu();
        partialUpdatedCongcudungcu.setId(congcudungcu.getId());

        partialUpdatedCongcudungcu
            .ma_ccdc(UPDATED_MA_CCDC)
            .ngay_ghi_tang(UPDATED_NGAY_GHI_TANG)
            .so_ct_ghi_tang(UPDATED_SO_CT_GHI_TANG)
            .dvt(UPDATED_DVT)
            .luy_ke_sl_da_giam(UPDATED_LUY_KE_SL_DA_GIAM)
            .sl_con_lai(UPDATED_SL_CON_LAI)
            .pb_trong_ky(UPDATED_PB_TRONG_KY)
            .gia_tri_con_lai(UPDATED_GIA_TRI_CON_LAI)
            .tk_cho_phan_bo(UPDATED_TK_CHO_PHAN_BO)
            .key_uuid(UPDATED_KEY_UUID);

        restCongcudungcuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCongcudungcu.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCongcudungcu))
            )
            .andExpect(status().isOk());

        // Validate the Congcudungcu in the database
        List<Congcudungcu> congcudungcuList = congcudungcuRepository.findAll();
        assertThat(congcudungcuList).hasSize(databaseSizeBeforeUpdate);
        Congcudungcu testCongcudungcu = congcudungcuList.get(congcudungcuList.size() - 1);
        assertThat(testCongcudungcu.getMa_ccdc()).isEqualTo(UPDATED_MA_CCDC);
        assertThat(testCongcudungcu.getTen_ccdc()).isEqualTo(DEFAULT_TEN_CCDC);
        assertThat(testCongcudungcu.getLoai_ccdc()).isEqualTo(DEFAULT_LOAI_CCDC);
        assertThat(testCongcudungcu.getLy_do_ghi_tang()).isEqualTo(DEFAULT_LY_DO_GHI_TANG);
        assertThat(testCongcudungcu.getNgay_ghi_tang()).isEqualTo(UPDATED_NGAY_GHI_TANG);
        assertThat(testCongcudungcu.getSo_ct_ghi_tang()).isEqualTo(UPDATED_SO_CT_GHI_TANG);
        assertThat(testCongcudungcu.getSo_ky_phan_bo()).isEqualTo(DEFAULT_SO_KY_PHAN_BO);
        assertThat(testCongcudungcu.getSo_ky_pb_con_lai()).isEqualTo(DEFAULT_SO_KY_PB_CON_LAI);
        assertThat(testCongcudungcu.getDvt()).isEqualTo(UPDATED_DVT);
        assertThat(testCongcudungcu.getSl_ghi_tang()).isEqualTo(DEFAULT_SL_GHI_TANG);
        assertThat(testCongcudungcu.getLuy_ke_sl_da_giam()).isEqualTo(UPDATED_LUY_KE_SL_DA_GIAM);
        assertThat(testCongcudungcu.getSl_con_lai()).isEqualTo(UPDATED_SL_CON_LAI);
        assertThat(testCongcudungcu.getGia_tri_ccdc()).isEqualTo(DEFAULT_GIA_TRI_CCDC);
        assertThat(testCongcudungcu.getGia_tri_PB_hang_ky()).isEqualTo(DEFAULT_GIA_TRI_PB_HANG_KY);
        assertThat(testCongcudungcu.getPb_trong_ky()).isEqualTo(UPDATED_PB_TRONG_KY);
        assertThat(testCongcudungcu.getLuy_ke_da_pb()).isEqualTo(DEFAULT_LUY_KE_DA_PB);
        assertThat(testCongcudungcu.getGia_tri_con_lai()).isEqualTo(UPDATED_GIA_TRI_CON_LAI);
        assertThat(testCongcudungcu.getTk_cho_phan_bo()).isEqualTo(UPDATED_TK_CHO_PHAN_BO);
        assertThat(testCongcudungcu.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testCongcudungcu.getKey_uuid()).isEqualTo(UPDATED_KEY_UUID);
    }

    @Test
    @Transactional
    void fullUpdateCongcudungcuWithPatch() throws Exception {
        // Initialize the database
        congcudungcuRepository.saveAndFlush(congcudungcu);

        int databaseSizeBeforeUpdate = congcudungcuRepository.findAll().size();

        // Update the congcudungcu using partial update
        Congcudungcu partialUpdatedCongcudungcu = new Congcudungcu();
        partialUpdatedCongcudungcu.setId(congcudungcu.getId());

        partialUpdatedCongcudungcu
            .ma_ccdc(UPDATED_MA_CCDC)
            .ten_ccdc(UPDATED_TEN_CCDC)
            .loai_ccdc(UPDATED_LOAI_CCDC)
            .ly_do_ghi_tang(UPDATED_LY_DO_GHI_TANG)
            .ngay_ghi_tang(UPDATED_NGAY_GHI_TANG)
            .so_ct_ghi_tang(UPDATED_SO_CT_GHI_TANG)
            .so_ky_phan_bo(UPDATED_SO_KY_PHAN_BO)
            .so_ky_pb_con_lai(UPDATED_SO_KY_PB_CON_LAI)
            .dvt(UPDATED_DVT)
            .sl_ghi_tang(UPDATED_SL_GHI_TANG)
            .luy_ke_sl_da_giam(UPDATED_LUY_KE_SL_DA_GIAM)
            .sl_con_lai(UPDATED_SL_CON_LAI)
            .gia_tri_ccdc(UPDATED_GIA_TRI_CCDC)
            .gia_tri_PB_hang_ky(UPDATED_GIA_TRI_PB_HANG_KY)
            .pb_trong_ky(UPDATED_PB_TRONG_KY)
            .luy_ke_da_pb(UPDATED_LUY_KE_DA_PB)
            .gia_tri_con_lai(UPDATED_GIA_TRI_CON_LAI)
            .tk_cho_phan_bo(UPDATED_TK_CHO_PHAN_BO)
            .createdDate(UPDATED_CREATED_DATE)
            .key_uuid(UPDATED_KEY_UUID);

        restCongcudungcuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCongcudungcu.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCongcudungcu))
            )
            .andExpect(status().isOk());

        // Validate the Congcudungcu in the database
        List<Congcudungcu> congcudungcuList = congcudungcuRepository.findAll();
        assertThat(congcudungcuList).hasSize(databaseSizeBeforeUpdate);
        Congcudungcu testCongcudungcu = congcudungcuList.get(congcudungcuList.size() - 1);
        assertThat(testCongcudungcu.getMa_ccdc()).isEqualTo(UPDATED_MA_CCDC);
        assertThat(testCongcudungcu.getTen_ccdc()).isEqualTo(UPDATED_TEN_CCDC);
        assertThat(testCongcudungcu.getLoai_ccdc()).isEqualTo(UPDATED_LOAI_CCDC);
        assertThat(testCongcudungcu.getLy_do_ghi_tang()).isEqualTo(UPDATED_LY_DO_GHI_TANG);
        assertThat(testCongcudungcu.getNgay_ghi_tang()).isEqualTo(UPDATED_NGAY_GHI_TANG);
        assertThat(testCongcudungcu.getSo_ct_ghi_tang()).isEqualTo(UPDATED_SO_CT_GHI_TANG);
        assertThat(testCongcudungcu.getSo_ky_phan_bo()).isEqualTo(UPDATED_SO_KY_PHAN_BO);
        assertThat(testCongcudungcu.getSo_ky_pb_con_lai()).isEqualTo(UPDATED_SO_KY_PB_CON_LAI);
        assertThat(testCongcudungcu.getDvt()).isEqualTo(UPDATED_DVT);
        assertThat(testCongcudungcu.getSl_ghi_tang()).isEqualTo(UPDATED_SL_GHI_TANG);
        assertThat(testCongcudungcu.getLuy_ke_sl_da_giam()).isEqualTo(UPDATED_LUY_KE_SL_DA_GIAM);
        assertThat(testCongcudungcu.getSl_con_lai()).isEqualTo(UPDATED_SL_CON_LAI);
        assertThat(testCongcudungcu.getGia_tri_ccdc()).isEqualTo(UPDATED_GIA_TRI_CCDC);
        assertThat(testCongcudungcu.getGia_tri_PB_hang_ky()).isEqualTo(UPDATED_GIA_TRI_PB_HANG_KY);
        assertThat(testCongcudungcu.getPb_trong_ky()).isEqualTo(UPDATED_PB_TRONG_KY);
        assertThat(testCongcudungcu.getLuy_ke_da_pb()).isEqualTo(UPDATED_LUY_KE_DA_PB);
        assertThat(testCongcudungcu.getGia_tri_con_lai()).isEqualTo(UPDATED_GIA_TRI_CON_LAI);
        assertThat(testCongcudungcu.getTk_cho_phan_bo()).isEqualTo(UPDATED_TK_CHO_PHAN_BO);
        assertThat(testCongcudungcu.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCongcudungcu.getKey_uuid()).isEqualTo(UPDATED_KEY_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingCongcudungcu() throws Exception {
        int databaseSizeBeforeUpdate = congcudungcuRepository.findAll().size();
        congcudungcu.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCongcudungcuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, congcudungcu.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(congcudungcu))
            )
            .andExpect(status().isBadRequest());

        // Validate the Congcudungcu in the database
        List<Congcudungcu> congcudungcuList = congcudungcuRepository.findAll();
        assertThat(congcudungcuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCongcudungcu() throws Exception {
        int databaseSizeBeforeUpdate = congcudungcuRepository.findAll().size();
        congcudungcu.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCongcudungcuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(congcudungcu))
            )
            .andExpect(status().isBadRequest());

        // Validate the Congcudungcu in the database
        List<Congcudungcu> congcudungcuList = congcudungcuRepository.findAll();
        assertThat(congcudungcuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCongcudungcu() throws Exception {
        int databaseSizeBeforeUpdate = congcudungcuRepository.findAll().size();
        congcudungcu.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCongcudungcuMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(congcudungcu))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Congcudungcu in the database
        List<Congcudungcu> congcudungcuList = congcudungcuRepository.findAll();
        assertThat(congcudungcuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCongcudungcu() throws Exception {
        // Initialize the database
        congcudungcuRepository.saveAndFlush(congcudungcu);

        int databaseSizeBeforeDelete = congcudungcuRepository.findAll().size();

        // Delete the congcudungcu
        restCongcudungcuMockMvc
            .perform(delete(ENTITY_API_URL_ID, congcudungcu.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Congcudungcu> congcudungcuList = congcudungcuRepository.findAll();
        assertThat(congcudungcuList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
