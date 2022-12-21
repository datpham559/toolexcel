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
public interface SyntheticRepository extends JpaRepository<Synthetic, Long>, SyntheticRepositoryCustom {
    @Query(value = "select * from synthetic where voucherTypeNo = 7 order by voucherNo", nativeQuery = true)
    List<Synthetic> getAccreditativeOrderByVoucherNoOfPayment();

    @Query(value = "select * from synthetic where voucherTypeNo = 8 order by voucherNo", nativeQuery = true)
    List<Synthetic> getAccreditativeOrderByVoucherNoOfReceipts();
}
