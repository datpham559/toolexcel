package com.softdreams.excel.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.softdreams.excel.IntegrationTest;
import com.softdreams.excel.domain.DmNCC;
import com.softdreams.excel.repository.DmNCCRepository;
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
 * Integration tests for the {@link DmNCCResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DmNCCResourceIT {

    private static final String DEFAULT_SUPPLIER_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SUPPLIER_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SUPPLIER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SUPPLIER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_GROUP_KH_NCC = "AAAAAAAAAA";
    private static final String UPDATED_GROUP_KH_NCC = "BBBBBBBBBB";

    private static final String DEFAULT_TAX_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TAX_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final Boolean DEFAULT_UNFOLLOW = false;
    private static final Boolean UPDATED_UNFOLLOW = true;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_KEY_UUID = "AAAAAAAAAA";
    private static final String UPDATED_KEY_UUID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/dm-nccs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DmNCCRepository dmNCCRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDmNCCMockMvc;

    private DmNCC dmNCC;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DmNCC createEntity(EntityManager em) {
        DmNCC dmNCC = new DmNCC()
            .supplier_code(DEFAULT_SUPPLIER_CODE)
            .supplier_name(DEFAULT_SUPPLIER_NAME)
            .address(DEFAULT_ADDRESS)
            .group_kh_ncc(DEFAULT_GROUP_KH_NCC)
            .tax_code(DEFAULT_TAX_CODE)
            .phone_number(DEFAULT_PHONE_NUMBER)
            .unfollow(DEFAULT_UNFOLLOW)
            .created_date(DEFAULT_CREATED_DATE)
            .keyUUID(DEFAULT_KEY_UUID);
        return dmNCC;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DmNCC createUpdatedEntity(EntityManager em) {
        DmNCC dmNCC = new DmNCC()
            .supplier_code(UPDATED_SUPPLIER_CODE)
            .supplier_name(UPDATED_SUPPLIER_NAME)
            .address(UPDATED_ADDRESS)
            .group_kh_ncc(UPDATED_GROUP_KH_NCC)
            .tax_code(UPDATED_TAX_CODE)
            .phone_number(UPDATED_PHONE_NUMBER)
            .unfollow(UPDATED_UNFOLLOW)
            .created_date(UPDATED_CREATED_DATE)
            .keyUUID(UPDATED_KEY_UUID);
        return dmNCC;
    }

    @BeforeEach
    public void initTest() {
        dmNCC = createEntity(em);
    }

    @Test
    @Transactional
    void createDmNCC() throws Exception {
        int databaseSizeBeforeCreate = dmNCCRepository.findAll().size();
        // Create the DmNCC
        restDmNCCMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(dmNCC)))
            .andExpect(status().isCreated());

        // Validate the DmNCC in the database
        List<DmNCC> dmNCCList = dmNCCRepository.findAll();
        assertThat(dmNCCList).hasSize(databaseSizeBeforeCreate + 1);
        DmNCC testDmNCC = dmNCCList.get(dmNCCList.size() - 1);
        assertThat(testDmNCC.getSupplier_code()).isEqualTo(DEFAULT_SUPPLIER_CODE);
        assertThat(testDmNCC.getSupplier_name()).isEqualTo(DEFAULT_SUPPLIER_NAME);
        assertThat(testDmNCC.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testDmNCC.getGroup_kh_ncc()).isEqualTo(DEFAULT_GROUP_KH_NCC);
        assertThat(testDmNCC.getTax_code()).isEqualTo(DEFAULT_TAX_CODE);
        assertThat(testDmNCC.getPhone_number()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testDmNCC.getUnfollow()).isEqualTo(DEFAULT_UNFOLLOW);
        assertThat(testDmNCC.getCreated_date()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testDmNCC.getKeyUUID()).isEqualTo(DEFAULT_KEY_UUID);
    }

    @Test
    @Transactional
    void createDmNCCWithExistingId() throws Exception {
        // Create the DmNCC with an existing ID
        dmNCC.setId(1L);

        int databaseSizeBeforeCreate = dmNCCRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDmNCCMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(dmNCC)))
            .andExpect(status().isBadRequest());

        // Validate the DmNCC in the database
        List<DmNCC> dmNCCList = dmNCCRepository.findAll();
        assertThat(dmNCCList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDmNCCS() throws Exception {
        // Initialize the database
        dmNCCRepository.saveAndFlush(dmNCC);

        // Get all the dmNCCList
        restDmNCCMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dmNCC.getId().intValue())))
            .andExpect(jsonPath("$.[*].supplier_code").value(hasItem(DEFAULT_SUPPLIER_CODE)))
            .andExpect(jsonPath("$.[*].supplier_name").value(hasItem(DEFAULT_SUPPLIER_NAME)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].group_kh_ncc").value(hasItem(DEFAULT_GROUP_KH_NCC)))
            .andExpect(jsonPath("$.[*].tax_code").value(hasItem(DEFAULT_TAX_CODE)))
            .andExpect(jsonPath("$.[*].phone_number").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].unfollow").value(hasItem(DEFAULT_UNFOLLOW.booleanValue())))
            .andExpect(jsonPath("$.[*].created_date").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].keyUUID").value(hasItem(DEFAULT_KEY_UUID)));
    }

    @Test
    @Transactional
    void getDmNCC() throws Exception {
        // Initialize the database
        dmNCCRepository.saveAndFlush(dmNCC);

        // Get the dmNCC
        restDmNCCMockMvc
            .perform(get(ENTITY_API_URL_ID, dmNCC.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dmNCC.getId().intValue()))
            .andExpect(jsonPath("$.supplier_code").value(DEFAULT_SUPPLIER_CODE))
            .andExpect(jsonPath("$.supplier_name").value(DEFAULT_SUPPLIER_NAME))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.group_kh_ncc").value(DEFAULT_GROUP_KH_NCC))
            .andExpect(jsonPath("$.tax_code").value(DEFAULT_TAX_CODE))
            .andExpect(jsonPath("$.phone_number").value(DEFAULT_PHONE_NUMBER))
            .andExpect(jsonPath("$.unfollow").value(DEFAULT_UNFOLLOW.booleanValue()))
            .andExpect(jsonPath("$.created_date").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.keyUUID").value(DEFAULT_KEY_UUID));
    }

    @Test
    @Transactional
    void getNonExistingDmNCC() throws Exception {
        // Get the dmNCC
        restDmNCCMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDmNCC() throws Exception {
        // Initialize the database
        dmNCCRepository.saveAndFlush(dmNCC);

        int databaseSizeBeforeUpdate = dmNCCRepository.findAll().size();

        // Update the dmNCC
        DmNCC updatedDmNCC = dmNCCRepository.findById(dmNCC.getId()).get();
        // Disconnect from session so that the updates on updatedDmNCC are not directly saved in db
        em.detach(updatedDmNCC);
        updatedDmNCC
            .supplier_code(UPDATED_SUPPLIER_CODE)
            .supplier_name(UPDATED_SUPPLIER_NAME)
            .address(UPDATED_ADDRESS)
            .group_kh_ncc(UPDATED_GROUP_KH_NCC)
            .tax_code(UPDATED_TAX_CODE)
            .phone_number(UPDATED_PHONE_NUMBER)
            .unfollow(UPDATED_UNFOLLOW)
            .created_date(UPDATED_CREATED_DATE)
            .keyUUID(UPDATED_KEY_UUID);

        restDmNCCMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedDmNCC.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedDmNCC))
            )
            .andExpect(status().isOk());

        // Validate the DmNCC in the database
        List<DmNCC> dmNCCList = dmNCCRepository.findAll();
        assertThat(dmNCCList).hasSize(databaseSizeBeforeUpdate);
        DmNCC testDmNCC = dmNCCList.get(dmNCCList.size() - 1);
        assertThat(testDmNCC.getSupplier_code()).isEqualTo(UPDATED_SUPPLIER_CODE);
        assertThat(testDmNCC.getSupplier_name()).isEqualTo(UPDATED_SUPPLIER_NAME);
        assertThat(testDmNCC.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testDmNCC.getGroup_kh_ncc()).isEqualTo(UPDATED_GROUP_KH_NCC);
        assertThat(testDmNCC.getTax_code()).isEqualTo(UPDATED_TAX_CODE);
        assertThat(testDmNCC.getPhone_number()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testDmNCC.getUnfollow()).isEqualTo(UPDATED_UNFOLLOW);
        assertThat(testDmNCC.getCreated_date()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDmNCC.getKeyUUID()).isEqualTo(UPDATED_KEY_UUID);
    }

    @Test
    @Transactional
    void putNonExistingDmNCC() throws Exception {
        int databaseSizeBeforeUpdate = dmNCCRepository.findAll().size();
        dmNCC.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDmNCCMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dmNCC.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dmNCC))
            )
            .andExpect(status().isBadRequest());

        // Validate the DmNCC in the database
        List<DmNCC> dmNCCList = dmNCCRepository.findAll();
        assertThat(dmNCCList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDmNCC() throws Exception {
        int databaseSizeBeforeUpdate = dmNCCRepository.findAll().size();
        dmNCC.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDmNCCMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dmNCC))
            )
            .andExpect(status().isBadRequest());

        // Validate the DmNCC in the database
        List<DmNCC> dmNCCList = dmNCCRepository.findAll();
        assertThat(dmNCCList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDmNCC() throws Exception {
        int databaseSizeBeforeUpdate = dmNCCRepository.findAll().size();
        dmNCC.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDmNCCMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(dmNCC)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DmNCC in the database
        List<DmNCC> dmNCCList = dmNCCRepository.findAll();
        assertThat(dmNCCList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDmNCCWithPatch() throws Exception {
        // Initialize the database
        dmNCCRepository.saveAndFlush(dmNCC);

        int databaseSizeBeforeUpdate = dmNCCRepository.findAll().size();

        // Update the dmNCC using partial update
        DmNCC partialUpdatedDmNCC = new DmNCC();
        partialUpdatedDmNCC.setId(dmNCC.getId());

        partialUpdatedDmNCC
            .supplier_name(UPDATED_SUPPLIER_NAME)
            .group_kh_ncc(UPDATED_GROUP_KH_NCC)
            .phone_number(UPDATED_PHONE_NUMBER)
            .unfollow(UPDATED_UNFOLLOW)
            .created_date(UPDATED_CREATED_DATE);

        restDmNCCMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDmNCC.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDmNCC))
            )
            .andExpect(status().isOk());

        // Validate the DmNCC in the database
        List<DmNCC> dmNCCList = dmNCCRepository.findAll();
        assertThat(dmNCCList).hasSize(databaseSizeBeforeUpdate);
        DmNCC testDmNCC = dmNCCList.get(dmNCCList.size() - 1);
        assertThat(testDmNCC.getSupplier_code()).isEqualTo(DEFAULT_SUPPLIER_CODE);
        assertThat(testDmNCC.getSupplier_name()).isEqualTo(UPDATED_SUPPLIER_NAME);
        assertThat(testDmNCC.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testDmNCC.getGroup_kh_ncc()).isEqualTo(UPDATED_GROUP_KH_NCC);
        assertThat(testDmNCC.getTax_code()).isEqualTo(DEFAULT_TAX_CODE);
        assertThat(testDmNCC.getPhone_number()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testDmNCC.getUnfollow()).isEqualTo(UPDATED_UNFOLLOW);
        assertThat(testDmNCC.getCreated_date()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDmNCC.getKeyUUID()).isEqualTo(DEFAULT_KEY_UUID);
    }

    @Test
    @Transactional
    void fullUpdateDmNCCWithPatch() throws Exception {
        // Initialize the database
        dmNCCRepository.saveAndFlush(dmNCC);

        int databaseSizeBeforeUpdate = dmNCCRepository.findAll().size();

        // Update the dmNCC using partial update
        DmNCC partialUpdatedDmNCC = new DmNCC();
        partialUpdatedDmNCC.setId(dmNCC.getId());

        partialUpdatedDmNCC
            .supplier_code(UPDATED_SUPPLIER_CODE)
            .supplier_name(UPDATED_SUPPLIER_NAME)
            .address(UPDATED_ADDRESS)
            .group_kh_ncc(UPDATED_GROUP_KH_NCC)
            .tax_code(UPDATED_TAX_CODE)
            .phone_number(UPDATED_PHONE_NUMBER)
            .unfollow(UPDATED_UNFOLLOW)
            .created_date(UPDATED_CREATED_DATE)
            .keyUUID(UPDATED_KEY_UUID);

        restDmNCCMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDmNCC.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDmNCC))
            )
            .andExpect(status().isOk());

        // Validate the DmNCC in the database
        List<DmNCC> dmNCCList = dmNCCRepository.findAll();
        assertThat(dmNCCList).hasSize(databaseSizeBeforeUpdate);
        DmNCC testDmNCC = dmNCCList.get(dmNCCList.size() - 1);
        assertThat(testDmNCC.getSupplier_code()).isEqualTo(UPDATED_SUPPLIER_CODE);
        assertThat(testDmNCC.getSupplier_name()).isEqualTo(UPDATED_SUPPLIER_NAME);
        assertThat(testDmNCC.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testDmNCC.getGroup_kh_ncc()).isEqualTo(UPDATED_GROUP_KH_NCC);
        assertThat(testDmNCC.getTax_code()).isEqualTo(UPDATED_TAX_CODE);
        assertThat(testDmNCC.getPhone_number()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testDmNCC.getUnfollow()).isEqualTo(UPDATED_UNFOLLOW);
        assertThat(testDmNCC.getCreated_date()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDmNCC.getKeyUUID()).isEqualTo(UPDATED_KEY_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingDmNCC() throws Exception {
        int databaseSizeBeforeUpdate = dmNCCRepository.findAll().size();
        dmNCC.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDmNCCMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, dmNCC.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(dmNCC))
            )
            .andExpect(status().isBadRequest());

        // Validate the DmNCC in the database
        List<DmNCC> dmNCCList = dmNCCRepository.findAll();
        assertThat(dmNCCList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDmNCC() throws Exception {
        int databaseSizeBeforeUpdate = dmNCCRepository.findAll().size();
        dmNCC.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDmNCCMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(dmNCC))
            )
            .andExpect(status().isBadRequest());

        // Validate the DmNCC in the database
        List<DmNCC> dmNCCList = dmNCCRepository.findAll();
        assertThat(dmNCCList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDmNCC() throws Exception {
        int databaseSizeBeforeUpdate = dmNCCRepository.findAll().size();
        dmNCC.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDmNCCMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(dmNCC)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DmNCC in the database
        List<DmNCC> dmNCCList = dmNCCRepository.findAll();
        assertThat(dmNCCList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDmNCC() throws Exception {
        // Initialize the database
        dmNCCRepository.saveAndFlush(dmNCC);

        int databaseSizeBeforeDelete = dmNCCRepository.findAll().size();

        // Delete the dmNCC
        restDmNCCMockMvc
            .perform(delete(ENTITY_API_URL_ID, dmNCC.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DmNCC> dmNCCList = dmNCCRepository.findAll();
        assertThat(dmNCCList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
