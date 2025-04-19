package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class SaleService {

	private final SaleRepository repository;

    public SaleService(SaleRepository repository) {
        this.repository = repository;
    }

    public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleReportDTO> getReport(String minDate, String maxDate, String name, Pageable pageable) {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

		LocalDate max = (maxDate == null || maxDate.isBlank()) ? today : LocalDate.parse(maxDate, DateTimeFormatter.ISO_DATE);
		LocalDate min = (minDate == null || minDate.isBlank()) ? max.minusYears(1) : LocalDate.parse(minDate, DateTimeFormatter.ISO_DATE);
		String sellerName = (name == null || name.isBlank()) ? "" : name;

		Page<Sale> result = repository.findSales(min, max, sellerName, pageable);
		return result.map(SaleReportDTO::new);
	}

	public Page<SaleMinDTO> getSummary(String minDate, String maxDate) {

		return null;
	}
}
