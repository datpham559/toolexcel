package com.softdreams.excel.repository;

import com.softdreams.excel.domain.Congnophaitra;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Congnophaitra entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CongnophaitraRepository extends JpaRepository<Congnophaitra, Long> {}
