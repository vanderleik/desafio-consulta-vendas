package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSumaryDTO;
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

	public static final LocalDate TODAY = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
	private final SaleRepository repository;

    public SaleService(SaleRepository repository) {
        this.repository = repository;
    }

    public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		if (result.isEmpty()) {
			return null;
		}
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleReportDTO> getReport(String minDate, String maxDate, String name, Pageable pageable) {
		LocalDate max = (maxDate == null || maxDate.isBlank()) ? TODAY : LocalDate.parse(maxDate, DateTimeFormatter.ISO_DATE);
		LocalDate min = (minDate == null || minDate.isBlank()) ? max.minusYears(1) : LocalDate.parse(minDate, DateTimeFormatter.ISO_DATE);
		String sellerName = (name == null || name.isBlank()) ? "" : name;

		Page<Sale> result = repository.findSales(min, max, sellerName, pageable);
		return result.map(SaleReportDTO::new);
	}

	public Page<SaleSumaryDTO> getSummary(String minDate, String maxDate, Pageable pageable) {
		LocalDate max = (maxDate == null || maxDate.isBlank()) ? TODAY : LocalDate.parse(maxDate, DateTimeFormatter.ISO_DATE);
		LocalDate min = (minDate == null || minDate.isBlank()) ? max.minusYears(1) : LocalDate.parse(minDate, DateTimeFormatter.ISO_DATE);

		return repository.getSalesSummary(min, max, pageable);
	}
}
