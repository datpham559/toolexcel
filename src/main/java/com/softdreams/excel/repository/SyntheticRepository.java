package com.softdreams.excel.repository;

import com.softdreams.excel.domain.Synthetic;
import com.softdreams.excel.repository.custom.SyntheticRepositoryCustom;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Synthetic entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SyntheticRepository extends JpaRepository<Synthetic, Long> {
    @Query(value = "select * from synthetic where voucherNoType = '' order by voucherNo", nativeQuery = true)
    List<Synthetic> getAccreditativeOrderByVoucherNo();
}
