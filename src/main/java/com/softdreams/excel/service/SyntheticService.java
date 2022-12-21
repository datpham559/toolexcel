package com.softdreams.excel.service;

import com.softdreams.excel.domain.Synthetic;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service Interface for managing {@link Synthetic}.
 */
public interface SyntheticService {
    /**
     * Save a synthetic.
     *
     * @param synthetic the entity to save.
     * @return the persisted entity.
     */
    Synthetic save(Synthetic synthetic);

    /**
     * Updates a synthetic.
     *
     * @param synthetic the entity to update.
     * @return the persisted entity.
     */
    Synthetic update(Synthetic synthetic);

    /**
     * Partially updates a synthetic.
     *
     * @param synthetic the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Synthetic> partialUpdate(Synthetic synthetic);

    /**
     * Get all the synthetics.
     *
     * @return the list of entities.
     */
    List<Synthetic> findAll();

    /**
     * Get the "id" synthetic.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Synthetic> findOne(Long id);

    /**
     * Delete the "id" synthetic.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
    void saveToSynthetic(MultipartFile file);

    ByteArrayInputStream exportDebitNote(int voucherTypeNo, String keyUUID);
}
