package com.softdreams.excel.repository;

import com.softdreams.excel.domain.Congcudungcu;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Congcudungcu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CongcudungcuRepository extends JpaRepository<Congcudungcu, Long> {}
