package com.softdreams.excel.repository;

import com.softdreams.excel.domain.Synthetic;
import com.softdreams.excel.repository.custom.SyntheticRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the Synthetic entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SyntheticRepository extends JpaRepository<Synthetic, Long>, SyntheticRepositoryCustom {
    @Query(value = "select * from synthetic where voucherTypeNo = 13 order by voucherNo", nativeQuery = true)
    List<Synthetic> getAccreditativeOrderByVoucherNo();
}
