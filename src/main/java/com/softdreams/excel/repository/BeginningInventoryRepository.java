package com.softdreams.excel.repository;

import com.softdreams.excel.domain.BeginningInventory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the BeginningInventory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BeginningInventoryRepository extends JpaRepository<BeginningInventory, Long> {
    @Modifying
    @Query(value = "delete from beginning_inventory where keyUUID = :keyUUID",nativeQuery = true)
    void deleteByKeyUUID(String keyUUID);

    @Query(value = "select * from beginning_inventory where keyUUID = :keyUUID",nativeQuery = true)
    List<BeginningInventory> getBeginningInventoriesByKeyUUID(String keyUUID);
}
