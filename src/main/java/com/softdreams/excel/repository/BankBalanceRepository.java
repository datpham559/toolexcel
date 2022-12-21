package com.softdreams.excel.repository;

import com.softdreams.excel.domain.BankBalance;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the BankBalance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BankBalanceRepository extends JpaRepository<BankBalance, Long> {
    @Modifying
    @Query(value = "delete from bank_balance where keyUUID = :keyUUID", nativeQuery = true)
    void deleteByKeyUUID(String keyUUID);

    @Query(value = "select * from bank_balance where keyUUID = :keyUUID", nativeQuery = true)
    List<BankBalance> getBankBalanceByKeyUUID(String keyUUID);
}
