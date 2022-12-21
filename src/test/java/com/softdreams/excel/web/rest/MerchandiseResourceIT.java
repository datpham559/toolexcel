package com.softdreams.excel.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.softdreams.excel.IntegrationTest;
import com.softdreams.excel.domain.Merchandise;
import com.softdreams.excel.repository.MerchandiseRepository;
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
 * Integration tests for the {@link MerchandiseResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MerchandiseResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_NATURE = "AAAAAAAAAA";
    private static final String UPDATED_NATURE = "BBBBBBBBBB";

    private static final String DEFAULT_GROUP_VTHH = "AAAAAAAAAA";
    private static final String UPDATED_GROUP_VTHH = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIBE = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIBE = "BBBBBBBBBB";

    private static final String DEFAULT_EXPLAIN_BUY = "AAAAAAAAAA";
    private static final String UPDATED_EXPLAIN_BUY = "BBBBBBBBBB";

    private static final String DEFAULT_EXPLAIN_SELL = "AAAAAAAAAA";
    private static final String UPDATED_EXPLAIN_SELL = "BBBBBBBBBB";

    private static final String DEFAULT_MAIN_DVT = "AAAAAAAAAA";
    private static final String UPDATED_MAIN_DVT = "BBBBBBBBBB";

    private static final String DEFAULT_WARRANTY_PERIOD = "AAAAAAAAAA";
    private static final String UPDATED_WARRANTY_PERIOD = "BBBBBBBBBB";

    private static final Double DEFAULT_QUANTITY_INVENTORY = 1D;
    private static final Double UPDATED_QUANTITY_INVENTORY = 2D;

    private static final String DEFAULT_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE = "BBBBBBBBBB";

    private static final String DEFAULT_IMPLICITLY_REPOSITORY = "AAAAAAAAAA";
    private static final String UPDATED_IMPLICITLY_REPOSITORY = "BBBBBBBBBB";

    private static final String DEFAULT_WAREHOUSE_ACCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_WAREHOUSE_ACCOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_EXPENSE_ACCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_EXPENSE_ACCOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_INCOME_ACCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_INCOME_ACCOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_DISCOUNT_ACCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_DISCOUNT_ACCOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_SALE_ACCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_SALE_ACCOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_RETURN_ACCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_RETURN_ACCOUNT = "BBBBBBBBBB";

    private static final Double DEFAULT_RATE_CKMH = 1D;
    private static final Double UPDATED_RATE_CKMH = 2D;

    private static final Double DEFAULT_FIXED_PURCHASE_PRICE = 1D;
    private static final Double UPDATED_FIXED_PURCHASE_PRICE = 2D;

    private static final Double DEFAULT_LATEST_PURCHASE_PRICE = 1D;
    private static final Double UPDATED_LATEST_PURCHASE_PRICE = 2D;

    private static final Double DEFAULT_UNIT_PRICE_SELL_1 = 1D;
    private static final Double UPDATED_UNIT_PRICE_SELL_1 = 2D;

    private static final Double DEFAULT_UNIT_PRICE_SELL_2 = 1D;
    private static final Double UPDATED_UNIT_PRICE_SELL_2 = 2D;

    private static final Double DEFAULT_UNIT_PRICE_SELL_3 = 1D;
    private static final Double UPDATED_UNIT_PRICE_SELL_3 = 2D;

    private static final Double DEFAULT_FIXED_UNIT_PRICE = 1D;
    private static final Double UPDATED_FIXED_UNIT_PRICE = 2D;

    private static final Double DEFAULT_UNIT_PRICE_AFTER_TAX = 1D;
    private static final Double UPDATED_UNIT_PRICE_AFTER_TAX = 2D;

    private static final String DEFAULT_TAX_RATE_GTGT = "AAAAAAAAAA";
    private static final String UPDATED_TAX_RATE_GTGT = "BBBBBBBBBB";

    private static final Double DEFAULT_TAX_RATE_NK = 1D;
    private static final Double UPDATED_TAX_RATE_NK = 2D;

    private static final Double DEFAULT_TAX_RATE_XK = 1D;
    private static final Double UPDATED_TAX_RATE_XK = 2D;

    private static final String DEFAULT_GROUP_HHDV_TAXABLE_TTDB = "AAAAAAAAAA";
    private static final String UPDATED_GROUP_HHDV_TAXABLE_TTDB = "BBBBBBBBBB";

    private static final Double DEFAULT_UNFOLLOW = 1D;
    private static final Double UPDATED_UNFOLLOW = 2D;

    private static final Double DEFAULT_INVENTORY_NUMBER = 1D;
    private static final Double UPDATED_INVENTORY_NUMBER = 2D;

    private static final String DEFAULT_CHARACTERISTIC = "AAAAAAAAAA";
    private static final String UPDATED_CHARACTERISTIC = "BBBBBBBBBB";

    private static final Double DEFAULT_INVENTORY_VALUE = 1D;
    private static final Double UPDATED_INVENTORY_VALUE = 2D;

    private static final Double DEFAULT_FOLLOW = 1D;
    private static final Double UPDATED_FOLLOW = 2D;

    private static final Double DEFAULT_DISCOUNT = 1D;
    private static final Double UPDATED_DISCOUNT = 2D;

    private static final String DEFAULT_FROM_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_FROM_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_TO_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_TO_AMOUNT = "BBBBBBBBBB";

    private static final Double DEFAULT_PERCENT_DISCOUNT = 1D;
    private static final Double UPDATED_PERCENT_DISCOUNT = 2D;

    private static final Double DEFAULT_DISCOUNT_AMOUNT = 1D;
    private static final Double UPDATED_DISCOUNT_AMOUNT = 2D;

    private static final String DEFAULT_CONVERSION_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_CONVERSION_UNIT = "BBBBBBBBBB";

    private static final Double DEFAULT_PRIMARY_UNIT_CONVERSION_RATE = 1D;
    private static final Double UPDATED_PRIMARY_UNIT_CONVERSION_RATE = 2D;

    private static final String DEFAULT_CALCULATION = "AAAAAAAAAA";
    private static final String UPDATED_CALCULATION = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIBE_1 = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIBE_1 = "BBBBBBBBBB";

    private static final Double DEFAULT_UNIT_PRICE_1 = 1D;
    private static final Double UPDATED_UNIT_PRICE_1 = 2D;

    private static final Double DEFAULT_UNIT_PRICE_2 = 1D;
    private static final Double UPDATED_UNIT_PRICE_2 = 2D;

    private static final Double DEFAULT_UNIT_PRICE_3 = 1D;
    private static final Double UPDATED_UNIT_PRICE_3 = 2D;

    private static final Double DEFAULT_FIXED_UNIT_PRICE_1 = 1D;
    private static final Double UPDATED_FIXED_UNIT_PRICE_1 = 2D;

    private static final String DEFAULT_MATERIAL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_MATERIAL_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_MATERIAL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MATERIAL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DVT = "AAAAAAAAAA";
    private static final String UPDATED_DVT = "BBBBBBBBBB";

    private static final String DEFAULT_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_SPECIFICATION_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SPECIFICATION_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DISPLAY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DISPLAY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ALLOW_SAME = "AAAAAAAAAA";
    private static final String UPDATED_ALLOW_SAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_KEY_UUID = "AAAAAAAAAA";
    private static final String UPDATED_KEY_UUID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/merchandises";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private MerchandiseRepository merchandiseRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMerchandiseMockMvc;

    private Merchandise merchandise;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Merchandise createEntity(EntityManager em) {
        Merchandise merchandise = new Merchandise()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .nature(DEFAULT_NATURE)
            .group_vthh(DEFAULT_GROUP_VTHH)
            .describe(DEFAULT_DESCRIBE)
            .explain_buy(DEFAULT_EXPLAIN_BUY)
            .explain_sell(DEFAULT_EXPLAIN_SELL)
            .main_dvt(DEFAULT_MAIN_DVT)
            .warranty_period(DEFAULT_WARRANTY_PERIOD)
            .quantity_inventory(DEFAULT_QUANTITY_INVENTORY)
            .source(DEFAULT_SOURCE)
            .implicitly_repository(DEFAULT_IMPLICITLY_REPOSITORY)
            .warehouse_account(DEFAULT_WAREHOUSE_ACCOUNT)
            .expense_account(DEFAULT_EXPENSE_ACCOUNT)
            .income_account(DEFAULT_INCOME_ACCOUNT)
            .discount_account(DEFAULT_DISCOUNT_ACCOUNT)
            .sale_account(DEFAULT_SALE_ACCOUNT)
            .return_account(DEFAULT_RETURN_ACCOUNT)
            .rate_ckmh(DEFAULT_RATE_CKMH)
            .fixed_purchase_price(DEFAULT_FIXED_PURCHASE_PRICE)
            .latest_purchase_price(DEFAULT_LATEST_PURCHASE_PRICE)
            .unit_price_sell_1(DEFAULT_UNIT_PRICE_SELL_1)
            .unit_price_sell_2(DEFAULT_UNIT_PRICE_SELL_2)
            .unit_price_sell_3(DEFAULT_UNIT_PRICE_SELL_3)
            .fixed_unit_price(DEFAULT_FIXED_UNIT_PRICE)
            .unit_price_after_tax(DEFAULT_UNIT_PRICE_AFTER_TAX)
            .tax_rate_gtgt(DEFAULT_TAX_RATE_GTGT)
            .tax_rate_nk(DEFAULT_TAX_RATE_NK)
            .tax_rate_xk(DEFAULT_TAX_RATE_XK)
            .group_hhdv_taxable_ttdb(DEFAULT_GROUP_HHDV_TAXABLE_TTDB)
            .unfollow(DEFAULT_UNFOLLOW)
            .inventory_number(DEFAULT_INVENTORY_NUMBER)
            .characteristic(DEFAULT_CHARACTERISTIC)
            .inventory_value(DEFAULT_INVENTORY_VALUE)
            .follow(DEFAULT_FOLLOW)
            .discount(DEFAULT_DISCOUNT)
            .from_amount(DEFAULT_FROM_AMOUNT)
            .to_amount(DEFAULT_TO_AMOUNT)
            .percent_discount(DEFAULT_PERCENT_DISCOUNT)
            .discount_amount(DEFAULT_DISCOUNT_AMOUNT)
            .conversion_unit(DEFAULT_CONVERSION_UNIT)
            .primary_unit_conversion_rate(DEFAULT_PRIMARY_UNIT_CONVERSION_RATE)
            .calculation(DEFAULT_CALCULATION)
            .describe1(DEFAULT_DESCRIBE_1)
            .unit_price_1(DEFAULT_UNIT_PRICE_1)
            .unit_price_2(DEFAULT_UNIT_PRICE_2)
            .unit_price_3(DEFAULT_UNIT_PRICE_3)
            .fixed_unit_price1(DEFAULT_FIXED_UNIT_PRICE_1)
            .material_code(DEFAULT_MATERIAL_CODE)
            .material_name(DEFAULT_MATERIAL_NAME)
            .dvt(DEFAULT_DVT)
            .amount(DEFAULT_AMOUNT)
            .specification_code(DEFAULT_SPECIFICATION_CODE)
            .display_name(DEFAULT_DISPLAY_NAME)
            .allow_same(DEFAULT_ALLOW_SAME)
            .created_date(DEFAULT_CREATED_DATE)
            .keyUUID(DEFAULT_KEY_UUID);
        return merchandise;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Merchandise createUpdatedEntity(EntityManager em) {
        Merchandise merchandise = new Merchandise()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .nature(UPDATED_NATURE)
            .group_vthh(UPDATED_GROUP_VTHH)
            .describe(UPDATED_DESCRIBE)
            .explain_buy(UPDATED_EXPLAIN_BUY)
            .explain_sell(UPDATED_EXPLAIN_SELL)
            .main_dvt(UPDATED_MAIN_DVT)
            .warranty_period(UPDATED_WARRANTY_PERIOD)
            .quantity_inventory(UPDATED_QUANTITY_INVENTORY)
            .source(UPDATED_SOURCE)
            .implicitly_repository(UPDATED_IMPLICITLY_REPOSITORY)
            .warehouse_account(UPDATED_WAREHOUSE_ACCOUNT)
            .expense_account(UPDATED_EXPENSE_ACCOUNT)
            .income_account(UPDATED_INCOME_ACCOUNT)
            .discount_account(UPDATED_DISCOUNT_ACCOUNT)
            .sale_account(UPDATED_SALE_ACCOUNT)
            .return_account(UPDATED_RETURN_ACCOUNT)
            .rate_ckmh(UPDATED_RATE_CKMH)
            .fixed_purchase_price(UPDATED_FIXED_PURCHASE_PRICE)
            .latest_purchase_price(UPDATED_LATEST_PURCHASE_PRICE)
            .unit_price_sell_1(UPDATED_UNIT_PRICE_SELL_1)
            .unit_price_sell_2(UPDATED_UNIT_PRICE_SELL_2)
            .unit_price_sell_3(UPDATED_UNIT_PRICE_SELL_3)
            .fixed_unit_price(UPDATED_FIXED_UNIT_PRICE)
            .unit_price_after_tax(UPDATED_UNIT_PRICE_AFTER_TAX)
            .tax_rate_gtgt(UPDATED_TAX_RATE_GTGT)
            .tax_rate_nk(UPDATED_TAX_RATE_NK)
            .tax_rate_xk(UPDATED_TAX_RATE_XK)
            .group_hhdv_taxable_ttdb(UPDATED_GROUP_HHDV_TAXABLE_TTDB)
            .unfollow(UPDATED_UNFOLLOW)
            .inventory_number(UPDATED_INVENTORY_NUMBER)
            .characteristic(UPDATED_CHARACTERISTIC)
            .inventory_value(UPDATED_INVENTORY_VALUE)
            .follow(UPDATED_FOLLOW)
            .discount(UPDATED_DISCOUNT)
            .from_amount(UPDATED_FROM_AMOUNT)
            .to_amount(UPDATED_TO_AMOUNT)
            .percent_discount(UPDATED_PERCENT_DISCOUNT)
            .discount_amount(UPDATED_DISCOUNT_AMOUNT)
            .conversion_unit(UPDATED_CONVERSION_UNIT)
            .primary_unit_conversion_rate(UPDATED_PRIMARY_UNIT_CONVERSION_RATE)
            .calculation(UPDATED_CALCULATION)
            .describe1(UPDATED_DESCRIBE_1)
            .unit_price_1(UPDATED_UNIT_PRICE_1)
            .unit_price_2(UPDATED_UNIT_PRICE_2)
            .unit_price_3(UPDATED_UNIT_PRICE_3)
            .fixed_unit_price1(UPDATED_FIXED_UNIT_PRICE_1)
            .material_code(UPDATED_MATERIAL_CODE)
            .material_name(UPDATED_MATERIAL_NAME)
            .dvt(UPDATED_DVT)
            .amount(UPDATED_AMOUNT)
            .specification_code(UPDATED_SPECIFICATION_CODE)
            .display_name(UPDATED_DISPLAY_NAME)
            .allow_same(UPDATED_ALLOW_SAME)
            .created_date(UPDATED_CREATED_DATE)
            .keyUUID(UPDATED_KEY_UUID);
        return merchandise;
    }

    @BeforeEach
    public void initTest() {
        merchandise = createEntity(em);
    }

    @Test
    @Transactional
    void createMerchandise() throws Exception {
        int databaseSizeBeforeCreate = merchandiseRepository.findAll().size();
        // Create the Merchandise
        restMerchandiseMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(merchandise)))
            .andExpect(status().isCreated());

        // Validate the Merchandise in the database
        List<Merchandise> merchandiseList = merchandiseRepository.findAll();
        assertThat(merchandiseList).hasSize(databaseSizeBeforeCreate + 1);
        Merchandise testMerchandise = merchandiseList.get(merchandiseList.size() - 1);
        assertThat(testMerchandise.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testMerchandise.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMerchandise.getNature()).isEqualTo(DEFAULT_NATURE);
        assertThat(testMerchandise.getGroup_vthh()).isEqualTo(DEFAULT_GROUP_VTHH);
        assertThat(testMerchandise.getDescribe()).isEqualTo(DEFAULT_DESCRIBE);
        assertThat(testMerchandise.getExplain_buy()).isEqualTo(DEFAULT_EXPLAIN_BUY);
        assertThat(testMerchandise.getExplain_sell()).isEqualTo(DEFAULT_EXPLAIN_SELL);
        assertThat(testMerchandise.getMain_dvt()).isEqualTo(DEFAULT_MAIN_DVT);
        assertThat(testMerchandise.getWarranty_period()).isEqualTo(DEFAULT_WARRANTY_PERIOD);
        assertThat(testMerchandise.getQuantity_inventory()).isEqualTo(DEFAULT_QUANTITY_INVENTORY);
        assertThat(testMerchandise.getSource()).isEqualTo(DEFAULT_SOURCE);
        assertThat(testMerchandise.getImplicitly_repository()).isEqualTo(DEFAULT_IMPLICITLY_REPOSITORY);
        assertThat(testMerchandise.getWarehouse_account()).isEqualTo(DEFAULT_WAREHOUSE_ACCOUNT);
        assertThat(testMerchandise.getExpense_account()).isEqualTo(DEFAULT_EXPENSE_ACCOUNT);
        assertThat(testMerchandise.getIncome_account()).isEqualTo(DEFAULT_INCOME_ACCOUNT);
        assertThat(testMerchandise.getDiscount_account()).isEqualTo(DEFAULT_DISCOUNT_ACCOUNT);
        assertThat(testMerchandise.getSale_account()).isEqualTo(DEFAULT_SALE_ACCOUNT);
        assertThat(testMerchandise.getReturn_account()).isEqualTo(DEFAULT_RETURN_ACCOUNT);
        assertThat(testMerchandise.getRate_ckmh()).isEqualTo(DEFAULT_RATE_CKMH);
        assertThat(testMerchandise.getFixed_purchase_price()).isEqualTo(DEFAULT_FIXED_PURCHASE_PRICE);
        assertThat(testMerchandise.getLatest_purchase_price()).isEqualTo(DEFAULT_LATEST_PURCHASE_PRICE);
        assertThat(testMerchandise.getUnit_price_sell_1()).isEqualTo(DEFAULT_UNIT_PRICE_SELL_1);
        assertThat(testMerchandise.getUnit_price_sell_2()).isEqualTo(DEFAULT_UNIT_PRICE_SELL_2);
        assertThat(testMerchandise.getUnit_price_sell_3()).isEqualTo(DEFAULT_UNIT_PRICE_SELL_3);
        assertThat(testMerchandise.getFixed_unit_price()).isEqualTo(DEFAULT_FIXED_UNIT_PRICE);
        assertThat(testMerchandise.getUnit_price_after_tax()).isEqualTo(DEFAULT_UNIT_PRICE_AFTER_TAX);
        assertThat(testMerchandise.getTax_rate_gtgt()).isEqualTo(DEFAULT_TAX_RATE_GTGT);
        assertThat(testMerchandise.getTax_rate_nk()).isEqualTo(DEFAULT_TAX_RATE_NK);
        assertThat(testMerchandise.getTax_rate_xk()).isEqualTo(DEFAULT_TAX_RATE_XK);
        assertThat(testMerchandise.getGroup_hhdv_taxable_ttdb()).isEqualTo(DEFAULT_GROUP_HHDV_TAXABLE_TTDB);
        assertThat(testMerchandise.getUnfollow()).isEqualTo(DEFAULT_UNFOLLOW);
        assertThat(testMerchandise.getInventory_number()).isEqualTo(DEFAULT_INVENTORY_NUMBER);
        assertThat(testMerchandise.getCharacteristic()).isEqualTo(DEFAULT_CHARACTERISTIC);
        assertThat(testMerchandise.getInventory_value()).isEqualTo(DEFAULT_INVENTORY_VALUE);
        assertThat(testMerchandise.getFollow()).isEqualTo(DEFAULT_FOLLOW);
        assertThat(testMerchandise.getDiscount()).isEqualTo(DEFAULT_DISCOUNT);
        assertThat(testMerchandise.getFrom_amount()).isEqualTo(DEFAULT_FROM_AMOUNT);
        assertThat(testMerchandise.getTo_amount()).isEqualTo(DEFAULT_TO_AMOUNT);
        assertThat(testMerchandise.getPercent_discount()).isEqualTo(DEFAULT_PERCENT_DISCOUNT);
        assertThat(testMerchandise.getDiscount_amount()).isEqualTo(DEFAULT_DISCOUNT_AMOUNT);
        assertThat(testMerchandise.getConversion_unit()).isEqualTo(DEFAULT_CONVERSION_UNIT);
        assertThat(testMerchandise.getPrimary_unit_conversion_rate()).isEqualTo(DEFAULT_PRIMARY_UNIT_CONVERSION_RATE);
        assertThat(testMerchandise.getCalculation()).isEqualTo(DEFAULT_CALCULATION);
        assertThat(testMerchandise.getDescribe1()).isEqualTo(DEFAULT_DESCRIBE_1);
        assertThat(testMerchandise.getUnit_price_1()).isEqualTo(DEFAULT_UNIT_PRICE_1);
        assertThat(testMerchandise.getUnit_price_2()).isEqualTo(DEFAULT_UNIT_PRICE_2);
        assertThat(testMerchandise.getUnit_price_3()).isEqualTo(DEFAULT_UNIT_PRICE_3);
        assertThat(testMerchandise.getFixed_unit_price1()).isEqualTo(DEFAULT_FIXED_UNIT_PRICE_1);
        assertThat(testMerchandise.getMaterial_code()).isEqualTo(DEFAULT_MATERIAL_CODE);
        assertThat(testMerchandise.getMaterial_name()).isEqualTo(DEFAULT_MATERIAL_NAME);
        assertThat(testMerchandise.getDvt()).isEqualTo(DEFAULT_DVT);
        assertThat(testMerchandise.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testMerchandise.getSpecification_code()).isEqualTo(DEFAULT_SPECIFICATION_CODE);
        assertThat(testMerchandise.getDisplay_name()).isEqualTo(DEFAULT_DISPLAY_NAME);
        assertThat(testMerchandise.getAllow_same()).isEqualTo(DEFAULT_ALLOW_SAME);
        assertThat(testMerchandise.getCreated_date()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testMerchandise.getKeyUUID()).isEqualTo(DEFAULT_KEY_UUID);
    }

    @Test
    @Transactional
    void createMerchandiseWithExistingId() throws Exception {
        // Create the Merchandise with an existing ID
        merchandise.setId(1L);

        int databaseSizeBeforeCreate = merchandiseRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMerchandiseMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(merchandise)))
            .andExpect(status().isBadRequest());

        // Validate the Merchandise in the database
        List<Merchandise> merchandiseList = merchandiseRepository.findAll();
        assertThat(merchandiseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllMerchandises() throws Exception {
        // Initialize the database
        merchandiseRepository.saveAndFlush(merchandise);

        // Get all the merchandiseList
        restMerchandiseMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(merchandise.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].nature").value(hasItem(DEFAULT_NATURE)))
            .andExpect(jsonPath("$.[*].group_vthh").value(hasItem(DEFAULT_GROUP_VTHH)))
            .andExpect(jsonPath("$.[*].describe").value(hasItem(DEFAULT_DESCRIBE)))
            .andExpect(jsonPath("$.[*].explain_buy").value(hasItem(DEFAULT_EXPLAIN_BUY)))
            .andExpect(jsonPath("$.[*].explain_sell").value(hasItem(DEFAULT_EXPLAIN_SELL)))
            .andExpect(jsonPath("$.[*].main_dvt").value(hasItem(DEFAULT_MAIN_DVT)))
            .andExpect(jsonPath("$.[*].warranty_period").value(hasItem(DEFAULT_WARRANTY_PERIOD)))
            .andExpect(jsonPath("$.[*].quantity_inventory").value(hasItem(DEFAULT_QUANTITY_INVENTORY.doubleValue())))
            .andExpect(jsonPath("$.[*].source").value(hasItem(DEFAULT_SOURCE)))
            .andExpect(jsonPath("$.[*].implicitly_repository").value(hasItem(DEFAULT_IMPLICITLY_REPOSITORY)))
            .andExpect(jsonPath("$.[*].warehouse_account").value(hasItem(DEFAULT_WAREHOUSE_ACCOUNT)))
            .andExpect(jsonPath("$.[*].expense_account").value(hasItem(DEFAULT_EXPENSE_ACCOUNT)))
            .andExpect(jsonPath("$.[*].income_account").value(hasItem(DEFAULT_INCOME_ACCOUNT)))
            .andExpect(jsonPath("$.[*].discount_account").value(hasItem(DEFAULT_DISCOUNT_ACCOUNT)))
            .andExpect(jsonPath("$.[*].sale_account").value(hasItem(DEFAULT_SALE_ACCOUNT)))
            .andExpect(jsonPath("$.[*].return_account").value(hasItem(DEFAULT_RETURN_ACCOUNT)))
            .andExpect(jsonPath("$.[*].rate_ckmh").value(hasItem(DEFAULT_RATE_CKMH.doubleValue())))
            .andExpect(jsonPath("$.[*].fixed_purchase_price").value(hasItem(DEFAULT_FIXED_PURCHASE_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].latest_purchase_price").value(hasItem(DEFAULT_LATEST_PURCHASE_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].unit_price_sell_1").value(hasItem(DEFAULT_UNIT_PRICE_SELL_1.doubleValue())))
            .andExpect(jsonPath("$.[*].unit_price_sell_2").value(hasItem(DEFAULT_UNIT_PRICE_SELL_2.doubleValue())))
            .andExpect(jsonPath("$.[*].unit_price_sell_3").value(hasItem(DEFAULT_UNIT_PRICE_SELL_3.doubleValue())))
            .andExpect(jsonPath("$.[*].fixed_unit_price").value(hasItem(DEFAULT_FIXED_UNIT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].unit_price_after_tax").value(hasItem(DEFAULT_UNIT_PRICE_AFTER_TAX.doubleValue())))
            .andExpect(jsonPath("$.[*].tax_rate_gtgt").value(hasItem(DEFAULT_TAX_RATE_GTGT)))
            .andExpect(jsonPath("$.[*].tax_rate_nk").value(hasItem(DEFAULT_TAX_RATE_NK.doubleValue())))
            .andExpect(jsonPath("$.[*].tax_rate_xk").value(hasItem(DEFAULT_TAX_RATE_XK.doubleValue())))
            .andExpect(jsonPath("$.[*].group_hhdv_taxable_ttdb").value(hasItem(DEFAULT_GROUP_HHDV_TAXABLE_TTDB)))
            .andExpect(jsonPath("$.[*].unfollow").value(hasItem(DEFAULT_UNFOLLOW.doubleValue())))
            .andExpect(jsonPath("$.[*].inventory_number").value(hasItem(DEFAULT_INVENTORY_NUMBER.doubleValue())))
            .andExpect(jsonPath("$.[*].characteristic").value(hasItem(DEFAULT_CHARACTERISTIC)))
            .andExpect(jsonPath("$.[*].inventory_value").value(hasItem(DEFAULT_INVENTORY_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].follow").value(hasItem(DEFAULT_FOLLOW.doubleValue())))
            .andExpect(jsonPath("$.[*].discount").value(hasItem(DEFAULT_DISCOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].from_amount").value(hasItem(DEFAULT_FROM_AMOUNT)))
            .andExpect(jsonPath("$.[*].to_amount").value(hasItem(DEFAULT_TO_AMOUNT)))
            .andExpect(jsonPath("$.[*].percent_discount").value(hasItem(DEFAULT_PERCENT_DISCOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].discount_amount").value(hasItem(DEFAULT_DISCOUNT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].conversion_unit").value(hasItem(DEFAULT_CONVERSION_UNIT)))
            .andExpect(jsonPath("$.[*].primary_unit_conversion_rate").value(hasItem(DEFAULT_PRIMARY_UNIT_CONVERSION_RATE.doubleValue())))
            .andExpect(jsonPath("$.[*].calculation").value(hasItem(DEFAULT_CALCULATION)))
            .andExpect(jsonPath("$.[*].describe1").value(hasItem(DEFAULT_DESCRIBE_1)))
            .andExpect(jsonPath("$.[*].unit_price_1").value(hasItem(DEFAULT_UNIT_PRICE_1.doubleValue())))
            .andExpect(jsonPath("$.[*].unit_price_2").value(hasItem(DEFAULT_UNIT_PRICE_2.doubleValue())))
            .andExpect(jsonPath("$.[*].unit_price_3").value(hasItem(DEFAULT_UNIT_PRICE_3.doubleValue())))
            .andExpect(jsonPath("$.[*].fixed_unit_price1").value(hasItem(DEFAULT_FIXED_UNIT_PRICE_1.doubleValue())))
            .andExpect(jsonPath("$.[*].material_code").value(hasItem(DEFAULT_MATERIAL_CODE)))
            .andExpect(jsonPath("$.[*].material_name").value(hasItem(DEFAULT_MATERIAL_NAME)))
            .andExpect(jsonPath("$.[*].dvt").value(hasItem(DEFAULT_DVT)))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT)))
            .andExpect(jsonPath("$.[*].specification_code").value(hasItem(DEFAULT_SPECIFICATION_CODE)))
            .andExpect(jsonPath("$.[*].display_name").value(hasItem(DEFAULT_DISPLAY_NAME)))
            .andExpect(jsonPath("$.[*].allow_same").value(hasItem(DEFAULT_ALLOW_SAME)))
            .andExpect(jsonPath("$.[*].created_date").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].keyUUID").value(hasItem(DEFAULT_KEY_UUID)));
    }

    @Test
    @Transactional
    void getMerchandise() throws Exception {
        // Initialize the database
        merchandiseRepository.saveAndFlush(merchandise);

        // Get the merchandise
        restMerchandiseMockMvc
            .perform(get(ENTITY_API_URL_ID, merchandise.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(merchandise.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.nature").value(DEFAULT_NATURE))
            .andExpect(jsonPath("$.group_vthh").value(DEFAULT_GROUP_VTHH))
            .andExpect(jsonPath("$.describe").value(DEFAULT_DESCRIBE))
            .andExpect(jsonPath("$.explain_buy").value(DEFAULT_EXPLAIN_BUY))
            .andExpect(jsonPath("$.explain_sell").value(DEFAULT_EXPLAIN_SELL))
            .andExpect(jsonPath("$.main_dvt").value(DEFAULT_MAIN_DVT))
            .andExpect(jsonPath("$.warranty_period").value(DEFAULT_WARRANTY_PERIOD))
            .andExpect(jsonPath("$.quantity_inventory").value(DEFAULT_QUANTITY_INVENTORY.doubleValue()))
            .andExpect(jsonPath("$.source").value(DEFAULT_SOURCE))
            .andExpect(jsonPath("$.implicitly_repository").value(DEFAULT_IMPLICITLY_REPOSITORY))
            .andExpect(jsonPath("$.warehouse_account").value(DEFAULT_WAREHOUSE_ACCOUNT))
            .andExpect(jsonPath("$.expense_account").value(DEFAULT_EXPENSE_ACCOUNT))
            .andExpect(jsonPath("$.income_account").value(DEFAULT_INCOME_ACCOUNT))
            .andExpect(jsonPath("$.discount_account").value(DEFAULT_DISCOUNT_ACCOUNT))
            .andExpect(jsonPath("$.sale_account").value(DEFAULT_SALE_ACCOUNT))
            .andExpect(jsonPath("$.return_account").value(DEFAULT_RETURN_ACCOUNT))
            .andExpect(jsonPath("$.rate_ckmh").value(DEFAULT_RATE_CKMH.doubleValue()))
            .andExpect(jsonPath("$.fixed_purchase_price").value(DEFAULT_FIXED_PURCHASE_PRICE.doubleValue()))
            .andExpect(jsonPath("$.latest_purchase_price").value(DEFAULT_LATEST_PURCHASE_PRICE.doubleValue()))
            .andExpect(jsonPath("$.unit_price_sell_1").value(DEFAULT_UNIT_PRICE_SELL_1.doubleValue()))
            .andExpect(jsonPath("$.unit_price_sell_2").value(DEFAULT_UNIT_PRICE_SELL_2.doubleValue()))
            .andExpect(jsonPath("$.unit_price_sell_3").value(DEFAULT_UNIT_PRICE_SELL_3.doubleValue()))
            .andExpect(jsonPath("$.fixed_unit_price").value(DEFAULT_FIXED_UNIT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.unit_price_after_tax").value(DEFAULT_UNIT_PRICE_AFTER_TAX.doubleValue()))
            .andExpect(jsonPath("$.tax_rate_gtgt").value(DEFAULT_TAX_RATE_GTGT))
            .andExpect(jsonPath("$.tax_rate_nk").value(DEFAULT_TAX_RATE_NK.doubleValue()))
            .andExpect(jsonPath("$.tax_rate_xk").value(DEFAULT_TAX_RATE_XK.doubleValue()))
            .andExpect(jsonPath("$.group_hhdv_taxable_ttdb").value(DEFAULT_GROUP_HHDV_TAXABLE_TTDB))
            .andExpect(jsonPath("$.unfollow").value(DEFAULT_UNFOLLOW.doubleValue()))
            .andExpect(jsonPath("$.inventory_number").value(DEFAULT_INVENTORY_NUMBER.doubleValue()))
            .andExpect(jsonPath("$.characteristic").value(DEFAULT_CHARACTERISTIC))
            .andExpect(jsonPath("$.inventory_value").value(DEFAULT_INVENTORY_VALUE.doubleValue()))
            .andExpect(jsonPath("$.follow").value(DEFAULT_FOLLOW.doubleValue()))
            .andExpect(jsonPath("$.discount").value(DEFAULT_DISCOUNT.doubleValue()))
            .andExpect(jsonPath("$.from_amount").value(DEFAULT_FROM_AMOUNT))
            .andExpect(jsonPath("$.to_amount").value(DEFAULT_TO_AMOUNT))
            .andExpect(jsonPath("$.percent_discount").value(DEFAULT_PERCENT_DISCOUNT.doubleValue()))
            .andExpect(jsonPath("$.discount_amount").value(DEFAULT_DISCOUNT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.conversion_unit").value(DEFAULT_CONVERSION_UNIT))
            .andExpect(jsonPath("$.primary_unit_conversion_rate").value(DEFAULT_PRIMARY_UNIT_CONVERSION_RATE.doubleValue()))
            .andExpect(jsonPath("$.calculation").value(DEFAULT_CALCULATION))
            .andExpect(jsonPath("$.describe1").value(DEFAULT_DESCRIBE_1))
            .andExpect(jsonPath("$.unit_price_1").value(DEFAULT_UNIT_PRICE_1.doubleValue()))
            .andExpect(jsonPath("$.unit_price_2").value(DEFAULT_UNIT_PRICE_2.doubleValue()))
            .andExpect(jsonPath("$.unit_price_3").value(DEFAULT_UNIT_PRICE_3.doubleValue()))
            .andExpect(jsonPath("$.fixed_unit_price1").value(DEFAULT_FIXED_UNIT_PRICE_1.doubleValue()))
            .andExpect(jsonPath("$.material_code").value(DEFAULT_MATERIAL_CODE))
            .andExpect(jsonPath("$.material_name").value(DEFAULT_MATERIAL_NAME))
            .andExpect(jsonPath("$.dvt").value(DEFAULT_DVT))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT))
            .andExpect(jsonPath("$.specification_code").value(DEFAULT_SPECIFICATION_CODE))
            .andExpect(jsonPath("$.display_name").value(DEFAULT_DISPLAY_NAME))
            .andExpect(jsonPath("$.allow_same").value(DEFAULT_ALLOW_SAME))
            .andExpect(jsonPath("$.created_date").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.keyUUID").value(DEFAULT_KEY_UUID));
    }

    @Test
    @Transactional
    void getNonExistingMerchandise() throws Exception {
        // Get the merchandise
        restMerchandiseMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingMerchandise() throws Exception {
        // Initialize the database
        merchandiseRepository.saveAndFlush(merchandise);

        int databaseSizeBeforeUpdate = merchandiseRepository.findAll().size();

        // Update the merchandise
        Merchandise updatedMerchandise = merchandiseRepository.findById(merchandise.getId()).get();
        // Disconnect from session so that the updates on updatedMerchandise are not directly saved in db
        em.detach(updatedMerchandise);
        updatedMerchandise
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .nature(UPDATED_NATURE)
            .group_vthh(UPDATED_GROUP_VTHH)
            .describe(UPDATED_DESCRIBE)
            .explain_buy(UPDATED_EXPLAIN_BUY)
            .explain_sell(UPDATED_EXPLAIN_SELL)
            .main_dvt(UPDATED_MAIN_DVT)
            .warranty_period(UPDATED_WARRANTY_PERIOD)
            .quantity_inventory(UPDATED_QUANTITY_INVENTORY)
            .source(UPDATED_SOURCE)
            .implicitly_repository(UPDATED_IMPLICITLY_REPOSITORY)
            .warehouse_account(UPDATED_WAREHOUSE_ACCOUNT)
            .expense_account(UPDATED_EXPENSE_ACCOUNT)
            .income_account(UPDATED_INCOME_ACCOUNT)
            .discount_account(UPDATED_DISCOUNT_ACCOUNT)
            .sale_account(UPDATED_SALE_ACCOUNT)
            .return_account(UPDATED_RETURN_ACCOUNT)
            .rate_ckmh(UPDATED_RATE_CKMH)
            .fixed_purchase_price(UPDATED_FIXED_PURCHASE_PRICE)
            .latest_purchase_price(UPDATED_LATEST_PURCHASE_PRICE)
            .unit_price_sell_1(UPDATED_UNIT_PRICE_SELL_1)
            .unit_price_sell_2(UPDATED_UNIT_PRICE_SELL_2)
            .unit_price_sell_3(UPDATED_UNIT_PRICE_SELL_3)
            .fixed_unit_price(UPDATED_FIXED_UNIT_PRICE)
            .unit_price_after_tax(UPDATED_UNIT_PRICE_AFTER_TAX)
            .tax_rate_gtgt(UPDATED_TAX_RATE_GTGT)
            .tax_rate_nk(UPDATED_TAX_RATE_NK)
            .tax_rate_xk(UPDATED_TAX_RATE_XK)
            .group_hhdv_taxable_ttdb(UPDATED_GROUP_HHDV_TAXABLE_TTDB)
            .unfollow(UPDATED_UNFOLLOW)
            .inventory_number(UPDATED_INVENTORY_NUMBER)
            .characteristic(UPDATED_CHARACTERISTIC)
            .inventory_value(UPDATED_INVENTORY_VALUE)
            .follow(UPDATED_FOLLOW)
            .discount(UPDATED_DISCOUNT)
            .from_amount(UPDATED_FROM_AMOUNT)
            .to_amount(UPDATED_TO_AMOUNT)
            .percent_discount(UPDATED_PERCENT_DISCOUNT)
            .discount_amount(UPDATED_DISCOUNT_AMOUNT)
            .conversion_unit(UPDATED_CONVERSION_UNIT)
            .primary_unit_conversion_rate(UPDATED_PRIMARY_UNIT_CONVERSION_RATE)
            .calculation(UPDATED_CALCULATION)
            .describe1(UPDATED_DESCRIBE_1)
            .unit_price_1(UPDATED_UNIT_PRICE_1)
            .unit_price_2(UPDATED_UNIT_PRICE_2)
            .unit_price_3(UPDATED_UNIT_PRICE_3)
            .fixed_unit_price1(UPDATED_FIXED_UNIT_PRICE_1)
            .material_code(UPDATED_MATERIAL_CODE)
            .material_name(UPDATED_MATERIAL_NAME)
            .dvt(UPDATED_DVT)
            .amount(UPDATED_AMOUNT)
            .specification_code(UPDATED_SPECIFICATION_CODE)
            .display_name(UPDATED_DISPLAY_NAME)
            .allow_same(UPDATED_ALLOW_SAME)
            .created_date(UPDATED_CREATED_DATE)
            .keyUUID(UPDATED_KEY_UUID);

        restMerchandiseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedMerchandise.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedMerchandise))
            )
            .andExpect(status().isOk());

        // Validate the Merchandise in the database
        List<Merchandise> merchandiseList = merchandiseRepository.findAll();
        assertThat(merchandiseList).hasSize(databaseSizeBeforeUpdate);
        Merchandise testMerchandise = merchandiseList.get(merchandiseList.size() - 1);
        assertThat(testMerchandise.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testMerchandise.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMerchandise.getNature()).isEqualTo(UPDATED_NATURE);
        assertThat(testMerchandise.getGroup_vthh()).isEqualTo(UPDATED_GROUP_VTHH);
        assertThat(testMerchandise.getDescribe()).isEqualTo(UPDATED_DESCRIBE);
        assertThat(testMerchandise.getExplain_buy()).isEqualTo(UPDATED_EXPLAIN_BUY);
        assertThat(testMerchandise.getExplain_sell()).isEqualTo(UPDATED_EXPLAIN_SELL);
        assertThat(testMerchandise.getMain_dvt()).isEqualTo(UPDATED_MAIN_DVT);
        assertThat(testMerchandise.getWarranty_period()).isEqualTo(UPDATED_WARRANTY_PERIOD);
        assertThat(testMerchandise.getQuantity_inventory()).isEqualTo(UPDATED_QUANTITY_INVENTORY);
        assertThat(testMerchandise.getSource()).isEqualTo(UPDATED_SOURCE);
        assertThat(testMerchandise.getImplicitly_repository()).isEqualTo(UPDATED_IMPLICITLY_REPOSITORY);
        assertThat(testMerchandise.getWarehouse_account()).isEqualTo(UPDATED_WAREHOUSE_ACCOUNT);
        assertThat(testMerchandise.getExpense_account()).isEqualTo(UPDATED_EXPENSE_ACCOUNT);
        assertThat(testMerchandise.getIncome_account()).isEqualTo(UPDATED_INCOME_ACCOUNT);
        assertThat(testMerchandise.getDiscount_account()).isEqualTo(UPDATED_DISCOUNT_ACCOUNT);
        assertThat(testMerchandise.getSale_account()).isEqualTo(UPDATED_SALE_ACCOUNT);
        assertThat(testMerchandise.getReturn_account()).isEqualTo(UPDATED_RETURN_ACCOUNT);
        assertThat(testMerchandise.getRate_ckmh()).isEqualTo(UPDATED_RATE_CKMH);
        assertThat(testMerchandise.getFixed_purchase_price()).isEqualTo(UPDATED_FIXED_PURCHASE_PRICE);
        assertThat(testMerchandise.getLatest_purchase_price()).isEqualTo(UPDATED_LATEST_PURCHASE_PRICE);
        assertThat(testMerchandise.getUnit_price_sell_1()).isEqualTo(UPDATED_UNIT_PRICE_SELL_1);
        assertThat(testMerchandise.getUnit_price_sell_2()).isEqualTo(UPDATED_UNIT_PRICE_SELL_2);
        assertThat(testMerchandise.getUnit_price_sell_3()).isEqualTo(UPDATED_UNIT_PRICE_SELL_3);
        assertThat(testMerchandise.getFixed_unit_price()).isEqualTo(UPDATED_FIXED_UNIT_PRICE);
        assertThat(testMerchandise.getUnit_price_after_tax()).isEqualTo(UPDATED_UNIT_PRICE_AFTER_TAX);
        assertThat(testMerchandise.getTax_rate_gtgt()).isEqualTo(UPDATED_TAX_RATE_GTGT);
        assertThat(testMerchandise.getTax_rate_nk()).isEqualTo(UPDATED_TAX_RATE_NK);
        assertThat(testMerchandise.getTax_rate_xk()).isEqualTo(UPDATED_TAX_RATE_XK);
        assertThat(testMerchandise.getGroup_hhdv_taxable_ttdb()).isEqualTo(UPDATED_GROUP_HHDV_TAXABLE_TTDB);
        assertThat(testMerchandise.getUnfollow()).isEqualTo(UPDATED_UNFOLLOW);
        assertThat(testMerchandise.getInventory_number()).isEqualTo(UPDATED_INVENTORY_NUMBER);
        assertThat(testMerchandise.getCharacteristic()).isEqualTo(UPDATED_CHARACTERISTIC);
        assertThat(testMerchandise.getInventory_value()).isEqualTo(UPDATED_INVENTORY_VALUE);
        assertThat(testMerchandise.getFollow()).isEqualTo(UPDATED_FOLLOW);
        assertThat(testMerchandise.getDiscount()).isEqualTo(UPDATED_DISCOUNT);
        assertThat(testMerchandise.getFrom_amount()).isEqualTo(UPDATED_FROM_AMOUNT);
        assertThat(testMerchandise.getTo_amount()).isEqualTo(UPDATED_TO_AMOUNT);
        assertThat(testMerchandise.getPercent_discount()).isEqualTo(UPDATED_PERCENT_DISCOUNT);
        assertThat(testMerchandise.getDiscount_amount()).isEqualTo(UPDATED_DISCOUNT_AMOUNT);
        assertThat(testMerchandise.getConversion_unit()).isEqualTo(UPDATED_CONVERSION_UNIT);
        assertThat(testMerchandise.getPrimary_unit_conversion_rate()).isEqualTo(UPDATED_PRIMARY_UNIT_CONVERSION_RATE);
        assertThat(testMerchandise.getCalculation()).isEqualTo(UPDATED_CALCULATION);
        assertThat(testMerchandise.getDescribe1()).isEqualTo(UPDATED_DESCRIBE_1);
        assertThat(testMerchandise.getUnit_price_1()).isEqualTo(UPDATED_UNIT_PRICE_1);
        assertThat(testMerchandise.getUnit_price_2()).isEqualTo(UPDATED_UNIT_PRICE_2);
        assertThat(testMerchandise.getUnit_price_3()).isEqualTo(UPDATED_UNIT_PRICE_3);
        assertThat(testMerchandise.getFixed_unit_price1()).isEqualTo(UPDATED_FIXED_UNIT_PRICE_1);
        assertThat(testMerchandise.getMaterial_code()).isEqualTo(UPDATED_MATERIAL_CODE);
        assertThat(testMerchandise.getMaterial_name()).isEqualTo(UPDATED_MATERIAL_NAME);
        assertThat(testMerchandise.getDvt()).isEqualTo(UPDATED_DVT);
        assertThat(testMerchandise.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testMerchandise.getSpecification_code()).isEqualTo(UPDATED_SPECIFICATION_CODE);
        assertThat(testMerchandise.getDisplay_name()).isEqualTo(UPDATED_DISPLAY_NAME);
        assertThat(testMerchandise.getAllow_same()).isEqualTo(UPDATED_ALLOW_SAME);
        assertThat(testMerchandise.getCreated_date()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testMerchandise.getKeyUUID()).isEqualTo(UPDATED_KEY_UUID);
    }

    @Test
    @Transactional
    void putNonExistingMerchandise() throws Exception {
        int databaseSizeBeforeUpdate = merchandiseRepository.findAll().size();
        merchandise.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMerchandiseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, merchandise.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(merchandise))
            )
            .andExpect(status().isBadRequest());

        // Validate the Merchandise in the database
        List<Merchandise> merchandiseList = merchandiseRepository.findAll();
        assertThat(merchandiseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchMerchandise() throws Exception {
        int databaseSizeBeforeUpdate = merchandiseRepository.findAll().size();
        merchandise.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMerchandiseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(merchandise))
            )
            .andExpect(status().isBadRequest());

        // Validate the Merchandise in the database
        List<Merchandise> merchandiseList = merchandiseRepository.findAll();
        assertThat(merchandiseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamMerchandise() throws Exception {
        int databaseSizeBeforeUpdate = merchandiseRepository.findAll().size();
        merchandise.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMerchandiseMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(merchandise)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Merchandise in the database
        List<Merchandise> merchandiseList = merchandiseRepository.findAll();
        assertThat(merchandiseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateMerchandiseWithPatch() throws Exception {
        // Initialize the database
        merchandiseRepository.saveAndFlush(merchandise);

        int databaseSizeBeforeUpdate = merchandiseRepository.findAll().size();

        // Update the merchandise using partial update
        Merchandise partialUpdatedMerchandise = new Merchandise();
        partialUpdatedMerchandise.setId(merchandise.getId());

        partialUpdatedMerchandise
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .describe(UPDATED_DESCRIBE)
            .main_dvt(UPDATED_MAIN_DVT)
            .warranty_period(UPDATED_WARRANTY_PERIOD)
            .quantity_inventory(UPDATED_QUANTITY_INVENTORY)
            .source(UPDATED_SOURCE)
            .implicitly_repository(UPDATED_IMPLICITLY_REPOSITORY)
            .income_account(UPDATED_INCOME_ACCOUNT)
            .discount_account(UPDATED_DISCOUNT_ACCOUNT)
            .latest_purchase_price(UPDATED_LATEST_PURCHASE_PRICE)
            .unit_price_sell_1(UPDATED_UNIT_PRICE_SELL_1)
            .unit_price_after_tax(UPDATED_UNIT_PRICE_AFTER_TAX)
            .tax_rate_nk(UPDATED_TAX_RATE_NK)
            .tax_rate_xk(UPDATED_TAX_RATE_XK)
            .group_hhdv_taxable_ttdb(UPDATED_GROUP_HHDV_TAXABLE_TTDB)
            .unfollow(UPDATED_UNFOLLOW)
            .inventory_number(UPDATED_INVENTORY_NUMBER)
            .from_amount(UPDATED_FROM_AMOUNT)
            .discount_amount(UPDATED_DISCOUNT_AMOUNT)
            .primary_unit_conversion_rate(UPDATED_PRIMARY_UNIT_CONVERSION_RATE)
            .calculation(UPDATED_CALCULATION)
            .describe1(UPDATED_DESCRIBE_1)
            .unit_price_1(UPDATED_UNIT_PRICE_1)
            .unit_price_3(UPDATED_UNIT_PRICE_3)
            .dvt(UPDATED_DVT)
            .specification_code(UPDATED_SPECIFICATION_CODE);

        restMerchandiseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMerchandise.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMerchandise))
            )
            .andExpect(status().isOk());

        // Validate the Merchandise in the database
        List<Merchandise> merchandiseList = merchandiseRepository.findAll();
        assertThat(merchandiseList).hasSize(databaseSizeBeforeUpdate);
        Merchandise testMerchandise = merchandiseList.get(merchandiseList.size() - 1);
        assertThat(testMerchandise.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testMerchandise.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMerchandise.getNature()).isEqualTo(DEFAULT_NATURE);
        assertThat(testMerchandise.getGroup_vthh()).isEqualTo(DEFAULT_GROUP_VTHH);
        assertThat(testMerchandise.getDescribe()).isEqualTo(UPDATED_DESCRIBE);
        assertThat(testMerchandise.getExplain_buy()).isEqualTo(DEFAULT_EXPLAIN_BUY);
        assertThat(testMerchandise.getExplain_sell()).isEqualTo(DEFAULT_EXPLAIN_SELL);
        assertThat(testMerchandise.getMain_dvt()).isEqualTo(UPDATED_MAIN_DVT);
        assertThat(testMerchandise.getWarranty_period()).isEqualTo(UPDATED_WARRANTY_PERIOD);
        assertThat(testMerchandise.getQuantity_inventory()).isEqualTo(UPDATED_QUANTITY_INVENTORY);
        assertThat(testMerchandise.getSource()).isEqualTo(UPDATED_SOURCE);
        assertThat(testMerchandise.getImplicitly_repository()).isEqualTo(UPDATED_IMPLICITLY_REPOSITORY);
        assertThat(testMerchandise.getWarehouse_account()).isEqualTo(DEFAULT_WAREHOUSE_ACCOUNT);
        assertThat(testMerchandise.getExpense_account()).isEqualTo(DEFAULT_EXPENSE_ACCOUNT);
        assertThat(testMerchandise.getIncome_account()).isEqualTo(UPDATED_INCOME_ACCOUNT);
        assertThat(testMerchandise.getDiscount_account()).isEqualTo(UPDATED_DISCOUNT_ACCOUNT);
        assertThat(testMerchandise.getSale_account()).isEqualTo(DEFAULT_SALE_ACCOUNT);
        assertThat(testMerchandise.getReturn_account()).isEqualTo(DEFAULT_RETURN_ACCOUNT);
        assertThat(testMerchandise.getRate_ckmh()).isEqualTo(DEFAULT_RATE_CKMH);
        assertThat(testMerchandise.getFixed_purchase_price()).isEqualTo(DEFAULT_FIXED_PURCHASE_PRICE);
        assertThat(testMerchandise.getLatest_purchase_price()).isEqualTo(UPDATED_LATEST_PURCHASE_PRICE);
        assertThat(testMerchandise.getUnit_price_sell_1()).isEqualTo(UPDATED_UNIT_PRICE_SELL_1);
        assertThat(testMerchandise.getUnit_price_sell_2()).isEqualTo(DEFAULT_UNIT_PRICE_SELL_2);
        assertThat(testMerchandise.getUnit_price_sell_3()).isEqualTo(DEFAULT_UNIT_PRICE_SELL_3);
        assertThat(testMerchandise.getFixed_unit_price()).isEqualTo(DEFAULT_FIXED_UNIT_PRICE);
        assertThat(testMerchandise.getUnit_price_after_tax()).isEqualTo(UPDATED_UNIT_PRICE_AFTER_TAX);
        assertThat(testMerchandise.getTax_rate_gtgt()).isEqualTo(DEFAULT_TAX_RATE_GTGT);
        assertThat(testMerchandise.getTax_rate_nk()).isEqualTo(UPDATED_TAX_RATE_NK);
        assertThat(testMerchandise.getTax_rate_xk()).isEqualTo(UPDATED_TAX_RATE_XK);
        assertThat(testMerchandise.getGroup_hhdv_taxable_ttdb()).isEqualTo(UPDATED_GROUP_HHDV_TAXABLE_TTDB);
        assertThat(testMerchandise.getUnfollow()).isEqualTo(UPDATED_UNFOLLOW);
        assertThat(testMerchandise.getInventory_number()).isEqualTo(UPDATED_INVENTORY_NUMBER);
        assertThat(testMerchandise.getCharacteristic()).isEqualTo(DEFAULT_CHARACTERISTIC);
        assertThat(testMerchandise.getInventory_value()).isEqualTo(DEFAULT_INVENTORY_VALUE);
        assertThat(testMerchandise.getFollow()).isEqualTo(DEFAULT_FOLLOW);
        assertThat(testMerchandise.getDiscount()).isEqualTo(DEFAULT_DISCOUNT);
        assertThat(testMerchandise.getFrom_amount()).isEqualTo(UPDATED_FROM_AMOUNT);
        assertThat(testMerchandise.getTo_amount()).isEqualTo(DEFAULT_TO_AMOUNT);
        assertThat(testMerchandise.getPercent_discount()).isEqualTo(DEFAULT_PERCENT_DISCOUNT);
        assertThat(testMerchandise.getDiscount_amount()).isEqualTo(UPDATED_DISCOUNT_AMOUNT);
        assertThat(testMerchandise.getConversion_unit()).isEqualTo(DEFAULT_CONVERSION_UNIT);
        assertThat(testMerchandise.getPrimary_unit_conversion_rate()).isEqualTo(UPDATED_PRIMARY_UNIT_CONVERSION_RATE);
        assertThat(testMerchandise.getCalculation()).isEqualTo(UPDATED_CALCULATION);
        assertThat(testMerchandise.getDescribe1()).isEqualTo(UPDATED_DESCRIBE_1);
        assertThat(testMerchandise.getUnit_price_1()).isEqualTo(UPDATED_UNIT_PRICE_1);
        assertThat(testMerchandise.getUnit_price_2()).isEqualTo(DEFAULT_UNIT_PRICE_2);
        assertThat(testMerchandise.getUnit_price_3()).isEqualTo(UPDATED_UNIT_PRICE_3);
        assertThat(testMerchandise.getFixed_unit_price1()).isEqualTo(DEFAULT_FIXED_UNIT_PRICE_1);
        assertThat(testMerchandise.getMaterial_code()).isEqualTo(DEFAULT_MATERIAL_CODE);
        assertThat(testMerchandise.getMaterial_name()).isEqualTo(DEFAULT_MATERIAL_NAME);
        assertThat(testMerchandise.getDvt()).isEqualTo(UPDATED_DVT);
        assertThat(testMerchandise.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testMerchandise.getSpecification_code()).isEqualTo(UPDATED_SPECIFICATION_CODE);
        assertThat(testMerchandise.getDisplay_name()).isEqualTo(DEFAULT_DISPLAY_NAME);
        assertThat(testMerchandise.getAllow_same()).isEqualTo(DEFAULT_ALLOW_SAME);
        assertThat(testMerchandise.getCreated_date()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testMerchandise.getKeyUUID()).isEqualTo(DEFAULT_KEY_UUID);
    }

    @Test
    @Transactional
    void fullUpdateMerchandiseWithPatch() throws Exception {
        // Initialize the database
        merchandiseRepository.saveAndFlush(merchandise);

        int databaseSizeBeforeUpdate = merchandiseRepository.findAll().size();

        // Update the merchandise using partial update
        Merchandise partialUpdatedMerchandise = new Merchandise();
        partialUpdatedMerchandise.setId(merchandise.getId());

        partialUpdatedMerchandise
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .nature(UPDATED_NATURE)
            .group_vthh(UPDATED_GROUP_VTHH)
            .describe(UPDATED_DESCRIBE)
            .explain_buy(UPDATED_EXPLAIN_BUY)
            .explain_sell(UPDATED_EXPLAIN_SELL)
            .main_dvt(UPDATED_MAIN_DVT)
            .warranty_period(UPDATED_WARRANTY_PERIOD)
            .quantity_inventory(UPDATED_QUANTITY_INVENTORY)
            .source(UPDATED_SOURCE)
            .implicitly_repository(UPDATED_IMPLICITLY_REPOSITORY)
            .warehouse_account(UPDATED_WAREHOUSE_ACCOUNT)
            .expense_account(UPDATED_EXPENSE_ACCOUNT)
            .income_account(UPDATED_INCOME_ACCOUNT)
            .discount_account(UPDATED_DISCOUNT_ACCOUNT)
            .sale_account(UPDATED_SALE_ACCOUNT)
            .return_account(UPDATED_RETURN_ACCOUNT)
            .rate_ckmh(UPDATED_RATE_CKMH)
            .fixed_purchase_price(UPDATED_FIXED_PURCHASE_PRICE)
            .latest_purchase_price(UPDATED_LATEST_PURCHASE_PRICE)
            .unit_price_sell_1(UPDATED_UNIT_PRICE_SELL_1)
            .unit_price_sell_2(UPDATED_UNIT_PRICE_SELL_2)
            .unit_price_sell_3(UPDATED_UNIT_PRICE_SELL_3)
            .fixed_unit_price(UPDATED_FIXED_UNIT_PRICE)
            .unit_price_after_tax(UPDATED_UNIT_PRICE_AFTER_TAX)
            .tax_rate_gtgt(UPDATED_TAX_RATE_GTGT)
            .tax_rate_nk(UPDATED_TAX_RATE_NK)
            .tax_rate_xk(UPDATED_TAX_RATE_XK)
            .group_hhdv_taxable_ttdb(UPDATED_GROUP_HHDV_TAXABLE_TTDB)
            .unfollow(UPDATED_UNFOLLOW)
            .inventory_number(UPDATED_INVENTORY_NUMBER)
            .characteristic(UPDATED_CHARACTERISTIC)
            .inventory_value(UPDATED_INVENTORY_VALUE)
            .follow(UPDATED_FOLLOW)
            .discount(UPDATED_DISCOUNT)
            .from_amount(UPDATED_FROM_AMOUNT)
            .to_amount(UPDATED_TO_AMOUNT)
            .percent_discount(UPDATED_PERCENT_DISCOUNT)
            .discount_amount(UPDATED_DISCOUNT_AMOUNT)
            .conversion_unit(UPDATED_CONVERSION_UNIT)
            .primary_unit_conversion_rate(UPDATED_PRIMARY_UNIT_CONVERSION_RATE)
            .calculation(UPDATED_CALCULATION)
            .describe1(UPDATED_DESCRIBE_1)
            .unit_price_1(UPDATED_UNIT_PRICE_1)
            .unit_price_2(UPDATED_UNIT_PRICE_2)
            .unit_price_3(UPDATED_UNIT_PRICE_3)
            .fixed_unit_price1(UPDATED_FIXED_UNIT_PRICE_1)
            .material_code(UPDATED_MATERIAL_CODE)
            .material_name(UPDATED_MATERIAL_NAME)
            .dvt(UPDATED_DVT)
            .amount(UPDATED_AMOUNT)
            .specification_code(UPDATED_SPECIFICATION_CODE)
            .display_name(UPDATED_DISPLAY_NAME)
            .allow_same(UPDATED_ALLOW_SAME)
            .created_date(UPDATED_CREATED_DATE)
            .keyUUID(UPDATED_KEY_UUID);

        restMerchandiseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMerchandise.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMerchandise))
            )
            .andExpect(status().isOk());

        // Validate the Merchandise in the database
        List<Merchandise> merchandiseList = merchandiseRepository.findAll();
        assertThat(merchandiseList).hasSize(databaseSizeBeforeUpdate);
        Merchandise testMerchandise = merchandiseList.get(merchandiseList.size() - 1);
        assertThat(testMerchandise.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testMerchandise.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMerchandise.getNature()).isEqualTo(UPDATED_NATURE);
        assertThat(testMerchandise.getGroup_vthh()).isEqualTo(UPDATED_GROUP_VTHH);
        assertThat(testMerchandise.getDescribe()).isEqualTo(UPDATED_DESCRIBE);
        assertThat(testMerchandise.getExplain_buy()).isEqualTo(UPDATED_EXPLAIN_BUY);
        assertThat(testMerchandise.getExplain_sell()).isEqualTo(UPDATED_EXPLAIN_SELL);
        assertThat(testMerchandise.getMain_dvt()).isEqualTo(UPDATED_MAIN_DVT);
        assertThat(testMerchandise.getWarranty_period()).isEqualTo(UPDATED_WARRANTY_PERIOD);
        assertThat(testMerchandise.getQuantity_inventory()).isEqualTo(UPDATED_QUANTITY_INVENTORY);
        assertThat(testMerchandise.getSource()).isEqualTo(UPDATED_SOURCE);
        assertThat(testMerchandise.getImplicitly_repository()).isEqualTo(UPDATED_IMPLICITLY_REPOSITORY);
        assertThat(testMerchandise.getWarehouse_account()).isEqualTo(UPDATED_WAREHOUSE_ACCOUNT);
        assertThat(testMerchandise.getExpense_account()).isEqualTo(UPDATED_EXPENSE_ACCOUNT);
        assertThat(testMerchandise.getIncome_account()).isEqualTo(UPDATED_INCOME_ACCOUNT);
        assertThat(testMerchandise.getDiscount_account()).isEqualTo(UPDATED_DISCOUNT_ACCOUNT);
        assertThat(testMerchandise.getSale_account()).isEqualTo(UPDATED_SALE_ACCOUNT);
        assertThat(testMerchandise.getReturn_account()).isEqualTo(UPDATED_RETURN_ACCOUNT);
        assertThat(testMerchandise.getRate_ckmh()).isEqualTo(UPDATED_RATE_CKMH);
        assertThat(testMerchandise.getFixed_purchase_price()).isEqualTo(UPDATED_FIXED_PURCHASE_PRICE);
        assertThat(testMerchandise.getLatest_purchase_price()).isEqualTo(UPDATED_LATEST_PURCHASE_PRICE);
        assertThat(testMerchandise.getUnit_price_sell_1()).isEqualTo(UPDATED_UNIT_PRICE_SELL_1);
        assertThat(testMerchandise.getUnit_price_sell_2()).isEqualTo(UPDATED_UNIT_PRICE_SELL_2);
        assertThat(testMerchandise.getUnit_price_sell_3()).isEqualTo(UPDATED_UNIT_PRICE_SELL_3);
        assertThat(testMerchandise.getFixed_unit_price()).isEqualTo(UPDATED_FIXED_UNIT_PRICE);
        assertThat(testMerchandise.getUnit_price_after_tax()).isEqualTo(UPDATED_UNIT_PRICE_AFTER_TAX);
        assertThat(testMerchandise.getTax_rate_gtgt()).isEqualTo(UPDATED_TAX_RATE_GTGT);
        assertThat(testMerchandise.getTax_rate_nk()).isEqualTo(UPDATED_TAX_RATE_NK);
        assertThat(testMerchandise.getTax_rate_xk()).isEqualTo(UPDATED_TAX_RATE_XK);
        assertThat(testMerchandise.getGroup_hhdv_taxable_ttdb()).isEqualTo(UPDATED_GROUP_HHDV_TAXABLE_TTDB);
        assertThat(testMerchandise.getUnfollow()).isEqualTo(UPDATED_UNFOLLOW);
        assertThat(testMerchandise.getInventory_number()).isEqualTo(UPDATED_INVENTORY_NUMBER);
        assertThat(testMerchandise.getCharacteristic()).isEqualTo(UPDATED_CHARACTERISTIC);
        assertThat(testMerchandise.getInventory_value()).isEqualTo(UPDATED_INVENTORY_VALUE);
        assertThat(testMerchandise.getFollow()).isEqualTo(UPDATED_FOLLOW);
        assertThat(testMerchandise.getDiscount()).isEqualTo(UPDATED_DISCOUNT);
        assertThat(testMerchandise.getFrom_amount()).isEqualTo(UPDATED_FROM_AMOUNT);
        assertThat(testMerchandise.getTo_amount()).isEqualTo(UPDATED_TO_AMOUNT);
        assertThat(testMerchandise.getPercent_discount()).isEqualTo(UPDATED_PERCENT_DISCOUNT);
        assertThat(testMerchandise.getDiscount_amount()).isEqualTo(UPDATED_DISCOUNT_AMOUNT);
        assertThat(testMerchandise.getConversion_unit()).isEqualTo(UPDATED_CONVERSION_UNIT);
        assertThat(testMerchandise.getPrimary_unit_conversion_rate()).isEqualTo(UPDATED_PRIMARY_UNIT_CONVERSION_RATE);
        assertThat(testMerchandise.getCalculation()).isEqualTo(UPDATED_CALCULATION);
        assertThat(testMerchandise.getDescribe1()).isEqualTo(UPDATED_DESCRIBE_1);
        assertThat(testMerchandise.getUnit_price_1()).isEqualTo(UPDATED_UNIT_PRICE_1);
        assertThat(testMerchandise.getUnit_price_2()).isEqualTo(UPDATED_UNIT_PRICE_2);
        assertThat(testMerchandise.getUnit_price_3()).isEqualTo(UPDATED_UNIT_PRICE_3);
        assertThat(testMerchandise.getFixed_unit_price1()).isEqualTo(UPDATED_FIXED_UNIT_PRICE_1);
        assertThat(testMerchandise.getMaterial_code()).isEqualTo(UPDATED_MATERIAL_CODE);
        assertThat(testMerchandise.getMaterial_name()).isEqualTo(UPDATED_MATERIAL_NAME);
        assertThat(testMerchandise.getDvt()).isEqualTo(UPDATED_DVT);
        assertThat(testMerchandise.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testMerchandise.getSpecification_code()).isEqualTo(UPDATED_SPECIFICATION_CODE);
        assertThat(testMerchandise.getDisplay_name()).isEqualTo(UPDATED_DISPLAY_NAME);
        assertThat(testMerchandise.getAllow_same()).isEqualTo(UPDATED_ALLOW_SAME);
        assertThat(testMerchandise.getCreated_date()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testMerchandise.getKeyUUID()).isEqualTo(UPDATED_KEY_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingMerchandise() throws Exception {
        int databaseSizeBeforeUpdate = merchandiseRepository.findAll().size();
        merchandise.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMerchandiseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, merchandise.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(merchandise))
            )
            .andExpect(status().isBadRequest());

        // Validate the Merchandise in the database
        List<Merchandise> merchandiseList = merchandiseRepository.findAll();
        assertThat(merchandiseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchMerchandise() throws Exception {
        int databaseSizeBeforeUpdate = merchandiseRepository.findAll().size();
        merchandise.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMerchandiseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(merchandise))
            )
            .andExpect(status().isBadRequest());

        // Validate the Merchandise in the database
        List<Merchandise> merchandiseList = merchandiseRepository.findAll();
        assertThat(merchandiseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamMerchandise() throws Exception {
        int databaseSizeBeforeUpdate = merchandiseRepository.findAll().size();
        merchandise.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMerchandiseMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(merchandise))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Merchandise in the database
        List<Merchandise> merchandiseList = merchandiseRepository.findAll();
        assertThat(merchandiseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteMerchandise() throws Exception {
        // Initialize the database
        merchandiseRepository.saveAndFlush(merchandise);

        int databaseSizeBeforeDelete = merchandiseRepository.findAll().size();

        // Delete the merchandise
        restMerchandiseMockMvc
            .perform(delete(ENTITY_API_URL_ID, merchandise.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Merchandise> merchandiseList = merchandiseRepository.findAll();
        assertThat(merchandiseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
