package com.softdreams.excel.repository;

import com.softdreams.excel.domain.Customer;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Spring Data JPA repository for the Customer entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Modifying
    @Query(value = "delete from customers where keyUUID = :keyUUID",nativeQuery = true)
    void deleteByKeyUUID(String keyUUID);

    @Query(value = "select * from customers where keyUUID = :keyUUID",nativeQuery = true)
    List<Customer> getCustomersByKeyUUID(String keyUUID);
}
