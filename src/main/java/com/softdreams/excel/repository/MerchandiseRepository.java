package com.softdreams.excel.repository;

import com.softdreams.excel.domain.Merchandise;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Merchandise entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MerchandiseRepository extends JpaRepository<Merchandise, Long> {
    @Modifying
    @Query(value = "delete from merchandise where keyUUID = :keyUUID", nativeQuery = true)
    void deleteByKeyUUID(String keyUUID);

    @Query(value = "select * from merchandise where keyUUID = :keyUUID", nativeQuery = true)
    List<Merchandise> getMerchandiseByKeyUUID(String keyUUID);
}
