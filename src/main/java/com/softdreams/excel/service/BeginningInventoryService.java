package com.softdreams.excel.service;

import com.softdreams.excel.domain.BeginningInventory;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link BeginningInventory}.
 */
public interface BeginningInventoryService {
    /**
     * Save a beginningInventory.
     *
     * @param beginningInventory the entity to save.
     * @return the persisted entity.
     */
    BeginningInventory save(BeginningInventory beginningInventory);

    /**
     * Updates a beginningInventory.
     *
     * @param beginningInventory the entity to update.
     * @return the persisted entity.
     */
    BeginningInventory update(BeginningInventory beginningInventory);

    /**
     * Partially updates a beginningInventory.
     *
     * @param beginningInventory the entity to update partially.
     * @return the persisted entity.
     */
    Optional<BeginningInventory> partialUpdate(BeginningInventory beginningInventory);

    /**
     * Get all the beginningInventories.
     *
     * @return the list of entities.
     */
    List<BeginningInventory> findAll();

    /**
     * Get the "id" beginningInventory.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BeginningInventory> findOne(Long id);

    /**
     * Delete the "id" beginningInventory.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    void saveToBeginningInventory(MultipartFile file);
}
