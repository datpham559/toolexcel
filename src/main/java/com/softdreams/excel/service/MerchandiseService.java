package com.softdreams.excel.service;

import com.softdreams.excel.domain.Merchandise;
import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service Interface for managing {@link Merchandise}.
 */
public interface MerchandiseService {
    /**
     * Save a merchandise.
     *
     * @param merchandise the entity to save.
     * @return the persisted entity.
     */
    Merchandise save(Merchandise merchandise);

    /**
     * Updates a merchandise.
     *
     * @param merchandise the entity to update.
     * @return the persisted entity.
     */
    Merchandise update(Merchandise merchandise);

    /**
     * Partially updates a merchandise.
     *
     * @param merchandise the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Merchandise> partialUpdate(Merchandise merchandise);

    /**
     * Get all the merchandises.
     *
     * @return the list of entities.
     */
    List<Merchandise> findAll();

    /**
     * Get the "id" merchandise.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Merchandise> findOne(Long id);

    /**
     * Delete the "id" merchandise.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    void saveToMerchandise(MultipartFile file);
}
