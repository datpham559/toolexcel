//package com.softdreams.excel.web.rest;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.hamcrest.Matchers.hasItem;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//import com.softdreams.excel.IntegrationTest;
//import com.softdreams.excel.domain.Synthetic;
//import com.softdreams.excel.repository.SyntheticRepository;
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.util.List;
//import java.util.Random;
//import java.util.concurrent.atomic.AtomicLong;
//import javax.persistence.EntityManager;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// * Integration tests for the {@link SyntheticResource} REST controller.
// */
//@IntegrationTest
//@AutoConfigureMockMvc
//@WithMockUser
//class SyntheticResourceIT {
//
//    private static final String DEFAULT_VOUCHER_TYPE = "AAAAAAAAAA";
//    private static final String UPDATED_VOUCHER_TYPE = "BBBBBBBBBB";
//
//    private static final Integer DEFAULT_VOUCHER_TYPE_NO = 1;
//    private static final Integer UPDATED_VOUCHER_TYPE_NO = 2;
//
//    private static final String DEFAULT_VOUCHER_NO = "AAAAAAAAAA";
//    private static final String UPDATED_VOUCHER_NO = "BBBBBBBBBB";
//
//    private static final LocalDate DEFAULT_VOUCHER_DATE = LocalDate.ofEpochDay(0L);
//    private static final LocalDate UPDATED_VOUCHER_DATE = LocalDate.now(ZoneId.systemDefault());
//
//    private static final LocalDate DEFAULT_ACCOUNTING_DATE = LocalDate.ofEpochDay(0L);
//    private static final LocalDate UPDATED_ACCOUNTING_DATE = LocalDate.now(ZoneId.systemDefault());
//
//    private static final String DEFAULT_INVOICE_NO = "AAAAAAAAAA";
//    private static final String UPDATED_INVOICE_NO = "BBBBBBBBBB";
//
//    private static final LocalDate DEFAULT_INVOICE_DATE = LocalDate.ofEpochDay(0L);
//    private static final LocalDate UPDATED_INVOICE_DATE = LocalDate.now(ZoneId.systemDefault());
//
//    private static final String DEFAULT_DEBIT_ACCOUNT = "AAAAAAAAAA";
//    private static final String UPDATED_DEBIT_ACCOUNT = "BBBBBBBBBB";
//
//    private static final String DEFAULT_CREDIT_ACCOUNT = "AAAAAAAAAA";
//    private static final String UPDATED_CREDIT_ACCOUNT = "BBBBBBBBBB";
//
//    private static final String DEFAULT_CURRENCY_TYPE = "AAAAAAAAAA";
//    private static final String UPDATED_CURRENCY_TYPE = "BBBBBBBBBB";
//
//    private static final Long DEFAULT_CURRENCY = 1L;
//    private static final Long UPDATED_CURRENCY = 2L;
//
//    private static final String DEFAULT_MATERIAL_GOOD_CODE = "AAAAAAAAAA";
//    private static final String UPDATED_MATERIAL_GOOD_CODE = "BBBBBBBBBB";
//
//    private static final String DEFAULT_MATERIAL_GOOD_NAME = "AAAAAAAAAA";
//    private static final String UPDATED_MATERIAL_GOOD_NAME = "BBBBBBBBBB";
//
//    private static final String DEFAULT_STORAGE_IN = "AAAAAAAAAA";
//    private static final String UPDATED_STORAGE_IN = "BBBBBBBBBB";
//
//    private static final String DEFAULT_STORAGE_OUT = "AAAAAAAAAA";
//    private static final String UPDATED_STORAGE_OUT = "BBBBBBBBBB";
//
//    private static final String DEFAULT_CACULATION_UNIT = "AAAAAAAAAA";
//    private static final String UPDATED_CACULATION_UNIT = "BBBBBBBBBB";
//
//    private static final Long DEFAULT_AMOUNT = 1L;
//    private static final Long UPDATED_AMOUNT = 2L;
//
//    private static final Long DEFAULT_PRICE = 1L;
//    private static final Long UPDATED_PRICE = 2L;
//
//    private static final Long DEFAULT_TRANFER_RATE = 1L;
//    private static final Long UPDATED_TRANFER_RATE = 2L;
//
//    private static final Long DEFAULT_MONEY_TRANFER = 1L;
//    private static final Long UPDATED_MONEY_TRANFER = 2L;
//
//    private static final String DEFAULT_FIXED_ASSETS_TYPE = "AAAAAAAAAA";
//    private static final String UPDATED_FIXED_ASSETS_TYPE = "BBBBBBBBBB";
//
//    private static final String DEFAULT_FIXED_ASSETS_CODE = "AAAAAAAAAA";
//    private static final String UPDATED_FIXED_ASSETS_CODE = "BBBBBBBBBB";
//
//    private static final String DEFAULT_TOOLS_CODE = "AAAAAAAAAA";
//    private static final String UPDATED_TOOLS_CODE = "BBBBBBBBBB";
//
//    private static final String DEFAULT_DEBIT_OBJECT = "AAAAAAAAAA";
//    private static final String UPDATED_DEBIT_OBJECT = "BBBBBBBBBB";
//
//    private static final String DEFAULT_CREDIT_OBJECT = "AAAAAAAAAA";
//    private static final String UPDATED_CREDIT_OBJECT = "BBBBBBBBBB";
//
//    private static final String DEFAULT_UNIT = "AAAAAAAAAA";
//    private static final String UPDATED_UNIT = "BBBBBBBBBB";
//
//    private static final String DEFAULT_EMPLOYEE = "AAAAAAAAAA";
//    private static final String UPDATED_EMPLOYEE = "BBBBBBBBBB";
//
//    private static final String DEFAULT_BANK_ACCOUNT = "AAAAAAAAAA";
//    private static final String UPDATED_BANK_ACCOUNT = "BBBBBBBBBB";
//
//    private static final String DEFAULT_ITEM_COST = "AAAAAAAAAA";
//    private static final String UPDATED_ITEM_COST = "BBBBBBBBBB";
//
//    private static final String DEFAULT_CONSTRUCTION = "AAAAAAAAAA";
//    private static final String UPDATED_CONSTRUCTION = "BBBBBBBBBB";
//
//    private static final String DEFAULT_COST_SET = "AAAAAAAAAA";
//    private static final String UPDATED_COST_SET = "BBBBBBBBBB";
//
//    private static final String DEFAULT_PURCHASE_ORDER = "AAAAAAAAAA";
//    private static final String UPDATED_PURCHASE_ORDER = "BBBBBBBBBB";
//
//    private static final String DEFAULT_BUY_ORDER = "AAAAAAAAAA";
//    private static final String UPDATED_BUY_ORDER = "BBBBBBBBBB";
//
//    private static final String DEFAULT_PURCHASE_CONTRACT = "AAAAAAAAAA";
//    private static final String UPDATED_PURCHASE_CONTRACT = "BBBBBBBBBB";
//
//    private static final String DEFAULT_SALE_CONTRACT = "AAAAAAAAAA";
//    private static final String UPDATED_SALE_CONTRACT = "BBBBBBBBBB";
//
//    private static final String DEFAULT_STATS_CODE = "AAAAAAAAAA";
//    private static final String UPDATED_STATS_CODE = "BBBBBBBBBB";
//
//    private static final String DEFAULT_EXPLANATION = "AAAAAAAAAA";
//    private static final String UPDATED_EXPLANATION = "BBBBBBBBBB";
//
//    private static final String DEFAULT_EXPLANATION_DETAIL = "AAAAAAAAAA";
//    private static final String UPDATED_EXPLANATION_DETAIL = "BBBBBBBBBB";
//
//    private static final String DEFAULT_RECORD_STATUS = "AAAAAAAAAA";
//    private static final String UPDATED_RECORD_STATUS = "BBBBBBBBBB";
//
//    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
//    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());
//
//    private static final String DEFAULT_KEY_UUID = "AAAAAAAAAA";
//    private static final String UPDATED_KEY_UUID = "BBBBBBBBBB";
//
//    private static final String ENTITY_API_URL = "/api/synthetics";
//    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
//
//    private static Random random = new Random();
//    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
//
//    @Autowired
//    private SyntheticRepository syntheticRepository;
//
//    @Autowired
//    private EntityManager em;
//
//    @Autowired
//    private MockMvc restSyntheticMockMvc;
//
//    private Synthetic synthetic;
//
//    /**
//     * Create an entity for this test.
//     *
//     * This is a static method, as tests for other entities might also need it,
//     * if they test an entity which requires the current entity.
//     */
//    public static Synthetic createEntity(EntityManager em) {
//        Synthetic synthetic = new Synthetic()
//            .voucherType(DEFAULT_VOUCHER_TYPE)
//            .voucherTypeNo(DEFAULT_VOUCHER_TYPE_NO)
//            .voucherNo(DEFAULT_VOUCHER_NO)
//            .voucherDate(DEFAULT_VOUCHER_DATE)
//            .accountingDate(DEFAULT_ACCOUNTING_DATE)
//            .invoiceNo(DEFAULT_INVOICE_NO)
//            .invoiceDate(DEFAULT_INVOICE_DATE)
//            .debitAccount(DEFAULT_DEBIT_ACCOUNT)
//            .creditAccount(DEFAULT_CREDIT_ACCOUNT)
//            .currencyType(DEFAULT_CURRENCY_TYPE)
//            .currency(DEFAULT_CURRENCY)
//            .materialGoodCode(DEFAULT_MATERIAL_GOOD_CODE)
//            .materialGoodName(DEFAULT_MATERIAL_GOOD_NAME)
//            .storageIn(DEFAULT_STORAGE_IN)
//            .storageOut(DEFAULT_STORAGE_OUT)
//            .caculationUnit(DEFAULT_CACULATION_UNIT)
//            .amount(DEFAULT_AMOUNT)
//            .price(DEFAULT_PRICE)
//            .tranferRate(DEFAULT_TRANFER_RATE)
//            .moneyTranfer(DEFAULT_MONEY_TRANFER)
//            .fixedAssetsType(DEFAULT_FIXED_ASSETS_TYPE)
//            .fixedAssetsCode(DEFAULT_FIXED_ASSETS_CODE)
//            .toolsCode(DEFAULT_TOOLS_CODE)
//            .debitObject(DEFAULT_DEBIT_OBJECT)
//            .creditObject(DEFAULT_CREDIT_OBJECT)
//            .unit(DEFAULT_UNIT)
//            .employee(DEFAULT_EMPLOYEE)
//            .bankAccount(DEFAULT_BANK_ACCOUNT)
//            .itemCost(DEFAULT_ITEM_COST)
//            .construction(DEFAULT_CONSTRUCTION)
//            .costSet(DEFAULT_COST_SET)
//            .purchaseOrder(DEFAULT_PURCHASE_ORDER)
//            .buyOrder(DEFAULT_BUY_ORDER)
//            .purchaseContract(DEFAULT_PURCHASE_CONTRACT)
//            .saleContract(DEFAULT_SALE_CONTRACT)
//            .statsCode(DEFAULT_STATS_CODE)
//            .explanation(DEFAULT_EXPLANATION)
//            .explanationDetail(DEFAULT_EXPLANATION_DETAIL)
//            .recordStatus(DEFAULT_RECORD_STATUS)
//            .createdDate(DEFAULT_CREATED_DATE)
//            .keyUUID(DEFAULT_KEY_UUID);
//        return synthetic;
//    }
//
//    /**
//     * Create an updated entity for this test.
//     *
//     * This is a static method, as tests for other entities might also need it,
//     * if they test an entity which requires the current entity.
//     */
//    public static Synthetic createUpdatedEntity(EntityManager em) {
//        Synthetic synthetic = new Synthetic()
//            .voucherType(UPDATED_VOUCHER_TYPE)
//            .voucherTypeNo(UPDATED_VOUCHER_TYPE_NO)
//            .voucherNo(UPDATED_VOUCHER_NO)
//            .voucherDate(UPDATED_VOUCHER_DATE)
//            .accountingDate(UPDATED_ACCOUNTING_DATE)
//            .invoiceNo(UPDATED_INVOICE_NO)
//            .invoiceDate(UPDATED_INVOICE_DATE)
//            .debitAccount(UPDATED_DEBIT_ACCOUNT)
//            .creditAccount(UPDATED_CREDIT_ACCOUNT)
//            .currencyType(UPDATED_CURRENCY_TYPE)
//            .currency(UPDATED_CURRENCY)
//            .materialGoodCode(UPDATED_MATERIAL_GOOD_CODE)
//            .materialGoodName(UPDATED_MATERIAL_GOOD_NAME)
//            .storageIn(UPDATED_STORAGE_IN)
//            .storageOut(UPDATED_STORAGE_OUT)
//            .caculationUnit(UPDATED_CACULATION_UNIT)
//            .amount(UPDATED_AMOUNT)
//            .price(UPDATED_PRICE)
//            .tranferRate(UPDATED_TRANFER_RATE)
//            .moneyTranfer(UPDATED_MONEY_TRANFER)
//            .fixedAssetsType(UPDATED_FIXED_ASSETS_TYPE)
//            .fixedAssetsCode(UPDATED_FIXED_ASSETS_CODE)
//            .toolsCode(UPDATED_TOOLS_CODE)
//            .debitObject(UPDATED_DEBIT_OBJECT)
//            .creditObject(UPDATED_CREDIT_OBJECT)
//            .unit(UPDATED_UNIT)
//            .employee(UPDATED_EMPLOYEE)
//            .bankAccount(UPDATED_BANK_ACCOUNT)
//            .itemCost(UPDATED_ITEM_COST)
//            .construction(UPDATED_CONSTRUCTION)
//            .costSet(UPDATED_COST_SET)
//            .purchaseOrder(UPDATED_PURCHASE_ORDER)
//            .buyOrder(UPDATED_BUY_ORDER)
//            .purchaseContract(UPDATED_PURCHASE_CONTRACT)
//            .saleContract(UPDATED_SALE_CONTRACT)
//            .statsCode(UPDATED_STATS_CODE)
//            .explanation(UPDATED_EXPLANATION)
//            .explanationDetail(UPDATED_EXPLANATION_DETAIL)
//            .recordStatus(UPDATED_RECORD_STATUS)
//            .createdDate(UPDATED_CREATED_DATE)
//            .keyUUID(UPDATED_KEY_UUID);
//        return synthetic;
//    }
//
//    @BeforeEach
//    public void initTest() {
//        synthetic = createEntity(em);
//    }
//
//    @Test
//    @Transactional
//    void createSynthetic() throws Exception {
//        int databaseSizeBeforeCreate = syntheticRepository.findAll().size();
//        // Create the Synthetic
//        restSyntheticMockMvc
//            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(synthetic)))
//            .andExpect(status().isCreated());
//
//        // Validate the Synthetic in the database
//        List<Synthetic> syntheticList = syntheticRepository.findAll();
//        assertThat(syntheticList).hasSize(databaseSizeBeforeCreate + 1);
//        Synthetic testSynthetic = syntheticList.get(syntheticList.size() - 1);
//        assertThat(testSynthetic.getVoucherType()).isEqualTo(DEFAULT_VOUCHER_TYPE);
//        assertThat(testSynthetic.getVoucherTypeNo()).isEqualTo(DEFAULT_VOUCHER_TYPE_NO);
//        assertThat(testSynthetic.getVoucherNo()).isEqualTo(DEFAULT_VOUCHER_NO);
//        assertThat(testSynthetic.getVoucherDate()).isEqualTo(DEFAULT_VOUCHER_DATE);
//        assertThat(testSynthetic.getAccountingDate()).isEqualTo(DEFAULT_ACCOUNTING_DATE);
//        assertThat(testSynthetic.getInvoiceNo()).isEqualTo(DEFAULT_INVOICE_NO);
//        assertThat(testSynthetic.getInvoiceDate()).isEqualTo(DEFAULT_INVOICE_DATE);
//        assertThat(testSynthetic.getDebitAccount()).isEqualTo(DEFAULT_DEBIT_ACCOUNT);
//        assertThat(testSynthetic.getCreditAccount()).isEqualTo(DEFAULT_CREDIT_ACCOUNT);
//        assertThat(testSynthetic.getCurrencyType()).isEqualTo(DEFAULT_CURRENCY_TYPE);
//        assertThat(testSynthetic.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
//        assertThat(testSynthetic.getMaterialGoodCode()).isEqualTo(DEFAULT_MATERIAL_GOOD_CODE);
//        assertThat(testSynthetic.getMaterialGoodName()).isEqualTo(DEFAULT_MATERIAL_GOOD_NAME);
//        assertThat(testSynthetic.getStorageIn()).isEqualTo(DEFAULT_STORAGE_IN);
//        assertThat(testSynthetic.getStorageOut()).isEqualTo(DEFAULT_STORAGE_OUT);
//        assertThat(testSynthetic.getCaculationUnit()).isEqualTo(DEFAULT_CACULATION_UNIT);
//        assertThat(testSynthetic.getAmount()).isEqualTo(DEFAULT_AMOUNT);
//        assertThat(testSynthetic.getPrice()).isEqualTo(DEFAULT_PRICE);
//        assertThat(testSynthetic.getTranferRate()).isEqualTo(DEFAULT_TRANFER_RATE);
//        assertThat(testSynthetic.getMoneyTranfer()).isEqualTo(DEFAULT_MONEY_TRANFER);
//        assertThat(testSynthetic.getFixedAssetsType()).isEqualTo(DEFAULT_FIXED_ASSETS_TYPE);
//        assertThat(testSynthetic.getFixedAssetsCode()).isEqualTo(DEFAULT_FIXED_ASSETS_CODE);
//        assertThat(testSynthetic.getToolsCode()).isEqualTo(DEFAULT_TOOLS_CODE);
//        assertThat(testSynthetic.getDebitObject()).isEqualTo(DEFAULT_DEBIT_OBJECT);
//        assertThat(testSynthetic.getCreditObject()).isEqualTo(DEFAULT_CREDIT_OBJECT);
//        assertThat(testSynthetic.getUnit()).isEqualTo(DEFAULT_UNIT);
//        assertThat(testSynthetic.getEmployee()).isEqualTo(DEFAULT_EMPLOYEE);
//        assertThat(testSynthetic.getBankAccount()).isEqualTo(DEFAULT_BANK_ACCOUNT);
//        assertThat(testSynthetic.getItemCost()).isEqualTo(DEFAULT_ITEM_COST);
//        assertThat(testSynthetic.getConstruction()).isEqualTo(DEFAULT_CONSTRUCTION);
//        assertThat(testSynthetic.getCostSet()).isEqualTo(DEFAULT_COST_SET);
//        assertThat(testSynthetic.getPurchaseOrder()).isEqualTo(DEFAULT_PURCHASE_ORDER);
//        assertThat(testSynthetic.getBuyOrder()).isEqualTo(DEFAULT_BUY_ORDER);
//        assertThat(testSynthetic.getPurchaseContract()).isEqualTo(DEFAULT_PURCHASE_CONTRACT);
//        assertThat(testSynthetic.getSaleContract()).isEqualTo(DEFAULT_SALE_CONTRACT);
//        assertThat(testSynthetic.getStatsCode()).isEqualTo(DEFAULT_STATS_CODE);
//        assertThat(testSynthetic.getExplanation()).isEqualTo(DEFAULT_EXPLANATION);
//        assertThat(testSynthetic.getExplanationDetail()).isEqualTo(DEFAULT_EXPLANATION_DETAIL);
//        assertThat(testSynthetic.getRecordStatus()).isEqualTo(DEFAULT_RECORD_STATUS);
//        assertThat(testSynthetic.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
//        assertThat(testSynthetic.getKeyUUID()).isEqualTo(DEFAULT_KEY_UUID);
//    }
//
//    @Test
//    @Transactional
//    void createSyntheticWithExistingId() throws Exception {
//        // Create the Synthetic with an existing ID
//        synthetic.setId(1L);
//
//        int databaseSizeBeforeCreate = syntheticRepository.findAll().size();
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restSyntheticMockMvc
//            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(synthetic)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the Synthetic in the database
//        List<Synthetic> syntheticList = syntheticRepository.findAll();
//        assertThat(syntheticList).hasSize(databaseSizeBeforeCreate);
//    }
//
//    @Test
//    @Transactional
//    void getAllSynthetics() throws Exception {
//        // Initialize the database
//        syntheticRepository.saveAndFlush(synthetic);
//
//        // Get all the syntheticList
//        restSyntheticMockMvc
//            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(synthetic.getId().intValue())))
//            .andExpect(jsonPath("$.[*].voucherType").value(hasItem(DEFAULT_VOUCHER_TYPE)))
//            .andExpect(jsonPath("$.[*].voucherTypeNo").value(hasItem(DEFAULT_VOUCHER_TYPE_NO)))
//            .andExpect(jsonPath("$.[*].voucherNo").value(hasItem(DEFAULT_VOUCHER_NO)))
//            .andExpect(jsonPath("$.[*].voucherDate").value(hasItem(DEFAULT_VOUCHER_DATE.toString())))
//            .andExpect(jsonPath("$.[*].accountingDate").value(hasItem(DEFAULT_ACCOUNTING_DATE.toString())))
//            .andExpect(jsonPath("$.[*].invoiceNo").value(hasItem(DEFAULT_INVOICE_NO)))
//            .andExpect(jsonPath("$.[*].invoiceDate").value(hasItem(DEFAULT_INVOICE_DATE.toString())))
//            .andExpect(jsonPath("$.[*].debitAccount").value(hasItem(DEFAULT_DEBIT_ACCOUNT)))
//            .andExpect(jsonPath("$.[*].creditAccount").value(hasItem(DEFAULT_CREDIT_ACCOUNT)))
//            .andExpect(jsonPath("$.[*].currencyType").value(hasItem(DEFAULT_CURRENCY_TYPE)))
//            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY.intValue())))
//            .andExpect(jsonPath("$.[*].materialGoodCode").value(hasItem(DEFAULT_MATERIAL_GOOD_CODE)))
//            .andExpect(jsonPath("$.[*].materialGoodName").value(hasItem(DEFAULT_MATERIAL_GOOD_NAME)))
//            .andExpect(jsonPath("$.[*].storageIn").value(hasItem(DEFAULT_STORAGE_IN)))
//            .andExpect(jsonPath("$.[*].storageOut").value(hasItem(DEFAULT_STORAGE_OUT)))
//            .andExpect(jsonPath("$.[*].caculationUnit").value(hasItem(DEFAULT_CACULATION_UNIT)))
//            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())))
//            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.intValue())))
//            .andExpect(jsonPath("$.[*].tranferRate").value(hasItem(DEFAULT_TRANFER_RATE.intValue())))
//            .andExpect(jsonPath("$.[*].moneyTranfer").value(hasItem(DEFAULT_MONEY_TRANFER.intValue())))
//            .andExpect(jsonPath("$.[*].fixedAssetsType").value(hasItem(DEFAULT_FIXED_ASSETS_TYPE)))
//            .andExpect(jsonPath("$.[*].fixedAssetsCode").value(hasItem(DEFAULT_FIXED_ASSETS_CODE)))
//            .andExpect(jsonPath("$.[*].toolsCode").value(hasItem(DEFAULT_TOOLS_CODE)))
//            .andExpect(jsonPath("$.[*].debitObject").value(hasItem(DEFAULT_DEBIT_OBJECT)))
//            .andExpect(jsonPath("$.[*].creditObject").value(hasItem(DEFAULT_CREDIT_OBJECT)))
//            .andExpect(jsonPath("$.[*].unit").value(hasItem(DEFAULT_UNIT)))
//            .andExpect(jsonPath("$.[*].employee").value(hasItem(DEFAULT_EMPLOYEE)))
//            .andExpect(jsonPath("$.[*].bankAccount").value(hasItem(DEFAULT_BANK_ACCOUNT)))
//            .andExpect(jsonPath("$.[*].itemCost").value(hasItem(DEFAULT_ITEM_COST)))
//            .andExpect(jsonPath("$.[*].construction").value(hasItem(DEFAULT_CONSTRUCTION)))
//            .andExpect(jsonPath("$.[*].costSet").value(hasItem(DEFAULT_COST_SET)))
//            .andExpect(jsonPath("$.[*].purchaseOrder").value(hasItem(DEFAULT_PURCHASE_ORDER)))
//            .andExpect(jsonPath("$.[*].buyOrder").value(hasItem(DEFAULT_BUY_ORDER)))
//            .andExpect(jsonPath("$.[*].purchaseContract").value(hasItem(DEFAULT_PURCHASE_CONTRACT)))
//            .andExpect(jsonPath("$.[*].saleContract").value(hasItem(DEFAULT_SALE_CONTRACT)))
//            .andExpect(jsonPath("$.[*].statsCode").value(hasItem(DEFAULT_STATS_CODE)))
//            .andExpect(jsonPath("$.[*].explanation").value(hasItem(DEFAULT_EXPLANATION)))
//            .andExpect(jsonPath("$.[*].explanationDetail").value(hasItem(DEFAULT_EXPLANATION_DETAIL)))
//            .andExpect(jsonPath("$.[*].recordStatus").value(hasItem(DEFAULT_RECORD_STATUS)))
//            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
//            .andExpect(jsonPath("$.[*].keyUUID").value(hasItem(DEFAULT_KEY_UUID)));
//    }
//
//    @Test
//    @Transactional
//    void getSynthetic() throws Exception {
//        // Initialize the database
//        syntheticRepository.saveAndFlush(synthetic);
//
//        // Get the synthetic
//        restSyntheticMockMvc
//            .perform(get(ENTITY_API_URL_ID, synthetic.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.id").value(synthetic.getId().intValue()))
//            .andExpect(jsonPath("$.voucherType").value(DEFAULT_VOUCHER_TYPE))
//            .andExpect(jsonPath("$.voucherTypeNo").value(DEFAULT_VOUCHER_TYPE_NO))
//            .andExpect(jsonPath("$.voucherNo").value(DEFAULT_VOUCHER_NO))
//            .andExpect(jsonPath("$.voucherDate").value(DEFAULT_VOUCHER_DATE.toString()))
//            .andExpect(jsonPath("$.accountingDate").value(DEFAULT_ACCOUNTING_DATE.toString()))
//            .andExpect(jsonPath("$.invoiceNo").value(DEFAULT_INVOICE_NO))
//            .andExpect(jsonPath("$.invoiceDate").value(DEFAULT_INVOICE_DATE.toString()))
//            .andExpect(jsonPath("$.debitAccount").value(DEFAULT_DEBIT_ACCOUNT))
//            .andExpect(jsonPath("$.creditAccount").value(DEFAULT_CREDIT_ACCOUNT))
//            .andExpect(jsonPath("$.currencyType").value(DEFAULT_CURRENCY_TYPE))
//            .andExpect(jsonPath("$.currency").value(DEFAULT_CURRENCY.intValue()))
//            .andExpect(jsonPath("$.materialGoodCode").value(DEFAULT_MATERIAL_GOOD_CODE))
//            .andExpect(jsonPath("$.materialGoodName").value(DEFAULT_MATERIAL_GOOD_NAME))
//            .andExpect(jsonPath("$.storageIn").value(DEFAULT_STORAGE_IN))
//            .andExpect(jsonPath("$.storageOut").value(DEFAULT_STORAGE_OUT))
//            .andExpect(jsonPath("$.caculationUnit").value(DEFAULT_CACULATION_UNIT))
//            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()))
//            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.intValue()))
//            .andExpect(jsonPath("$.tranferRate").value(DEFAULT_TRANFER_RATE.intValue()))
//            .andExpect(jsonPath("$.moneyTranfer").value(DEFAULT_MONEY_TRANFER.intValue()))
//            .andExpect(jsonPath("$.fixedAssetsType").value(DEFAULT_FIXED_ASSETS_TYPE))
//            .andExpect(jsonPath("$.fixedAssetsCode").value(DEFAULT_FIXED_ASSETS_CODE))
//            .andExpect(jsonPath("$.toolsCode").value(DEFAULT_TOOLS_CODE))
//            .andExpect(jsonPath("$.debitObject").value(DEFAULT_DEBIT_OBJECT))
//            .andExpect(jsonPath("$.creditObject").value(DEFAULT_CREDIT_OBJECT))
//            .andExpect(jsonPath("$.unit").value(DEFAULT_UNIT))
//            .andExpect(jsonPath("$.employee").value(DEFAULT_EMPLOYEE))
//            .andExpect(jsonPath("$.bankAccount").value(DEFAULT_BANK_ACCOUNT))
//            .andExpect(jsonPath("$.itemCost").value(DEFAULT_ITEM_COST))
//            .andExpect(jsonPath("$.construction").value(DEFAULT_CONSTRUCTION))
//            .andExpect(jsonPath("$.costSet").value(DEFAULT_COST_SET))
//            .andExpect(jsonPath("$.purchaseOrder").value(DEFAULT_PURCHASE_ORDER))
//            .andExpect(jsonPath("$.buyOrder").value(DEFAULT_BUY_ORDER))
//            .andExpect(jsonPath("$.purchaseContract").value(DEFAULT_PURCHASE_CONTRACT))
//            .andExpect(jsonPath("$.saleContract").value(DEFAULT_SALE_CONTRACT))
//            .andExpect(jsonPath("$.statsCode").value(DEFAULT_STATS_CODE))
//            .andExpect(jsonPath("$.explanation").value(DEFAULT_EXPLANATION))
//            .andExpect(jsonPath("$.explanationDetail").value(DEFAULT_EXPLANATION_DETAIL))
//            .andExpect(jsonPath("$.recordStatus").value(DEFAULT_RECORD_STATUS))
//            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
//            .andExpect(jsonPath("$.keyUUID").value(DEFAULT_KEY_UUID));
//    }
//
//    @Test
//    @Transactional
//    void getNonExistingSynthetic() throws Exception {
//        // Get the synthetic
//        restSyntheticMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Transactional
//    void putExistingSynthetic() throws Exception {
//        // Initialize the database
//        syntheticRepository.saveAndFlush(synthetic);
//
//        int databaseSizeBeforeUpdate = syntheticRepository.findAll().size();
//
//        // Update the synthetic
//        Synthetic updatedSynthetic = syntheticRepository.findById(synthetic.getId()).get();
//        // Disconnect from session so that the updates on updatedSynthetic are not directly saved in db
//        em.detach(updatedSynthetic);
//        updatedSynthetic
//            .voucherType(UPDATED_VOUCHER_TYPE)
//            .voucherTypeNo(UPDATED_VOUCHER_TYPE_NO)
//            .voucherNo(UPDATED_VOUCHER_NO)
//            .voucherDate(UPDATED_VOUCHER_DATE)
//            .accountingDate(UPDATED_ACCOUNTING_DATE)
//            .invoiceNo(UPDATED_INVOICE_NO)
//            .invoiceDate(UPDATED_INVOICE_DATE)
//            .debitAccount(UPDATED_DEBIT_ACCOUNT)
//            .creditAccount(UPDATED_CREDIT_ACCOUNT)
//            .currencyType(UPDATED_CURRENCY_TYPE)
//            .currency(UPDATED_CURRENCY)
//            .materialGoodCode(UPDATED_MATERIAL_GOOD_CODE)
//            .materialGoodName(UPDATED_MATERIAL_GOOD_NAME)
//            .storageIn(UPDATED_STORAGE_IN)
//            .storageOut(UPDATED_STORAGE_OUT)
//            .caculationUnit(UPDATED_CACULATION_UNIT)
//            .amount(UPDATED_AMOUNT)
//            .price(UPDATED_PRICE)
//            .tranferRate(UPDATED_TRANFER_RATE)
//            .moneyTranfer(UPDATED_MONEY_TRANFER)
//            .fixedAssetsType(UPDATED_FIXED_ASSETS_TYPE)
//            .fixedAssetsCode(UPDATED_FIXED_ASSETS_CODE)
//            .toolsCode(UPDATED_TOOLS_CODE)
//            .debitObject(UPDATED_DEBIT_OBJECT)
//            .creditObject(UPDATED_CREDIT_OBJECT)
//            .unit(UPDATED_UNIT)
//            .employee(UPDATED_EMPLOYEE)
//            .bankAccount(UPDATED_BANK_ACCOUNT)
//            .itemCost(UPDATED_ITEM_COST)
//            .construction(UPDATED_CONSTRUCTION)
//            .costSet(UPDATED_COST_SET)
//            .purchaseOrder(UPDATED_PURCHASE_ORDER)
//            .buyOrder(UPDATED_BUY_ORDER)
//            .purchaseContract(UPDATED_PURCHASE_CONTRACT)
//            .saleContract(UPDATED_SALE_CONTRACT)
//            .statsCode(UPDATED_STATS_CODE)
//            .explanation(UPDATED_EXPLANATION)
//            .explanationDetail(UPDATED_EXPLANATION_DETAIL)
//            .recordStatus(UPDATED_RECORD_STATUS)
//            .createdDate(UPDATED_CREATED_DATE)
//            .keyUUID(UPDATED_KEY_UUID);
//
//        restSyntheticMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, updatedSynthetic.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(updatedSynthetic))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the Synthetic in the database
//        List<Synthetic> syntheticList = syntheticRepository.findAll();
//        assertThat(syntheticList).hasSize(databaseSizeBeforeUpdate);
//        Synthetic testSynthetic = syntheticList.get(syntheticList.size() - 1);
//        assertThat(testSynthetic.getVoucherType()).isEqualTo(UPDATED_VOUCHER_TYPE);
//        assertThat(testSynthetic.getVoucherTypeNo()).isEqualTo(UPDATED_VOUCHER_TYPE_NO);
//        assertThat(testSynthetic.getVoucherNo()).isEqualTo(UPDATED_VOUCHER_NO);
//        assertThat(testSynthetic.getVoucherDate()).isEqualTo(UPDATED_VOUCHER_DATE);
//        assertThat(testSynthetic.getAccountingDate()).isEqualTo(UPDATED_ACCOUNTING_DATE);
//        assertThat(testSynthetic.getInvoiceNo()).isEqualTo(UPDATED_INVOICE_NO);
//        assertThat(testSynthetic.getInvoiceDate()).isEqualTo(UPDATED_INVOICE_DATE);
//        assertThat(testSynthetic.getDebitAccount()).isEqualTo(UPDATED_DEBIT_ACCOUNT);
//        assertThat(testSynthetic.getCreditAccount()).isEqualTo(UPDATED_CREDIT_ACCOUNT);
//        assertThat(testSynthetic.getCurrencyType()).isEqualTo(UPDATED_CURRENCY_TYPE);
//        assertThat(testSynthetic.getCurrency()).isEqualTo(UPDATED_CURRENCY);
//        assertThat(testSynthetic.getMaterialGoodCode()).isEqualTo(UPDATED_MATERIAL_GOOD_CODE);
//        assertThat(testSynthetic.getMaterialGoodName()).isEqualTo(UPDATED_MATERIAL_GOOD_NAME);
//        assertThat(testSynthetic.getStorageIn()).isEqualTo(UPDATED_STORAGE_IN);
//        assertThat(testSynthetic.getStorageOut()).isEqualTo(UPDATED_STORAGE_OUT);
//        assertThat(testSynthetic.getCaculationUnit()).isEqualTo(UPDATED_CACULATION_UNIT);
//        assertThat(testSynthetic.getAmount()).isEqualTo(UPDATED_AMOUNT);
//        assertThat(testSynthetic.getPrice()).isEqualTo(UPDATED_PRICE);
//        assertThat(testSynthetic.getTranferRate()).isEqualTo(UPDATED_TRANFER_RATE);
//        assertThat(testSynthetic.getMoneyTranfer()).isEqualTo(UPDATED_MONEY_TRANFER);
//        assertThat(testSynthetic.getFixedAssetsType()).isEqualTo(UPDATED_FIXED_ASSETS_TYPE);
//        assertThat(testSynthetic.getFixedAssetsCode()).isEqualTo(UPDATED_FIXED_ASSETS_CODE);
//        assertThat(testSynthetic.getToolsCode()).isEqualTo(UPDATED_TOOLS_CODE);
//        assertThat(testSynthetic.getDebitObject()).isEqualTo(UPDATED_DEBIT_OBJECT);
//        assertThat(testSynthetic.getCreditObject()).isEqualTo(UPDATED_CREDIT_OBJECT);
//        assertThat(testSynthetic.getUnit()).isEqualTo(UPDATED_UNIT);
//        assertThat(testSynthetic.getEmployee()).isEqualTo(UPDATED_EMPLOYEE);
//        assertThat(testSynthetic.getBankAccount()).isEqualTo(UPDATED_BANK_ACCOUNT);
//        assertThat(testSynthetic.getItemCost()).isEqualTo(UPDATED_ITEM_COST);
//        assertThat(testSynthetic.getConstruction()).isEqualTo(UPDATED_CONSTRUCTION);
//        assertThat(testSynthetic.getCostSet()).isEqualTo(UPDATED_COST_SET);
//        assertThat(testSynthetic.getPurchaseOrder()).isEqualTo(UPDATED_PURCHASE_ORDER);
//        assertThat(testSynthetic.getBuyOrder()).isEqualTo(UPDATED_BUY_ORDER);
//        assertThat(testSynthetic.getPurchaseContract()).isEqualTo(UPDATED_PURCHASE_CONTRACT);
//        assertThat(testSynthetic.getSaleContract()).isEqualTo(UPDATED_SALE_CONTRACT);
//        assertThat(testSynthetic.getStatsCode()).isEqualTo(UPDATED_STATS_CODE);
//        assertThat(testSynthetic.getExplanation()).isEqualTo(UPDATED_EXPLANATION);
//        assertThat(testSynthetic.getExplanationDetail()).isEqualTo(UPDATED_EXPLANATION_DETAIL);
//        assertThat(testSynthetic.getRecordStatus()).isEqualTo(UPDATED_RECORD_STATUS);
//        assertThat(testSynthetic.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
//        assertThat(testSynthetic.getKeyUUID()).isEqualTo(UPDATED_KEY_UUID);
//    }
//
//    @Test
//    @Transactional
//    void putNonExistingSynthetic() throws Exception {
//        int databaseSizeBeforeUpdate = syntheticRepository.findAll().size();
//        synthetic.setId(count.incrementAndGet());
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restSyntheticMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, synthetic.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(synthetic))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the Synthetic in the database
//        List<Synthetic> syntheticList = syntheticRepository.findAll();
//        assertThat(syntheticList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void putWithIdMismatchSynthetic() throws Exception {
//        int databaseSizeBeforeUpdate = syntheticRepository.findAll().size();
//        synthetic.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restSyntheticMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(synthetic))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the Synthetic in the database
//        List<Synthetic> syntheticList = syntheticRepository.findAll();
//        assertThat(syntheticList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void putWithMissingIdPathParamSynthetic() throws Exception {
//        int databaseSizeBeforeUpdate = syntheticRepository.findAll().size();
//        synthetic.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restSyntheticMockMvc
//            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(synthetic)))
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the Synthetic in the database
//        List<Synthetic> syntheticList = syntheticRepository.findAll();
//        assertThat(syntheticList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void partialUpdateSyntheticWithPatch() throws Exception {
//        // Initialize the database
//        syntheticRepository.saveAndFlush(synthetic);
//
//        int databaseSizeBeforeUpdate = syntheticRepository.findAll().size();
//
//        // Update the synthetic using partial update
//        Synthetic partialUpdatedSynthetic = new Synthetic();
//        partialUpdatedSynthetic.setId(synthetic.getId());
//
//        partialUpdatedSynthetic
//            .voucherType(UPDATED_VOUCHER_TYPE)
//            .voucherTypeNo(UPDATED_VOUCHER_TYPE_NO)
//            .accountingDate(UPDATED_ACCOUNTING_DATE)
//            .debitAccount(UPDATED_DEBIT_ACCOUNT)
//            .currencyType(UPDATED_CURRENCY_TYPE)
//            .storageIn(UPDATED_STORAGE_IN)
//            .storageOut(UPDATED_STORAGE_OUT)
//            .tranferRate(UPDATED_TRANFER_RATE)
//            .moneyTranfer(UPDATED_MONEY_TRANFER)
//            .toolsCode(UPDATED_TOOLS_CODE)
//            .creditObject(UPDATED_CREDIT_OBJECT)
//            .employee(UPDATED_EMPLOYEE)
//            .bankAccount(UPDATED_BANK_ACCOUNT)
//            .itemCost(UPDATED_ITEM_COST)
//            .costSet(UPDATED_COST_SET)
//            .buyOrder(UPDATED_BUY_ORDER)
//            .purchaseContract(UPDATED_PURCHASE_CONTRACT)
//            .saleContract(UPDATED_SALE_CONTRACT)
//            .explanation(UPDATED_EXPLANATION)
//            .explanationDetail(UPDATED_EXPLANATION_DETAIL)
//            .keyUUID(UPDATED_KEY_UUID);
//
//        restSyntheticMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, partialUpdatedSynthetic.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSynthetic))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the Synthetic in the database
//        List<Synthetic> syntheticList = syntheticRepository.findAll();
//        assertThat(syntheticList).hasSize(databaseSizeBeforeUpdate);
//        Synthetic testSynthetic = syntheticList.get(syntheticList.size() - 1);
//        assertThat(testSynthetic.getVoucherType()).isEqualTo(UPDATED_VOUCHER_TYPE);
//        assertThat(testSynthetic.getVoucherTypeNo()).isEqualTo(UPDATED_VOUCHER_TYPE_NO);
//        assertThat(testSynthetic.getVoucherNo()).isEqualTo(DEFAULT_VOUCHER_NO);
//        assertThat(testSynthetic.getVoucherDate()).isEqualTo(DEFAULT_VOUCHER_DATE);
//        assertThat(testSynthetic.getAccountingDate()).isEqualTo(UPDATED_ACCOUNTING_DATE);
//        assertThat(testSynthetic.getInvoiceNo()).isEqualTo(DEFAULT_INVOICE_NO);
//        assertThat(testSynthetic.getInvoiceDate()).isEqualTo(DEFAULT_INVOICE_DATE);
//        assertThat(testSynthetic.getDebitAccount()).isEqualTo(UPDATED_DEBIT_ACCOUNT);
//        assertThat(testSynthetic.getCreditAccount()).isEqualTo(DEFAULT_CREDIT_ACCOUNT);
//        assertThat(testSynthetic.getCurrencyType()).isEqualTo(UPDATED_CURRENCY_TYPE);
//        assertThat(testSynthetic.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
//        assertThat(testSynthetic.getMaterialGoodCode()).isEqualTo(DEFAULT_MATERIAL_GOOD_CODE);
//        assertThat(testSynthetic.getMaterialGoodName()).isEqualTo(DEFAULT_MATERIAL_GOOD_NAME);
//        assertThat(testSynthetic.getStorageIn()).isEqualTo(UPDATED_STORAGE_IN);
//        assertThat(testSynthetic.getStorageOut()).isEqualTo(UPDATED_STORAGE_OUT);
//        assertThat(testSynthetic.getCaculationUnit()).isEqualTo(DEFAULT_CACULATION_UNIT);
//        assertThat(testSynthetic.getAmount()).isEqualTo(DEFAULT_AMOUNT);
//        assertThat(testSynthetic.getPrice()).isEqualTo(DEFAULT_PRICE);
//        assertThat(testSynthetic.getTranferRate()).isEqualTo(UPDATED_TRANFER_RATE);
//        assertThat(testSynthetic.getMoneyTranfer()).isEqualTo(UPDATED_MONEY_TRANFER);
//        assertThat(testSynthetic.getFixedAssetsType()).isEqualTo(DEFAULT_FIXED_ASSETS_TYPE);
//        assertThat(testSynthetic.getFixedAssetsCode()).isEqualTo(DEFAULT_FIXED_ASSETS_CODE);
//        assertThat(testSynthetic.getToolsCode()).isEqualTo(UPDATED_TOOLS_CODE);
//        assertThat(testSynthetic.getDebitObject()).isEqualTo(DEFAULT_DEBIT_OBJECT);
//        assertThat(testSynthetic.getCreditObject()).isEqualTo(UPDATED_CREDIT_OBJECT);
//        assertThat(testSynthetic.getUnit()).isEqualTo(DEFAULT_UNIT);
//        assertThat(testSynthetic.getEmployee()).isEqualTo(UPDATED_EMPLOYEE);
//        assertThat(testSynthetic.getBankAccount()).isEqualTo(UPDATED_BANK_ACCOUNT);
//        assertThat(testSynthetic.getItemCost()).isEqualTo(UPDATED_ITEM_COST);
//        assertThat(testSynthetic.getConstruction()).isEqualTo(DEFAULT_CONSTRUCTION);
//        assertThat(testSynthetic.getCostSet()).isEqualTo(UPDATED_COST_SET);
//        assertThat(testSynthetic.getPurchaseOrder()).isEqualTo(DEFAULT_PURCHASE_ORDER);
//        assertThat(testSynthetic.getBuyOrder()).isEqualTo(UPDATED_BUY_ORDER);
//        assertThat(testSynthetic.getPurchaseContract()).isEqualTo(UPDATED_PURCHASE_CONTRACT);
//        assertThat(testSynthetic.getSaleContract()).isEqualTo(UPDATED_SALE_CONTRACT);
//        assertThat(testSynthetic.getStatsCode()).isEqualTo(DEFAULT_STATS_CODE);
//        assertThat(testSynthetic.getExplanation()).isEqualTo(UPDATED_EXPLANATION);
//        assertThat(testSynthetic.getExplanationDetail()).isEqualTo(UPDATED_EXPLANATION_DETAIL);
//        assertThat(testSynthetic.getRecordStatus()).isEqualTo(DEFAULT_RECORD_STATUS);
//        assertThat(testSynthetic.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
//        assertThat(testSynthetic.getKeyUUID()).isEqualTo(UPDATED_KEY_UUID);
//    }
//
//    @Test
//    @Transactional
//    void fullUpdateSyntheticWithPatch() throws Exception {
//        // Initialize the database
//        syntheticRepository.saveAndFlush(synthetic);
//
//        int databaseSizeBeforeUpdate = syntheticRepository.findAll().size();
//
//        // Update the synthetic using partial update
//        Synthetic partialUpdatedSynthetic = new Synthetic();
//        partialUpdatedSynthetic.setId(synthetic.getId());
//
//        partialUpdatedSynthetic
//            .voucherType(UPDATED_VOUCHER_TYPE)
//            .voucherTypeNo(UPDATED_VOUCHER_TYPE_NO)
//            .voucherNo(UPDATED_VOUCHER_NO)
//            .voucherDate(UPDATED_VOUCHER_DATE)
//            .accountingDate(UPDATED_ACCOUNTING_DATE)
//            .invoiceNo(UPDATED_INVOICE_NO)
//            .invoiceDate(UPDATED_INVOICE_DATE)
//            .debitAccount(UPDATED_DEBIT_ACCOUNT)
//            .creditAccount(UPDATED_CREDIT_ACCOUNT)
//            .currencyType(UPDATED_CURRENCY_TYPE)
//            .currency(UPDATED_CURRENCY)
//            .materialGoodCode(UPDATED_MATERIAL_GOOD_CODE)
//            .materialGoodName(UPDATED_MATERIAL_GOOD_NAME)
//            .storageIn(UPDATED_STORAGE_IN)
//            .storageOut(UPDATED_STORAGE_OUT)
//            .caculationUnit(UPDATED_CACULATION_UNIT)
//            .amount(UPDATED_AMOUNT)
//            .price(UPDATED_PRICE)
//            .tranferRate(UPDATED_TRANFER_RATE)
//            .moneyTranfer(UPDATED_MONEY_TRANFER)
//            .fixedAssetsType(UPDATED_FIXED_ASSETS_TYPE)
//            .fixedAssetsCode(UPDATED_FIXED_ASSETS_CODE)
//            .toolsCode(UPDATED_TOOLS_CODE)
//            .debitObject(UPDATED_DEBIT_OBJECT)
//            .creditObject(UPDATED_CREDIT_OBJECT)
//            .unit(UPDATED_UNIT)
//            .employee(UPDATED_EMPLOYEE)
//            .bankAccount(UPDATED_BANK_ACCOUNT)
//            .itemCost(UPDATED_ITEM_COST)
//            .construction(UPDATED_CONSTRUCTION)
//            .costSet(UPDATED_COST_SET)
//            .purchaseOrder(UPDATED_PURCHASE_ORDER)
//            .buyOrder(UPDATED_BUY_ORDER)
//            .purchaseContract(UPDATED_PURCHASE_CONTRACT)
//            .saleContract(UPDATED_SALE_CONTRACT)
//            .statsCode(UPDATED_STATS_CODE)
//            .explanation(UPDATED_EXPLANATION)
//            .explanationDetail(UPDATED_EXPLANATION_DETAIL)
//            .recordStatus(UPDATED_RECORD_STATUS)
//            .createdDate(UPDATED_CREATED_DATE)
//            .keyUUID(UPDATED_KEY_UUID);
//
//        restSyntheticMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, partialUpdatedSynthetic.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSynthetic))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the Synthetic in the database
//        List<Synthetic> syntheticList = syntheticRepository.findAll();
//        assertThat(syntheticList).hasSize(databaseSizeBeforeUpdate);
//        Synthetic testSynthetic = syntheticList.get(syntheticList.size() - 1);
//        assertThat(testSynthetic.getVoucherType()).isEqualTo(UPDATED_VOUCHER_TYPE);
//        assertThat(testSynthetic.getVoucherTypeNo()).isEqualTo(UPDATED_VOUCHER_TYPE_NO);
//        assertThat(testSynthetic.getVoucherNo()).isEqualTo(UPDATED_VOUCHER_NO);
//        assertThat(testSynthetic.getVoucherDate()).isEqualTo(UPDATED_VOUCHER_DATE);
//        assertThat(testSynthetic.getAccountingDate()).isEqualTo(UPDATED_ACCOUNTING_DATE);
//        assertThat(testSynthetic.getInvoiceNo()).isEqualTo(UPDATED_INVOICE_NO);
//        assertThat(testSynthetic.getInvoiceDate()).isEqualTo(UPDATED_INVOICE_DATE);
//        assertThat(testSynthetic.getDebitAccount()).isEqualTo(UPDATED_DEBIT_ACCOUNT);
//        assertThat(testSynthetic.getCreditAccount()).isEqualTo(UPDATED_CREDIT_ACCOUNT);
//        assertThat(testSynthetic.getCurrencyType()).isEqualTo(UPDATED_CURRENCY_TYPE);
//        assertThat(testSynthetic.getCurrency()).isEqualTo(UPDATED_CURRENCY);
//        assertThat(testSynthetic.getMaterialGoodCode()).isEqualTo(UPDATED_MATERIAL_GOOD_CODE);
//        assertThat(testSynthetic.getMaterialGoodName()).isEqualTo(UPDATED_MATERIAL_GOOD_NAME);
//        assertThat(testSynthetic.getStorageIn()).isEqualTo(UPDATED_STORAGE_IN);
//        assertThat(testSynthetic.getStorageOut()).isEqualTo(UPDATED_STORAGE_OUT);
//        assertThat(testSynthetic.getCaculationUnit()).isEqualTo(UPDATED_CACULATION_UNIT);
//        assertThat(testSynthetic.getAmount()).isEqualTo(UPDATED_AMOUNT);
//        assertThat(testSynthetic.getPrice()).isEqualTo(UPDATED_PRICE);
//        assertThat(testSynthetic.getTranferRate()).isEqualTo(UPDATED_TRANFER_RATE);
//        assertThat(testSynthetic.getMoneyTranfer()).isEqualTo(UPDATED_MONEY_TRANFER);
//        assertThat(testSynthetic.getFixedAssetsType()).isEqualTo(UPDATED_FIXED_ASSETS_TYPE);
//        assertThat(testSynthetic.getFixedAssetsCode()).isEqualTo(UPDATED_FIXED_ASSETS_CODE);
//        assertThat(testSynthetic.getToolsCode()).isEqualTo(UPDATED_TOOLS_CODE);
//        assertThat(testSynthetic.getDebitObject()).isEqualTo(UPDATED_DEBIT_OBJECT);
//        assertThat(testSynthetic.getCreditObject()).isEqualTo(UPDATED_CREDIT_OBJECT);
//        assertThat(testSynthetic.getUnit()).isEqualTo(UPDATED_UNIT);
//        assertThat(testSynthetic.getEmployee()).isEqualTo(UPDATED_EMPLOYEE);
//        assertThat(testSynthetic.getBankAccount()).isEqualTo(UPDATED_BANK_ACCOUNT);
//        assertThat(testSynthetic.getItemCost()).isEqualTo(UPDATED_ITEM_COST);
//        assertThat(testSynthetic.getConstruction()).isEqualTo(UPDATED_CONSTRUCTION);
//        assertThat(testSynthetic.getCostSet()).isEqualTo(UPDATED_COST_SET);
//        assertThat(testSynthetic.getPurchaseOrder()).isEqualTo(UPDATED_PURCHASE_ORDER);
//        assertThat(testSynthetic.getBuyOrder()).isEqualTo(UPDATED_BUY_ORDER);
//        assertThat(testSynthetic.getPurchaseContract()).isEqualTo(UPDATED_PURCHASE_CONTRACT);
//        assertThat(testSynthetic.getSaleContract()).isEqualTo(UPDATED_SALE_CONTRACT);
//        assertThat(testSynthetic.getStatsCode()).isEqualTo(UPDATED_STATS_CODE);
//        assertThat(testSynthetic.getExplanation()).isEqualTo(UPDATED_EXPLANATION);
//        assertThat(testSynthetic.getExplanationDetail()).isEqualTo(UPDATED_EXPLANATION_DETAIL);
//        assertThat(testSynthetic.getRecordStatus()).isEqualTo(UPDATED_RECORD_STATUS);
//        assertThat(testSynthetic.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
//        assertThat(testSynthetic.getKeyUUID()).isEqualTo(UPDATED_KEY_UUID);
//    }
//
//    @Test
//    @Transactional
//    void patchNonExistingSynthetic() throws Exception {
//        int databaseSizeBeforeUpdate = syntheticRepository.findAll().size();
//        synthetic.setId(count.incrementAndGet());
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restSyntheticMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, synthetic.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(synthetic))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the Synthetic in the database
//        List<Synthetic> syntheticList = syntheticRepository.findAll();
//        assertThat(syntheticList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void patchWithIdMismatchSynthetic() throws Exception {
//        int databaseSizeBeforeUpdate = syntheticRepository.findAll().size();
//        synthetic.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restSyntheticMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(synthetic))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the Synthetic in the database
//        List<Synthetic> syntheticList = syntheticRepository.findAll();
//        assertThat(syntheticList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void patchWithMissingIdPathParamSynthetic() throws Exception {
//        int databaseSizeBeforeUpdate = syntheticRepository.findAll().size();
//        synthetic.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restSyntheticMockMvc
//            .perform(
//                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(synthetic))
//            )
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the Synthetic in the database
//        List<Synthetic> syntheticList = syntheticRepository.findAll();
//        assertThat(syntheticList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void deleteSynthetic() throws Exception {
//        // Initialize the database
//        syntheticRepository.saveAndFlush(synthetic);
//
//        int databaseSizeBeforeDelete = syntheticRepository.findAll().size();
//
//        // Delete the synthetic
//        restSyntheticMockMvc
//            .perform(delete(ENTITY_API_URL_ID, synthetic.getId()).accept(MediaType.APPLICATION_JSON))
//            .andExpect(status().isNoContent());
//
//        // Validate the database contains one less item
//        List<Synthetic> syntheticList = syntheticRepository.findAll();
//        assertThat(syntheticList).hasSize(databaseSizeBeforeDelete - 1);
//    }
//}
