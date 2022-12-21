package com.softdreams.excel.service.impl;

import com.softdreams.excel.domain.Merchandise;
import com.softdreams.excel.helper.MerchandiseExcelHelper;
import com.softdreams.excel.repository.MerchandiseRepository;
import com.softdreams.excel.service.MerchandiseService;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service Implementation for managing {@link Merchandise}.
 */
@Service
@Transactional
public class MerchandiseServiceImpl implements MerchandiseService {

    private final Logger log = LoggerFactory.getLogger(MerchandiseServiceImpl.class);

    private final MerchandiseRepository merchandiseRepository;

    public MerchandiseServiceImpl(MerchandiseRepository merchandiseRepository) {
        this.merchandiseRepository = merchandiseRepository;
    }

    @Override
    public Merchandise save(Merchandise merchandise) {
        log.debug("Request to save Merchandise : {}", merchandise);
        return merchandiseRepository.save(merchandise);
    }

    @Override
    public Merchandise update(Merchandise merchandise) {
        log.debug("Request to update Merchandise : {}", merchandise);
        return merchandiseRepository.save(merchandise);
    }

    @Override
    public Optional<Merchandise> partialUpdate(Merchandise merchandise) {
        log.debug("Request to partially update Merchandise : {}", merchandise);

        return merchandiseRepository
            .findById(merchandise.getId())
            .map(existingMerchandise -> {
                if (merchandise.getCode() != null) {
                    existingMerchandise.setCode(merchandise.getCode());
                }
                if (merchandise.getName() != null) {
                    existingMerchandise.setName(merchandise.getName());
                }
                if (merchandise.getNature() != null) {
                    existingMerchandise.setNature(merchandise.getNature());
                }
                if (merchandise.getGroup_vthh() != null) {
                    existingMerchandise.setGroup_vthh(merchandise.getGroup_vthh());
                }
                if (merchandise.getDescribe() != null) {
                    existingMerchandise.setDescribe(merchandise.getDescribe());
                }
                if (merchandise.getExplain_buy() != null) {
                    existingMerchandise.setExplain_buy(merchandise.getExplain_buy());
                }
                if (merchandise.getExplain_sell() != null) {
                    existingMerchandise.setExplain_sell(merchandise.getExplain_sell());
                }
                if (merchandise.getMain_dvt() != null) {
                    existingMerchandise.setMain_dvt(merchandise.getMain_dvt());
                }
                if (merchandise.getWarranty_period() != null) {
                    existingMerchandise.setWarranty_period(merchandise.getWarranty_period());
                }
                if (merchandise.getQuantity_inventory() != null) {
                    existingMerchandise.setQuantity_inventory(merchandise.getQuantity_inventory());
                }
                if (merchandise.getSource() != null) {
                    existingMerchandise.setSource(merchandise.getSource());
                }
                if (merchandise.getImplicitly_repository() != null) {
                    existingMerchandise.setImplicitly_repository(merchandise.getImplicitly_repository());
                }
                if (merchandise.getWarehouse_account() != null) {
                    existingMerchandise.setWarehouse_account(merchandise.getWarehouse_account());
                }
                if (merchandise.getExpense_account() != null) {
                    existingMerchandise.setExpense_account(merchandise.getExpense_account());
                }
                if (merchandise.getIncome_account() != null) {
                    existingMerchandise.setIncome_account(merchandise.getIncome_account());
                }
                if (merchandise.getDiscount_account() != null) {
                    existingMerchandise.setDiscount_account(merchandise.getDiscount_account());
                }
                if (merchandise.getSale_account() != null) {
                    existingMerchandise.setSale_account(merchandise.getSale_account());
                }
                if (merchandise.getReturn_account() != null) {
                    existingMerchandise.setReturn_account(merchandise.getReturn_account());
                }
                if (merchandise.getRate_ckmh() != null) {
                    existingMerchandise.setRate_ckmh(merchandise.getRate_ckmh());
                }
                if (merchandise.getFixed_purchase_price() != null) {
                    existingMerchandise.setFixed_purchase_price(merchandise.getFixed_purchase_price());
                }
                if (merchandise.getLatest_purchase_price() != null) {
                    existingMerchandise.setLatest_purchase_price(merchandise.getLatest_purchase_price());
                }
                if (merchandise.getUnit_price_sell_1() != null) {
                    existingMerchandise.setUnit_price_sell_1(merchandise.getUnit_price_sell_1());
                }
                if (merchandise.getUnit_price_sell_2() != null) {
                    existingMerchandise.setUnit_price_sell_2(merchandise.getUnit_price_sell_2());
                }
                if (merchandise.getUnit_price_sell_3() != null) {
                    existingMerchandise.setUnit_price_sell_3(merchandise.getUnit_price_sell_3());
                }
                if (merchandise.getFixed_unit_price() != null) {
                    existingMerchandise.setFixed_unit_price(merchandise.getFixed_unit_price());
                }
                if (merchandise.getUnit_price_after_tax() != null) {
                    existingMerchandise.setUnit_price_after_tax(merchandise.getUnit_price_after_tax());
                }
                if (merchandise.getTax_rate_gtgt() != null) {
                    existingMerchandise.setTax_rate_gtgt(merchandise.getTax_rate_gtgt());
                }
                if (merchandise.getTax_rate_nk() != null) {
                    existingMerchandise.setTax_rate_nk(merchandise.getTax_rate_nk());
                }
                if (merchandise.getTax_rate_xk() != null) {
                    existingMerchandise.setTax_rate_xk(merchandise.getTax_rate_xk());
                }
                if (merchandise.getGroup_hhdv_taxable_ttdb() != null) {
                    existingMerchandise.setGroup_hhdv_taxable_ttdb(merchandise.getGroup_hhdv_taxable_ttdb());
                }
                if (merchandise.getUnfollow() != null) {
                    existingMerchandise.setUnfollow(merchandise.getUnfollow());
                }
                if (merchandise.getInventory_number() != null) {
                    existingMerchandise.setInventory_number(merchandise.getInventory_number());
                }
                if (merchandise.getCharacteristic() != null) {
                    existingMerchandise.setCharacteristic(merchandise.getCharacteristic());
                }
                if (merchandise.getInventory_value() != null) {
                    existingMerchandise.setInventory_value(merchandise.getInventory_value());
                }
                if (merchandise.getFollow() != null) {
                    existingMerchandise.setFollow(merchandise.getFollow());
                }
                if (merchandise.getDiscount() != null) {
                    existingMerchandise.setDiscount(merchandise.getDiscount());
                }
                if (merchandise.getFrom_amount() != null) {
                    existingMerchandise.setFrom_amount(merchandise.getFrom_amount());
                }
                if (merchandise.getTo_amount() != null) {
                    existingMerchandise.setTo_amount(merchandise.getTo_amount());
                }
                if (merchandise.getPercent_discount() != null) {
                    existingMerchandise.setPercent_discount(merchandise.getPercent_discount());
                }
                if (merchandise.getDiscount_amount() != null) {
                    existingMerchandise.setDiscount_amount(merchandise.getDiscount_amount());
                }
                if (merchandise.getConversion_unit() != null) {
                    existingMerchandise.setConversion_unit(merchandise.getConversion_unit());
                }
                if (merchandise.getPrimary_unit_conversion_rate() != null) {
                    existingMerchandise.setPrimary_unit_conversion_rate(merchandise.getPrimary_unit_conversion_rate());
                }
                if (merchandise.getCalculation() != null) {
                    existingMerchandise.setCalculation(merchandise.getCalculation());
                }
                if (merchandise.getDescribe1() != null) {
                    existingMerchandise.setDescribe1(merchandise.getDescribe1());
                }
                if (merchandise.getUnit_price_1() != null) {
                    existingMerchandise.setUnit_price_1(merchandise.getUnit_price_1());
                }
                if (merchandise.getUnit_price_2() != null) {
                    existingMerchandise.setUnit_price_2(merchandise.getUnit_price_2());
                }
                if (merchandise.getUnit_price_3() != null) {
                    existingMerchandise.setUnit_price_3(merchandise.getUnit_price_3());
                }
                if (merchandise.getFixed_unit_price1() != null) {
                    existingMerchandise.setFixed_unit_price1(merchandise.getFixed_unit_price1());
                }
                if (merchandise.getMaterial_code() != null) {
                    existingMerchandise.setMaterial_code(merchandise.getMaterial_code());
                }
                if (merchandise.getMaterial_name() != null) {
                    existingMerchandise.setMaterial_name(merchandise.getMaterial_name());
                }
                if (merchandise.getDvt() != null) {
                    existingMerchandise.setDvt(merchandise.getDvt());
                }
                if (merchandise.getAmount() != null) {
                    existingMerchandise.setAmount(merchandise.getAmount());
                }
                if (merchandise.getSpecification_code() != null) {
                    existingMerchandise.setSpecification_code(merchandise.getSpecification_code());
                }
                if (merchandise.getDisplay_name() != null) {
                    existingMerchandise.setDisplay_name(merchandise.getDisplay_name());
                }
                if (merchandise.getAllow_same() != null) {
                    existingMerchandise.setAllow_same(merchandise.getAllow_same());
                }
                if (merchandise.getCreated_date() != null) {
                    existingMerchandise.setCreated_date(merchandise.getCreated_date());
                }
                if (merchandise.getKeyUUID() != null) {
                    existingMerchandise.setKeyUUID(merchandise.getKeyUUID());
                }

                return existingMerchandise;
            })
            .map(merchandiseRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Merchandise> findAll() {
        log.debug("Request to get all Merchandises");
        return merchandiseRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Merchandise> findOne(Long id) {
        log.debug("Request to get Merchandise : {}", id);
        return merchandiseRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Merchandise : {}", id);
        merchandiseRepository.deleteById(id);
    }

    @Override
    public void saveToMerchandise(MultipartFile file) {
        try {
            List<Merchandise> merchandises = MerchandiseExcelHelper.excelToMerchandise(file.getInputStream());
            String key = UUID.randomUUID().toString();
            LocalDate date = LocalDate.now();
            for (Merchandise merchandise : merchandises) {
                merchandise.setCreated_date(date);
                merchandise.setKeyUUID(key);
            }
            merchandiseRepository.saveAll(merchandises);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}
