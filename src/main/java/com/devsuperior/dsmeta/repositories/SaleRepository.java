package com.devsuperior.dsmeta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devsuperior.dsmeta.entities.Sale;

import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<Sale, Long> {

	@Query("SELECT s FROM Sale s WHERE s.date BETWEEN :minDate AND :maxDate AND LOWER(s.seller.name) LIKE LOWER(CONCAT('%', :name, '%'))")
	Page<Sale> findSales(@Param("minDate") LocalDate minDate, @Param("maxDate") LocalDate maxDate, @Param("name") String name, Pageable pageable);
}

