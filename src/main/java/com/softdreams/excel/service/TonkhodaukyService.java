package com.softdreams.excel.service;

import com.softdreams.excel.domain.Tonkhodauky;
import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service Interface for managing {@link Tonkhodauky}.
 */
public interface TonkhodaukyService {
    /**
     * Save a tonkhodauky.
     *
     * @param tonkhodauky the entity to save.
     * @return the persisted entity.
     */
    Tonkhodauky save(Tonkhodauky tonkhodauky);

    /**
     * Updates a tonkhodauky.
     *
     * @param tonkhodauky the entity to update.
     * @return the persisted entity.
     */
    Tonkhodauky update(Tonkhodauky tonkhodauky);

    /**
     * Partially updates a tonkhodauky.
     *
     * @param tonkhodauky the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Tonkhodauky> partialUpdate(Tonkhodauky tonkhodauky);

    /**
     * Get all the tonkhodaukies.
     *
     * @return the list of entities.
     */
    List<Tonkhodauky> findAll();

    /**
     * Get the "id" tonkhodauky.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Tonkhodauky> findOne(Long id);

    /**
     * Delete the "id" tonkhodauky.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    public void saveTonkhodauky(MultipartFile file);
}
