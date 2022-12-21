package com.softdreams.excel.service;

import com.softdreams.excel.domain.BankBalance;
import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service Interface for managing {@link BankBalance}.
 */
public interface BankBalanceService {
    /**
     * Save a bankBalance.
     *
     * @param bankBalance the entity to save.
     * @return the persisted entity.
     */
    BankBalance save(BankBalance bankBalance);

    /**
     * Updates a bankBalance.
     *
     * @param bankBalance the entity to update.
     * @return the persisted entity.
     */
    BankBalance update(BankBalance bankBalance);

    /**
     * Partially updates a bankBalance.
     *
     * @param bankBalance the entity to update partially.
     * @return the persisted entity.
     */
    Optional<BankBalance> partialUpdate(BankBalance bankBalance);

    /**
     * Get all the bankBalances.
     *
     * @return the list of entities.
     */
    List<BankBalance> findAll();

    /**
     * Get the "id" bankBalance.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BankBalance> findOne(Long id);

    /**
     * Delete the "id" bankBalance.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    void saveToBankBlance(MultipartFile file);
}
