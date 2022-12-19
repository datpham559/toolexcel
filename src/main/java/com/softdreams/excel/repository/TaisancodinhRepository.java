package com.softdreams.excel.repository;

import com.softdreams.excel.domain.Taisancodinh;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Taisancodinh entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaisancodinhRepository extends JpaRepository<Taisancodinh, Long> {}
