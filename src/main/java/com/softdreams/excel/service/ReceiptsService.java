package com.softdreams.excel.service;

import com.softdreams.excel.domain.Merchandise;

import java.io.ByteArrayInputStream;

/**
 * Service Interface for managing {@link Merchandise}.
 */
public interface ReceiptsService {
//    /**
//     * Save a merchandise.
//     *
//     * @param merchandise the entity to save.
//     * @return the persisted entity.
//     */
//    Synthetic save(Synthetic synthetic);
//
//    /**
//     * Updates a merchandise.
//     *
//     * @param merchandise the entity to update.
//     * @return the persisted entity.
//     */
//    Synthetic update(Synthetic synthetic);
//
//    /**
//     * Partially updates a merchandise.
//     *
//     * @param merchandise the entity to update partially.
//     * @return the persisted entity.
//     */
//    Optional<Synthetic> partialUpdate(Synthetic synthetic);
//
//    /**
//     * Get all the merchandises.
//     *
//     * @return the list of entities.
//     */
//    List<Synthetic> findAll();
//
//    /**
//     * Get the "id" merchandise.
//     *
//     * @param id the id of the entity.
//     * @return the entity.
//     */
//    Optional<Synthetic> findOne(Long id);
//
//    /**
//     * Delete the "id" merchandise.
//     *
//     * @param id the id of the entity.
//     */
//    void delete(Long id);

    ByteArrayInputStream exportExcel();
}
