package com.softdreams.excel.service;

import com.softdreams.excel.domain.Congcudungcu;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service Interface for managing {@link Congcudungcu}.
 */
public interface CongcudungcuService {
    /**
     * Save a congcudungcu.
     *
     * @param congcudungcu the entity to save.
     * @return the persisted entity.
     */
    Congcudungcu save(Congcudungcu congcudungcu);

    /**
     * Updates a congcudungcu.
     *
     * @param congcudungcu the entity to update.
     * @return the persisted entity.
     */
    Congcudungcu update(Congcudungcu congcudungcu);

    /**
     * Partially updates a congcudungcu.
     *
     * @param congcudungcu the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Congcudungcu> partialUpdate(Congcudungcu congcudungcu);

    /**
     * Get all the congcudungcus.
     *
     * @return the list of entities.
     */
    List<Congcudungcu> findAll();

    /**
     * Get the "id" congcudungcu.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Congcudungcu> findOne(Long id);

    /**
     * Delete the "id" congcudungcu.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    void saveCongcudungcu(MultipartFile file);

    ByteArrayInputStream exportCongcudungcu();
}
