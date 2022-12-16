package com.softdreams.excel.repository;

import com.softdreams.excel.domain.Congnophaithu;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Congnophaithu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CongnophaithuRepository extends JpaRepository<Congnophaithu, Long> {}
