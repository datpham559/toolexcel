package com.softdreams.excel.service;

import com.softdreams.excel.domain.Congnophaithu;
import com.softdreams.excel.helper.ExcelHelper;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service Interface for managing {@link Congnophaithu}.
 */
public interface CongnophaithuService {
    /**
     * Save a congnophaithu.
     *
     * @param congnophaithu the entity to save.
     * @return the persisted entity.
     */
    Congnophaithu save(Congnophaithu congnophaithu);

    /**
     * Updates a congnophaithu.
     *
     * @param congnophaithu the entity to update.
     * @return the persisted entity.
     */
    Congnophaithu update(Congnophaithu congnophaithu);

    /**
     * Partially updates a congnophaithu.
     *
     * @param congnophaithu the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Congnophaithu> partialUpdate(Congnophaithu congnophaithu);

    /**
     * Get all the congnophaithus.
     *
     * @return the list of entities.
     */
    List<Congnophaithu> findAll();

    /**
     * Get the "id" congnophaithu.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Congnophaithu> findOne(Long id);

    /**
     * Delete the "id" congnophaithu.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    public void saveCongnophaithu(MultipartFile file);
}
