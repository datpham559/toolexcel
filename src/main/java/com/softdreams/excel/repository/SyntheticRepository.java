package com.softdreams.excel.repository;

import com.softdreams.excel.domain.Synthetic;
import com.softdreams.excel.repository.custom.SyntheticRepositoryCustom;
import java.util.List;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
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

    @Query(value = "select * from synthetic where voucherTypeNo = 13 order by voucherNo", nativeQuery = true)
    List<Synthetic> getAccreditativeOrderByVoucherNo13();

    @Query(value = "select * from synthetic where voucherTypeNo = 9 order by voucherNo", nativeQuery = true)
    List<Synthetic> getAccreditativeOrderByVoucherNo9();
}
