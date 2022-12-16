package com.softdreams.excel.service;

import com.softdreams.excel.domain.Taisancodinh;
import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service Interface for managing {@link Taisancodinh}.
 */
public interface TaisancodinhService {
    /**
     * Save a taisancodinh.
     *
     * @param taisancodinh the entity to save.
     * @return the persisted entity.
     */
    Taisancodinh save(Taisancodinh taisancodinh);

    /**
     * Updates a taisancodinh.
     *
     * @param taisancodinh the entity to update.
     * @return the persisted entity.
     */
    Taisancodinh update(Taisancodinh taisancodinh);

    /**
     * Partially updates a taisancodinh.
     *
     * @param taisancodinh the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Taisancodinh> partialUpdate(Taisancodinh taisancodinh);

    /**
     * Get all the taisancodinhs.
     *
     * @return the list of entities.
     */
    List<Taisancodinh> findAll();

    /**
     * Get the "id" taisancodinh.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Taisancodinh> findOne(Long id);

    /**
     * Delete the "id" taisancodinh.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    void saveTaisancodinh(MultipartFile file);
}
