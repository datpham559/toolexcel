package com.softdreams.excel.repository;

import com.softdreams.excel.domain.Tonkhodauky;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Tonkhodauky entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TonkhodaukyRepository extends JpaRepository<Tonkhodauky, Long> {}
