package com.softdreams.excel.service;

import com.softdreams.excel.domain.DmNCC;
import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service Interface for managing {@link DmNCC}.
 */
public interface DmNCCService {
    /**
     * Save a dmNCC.
     *
     * @param dmNCC the entity to save.
     * @return the persisted entity.
     */
    DmNCC save(DmNCC dmNCC);

    /**
     * Updates a dmNCC.
     *
     * @param dmNCC the entity to update.
     * @return the persisted entity.
     */
    DmNCC update(DmNCC dmNCC);

    /**
     * Partially updates a dmNCC.
     *
     * @param dmNCC the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DmNCC> partialUpdate(DmNCC dmNCC);

    /**
     * Get all the dmNCCS.
     *
     * @return the list of entities.
     */
    List<DmNCC> findAll();

    /**
     * Get the "id" dmNCC.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DmNCC> findOne(Long id);

    /**
     * Delete the "id" dmNCC.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    void saveToDmNcc(MultipartFile file);
}
