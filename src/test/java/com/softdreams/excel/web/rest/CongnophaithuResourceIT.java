package com.softdreams.excel.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.softdreams.excel.IntegrationTest;
import com.softdreams.excel.domain.Congnophaithu;
import com.softdreams.excel.repository.CongnophaithuRepository;
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
 * Integration tests for the {@link CongnophaithuResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CongnophaithuResourceIT {

    private static final String DEFAULT_MAKH = "AAAAAAAAAA";
    private static final String UPDATED_MAKH = "BBBBBBBBBB";

    private static final String DEFAULT_TENKH = "AAAAAAAAAA";
    private static final String UPDATED_TENKH = "BBBBBBBBBB";

    private static final String DEFAULT_TKCONGNO = "AAAAAAAAAA";
    private static final String UPDATED_TKCONGNO = "BBBBBBBBBB";

    private static final Double DEFAULT_SODUNODAUKY = 1D;
    private static final Double UPDATED_SODUNODAUKY = 2D;

    private static final Double DEFAULT_SODUCODAUKY = 1D;
    private static final Double UPDATED_SODUCODAUKY = 2D;

    private static final Double DEFAULT_SONOPHATSINH = 1D;
    private static final Double UPDATED_SONOPHATSINH = 2D;

    private static final Double DEFAULT_SOCOPHATSINH = 1D;
    private static final Double UPDATED_SOCOPHATSINH = 2D;

    private static final Double DEFAULT_SODUNOCUOIKY = 1D;
    private static final Double UPDATED_SODUNOCUOIKY = 2D;

    private static final Double DEFAULT_SODUCOCUOIKY = 1D;
    private static final Double UPDATED_SODUCOCUOIKY = 2D;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_KEY_UUID = "AAAAAAAAAA";
    private static final String UPDATED_KEY_UUID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/congnophaithus";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CongnophaithuRepository congnophaithuRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCongnophaithuMockMvc;

    private Congnophaithu congnophaithu;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Congnophaithu createEntity(EntityManager em) {
        Congnophaithu congnophaithu = new Congnophaithu()
            .makh(DEFAULT_MAKH)
            .tenkh(DEFAULT_TENKH)
            .tkcongno(DEFAULT_TKCONGNO)
            .sodunodauky(DEFAULT_SODUNODAUKY)
            .soducodauky(DEFAULT_SODUCODAUKY)
            .sonophatsinh(DEFAULT_SONOPHATSINH)
            .socophatsinh(DEFAULT_SOCOPHATSINH)
            .sodunocuoiky(DEFAULT_SODUNOCUOIKY)
            .soducocuoiky(DEFAULT_SODUCOCUOIKY)
            .createdDate(DEFAULT_CREATED_DATE)
            .key_uuid(DEFAULT_KEY_UUID);
        return congnophaithu;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Congnophaithu createUpdatedEntity(EntityManager em) {
        Congnophaithu congnophaithu = new Congnophaithu()
            .makh(UPDATED_MAKH)
            .tenkh(UPDATED_TENKH)
            .tkcongno(UPDATED_TKCONGNO)
            .sodunodauky(UPDATED_SODUNODAUKY)
            .soducodauky(UPDATED_SODUCODAUKY)
            .sonophatsinh(UPDATED_SONOPHATSINH)
            .socophatsinh(UPDATED_SOCOPHATSINH)
            .sodunocuoiky(UPDATED_SODUNOCUOIKY)
            .soducocuoiky(UPDATED_SODUCOCUOIKY)
            .createdDate(UPDATED_CREATED_DATE)
            .key_uuid(UPDATED_KEY_UUID);
        return congnophaithu;
    }

    @BeforeEach
    public void initTest() {
        congnophaithu = createEntity(em);
    }

    @Test
    @Transactional
    void createCongnophaithu() throws Exception {
        int databaseSizeBeforeCreate = congnophaithuRepository.findAll().size();
        // Create the Congnophaithu
        restCongnophaithuMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(congnophaithu)))
            .andExpect(status().isCreated());

        // Validate the Congnophaithu in the database
        List<Congnophaithu> congnophaithuList = congnophaithuRepository.findAll();
        assertThat(congnophaithuList).hasSize(databaseSizeBeforeCreate + 1);
        Congnophaithu testCongnophaithu = congnophaithuList.get(congnophaithuList.size() - 1);
        assertThat(testCongnophaithu.getMakh()).isEqualTo(DEFAULT_MAKH);
        assertThat(testCongnophaithu.getTenkh()).isEqualTo(DEFAULT_TENKH);
        assertThat(testCongnophaithu.getTkcongno()).isEqualTo(DEFAULT_TKCONGNO);
        assertThat(testCongnophaithu.getSodunodauky()).isEqualTo(DEFAULT_SODUNODAUKY);
        assertThat(testCongnophaithu.getSoducodauky()).isEqualTo(DEFAULT_SODUCODAUKY);
        assertThat(testCongnophaithu.getSonophatsinh()).isEqualTo(DEFAULT_SONOPHATSINH);
        assertThat(testCongnophaithu.getSocophatsinh()).isEqualTo(DEFAULT_SOCOPHATSINH);
        assertThat(testCongnophaithu.getSodunocuoiky()).isEqualTo(DEFAULT_SODUNOCUOIKY);
        assertThat(testCongnophaithu.getSoducocuoiky()).isEqualTo(DEFAULT_SODUCOCUOIKY);
        assertThat(testCongnophaithu.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testCongnophaithu.getKey_uuid()).isEqualTo(DEFAULT_KEY_UUID);
    }

    @Test
    @Transactional
    void createCongnophaithuWithExistingId() throws Exception {
        // Create the Congnophaithu with an existing ID
        congnophaithu.setId(1L);

        int databaseSizeBeforeCreate = congnophaithuRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCongnophaithuMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(congnophaithu)))
            .andExpect(status().isBadRequest());

        // Validate the Congnophaithu in the database
        List<Congnophaithu> congnophaithuList = congnophaithuRepository.findAll();
        assertThat(congnophaithuList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCongnophaithus() throws Exception {
        // Initialize the database
        congnophaithuRepository.saveAndFlush(congnophaithu);

        // Get all the congnophaithuList
        restCongnophaithuMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(congnophaithu.getId().intValue())))
            .andExpect(jsonPath("$.[*].makh").value(hasItem(DEFAULT_MAKH)))
            .andExpect(jsonPath("$.[*].tenkh").value(hasItem(DEFAULT_TENKH)))
            .andExpect(jsonPath("$.[*].tkcongno").value(hasItem(DEFAULT_TKCONGNO)))
            .andExpect(jsonPath("$.[*].sodunodauky").value(hasItem(DEFAULT_SODUNODAUKY.doubleValue())))
            .andExpect(jsonPath("$.[*].soducodauky").value(hasItem(DEFAULT_SODUCODAUKY.doubleValue())))
            .andExpect(jsonPath("$.[*].sonophatsinh").value(hasItem(DEFAULT_SONOPHATSINH.doubleValue())))
            .andExpect(jsonPath("$.[*].socophatsinh").value(hasItem(DEFAULT_SOCOPHATSINH.doubleValue())))
            .andExpect(jsonPath("$.[*].sodunocuoiky").value(hasItem(DEFAULT_SODUNOCUOIKY.doubleValue())))
            .andExpect(jsonPath("$.[*].soducocuoiky").value(hasItem(DEFAULT_SODUCOCUOIKY.doubleValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].key_uuid").value(hasItem(DEFAULT_KEY_UUID)));
    }

    @Test
    @Transactional
    void getCongnophaithu() throws Exception {
        // Initialize the database
        congnophaithuRepository.saveAndFlush(congnophaithu);

        // Get the congnophaithu
        restCongnophaithuMockMvc
            .perform(get(ENTITY_API_URL_ID, congnophaithu.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(congnophaithu.getId().intValue()))
            .andExpect(jsonPath("$.makh").value(DEFAULT_MAKH))
            .andExpect(jsonPath("$.tenkh").value(DEFAULT_TENKH))
            .andExpect(jsonPath("$.tkcongno").value(DEFAULT_TKCONGNO))
            .andExpect(jsonPath("$.sodunodauky").value(DEFAULT_SODUNODAUKY.doubleValue()))
            .andExpect(jsonPath("$.soducodauky").value(DEFAULT_SODUCODAUKY.doubleValue()))
            .andExpect(jsonPath("$.sonophatsinh").value(DEFAULT_SONOPHATSINH.doubleValue()))
            .andExpect(jsonPath("$.socophatsinh").value(DEFAULT_SOCOPHATSINH.doubleValue()))
            .andExpect(jsonPath("$.sodunocuoiky").value(DEFAULT_SODUNOCUOIKY.doubleValue()))
            .andExpect(jsonPath("$.soducocuoiky").value(DEFAULT_SODUCOCUOIKY.doubleValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.key_uuid").value(DEFAULT_KEY_UUID));
    }

    @Test
    @Transactional
    void getNonExistingCongnophaithu() throws Exception {
        // Get the congnophaithu
        restCongnophaithuMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCongnophaithu() throws Exception {
        // Initialize the database
        congnophaithuRepository.saveAndFlush(congnophaithu);

        int databaseSizeBeforeUpdate = congnophaithuRepository.findAll().size();

        // Update the congnophaithu
        Congnophaithu updatedCongnophaithu = congnophaithuRepository.findById(congnophaithu.getId()).get();
        // Disconnect from session so that the updates on updatedCongnophaithu are not directly saved in db
        em.detach(updatedCongnophaithu);
        updatedCongnophaithu
            .makh(UPDATED_MAKH)
            .tenkh(UPDATED_TENKH)
            .tkcongno(UPDATED_TKCONGNO)
            .sodunodauky(UPDATED_SODUNODAUKY)
            .soducodauky(UPDATED_SODUCODAUKY)
            .sonophatsinh(UPDATED_SONOPHATSINH)
            .socophatsinh(UPDATED_SOCOPHATSINH)
            .sodunocuoiky(UPDATED_SODUNOCUOIKY)
            .soducocuoiky(UPDATED_SODUCOCUOIKY)
            .createdDate(UPDATED_CREATED_DATE)
            .key_uuid(UPDATED_KEY_UUID);

        restCongnophaithuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCongnophaithu.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedCongnophaithu))
            )
            .andExpect(status().isOk());

        // Validate the Congnophaithu in the database
        List<Congnophaithu> congnophaithuList = congnophaithuRepository.findAll();
        assertThat(congnophaithuList).hasSize(databaseSizeBeforeUpdate);
        Congnophaithu testCongnophaithu = congnophaithuList.get(congnophaithuList.size() - 1);
        assertThat(testCongnophaithu.getMakh()).isEqualTo(UPDATED_MAKH);
        assertThat(testCongnophaithu.getTenkh()).isEqualTo(UPDATED_TENKH);
        assertThat(testCongnophaithu.getTkcongno()).isEqualTo(UPDATED_TKCONGNO);
        assertThat(testCongnophaithu.getSodunodauky()).isEqualTo(UPDATED_SODUNODAUKY);
        assertThat(testCongnophaithu.getSoducodauky()).isEqualTo(UPDATED_SODUCODAUKY);
        assertThat(testCongnophaithu.getSonophatsinh()).isEqualTo(UPDATED_SONOPHATSINH);
        assertThat(testCongnophaithu.getSocophatsinh()).isEqualTo(UPDATED_SOCOPHATSINH);
        assertThat(testCongnophaithu.getSodunocuoiky()).isEqualTo(UPDATED_SODUNOCUOIKY);
        assertThat(testCongnophaithu.getSoducocuoiky()).isEqualTo(UPDATED_SODUCOCUOIKY);
        assertThat(testCongnophaithu.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCongnophaithu.getKey_uuid()).isEqualTo(UPDATED_KEY_UUID);
    }

    @Test
    @Transactional
    void putNonExistingCongnophaithu() throws Exception {
        int databaseSizeBeforeUpdate = congnophaithuRepository.findAll().size();
        congnophaithu.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCongnophaithuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, congnophaithu.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(congnophaithu))
            )
            .andExpect(status().isBadRequest());

        // Validate the Congnophaithu in the database
        List<Congnophaithu> congnophaithuList = congnophaithuRepository.findAll();
        assertThat(congnophaithuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCongnophaithu() throws Exception {
        int databaseSizeBeforeUpdate = congnophaithuRepository.findAll().size();
        congnophaithu.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCongnophaithuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(congnophaithu))
            )
            .andExpect(status().isBadRequest());

        // Validate the Congnophaithu in the database
        List<Congnophaithu> congnophaithuList = congnophaithuRepository.findAll();
        assertThat(congnophaithuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCongnophaithu() throws Exception {
        int databaseSizeBeforeUpdate = congnophaithuRepository.findAll().size();
        congnophaithu.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCongnophaithuMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(congnophaithu)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Congnophaithu in the database
        List<Congnophaithu> congnophaithuList = congnophaithuRepository.findAll();
        assertThat(congnophaithuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCongnophaithuWithPatch() throws Exception {
        // Initialize the database
        congnophaithuRepository.saveAndFlush(congnophaithu);

        int databaseSizeBeforeUpdate = congnophaithuRepository.findAll().size();

        // Update the congnophaithu using partial update
        Congnophaithu partialUpdatedCongnophaithu = new Congnophaithu();
        partialUpdatedCongnophaithu.setId(congnophaithu.getId());

        partialUpdatedCongnophaithu.sodunocuoiky(UPDATED_SODUNOCUOIKY).createdDate(UPDATED_CREATED_DATE).key_uuid(UPDATED_KEY_UUID);

        restCongnophaithuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCongnophaithu.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCongnophaithu))
            )
            .andExpect(status().isOk());

        // Validate the Congnophaithu in the database
        List<Congnophaithu> congnophaithuList = congnophaithuRepository.findAll();
        assertThat(congnophaithuList).hasSize(databaseSizeBeforeUpdate);
        Congnophaithu testCongnophaithu = congnophaithuList.get(congnophaithuList.size() - 1);
        assertThat(testCongnophaithu.getMakh()).isEqualTo(DEFAULT_MAKH);
        assertThat(testCongnophaithu.getTenkh()).isEqualTo(DEFAULT_TENKH);
        assertThat(testCongnophaithu.getTkcongno()).isEqualTo(DEFAULT_TKCONGNO);
        assertThat(testCongnophaithu.getSodunodauky()).isEqualTo(DEFAULT_SODUNODAUKY);
        assertThat(testCongnophaithu.getSoducodauky()).isEqualTo(DEFAULT_SODUCODAUKY);
        assertThat(testCongnophaithu.getSonophatsinh()).isEqualTo(DEFAULT_SONOPHATSINH);
        assertThat(testCongnophaithu.getSocophatsinh()).isEqualTo(DEFAULT_SOCOPHATSINH);
        assertThat(testCongnophaithu.getSodunocuoiky()).isEqualTo(UPDATED_SODUNOCUOIKY);
        assertThat(testCongnophaithu.getSoducocuoiky()).isEqualTo(DEFAULT_SODUCOCUOIKY);
        assertThat(testCongnophaithu.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCongnophaithu.getKey_uuid()).isEqualTo(UPDATED_KEY_UUID);
    }

    @Test
    @Transactional
    void fullUpdateCongnophaithuWithPatch() throws Exception {
        // Initialize the database
        congnophaithuRepository.saveAndFlush(congnophaithu);

        int databaseSizeBeforeUpdate = congnophaithuRepository.findAll().size();

        // Update the congnophaithu using partial update
        Congnophaithu partialUpdatedCongnophaithu = new Congnophaithu();
        partialUpdatedCongnophaithu.setId(congnophaithu.getId());

        partialUpdatedCongnophaithu
            .makh(UPDATED_MAKH)
            .tenkh(UPDATED_TENKH)
            .tkcongno(UPDATED_TKCONGNO)
            .sodunodauky(UPDATED_SODUNODAUKY)
            .soducodauky(UPDATED_SODUCODAUKY)
            .sonophatsinh(UPDATED_SONOPHATSINH)
            .socophatsinh(UPDATED_SOCOPHATSINH)
            .sodunocuoiky(UPDATED_SODUNOCUOIKY)
            .soducocuoiky(UPDATED_SODUCOCUOIKY)
            .createdDate(UPDATED_CREATED_DATE)
            .key_uuid(UPDATED_KEY_UUID);

        restCongnophaithuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCongnophaithu.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCongnophaithu))
            )
            .andExpect(status().isOk());

        // Validate the Congnophaithu in the database
        List<Congnophaithu> congnophaithuList = congnophaithuRepository.findAll();
        assertThat(congnophaithuList).hasSize(databaseSizeBeforeUpdate);
        Congnophaithu testCongnophaithu = congnophaithuList.get(congnophaithuList.size() - 1);
        assertThat(testCongnophaithu.getMakh()).isEqualTo(UPDATED_MAKH);
        assertThat(testCongnophaithu.getTenkh()).isEqualTo(UPDATED_TENKH);
        assertThat(testCongnophaithu.getTkcongno()).isEqualTo(UPDATED_TKCONGNO);
        assertThat(testCongnophaithu.getSodunodauky()).isEqualTo(UPDATED_SODUNODAUKY);
        assertThat(testCongnophaithu.getSoducodauky()).isEqualTo(UPDATED_SODUCODAUKY);
        assertThat(testCongnophaithu.getSonophatsinh()).isEqualTo(UPDATED_SONOPHATSINH);
        assertThat(testCongnophaithu.getSocophatsinh()).isEqualTo(UPDATED_SOCOPHATSINH);
        assertThat(testCongnophaithu.getSodunocuoiky()).isEqualTo(UPDATED_SODUNOCUOIKY);
        assertThat(testCongnophaithu.getSoducocuoiky()).isEqualTo(UPDATED_SODUCOCUOIKY);
        assertThat(testCongnophaithu.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCongnophaithu.getKey_uuid()).isEqualTo(UPDATED_KEY_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingCongnophaithu() throws Exception {
        int databaseSizeBeforeUpdate = congnophaithuRepository.findAll().size();
        congnophaithu.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCongnophaithuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, congnophaithu.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(congnophaithu))
            )
            .andExpect(status().isBadRequest());

        // Validate the Congnophaithu in the database
        List<Congnophaithu> congnophaithuList = congnophaithuRepository.findAll();
        assertThat(congnophaithuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCongnophaithu() throws Exception {
        int databaseSizeBeforeUpdate = congnophaithuRepository.findAll().size();
        congnophaithu.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCongnophaithuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(congnophaithu))
            )
            .andExpect(status().isBadRequest());

        // Validate the Congnophaithu in the database
        List<Congnophaithu> congnophaithuList = congnophaithuRepository.findAll();
        assertThat(congnophaithuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCongnophaithu() throws Exception {
        int databaseSizeBeforeUpdate = congnophaithuRepository.findAll().size();
        congnophaithu.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCongnophaithuMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(congnophaithu))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Congnophaithu in the database
        List<Congnophaithu> congnophaithuList = congnophaithuRepository.findAll();
        assertThat(congnophaithuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCongnophaithu() throws Exception {
        // Initialize the database
        congnophaithuRepository.saveAndFlush(congnophaithu);

        int databaseSizeBeforeDelete = congnophaithuRepository.findAll().size();

        // Delete the congnophaithu
        restCongnophaithuMockMvc
            .perform(delete(ENTITY_API_URL_ID, congnophaithu.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Congnophaithu> congnophaithuList = congnophaithuRepository.findAll();
        assertThat(congnophaithuList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
