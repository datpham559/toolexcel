package com.softdreams.excel.repository;

import com.softdreams.excel.domain.DmNCC;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the DmNCC entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DmNCCRepository extends JpaRepository<DmNCC, Long> {
    @Modifying
    @Query(value = "delete from dmncc where keyUUID = :keyUUID",nativeQuery = true)
    void deleteByKeyUUID(String keyUUID);

    @Query(value = "select * from dmncc where keyUUID = :keyUUID",nativeQuery = true)
    List<DmNCC> getDmNCCByKeyUUID(String keyUUID);
}
