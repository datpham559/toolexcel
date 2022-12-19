package com.softdreams.excel.service;

import com.softdreams.excel.domain.Congnophaitra;
import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service Interface for managing {@link Congnophaitra}.
 */
public interface CongnophaitraService {
    /**
     * Save a congnophaitra.
     *
     * @param congnophaitra the entity to save.
     * @return the persisted entity.
     */
    Congnophaitra save(Congnophaitra congnophaitra);

    /**
     * Updates a congnophaitra.
     *
     * @param congnophaitra the entity to update.
     * @return the persisted entity.
     */
    Congnophaitra update(Congnophaitra congnophaitra);

    /**
     * Partially updates a congnophaitra.
     *
     * @param congnophaitra the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Congnophaitra> partialUpdate(Congnophaitra congnophaitra);

    /**
     * Get all the congnophaitras.
     *
     * @return the list of entities.
     */
    List<Congnophaitra> findAll();

    /**
     * Get the "id" congnophaitra.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Congnophaitra> findOne(Long id);

    /**
     * Delete the "id" congnophaitra.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    void saveCongnophaitra(MultipartFile file);
}
